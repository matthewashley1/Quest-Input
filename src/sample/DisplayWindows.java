package sample;

/*
 * Created by Matthew Ashley on 12/29/16.
 */

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

class DisplayWindows {

    private HBox item1HBox, item2HBox, item3HBox, item4HBox, item5HBox, item6HBox, item7HBox, item8HBox, item9HBox, item10HBox, item11HBox, item12HBox, item13HBox, item14HBox, item15HBox,
            bottomLeft;

    private VBox itemsVBox; /* VBox that holds all HBox`s for each item! */

    private TextField item1Text, item2Text, item3Text, item4Text, item5Text, item6Text, item7Text, item8Text, item9Text, item10Text, item11Text, item12Text, item13Text, item14Text, item15Text,
            description1Text, description2Text, description3Text, description4Text, description5Text, description6Text, description7Text, description8Text, description9Text, description10Text, description11Text, description12Text, description13Text, description14Text, description15Text,
            qty1Text, qty2Text, qty3Text, qty4Text, qty5Text, qty6Text, qty7Text, qty8Text, qty9Text, qty10Text, qty11Text, qty12Text, qty13Text, qty14Text, qty15Text,
            rate1Text, rate2Text, rate3Text, rate4Text, rate5Text, rate6Text, rate7Text, rate8Text, rate9Text, rate10Text, rate11Text, rate12Text, rate13Text, rate14Text, rate15Text,
            invoiceText, nameAddressText, itemQuantityText, dqProjectText;

    private DatePicker dateText; /* Setup for the Date Picker being used in the Estimate window! */

    private Label totalCost; /* Label used for displaying the total cost of each item! */

    private String enteredDate; /* Variable for the Date Entered, stores the date entered on the estimateWindow! */
    private String costTotalString; /* Variable for the cost total of each item! */
    private String date; /* Variable for storing the current date! */
    private String dateDay; /* Variable for storing the current day of the month when the current day is below 10! */

    private final String[] itemsData = new String [64]; /* Array for storing user input for items data! */

    private Double qty1, qty2, qty3, qty4, qty5, qty6, qty7, qty8, qty9, qty10, qty11, qty12, qty13, qty14, qty15,
            rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8, rate9, rate10, rate11, rate12, rate13, rate14, rate15;

    private Double costTotalDouble = 00.00; /* Variable for calculating the total cost of each item, stores the total cost of each item used to display in the estimate window! */

    private int itemNumber; /* Variable for the items number entered, stores the number entered on the estimateWindow for items! */
    private int savedItemNumber; /* Variable for the last item number entered, stores the last number entered on the estimateWindow for items! */

    private boolean checkDataInput; /* Variable for when data has been entered in every TextField, used to store true or false for checking
                               that data has been entered in every TextField in the checkDataInput method! */
    private boolean checkCurrencyInput; /* Variable for checking number inputs in currency format, used to store true or false whether or not a number in currency format has been entered correctly by the user! */
    private boolean checkNumberInput; /* Variable for checking number inputs, used to store true or false whether or not a number has been entered correctly by the user! */
    private boolean itemsGenerated; /* Variable for checking if items elements have already been created, will store true if items elements have been created! */
    private boolean calculationCheck; /* Variable for checking calculation data, will store true if all calculations pass and false if they don't! */
    private boolean calculationReturn; /* Variable for storing if all calculations passed! */

    private final ConfirmMessages errorCheck = new ConfirmMessages(); /* Initializer for ConfirmMessages class! */
    private final Functions functionCheck = new Functions(); /* Initializer for Functions class! */
    private final ExcelEditing inputWorkbookData = new ExcelEditing(); /* Initializer for ExcelEditing class! */
    private final DecimalFormat df = new DecimalFormat("0.00"); /* Initializer for DecimalFormat, will format a Double variable to always show two decimal places! */

