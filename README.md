# loan-investor-matcher
Two versions of a small program to match loans to Investment requests.

LoanMatcher uses an abstract class Product to provide inheritance to the Loan and Investment classes to capture the common features of 
amount, type and term.

However to use the @CsvBindByName annotations when converting the csv files to a colleciton of Loan or Investment beans the various 
headings associated with these common features is different for the two child classes. As a result to incorporate the csv reading option
with this feature of inheritance the headings must be changed to a common heading. 

LoanMatcherAlternative removes the inherited fields from the Product class that must be filled from the csv files as a reuslt the orignal 
csv files can be used.

In both cases I make the assumption that Investment requests used to fund loans must not be entriely met (as in an Investor can invest half
the amount in their request into a Loan
