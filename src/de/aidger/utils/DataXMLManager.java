/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

import siena.SienaException;

import de.aidger.model.Runtime;
import de.aidger.model.models.CostUnit;

//TODO: Handle all empty exception handlers
//TODO: Rewrite completely

/**
 * This class manages data stored in a XML file.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("unchecked")
public class DataXMLManager {

    /**
     * The file name where the data will be stored.
     */
    private final String filename = Runtime.getInstance().getConfigPath()
            + "data.xml";

    /**
     * The cost unit map as list that is loaded from file.
     */
    private List<CostUnit> costUnitMap = new ArrayList<CostUnit>();

    /**
     * The unedited cost unit. It is needed by update().
     */
    private CostUnit costUnitBeforeEdit;

    /**
     * The email suffix for the assistants.
     */
    private String emailSuffix;

    /**
     * Creates an example cost unit map and stores it to file.
     */
    private void createDefaultData() {
        try {
            XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(filename)));

            // create example cost units
            CostUnit c = new CostUnit();
            c.setCostUnit("11111111");
            c.setFunds("Fonds 1");
            c.setTokenDB("A");

            CostUnit c_ = new CostUnit();
            c_.setCostUnit("22222222");
            c_.setFunds("Fonds 2");
            c_.setTokenDB("B");

            CostUnit c__ = new CostUnit();
            c__.setCostUnit("33333333");
            c__.setFunds("Fonds 3");
            c__.setTokenDB("C");

            costUnitMap.add(c);
            costUnitMap.add(c_);
            costUnitMap.add(c__);

            emailSuffix = "studi.informatik.uni-stuttgart.de";

            xmlEncoder.writeObject(costUnitMap);
            xmlEncoder.writeObject(emailSuffix);

            xmlEncoder.close();
        } catch (IOException e) {
        }
    }

    /**
     * Reads the data by loading it from XML file.
     */
    private void readData() {
        try {
            XMLDecoder dec = new XMLDecoder(new FileInputStream(filename));

            costUnitMap = (List<CostUnit>) dec.readObject();
            emailSuffix = (String) dec.readObject();

            dec.close();
        } catch (IOException e) {
        }
    }

    /**
     * Saves the data to the XML file.
     */
    private void saveData() {
        try {
            XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(filename)));

            xmlEncoder.writeObject(costUnitMap);
            xmlEncoder.writeObject(emailSuffix);

            xmlEncoder.close();
        } catch (IOException e) {
        }
    }

    /**
     * Creates a data manager.
     */
    public DataXMLManager() {
        File file = new File(filename);

        if (file.exists()) {
            readData();
        } else {
            createDefaultData();
        }
    }

    /**
     * Returns the cost unit map as list of cost units.
     * 
     * @return the cost unit map as list of cost units
     */
    public List<CostUnit> getCostUnitMap() {
        try {
            return getAll();
        } catch (SienaException e) {

        }

        return null;
    }

    /**
     * Returns the email suffix of the assistants.
     * 
     * @return the email suffix of the assistants
     */
    public String getEmailSuffix() {
        return emailSuffix;
    }

    /**
     * Sets the unedited cost unit.
     * 
     * @param costUnitBeforeEdit
     *            the unedited cost unit
     */
    public void setCostUnitBeforeEdit(CostUnit costUnitBeforeEdit) {
        this.costUnitBeforeEdit = costUnitBeforeEdit;
    }

    /**
     * Returns a cost unit object from given database token.
     * 
     * @param tokenDB
     *            the database token
     * @return a cost unit object. If no object was found, null is returned.
     */
    public CostUnit fromTokenDB(String tokenDB) {
        for (CostUnit c : getCostUnitMap()) {
            if (tokenDB.equals(c.getTokenDB())) {
                return c;
            }
        }

        return null;
    }

    /**
     * Returns a list of all stored cost units.
     * 
     * @return a list of all stored cost units
     */
    public List<String> getCostUnits() {
        List<String> costUnits = new ArrayList<String>();

        for (CostUnit c : getCostUnitMap()) {
            costUnits.add(c.getCostUnit());
        }

        return costUnits;
    }

    public List<CostUnit> getAll() throws SienaException {
        List<CostUnit> costUnits = new ArrayList<CostUnit>();

        for (CostUnit costUnit : costUnitMap) {
            costUnits.add(costUnit.clone());
        }

        return costUnits;
    }

    public boolean add(CostUnit e) throws SienaException {
        costUnitMap.add((CostUnit) e);

        saveData();

        return true;
    }

    public boolean update(CostUnit o) throws SienaException {
        costUnitMap.remove(costUnitBeforeEdit);
        costUnitMap.add((CostUnit) o);

        saveData();

        return true;
    }

    public boolean remove(CostUnit e) throws SienaException {
        costUnitMap.remove(e);

        saveData();

        return true;
    }

    // the following methods will not be used but are implemented

    public void clear() throws SienaException {
        costUnitMap.clear();
    }

    public boolean contains(CostUnit o) throws SienaException {
        return costUnitMap.contains(o);
    }

    public CostUnit get(int index) throws SienaException {
        return costUnitMap.get(index);
    }

    public boolean isEmpty() throws SienaException {
        return costUnitMap.isEmpty();
    }

    public int size() throws SienaException {
        return costUnitMap.size();
    }

    public List<CostUnit> subList(int fromIndex, int toIndex)
            throws SienaException {
        return costUnitMap.subList(fromIndex, toIndex);
    }

    public CostUnit getById(int id) throws SienaException {
        return get(id);
    }

    public CostUnit getByKeys(Object... o) throws SienaException {
        return null;
    }
}
