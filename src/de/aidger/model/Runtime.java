package de.aidger.model;

import java.io.File;

import de.aidger.utils.Configuration;

/**
 * Initializes Configuration and Translation and relays the methods
 * 
 * @author Philipp Pirrung
 */
public final class Runtime {
	
	public Runtime() {
		
	}
	
	 /**
     * Set of supported operating systems.
     */
    public enum OS {
        /* Any kind of Windows operating system */
        WINDOWS,
        /* All systems using a linux kernel */
        LINUX,
        /* Macintoshs (not necessarily OS X) */
        MACOSX,
        /* Unknown operating system */
        UNKNOWN
    };

    /**
     * The users operating system.
     */
    private static OS operatingSystem;
    
    /**
     * The configuration class handling the settings.
     */
    private static Configuration configuration = null;
	
	public void initialize() {
	    /* Set the operating system */
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("windows") > -1) {
            operatingSystem = OS.WINDOWS;
        } else if (os.indexOf("linux") > -1) {
            operatingSystem = OS.LINUX;
        } else if (os.indexOf("mac") > -1) {
            operatingSystem = OS.MACOSX;
        } else {
            operatingSystem = OS.UNKNOWN;
        }

        /* Get the path to the users home or config directory */
        String home = "";
        if (operatingSystem == OS.LINUX) {
        /* Try using the XDG_CONFIG_DIR variable first. Provides the standard
           directory according to the desktop guidelines or a user set
           equivalent */
            String confdir = System.getenv("XDG_CONFIG_HOME");
            if (confdir == null || confdir.isEmpty()) {
            /* Otherwise just use the homedir and the hidden config directory */
                confdir = System.getProperty("user.home", ".");
                confdir += "/.config/";
            }
            home = confdir;
        } else if (operatingSystem == OS.WINDOWS) {
        /* Try using the APPDATE variable first. Should return the
           ApplicationData folder but may fail on older Windows versions. */
            String confdir = System.getenv("APPDATA");
            if (confdir == null || confdir.isEmpty()) {
            /* Otherwise just use the homedir */
                confdir = System.getProperty("user.home", ".");
            }
            home = confdir;
        } else {
        /* We've got no mac so we don't have any idea where to put our files.
           Same goes for any other unknown operating systems.
           That's why we're simply using the homedir */
            home = System.getProperty("user.home", ".");
        }
        home = home + "/aidGer/";
        File file = new File(home);
        if ((!file.exists() || !file.isDirectory()) && !file.mkdirs()) {
            System.err.println("Konnte Verzeichnis für Datenbank nicht "
                    + "erstellen");
        }
        
        configuration = new Configuration(home);
        configuration.initialize();
	}
	
	/**
	 * Get the value of an option.
	 * 
	 * @param option - The option to get
	 * @return The value of the specified option
	 */
	public String getOption(String option) {
		return configuration.get(option);
	}
	
	/**
	 * Set the value of an option.
	 * 
	 * @param option - The option to set
	 * @param value - The value to set it to
	 */
	public void setOption(String option, String value) {
		configuration.set(option, value);
	}
	
	public String getTranslation(String id) {
		return "";
	}
	
}
