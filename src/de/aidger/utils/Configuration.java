package de.aidger.utils;

import java.io.*;
import java.util.Properties;

/**
 * Gets and sets the settings
 * 
 * @author Philipp Pirrung
 */
public final class Configuration {
	
	/**
	 * The settings of the program.
	 */
	private Properties properties = new Properties();
	
	/**
	 * The file name of the configuration.
	 */
	private String file;
	
	/**
	 * Initializes this Configuration with a given path.
	 * 
	 * @param path - The path of the configuration.
	 */
	public Configuration(String path) {
		file = path + "settings.cfg";
	}
	
	public boolean initialize() {
	/* Check if the configuration exists and create it if it does not */
        if (!(new File(file)).exists()) {
        	createFile();
        }else{
        /* Read the settings from the file. */
        	try{
        		File inputFile = new File(file);
        		FileInputStream inputStream = new FileInputStream(inputFile);
        		properties.loadFromXML(inputStream);
        		inputStream.close();
        	}
        	catch (Exception e) {
        		createFile();
        		return false;
			}
        }
        return true;
	}
	
	/**
	 * Writes the default settings to the settings file.
	 */
	private void createFile() {
		try{
        	File outputFile = new File(file);
        	FileOutputStream outputStream = 
        			new FileOutputStream(outputFile);
        	properties.setProperty("name", "");
        	properties.setProperty("pdf-viewer", "");
        	properties.setProperty("sprache", "deutsch");
        	properties.setProperty("vorgaenge", "10");
        	properties.setProperty("auto-open", "n");
        	properties.setProperty("auto-save", "n");
    		properties.storeToXML(outputStream, "");
    		outputStream.close();
    	}
    	catch(Exception e) {
    	}
	}
	
	/**
	 * Gets the value of a property.
	 * 
	 * @param option - The property of which to get the value from
	 * @return The value of the specified property.
	 */
	public String get(String option) {
		return properties.getProperty(option);
	}
	
	/**
	 * Sets the value of a property.
	 * 
	 * @param option - The property to change.
	 * @param value - The value to change the property to.
	 */
	public void set(String option, String value) {
		properties.setProperty(option, value);
		try{
    		File outputFile = new File(file);
    		FileOutputStream outputStream = new FileOutputStream(outputFile);
    		properties.storeToXML(outputStream, "");
    		outputStream.close();
    	}
    	catch(Exception e) {
    		createFile();
		}
	}
	
}
