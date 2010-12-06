/**
 * 
 */
package de.unistuttgart.iste.se.adohive.controller.ansi;

import java.math.BigDecimal;

import de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcColumn;
import de.unistuttgart.iste.se.adohive.controller.jdbc.SqlDialect;

/**
 * @author rashfael
 *
 */
public class AnsiSqlDialect implements SqlDialect {

	private static final String QUOTE = "\"";
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.jdbc.SqlDialect#quote(java.lang.String)
	 */
	@Override
	public String quote(String quoteString) {
		return QUOTE+quoteString+QUOTE;
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.jdbc.SqlDialect#quote(java.lang.String, java.lang.StringBuilder)
	 */
	@Override
	public void quote(String quoteString, StringBuilder stringBuilder) {
		stringBuilder.append(QUOTE);
		stringBuilder.append(quoteString);
		stringBuilder.append(QUOTE);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.jdbc.SqlDialect#convertTypeFromDb(java.lang.Object, de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcColumn)
	 */
	@Override
	public Object convertTypeFromDb(Object cell, JdbcColumn column) {
		Class<?> paramType = column.getSetMethod().getParameterTypes()[0];
		//Class.cast seems not to cast long to int ¬_¬
		if(cell instanceof Long && (paramType == int.class || paramType == Integer.class)) {
			return (Integer)((Long)cell).intValue();			
		}
		//convert String to char
		else if(cell instanceof String && (paramType == char.class || paramType == Character.class))
			return (Character)((String)cell).charAt(0);			
		//jay for crappy Derby not having a byte type
		else if(cell instanceof Integer && (paramType == byte.class	|| paramType == Byte.class))
			return ((Integer)cell).byteValue();			
		//derby seems to have a SMALLINT, but it returns an int *gahhh*
		else if(cell instanceof Integer && (paramType == short.class || paramType == Short.class))
			return ((Integer)cell).shortValue();
		//jay for crappy Derby not having a boolean type
		else if(cell instanceof Integer && (paramType == boolean.class || paramType == Boolean.class))
			return((Integer)cell) != 0;
		else if(cell instanceof Float && (paramType == double.class || paramType == Double.class))
			return ((Float) cell).doubleValue();
		else if(cell instanceof Double && (paramType == BigDecimal.class))
			return new BigDecimal((Double)cell);
		else 
			return null;
	}

}
