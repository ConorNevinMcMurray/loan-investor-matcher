import java.util.*;
/**
 * A class to that holds methods to match laons to investments obeying the matching rules
 * @author conor
 *
 */
public class LoanMatcher {
	/**
	 * loans that are processed
	 */
	private ArrayList<Loan> loans;
	/**
	 * investments that are processed
	 */
	private ArrayList<Investment> investments;
	/**
	 * the matched loans
	 */
	private ArrayList<Loan> matchedLoans;
	
	/**
	 * Creates a LoanMatcher object taking the loans and investments for processing 
	 * and initiates the matching process
	 * @param loans
	 * @param investments
	 */
	public LoanMatcher(ArrayList<Loan> loans, ArrayList<Investment> investments) {
		setLoans(loans);
		setInvestments(investments);
		matchedLoans = new ArrayList<>();
		orderLoansByAge();
		matchLoans();
	}
	
	/**
	 * match the Loans with investors and store in the matched loans ArrayList 
	 */
	public void matchLoans() {
		matchedLoans = getMatchedLoans();
		for(Loan loan : getLoans()) {
			// process the loan
			process(loan);
			// if the loan is funded add it to the matched Loan list 
			if(loan.funded()) {
				matchedLoans.add(loan);
			}
		}
		// set the matched Loans as this matched loan list 
		setMatchedLoans(matchedLoans);
	}
	
	/**
	 * processes a loan using the investments to allocate funds through the loans fund
	 * method
	 * @param loan the loan to be funded
	 */
	public void process(Loan loan) {
		// iterate through investments
		for(Investment investment : getInvestments()) {
			if(sameType(loan, investment) && investmentTermIsLonger(loan, investment)) {
				loan.fund(investment);
			}
		}
		// if a loan could not be funded defund this is not a requirement but insures that no loan is partial funded
		if(!loan.funded()) {
			loan.defund();
		}
	}
	
	/**
	 * A rule implemented as a Boolean test to check if loans are the same type FIXED or TRACKER
	 * @param loan the loan to be checked
	 * @param investment the investment to be checked
	 * @return true if the same type false if not
	 */
	public Boolean sameType(Loan loan, Investment investment) {
		if(loan.getType().equals(investment.getType())){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * A rule implemented as a Boolean test to check if an investment has a longer term than a loan
	 * @param loan the loan to be checked 
	 * @param investment the investment to be checked
	 * @return true if investment is longer false if not
	 */
	public Boolean investmentTermIsLonger(Loan loan, Investment investment) {
		if(investment.getTerm() > loan.getTerm()){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * A boolean check to see if loans and investments meet are balanced (allocations don't sum to greater
	 * their respective amounts)
	 * @return false if their is any unbalanced loan or investment and true otherwise 
	 */
	public Boolean balanced() {
		for(Loan loan: getLoans()) {
			if(!loan.balanced()) {
				return false;
			}
		}
		for(Investment investment : getInvestments()) {
			if(!investment.balanced()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * orders the loans by age (meeting a rule requirement) from oldest to youngest
	 */
	public void orderLoansByAge() {
		ArrayList<Loan> orderedLoans = getLoans();
		Collections.sort(orderedLoans,new SortByAge());
		setLoans(orderedLoans);
	}
	
	/**
	 * return the loans for matching
	 * @return loans for matching
	 */
	public ArrayList<Loan> getLoans() {
		return loans;
	}

	/**
	 * set the loans for matching
	 * @param loans the loans for matching to be set
	 */
	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	/**
	 * return the investments for matching 
	 * @return investments for matching
	 */
	public ArrayList<Investment> getInvestments() {
		return investments;
	}

	/**
	 * set the investments for matching 
	 * @param investments investments for matching
	 */
	public void setInvestments(ArrayList<Investment> investments) {
		this.investments = investments;
	}

	/**
	 * return the matching loans 
	 * @return matched loans 
	 */
	public ArrayList<Loan> getMatchedLoans() {
		return matchedLoans;
	}

	/**
	 * set the matched loans 
	 * @param matchedLoans the loans matched to be set
	 */
	public void setMatchedLoans(ArrayList<Loan> matchedLoans) {
		this.matchedLoans = matchedLoans;
	}
	

	

}
