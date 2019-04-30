import java.util.Comparator;

/**
 * a sorting class for sorting Loans by age implementing comparator 
 * @author conor
 *
 */
public class SortByAge implements Comparator<Loan>{

	/**
	 * sorts the Loans oldest to youngest where oldest are the begining of the 
	 * colleciton 
	 */
	@Override
	public int compare(Loan a, Loan b) {
		if(a.isOlder(b)) {
			return -1;
		}else if(b.isOlder(a)) {
			return 1;
		}
		return 0;
	}
	

}
