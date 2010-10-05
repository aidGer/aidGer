package de.aidger.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.aidger.model.Runtime;
import de.aidger.model.models.CostUnit;

/**
 * This class represents the map between cost unit and funds.
 * 
 * @author aidGer Team
 */
public class CostUnitMap {

    /**
     * The cost unit map as list that is loaded from file.
     */
    private List<CostUnit> map = new ArrayList<CostUnit>();

    /**
     * The file name where the cost unit map will be stored.
     */
    private final String filename = Runtime.getInstance().getConfigPath()
            + "costUnitMap.xml";

    /**
     * Creates an empty cost unit map and stores it to file.
     */
    private void createEmptyMap() {
        try {
            XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(filename)));

            // write empty map
            xmlEncoder.writeObject(map);

            xmlEncoder.close();
        } catch (IOException e) {
        }
    }

    /**
     * Reads the cost unit map by loading it from file.
     */
    @SuppressWarnings("unchecked")
    private void readMap() {
        try {
            XMLDecoder dec = new XMLDecoder(new FileInputStream(filename));

            map = (List<CostUnit>) dec.readObject();
        } catch (IOException e) {
        }
    }

    /**
     * Creates a cost unit map.
     */
    public CostUnitMap() {
        File file = new File(filename);

        if (file.exists()) {
            readMap();
        } else {
            createEmptyMap();
        }
    }

    /**
     * Returns the cost unit map as list of cost units.
     * 
     * @return the cost unit map as list of cost units
     */
    public List<CostUnit> getMap() {
        return map;
    }

    /**
     * Returns a cost unit object from given database token.
     * 
     * @param tokenDB
     *            the database token
     * @return a cost unit object. If no object was found, null is returned.
     */
    public CostUnit fromTokenDB(String tokenDB) {
        for (CostUnit c : getMap()) {
            if (tokenDB.equals(c.getTokenDB())) {
                return c;
            }
        }

        return null;
    }

    /**
     * Returns an object list of all stored cost units.
     * 
     * @return an object list of all stored cost units
     */
    public Object[] getCostUnits() {
        List<String> costUnits = new ArrayList<String>();

        for (CostUnit c : getMap()) {
            costUnits.add(c.getCostUnit());
        }

        return costUnits.toArray();
    }

}
