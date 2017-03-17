package sample;

/*
 * Created by Matthew Ashley on 1/17/17.
 */

@SuppressWarnings("ConstantConditions")
class Functions {

    private boolean checkCurrencyInput; /* Variable for checking currency inputs, used to store true or false whether or not a number in currency format has been entered correctly by the user! */
    private boolean checkNumberInput; /* Variable for checking number inputs, used to store true or false whether or not a number has been entered correctly by the user! */
    private boolean checkNumberFormat; /* Variable for checking if inputted comas are correct! */
    private boolean noDecimalCheck; /* Variable for storing if a number doesn't have a decimal*/
    private boolean invoiceCheck; /* Variable for storing if the invoice number is in the correct format! */

    /* Method to check that every character entered by the user is correct when user input is a number! */
    public boolean checkNumberInput(String check) {

        checkNumberInput = false;

        char[] numberCheck = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        char[] inputNumberArray = check.toCharArray();

        int z = 0;

        for (char anInputNumberArray : inputNumberArray) {

            for (char aNumberCheck : numberCheck) {

                if (anInputNumberArray == aNumberCheck) {

                    z++;

                }
            }
        }

        if (z == inputNumberArray.length) {

            checkNumberInput = true;
        }

        return checkNumberInput;
    }

    /* Method to check that every character entered by the user is correct when user input is a number in currency format! */
    public boolean checkCurrencyInput(String check) {

        checkCurrencyInput = false;

        char[] currencyCheck = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ','};

        char[] inputCurrencyArray = check.toCharArray();

        int z = 0;

        for (char anInputCurrencyArray : inputCurrencyArray) {

            for (char aCurrencyCheck : currencyCheck) {

                if (anInputCurrencyArray == aCurrencyCheck) {

                    z++;

                }
            }
        }

        if (z == inputCurrencyArray.length) {

            checkCurrencyInput = true;
        }

        return checkCurrencyInput;
    }

    /* Method to check if a number in String format needs comas. If number is large enough, a coma will be added at the appropriate places! */
    public String addComas(String checkAdd) {

        StringBuilder buildAdd = new StringBuilder(checkAdd);

        buildAdd.reverse();

        int y = 0;
        boolean inserted = false;

        for (int x = 0; x < buildAdd.length(); x++) {

            y = (y + 1);

            if (y == 7 || (inserted && y == 4)) {

                buildAdd.insert(x , ",");

                inserted = true;
                y = 0;

            }
        }

        buildAdd.reverse();

        return String.valueOf(buildAdd);
    }

    /* Method to remove added comas by the users so that an entered number can be parsed to Double format! */
    public String removeComas(String checkRemove) {

        StringBuilder buildRemove = new StringBuilder(checkRemove);

        for (int x = 0; x < buildRemove.length(); x++) {

            if (buildRemove.charAt(x) == ',') {

                buildRemove.deleteCharAt(x);

            }
        }

        return  String.valueOf(buildRemove);
    }

    /* Method to check the format of numbers for the quantity and rate section of each item. Checks for Integer or Double variable! */
    public boolean checkNumberFormat (String check) {

        checkNumberFormat = true;
        noDecimalCheck = false;

        StringBuilder numberCheck = new StringBuilder(check);

        int a; /* Variable for tracking ',' position of Double variable format! */
        int b; /* Variable for tracking ',' position of Integer variable format! */
        int c = 0; /* Variable for counting the total number of characters in the inputted String that are not equal to '.'! */
        int d = 0; /* Variable for counting the number of times a ',' has been checked in a Double variable! */
        int e = 0; /* Variable for counting the number of times a ',' has been checked in a Integer variable! */

        char[] numberFormatCheck = {'.'};
        char[] numberFormatCheckArray = check.toCharArray();

        numberCheck.reverse();

        /* Determines if the inputted number in String format is a Double variable or not and determines if ',' and '.' are in the right location! */
        for (int x = 0; x < numberCheck.length(); x++) {

            if (numberCheck.length() > 2) {

                if (numberCheck.charAt(2) == '.') {

                    if (numberCheck.charAt(x) == ',') {

                        a = (6 + (d * 4));

                        if (x != a) {

                            checkNumberFormat = false;
                        }

                        d++;
                    }
                }
            }

            if (numberCheck.charAt(x) == '.') {

                if (x != 2) {

                    checkNumberFormat = false;

                }
            }
        }

        /* Determines if the inputted number in String format is a Integer variable or not! */
        for (char aNumberFormatCheckArray : numberFormatCheckArray) {

            for (char aNumberFormatCheck : numberFormatCheck) {

                if (aNumberFormatCheckArray != aNumberFormatCheck) {

                    c++;

                }
            }
        }

        if (c == numberFormatCheckArray.length) {

            noDecimalCheck = true;

        }

        /* Checks that the location of ',' in the Integer variable are correct after inputted String is determined to be in Integer format! */
        if (noDecimalCheck) {

            for (int q = 0; q < numberCheck.length(); q++) {

                if (numberCheck.charAt(q) == ',') {

                    b = (3 + (e * 4));

                    if (q != b) {

                       checkNumberFormat = false;
                    }

                    e++;
                }
            }
        }

        return checkNumberFormat;
    }

    /* Method to check the format of the invoice number entered, must have two '-' and be in the correct position! */
    public boolean invoiceFormat (String check) {

        invoiceCheck = true;

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




} /* Closing bracket for Functions class! */
