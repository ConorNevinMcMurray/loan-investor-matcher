import java.io.FileNotFoundException;
import java.util.*;

/**
 * 
 * @author conor
 *
 */
public class Main {

	/**
	 * 
	 * @param args
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws IllegalStateException, FileNotFoundException {
		
		DataProcessor dataProcessor = new DataProcessor();
		ArrayList<Loan> loans = null;
		ArrayList<Investment> investRequests = null;
		// comment in or out to select method
//		String method = "hardcoded";
		String method = "csv";
		if(method.equals("hardcoded")){
			// use the hard coded data in the dataProcessor
			loans = dataProcessor.provideLoans();
			investRequests = dataProcessor.provideInvestmentRequests();
		}else {
			// use the files
			String loansFilepath = "loans-data.csv";
			loans = (ArrayList<Loan>) dataProcessor.processLoans(loansFilepath);
			String investmentRequestsFilepath = "investmentRequests-data.csv";
			investRequests = (ArrayList<Investment>) dataProcessor.processInvestmentRequests(investmentRequestsFilepath);
		}

		LoanMatcher loanMatcher = new LoanMatcher(loans, investRequests);
		dataProcessor.writeLoansToJSON(loanMatcher.getMatchedLoans());
		if(loanMatcher.balanced()) {
			for(Loan loan : loanMatcher.getMatchedLoans()) {
				System.out.println(loan);
			}
		}
		
		
		

	}
	
	

}
