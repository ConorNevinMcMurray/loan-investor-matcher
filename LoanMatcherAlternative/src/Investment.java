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
	 * the amount for the product
	 */
	@CsvBindByName(column = "investmentAmount", required = true)
	private double investmentAmount;
	/**
	 * the type of the product
	 */
	@CsvBindByName(column = "productType", required = true)
	private String productType;


	
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
		super(term);
		setAmount(amount);		
		setType(type);
		setInvestor(investor);
	}
	
	
	/**
	 * The amount left unallocated so if a loan how much of the loan is left unfunded 
	 * if an investment how much the investment is free for investing
	 * @return the amount unallocated
	 */
	public double uninvested() {
		return getAmount() - allocated();
	}
	
	
	
	/**
	 * A boolean check to see if a loan is funded
	 * @return true if funded false not
	 */
	public Boolean invested() {
		if(allocated() >= getAmount()) {
			return true;
		}else {
			return false;
		}
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
	
	/**
	 * a Boolean balance check to insure no product allocate more than their amount
	 * @return
	 */
	public Boolean balanced() {
		if(allocated() > getAmount()) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * return the type of the Product FIXED or TRACKER
	 * @return the type of the Product 
	 */
	public String getType() {
		return productType;
	}

	/**
	 * set the type of the Product
	 * @param type of the Product to be set
	 */
	public void setType(String type) {
		this.productType = type;
	}

	/**
	 * return the amount for the Product (loan amount or Investment amount)
	 * @return
	 */
	public double getAmount() {
		return investmentAmount;
	}

	/**
	 * set the amount of the Product
	 * @param amount (loan amount or Investment amount) to be set
	 */
	public void setAmount(double amount) {
		this.investmentAmount = amount;
	}
	



}
