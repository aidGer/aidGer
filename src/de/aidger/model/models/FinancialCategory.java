package de.aidger.model.models;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

public class FinancialCategory extends AbstractModel implements
		IFinancialCategory {

	public FinancialCategory clone()
	{
		return new FinancialCategory();
	}

	@Override
	public int[] getBugdetCosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getFonds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getYear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBugdetCosts(int[] arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFonds(int[] arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setYear(short arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(int arg0) {
		// TODO Auto-generated method stub

	}

}
