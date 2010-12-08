/*
 * Copyright (C) 2010 The AdoHive Team
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/**
 * 
 */
package de.unistuttgart.iste.se.adohive.controller.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveArgumentException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveDatabaseException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAdoHiveModel;
import de.unistuttgart.iste.se.adohive.model.internal.AdoClass;
import de.unistuttgart.iste.se.adohive.model.internal.AdoMethod;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

/**
 * @author Team AdoHive
 */
abstract class JdbcAdoHiveManager<T extends IAdoHiveModel<T>> implements IAdoHiveManager<T> {
	
	protected SqlDialect dialect;
	
	protected Connection con;
	protected Class<? extends T> modelClass;
	protected Class<T> interfaceClass;
	
	protected HashMap<String, JdbcColumn> columnMap = new HashMap<String, JdbcColumn>();
	//Just for easier access, store a list of primary keys
	protected ArrayList<JdbcColumn> primaryKeys = new ArrayList<JdbcColumn>();
	protected String tableName;
	
	protected boolean hasAutoIncrement = false;
	protected PreparedStatement insertIncrementStatement;
	protected PreparedStatement insertAllStatement;
	protected PreparedStatement updateStatement;
	protected PreparedStatement removeStatement;
	protected PreparedStatement containStatement;

	protected boolean alwaysAutoIncrement;
	
	protected List<JdbcColumn> updateColumns = new ArrayList<JdbcColumn>();
	
	public JdbcAdoHiveManager(Class<? extends T> modelClass, Class<T> interfaceClass, 
			Connection con, SqlDialect dialect, boolean alwaysAutoIncrement) throws AdoHiveException
	{
		this.modelClass = modelClass;
		this.interfaceClass = interfaceClass;
		this.con = con;
		this.dialect = dialect;
		this.alwaysAutoIncrement = alwaysAutoIncrement;
		buildColumnMapping();
		createStatements();
	}
	
	//IAdoHiveManager methods
	@Override
	public List<T> getAll() throws AdoHiveException	{
		return executeQuery(createQuery((List<Pair<String, Object>>)null));
	}
	
	@Override
	public T getById(int id) throws AdoHiveException {
		if(primaryKeys.size() != 1)
			throw new AdoHiveException();
		List<Pair<String, Object>> pairlist = new ArrayList<Pair<String,Object>>();
		//TODO: change this to work with any single integer primary key
		pairlist.add(new Pair<String, Object>(dialect.quote("ID"), id));
		List<T> result = executeQuery(createQuery(pairlist));
		try {
		return result.get(0);
		} catch (IndexOutOfBoundsException ioobe) {
			return null;
		}
	}
	
	@Override
	public T getByKeys(Object... o) throws AdoHiveException {
		if(o.length != 1 || !(o[0] instanceof Integer))
			throw new AdoHiveArgumentException("Keys not supported");
		return getById((Integer)o[0]);
	}
	
