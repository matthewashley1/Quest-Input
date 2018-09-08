package sample;

/*
 * Created by Matthew Ashley on 1/17/17.
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

@SuppressWarnings("ConstantConditions")
class CreatedFunctions {

    /* Method for generating the correct format for the invoice TextField within the estimate form! */
    String getInvoiceString () {

        /* Calendar variable used to get current year, month, and day of month! */
        Calendar calendar = new GregorianCalendar(Locale.ENGLISH);

        String date; /* Variable for storing the current date with year, month, and day! */
        String day; /* Variable for storing the current day! */
        String month; /* Variable for storing the current month! */

        if (calendar.get(Calendar.DATE) < 10) {

            day = "0" + String.valueOf(calendar.get(Calendar.DATE));

        } else {

            day = String.valueOf(calendar.get(Calendar.DATE));
        }

        if (calendar.get(Calendar.MONTH) < 9) {

            month = "0" + String.valueOf(calendar.get(Calendar.MONTH) + 1);

        } else {

            month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        }

        date = String.valueOf(calendar.get(Calendar.YEAR) - 2000) + "-" + month + day + "-";

        return date;
    }

    /* Method to check the format of the invoice number entered, must have two '-' and be in the correct position! */
    boolean invoiceFormat(String check) {

        boolean invoiceCheck = true; /* Variable for storing if the invoice number is in the correct format! */

        StringBuilder invoiceNumberCheck = new StringBuilder(check);

        int a = 2; /* Variable for setting the check number for the location of the '-'! */
        int b = 0; /* Variable for counting the number of '-' in the inputted String! */

        for (int x = 0; x < invoiceNumberCheck.length(); x++) {

            if (invoiceNumberCheck.charAt(x) == '-') {

                if (x != a) { invoiceCheck = false; }

                if (x == a) { b++; }

                if (x == 2) { a = 7; }
            }
        }

        if (b != 2) { invoiceCheck = false; }

        return invoiceCheck;
    }

    /* Method to set the format of entered dates correctly for being entered into each generated form! */
    String dateConverter(String date) {

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
