package sample;

/*
 * Created by Matthew Ashley on 1/17/17.
 */

@SuppressWarnings("ConstantConditions")
public class CreatedFunctions {

    /* Method to check the format of the invoice number entered, must have two '-' and be in the correct position! */
    public boolean invoiceFormat (String check) {

        boolean invoiceCheck = true; /* Variable for storing if the invoice number is in the correct format! */

        StringBuilder invoiceNumberCheck = new StringBuilder(check);

        int a = 2; /* Variable for setting the check number for the location of the '-'! */
        int b = 0; /* Variable for counting the number of '-' in the inputted String! */

        for (int x = 0; x < invoiceNumberCheck.length(); x++) {

            if (invoiceNumberCheck.charAt(x) == '-') {

                if (x != a) {

                    invoiceCheck = false;
                }

                if (x == a) {

                    b++;
                }

                if (x == 2) {

                    a = 7;
                }
            }
        }

        if (b != 2) {

            invoiceCheck = false;
        }

        return invoiceCheck;
    }

    /* Method to set the format of entered dates correctly for being entered into each generated file! */
    public String dateConverter(String date) {

        char[] dateArray = date.toCharArray();
        StringBuilder dateString = new StringBuilder();

        for (int x = 5; x < 10; x++) {
            dateString.append(String.valueOf(dateArray[x]));
        }

        dateString.append(String.valueOf(dateArray[4]));

        for (int y = 0; y < 4; y++) {
            dateString.append(String.valueOf(dateArray[y]));
        }

        return String.valueOf(dateString);
    }

} /* Closing bracket for CreatedFunctions class! */
