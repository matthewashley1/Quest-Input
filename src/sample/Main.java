package sample;

/*
 * Created by Matthew Ashley on 12/15/16.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class Main extends Application {

    private HBox item1HBox, item2HBox, item3HBox, item4HBox, item5HBox, item6HBox, item7HBox, item8HBox, item9HBox, item10HBox, item11HBox, item12HBox, item13HBox, item14HBox, item15HBox,
            bottomLeft;

    private HBox bottom; /* HBox for HBox to hold bottom of estimateLayout VBox elements for estimate form! */

    private VBox itemsVBox; /* VBox that holds all HBox`s for each item is estimate form! */
    private VBox estimateLayout; /* VBox that holds all elements for estimateScrollPane! */
    private VBox errorMessages; /* VBox used for displaying error messages in estimate form! */
    private VBox dataInput; /* VBox for the top portion of the estimateLayout VBox in estimate form! */
    private VBox content; /* VBox that holds all elements for center of BorderPane for window Stage! */

    private ScrollPane estimateScrollPane; /* ScrollPane for the estimate form! */

    private Scene mainScene; /* Is the main Scene for program to be set inside the window Stage! */

    private Stage window; /* Is the primary Stage for program! */

    private TextField item1Text, item2Text, item3Text, item4Text, item5Text, item6Text, item7Text, item8Text, item9Text, item10Text, item11Text, item12Text, item13Text, item14Text, item15Text,
            description1Text, description2Text, description3Text, description4Text, description5Text, description6Text, description7Text, description8Text, description9Text, description10Text, description11Text, description12Text, description13Text, description14Text, description15Text,
            qty1Text, qty2Text, qty3Text, qty4Text, qty5Text, qty6Text, qty7Text, qty8Text, qty9Text, qty10Text, qty11Text, qty12Text, qty13Text, qty14Text, qty15Text,
            rate1Text, rate2Text, rate3Text, rate4Text, rate5Text, rate6Text, rate7Text, rate8Text, rate9Text, rate10Text, rate11Text, rate12Text, rate13Text, rate14Text, rate15Text,
            invoiceText, nameAddressText, itemQuantityText, dqProjectText;

    private DatePicker dateText; /* Setup for the Date Picker being used in the Estimate window! */

    private Label totalCost; /* Label used for displaying the total cost of each item! */
    private Label messages; /* Label used for displaying error messages! */
    private Label contentPrompt; /* Label used for display a prompt message for selecting a form to complete! */

    private String costTotalString; /* Variable for the cost total of each item! */
    private String date; /* Variable for storing the current date! */
    private String dateDay; /* Variable for storing the current day of the month when the current day is below 10! */

    private final String[] itemsData = new String [64]; /* Array for storing user input for items data on estimate form! */

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
    private boolean closeProgram; /* Variable for checking if the program is going to be closed! */
    private boolean estimateProgramHasRan = false; /* Variable for storing if the estimate form has been created before! */
    private boolean estimateFormRunning; /* Variable for storing if an estimate form is being filled! */

    private final Functions functionCheck = new Functions(); /* Initializer for Functions class! */
    private final ExcelEditing inputWorkbookData = new ExcelEditing(); /* Initializer for ExcelEditing class! */
    private final ConfirmMessages confirmProgramClose = new ConfirmMessages(); /* Initializer for ConfirmMessages class! */
    private final DecimalFormat df = new DecimalFormat("0.00"); /* Initializer for a DecimalFormat instance, will format a Double variable to always show two decimal places! */
    private final Timer timer = new Timer(); /* Initializer for a timer instance! */
    private final DoubleProperty fontSize = new SimpleDoubleProperty(10); /* Initializer for a DoubleProperty variable, used to bind the font size of buttons relative to the main Stage width! */

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        window = primaryStage;

        /* Variable used to get the bounds of the device screen! */
        Rectangle2D ScreenBounds = Screen.getPrimary().getVisualBounds();

        URL logoImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/image001.png");
        URL estimateButtonImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/Estimate.png");
        URL invoiceButtonImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/Invoice.png");
        URL workOrderButtonImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/WorkOrder.png");
        URL salesReceiptButtonImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/SalesReceipt.png");

        /* Setup for Design Quest Image on window Stage! */
        ImageView logo = new ImageView(new Image(String.valueOf(logoImagePath)));
        logo.fitWidthProperty().bind(window.widthProperty().divide(3));
        logo.setFitHeight(100);

        /* Conditions for the Estimate button and it's content! */
        ImageView estimateButtonImage = new ImageView(new Image(String.valueOf(estimateButtonImagePath)));
        estimateButtonImage.setFitHeight(70);
        estimateButtonImage.setFitWidth(70);

        Button estimate = new Button("Estimate");
        estimate.setStyle("-fx-font: 18 arial; -fx-base: #3178ea;");
        estimate.setGraphic(estimateButtonImage);
        estimate.setContentDisplay(ContentDisplay.RIGHT);
        estimate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        estimate.setMinHeight(100);
        estimate.setPrefWidth(window.widthProperty().doubleValue() / 3);
        estimate.setOnAction(e -> {

            if (estimateProgramHasRan) {

                itemsDataInit();
            }

            if (!estimateFormRunning) {

                estimateFromCreate();

                content.getChildren().removeAll(contentPrompt);
                content.getChildren().add(estimateScrollPane);
                content.setAlignment(Pos.TOP_LEFT);
            }

            estimateProgramHasRan = true;
        });

        /* Conditions for the Invoice button and it's content! */
        ImageView invoiceButton = new ImageView(new Image(String.valueOf(invoiceButtonImagePath)));
        invoiceButton.setFitHeight(70);
        invoiceButton.setFitWidth(70);

        Button invoice = new Button("Invoice");
        invoice.setStyle("-fx-font: 18 arial; -fx-base: #31eaa6;");
        invoice.setGraphic(invoiceButton);
        invoice.setContentDisplay(ContentDisplay.RIGHT);
        invoice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        invoice.setMinHeight(100);
        invoice.setPrefWidth(window.widthProperty().doubleValue() / 3);
        invoice.setOnAction(e -> inputInvoice());

        /* Conditions for the Work Order button and it's content! */
        ImageView workOrderButton = new ImageView(new Image(String.valueOf(workOrderButtonImagePath)));
        workOrderButton.setFitHeight(70);
        workOrderButton.setFitWidth(70);

        Button workOrder = new Button("Work Order");
        workOrder.setStyle("-fx-font: 18 arial; -fx-base: #eab231;");
        workOrder.setGraphic(workOrderButton);
        workOrder.setContentDisplay(ContentDisplay.RIGHT);
        workOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        workOrder.setMinHeight(100);
        workOrder.setPrefWidth(window.widthProperty().doubleValue() / 3);
        workOrder.setOnAction(e -> inputWorkOrder());

        /* Conditions for the Sales Receipt button and it's content! */
        ImageView salesReceiptButton = new ImageView(new Image(String.valueOf(salesReceiptButtonImagePath)));
        salesReceiptButton.setFitHeight(70);
        salesReceiptButton.setFitWidth(70);

        Button salesReceipt = new Button("Sales Receipt");
        salesReceipt.setStyle("-fx-font: 18 arial; -fx-base: #efefef;");
        salesReceipt.setGraphic(salesReceiptButton);
        salesReceipt.setContentDisplay(ContentDisplay.RIGHT);
        salesReceipt.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        salesReceipt.setMinHeight(100);
        salesReceipt.setPrefWidth(window.widthProperty().doubleValue() / 3);
        salesReceipt.setOnAction(e -> inputSalesReceipt());

        /* Conditions for MenuBar and it's content! */
        ImageView estimateOpenButtonImage = new ImageView(new Image(String.valueOf(estimateButtonImagePath)));
        estimateOpenButtonImage.setFitHeight(20);
        estimateOpenButtonImage.setFitWidth(20);

        ImageView invoiceOpenButtonImage = new ImageView(new Image(String.valueOf(invoiceButtonImagePath)));
        invoiceOpenButtonImage.setFitHeight(20);
        invoiceOpenButtonImage.setFitWidth(20);

        ImageView workOrderOpenButtonImage = new ImageView(new Image(String.valueOf(workOrderButtonImagePath)));
        workOrderOpenButtonImage.setFitHeight(20);
        workOrderOpenButtonImage.setFitWidth(20);

        ImageView salesReceiptOpenButtonImage = new ImageView(new Image(String.valueOf(salesReceiptButtonImagePath)));
        salesReceiptOpenButtonImage.setFitHeight(20);
        salesReceiptOpenButtonImage.setFitWidth(20);

        MenuBar menuBar = new MenuBar();
        menuBar.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        Menu menuOpen = new Menu("Open");
        MenuItem estimateArchive = new MenuItem("Estimate", estimateOpenButtonImage);
        MenuItem invoiceArchive = new MenuItem("Invoice", invoiceOpenButtonImage);
        MenuItem workOrderArchive = new MenuItem("Work Order", workOrderOpenButtonImage);
        MenuItem saleReceiptArchive = new MenuItem("Sales Receipt", salesReceiptOpenButtonImage);
        menuOpen.getItems().addAll(estimateArchive, invoiceArchive, workOrderArchive, saleReceiptArchive);

        Menu menuInfo = new Menu("Info");
        MenuItem devInfo = new MenuItem("Dev Info");
        MenuItem proTip = new MenuItem("Pro Tip");
        menuInfo.getItems().addAll(devInfo, proTip);

        devInfo.setOnAction(e -> displayDevInfo());

        proTip.setOnAction(e -> displayProTip());

        menuBar.getMenus().addAll(menuOpen, menuInfo);

        /* Setup for top of BorderPane! */
        VBox information = new VBox();
        information.getChildren().addAll(menuBar);

        /* Setup for left of BorderPane! */
        VBox input = new VBox();
        input.getChildren().addAll(logo, estimate, invoice, workOrder, salesReceipt);

        /* Setup for center of BorderPane! */
        contentPrompt = new Label("Select a form to complete");
        contentPrompt.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));

        content = new VBox();
        content.getChildren().add(contentPrompt);
        content.setAlignment(Pos.CENTER);

        /* Conditions for Main Scene and content locations! */
        BorderPane mainScreen = new BorderPane();
        mainScreen.setTop(information);
        mainScreen.setLeft(input);
        mainScreen.setCenter(content);

        //mainScreen.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));  /* Sets the background color of the primary Scene! */

        mainScene = new Scene(mainScreen, (ScreenBounds.getWidth() - (ScreenBounds.getWidth() / 4)), 700);

        /* Starting parameters for primaryStage! */
        window.setScene(mainScene);
        window.setTitle("Quest Input");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram = confirmProgramClose.display();

            if (closeProgram) {
                window.close();
                Platform.exit();
                System.exit(0);
            }

        });
        window.show();

        /* Lambda expression for a ChangeListener of the width of the primaryStage.
           This expression auto resizes the width of the buttons within the primaryStage, as well as the button's text font size! */
        window.widthProperty().addListener((observable, oldStageWidth, newStageWidth) -> {

            HBox.setHgrow(estimate, Priority.ALWAYS);
            HBox.setHgrow(invoice, Priority.ALWAYS);

            Double newWidth = (newStageWidth.doubleValue() / 3.0);

            estimate.setMaxWidth(newWidth);
            invoice.setMaxWidth(newWidth);
            workOrder.setMaxWidth(newWidth);
            salesReceipt.setMaxWidth(newWidth);

            fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(100));

            estimate.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";" ,"-fx-base: #3178ea;"));
            invoice.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";" , "-fx-base: #31eaa6;"));
            workOrder.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #eab231;"));
            salesReceipt.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #efefef;"));

        });
    }

    /* Method that creates all the elements of the estimate form and implements them within a ScrollPane to be added to the primaryStage! */
    private void estimateFromCreate() {

        estimateFormRunning = true;

        /* Setup for items VBox! */
        itemsVBox = new VBox(5);
        itemsVBox.setPadding(new Insets(4));

        /* Setup for the Left of the Border Pane! */
        Label dqProjectLabel = new Label("DQ Project #:");
        dqProjectText = new TextField();
        dqProjectText.setPrefWidth(100.0);

        Label itemQuantityLabel = new Label("Number of Items:");
        itemQuantityText = new TextField();
        itemQuantityText.setPrefWidth(50.0);

        /* Conditions for the item number enter button, within the action for the button, the appropriate number of element will be created and added to estimateScrollPane! */
        Button itemQuantityButton = new Button("Enter");
        itemQuantityButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        itemQuantityButton.setOnAction((ActionEvent e) -> {

            if (itemQuantityText.getText().isEmpty()) {

                messages.setText("Nothing was entered for the items Number");

            } else {

                checkNumberInput = functionCheck.checkNumberInput(itemQuantityText.getText());

                if (checkNumberInput) {

                    /* Variable for the items number entered, stores the number entered on the estimateWindow for items! */
                    itemNumber = Integer.parseInt(itemQuantityText.getText());

                    if (itemNumber <= 15 && itemNumber != 0) {

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

                    } else if (itemNumber == 0) {

                        itemQuantityText.setText(String.valueOf(savedItemNumber));

                        messages.setText("Items number cannot be 0");

                    } else {

                        messages.setText("Items number entered was greater than the limit of 15");

                    }

                } else {

                    saveItemData();
                    messages.setText("Number of items was enter wrong");
                }
            }

            if (itemNumber > 0 && itemNumber <= 15) {

                itemsVBox.getChildren().removeAll(item1HBox, item2HBox, item3HBox, item4HBox, item5HBox, item6HBox, item7HBox, item8HBox, item9HBox, item10HBox, item11HBox, item12HBox, item13HBox, item14HBox, item15HBox);
            }

            /* Generates all elements for each item! */
            if (itemNumber >= 1 && itemNumber <= 15) {

                Label item1Label = new Label("Item 1:");
                item1Label.setPrefWidth(60);
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
                item1HBox.getChildren().addAll(item1Label, item1Text, description1Label, description1Text, qty1Label, qty1Text, rate1Label, rate1Text);

                itemsVBox.getChildren().add(item1HBox);
            }

            if (itemNumber >= 2 && itemNumber <= 15) {

                Label item2Label = new Label("Item 2:");
                item2Label.setPrefWidth(60);
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
                item2HBox.getChildren().addAll(item2Label, item2Text, description2Label, description2Text, qty2Label, qty2Text, rate2Label, rate2Text);

                itemsVBox.getChildren().add(item2HBox);
            }

            if (itemNumber >= 3 && itemNumber <= 15) {

                Label item3Label = new Label("Item 3:");
                item3Label.setPrefWidth(60);
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
                item3HBox.getChildren().addAll(item3Label, item3Text, description3Label, description3Text, qty3Label, qty3Text, rate3Label, rate3Text);

                itemsVBox.getChildren().add(item3HBox);
            }

            if (itemNumber >= 4 && itemNumber <= 15) {

                Label item4Label = new Label("Item 4:");
                item4Label.setPrefWidth(60);
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
                item4HBox.getChildren().addAll(item4Label, item4Text, description4Label, description4Text, qty4Label, qty4Text, rate4Label, rate4Text);

                itemsVBox.getChildren().add(item4HBox);
            }

            if (itemNumber >= 5 && itemNumber <= 15) {

                Label item5Label = new Label("Item 5:");
                item5Label.setPrefWidth(60);
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
                item5HBox.getChildren().addAll(item5Label, item5Text, description5Label, description5Text, qty5Label, qty5Text, rate5Label, rate5Text);

                itemsVBox.getChildren().add(item5HBox);
            }

            if (itemNumber >= 6 && itemNumber <= 15) {

                Label item6Label = new Label("Item 6:");
                item6Label.setPrefWidth(60);
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
                item6HBox.getChildren().addAll(item6Label, item6Text, description6Label, description6Text, qty6Label, qty6Text, rate6Label, rate6Text);

                itemsVBox.getChildren().add(item6HBox);
            }

            if (itemNumber >= 7 && itemNumber <= 15) {

                Label item7Label = new Label("Item 7:");
                item7Label.setPrefWidth(60);
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
                item7HBox.getChildren().addAll(item7Label, item7Text, description7Label, description7Text, qty7Label, qty7Text, rate7Label, rate7Text);

                itemsVBox.getChildren().add(item7HBox);
            }

            if (itemNumber >= 8 && itemNumber <= 15) {

                Label item8Label = new Label("Item 8:");
                item8Label.setPrefWidth(60);
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
                item8HBox.getChildren().addAll(item8Label, item8Text, description8Label, description8Text, qty8Label, qty8Text, rate8Label, rate8Text);

                itemsVBox.getChildren().add(item8HBox);
            }

            if (itemNumber >= 9 && itemNumber <= 15) {

                Label item9Label = new Label("Item 9:");
                item9Label.setPrefWidth(60);
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
                item9HBox.getChildren().addAll(item9Label, item9Text, description9Label, description9Text, qty9Label, qty9Text, rate9Label, rate9Text);

                itemsVBox.getChildren().add(item9HBox);
            }

            if (itemNumber >= 10 && itemNumber <= 15) {

                Label item10Label = new Label("Item 10:");
                item10Label.setPrefWidth(60);
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
                item10HBox.getChildren().addAll(item10Label, item10Text, description10Label, description10Text, qty10Label, qty10Text, rate10Label, rate10Text);

                itemsVBox.getChildren().add(item10HBox);
            }

            if (itemNumber >= 11 && itemNumber <= 15) {

                Label item11Label = new Label("Item 11:");
                item11Label.setPrefWidth(60);
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
                item11HBox.getChildren().addAll(item11Label, item11Text, description11Label, description11Text, qty11Label, qty11Text, rate11Label, rate11Text);

                itemsVBox.getChildren().add(item11HBox);
            }

            if (itemNumber >= 12 && itemNumber <= 15) {

                Label item12Label = new Label("Item 12:");
                item12Label.setPrefWidth(60);
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
                item12HBox.getChildren().addAll(item12Label, item12Text, description12Label, description12Text, qty12Label, qty12Text, rate12Label, rate12Text);

                itemsVBox.getChildren().add(item12HBox);
            }

            if (itemNumber >= 13 && itemNumber <= 15) {

                Label item13Label = new Label("Item 13:");
                item13Label.setPrefWidth(60);
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
                item13HBox.getChildren().addAll(item13Label, item13Text, description13Label, description13Text, qty13Label, qty13Text, rate13Label, rate13Text);

                itemsVBox.getChildren().add(item13HBox);
            }

            if (itemNumber >= 14 && itemNumber <= 15) {

                Label item14Label = new Label("Item 14:");
                item14Label.setPrefWidth(60);
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
                item14HBox.getChildren().addAll(item14Label, item14Text, description14Label, description14Text, qty14Label, qty14Text, rate14Label, rate14Text);

                itemsVBox.getChildren().add(item14HBox);
            }

            if (itemNumber == 15) {

                Label item15Label = new Label("Item 15:");
                item15Label.setPrefWidth(60);
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
                item15HBox.getChildren().addAll(item15Label, item15Text, description15Label, description15Text, qty15Label, qty15Text, rate15Label, rate15Text);

                itemsVBox.getChildren().add(item15HBox);
            }

            if (itemsGenerated) {

                enterItemsData();
            }

            itemsGenerated = true;

        });

        HBox dqProjectHBox = new HBox(8);
        dqProjectHBox.setPadding(new Insets(5));
        dqProjectHBox.getChildren().addAll(dqProjectLabel, dqProjectText, itemQuantityLabel, itemQuantityText, itemQuantityButton);
        dqProjectHBox.setAlignment(Pos.TOP_LEFT);

        Label dateLabel = new Label("Date:");
        dateText = new DatePicker();
        dateText.setPrefWidth(130.0);

        HBox dateHBox = new HBox(8);
        dateHBox.setPadding(new Insets(5));
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
        invoiceHBox.setPadding(new Insets(5));
        invoiceHBox.getChildren().addAll(invoiceLabel, invoiceText);
        invoiceHBox.setAlignment(Pos.TOP_LEFT);

        Label nameAddressLabel = new Label("Name / Address:");
        nameAddressText = new TextField();
        nameAddressText.setPrefWidth(350.0);

        HBox nameAddressHBox = new HBox(4);
        nameAddressHBox.setPadding(new Insets(5));
        nameAddressHBox.getChildren().addAll(nameAddressLabel, nameAddressText);
        nameAddressHBox.setAlignment(Pos.TOP_LEFT);

        /* Setup for error message label! */
        messages = new Label();
        messages.setTextFill(Color.web("#f73636"));
        messages.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        messages.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!Objects.equals(newValue, "")) {

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> messages.setText(""));
                    }
                },5000);

            }
        });

        messages.setOnMouseEntered(event -> {
            messages.setScaleX(1.20);
            messages.setScaleY(1.20);
        });

        messages.setOnMouseExited(event -> {
            messages.setScaleX(1.00);
            messages.setScaleY(1.00);
        });

        /* Conditions for the Close button to close the Estimate window! */
        Button closeButton = new Button("Cancel");
        closeButton.setStyle("-fx-font: 13 arial; -fx-base: #C0C0C0;");
        closeButton.setOnAction((ActionEvent e) -> {

            double centerXPosition = window.getX();
            double centerYPosition = window.getY();
            double primaryStageWidth = window.getWidth();
            double primaryStageHeight = window.getHeight();

            boolean cancelForm = confirmProgramClose.cancelAcknowledged("Estimate Form", centerXPosition, centerYPosition, primaryStageWidth, primaryStageHeight);

            if (cancelForm) {

                itemNumber = 0;
                itemsGenerated = false;
                deleteItemData(true);
                content.getChildren().removeAll(estimateScrollPane);
                content.getChildren().add(contentPrompt);
                content.setAlignment(Pos.CENTER);

                estimateFormRunning = false;
            }

        });

        /* Conditions for the Save button to save the entered information for an estimate! */
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
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

                    content.getChildren().removeAll(estimateScrollPane);
                    content.getChildren().add(contentPrompt);
                    content.setAlignment(Pos.CENTER);

                    estimateFormRunning = false;
                }
            }
        });

        /* Conditions for the Calculate button to calculate the total cost of each item! */
        Button calCost = new Button("Calculate");
        calCost.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        calCost.setOnAction((ActionEvent e) -> {

            checkDataInput = checkDataInput();

            calculate(checkDataInput);
        });

        /* Setup of Label to display the total cost of each item for an estimate! */
        totalCost = new Label("$00.00");
        totalCost.setFont(Font.font("Arial", 16));

        Label total = new Label("Cost Total:");
        total.setFont(Font.font("Arial", 16));

        /* Setup for the top portion of the estimateLayout VBox! */
        dataInput = new VBox(4);
        dataInput.setPadding(new Insets(4));
        dataInput.getChildren().addAll(dqProjectHBox, dateHBox, invoiceHBox, nameAddressHBox, itemsVBox);

        /* Setup for the middle portion of the estimateLayout VBox that displays error messages! */
        errorMessages = new VBox(8);
        errorMessages.setPadding(new Insets(4));
        errorMessages.setAlignment(Pos.BOTTOM_CENTER);
        errorMessages.getChildren().add(messages);
        errorMessages.setStyle("-fx-font: 14 arial; -fx-base: #f73636;");

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
        bottom = new HBox(8);
        bottom.getChildren().addAll(bottomLeft, bottomRight);

        /* Setup for the estimateLayout VBox and estimateScrollPane ScrollPane! */
        estimateLayout = new VBox(0);

        estimateLayout.getChildren().addAll(dataInput, errorMessages, bottom);

        estimateScrollPane = new ScrollPane();
        estimateScrollPane.setContent(estimateLayout);

        estimateScrollPane.setPrefHeight(window.heightProperty().doubleValue());


    }

    /* Method to check that data has been entered in each TextField of the estimate form. If data hasn't been entered then an error message will be displayed! */
    private boolean checkDataInput() {

        checkDataInput = false;

        if (itemQuantityText.getText().isEmpty()) {

            messages.setText("Nothing was entered for Items Number");

        } else if (itemNumber == 0) {

            messages.setText("No item number was ever entered");

        } else if (dateText.getValue() == null) {

            messages.setText("Date entered Incorrectly or not at all");

        } else if (invoiceText.getText().isEmpty()) {

            messages.setText("Nothing was entered for Invoice Number");

        } else if (nameAddressText.getText().isEmpty()) {

            messages.setText("Nothing was entered for Name or Address");

        } else if ((itemNumber >= 1 && itemNumber <= 15) && (item1Text.getText().isEmpty() || description1Text.getText().isEmpty() || qty1Text.getText().isEmpty() || rate1Text.getText().isEmpty())) {

            if (item1Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 1");

            } else if (description1Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 1");

            } else if (qty1Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 1");

            } else if (rate1Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 1");
            }

        } else if ((itemNumber >= 2 && itemNumber <= 15) && (item2Text.getText().isEmpty() || description2Text.getText().isEmpty() || qty2Text.getText().isEmpty() || rate2Text.getText().isEmpty())) {

            if (item2Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 2");

            } else if (description2Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 2");

            } else if (qty2Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 2");

            } else if (rate2Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 2");
            }

        } else if ((itemNumber >= 3 && itemNumber <= 15) && (item3Text.getText().isEmpty() || description3Text.getText().isEmpty() || qty3Text.getText().isEmpty() || rate3Text.getText().isEmpty())) {

            if (item3Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 3");

            } else if (description3Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 3");

            } else if (qty3Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 3");

            } else if (rate3Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 3");
            }

        } else if ((itemNumber >= 4 && itemNumber <= 15) && (item4Text.getText().isEmpty() || description4Text.getText().isEmpty() || qty4Text.getText().isEmpty() || rate4Text.getText().isEmpty())) {

            if (item4Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 4");

            } else if (description4Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 4");

            } else if (qty4Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 4");

            } else if (rate4Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 4");
            }

        } else if ((itemNumber >= 5 && itemNumber <= 15) && (item5Text.getText().isEmpty() || description5Text.getText().isEmpty() || qty5Text.getText().isEmpty() || rate5Text.getText().isEmpty())) {

            if (item5Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 5");

            } else if (description5Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 5");

            } else if (qty5Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 5");

            } else if (rate5Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 5");
            }

        } else if ((itemNumber >= 6 && itemNumber <= 15) && (item6Text.getText().isEmpty() || description6Text.getText().isEmpty() || qty6Text.getText().isEmpty() || rate6Text.getText().isEmpty())) {

            if (item6Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 6");

            } else if (description6Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 6");

            } else if (qty6Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 6");

            } else if (rate6Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 6");
            }

        } else if ((itemNumber >= 7 && itemNumber <= 15) && (item7Text.getText().isEmpty() || description7Text.getText().isEmpty() || qty7Text.getText().isEmpty() || rate7Text.getText().isEmpty())) {

            if (item7Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 7");

            } else if (description7Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 7");

            } else if (qty7Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 7");

            } else if (rate7Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 7");
            }

        } else if ((itemNumber >= 8 && itemNumber <= 15) && (item8Text.getText().isEmpty() || description8Text.getText().isEmpty() || qty8Text.getText().isEmpty() || rate8Text.getText().isEmpty())) {

            if (item8Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 8");

            } else if (description8Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 8");

            } else if (qty8Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 8");

            } else if (rate8Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 8");
            }

        } else if ((itemNumber >= 9 && itemNumber <= 15) && (item9Text.getText().isEmpty() || description9Text.getText().isEmpty() || qty9Text.getText().isEmpty() || rate9Text.getText().isEmpty())) {

            if (item9Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 9");

            } else if (description9Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 9");

            } else if (qty9Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 9");

            } else if (rate9Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 9");
            }

        } else if ((itemNumber >= 10 && itemNumber <= 15) && (item10Text.getText().isEmpty() || description10Text.getText().isEmpty() || qty10Text.getText().isEmpty() || rate10Text.getText().isEmpty())) {

            if (item10Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 10");

            } else if (description10Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 10");

            } else if (qty10Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 10");

            } else if (rate10Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 10");
            }

        } else if ((itemNumber >= 11 && itemNumber <= 15) && (item11Text.getText().isEmpty() || description11Text.getText().isEmpty() || qty11Text.getText().isEmpty() || rate11Text.getText().isEmpty())) {

            if (item11Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 11");

            } else if (description11Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 11");

            } else if (qty11Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 11");

            } else if (rate11Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 11");
            }

        } else if ((itemNumber >= 12 && itemNumber <= 15) && (item12Text.getText().isEmpty() || description12Text.getText().isEmpty() || qty12Text.getText().isEmpty() || rate12Text.getText().isEmpty())) {

            if (item12Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 12");

            } else if (description12Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 12");

            } else if (qty12Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 12");

            } else if (rate12Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 12");
            }

        } else if ((itemNumber >= 13 && itemNumber <= 15) && (item13Text.getText().isEmpty() || description13Text.getText().isEmpty() || qty13Text.getText().isEmpty() || rate13Text.getText().isEmpty())) {

            if (item13Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 13");

            } else if (description13Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 13");

            } else if (qty13Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 13");

            } else if (rate13Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 13");
            }

        } else if ((itemNumber >= 14 && itemNumber <= 15) && (item14Text.getText().isEmpty() || description14Text.getText().isEmpty() || qty14Text.getText().isEmpty() || rate14Text.getText().isEmpty())) {

            if (item14Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 14");

            } else if (description14Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 14");

            } else if (qty14Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 14");

            } else if (rate14Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 14");
            }

        } else if (itemNumber == 15 && (item15Text.getText().isEmpty() || description15Text.getText().isEmpty() || qty15Text.getText().isEmpty() || rate15Text.getText().isEmpty())) {

            if (item15Text.getText().isEmpty()) {

                messages.setText("Nothing was entered for Item 15");

            } else if (description15Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for Description of Item 15");

            } else if (qty15Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Quantity of Item 15");

            } else if (rate15Text.getText().isEmpty()) {

                messages.setText("Nothing was enter for the Rate of Item 15");
            }

        } else {

            checkDataInput = true;
        }

        if (!dqProjectText.getText().isEmpty() && checkDataInput) {

            checkNumberInput = functionCheck.checkNumberInput(dqProjectText.getText());

            if (!checkNumberInput) {

                messages.setText("Number for DQ Project Number was entered wrong");

                checkDataInput = false;
            }
        }

        if (!invoiceText.getText().isEmpty() && checkDataInput) {

            checkNumberInput = functionCheck.invoiceFormat(invoiceText.getText());

            if (!checkNumberInput) {

                messages.setText("Number for Invoice Number was entered wrong");

                checkDataInput = false;
            }
        }

        return checkDataInput;
    }

    /* Method to calculate to total cost of each item if the estimate form! */
    @SuppressWarnings("ConstantConditions")
    private boolean calculate(boolean check) {

        calculationCheck = true;

        totalCost.setText("$00.00");

        if (check) {

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
                        messages.setText("Number was formatted incorrectly for item 1 Quantity");
                    }

                } else {

                    if (!qty1Text.getText().isEmpty()) {

                        qty1 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 1 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 1 Rate");
                    }

                } else {

                    if (!rate1Text.getText().isEmpty()) {

                        rate1 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 1 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 2 Quantity");
                    }

                } else {

                    if (!qty2Text.getText().isEmpty()) {

                        qty2 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 2 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 2 Rate");
                    }

                } else {

                    if (!rate2Text.getText().isEmpty()) {

                        rate2 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 2 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 3 Quantity");
                    }

                } else {

                    if (!qty3Text.getText().isEmpty()) {

                        qty3 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 3 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 3 Rate");
                    }

                } else {

                    if (!rate3Text.getText().isEmpty()) {

                        rate3 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 3 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 4 Quantity");
                    }

                } else {

                    if (!qty4Text.getText().isEmpty()) {

                        qty4 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 4 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 4 Rate");
                    }

                } else {

                    if (!rate4Text.getText().isEmpty()) {

                        rate4 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 4 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 5 Quantity");
                    }

                } else {

                    if (!qty5Text.getText().isEmpty()) {

                        qty5 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 5 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 5 Rate");
                    }

                } else {

                    if (!rate5Text.getText().isEmpty()) {

                        rate5 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 5 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 6 Quantity");
                    }

                } else {

                    if (!qty6Text.getText().isEmpty()) {

                        qty6 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 6 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 6 Rate");
                    }

                } else {

                    if (!rate6Text.getText().isEmpty()) {

                        rate6 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 6 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 7 Quantity");
                    }

                } else {

                    if (!qty7Text.getText().isEmpty()) {

                        qty7 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 7 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 7 Rate");
                    }

                } else {

                    if (!rate7Text.getText().isEmpty()) {

                        rate7 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 7 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 8 Quantity");
                    }

                } else {

                    if (!qty8Text.getText().isEmpty()) {

                        qty8 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 8 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 8 Rate");
                    }

                } else {

                    if (!rate8Text.getText().isEmpty()) {

                        rate8 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 8 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 9 Quantity");
                    }

                } else {

                    if (!qty9Text.getText().isEmpty()) {

                        qty9 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 9 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 9 Rate");
                    }

                } else {

                    if (!rate9Text.getText().isEmpty()) {

                        rate9 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 9 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 10 Quantity");
                    }

                } else {

                    if (!qty10Text.getText().isEmpty()) {

                        qty10 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 10 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 10 Rate");
                    }

                } else {

                    if (!rate10Text.getText().isEmpty()) {

                        rate10 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 10 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 11 Quantity");
                    }

                } else {

                    if (!qty11Text.getText().isEmpty()) {

                        qty11 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 11 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 11 Rate");
                    }

                } else {

                    if (!rate11Text.getText().isEmpty()) {

                        rate11 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 11 Rate");
                    }
                }

                if (qty11 != null && rate11 != null) {

                    costTotalDouble = (costTotalDouble + (qty11 * rate11));
                }
            }

            if ((itemNumber >= 12 && itemNumber <= 15) && (!qty12Text.getText().isEmpty() || !rate12Text.getText().isEmpty())) {

                checkCurrencyInput = functionCheck.checkCurrencyInput(qty12Text.getText());

                if (checkCurrencyInput) {

                    checkCurrencyInput = functionCheck.checkNumberFormat(qty12Text.getText());

                    if (checkCurrencyInput) {

                        qty12 = Double.parseDouble(functionCheck.removeComas(qty12Text.getText()));

                    } else {

                        qty12 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was formatted incorrectly for item 12 Quantity");
                    }

                } else {

                    if (!qty12Text.getText().isEmpty()) {

                        qty12 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 12 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 12 Rate");
                    }

                } else {

                    if (!rate12Text.getText().isEmpty()) {

                        rate12 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 12 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 13 Quantity");
                    }

                } else {

                    if (!qty13Text.getText().isEmpty()) {

                        qty13 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 13 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 13 Rate");
                    }

                } else {

                    if (!rate13Text.getText().isEmpty()) {

                        rate13 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 13 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 14 Quantity");
                    }

                } else {

                    if (!qty14Text.getText().isEmpty()) {

                        qty14 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 14 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 14 Rate");
                    }

                } else {

                    if (!rate14Text.getText().isEmpty()) {

                        rate14 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 14 Rate");
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
                        messages.setText("Number was formatted incorrectly for item 15 Quantity");
                    }

                } else {

                    if (!qty15Text.getText().isEmpty()) {

                        qty15 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 15 Quantity");
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
                        messages.setText("Number was formatted incorrectly for item 15 Rate");
                    }

                } else {

                    if (!rate15Text.getText().isEmpty()) {

                        rate15 = 0.00;
                        calculationCheck = false;
                        messages.setText("Number was entered wrong for item 15 Rate");
                    }
                }

                if (qty15 != null && rate15 != null) {

                    costTotalDouble = (costTotalDouble + (qty15 * rate15));
                }
            }

            if (calculationCheck) {

                costTotalString = functionCheck.addComas(df.format(costTotalDouble));

                totalCost.setText("$" + costTotalString);
            }
        }

        return calculationCheck;
    }

    /* Method to store entered information within the estimate form! */
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
            itemsData[63] = rate15Text.getText();
        }

    }

    /* Method to set the data of each element within the estimate form! */
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

    /* Method to delete the needed information out of the array that stores entered information within the estimate form.
     * Method will either delete a certain set of information or delete all information! */
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

    /* Method that initializes the array for storing entered information within the estimate form! */
    private void itemsDataInit() {

        for (int x = 0; x < 64; x++) {

            itemsData[x] = "";

        }
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

    public void inputInvoice() {

    }

    public void inputWorkOrder() {

    }

    public void inputSalesReceipt() {


    }

    public static void main(String[] args) {
        launch(args);
    }

    }

