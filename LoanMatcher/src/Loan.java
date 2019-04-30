import java.util.*;

import com.opencsv.bean.CsvBindByName;

/**
 * A class representing a Loan and the attributes that a loan might have
 * the class is a subclass of Product 
 * @author conor
 *
 */
public class Loan extends Product{
	
	/**
	 *  the loan id
	 */
	@CsvBindByName(column = "loanId", required = true)
	private int id;
	/**
	 * the compelted date of the loan
	 */
	@CsvBindByName(column = "completedDate", required = true)
	private String completedDate;
	
	/**
	 * creates a Loan object in the instance the object is being instantiated from a .csv file
	 */
	public Loan() {
		
	}
	
	/**
	 * creates a Loan with the given id, amount, type, term and completed date
	 * @param id the LoanId
	 * @param amount the loanAmount 
	 * @param type the loan product type
	 * @param term the term of the loan
	 * @param completedDate the completed date of the loan
	 */
	public Loan(int id, double amount, String type, int term, String date) {
		super(amount, type, term);
		this.id = id;
		this.completedDate = date;
	}
	
	/**
	 * creates an int array of the completedDate for comparison purposes
	 * with the year, month and day indexed from 0 to 2
	 * @return the int array representation of the date
	 */
	public int[] intDate() {
		// split the String into String array
		String[] stringDate = getCompletedDate().split("/");
		// create an new Int array
		int[] intDate = new int[stringDate.length];
		// convert each String in the String array to an int
		for(int i = 0; i < intDate.length; i++) {
			intDate[i] = Integer.parseInt(stringDate[i]);
		}
		return intDate;
	}
	
	/**
	 * A boolean comparison for the age of another loan
	 * @param anotherLoan the other loan to be compared
	 * @return true if the this loan is older false if not
	 */
	public Boolean isOlder(Loan anotherLoan) {
		int[] age = intDate();
		int[] otherAge = anotherLoan.intDate();
		// iterate through each element of the int array
		for(int i = age.length - 1; i >= 0; i--) {
			// if a value is less then it indicates it is older and thus true
			if(age[i] < otherAge[i]) {
				return true;
			}else if(age[i] > otherAge[i]) {
				return false;
			}
		}
		// if all values were the same it is not older so return false
		return false;
	}
	
	/**
	 * Funds the loan with an investment, there is currently no indication to the amount 
	 * a lone should be funded with, so the method fills the remaining amount with as much of the investment 
	 * as possible. Further methods could be used to allocate a specific amount of the investment to this loan.
	 * Then method insures that loan and the investments respective allocation HashMaps store the amount and the
	 * associated investment or loan. 
	 * @param investment the investment used to fund the loan
	 */
	public void fund(Investment investment) {
		// the amount unfunded is the unallocated amount
		double unfunded = unallocated();
		// the uninvested amount is the investments unallocated 
		double uninvested = investment.unallocated();
		if(unfunded != 0 && uninvested != 0) {
			if(unfunded >= uninvested) {
				// if the unfunded amount is greater than or equal to the uninvested amount allocated what is univested
				allocate(investment, uninvested);
			}else {
				// if the unfunded amount is less than the uninvested amount allocate the unfunded amount
				allocate(investment, unfunded);
			}
		}
	}
	
	/**
	 * defunds the loan be removing the loan association in each of associated investments own allocations HashMaps
	 * and resets the loans allocations HashMap this insures that investments that are defunded are then available 
	 * again for further allocations
	 */
	public void defund() {
		for(Product investment : getAllocations().keySet()) {
//			Investment investment = (Investment)product;
			// for each investment allocated to this loan access its allocations and remove this loan from it
			investment.getAllocations().remove(this);
		}
		// create a new investments allocations (empty) and reset the allocations as this new empty HashMap
		HashMap<Product, Double> investments = new HashMap<Product, Double>();
		setAllocations(investments);
	}
	
	/**
	 * for debugging purposes prints the loan info with the investments as well
	 */
	public void printInfo() {
		System.out.println("----------------------------------");
		System.out.println("------------Loan Info-------------");
		System.out.println("ID: " + getId());
		System.out.println("AMOUNT: " + getAmount());
		System.out.println("TYPE: " + getType());
		System.out.println("TERM: " + getTerm());
		System.out.println("COMPLETED DATE: " + getCompletedDate());
		System.out.println("------------Investments-------------");
		for(Product product : getAllocations().keySet()) {
			Investment investment = (Investment)product;
			System.out.println(investment.getInvestor() + " : " + getAllocations().get(product));
		}
		System.out.println("Status of fund");
		System.out.println("% funded: " + allocated()/getAmount());
	}
	
	/**
	 * A boolean check to see if a loan is funded
	 * @return true if funded false not
	 */
	public Boolean funded() {
		if(allocated() >= getAmount()) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * returns the loans ID
	 * @return the ID of the loan
	 */
	public int getId() {
		return id;
	}

	/**
	 * returns the completedDate of the loan
	 * @return the completedDate
	 */
	public String getCompletedDate() {
		return completedDate;
	}

	public String toString() {
		String loanInfo = "--------LOAN INFO--------\n";
		loanInfo += "ID: " + getId() + "\n";
		loanInfo += "AMOUNT: " + getAmount() + "\n";
		loanInfo += "TYPE: " + getType() + "\n";
		loanInfo += "TERM: " + getTerm() + "\n";
		loanInfo += "COMPLETED DATE: " + getCompletedDate() + "\n";
		loanInfo += "--------IVESTORS--------\n";
		for(Product product : getAllocations().keySet()) {
			Investment investment = (Investment)product;
			loanInfo += investment.getInvestor() + " : " + getAllocations().get(product) + "\n";
		}
		loanInfo +="Status of fund\n";
		loanInfo +="% funded: " + allocated()/getAmount() + "\n";
		return loanInfo;
	}

}
