/**
 * 
 */
package de.unistuttgart.iste.se.adohive.model.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.unistuttgart.iste.se.adohive.controller.ansi.AnsiAdoHiveController;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IEmployment;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;

/**
 * @author Felix
 *
 */
public class IndependentTestDataProvider implements ITestDataProvider {

	private static Random r = new Random();	
	
	private List<HourlyWage> hw = new ArrayList<HourlyWage>();
	
	public static String randomString() {
		switch(r.nextInt(10)) {
		case 0: return "hier steht eine null";
		case 1: return "schb�tzlesbschdegg";
		case 2: return "12345678901234567890";
		case 3: return "";
		case 4: return "\";DROP DATABASE; --";
		case 5: return Long.toString(r.nextLong());
		case 6: return "(bb)|(�bb)";
		case 7: return "\"\'@@@ hurrdurr";
		case 8: return "Lieschen M�ller";
		case 9: return "baked beans and spam";
		}
		
		// just to make eclipse NOT complaining
		return "never happens";
	}
	
	public static String randomStringFromArr(String[] arr) {
		return arr[r.nextInt(arr.length - 1)];
	}
	
	public static String randomFixedLengthDigitString(int length) {
		String ret = Long.toString(r.nextLong());
		while (ret.length() < length) {
			ret = "0".concat(ret);
		}
		return ret.substring(0, length);
	}
	
	public static int randomInt() {
		return r.nextInt();
	}
	
	public static Integer[] randomIntArray(int length) {
		Integer[] arr = new Integer[length];
		for(int i=0; i<arr.length;i++)
			arr[i] = r.nextInt();
		return arr;
	}
	public static long randomLong() {
		return r.nextLong();
	}
	
	public static double randomDouble() {
		return r.nextDouble();
	}
	
	public static char randomChar() {
		return (char) r.nextInt(255);
	}
	
	@SuppressWarnings("deprecation")
	public static Date randomDate() {
		Date datetime = new Date(r.nextInt());
		return new Date(datetime.getYear(), datetime.getMonth(), datetime.getDay());
	}
	
	public static Random getRandom() {
		return r;
	}
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider#newIActivity()
	 */
	@Override
	public IActivity newIActivity() throws AdoHiveException {
		IAssistant assi = newIAssistant();
		AnsiAdoHiveController.getInstance().getAssistantManager().add(assi);
		ICourse course = newICourse();
		AnsiAdoHiveController.getInstance().getCourseManager().add(course);
		IActivity activity = new Activity();
		activity.setAssistantId(assi.getId());
		activity.setCourseId(course.getId());
		return activity;
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider#newIAssistant()
	 */
	@Override
	public IAssistant newIAssistant() throws AdoHiveException  {
		return new Assistant();
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider#newIContract()
	 */
	@Override
	public IContract newIContract() throws AdoHiveException  {
		IAssistant assi = newIAssistant();
		AnsiAdoHiveController.getInstance().getAssistantManager().add(assi);
		Contract contract = new Contract();
		contract.setAssistantId(assi.getId());
		return contract;
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider#newICourse()
	 */
	@Override
	public ICourse newICourse() throws AdoHiveException  {
		IFinancialCategory fcat = newIFinancialCategory();
		AnsiAdoHiveController.getInstance().getFinancialCategoryManager().add(fcat);
		Course course = new Course();
		course.setFinancialCategoryId(fcat.getId());
		return course;
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider#newIEmployment()
	 */
	@Override
	public IEmployment newIEmployment() throws AdoHiveException  {
		IContract contract = newIContract();
		AnsiAdoHiveController.getInstance().getContractManager().add(contract);
		ICourse course = newICourse();
		AnsiAdoHiveController.getInstance().getCourseManager().add(course);
		Employment employment = new Employment();
		employment.setAssistantId(contract.getAssistantId());
		employment.setContractId(contract.getId());
		employment.setCourseId(course.getId());
		return employment;
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider#newIFinancialCategory()
	 */
	@Override
	public IFinancialCategory newIFinancialCategory() throws AdoHiveException  {
		return new FinancialCategory();
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.test.ITestDataProvider#newIHourlyWage()
	 */
	@Override
	public IHourlyWage newIHourlyWage() {
		// make sure that the new HourlyWage is independent from older
		// returned instances of HourlyWage.
		HourlyWage ret = null;
		boolean testing = true;
		
		while (testing) {
			ret = new HourlyWage();
			if(hw.isEmpty())
				break;
			for (HourlyWage h : hw) {
				testing = h.getQualification().equals(ret.getQualification()) && h.getMonth().equals(ret.getMonth()) && h.getYear().equals(ret.getYear());
				if(!testing)
					break;
			}
			
		}
		
		hw.add(ret);
		return new HourlyWage();
	}

}
