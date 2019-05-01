import java.util.HashMap;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * A class that is abstract and represents the common nature of a Loan and Investment 
 * this being the associations a loan might have with an investment and the common fields
 * being type term and the amount this allows to reduce code duplication however it has restricted 
 * the ease in which the original csv files can be used without changing titles of columns and as
 * a result the csv files require the column headings to be altered to be common for those they inherit
 * @author conor
 *
 */
public abstract class Product {

	
	/**
	 * the term of the product
	 */
	@CsvBindByName(column = "term", required = true)
	private int term;
	/**
	 * the allocations to the product of other products
	 */
	private HashMap<Product, Double> allocations;
	
	/**
	 * cteates a product when the product is being instantiated from a csv file 
	 */
	public Product() {
		
		this.allocations = new HashMap<>();
	}

	/**
	 * creates a Product with the given amount, type, term 
	 * @param amount
	 * @param type
	 * @param term
	 */
	public Product(int term) {
		setTerm(term);
		allocations = new HashMap<>();
	}
	
	/**
	 * Calculates the amount of the product allocated so if a loan how much of the loan is funded 
	 * if an investment how much the investment is funding loans
	 * @return the amount allocated by the product
	 */
	public double allocated() {
		double allocated = 0;
		for(double amount: getAllocations().values()) {
			allocated += amount;
		}
		return allocated;
	}
	


	/**
	 * allocates a product with an amount and insures the same association is made with the other 
	 * product in terms of the amount and the what that amount is associated with (this product)
	 * in the case of this being a loan its an investment and an amount from that investment which
	 * is funding this loan. In the case of this being an investment, its a loan and amount of the 
	 * loan this investment is funding
	 * @param product
	 * @param amount
	 */
	public void allocate(Product product, double amount) {
		getAllocations().put(product, amount);
		product.getAllocations().put(this, amount);
	}
	/**
	 * return the term of the Product
	 * @return the term of the Product
	 */
	public int getTerm() {
		return term;
	}

	/**
	 * set the term of the Product
	 * @param term of the Product to be set
	 */
	public void setTerm(int term) {
		this.term = term;
	}

	/**
	 * return the allocations of the Product 
	 * @return the allocations of the Product
	 */
	public HashMap<Product, Double> getAllocations() {
		return allocations;
	}

	/**
	 * set the allocations of the Product 
	 * @param allocations of the Product to be set
	 */
	public void setAllocations(HashMap<Product, Double> allocations) {
		this.allocations = allocations;
	}

}