    /* Method for inputting data into Estimate Excel spreadsheet and PDF file for Design Quest! */
    public void inputEstimate() {

        itemsDataInit();

        Stage estimateWindow = new Stage();

        estimateWindow.initModality(Modality.APPLICATION_MODAL);
        estimateWindow.setTitle("Estimate");

        /* Setup for items VBox! */
        itemsVBox = new VBox(8);
        itemsVBox.setPadding(new Insets(8));

        /* Setup for the Left of the Border Pane! */
        Label dqProjectLabel = new Label("DQ Project #:");
        dqProjectText = new TextField();
        dqProjectText.setPrefWidth(100.0);

        Label itemQuantityLabel = new Label("Number of Items:");
        itemQuantityText = new TextField();
        itemQuantityText.setPrefWidth(50.0);

        /* Conditions for the item number enter button, within action for button the estimate window will auto expand
        * the width and height to fit each element in the window. Also, will create and add in the appropriate item elements! */
        Button itemQuantityButton = new Button("Enter");
        itemQuantityButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        itemQuantityButton.setOnAction((ActionEvent e) -> {

            if (itemQuantityText.getText().isEmpty()) {

                errorCheck.infoEnteredError("Items Error", "Nothing was entered of the items Number", "Example: Must be 15 or less!");

            } else {

                checkNumberInput = functionCheck.checkNumberInput(itemQuantityText.getText());

                if (checkNumberInput) {

                    /* Variable for the items number entered, stores the number entered on the estimateWindow for items! */
                    itemNumber = Integer.parseInt(itemQuantityText.getText());

                    if (itemNumber >= savedItemNumber && itemsGenerated) {

                        saveItemData();

                    } else {

                        saveItemData();
                        deleteItemData(false);

                        if (itemsGenerated) {

                            enterItemsData();

                            /* Will re-calculate the total cost of each item when items number is decreased! */
                            calculate(true);
                        }
                    }

                    if (itemNumber <= 15) {

                        savedItemNumber = itemNumber;
                    }

                } else {

                    saveItemData();
                    errorCheck.infoEnteredError("Items Error", "Number of items was enter wrong!", "Example: Must be 15 or less!");
                }
            }

            int addedHeight = (itemNumber * 55);

            /* Determines when and by how much to expand the estimateWindow Stage to fit the elements in the window! */
            if (itemNumber >= 9 && itemNumber <= 15) {

                estimateWindow.setWidth(687.0);

            } else if (itemNumber == 0) {

                bottomLeft.getChildren().remove(totalCost);

                costTotalString = "00.00";
                totalCost = new Label("$" + costTotalString);
                totalCost.setStyle("-fx-font: 16 arial; -fx-base: #b4ffa3;");

                bottomLeft.getChildren().add(totalCost);

                estimateWindow.setWidth(502.0);
                estimateWindow.setHeight(337.0);

            } else if (itemNumber > 15) {

                errorCheck.infoEnteredError("Items Error","Incorrect item number entered!", "Example: Must be 15 or less!");

            } else {

                estimateWindow.setWidth(670.0);
            }

            if (itemNumber <= 15) {

                estimateWindow.setHeight(337.0 + addedHeight);
                itemsVBox.getChildren().removeAll(item1HBox, item2HBox, item3HBox, item4HBox, item5HBox, item6HBox, item7HBox, item8HBox, item9HBox, item10HBox, item11HBox, item12HBox, item13HBox, item14HBox, item15HBox);
            }

            /* Centers the estimateWindow on the device screen! */
            Rectangle2D ScreenBounds = Screen.getPrimary().getVisualBounds();
            estimateWindow.setX((ScreenBounds.getWidth() - estimateWindow.getWidth()) / 2);
            estimateWindow.setY((ScreenBounds.getHeight() - estimateWindow.getHeight()) / 2);

            /* Generates all elements for each item! */
            if (itemNumber >= 1 && itemNumber <= 15) {

                Label item1Label = new Label("Item:");
                item1Text = new TextField();
                item1Text.setPrefWidth(100.0);
                Label description1Label = new Label("Description:");
                description1Text = new TextField();
                Label qty1Label = new Label("Qty:");
                qty1Text = new TextField();
                qty1Text.setPrefWidth(50.0);
                Label rate1Label = new Label("Rate:");
                rate1Text = new TextField();
                rate1Text.setPrefWidth(75.0);

                item1HBox = new HBox(8);
                item1HBox.setPadding(new Insets(10));
                item1HBox.getChildren().addAll(item1Label, item1Text, description1Label, description1Text, qty1Label, qty1Text, rate1Label, rate1Text);

                itemsVBox.getChildren().add(item1HBox);
            }

            if (itemNumber >= 2 && itemNumber <= 15) {

                Label item2Label = new Label("Item:");
                item2Text = new TextField();
                item2Text.setPrefWidth(100.0);
                Label description2Label = new Label("Description:");
                description2Text = new TextField();
                Label qty2Label = new Label("Qty:");
                qty2Text = new TextField();
                qty2Text.setPrefWidth(50.0);
                Label rate2Label = new Label("Rate:");
                rate2Text = new TextField();
                rate2Text.setPrefWidth(75.0);
               
                item2HBox = new HBox(8);
                item2HBox.setPadding(new Insets(10));
                item2HBox.getChildren().addAll(item2Label, item2Text, description2Label, description2Text, qty2Label, qty2Text, rate2Label, rate2Text);

                itemsVBox.getChildren().add(item2HBox);
            }

            if (itemNumber >= 3 && itemNumber <= 15) {

                Label item3Label = new Label("Item:");
                item3Text = new TextField();
                item3Text.setPrefWidth(100.0);
                Label description3Label = new Label("Description:");
                description3Text = new TextField();
                Label qty3Label = new Label("Qty:");
                qty3Text = new TextField();
                qty3Text.setPrefWidth(50.0);
                Label rate3Label = new Label("Rate:");
                rate3Text = new TextField();
                rate3Text.setPrefWidth(75.0);
                
                item3HBox = new HBox(8);
                item3HBox.setPadding(new Insets(10));
                item3HBox.getChildren().addAll(item3Label, item3Text, description3Label, description3Text, qty3Label, qty3Text, rate3Label, rate3Text);

                itemsVBox.getChildren().add(item3HBox);
            }

            if (itemNumber >= 4 && itemNumber <= 15) {

                Label item4Label = new Label("Item:");
                item4Text = new TextField();
                item4Text.setPrefWidth(100.0);
                Label description4Label = new Label("Description:");
                description4Text = new TextField();
                Label qty4Label = new Label("Qty:");
                qty4Text = new TextField();
                qty4Text.setPrefWidth(50.0);
                Label rate4Label = new Label("Rate:");
                rate4Text = new TextField();
                rate4Text.setPrefWidth(75.0);
                
                item4HBox = new HBox(8);
                item4HBox.setPadding(new Insets(10));
                item4HBox.getChildren().addAll(item4Label, item4Text, description4Label, description4Text, qty4Label, qty4Text, rate4Label, rate4Text);

                itemsVBox.getChildren().add(item4HBox);
            }

            if (itemNumber >= 5 && itemNumber <= 15) {

                Label item5Label = new Label("Item:");
                item5Text = new TextField();
                item5Text.setPrefWidth(100.0);
                Label description5Label = new Label("Description:");
                description5Text = new TextField();
                Label qty5Label = new Label("Qty:");
                qty5Text = new TextField();
                qty5Text.setPrefWidth(50.0);
                Label rate5Label = new Label("Rate:");
                rate5Text = new TextField();
                rate5Text.setPrefWidth(75.0);
                
                item5HBox = new HBox(8);
                item5HBox.setPadding(new Insets(10));
                item5HBox.getChildren().addAll(item5Label, item5Text, description5Label, description5Text, qty5Label, qty5Text, rate5Label, rate5Text);

                itemsVBox.getChildren().add(item5HBox);
            }

            if (itemNumber >= 6 && itemNumber <= 15) {

                Label item6Label = new Label("Item:");
                item6Text = new TextField();
                item6Text.setPrefWidth(100.0);
                Label description6Label = new Label("Description:");
                description6Text = new TextField();
                Label qty6Label = new Label("Qty:");
                qty6Text = new TextField();
                qty6Text.setPrefWidth(50.0);
                Label rate6Label = new Label("Rate:");
                rate6Text = new TextField();
                rate6Text.setPrefWidth(75.0);
                
                item6HBox = new HBox(8);
                item6HBox.setPadding(new Insets(10));
                item6HBox.getChildren().addAll(item6Label, item6Text, description6Label, description6Text, qty6Label, qty6Text, rate6Label, rate6Text);

                itemsVBox.getChildren().add(item6HBox);
            }

            if (itemNumber >= 7 && itemNumber <= 15) {

                Label item7Label = new Label("Item:");
                item7Text = new TextField();
                item7Text.setPrefWidth(100.0);
                Label description7Label = new Label("Description:");
                description7Text = new TextField();
                Label qty7Label = new Label("Qty:");
                qty7Text = new TextField();
                qty7Text.setPrefWidth(50.0);
                Label rate7Label = new Label("Rate:");
                rate7Text = new TextField();
                rate7Text.setPrefWidth(75.0);
                
                item7HBox = new HBox(8);
                item7HBox.setPadding(new Insets(10));
                item7HBox.getChildren().addAll(item7Label, item7Text, description7Label, description7Text, qty7Label, qty7Text, rate7Label, rate7Text);

                itemsVBox.getChildren().add(item7HBox);
            }

            if (itemNumber >= 8 && itemNumber <= 15) {

                Label item8Label = new Label("Item:");
                item8Text = new TextField();
                item8Text.setPrefWidth(100.0);
                Label description8Label = new Label("Description:");
                description8Text = new TextField();
                Label qty8Label = new Label("Qty:");
                qty8Text = new TextField();
                qty8Text.setPrefWidth(50.0);
                Label rate8Label = new Label("Rate:");
                rate8Text = new TextField();
                rate8Text.setPrefWidth(75.0);
                
                item8HBox = new HBox(8);
                item8HBox.setPadding(new Insets(10));
                item8HBox.getChildren().addAll(item8Label, item8Text, description8Label, description8Text, qty8Label, qty8Text, rate8Label, rate8Text);

                itemsVBox.getChildren().add(item8HBox);
            }

            if (itemNumber >= 9 && itemNumber <= 15) {

                Label item9Label = new Label("Item:");
                item9Text = new TextField();
                item9Text.setPrefWidth(100.0);
                Label description9Label = new Label("Description:");
                description9Text = new TextField();
                Label qty9Label = new Label("Qty:");
                qty9Text = new TextField();
                qty9Text.setPrefWidth(50.0);
                Label rate9Label = new Label("Rate:");
                rate9Text = new TextField();
                rate9Text.setPrefWidth(75.0);
                
                item9HBox = new HBox(8);
                item9HBox.setPadding(new Insets(10));
                item9HBox.getChildren().addAll(item9Label, item9Text, description9Label, description9Text, qty9Label, qty9Text, rate9Label, rate9Text);

                itemsVBox.getChildren().add(item9HBox);
            }

            if (itemNumber >= 10 && itemNumber <= 15) {

                Label item10Label = new Label("Item:");
                item10Text = new TextField();
                item10Text.setPrefWidth(100.0);
                Label description10Label = new Label("Description:");
                description10Text = new TextField();
                Label qty10Label = new Label("Qty:");
                qty10Text = new TextField();
                qty10Text.setPrefWidth(50.0);
                Label rate10Label = new Label("Rate:");
                rate10Text = new TextField();
                rate10Text.setPrefWidth(75.0);
                
                item10HBox = new HBox(8);
                item10HBox.setPadding(new Insets(10));
                item10HBox.getChildren().addAll(item10Label, item10Text, description10Label, description10Text, qty10Label, qty10Text, rate10Label, rate10Text);

                itemsVBox.getChildren().add(item10HBox);
            }

            if (itemNumber >= 11 && itemNumber <= 15) {

                Label item11Label = new Label("Item:");
                item11Text = new TextField();
                item11Text.setPrefWidth(100.0);
                Label description11Label = new Label("Description:");
                description11Text = new TextField();
                Label qty11Label = new Label("Qty:");
                qty11Text = new TextField();
                qty11Text.setPrefWidth(50.0);
                Label rate11Label = new Label("Rate:");
                rate11Text = new TextField();
                rate11Text.setPrefWidth(75.0);
               
                item11HBox = new HBox(8);
                item11HBox.setPadding(new Insets(10));
                item11HBox.getChildren().addAll(item11Label, item11Text, description11Label, description11Text, qty11Label, qty11Text, rate11Label, rate11Text);

                itemsVBox.getChildren().add(item11HBox);
            }

            if (itemNumber >= 12 && itemNumber <= 15) {

                Label item12Label = new Label("Item:");
                item12Text = new TextField();
                item12Text.setPrefWidth(100.0);
                Label description12Label = new Label("Description:");
                description12Text = new TextField();
                Label qty12Label = new Label("Qty:");
                qty12Text = new TextField();
                qty12Text.setPrefWidth(50.0);
                Label rate12Label = new Label("Rate:");
                rate12Text = new TextField();
                rate12Text.setPrefWidth(75.0);
                
                item12HBox = new HBox(8);
                item12HBox.setPadding(new Insets(10));
                item12HBox.getChildren().addAll(item12Label, item12Text, description12Label, description12Text, qty12Label, qty12Text, rate12Label, rate12Text);

                itemsVBox.getChildren().add(item12HBox);
            }

            if (itemNumber >= 13 && itemNumber <= 15) {

                Label item13Label = new Label("Item:");
                item13Text = new TextField();
                item13Text.setPrefWidth(100.0);
                Label description13Label = new Label("Description:");
                description13Text = new TextField();
                Label qty13Label = new Label("Qty:");
                qty13Text = new TextField();
                qty13Text.setPrefWidth(50.0);
                Label rate13Label = new Label("Rate:");
                rate13Text = new TextField();
                rate13Text.setPrefWidth(75.0);
               
                item13HBox = new HBox(8);
                item13HBox.setPadding(new Insets(10));
                item13HBox.getChildren().addAll(item13Label, item13Text, description13Label, description13Text, qty13Label, qty13Text, rate13Label, rate13Text);

                itemsVBox.getChildren().add(item13HBox);
            }

            if (itemNumber >= 14 && itemNumber <= 15) {

                Label item14Label = new Label("Item:");
                item14Text = new TextField();
                item14Text.setPrefWidth(100.0);
                Label description14Label = new Label("Description:");
                description14Text = new TextField();
                Label qty14Label = new Label("Qty:");
                qty14Text = new TextField();
                qty14Text.setPrefWidth(50.0);
                Label rate14Label = new Label("Rate:");
                rate14Text = new TextField();
                rate14Text.setPrefWidth(75.0);
                
                item14HBox = new HBox(8);
                item14HBox.setPadding(new Insets(10));
                item14HBox.getChildren().addAll(item14Label, item14Text, description14Label, description14Text, qty14Label, qty14Text, rate14Label, rate14Text);

                itemsVBox.getChildren().add(item14HBox);
            }

            if (itemNumber == 15) {

                Label item15Label = new Label("Item:");
                item15Text = new TextField();
                item15Text.setPrefWidth(100.0);
                Label description15Label = new Label("Description:");
                description15Text = new TextField();
                Label qty15Label = new Label("Qty:");
                qty15Text = new TextField();
                qty15Text.setPrefWidth(50.0);
                Label rate15Label = new Label("Rate:");
                rate15Text = new TextField();
                rate15Text.setPrefWidth(75.0);
                
                item15HBox = new HBox(8);
                item15HBox.setPadding(new Insets(10));
                item15HBox.getChildren().addAll(item15Label, item15Text, description15Label, description15Text, qty15Label, qty15Text, rate15Label, rate15Text);

                itemsVBox.getChildren().add(item15HBox);
            }

            if (itemsGenerated) {

                enterItemsData();
            }

            itemsGenerated = true;

        });

        HBox dqProjectHBox = new HBox(8);
        dqProjectHBox.setPadding(new Insets(10));
        dqProjectHBox.getChildren().addAll(dqProjectLabel, dqProjectText, itemQuantityLabel, itemQuantityText, itemQuantityButton);
        dqProjectHBox.setAlignment(Pos.TOP_LEFT);

        Label dateLabel = new Label("Date:");
        dateText = new DatePicker();
        dateText.setPrefWidth(130.0);

        HBox dateHBox = new HBox(8);
        dateHBox.setPadding(new Insets(10));
        dateHBox.getChildren().addAll(dateLabel, dateText);
        dateHBox.setAlignment(Pos.TOP_LEFT);

        Label invoiceLabel = new Label("Invoice #:");
        invoiceText = new TextField();
        invoiceText.setPrefWidth(100.0);

        /* Calendar variable used to get current year, month, and day of month for presetting the value in the invoice TextField! */
        Calendar calendar = new GregorianCalendar(Locale.ENGLISH);

        /* Determines if the current day of the month is less than ten and sets the format correctly if it is! */
        if (calendar.get(Calendar.DATE) < 10) {

            dateDay = "0" + String.valueOf(calendar.get(Calendar.DATE));

        } else {

            dateDay = String.valueOf(calendar.get(Calendar.DATE));

        }

        /* Determines if the current month is less than ten and sets the format correctly if it is! */
        if (calendar.get(Calendar.MONTH) < 10) {

            String addZero = "0";
            String month = addZero + String.valueOf(calendar.get(Calendar.MONTH) + 1);

            date = String.valueOf(calendar.get(Calendar.YEAR) - 2000) + "-" + month + dateDay + "-";

        } else {

            date = String.valueOf(calendar.get(Calendar.YEAR) - 2000) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + dateDay + "-";

        }
        invoiceText.setText(date);

        HBox invoiceHBox = new HBox(8);
        invoiceHBox.setPadding(new Insets(10));
        invoiceHBox.getChildren().addAll(invoiceLabel, invoiceText);
        invoiceHBox.setAlignment(Pos.TOP_LEFT);

        Label nameAddressLabel = new Label("Name / Address:");
        nameAddressText = new TextField();
        nameAddressText.setPrefWidth(350.0);

        HBox nameAddressHBox = new HBox(8);
        nameAddressHBox.setPadding(new Insets(10));
        nameAddressHBox.getChildren().addAll(nameAddressLabel, nameAddressText);
        nameAddressHBox.setAlignment(Pos.TOP_LEFT);

        /* Conditions for the Close button to close the Estimate window! */
        Button closeButton = new Button("Cancel");
        closeButton.setOnAction((ActionEvent e) -> {

            itemNumber = 0;
            itemsGenerated = false;
            deleteItemData(true);
            estimateWindow.close();

        });
        closeButton.setStyle("-fx-font: 13 arial; -fx-base: #C0C0C0;");

        /* Conditions for the Save button to save the entered information for an estimate! */
        Button saveButton = new Button("Save");
        saveButton.setOnAction((ActionEvent e) -> {

            checkDataInput = checkDataInput();

            if (checkDataInput) {

                calculationReturn = calculate(true);

                if (calculationReturn) {

                    saveItemData();

                    inputWorkbookData.estimateExcel(itemsData, savedItemNumber);

                    itemNumber = 0;
                    itemsGenerated = false;
                    deleteItemData(true);

                    estimateWindow.close();

                }
            }
        });
        saveButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");

        /* Conditions for the Calculate button to calculate the total cost of each item! */
        Button calCost = new Button("Calculate");
        calCost.setOnAction((ActionEvent e) -> {

            checkDataInput = checkDataInput();

            calculate(checkDataInput);

        });
        calCost.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");

        totalCost = new Label("$00.00");
        totalCost.setStyle("-fx-font: 16 arial; -fx-base: #b4ffa3;");

        Label total = new Label("Cost Total:");
        total.setStyle("-fx-font: 16 arial; -fx-base: #b4ffa3;");

        /* Setup for Left of the BorderPane! */
        VBox dataInput = new VBox(8);
        dataInput.setPadding(new Insets(10));
        dataInput.getChildren().addAll(dqProjectHBox, dateHBox, invoiceHBox, nameAddressHBox, itemsVBox);

        /* Setup for the Left Bottom of the bottom HBox! */
        bottomLeft = new HBox(8);
        bottomLeft.setPadding(new Insets(10));
        bottomLeft.getChildren().addAll(calCost, total, totalCost);
        bottomLeft.setAlignment(Pos.BOTTOM_LEFT);
        bottomLeft.setMinHeight(65);

        /* Setup for the Right Bottom of the bottom HBox! */
        HBox bottomRight = new HBox(8);
        bottomRight.setPadding(new Insets(10));
        bottomRight.getChildren().addAll(closeButton, saveButton);
        bottomRight.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRight.setMinHeight(65);
        HBox.setHgrow(bottomRight, Priority.ALWAYS);

        /* Setup for HBox to hold bottom of estimateLayout elements! */
        HBox bottom = new HBox(8);
        bottom.getChildren().addAll(bottomLeft, bottomRight);

        /* Setup for the estimateLayout BorderPane and estimateScrollPane ScrollPane! */
        BorderPane estimateLayout = new BorderPane();

        estimateLayout.setLeft(dataInput);
        estimateLayout.setBottom(bottom);

        ScrollPane estimateScrollPane = new ScrollPane();
        estimateScrollPane.setContent(estimateLayout);

        Scene estimateScene = new Scene(estimateScrollPane);
        estimateWindow.setScene(estimateScene);
        estimateWindow.centerOnScreen();
        estimateWindow.setResizable(false);
        estimateWindow.showAndWait();

    }

