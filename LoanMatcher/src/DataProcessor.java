import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileWriter;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A helper class that should process data inputs from a .csv file or provide data internally
 * and then can be used to create data as a JSON output  
 * @author conor
 * 
 */
public class DataProcessor {
	
	/**
	 * creates a new DataProcessor
	 */
	public DataProcessor(){
	}
	
	/**
	 * returns a list of Loan objects processed from a .csv file
	 * @param filepath the file path to the .csv file that will contain Loan objects
	 * @return a list of loan objects
	 * @throws IllegalStateException when the state is illegal
	 * @throws FileNotFoundException when the file is not found
	 */
	public List<Loan> processLoans(String filepath) throws IllegalStateException, FileNotFoundException {
		FileReader fileReader = new FileReader(filepath);
		List<Loan> loans = new CsvToBeanBuilder<Loan>(fileReader).withType(Loan.class).build().parse();
		return loans;
	}
	
	/**
	 * returns a list of Investment objects processed from a .csv file
	 * @param filepath the file path to the .csv file that will contain Investment objects
	 * @return a list of Investment objects
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	public List<Investment> processInvestmentRequests(String filepath) throws IllegalStateException, FileNotFoundException {
		FileReader fileReader = new FileReader(filepath);
		List<Investment> investments = new CsvToBeanBuilder<Investment>(fileReader).withType(Investment.class).build().parse();
		return investments;
	}
	
	/**
	 * creates an ArrayList of Loans that are internally hard coded 
	 * @return ArrayList of Loan objects 
	 */
	public ArrayList<Loan> provideLoans(){
		ArrayList<Loan> loans = new ArrayList<>();
		loans.add(new Loan(1,100000,"FIXED",18,"01/01/2015"));
		loans.add(new Loan(2,99000,"FIXED",10,"02/01/2015"));
		loans.add(new Loan(3,100000,"FIXED",10,"03/01/2015"));
		loans.add(new Loan(4,85000,"TRACKER",24,"04/01/2015"));
		loans.add(new Loan(5,90000,"TRACKER",24,"05/01/2015"));
		loans.add(new Loan(6,120000,"FIXED",11,"06/01/2015"));
		loans.add(new Loan(7,80000,"TRACKER",24,"07/01/2015"));
		loans.add(new Loan(8,77700,"TRACKER",24,"08/01/2015"));
		loans.add(new Loan(9,110000,"TRACKER",24,"09/01/2015"));
		return loans;
	}
	
	/**
	 * creates an ArrayList of Investments that are internally hard coded 
	 * @return ArrayList of Investment objects 
	 */
	public ArrayList<Investment> provideInvestmentRequests(){
		ArrayList<Investment> investRequests = new ArrayList<>();
		investRequests.add(new Investment("Alice",100,"FIXED",12));
		investRequests.add(new Investment("Bob",330000,"TRACKER",30));
		investRequests.add(new Investment("Charlie",1000,"FIXED",12));
		investRequests.add(new Investment("Dan",20000,"TRACKER",50));
		investRequests.add(new Investment("Eve",13000,"TRACKER",30));
		investRequests.add(new Investment("Faith",40,"FIXED",12));
		investRequests.add(new Investment("Gary",1300,"FIXED",12));
		investRequests.add(new Investment("Helgor",400000,"FIXED",12));
		investRequests.add(new Investment("India",5670,"FIXED",12));
		investRequests.add(new Investment("Jane",100,"FIXED",12));
		investRequests.add(new Investment("Klaus",2000,"FIXED",12));
		investRequests.add(new Investment("Leo",67800,"TRACKER",30));
		investRequests.add(new Investment("Mark",180,"TRACKER",30));
		investRequests.add(new Investment("Nina",10000,"TRACKER",30));
		return investRequests;
	}
	
	/**
	 * writes a JSON file from an ArrayList of Loan objects
	 * @param loans the ArrayList of Loan objects from which a JSON file is written
	 */
	@SuppressWarnings("unchecked")
	public void writeLoansToJSON(ArrayList<Loan> loans) {
		JSONArray matchedLoans = new JSONArray();
		for(Loan loan : loans) {
			JSONObject matchedLoan = new JSONObject();
			matchedLoan.put("ID", loan.getId());
			matchedLoan.put("amount", loan.getAmount());
			matchedLoan.put("product", loan.getType());
			matchedLoan.put("term", loan.getTerm());
			matchedLoan.put("completedDate", loan.getCompletedDate());
			for(Product product : loan.getAllocations().keySet()) {
				Investment investment = (Investment)product;
				matchedLoan.put("investor name", investment.getInvestor());
				matchedLoan.put("amount invested", investment.getAmount());
			}
			matchedLoans.add(matchedLoan);
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter("matchedLoans.json")) {
 
            file.write(matchedLoans.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
}
