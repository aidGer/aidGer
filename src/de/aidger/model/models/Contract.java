package de.aidger.model.models;

import java.util.Date;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IContract;

public class Contract extends AbstractModel implements IContract {
	
	public Contract clone()
	{
		return new Contract();
	}

	@Override
	public Date getCompletionDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getConfirmationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDelegation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCompletionDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setConfirmationDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDelegation(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEndDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStartDate(Date arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setType(String arg0) {
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