    public void inputInvoice() {



    }

    public void displayDevInfo() {

        Stage devWindow = new Stage();

        devWindow.initModality(Modality.APPLICATION_MODAL);
        devWindow.setTitle("Developer Information");
        devWindow.setMinWidth(250);
        devWindow.setMinHeight(150);

        /* Conditions for the Close button to close the Developer Information window! */
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> devWindow.close());
        closeButton.setStyle("-fx-font: 13 arial; -fx-base: #C0C0C0;");

        /* Setup for the Bottom of the BorderPane! */
        HBox bottom = new HBox(8);
        bottom.setPadding(new Insets(10));
        bottom.getChildren().addAll(closeButton);
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.setMinHeight(65);

        /* Label for Company information! */
        Label company = new Label("Vern Tech " + "(864)-871-5278, " + "Created 2017");
        company.setStyle("-fx-font: 13 arial; -fx-base: #FFFFFF;");
        company.setPadding(new Insets(10));

        /* Setup for the devLayout BorderPane and the locations of the different elements within the BorderPane! */
        BorderPane devLayout = new BorderPane();

        devLayout.setBottom(bottom);
        devLayout.setCenter(company);

        Scene devScene = new Scene(devLayout);
        devWindow.setScene(devScene);
        devWindow.setResizable(false);
        devWindow.showAndWait();

    }

    public void displayProTip() {


    }

    /* Method to check that data has been entered in each TextField. If data hasn't been entered then a pop-up message will be displayed! */
    private boolean checkDataInput() {

        checkDataInput = false;

        if (itemQuantityText.getText().isEmpty()) {

            errorCheck.infoEnteredError("Items Error", "Nothing was entered for Items Number", "Example: Must be 15 or less!");

        } else if (itemNumber == 0) {

            errorCheck.infoEnteredError("Items Error", "No item number was every entered", "Example: Must be 15 or less!");

        } else if (dateText.getValue() == null) {

            try {

                /* Variable for the Date Entered, stores the date entered on the estimateWindow! */
                enteredDate = dateText.getValue().toString();

            } catch ( NullPointerException e1 ) {

                errorCheck.infoEnteredError("Date Error", "Date entered Incorrectly as " + enteredDate, "Example: 01/11/17");
            }

        } else if (invoiceText.getText().isEmpty()) {

            errorCheck.infoEnteredError("Invoice # Error", "Nothing was entered for Invoice Number", "Example: 12345");

        } else if (nameAddressText.getText().isEmpty()) {

            errorCheck.infoEnteredError("Name Address Error", "Nothing was entered for Name or Address", "Example: DesignQuest, LLC 10601 Highway 14 Gray Court, SC 29645");

        } else if ((itemNumber >= 1 && itemNumber <= 15) && (item1Text.getText().isEmpty() || description1Text.getText().isEmpty() || qty1Text.getText().isEmpty() || rate1Text.getText().isEmpty())) {

            if (item1Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 1 Error", "Nothing was entered for Item 1", "Example: CNC bit");

            } else if (description1Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 1 Error", "Nothing was enter for Description of Item 1", "Example: Bit used to cut steal");

            } else if (qty1Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 1 Error", "Nothing was enter for the Quantity of Item 1", "Example: 5");

            } else if (rate1Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 1 Error", "Nothing was enter for the Rate of Item 1", "Example: $5.67");
            }

        } else if ((itemNumber >= 2 && itemNumber <= 15) && (item2Text.getText().isEmpty() || description2Text.getText().isEmpty() || qty2Text.getText().isEmpty() || rate2Text.getText().isEmpty())) {

            if (item2Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 2 Error", "Nothing was entered for Item 2", "Example: CNC bit");

            } else if (description2Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 2 Error", "Nothing was enter for Description of Item 2", "Example: Bit used to cut steal");

            } else if (qty2Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 2 Error", "Nothing was enter for the Quantity of Item 2", "Example: 5");

            } else if (rate2Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 2 Error", "Nothing was enter for the Rate of Item 2", "Example: $5.67");
            }

        } else if ((itemNumber >= 3 && itemNumber <= 15) && (item3Text.getText().isEmpty() || description3Text.getText().isEmpty() || qty3Text.getText().isEmpty() || rate3Text.getText().isEmpty())) {

            if (item3Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 3 Error", "Nothing was entered for Item 3", "Example: CNC bit");

            } else if (description3Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 3 Error", "Nothing was enter for Description of Item 3", "Example: Bit used to cut steal");

            } else if (qty3Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 3 Error", "Nothing was enter for the Quantity of Item 3", "Example: 5");

            } else if (rate3Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 3 Error", "Nothing was enter for the Rate of Item 3", "Example: $5.67");
            }

        } else if ((itemNumber >= 4 && itemNumber <= 15) && (item4Text.getText().isEmpty() || description4Text.getText().isEmpty() || qty4Text.getText().isEmpty() || rate4Text.getText().isEmpty())) {

            if (item4Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 4 Error", "Nothing was entered for Item 4", "Example: CNC bit");

            } else if (description4Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 4 Error", "Nothing was enter for Description of Item 4", "Example: Bit used to cut steal");

            } else if (qty4Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 4 Error", "Nothing was enter for the Quantity of Item 4", "Example: 5");

            } else if (rate4Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 4 Error", "Nothing was enter for the Rate of Item 4", "Example: $5.67");
            }

        } else if ((itemNumber >= 5 && itemNumber <= 15) && (item5Text.getText().isEmpty() || description5Text.getText().isEmpty() || qty5Text.getText().isEmpty() || rate5Text.getText().isEmpty())) {

            if (item5Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 5 Error", "Nothing was entered for Item 5", "Example: CNC bit");

            } else if (description5Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 5 Error", "Nothing was enter for Description of Item 5", "Example: Bit used to cut steal");

            } else if (qty5Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 5 Error", "Nothing was enter for the Quantity of Item 5", "Example: 5");

            } else if (rate5Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 5 Error", "Nothing was enter for the Rate of Item 5", "Example: $5.67");
            }

        } else if ((itemNumber >= 6 && itemNumber <= 15) && (item6Text.getText().isEmpty() || description6Text.getText().isEmpty() || qty6Text.getText().isEmpty() || rate6Text.getText().isEmpty())) {

            if (item6Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 6 Error", "Nothing was entered for Item 6", "Example: CNC bit");

            } else if (description6Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 6 Error", "Nothing was enter for Description of Item 6", "Example: Bit used to cut steal");

            } else if (qty6Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 6 Error", "Nothing was enter for the Quantity of Item 6", "Example: 5");

            } else if (rate6Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 6 Error", "Nothing was enter for the Rate of Item 6", "Example: $5.67");
            }

        } else if ((itemNumber >= 7 && itemNumber <= 15) && (item7Text.getText().isEmpty() || description7Text.getText().isEmpty() || qty7Text.getText().isEmpty() || rate7Text.getText().isEmpty())) {

            if (item7Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 7 Error", "Nothing was entered for Item 7", "Example: CNC bit");

            } else if (description7Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 7 Error", "Nothing was enter for Description of Item 7", "Example: Bit used to cut steal");

            } else if (qty7Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 7 Error", "Nothing was enter for the Quantity of Item 7", "Example: 5");

            } else if (rate7Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 7 Error", "Nothing was enter for the Rate of Item 7", "Example: $5.67");
            }

        } else if ((itemNumber >= 8 && itemNumber <= 15) && (item8Text.getText().isEmpty() || description8Text.getText().isEmpty() || qty8Text.getText().isEmpty() || rate8Text.getText().isEmpty())) {

            if (item8Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 8 Error", "Nothing was entered for Item 8", "Example: CNC bit");

            } else if (description8Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 8 Error", "Nothing was enter for Description of Item 8", "Example: Bit used to cut steal");

            } else if (qty8Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 8 Error", "Nothing was enter for the Quantity of Item 8", "Example: 5");

            } else if (rate8Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 8 Error", "Nothing was enter for the Rate of Item 8", "Example: $5.67");
            }

        } else if ((itemNumber >= 9 && itemNumber <= 15) && (item9Text.getText().isEmpty() || description9Text.getText().isEmpty() || qty9Text.getText().isEmpty() || rate9Text.getText().isEmpty())) {

            if (item9Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 9 Error", "Nothing was entered for Item 9", "Example: CNC bit");

            } else if (description9Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 9 Error", "Nothing was enter for Description of Item 9", "Example: Bit used to cut steal");

            } else if (qty9Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 9 Error", "Nothing was enter for the Quantity of Item 9", "Example: 5");

            } else if (rate9Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 9 Error", "Nothing was enter for the Rate of Item 9", "Example: $5.67");
            }

        } else if ((itemNumber >= 10 && itemNumber <= 15) && (item10Text.getText().isEmpty() || description10Text.getText().isEmpty() || qty10Text.getText().isEmpty() || rate10Text.getText().isEmpty())) {

            if (item10Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 10 Error", "Nothing was entered for Item 10", "Example: CNC bit");

            } else if (description10Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 10 Error", "Nothing was enter for Description of Item 10", "Example: Bit used to cut steal");

            } else if (qty10Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 10 Error", "Nothing was enter for the Quantity of Item 10", "Example: 5");

            } else if (rate10Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 10 Error", "Nothing was enter for the Rate of Item 10", "Example: $5.67");
            }

        } else if ((itemNumber >= 11 && itemNumber <= 15) && (item11Text.getText().isEmpty() || description11Text.getText().isEmpty() || qty11Text.getText().isEmpty() || rate11Text.getText().isEmpty())) {

            if (item11Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 11 Error", "Nothing was entered for Item 11", "Example: CNC bit");

            } else if (description11Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 11 Error", "Nothing was enter for Description of Item 11", "Example: Bit used to cut steal");

            } else if (qty11Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 11 Error", "Nothing was enter for the Quantity of Item 11", "Example: 5");

            } else if (rate11Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 11 Error", "Nothing was enter for the Rate of Item 11", "Example: $5.67");
            }

        } else if ((itemNumber >= 12 && itemNumber <= 15) && (item12Text.getText().isEmpty() || description12Text.getText().isEmpty() || qty12Text.getText().isEmpty() || rate12Text.getText().isEmpty())) {

            if (item12Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 12 Error", "Nothing was entered for Item 12", "Example: CNC bit");

            } else if (description12Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 12 Error", "Nothing was enter for Description of Item 12", "Example: Bit used to cut steal");

            } else if (qty12Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 12 Error", "Nothing was enter for the Quantity of Item 12", "Example: 5");

            } else if (rate12Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 12 Error", "Nothing was enter for the Rate of Item 12", "Example: $5.67");
            }

        } else if ((itemNumber >= 13 && itemNumber <= 15) && (item13Text.getText().isEmpty() || description13Text.getText().isEmpty() || qty13Text.getText().isEmpty() || rate13Text.getText().isEmpty())) {

            if (item13Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 13 Error", "Nothing was entered for Item 13", "Example: CNC bit");

            } else if (description13Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 13 Error", "Nothing was enter for Description of Item 13", "Example: Bit used to cut steal");

            } else if (qty13Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 13 Error", "Nothing was enter for the Quantity of Item 13", "Example: 5");

            } else if (rate13Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 13 Error", "Nothing was enter for the Rate of Item 13", "Example: $5.67");
            }

        } else if ((itemNumber >= 14 && itemNumber <= 15) && (item14Text.getText().isEmpty() || description14Text.getText().isEmpty() || qty14Text.getText().isEmpty() || rate14Text.getText().isEmpty())) {

            if (item14Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 14 Error", "Nothing was entered for Item 14", "Example: CNC bit");

            } else if (description14Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 14 Error", "Nothing was enter for Description of Item 14", "Example: Bit used to cut steal");

            } else if (qty14Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 14 Error", "Nothing was enter for the Quantity of Item 14", "Example: 5");

            } else if (rate14Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 14 Error", "Nothing was enter for the Rate of Item 14", "Example: $5.67");
            }

        } else if (itemNumber == 15 && (item15Text.getText().isEmpty() || description15Text.getText().isEmpty() || qty15Text.getText().isEmpty() || rate15Text.getText().isEmpty())) {

            if (item15Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Item 15 Error", "Nothing was entered for Item 15", "Example: CNC bit");

            } else if (description15Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Description 15 Error", "Nothing was enter for Description of Item 15", "Example: Bit used to cut steal");

            } else if (qty15Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Quantity 15 Error", "Nothing was enter for the Quantity of Item 15", "Example: 5");

            } else if (rate15Text.getText().isEmpty()) {

                errorCheck.infoEnteredError("Rate 15 Error", "Nothing was enter for the Rate of Item 15", "Example: $5.67");
            }

        } else {

            checkDataInput = true;
        }

        if (!dqProjectText.getText().isEmpty() && checkDataInput) {

            checkNumberInput = functionCheck.checkNumberInput(dqProjectText.getText());

            if (!checkNumberInput) {

                errorCheck.infoEnteredError("Number Format Error", "Number for DQ Project Number was entered wrong", "Example: 481532");

                checkDataInput = false;

            }
        }

        if (!invoiceText.getText().isEmpty() && checkDataInput) {

            checkNumberInput = functionCheck.invoiceFormat(invoiceText.getText());

            if (!checkNumberInput) {

                errorCheck.infoEnteredError("Number Format Error", "Number for Invoice Number was entered wrong", "Example: 17-0128-01");

                checkDataInput = false;

            }
        }

        return checkDataInput;
    }

    /* Method to calculate to total cost of each item! */
    @SuppressWarnings("ConstantConditions")
    private boolean calculate(boolean check) {

        calculationCheck = true;

        if (check) {

            bottomLeft.getChildren().remove(totalCost);

            costTotalDouble = 00.00;

            if ((itemNumber >= 1 && itemNumber <= 15) && (!qty1Text.getText().isEmpty() || !rate1Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty1Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty1Text.getText());

                    if (checkCurrencyInput) {

                        qty1 = Double.parseDouble(functionCheck.removeComas(qty1Text.getText()));

                    } else {

                        qty1 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 1 Quantity", "Example: 1,234 or 1,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty1Text.getText().isEmpty()) {

                        qty1 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 1 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate1Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate1Text.getText());

                    if (checkCurrencyInput) {

                        rate1 = Double.parseDouble(functionCheck.removeComas(rate1Text.getText()));

                    } else {

                        rate1 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 1 Rate", "Example: 1,234 or 1,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate1Text.getText().isEmpty()) {

                        rate1 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 1 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty1 != null && rate1 != null) {

                    costTotalDouble = (costTotalDouble + (qty1 * rate1));
                }
            }

            if ((itemNumber >= 2 && itemNumber <= 15) && (!qty2Text.getText().isEmpty() || !rate2Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty2Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty2Text.getText());

                    if (checkCurrencyInput) {

                        qty2 = Double.parseDouble(functionCheck.removeComas(qty2Text.getText()));

                    } else {

                        qty2 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 2 Quantity", "Example: 2,234 or 2,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty2Text.getText().isEmpty()) {

                        qty2 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 2 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate2Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate2Text.getText());

                    if (checkCurrencyInput) {

                        rate2 = Double.parseDouble(functionCheck.removeComas(rate2Text.getText()));

                    } else {

                        rate2 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 2 Rate", "Example: 2,234 or 2,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate2Text.getText().isEmpty()) {

                        rate2 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 2 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty2 != null && rate2 != null) {

                    costTotalDouble = (costTotalDouble + (qty2 * rate2));
                }
            }

            if ((itemNumber >= 3 && itemNumber <= 15) && (!qty3Text.getText().isEmpty() || !rate3Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty3Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty3Text.getText());

                    if (checkCurrencyInput) {

                        qty3 = Double.parseDouble(functionCheck.removeComas(qty3Text.getText()));

                    } else {

                        qty3 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 3 Quantity", "Example: 3,234 or 3,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty3Text.getText().isEmpty()) {

                        qty3 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 3 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate3Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate3Text.getText());

                    if (checkCurrencyInput) {

                        rate3 = Double.parseDouble(functionCheck.removeComas(rate3Text.getText()));

                    } else {

                        rate3 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 3 Rate", "Example: 3,234 or 3,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate3Text.getText().isEmpty()) {

                        rate3 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 3 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty3 != null && rate3 != null) {

                    costTotalDouble = (costTotalDouble + (qty3 * rate3));
                }
            }

            if ((itemNumber >= 4 && itemNumber <= 15) && (!qty4Text.getText().isEmpty() || !rate4Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty4Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty4Text.getText());

                    if (checkCurrencyInput) {

                        qty4 = Double.parseDouble(functionCheck.removeComas(qty4Text.getText()));

                    } else {

                        qty4 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 4 Quantity", "Example: 4,234 or 4,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty4Text.getText().isEmpty()) {

                        qty4 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 4 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate4Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate4Text.getText());

                    if (checkCurrencyInput) {

                        rate4 = Double.parseDouble(functionCheck.removeComas(rate4Text.getText()));

                    } else {

                        rate4 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 4 Rate", "Example: 4,234 or 4,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate4Text.getText().isEmpty()) {

                        rate4 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 4 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty4 != null && rate4 != null) {

                    costTotalDouble = (costTotalDouble + (qty4 * rate4));
                }
            }

            if ((itemNumber >= 5 && itemNumber <= 15) && (!qty5Text.getText().isEmpty() || !rate5Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty5Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty5Text.getText());

                    if (checkCurrencyInput) {

                        qty5 = Double.parseDouble(functionCheck.removeComas(qty5Text.getText()));

                    } else {

                        qty5 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 5 Quantity", "Example: 5,234 or 5,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty5Text.getText().isEmpty()) {

                        qty5 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 5 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate5Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate5Text.getText());

                    if (checkCurrencyInput) {

                        rate5 = Double.parseDouble(functionCheck.removeComas(rate5Text.getText()));

                    } else {

                        rate5 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 5 Rate", "Example: 5,234 or 5,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate5Text.getText().isEmpty()) {

                        rate5 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 5 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty5 != null && rate5 != null) {

                    costTotalDouble = (costTotalDouble + (qty5 * rate5));
                }
            }

            if ((itemNumber >= 6 && itemNumber <= 15) && (!qty6Text.getText().isEmpty() || !rate6Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty6Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty6Text.getText());

                    if (checkCurrencyInput) {

                        qty6 = Double.parseDouble(functionCheck.removeComas(qty6Text.getText()));

                    } else {

                        qty6 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 6 Quantity", "Example: 6,234 or 6,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty6Text.getText().isEmpty()) {

                        qty6 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 6 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate6Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate6Text.getText());

                    if (checkCurrencyInput) {

                        rate6 = Double.parseDouble(functionCheck.removeComas(rate6Text.getText()));

                    } else {

                        rate6 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 6 Rate", "Example: 6,234 or 6,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate6Text.getText().isEmpty()) {

                        rate6 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 6 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty6 != null && rate6 != null) {

                    costTotalDouble = (costTotalDouble + (qty6 * rate6));
                }
            }

            if ((itemNumber >= 7 && itemNumber <= 15) && (!qty7Text.getText().isEmpty() || !rate7Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty7Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty7Text.getText());

                    if (checkCurrencyInput) {

                        qty7 = Double.parseDouble(functionCheck.removeComas(qty7Text.getText()));

                    } else {

                        qty7 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 7 Quantity", "Example: 7,234 or 7,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty7Text.getText().isEmpty()) {

                        qty7 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 7 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate7Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate7Text.getText());

                    if (checkCurrencyInput) {

                        rate7 = Double.parseDouble(functionCheck.removeComas(rate7Text.getText()));

                    } else {

                        rate7 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 7 Rate", "Example: 7,234 or 7,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate7Text.getText().isEmpty()) {

                        rate7 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 7 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty7 != null && rate7 != null) {

                    costTotalDouble = (costTotalDouble + (qty7 * rate7));
                }
            }

            if ((itemNumber >= 8 && itemNumber <= 15) && (!qty8Text.getText().isEmpty() || !rate8Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty8Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty8Text.getText());

                    if (checkCurrencyInput) {

                        qty8 = Double.parseDouble(functionCheck.removeComas(qty8Text.getText()));

                    } else {

                        qty8 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 8 Quantity", "Example: 8,234 or 8,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty8Text.getText().isEmpty()) {

                        qty8 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 8 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate8Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate8Text.getText());

                    if (checkCurrencyInput) {

                        rate8 = Double.parseDouble(functionCheck.removeComas(rate8Text.getText()));

                    } else {

                        rate8 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 8 Rate", "Example: 8,234 or 8,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate8Text.getText().isEmpty()) {

                        rate8 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 8 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty8 != null && rate8 != null) {

                    costTotalDouble = (costTotalDouble + (qty8 * rate8));
                }
            }

            if ((itemNumber >= 9 && itemNumber <= 15) && (!qty9Text.getText().isEmpty() || !rate9Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty9Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty9Text.getText());

                    if (checkCurrencyInput) {

                        qty9 = Double.parseDouble(functionCheck.removeComas(qty9Text.getText()));

                    } else {

                        qty9 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 9 Quantity", "Example: 9,234 or 9,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty9Text.getText().isEmpty()) {

                        qty9 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 9 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate9Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate9Text.getText());

                    if (checkCurrencyInput) {

                        rate9 = Double.parseDouble(functionCheck.removeComas(rate9Text.getText()));

                    } else {

                        rate9 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 9 Rate", "Example: 9,234 or 9,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate9Text.getText().isEmpty()) {

                        rate9 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 9 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty9 != null && rate9 != null) {

                    costTotalDouble = (costTotalDouble + (qty9 * rate9));
                }
            }

            if ((itemNumber >= 10 && itemNumber <= 15) && (!qty10Text.getText().isEmpty() || !rate10Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty10Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty10Text.getText());

                    if (checkCurrencyInput) {

                        qty10 = Double.parseDouble(functionCheck.removeComas(qty10Text.getText()));

                    } else {

                        qty10 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 10 Quantity", "Example: 10,234 or 10,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty10Text.getText().isEmpty()) {

                        qty10 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 10 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate10Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate10Text.getText());

                    if (checkCurrencyInput) {

                        rate10 = Double.parseDouble(functionCheck.removeComas(rate10Text.getText()));

                    } else {

                        rate10 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 10 Rate", "Example: 10,234 or 10,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate10Text.getText().isEmpty()) {

                        rate10 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 10 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty10 != null && rate10 != null) {

                    costTotalDouble = (costTotalDouble + (qty10 * rate10));
                }
            }

            if ((itemNumber >= 11 && itemNumber <= 15) && (!qty11Text.getText().isEmpty() || !rate11Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty11Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty11Text.getText());

                    if (checkCurrencyInput) {

                        qty11 = Double.parseDouble(functionCheck.removeComas(qty11Text.getText()));

                    } else {

                        qty11 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 11 Quantity", "Example: 11,234 or 11,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty11Text.getText().isEmpty()) {

                        qty11 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 11 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate11Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate11Text.getText());

                    if (checkCurrencyInput) {

                        rate11 = Double.parseDouble(functionCheck.removeComas(rate11Text.getText()));

                    } else {

                        rate11 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 11 Rate", "Example: 11,234 or 11,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate11Text.getText().isEmpty()) {

                        rate11 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 11 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty11 != null && rate11 != null) {

                    costTotalDouble = (costTotalDouble + (qty11 * rate11));
                }
            }

            if ((itemNumber >= 12 && itemNumber <= 15) && (!qty2Text.getText().isEmpty() || !rate2Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty12Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty12Text.getText());

                    if (checkCurrencyInput) {

                        qty12 = Double.parseDouble(functionCheck.removeComas(qty12Text.getText()));

                    } else {

                        qty12 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 12 Quantity", "Example: 12,234 or 12,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty12Text.getText().isEmpty()) {

                        qty12 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 12 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate12Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate12Text.getText());

                    if (checkCurrencyInput) {

                        rate12 = Double.parseDouble(functionCheck.removeComas(rate12Text.getText()));

                    } else {

                        rate12 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 12 Rate", "Example: 12,234 or 12,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate12Text.getText().isEmpty()) {

                        rate12 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 12 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty12 != null && rate12 != null) {

                    costTotalDouble = (costTotalDouble + (qty12 * rate12));
                }
            }

            if ((itemNumber >= 13 && itemNumber <= 15) && (!qty13Text.getText().isEmpty() || !rate13Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty13Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty13Text.getText());

                    if (checkCurrencyInput) {

                        qty13 = Double.parseDouble(functionCheck.removeComas(qty13Text.getText()));

                    } else {

                        qty13 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 13 Quantity", "Example: 13,234 or 13,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty13Text.getText().isEmpty()) {

                        qty13 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 13 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate13Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate13Text.getText());

                    if (checkCurrencyInput) {

                        rate13 = Double.parseDouble(functionCheck.removeComas(rate13Text.getText()));

                    } else {

                        rate13 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 13 Rate", "Example: 13,234 or 13,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate13Text.getText().isEmpty()) {

                        rate13 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 13 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty13 != null && rate13 != null) {

                    costTotalDouble = (costTotalDouble + (qty13 * rate13));
                }
            }

            if ((itemNumber >= 14 && itemNumber <= 15) && (!qty14Text.getText().isEmpty() || !rate14Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty14Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty14Text.getText());

                    if (checkCurrencyInput) {

                        qty14 = Double.parseDouble(functionCheck.removeComas(qty14Text.getText()));

                    } else {

                        qty14 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 14 Quantity", "Example: 14,234 or 14,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty14Text.getText().isEmpty()) {

                        qty14 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 14 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate14Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate14Text.getText());

                    if (checkCurrencyInput) {

                        rate14 = Double.parseDouble(functionCheck.removeComas(rate14Text.getText()));

                    } else {

                        rate14 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 14 Rate", "Example: 14,234 or 14,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate14Text.getText().isEmpty()) {

                        rate14 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 14 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty14 != null && rate14 != null) {

                    costTotalDouble = (costTotalDouble + (qty14 * rate14));
                }
            }

            if (itemNumber == 15 && (!qty15Text.getText().isEmpty() || !rate15Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty15Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty15Text.getText());

                    if (checkCurrencyInput) {

                        qty15 = Double.parseDouble(functionCheck.removeComas(qty15Text.getText()));

                    } else {

                        qty15 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 15 Quantity", "Example: 15,234 or 15,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!qty15Text.getText().isEmpty()) {

                        qty15 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 15 Quantity", "Example: 2435 or 2,435");

                    }
                }

                checkCurrencyInput = functionCheck.checkCurrencyInput(rate15Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(rate15Text.getText());

                    if (checkCurrencyInput) {

                        rate15 = Double.parseDouble(functionCheck.removeComas(rate15Text.getText()));

                    } else {

                        rate15 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was formatted incorrectly for item 15 Rate", "Example: 15,234 or 15,453.67 : Must have two decimal places");
                    }

                } else {

                    if (!rate15Text.getText().isEmpty()) {

                        rate15 = 0.00;
                        calculationCheck = false;
                        errorCheck.infoEnteredError("Number Format Error", "Number was entered wrong for item 15 Rate", "Example: 5432.78 or 5,432.78");

                    }
                }

                if (qty15 != null && rate15 != null) {

                    costTotalDouble = (costTotalDouble + (qty15 * rate15));
                }
            }

            costTotalString = functionCheck.addComas(df.format(costTotalDouble));

            totalCost = new Label("$" + costTotalString);
            totalCost.setStyle("-fx-font: 16 arial; -fx-base: #b4ffa3;");

            bottomLeft.getChildren().add(totalCost);

        }

        return calculationCheck;
    }

    private void saveItemData() {

        if (!dqProjectText.getText().isEmpty()) {

            itemsData[0] = dqProjectText.getText();

        }

        if (dateText.getValue() != null) {

            itemsData[1] = dateText.getValue().toString();

        }

        if (!invoiceText.getText().isEmpty()) {

            itemsData[2] = invoiceText.getText();

        }

        if (!nameAddressText.getText().isEmpty()) {

            itemsData[3] = nameAddressText.getText();

        }

        if (savedItemNumber >= 1) {

            itemsData[4] = item1Text.getText();
            itemsData[5] = description1Text.getText();
            itemsData[6] = qty1Text.getText();
            itemsData[7] = rate1Text.getText();
        }

        if (savedItemNumber >= 2) {

            itemsData[8] = item2Text.getText();
            itemsData[9] = description2Text.getText();
            itemsData[10] = qty2Text.getText();
            itemsData[11] = rate2Text.getText();
        }

        if (savedItemNumber >= 3) {

            itemsData[12] = item3Text.getText();
            itemsData[13] = description3Text.getText();
            itemsData[14] = qty3Text.getText();
            itemsData[15] = rate3Text.getText();
        }

        if (savedItemNumber >= 4) {

            itemsData[16] = item4Text.getText();
            itemsData[17] = description4Text.getText();
            itemsData[18] = qty4Text.getText();
            itemsData[19] = rate4Text.getText();
        }

        if (savedItemNumber >= 5) {

            itemsData[20] = item5Text.getText();
            itemsData[21] = description5Text.getText();
            itemsData[22] = qty5Text.getText();
            itemsData[23] = rate5Text.getText();
        }

        if (savedItemNumber >= 6) {

            itemsData[24] = item6Text.getText();
            itemsData[25] = description6Text.getText();
            itemsData[26] = qty6Text.getText();
            itemsData[27] = rate6Text.getText();
        }

        if (savedItemNumber >= 7) {

            itemsData[28] = item7Text.getText();
            itemsData[29] = description7Text.getText();
            itemsData[30] = qty7Text.getText();
            itemsData[31] = rate7Text.getText();
        }

        if (savedItemNumber >= 8) {

            itemsData[32] = item8Text.getText();
            itemsData[33] = description8Text.getText();
            itemsData[34] = qty8Text.getText();
            itemsData[35] = rate8Text.getText();
        }

        if (savedItemNumber >= 9) {

            itemsData[36] = item9Text.getText();
            itemsData[37] = description9Text.getText();
            itemsData[38] = qty9Text.getText();
            itemsData[39] = rate9Text.getText();
        }

        if (savedItemNumber >= 10) {

            itemsData[40] = item10Text.getText();
            itemsData[41] = description10Text.getText();
            itemsData[42] = qty10Text.getText();
            itemsData[43] = rate10Text.getText();
        }

        if (savedItemNumber >= 11) {

            itemsData[44] = item11Text.getText();
            itemsData[45] = description11Text.getText();
            itemsData[46] = qty11Text.getText();
            itemsData[47] = rate11Text.getText();
        }

        if (savedItemNumber >= 12) {

            itemsData[48] = item12Text.getText();
            itemsData[49] = description12Text.getText();
            itemsData[50] = qty12Text.getText();
            itemsData[51] = rate12Text.getText();
        }

        if (savedItemNumber >= 13) {

            itemsData[52] = item13Text.getText();
            itemsData[53] = description13Text.getText();
            itemsData[54] = qty13Text.getText();
            itemsData[55] = rate13Text.getText();
        }

        if (savedItemNumber >= 14) {

            itemsData[56] = item14Text.getText();
            itemsData[57] = description14Text.getText();
            itemsData[58] = qty14Text.getText();
            itemsData[59] = rate14Text.getText();
        }

        if (savedItemNumber == 15) {

            itemsData[60] = item15Text.getText();
            itemsData[61] = description15Text.getText();
            itemsData[62] = qty15Text.getText();
            itemsData[64] = rate15Text.getText();
        }

    }

    @SuppressWarnings("ConstantConditions")
    private  void enterItemsData() {
        
        if (itemNumber >= 1 && itemNumber <= 15) {

            item1Text.setText(itemsData[4]);
            description1Text.setText(itemsData[5]);
            qty1Text.setText(itemsData[6]);
            rate1Text.setText(itemsData[7]);
        }

        if (itemNumber >= 2 && itemNumber <= 15) {

            item2Text.setText(itemsData[8]);
            description2Text.setText(itemsData[9]);
            qty2Text.setText(itemsData[10]);
            rate2Text.setText(itemsData[11]);
        }

        if (itemNumber >= 3 && itemNumber <= 15) {

            item3Text.setText(itemsData[12]);
            description3Text.setText(itemsData[13]);
            qty3Text.setText(itemsData[14]);
            rate3Text.setText(itemsData[15]);
        }

        if (itemNumber >= 4 && itemNumber <= 15) {

            item4Text.setText(itemsData[16]);
            description4Text.setText(itemsData[17]);
            qty4Text.setText(itemsData[18]);
            rate4Text.setText(itemsData[19]);
        }

        if (itemNumber >= 5 && itemNumber <= 15) {

            item5Text.setText(itemsData[20]);
            description5Text.setText(itemsData[21]);
            qty5Text.setText(itemsData[22]);
            rate5Text.setText(itemsData[23]);
        }

        if (itemNumber >= 6 && itemNumber <= 15) {

            item6Text.setText(itemsData[24]);
            description6Text.setText(itemsData[25]);
            qty6Text.setText(itemsData[26]);
            rate6Text.setText(itemsData[27]);
        }

        if (itemNumber >= 7 && itemNumber <= 15) {

            item7Text.setText(itemsData[28]);
            description7Text.setText(itemsData[29]);
            qty7Text.setText(itemsData[30]);
            rate7Text.setText(itemsData[31]);
        }

        if (itemNumber >= 8 && itemNumber <= 15) {

            item8Text.setText(itemsData[32]);
            description8Text.setText(itemsData[33]);
            qty8Text.setText(itemsData[34]);
            rate8Text.setText(itemsData[35]);
        }

        if (itemNumber >= 9 && itemNumber <= 15) {

            item9Text.setText(itemsData[36]);
            description9Text.setText(itemsData[37]);
            qty9Text.setText(itemsData[38]);
            rate9Text.setText(itemsData[39]);
        }

        if (itemNumber >= 10 && itemNumber <= 15) {

            item10Text.setText(itemsData[40]);
            description10Text.setText(itemsData[41]);
            qty10Text.setText(itemsData[42]);
            rate10Text.setText(itemsData[43]);
        }

        if (itemNumber >= 11 && itemNumber <= 15) {

            item11Text.setText(itemsData[44]);
            description11Text.setText(itemsData[45]);
            qty11Text.setText(itemsData[46]);
            rate11Text.setText(itemsData[47]);
        }

        if (itemNumber >= 12 && itemNumber <= 15) {

            item12Text.setText(itemsData[48]);
            description12Text.setText(itemsData[49]);
            qty12Text.setText(itemsData[50]);
            rate12Text.setText(itemsData[51]);
        }

        if (itemNumber >= 13 && itemNumber <= 15) {

            item13Text.setText(itemsData[52]);
            description13Text.setText(itemsData[53]);
            qty13Text.setText(itemsData[54]);
            rate13Text.setText(itemsData[55]);
        }

        if (itemNumber >= 14 && itemNumber <= 15) {

            item14Text.setText(itemsData[56]);
            description14Text.setText(itemsData[57]);
            qty14Text.setText(itemsData[58]);
            rate14Text.setText(itemsData[59]);
        }

        if (itemNumber == 15) {

            item15Text.setText(itemsData[60]);
            description15Text.setText(itemsData[61]);
            qty15Text.setText(itemsData[62]);
            rate15Text.setText(itemsData[63]);
        }
    }
    
    private void deleteItemData(boolean deleteThis) {

        if (!deleteThis) {

            for (int x = ((itemNumber * 4) + 4); x < ((savedItemNumber * 4) + 4); x++) {

                itemsData[x] = "";

            }
        }

        if (deleteThis) {

            for (int y = 0; y < 64; y++) {

                itemsData[y] = "";

            }
        }
    }

    private void itemsDataInit() {

        for (int x = 0; x < 64; x++) {

            itemsData[x] = "";

        }
    }




} /* Closing bracket for the DisplayWindows class! */
