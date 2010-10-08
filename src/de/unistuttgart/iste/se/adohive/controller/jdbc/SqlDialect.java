/**
 * 
 */
package de.unistuttgart.iste.se.adohive.controller.jdbc;

/**
 * @author rashfael
 *
 */
public interface SqlDialect {
	public String quote (String quoteString);
	public void quote (String quoteString, StringBuilder stringBuilder);
	public Object convertTypeFromDb(Object cell, JdbcColumn column);
}
