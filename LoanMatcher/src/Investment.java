import java.util.*;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * A class representing an Investment and the attributes an investment might have
 * the class is a subclass of Product 
 * @author conor
 *
 */
public class Investment extends Product {
	/**
	 * the investor name
	 */
	@CsvBindByName(column = "investor", required = true)
	private String investor;

	
	/**
	 * creates an Investment object in the instance the object is being instantiated from a .csv file
	 */
	public Investment() {
		
	}

	/**
	 * creates an Investment with the given investor, amount, type, term 
	 * @param investor
	 * @param amount
	 * @param type
	 * @param term
	 */
	public Investment(String investor, double amount, String type, int term) {
		super(amount, type, term);
		setInvestor(investor);
	}

	/**
	 * returns the Investor of the Investment
	 * @return the investor of the Investment
	 */
	public String getInvestor() {
		return investor;
	}

	/**
	 * sets the Investor of the Investment
	 * @param investor who is making the Investment 
	 */
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	



}
