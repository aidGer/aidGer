package de.aidger.model.models;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;

public class HourlyWage extends AbstractModel implements IHourlyWage {
	
	public HourlyWage clone()
	{
		return new HourlyWage();
	}

	@Override
	public byte getMonth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getQualification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getWage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short getYear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMonth(byte arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setQualification(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWage(double arg0) {
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