	@Override
	public boolean update(T obj) throws AdoHiveException {
		try {
			if(!modelClass.equals(obj.getClass()))
				obj = modelClass.getConstructor(interfaceClass).newInstance(obj);
			fillPrepared(updateStatement, updateColumns, obj);			
			return updateStatement.executeUpdate() == 1;
		} catch (IllegalAccessException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InvocationTargetException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InstantiationException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (NoSuchMethodException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	//IAdoHiveList methods
	@Override
	public int size() throws AdoHiveException
	{
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + dialect.quote(tableName));
			if(rs.next())
				return rs.getInt(1);
			else
				throw new AdoHiveDatabaseException();
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	@Override
	public boolean isEmpty() throws AdoHiveException {
		//lazy implementation
		return size() == 0;
	}
	
	@Override
	public boolean contains(T obj) throws AdoHiveException {
		
		
		List<Pair<String, Object>> params = new ArrayList<Pair<String, Object>>();
		StringBuilder sb = new StringBuilder();
		try {
			if(!modelClass.equals(obj.getClass()))
				obj = modelClass.getConstructor(interfaceClass).newInstance(obj);
			for(JdbcColumn column : primaryKeys){
				Pair<String, Object> callpair = new Pair<String, Object>(dialect.quote(column.getColumnName()), column.getGetMethod().invoke(obj));
				//check if primary keys are null, if they are, we are done
				if (callpair.snd() == null)
					return false;
				params.add(callpair);
			}
			sb.append("SELECT * FROM ");
			dialect.quote(tableName, sb);
			sb.append(" WHERE");
			sb.append(joinPrepared(params, " AND"));
			PreparedStatement stmt = con.prepareStatement(sb.toString());
			fillPrepared(stmt, params);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SecurityException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalArgumentException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalAccessException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InvocationTargetException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InstantiationException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (NoSuchMethodException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	@Override
	public boolean add(T o) throws AdoHiveException {
		
		try {
			//wrap o in internal Class if o is not already internal
			T wrap;
			if(!modelClass.equals(o.getClass()))
				wrap = modelClass.getConstructor(interfaceClass).newInstance(o);
			else
				wrap = o;
			executeInsert(wrap);
			return true;
		} catch (IllegalArgumentException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (SecurityException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InstantiationException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalAccessException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InvocationTargetException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (NoSuchMethodException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	public boolean remove(T obj) throws AdoHiveException {
		
		try {
			if(!modelClass.equals(obj.getClass()))
				obj = modelClass.getConstructor(interfaceClass).newInstance(obj);

			fillPrepared(removeStatement, primaryKeys, obj);
			int result = removeStatement.executeUpdate();
			return result >= 1;
		} catch (SecurityException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalArgumentException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalAccessException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InvocationTargetException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InstantiationException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (NoSuchMethodException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	@Override
	public void clear() throws AdoHiveException {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM " + dialect.quote(tableName));
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	@Override
	public T get(int index) throws AdoHiveException {
		if(index < 0)
			throw new IndexOutOfBoundsException(index + " < 0!");
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * FROM "+dialect.quote(tableName)+" OFFSET "+ index +" ROWS FETCH NEXT ROW ONLY");
		} catch (SQLException e) {
				throw new AdoHiveDatabaseException(e);
		}
		//TODO: DO NOT TRY AT HOME
		try {
			return executeQuery(stmt).get(0);
		} catch (AdoHiveDatabaseException ade) {
			try {
				stmt = con.prepareStatement("SELECT * FROM" + dialect.quote(tableName) + " LIMIT 1 OFFSET "+ index);
			} catch (SQLException e) {
				throw new AdoHiveDatabaseException(e);
			}
			return executeQuery(stmt).get(0);
		}
		
	}
	
	@Override
	public List<T> subList(int fromIndex, int toIndex) throws AdoHiveException {
		throw new NotImplementedException();
	}
	
	protected void buildColumnMapping() throws AdoHiveException
	{
		//get tableName from modelClass annotation
		tableName = modelClass.getAnnotation(AdoClass.class).tableName();
		//check all methods for annotations, create JdbcColumns and add them to the map
		for(Method method : modelClass.getDeclaredMethods())
		{
			if(method.getAnnotation(AdoMethod.class) == null)
				continue;
			String columnName = method.getAnnotation(AdoMethod.class).columnName();
			JdbcColumn column = columnMap.get(columnName);
			if(column == null)
			{
				column = new JdbcColumn(columnName);
				columnMap.put(columnName, column);
			}
			
			if(column.getGetMethod() == null && isGetter(method))
				column.setGetMethod(method);
			else if(column.getSetMethod() == null && isSetter(method))
				column.setSetMethod(method);
		}
		
		//grab some column info from DatabaseMetaData and add them to the JdbcColumns
		try {
			DatabaseMetaData metadata = con.getMetaData();
			//Check if Table exists, if not, create it
			ResultSet tablemeta = metadata.getTables(null, null, tableName, null);
			if(!tablemeta.next())
				createTable();
			//I am not really sure what to set here for catalog and schema, needs testing
			ResultSet columns = metadata.getColumns(null, null, tableName, null);
			while(columns.next()) {
				String columnName = columns.getString("COLUMN_NAME");
				JdbcColumn column = columnMap.get(columnName);
				if(column == null)
					throw new AdoHiveException(); //TODO: Message
				column.setSqlType(columns.getInt("DATA_TYPE"));
				if("YES".equals(columns.getString("IS_AUTOINCREMENT"))) {
					column.setAutoIncrement(true);
					hasAutoIncrement = true;
				}

			}
			
			columns = metadata.getPrimaryKeys(null, null, tableName);
			
			while(columns.next()) {
				JdbcColumn column = columnMap.get(columns.getString("COLUMN_NAME"));
				if(column == null)
					throw new AdoHiveException(); //TODO: Message
				column.setPrimary(true);
				primaryKeys.add(column);
			}
		
		} catch (SQLException e) {
			throw new AdoHiveException(e); //TODO: Message
		}
		
		
	}
	
	protected void createStatements() throws AdoHiveException {
		
		//INSERT
		try {
			if(hasAutoIncrement)
				insertIncrementStatement = con.prepareStatement(createInsertStatement(false),Statement.RETURN_GENERATED_KEYS);
			if(!alwaysAutoIncrement || !hasAutoIncrement)
				insertAllStatement = con.prepareStatement(createInsertStatement(true));
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
		
		//UPDATE
		List<JdbcColumn> updateSetColumns = new ArrayList<JdbcColumn>();
		List<JdbcColumn> updateWhereColumns = new ArrayList<JdbcColumn>();
		StringBuilder query = new StringBuilder();
		
		for(JdbcColumn column : columnMap.values()) {
			if(column.isPrimary())
				updateWhereColumns.add(column);
			else
				updateSetColumns.add(column);
		}
		
		query.append("UPDATE ");
		query.append(dialect.quote(tableName));
		query.append(" SET ");
		joinPrepared(updateSetColumns, ",",query);
		query.append(" WHERE ");
		joinPrepared(updateWhereColumns, " AND", query);
		
		try {
			updateStatement = con.prepareStatement(query.toString());
			updateColumns.addAll(updateSetColumns);
			updateColumns.addAll(updateWhereColumns);
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
		
		
		//REMOVE
		query = new StringBuilder();
		query.append("DELETE FROM ");
		dialect.quote(tableName, query);
		query.append(" WHERE");
		joinPrepared(primaryKeys, " AND", query);
		try {
			removeStatement = con.prepareStatement(query.toString());
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
		
	}
	protected abstract void createTable() throws SQLException;
	
	protected String createInsertStatement(boolean withAutoIncrement) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		dialect.quote(tableName, query);
		query.append(" (");
		int paramlength = 0;
		Iterator<JdbcColumn> it = columnMap.values().iterator();
		while (it.hasNext()) {
			JdbcColumn column = it.next();
			if(column.isAutoIncrement && !withAutoIncrement)
				continue;
			dialect.quote(column.columnName, query);
			if(it.hasNext())
				query.append(",");
			paramlength++;
		}
		
		query.append(") VALUES (");
		query.append("?");
		for(int i=1; i<paramlength;i++)
			query.append(",?");
		query.append(")");
		
		return query.toString();
	}
	protected boolean isGetter(Method method)
	{
		return method.getParameterTypes().length == 0;
	}
	
	protected boolean isSetter(Method method)
	{
		return method.getParameterTypes().length != 0 && method.getReturnType() == void.class;
	}
	
	protected PreparedStatement createQuery(List<Pair<String, Object>> keys) throws AdoHiveException
	{	
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		dialect.quote(tableName,sb);
		if(keys != null && !keys.isEmpty()) {
		sb.append(" WHERE ");
		sb.append(joinPrepared(keys, " AND"));
		}
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sb.toString());
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
		if(keys != null && !keys.isEmpty())
			fillPrepared(stmt, keys);
		return stmt;
	}
	
	protected List<T> executeQuery(PreparedStatement statement) throws AdoHiveException
	{
		List<T> result = new ArrayList<T>();
		try {
			ResultSet resultset = statement.executeQuery();
			
			while(resultset.next())
			{
				T obj = modelClass.getConstructor().newInstance();
				for(int i=1;i<=resultset.getMetaData().getColumnCount();i++)
				{
					String columnname = resultset.getMetaData().getColumnName(i);
					Object cell = resultset.getObject(columnname);
					JdbcColumn column = columnMap.get(columnname);
					
					//try to convert with Dialect
					Object converted = dialect.convertTypeFromDb(cell, column);
					
					//if it succeeds, just call the method
					if(converted != null)
						column.getSetMethod().invoke(obj, converted);
					//if the dialect has no special conversion, let the reflection find something
					else {
						try {
							column.getSetMethod().invoke(obj, cell);		
						} catch (IllegalArgumentException e) {
							//TODO: throw exception
							System.out.println(column.columnName + " : " + cell.getClass() + " -> " + column.getSetMethod().getParameterTypes()[0]);
						}
					}
				}
				result.add(obj);
			}
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (SecurityException e) {
			throw new AdoHiveException(e);
		} catch (NoSuchMethodException e) {
			throw new AdoHiveException(e);
		} catch (IllegalArgumentException e) {
			throw new AdoHiveException(e);
		} catch (IllegalAccessException e) {
			throw new AdoHiveException(e);
		} catch (InvocationTargetException e) {
			throw new AdoHiveException(e);
		} catch (InstantiationException e) {
			throw new AdoHiveException(e);
		} 
		return result;
	}
	
	protected int executeInsert(T obj) throws AdoHiveException
	{
		//INSERT INTO tablename(colname1,...) VALUES (val1,...)	
		List<JdbcColumn> params = new ArrayList<JdbcColumn>();
		try {
			JdbcColumn autoIncrementColumn = null;
			
			for(JdbcColumn column : columnMap.values())
			{
				if(!column.isAutoIncrement)
					params.add(column);	
				else if (!alwaysAutoIncrement) {
					Integer val = (Integer)column.getMethod.invoke(obj);
					if(!(val == null || val.equals(0))) {
						params.add(column);
					} else
						autoIncrementColumn = column;
				} else
					autoIncrementColumn = column;
			}
			int returnval = 0;
			if(autoIncrementColumn != null) {
				fillPrepared(insertIncrementStatement, params , obj);
				returnval = insertIncrementStatement.executeUpdate();
				ResultSet keys = insertIncrementStatement.getGeneratedKeys();
				if(keys.next())
					autoIncrementColumn.getSetMethod().invoke(obj, keys.getInt(1));
			} else {
				fillPrepared(insertAllStatement, params , obj);
				returnval = insertAllStatement.executeUpdate();
			}
			return returnval;
		} catch (SecurityException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalArgumentException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalAccessException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InvocationTargetException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}

	}
	
	protected String joinPrepared(List<Pair<String, Object>> list, String delimiter )
	{
		StringBuilder sb = new StringBuilder();
		Iterator<Pair<String, Object>> it = list.iterator();
		while (it.hasNext()) {
			Pair<String, Object> param = it.next();
			sb.append(param.fst());
			sb.append(" = ?");
			if(it.hasNext())
			{
				sb.append(delimiter);
				sb.append(" ");
			}
		}
		
		return sb.toString();
	}
	
	protected String joinPreparedColumns(List<JdbcColumn> list, String delimiter )
	{
		StringBuilder sb = new StringBuilder();
		Iterator<JdbcColumn> it = list.iterator();
		while (it.hasNext()) {
			JdbcColumn column = it.next();
			dialect.quote(column.columnName,sb);
			sb.append(" = ?");
			if(it.hasNext())
			{
				sb.append(delimiter);
				sb.append(" ");
			}
		}
		
		return sb.toString();
	}
	
	protected void joinPrepared(List<JdbcColumn> list, String delimiter, StringBuilder sb )
	{
		Iterator<JdbcColumn> it = list.iterator();
		while (it.hasNext()) {
			JdbcColumn column = it.next();
			dialect.quote(column.columnName,sb);
			sb.append(" = ?");
			if(it.hasNext())
			{
				sb.append(delimiter);
				sb.append(" ");
			}
		}
	}
	
	protected String implode(List<JdbcColumn> list, String delimiter)
	{
		StringBuilder sb = new StringBuilder();
		Iterator<JdbcColumn> it = list.iterator();
		while (it.hasNext()) {
			JdbcColumn column = it.next();
			sb.append(column.columnName);
			if(it.hasNext())
				sb.append(delimiter);
		}
		
		return sb.toString();
	}


	
	protected void fillPrepared(PreparedStatement stmt, List<Pair<String, Object>> list) throws AdoHiveException
	{
		try {
			int paramIndex = 1;
			for(Pair<String, Object> param : list)
			{
				if(param.snd() == null)
					stmt.setNull(paramIndex, columnMap.get(param.fst()).sqlType);
				if(param.snd() instanceof Character)
					stmt.setObject(paramIndex, param.snd().toString());
				else
					stmt.setObject(paramIndex, param.snd());
				paramIndex++;
			}
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	protected void fillPrepared(PreparedStatement stmt, List<JdbcColumn> list, T obj) throws AdoHiveException
	{
		try {
			int paramIndex = 1;
			for(JdbcColumn column : list)
			{
				Object invoke = column.getGetMethod().invoke(obj);
				if(invoke == null)
					stmt.setNull(paramIndex, column.sqlType);
				else if(invoke instanceof Character)
					stmt.setObject(paramIndex, invoke.toString());
				else
					stmt.setObject(paramIndex, invoke);
				paramIndex++;
			}
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalArgumentException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalAccessException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InvocationTargetException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
}