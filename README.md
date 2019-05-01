# loan-investor-matcher
Two versions of a small program to match loans to Investment requests.

LoanMatcher uses an abstract class Product to provide inheritance to the Loan and Investment classes to capture the common features of 
amount, type and term.

However to use the @CsvBindByName annotations when converting the csv files to a collection of Loan or Investment beans the various headings associated with these common features is different for the two child classes. As a result to incorporate the csv reading option with this feature of inheritance the headings in each csv file that are common features must be changed to a common heading. 

LoanMatcherAlternative removes the inherited fields from the Product class that must be filled from the csv files, as a result the original csv files can be used.

In both cases I make the assumption that Investment requests used to fund loans must not be entirely met (as in an Investor can invest half the amount in their request into a Loan)

To target the requirement of making the rules extendable, I have given consideration to creating a Rule interface and a set of Rule classes so that a list of objects implementing the Rule interface could be used in a modularised sense, one could simply pass these rules to the process method and in theory the result would be something that met the requirements. However the rules have different levels of scope, some are simple matching rules such as do the types match or is an ivestment term longer than a loan term. Whereas others require the incorporation of these rules as well as an evaluation on the state of a Loan after funding has been attempted or require the ordering of the Loans to be in a specific way. This would therefore require rules to have quite different designs and limit the extent to which they can be used and applied in an abstract sense.

This not to say a series of Rule objects could not be used but they would likely end up taking the place of methods currently in the LoanMatcher class. This would thus only create more lines of code. As it stands the best way to further extend the rules for matching would be to write new methods in the LoanMatcher class and apply them where appropriate. Such as in the constructor (ordering the two collections) in the process method, applying a rule to how the collection of investments can be applied to an individual loan. Or in the matchLoan method, where a rule could be applied to the Loan collection before, after or as loans are funded.

