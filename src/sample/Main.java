package sample;

/*
 * Created by Matthew Ashley on 12/15/16.
 */

import VTFXcontrols.*;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import org.sqlite.SQLiteConfig;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.awt.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.function.Predicate;
import java.nio.file.Path;

@SuppressWarnings("ConstantConditions")
public class Main extends Application {

    private Stage window; /* Is the primary Stage for the program! */

    private ScrollPane estimateScrollPane; /* ScrollPane for the Estimate form! */
    private ScrollPane travelerScrollPane; /* ScrollPane for the Traveler form! */
    private ScrollPane packingListScrollPane; /* ScrollPane for the Packing List form! */
    private ScrollPane settingsScrollPane; /* ScrollPane for the settings page! */

    /* HBoxes used within each form! */
    private HBox estimateItem1HBox, estimateItem2HBox, estimateItem3HBox, estimateItem4HBox, estimateItem5HBox, estimateItem6HBox, estimateItem7HBox,
            estimateItem8HBox, estimateItem9HBox, estimateItem10HBox, estimateItem11HBox, estimateItem12HBox, estimateItem13HBox, estimateItem14HBox, estimateItem15HBox,
            travelerProcess1HBox, travelerProcess2HBox, travelerProcess3HBox, travelerProcess4HBox, travelerProcess5HBox, travelerProcess6HBox, travelerProcess7HBox, travelerProcess8HBox, travelerProcess9HBox,
            packingListItem1HBox, packingListItem2HBox, packingListItem3HBox, packingListItem4HBox, packingListItem5HBox, packingListItem6HBox, packingListItem7HBox, packingListItem8HBox,
            packingListItem9HBox, packingListItem10HBox, packingListItem11HBox, packingListItem12HBox, packingListItem13HBox, packingListItem14HBox, packingListItem15HBox, packingListItem16HBox,
            packingListItem17HBox, packingListItem18HBox, packingListItem19HBox, packingListItem20HBox, packingListItem21HBox, packingListItem22HBox, packingListItem23HBox, packingListItem24HBox;

    /* VBoxes used within each form! */
    private VBox estimateItemsVBox, travelerProcessesVBox, packingListItemsVBox, content;

    private TextField item1Text, item2Text, item3Text, item4Text, item5Text, item6Text, item7Text, item8Text, item9Text, item10Text, item11Text, item12Text, item13Text, item14Text, item15Text,
            description1Text, description2Text, description3Text, description4Text, description5Text, description6Text, description7Text, description8Text, description9Text, description10Text, description11Text, description12Text, description13Text, description14Text, description15Text,
            qty1Text, qty2Text, qty3Text, qty4Text, qty5Text, qty6Text, qty7Text, qty8Text, qty9Text, qty10Text, qty11Text, qty12Text, qty13Text, qty14Text, qty15Text,
            rate1Text, rate2Text, rate3Text, rate4Text, rate5Text, rate6Text, rate7Text, rate8Text, rate9Text, rate10Text, rate11Text, rate12Text, rate13Text, rate14Text, rate15Text,
            invoiceText, companyNameText, addressText, itemQuantityText, dqProjectText, jobNumberText, customerText, quantityText, partNumberText, poNumberText, processesNumberText,
            process1DescriptionText, process2DescriptionText, process3DescriptionText, process4DescriptionText, process5DescriptionText, process6DescriptionText, process7DescriptionText, process8DescriptionText, process9DescriptionText,
            process1PlannedHoursText, process2PlannedHoursText, process3PlannedHoursText, process4PlannedHoursText, process5PlannedHoursText, process6PlannedHoursText, process7PlannedHoursText, process8PlannedHoursText, process9PlannedHoursText,
            process1ActualHoursText, process2ActualHoursText, process3ActualHoursText, process4ActualHoursText, process5ActualHoursText, process6ActualHoursText, process7ActualHoursText, process8ActualHoursText, process9ActualHoursText,
            process1CompletedText, process2CompletedText, process3CompletedText, process4CompletedText, process5CompletedText, process6CompletedText, process7CompletedText, process8CompletedText, process9CompletedText,
            orderNumberPackingList, jobPackingList, itemsNumberPackingList,
            item1NumberText, item2NumberText, item3NumberText, item4NumberText, item5NumberText, item6NumberText, item7NumberText, item8NumberText, item9NumberText, item10NumberText, item11NumberText, item12NumberText, item13NumberText, item14NumberText,
            item15NumberText, item16NumberText, item17NumberText, item18NumberText, item19NumberText, item20NumberText, item21NumberText, item22NumberText, item23NumberText, item24NumberText,
            item1DescriptionText, item2DescriptionText, item3DescriptionText, item4DescriptionText, item5DescriptionText, item6DescriptionText, item7DescriptionText, item8DescriptionText, item9DescriptionText, item10DescriptionText, item11DescriptionText,
            item12DescriptionText, item13DescriptionText, item14DescriptionText, item15DescriptionText, item16DescriptionText, item17DescriptionText, item18DescriptionText, item19DescriptionText, item20DescriptionText, item21DescriptionText, item22DescriptionText, item23DescriptionText, item24DescriptionText,
            item1QuantityText, item2QuantityText, item3QuantityText, item4QuantityText, item5QuantityText, item6QuantityText, item7QuantityText, item8QuantityText, item9QuantityText, item10QuantityText, item11QuantityText, item12QuantityText, item13QuantityText,
            item14QuantityText, item15QuantityText, item16QuantityText, item17QuantityText, item18QuantityText, item19QuantityText, item20QuantityText, item21QuantityText, item22QuantityText, item23QuantityText, item24QuantityText;

    /* TextAreas used within the Traveler and PackingList forms! */
    private TextArea notesTextAreaTraveler, shipToPackingList, billToPackingList;

    private DatePicker dateText; /* Setup for the Date Picker being used in the Estimate window! */
    private DatePicker dateReceivedPicker; /* Setup for the Date Picker used for the date received in the Traveler window! */
    private DatePicker dateRequiredPicker; /* Setup for the Date Picker used for the date required in the Traveler window! */
    private DatePicker completedDatePicker; /* Setup for the Date Picker used for the date completed in the Traveler window! */
    private DatePicker orderDatePackingList; /* Setup for the Date Picker used for the order date in the Packing List window! */

    private Label totalCost; /* Label used for displaying the total cost of each item in the Estimate form! */
    private Label estimateMessages; /* Label used for displaying error estimateMessages! */
    private Label travelerMessages; /* Label used for displaying error travelerMessages! */
    private Label packingListMessages; /* Label used for displaying error packingListMessages! */
    private Label settingsMessages; /* Label used for displaying messages on the Settings window! */
    private Label contentPrompt; /* Label used for displaying a prompt message for selecting a form to complete! */
    private Label plannedHoursTotalPartTime; /* Label used for displaying the total hours for one part in the Traveler form! */
    private Label plannedHoursTotalTotalTime; /* Label used for displaying the total hours for all parts in the Traveler form! */
    private Label estimateLocationFilePath; /* Label used for displaying the default save location for the Estimate form! */
    private Label travelerLocationFilePath; /* Label used for displaying the default save location for the Traveler form! */
    private Label packingListLocationFilePath; /* Label used for displaying the default save location for the Packing List form! */

    private String costTotalString;
    private String datePattern = "MM-dd-yyyy"; /* Sets the date pattern for the StringConverter of each DatePicker! */

    private final String[] estimateData = new String[65]; /* Array for storing user input for items data on the Estimate form! */
    private final String[] travelerData = new String[46]; /* Array for storing user input for process data on the Traveler form! */
    private final String[] packingListData = new String[78]; /* Array for storing user input for items data on the Packing List form! */
    private final String[] correctedFilePaths = new String[3]; /* Array for storing the default directory locations for saving each file! */

    private final List<String> autoCompleteList = new ArrayList<>(); /* List for storing suggested inputs for TextFields! */

    /* Double variables used to calculate the total cost in the Estimate form! */
    private Double qty1, qty2, qty3, qty4, qty5, qty6, qty7, qty8, qty9, qty10, qty11, qty12, qty13, qty14, qty15,
            rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8, rate9, rate10, rate11, rate12, rate13, rate14, rate15;

    private int estimateItemNumber; /* Variable for the items number entered, stores the number entered on the EstimateWindow for items! */
    private int estimateSavedItemNumber; /* Variable for the last item number entered, stores the last number entered on the EstimateWindow for items! */
    private int travelerProcessNumber; /* Variable for the number of processes entered, stores the number entered on the TravelerWindow for processes! */
    private int travelerSavedProcessNumber; /* Variable for the last item number entered, stores the last number entered on the TravelerWindow for processes! */
    private int packingListItemNumber; /* Variable for the items number entered, stores the number entered on the PackingListWindow for items! */
    private int packingListSavedItemNumber; /* Variable for the last item number entered, stores the last number entered on the PackingListWindow for items! */

    private boolean checkEstimateDataInput; /* Variable for when data has been entered in every TextField, used to store true or false for checking
                               that data has been entered in every TextField in the checkEstimateDataInput method! */
    private boolean checkTravelerDataInput; /* Variable for when data has been entered in every TextField, used to store true or false for checking
                               that data has been entered in every TextField in the checkTravelerDataInput method! */
    private boolean checkPackingListDataInput; /* Variable for when data has been entered in every TextField, used to store true or false for checking
                               that data has been entered in every TextField in the checkPackingListDataInput method! */
    private boolean checkCurrencyFormat; /* Variable for checking number inputs in currency format, used to store true or false whether or not a number in currency format has been entered correctly by the user! */
    private boolean checkNumberFormat; /* Variable for checking number inputs, used to store true or false whether or not a number has been entered correctly by the user! */
    private boolean estimateItemsGenerated; /* Variable for checking if items elements have already been created, will store true if items elements have been created in the Estimate form! */
    private boolean travelerProcessesGenerated; /* Variable for checking if process elements have already been created, will store true if process elements have been created in the Traveler form! */
    private boolean packingListItemsGenerated; /* Variable for checking if item elements have already been created, will store true if item elements have been created in the Packing List form! */
    private boolean estimateCalculationReturn; /* Variable for storing if all calculations passed in the Estimate form! */
    private boolean travelerCalculationReturn; /* Variable for storing if all calculations passed in the Traveler form! */
    private boolean closeProgram; /* Variable for checking if the program is going to be closed! */
    private boolean estimateFormRunning; /* Variable for storing if an Estimate form is being filled! */
    private boolean travelerFormRunning; /* Variable for storing if the Traveler form is being filled! */
    private boolean packingListFormRunning; /* Variable for storing if the Packing List form is being filled! */
    private boolean settingsPageFilling; /* Variable for storing if the settings page is open or not! */
    private boolean autoCompletionState; /* Variable for storing if Auto Completion should be done for TextFields! */

    private final Functions functions = new Functions(); /* Initializer for the VTFXcontrols Function class! */
    private final CreatedFunctions create = new CreatedFunctions(); /* Initializer for CreatedFunctions class! */
    private final ExcelEditing inputWorkbookData = new ExcelEditing(); /* Initializer for ExcelEditing class! */
    private final ConfirmMessages confirmProgramClose = new ConfirmMessages(); /* Initializer for ConfirmMessages class! */
    private final DecimalFormat df = new DecimalFormat("0.00"); /* Initializer for a DecimalFormat instance, will format a Double variable to always show two decimal places! */
    private final Timer timer = new Timer(); /* Initializer for a timer instance! */
    private final DoubleProperty fontSize = new SimpleDoubleProperty(10); /* Initializer for a DoubleProperty variable, used to bind the font size of buttons relative to the main Stage width! */
    private final DoubleProperty imageSize = new SimpleDoubleProperty(10); /* Initializer for a DoubleProperty variable, used to bind the image size of buttons relative to the main Stage height! */

    private Connection connection = null; /* Connection variable used to store the local database connection information! */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        window = primaryStage;

        /* Variable used to get the bounds of the device screen! */
        Rectangle2D ScreenBounds = Screen.getPrimary().getVisualBounds();

        /* Setup for each image used, will get path of image were it is stored on the users device! */
        URL logoImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/image001.png");
        URL estimateButtonImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/Estimate.png");
        URL travelerButtonImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/Traveler.png");
        URL packingListButtonImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/PackingList.png");
        URL salesReceiptButtonImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/SalesReceipt.png");

        /* Setup for Design Quest Image on window Stage! */
        ImageView logo = new ImageView(new Image(String.valueOf(logoImagePath)));
        logo.fitWidthProperty().bind(window.widthProperty().divide(3));
        logo.fitHeightProperty().bind(window.heightProperty().divide(6));

        /* Conditions for the Estimate button and it's content! */
        ImageView estimateButtonImage = new ImageView(new Image(String.valueOf(estimateButtonImagePath)));
        estimateButtonImage.setFitHeight(70);
        estimateButtonImage.setFitWidth(70);

        Button estimate = new Button("Estimate");
        estimate.setStyle("-fx-font: 18 arial; -fx-base: #3178ea;");
        estimate.setGraphic(estimateButtonImage);
        estimate.setContentDisplay(ContentDisplay.RIGHT);
        estimate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        estimate.setPrefHeight((ScreenBounds.getHeight() - (ScreenBounds.getHeight() / 6)) / 6);
        estimate.setPrefWidth(window.widthProperty().doubleValue() / 3);
        estimate.setOnAction(e -> {

            if (!estimateFormRunning) {

                itemsDataInit();
                estimateFormCreate();

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, travelerScrollPane, packingListScrollPane);
                content.getChildren().add(estimateScrollPane);
                content.setAlignment(Pos.TOP_LEFT);

            } else {

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, travelerScrollPane, estimateScrollPane, packingListScrollPane);
                content.getChildren().add(estimateScrollPane);
                content.setAlignment(Pos.TOP_LEFT);
            }
        });

        /* Conditions for the Invoice button and it's content! */
        ImageView travelerButton = new ImageView(new Image(String.valueOf(travelerButtonImagePath)));
        travelerButton.setFitHeight(70);
        travelerButton.setFitWidth(70);

        Button traveler = new Button("Traveler");
        traveler.setStyle("-fx-font: 18 arial; -fx-base: #31eaa6;");
        traveler.setGraphic(travelerButton);
        traveler.setContentDisplay(ContentDisplay.RIGHT);
        traveler.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        traveler.setPrefHeight((ScreenBounds.getHeight() - (ScreenBounds.getHeight() / 6)) / 6);
        traveler.setPrefWidth(window.widthProperty().doubleValue() / 3);
        traveler.setOnAction(e -> {

            if (!travelerFormRunning) {

                travelerDataInit();
                travelerFormCreate();

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, estimateScrollPane, packingListScrollPane);
                content.getChildren().add(travelerScrollPane);
                content.setAlignment(Pos.TOP_LEFT);

            } else {

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, estimateScrollPane, travelerScrollPane, packingListScrollPane);
                content.getChildren().add(travelerScrollPane);
                content.setAlignment(Pos.TOP_LEFT);
            }
        });

        /* Conditions for the Packing List button and it's content! */
        ImageView packingListButton = new ImageView(new Image(String.valueOf(packingListButtonImagePath)));
        packingListButton.setFitHeight(70);
        packingListButton.setFitWidth(70);

        Button packingList = new Button("Packing List");
        packingList.setStyle("-fx-font: 18 arial; -fx-base: #eab231;");
        packingList.setGraphic(packingListButton);
        packingList.setContentDisplay(ContentDisplay.RIGHT);
        packingList.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        packingList.setPrefHeight((ScreenBounds.getHeight() - (ScreenBounds.getHeight() / 6)) / 6);
        packingList.setPrefWidth(window.widthProperty().doubleValue() / 3);
        packingList.setOnAction(e -> {

            if (!packingListFormRunning) {

                packingListDataInit();
                packingListFormCreate();

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, estimateScrollPane, travelerScrollPane);
                content.getChildren().add(packingListScrollPane);
                content.setAlignment(Pos.TOP_LEFT);

            } else {

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, estimateScrollPane, travelerScrollPane, packingListScrollPane);
                content.getChildren().add(packingListScrollPane);
                content.setAlignment(Pos.TOP_LEFT);
            }
        });

        /* Conditions for the Sales Receipt button and it's content! */
        ImageView salesReceiptButton = new ImageView(new Image(String.valueOf(salesReceiptButtonImagePath)));
        salesReceiptButton.setFitHeight(70);
        salesReceiptButton.setFitWidth(70);

        Button salesReceipt = new Button("Sales Receipt");
        salesReceipt.setStyle("-fx-font: 18 arial; -fx-base: #efefef;");
        salesReceipt.setGraphic(salesReceiptButton);
        salesReceipt.setContentDisplay(ContentDisplay.RIGHT);
        salesReceipt.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        salesReceipt.setPrefHeight((ScreenBounds.getHeight() - (ScreenBounds.getHeight() / 6)) / 6);
        salesReceipt.setPrefWidth(window.widthProperty().doubleValue() / 3);
        salesReceipt.setOnAction(e -> inputSalesReceipt());

        /* Conditions for MenuBar and it's content! */
        ImageView estimateOpenButtonImage = new ImageView(new Image(String.valueOf(estimateButtonImagePath)));
        estimateOpenButtonImage.setFitHeight(20);
        estimateOpenButtonImage.setFitWidth(20);

        ImageView travelerOpenButtonImage = new ImageView(new Image(String.valueOf(travelerButtonImagePath)));
        travelerOpenButtonImage.setFitHeight(20);
        travelerOpenButtonImage.setFitWidth(20);

        ImageView packingListOpenButtonImage = new ImageView(new Image(String.valueOf(packingListButtonImagePath)));
        packingListOpenButtonImage.setFitHeight(20);
        packingListOpenButtonImage.setFitWidth(20);

        ImageView salesReceiptOpenButtonImage = new ImageView(new Image(String.valueOf(salesReceiptButtonImagePath)));
        salesReceiptOpenButtonImage.setFitHeight(20);
        salesReceiptOpenButtonImage.setFitWidth(20);

        MenuBar menuBar = new MenuBar();
        menuBar.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        Menu menuOpen = new Menu("Open");
        MenuItem estimateArchive = new MenuItem("Estimate", estimateOpenButtonImage);
        MenuItem travelerArchive = new MenuItem("Traveler", travelerOpenButtonImage);
        MenuItem packingListArchive = new MenuItem("Packing List", packingListOpenButtonImage);
        MenuItem saleReceiptArchive = new MenuItem("Sales Receipt", salesReceiptOpenButtonImage);
        menuOpen.getItems().addAll(estimateArchive, travelerArchive, packingListArchive, saleReceiptArchive);

        estimateArchive.setOnAction(e -> formLookUp());
        travelerArchive.setOnAction(e -> formLookUp());
        packingListArchive.setOnAction(e -> formLookUp());
        saleReceiptArchive.setOnAction(e -> formLookUp());

        Menu preferences = new Menu("Pref");
        MenuItem settings = new MenuItem("Settings");
        preferences.getItems().add(settings);
        settings.setOnAction(e -> {

            if (!settingsPageFilling) {

                displaySettings();

                content.getChildren().removeAll(contentPrompt, estimateScrollPane, travelerScrollPane, packingListScrollPane);
                content.getChildren().add(settingsScrollPane);
                content.setAlignment(Pos.TOP_LEFT);


            } else {

                content.getChildren().removeAll(contentPrompt, estimateScrollPane, travelerScrollPane, packingListScrollPane, settingsScrollPane);
                content.getChildren().add(settingsScrollPane);
                content.setAlignment(Pos.TOP_LEFT);
            }
        });

        Menu file = new Menu("File");
        file.getItems().addAll(menuOpen, preferences);

        Menu menuInfo = new Menu("Info");
        MenuItem devInfo = new MenuItem("Dev Info");
        MenuItem proTip = new MenuItem("Pro Tip");
        menuInfo.getItems().addAll(devInfo, proTip);

        devInfo.setOnAction(e -> displayDevInfo());
        proTip.setOnAction(e -> displayProTip());

        menuBar.getMenus().addAll(file, menuInfo);

        /* Setup for top of BorderPane! */
        VBox information = new VBox();
        information.getChildren().addAll(menuBar);

        /* Setup for left of BorderPane! */
        VBox input = new VBox();
        input.getChildren().addAll(logo, estimate, traveler, packingList, salesReceipt);

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

        /* Sets the background color of the primary Scene! */
        //mainScreen.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene mainScene = new Scene(mainScreen, (ScreenBounds.getWidth() - (ScreenBounds.getWidth() / 4)), (ScreenBounds.getHeight() - (ScreenBounds.getHeight() / 6)));

        /* Starting parameters for primaryStage! */
        window.setScene(mainScene);
        window.setTitle("Quest Input");
        window.setOnCloseRequest(e -> {
            e.consume();

            double centerXPosition = window.getX();
            double centerYPosition = window.getY();
            double primaryStageWidth = window.getWidth();
            double primaryStageHeight = window.getHeight();

            closeProgram = confirmProgramClose.display(centerXPosition, centerYPosition, primaryStageWidth, primaryStageHeight);

            /* If program is closing, the database connection will be closed! */
            if (closeProgram) {

                try {
                    connection.close();
                } catch ( SQLException ignored ) {
                }

                window.close();
                Platform.exit();
                System.exit(0);
            }

        });
        window.show();

        /* Establishes a connection to database and pulls in data from database into the correctedFilePaths Array! */
        initDatabase();
        initDatabaseArray();

        /* Lambda expression for a ChangeListener of the width of the primaryStage.
           This expression auto resizes the width of the buttons within the primaryStage, as well as the button's text font size and image size! */
        window.widthProperty().addListener((observable, oldStageWidth, newStageWidth) -> {

            Double newWidth = (newStageWidth.doubleValue() / 3.0);

            estimate.setMaxWidth(newWidth);
            traveler.setMaxWidth(newWidth);
            packingList.setMaxWidth(newWidth);
            salesReceipt.setMaxWidth(newWidth);

            fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(100));
            imageSize.bind(window.widthProperty().add(window.heightProperty()).divide(26));

            estimate.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #3178ea;"));
            traveler.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #31eaa6;"));
            packingList.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #eab231;"));
            salesReceipt.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #efefef;"));

            estimateButtonImage.fitHeightProperty().bind(imageSize);
            travelerButton.fitHeightProperty().bind(imageSize);
            packingListButton.fitHeightProperty().bind(imageSize);
            salesReceiptButton.fitHeightProperty().bind(imageSize);

            estimateButtonImage.fitWidthProperty().bind(imageSize);
            travelerButton.fitWidthProperty().bind(imageSize);
            packingListButton.fitWidthProperty().bind(imageSize);
            salesReceiptButton.fitWidthProperty().bind(imageSize);


        });

        /* Lambda expression for a ChangeListener of the height of the primaryStage.
           This expression auto resizes the height of the buttons within the primaryStage, as well as the button's text font size and image size! */
        window.heightProperty().addListener((observable, oldStageHeight, newStageHeight) -> {

            Double newHeight = (newStageHeight.doubleValue() / 6.0);

            estimate.setMaxHeight(newHeight);
            traveler.setMaxHeight(newHeight);
            packingList.setMaxHeight(newHeight);
            salesReceipt.setMaxHeight(newHeight);

            fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(100));
            imageSize.bind(window.widthProperty().add(window.heightProperty()).divide(26));

            estimate.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #3178ea;"));
            traveler.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #31eaa6;"));
            packingList.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #eab231;"));
            salesReceipt.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #efefef;"));

            estimateButtonImage.fitHeightProperty().bind(imageSize);
            travelerButton.fitHeightProperty().bind(imageSize);
            packingListButton.fitHeightProperty().bind(imageSize);
            salesReceiptButton.fitHeightProperty().bind(imageSize);

            estimateButtonImage.fitWidthProperty().bind(imageSize);
            travelerButton.fitWidthProperty().bind(imageSize);
            packingListButton.fitWidthProperty().bind(imageSize);
            salesReceiptButton.fitWidthProperty().bind(imageSize);

        });
    }

    /* Method that will create the Estimate form window! */
    private void estimateFormCreate() {

        estimateFormRunning = true;

        /* Setup for items VBox! */
        estimateItemsVBox = new VBox(5);
        estimateItemsVBox.setPadding(new Insets(4));

        /* Setup for the for Descriptor of the Estimate form! */
        Label formDescription = new Label("Estimate Form");
        formDescription.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        /* Setup for the HBox that holds the form descriptor for the Estimate form! */
        HBox formLabel = new HBox(8);
        formLabel.setPadding(new Insets(5));
        formLabel.getChildren().add(formDescription);
        formLabel.setAlignment(Pos.CENTER);

        /* Setup for the Left of the Border Pane! */
        Label dqProjectLabel = new Label("DQ Project #:");
        dqProjectText = new TextField();
        dqProjectText.setPrefWidth(100.0);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(dqProjectText, autoCompleteList);
        }

        Label itemQuantityLabel = new Label("Number of Items:");
        itemQuantityText = new TextField();
        itemQuantityText.setPrefWidth(50.0);

        /* Conditions for the item number enter button, within the action for the button, the appropriate number of element will be created and added to estimateScrollPane! */
        Button itemQuantityButton = new Button("Enter");
        itemQuantityButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        itemQuantityButton.setOnAction((ActionEvent e) -> {

            if (itemQuantityText.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for the items Number");

            } else {

                checkNumberFormat = functions.checkIntegerFormat(itemQuantityText.getText());

                if (checkNumberFormat) {

                    /* Variable for the items number entered, stores the number entered on the estimateWindow for items! */
                    estimateItemNumber = Integer.parseInt(itemQuantityText.getText());

                    if (estimateItemNumber <= 15 && estimateItemNumber != 0) {

                        if (estimateItemNumber >= estimateSavedItemNumber && estimateItemsGenerated) {

                            saveItemData();

                        } else {

                            saveItemData();
                            deleteItemData(false);

                            if (estimateItemsGenerated) {

                                enterItemsData();

                                /* Will re-calculate the total cost of each item when items number is decreased! */
                                calculate(true);
                            }
                        }

                        if (estimateItemNumber <= 15) {

                            estimateSavedItemNumber = estimateItemNumber;
                        }

                    } else if (estimateItemNumber == 0) {

                        itemQuantityText.setText(String.valueOf(estimateSavedItemNumber));

                        estimateMessages.setText("Items number cannot be 0");

                    } else {

                        estimateMessages.setText("Items number entered was greater than the limit of 15");
                    }

                } else {

                    saveItemData();
                    estimateMessages.setText("Number of items was enter wrong");
                }
            }

            if (estimateItemNumber > 0 && estimateItemNumber <= 15) {

                estimateItemsVBox.getChildren().removeAll(estimateItem1HBox, estimateItem2HBox, estimateItem3HBox, estimateItem4HBox, estimateItem5HBox, estimateItem6HBox, estimateItem7HBox, estimateItem8HBox, estimateItem9HBox, estimateItem10HBox, estimateItem11HBox, estimateItem12HBox, estimateItem13HBox, estimateItem14HBox, estimateItem15HBox);
            }

            /* Generates all elements for each item! */
            if (estimateItemNumber >= 1 && estimateItemNumber <= 15) {

                Label item1Label = new Label("Item 1:");
                item1Label.setPrefWidth(60);
                item1Text = new TextField();
                item1Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item1Text, autoCompleteList);
                }
                Label description1Label = new Label("Description:");
                description1Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description1Text, autoCompleteList);
                }
                Label qty1Label = new Label("Qty:");
                qty1Text = new TextField();
                qty1Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty1Text, autoCompleteList);
                }
                Label rate1Label = new Label("Rate:");
                rate1Text = new TextField();
                rate1Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate1Text, autoCompleteList);
                }

                estimateItem1HBox = new HBox(8);
                estimateItem1HBox.getChildren().addAll(item1Label, item1Text, description1Label, description1Text, qty1Label, qty1Text, rate1Label, rate1Text);

                estimateItemsVBox.getChildren().add(estimateItem1HBox);
            }

            if (estimateItemNumber >= 2 && estimateItemNumber <= 15) {

                Label item2Label = new Label("Item 2:");
                item2Label.setPrefWidth(60);
                item2Text = new TextField();
                item2Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item2Text, autoCompleteList);
                }
                Label description2Label = new Label("Description:");
                description2Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description2Text, autoCompleteList);
                }
                Label qty2Label = new Label("Qty:");
                qty2Text = new TextField();
                qty2Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty2Text, autoCompleteList);
                }
                Label rate2Label = new Label("Rate:");
                rate2Text = new TextField();
                rate2Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate2Text, autoCompleteList);
                }

                estimateItem2HBox = new HBox(8);
                estimateItem2HBox.getChildren().addAll(item2Label, item2Text, description2Label, description2Text, qty2Label, qty2Text, rate2Label, rate2Text);

                estimateItemsVBox.getChildren().add(estimateItem2HBox);
            }

            if (estimateItemNumber >= 3 && estimateItemNumber <= 15) {

                Label item3Label = new Label("Item 3:");
                item3Label.setPrefWidth(60);
                item3Text = new TextField();
                item3Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item3Text, autoCompleteList);
                }
                Label description3Label = new Label("Description:");
                description3Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description3Text, autoCompleteList);
                }
                Label qty3Label = new Label("Qty:");
                qty3Text = new TextField();
                qty3Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty3Text, autoCompleteList);
                }
                Label rate3Label = new Label("Rate:");
                rate3Text = new TextField();
                rate3Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate3Text, autoCompleteList);
                }

                estimateItem3HBox = new HBox(8);
                estimateItem3HBox.getChildren().addAll(item3Label, item3Text, description3Label, description3Text, qty3Label, qty3Text, rate3Label, rate3Text);

                estimateItemsVBox.getChildren().add(estimateItem3HBox);
            }

            if (estimateItemNumber >= 4 && estimateItemNumber <= 15) {

                Label item4Label = new Label("Item 4:");
                item4Label.setPrefWidth(60);
                item4Text = new TextField();
                item4Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item4Text, autoCompleteList);
                }
                Label description4Label = new Label("Description:");
                description4Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description4Text, autoCompleteList);
                }
                Label qty4Label = new Label("Qty:");
                qty4Text = new TextField();
                qty4Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty4Text, autoCompleteList);
                }
                Label rate4Label = new Label("Rate:");
                rate4Text = new TextField();
                rate4Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate4Text, autoCompleteList);
                }

                estimateItem4HBox = new HBox(8);
                estimateItem4HBox.getChildren().addAll(item4Label, item4Text, description4Label, description4Text, qty4Label, qty4Text, rate4Label, rate4Text);

                estimateItemsVBox.getChildren().add(estimateItem4HBox);
            }

            if (estimateItemNumber >= 5 && estimateItemNumber <= 15) {

                Label item5Label = new Label("Item 5:");
                item5Label.setPrefWidth(60);
                item5Text = new TextField();
                item5Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item5Text, autoCompleteList);
                }
                Label description5Label = new Label("Description:");
                description5Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description5Text, autoCompleteList);
                }
                Label qty5Label = new Label("Qty:");
                qty5Text = new TextField();
                qty5Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty5Text, autoCompleteList);
                }
                Label rate5Label = new Label("Rate:");
                rate5Text = new TextField();
                rate5Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate5Text, autoCompleteList);
                }

                estimateItem5HBox = new HBox(8);
                estimateItem5HBox.getChildren().addAll(item5Label, item5Text, description5Label, description5Text, qty5Label, qty5Text, rate5Label, rate5Text);

                estimateItemsVBox.getChildren().add(estimateItem5HBox);
            }

            if (estimateItemNumber >= 6 && estimateItemNumber <= 15) {

                Label item6Label = new Label("Item 6:");
                item6Label.setPrefWidth(60);
                item6Text = new TextField();
                item6Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item6Text, autoCompleteList);
                }
                Label description6Label = new Label("Description:");
                description6Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description6Text, autoCompleteList);
                }
                Label qty6Label = new Label("Qty:");
                qty6Text = new TextField();
                qty6Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty6Text, autoCompleteList);
                }
                Label rate6Label = new Label("Rate:");
                rate6Text = new TextField();
                rate6Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate6Text, autoCompleteList);
                }

                estimateItem6HBox = new HBox(8);
                estimateItem6HBox.getChildren().addAll(item6Label, item6Text, description6Label, description6Text, qty6Label, qty6Text, rate6Label, rate6Text);

                estimateItemsVBox.getChildren().add(estimateItem6HBox);
            }

            if (estimateItemNumber >= 7 && estimateItemNumber <= 15) {

                Label item7Label = new Label("Item 7:");
                item7Label.setPrefWidth(60);
                item7Text = new TextField();
                item7Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item7Text, autoCompleteList);
                }
                Label description7Label = new Label("Description:");
                description7Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description7Text, autoCompleteList);
                }
                Label qty7Label = new Label("Qty:");
                qty7Text = new TextField();
                qty7Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty7Text, autoCompleteList);
                }
                Label rate7Label = new Label("Rate:");
                rate7Text = new TextField();
                rate7Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate7Text, autoCompleteList);
                }

                estimateItem7HBox = new HBox(8);
                estimateItem7HBox.getChildren().addAll(item7Label, item7Text, description7Label, description7Text, qty7Label, qty7Text, rate7Label, rate7Text);

                estimateItemsVBox.getChildren().add(estimateItem7HBox);
            }

            if (estimateItemNumber >= 8 && estimateItemNumber <= 15) {

                Label item8Label = new Label("Item 8:");
                item8Label.setPrefWidth(60);
                item8Text = new TextField();
                item8Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item8Text, autoCompleteList);
                }
                Label description8Label = new Label("Description:");
                description8Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description8Text, autoCompleteList);
                }
                Label qty8Label = new Label("Qty:");
                qty8Text = new TextField();
                qty8Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty8Text, autoCompleteList);
                }
                Label rate8Label = new Label("Rate:");
                rate8Text = new TextField();
                rate8Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate8Text, autoCompleteList);
                }

                estimateItem8HBox = new HBox(8);
                estimateItem8HBox.getChildren().addAll(item8Label, item8Text, description8Label, description8Text, qty8Label, qty8Text, rate8Label, rate8Text);

                estimateItemsVBox.getChildren().add(estimateItem8HBox);
            }

            if (estimateItemNumber >= 9 && estimateItemNumber <= 15) {

                Label item9Label = new Label("Item 9:");
                item9Label.setPrefWidth(60);
                item9Text = new TextField();
                item9Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item9Text, autoCompleteList);
                }
                Label description9Label = new Label("Description:");
                description9Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description9Text, autoCompleteList);
                }
                Label qty9Label = new Label("Qty:");
                qty9Text = new TextField();
                qty9Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty9Text, autoCompleteList);
                }
                Label rate9Label = new Label("Rate:");
                rate9Text = new TextField();
                rate9Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate9Text, autoCompleteList);
                }

                estimateItem9HBox = new HBox(8);
                estimateItem9HBox.getChildren().addAll(item9Label, item9Text, description9Label, description9Text, qty9Label, qty9Text, rate9Label, rate9Text);

                estimateItemsVBox.getChildren().add(estimateItem9HBox);
            }

            if (estimateItemNumber >= 10 && estimateItemNumber <= 15) {

                Label item10Label = new Label("Item 10:");
                item10Label.setPrefWidth(60);
                item10Text = new TextField();
                item10Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item10Text, autoCompleteList);
                }
                Label description10Label = new Label("Description:");
                description10Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description10Text, autoCompleteList);
                }
                Label qty10Label = new Label("Qty:");
                qty10Text = new TextField();
                qty10Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty10Text, autoCompleteList);
                }
                Label rate10Label = new Label("Rate:");
                rate10Text = new TextField();
                rate10Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate10Text, autoCompleteList);
                }

                estimateItem10HBox = new HBox(8);
                estimateItem10HBox.getChildren().addAll(item10Label, item10Text, description10Label, description10Text, qty10Label, qty10Text, rate10Label, rate10Text);

                estimateItemsVBox.getChildren().add(estimateItem10HBox);
            }

            if (estimateItemNumber >= 11 && estimateItemNumber <= 15) {

                Label item11Label = new Label("Item 11:");
                item11Label.setPrefWidth(60);
                item11Text = new TextField();
                item11Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item11Text, autoCompleteList);
                }
                Label description11Label = new Label("Description:");
                description11Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description11Text, autoCompleteList);
                }
                Label qty11Label = new Label("Qty:");
                qty11Text = new TextField();
                qty11Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty11Text, autoCompleteList);
                }
                Label rate11Label = new Label("Rate:");
                rate11Text = new TextField();
                rate11Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate11Text, autoCompleteList);
                }

                estimateItem11HBox = new HBox(8);
                estimateItem11HBox.getChildren().addAll(item11Label, item11Text, description11Label, description11Text, qty11Label, qty11Text, rate11Label, rate11Text);

                estimateItemsVBox.getChildren().add(estimateItem11HBox);
            }

            if (estimateItemNumber >= 12 && estimateItemNumber <= 15) {

                Label item12Label = new Label("Item 12:");
                item12Label.setPrefWidth(60);
                item12Text = new TextField();
                item12Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item12Text, autoCompleteList);
                }
                Label description12Label = new Label("Description:");
                description12Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description12Text, autoCompleteList);
                }
                Label qty12Label = new Label("Qty:");
                qty12Text = new TextField();
                qty12Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty12Text, autoCompleteList);
                }
                Label rate12Label = new Label("Rate:");
                rate12Text = new TextField();
                rate12Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate12Text, autoCompleteList);
                }

                estimateItem12HBox = new HBox(8);
                estimateItem12HBox.getChildren().addAll(item12Label, item12Text, description12Label, description12Text, qty12Label, qty12Text, rate12Label, rate12Text);

                estimateItemsVBox.getChildren().add(estimateItem12HBox);
            }

            if (estimateItemNumber >= 13 && estimateItemNumber <= 15) {

                Label item13Label = new Label("Item 13:");
                item13Label.setPrefWidth(60);
                item13Text = new TextField();
                item13Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item13Text, autoCompleteList);
                }
                Label description13Label = new Label("Description:");
                description13Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description13Text, autoCompleteList);
                }
                Label qty13Label = new Label("Qty:");
                qty13Text = new TextField();
                qty13Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty13Text, autoCompleteList);
                }
                Label rate13Label = new Label("Rate:");
                rate13Text = new TextField();
                rate13Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate13Text, autoCompleteList);
                }

                estimateItem13HBox = new HBox(8);
                estimateItem13HBox.getChildren().addAll(item13Label, item13Text, description13Label, description13Text, qty13Label, qty13Text, rate13Label, rate13Text);

                estimateItemsVBox.getChildren().add(estimateItem13HBox);
            }

            if (estimateItemNumber >= 14 && estimateItemNumber <= 15) {

                Label item14Label = new Label("Item 14:");
                item14Label.setPrefWidth(60);
                item14Text = new TextField();
                item14Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item14Text, autoCompleteList);
                }
                Label description14Label = new Label("Description:");
                description14Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description14Text, autoCompleteList);
                }
                Label qty14Label = new Label("Qty:");
                qty14Text = new TextField();
                qty14Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty14Text, autoCompleteList);
                }
                Label rate14Label = new Label("Rate:");
                rate14Text = new TextField();
                rate14Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate14Text, autoCompleteList);
                }

                estimateItem14HBox = new HBox(8);
                estimateItem14HBox.getChildren().addAll(item14Label, item14Text, description14Label, description14Text, qty14Label, qty14Text, rate14Label, rate14Text);

                estimateItemsVBox.getChildren().add(estimateItem14HBox);
            }

            if (estimateItemNumber == 15) {

                Label item15Label = new Label("Item 15:");
                item15Label.setPrefWidth(60);
                item15Text = new TextField();
                item15Text.setPrefWidth(100.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item15Text, autoCompleteList);
                }
                Label description15Label = new Label("Description:");
                description15Text = new TextField();
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(description15Text, autoCompleteList);
                }
                Label qty15Label = new Label("Qty:");
                qty15Text = new TextField();
                qty15Text.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(qty15Text, autoCompleteList);
                }
                Label rate15Label = new Label("Rate:");
                rate15Text = new TextField();
                rate15Text.setPrefWidth(75.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(rate15Text, autoCompleteList);
                }

                estimateItem15HBox = new HBox(8);
                estimateItem15HBox.getChildren().addAll(item15Label, item15Text, description15Label, description15Text, qty15Label, qty15Text, rate15Label, rate15Text);

                estimateItemsVBox.getChildren().add(estimateItem15HBox);
            }

            if (estimateItemsGenerated) {

                enterItemsData();
            }

            estimateItemsGenerated = true;

        });

        HBox dqProjectHBox = new HBox(8);
        dqProjectHBox.setPadding(new Insets(5));
        dqProjectHBox.getChildren().addAll(dqProjectLabel, dqProjectText, itemQuantityLabel, itemQuantityText, itemQuantityButton);
        dqProjectHBox.setAlignment(Pos.TOP_LEFT);

        Label dateLabel = new Label("Date:");
        dateText = new DatePicker();
        dateText.setPrefWidth(130.0);
        dateText.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        HBox dateHBox = new HBox(8);
        dateHBox.setPadding(new Insets(5));
        dateHBox.getChildren().addAll(dateLabel, dateText);
        dateHBox.setAlignment(Pos.TOP_LEFT);

        Label invoiceLabel = new Label("Invoice #:");
        invoiceText = new TextField();
        invoiceText.setPrefWidth(100.0);

        /* Calendar variable used to get current year, month, and day of month for presetting the value in the invoice TextField! */
        Calendar calendar = new GregorianCalendar(Locale.ENGLISH);

        String dateDay; /* Variable for storing the current day! */
        String date; /* Variable for storing the current data with year, month, and day! */

        if (calendar.get(Calendar.DATE) < 10) {

            dateDay = "0" + String.valueOf(calendar.get(Calendar.DATE));

        } else {

            dateDay = String.valueOf(calendar.get(Calendar.DATE));
        }

        if (calendar.get(Calendar.MONTH) < 9) {

            String month = "0" + String.valueOf(calendar.get(Calendar.MONTH) + 1);
            date = String.valueOf(calendar.get(Calendar.YEAR) - 2000) + "-" + month + dateDay + "-";

        } else {

            date = String.valueOf(calendar.get(Calendar.YEAR) - 2000) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + dateDay + "-";
        }

        invoiceText.setText(date);

        HBox invoiceHBox = new HBox(8);
        invoiceHBox.setPadding(new Insets(5));
        invoiceHBox.getChildren().addAll(invoiceLabel, invoiceText);
        invoiceHBox.setAlignment(Pos.TOP_LEFT);

        Label companyNameLabel = new Label("Name");
        companyNameText = new TextField();
        companyNameText.setPrefWidth(175.0);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(companyNameText, autoCompleteList);
        }

        Label addressLabel = new Label("Address:");
        addressText = new TextField();
        addressText.setPrefWidth(175.0);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(addressText, autoCompleteList);
        }

        HBox nameAddressHBox = new HBox(8);
        nameAddressHBox.setPadding(new Insets(5));
        nameAddressHBox.getChildren().addAll(companyNameLabel, companyNameText, addressLabel, addressText);
        nameAddressHBox.setAlignment(Pos.TOP_LEFT);

        /* Setup for error message label! */
        estimateMessages = new Label();
        estimateMessages.setTextFill(Color.web("#f73636"));
        estimateMessages.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        estimateMessages.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!Objects.equals(newValue, "")) {

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> estimateMessages.setText(""));
                    }
                }, 5000);
            }
        });

        estimateMessages.setOnMouseEntered(event -> {
            estimateMessages.setScaleX(1.20);
            estimateMessages.setScaleY(1.20);
        });

        estimateMessages.setOnMouseExited(event -> {
            estimateMessages.setScaleX(1.00);
            estimateMessages.setScaleY(1.00);
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

                estimateItemNumber = 0;
                estimateItemsGenerated = false;
                deleteItemData(true);

                content.getChildren().removeAll(estimateScrollPane);
                content.getChildren().add(contentPrompt);
                content.setAlignment(Pos.CENTER);
                contentPrompt.setText("Form Canceled");

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> contentPrompt.setText("Select a form to complete"));
                    }
                }, 5000);

                estimateFormRunning = false;
            }
        });

        /* Conditions for the Save button to save the entered information for the Estimate form! */
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        saveButton.setOnAction((ActionEvent e) -> {

            checkEstimateDataInput = checkEstimateDataInput();

            if (checkEstimateDataInput) {

                estimateCalculationReturn = calculate(true);

                if (estimateCalculationReturn) {

                    saveItemData();

                    File saveFile = saveFileChooser(estimateData[2], "Save", "Estimate");

                    if (saveFile != null) {

                        try {

                            Statement statementInsertAuto = connection.createStatement();
                            statementInsertAuto.setQueryTimeout(10);

                            for (String anEstimateData : estimateData) {

                                if (!Objects.equals(anEstimateData, "") && anEstimateData != null) {

                                    boolean passed = true;

                                    for (String anAutoCompleteList : autoCompleteList) {

                                        if (anEstimateData.equals(anAutoCompleteList)) {

                                            passed = false;
                                        }
                                    }

                                    if (passed) {

                                        String insertAuto = String.format("INSERT INTO AUTO (WORD) VALUES ('%s')", anEstimateData);
                                        statementInsertAuto.execute(insertAuto);
                                        autoCompleteList.add(anEstimateData);
                                    }
                                }
                            }

                            statementInsertAuto.close();

                        } catch ( Exception e1 ) {
                            e1.printStackTrace();
                        }

                        inputWorkbookData.estimateExcel(estimateData, estimateSavedItemNumber, saveFile.getAbsolutePath());

                        URL notificationImage = this.getClass().getResource("/sample/CustomerFiles/Images/Saved.gif");

                        TrayNotification notification = new TrayNotification("Estimate Formed Saved", "Saved To: " + saveFile.getAbsolutePath(), NotificationType.SUCCESS);
                        notification.setImage(new Image(String.valueOf(notificationImage)));
                        notification.setRectangleFill(Paint.valueOf("#46494f"));
                        notification.showAndDismiss(Duration.seconds(4));

                        estimateItemNumber = 0;
                        estimateItemsGenerated = false;
                        deleteItemData(true);

                        content.getChildren().removeAll(estimateScrollPane);
                        content.getChildren().add(contentPrompt);
                        content.setAlignment(Pos.CENTER);
                        contentPrompt.setText("Form Saved");

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() ->
                                        contentPrompt.setText("Select a form to complete"));
                                try {
                                    Desktop.getDesktop().open(new File(saveFile.getAbsolutePath()));
                                } catch ( IOException e1 ) {
                                    e1.printStackTrace();
                                }
                            }
                        }, 5000);

                        estimateFormRunning = false;
                    }
                }
            }
        });

        /* Conditions for the Calculate button to calculate the total cost of each item! */
        Button calCost = new Button("Calculate");
        calCost.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        calCost.setOnAction((ActionEvent e) -> {

            checkEstimateDataInput = checkEstimateDataInput();

            calculate(checkEstimateDataInput);
        });

        /* Setup of Label to display the total cost of each item for an Estimate! */
        totalCost = new Label("$00.00");
        totalCost.setFont(Font.font("Arial", 16));

        Label total = new Label("Cost Total:");
        total.setFont(Font.font("Arial", 16));

        /* Setup for the top portion of the estimateLayout VBox! */
        VBox dataInput = new VBox(4);
        dataInput.setPadding(new Insets(4));
        dataInput.getChildren().addAll(formLabel, dqProjectHBox, dateHBox, invoiceHBox, nameAddressHBox, estimateItemsVBox);

        /* Setup for the middle portion of the estimateLayout VBox that displays error estimateMessages! */
        VBox errorMessages = new VBox(8);
        errorMessages.setPadding(new Insets(4));
        errorMessages.setAlignment(Pos.BOTTOM_CENTER);
        errorMessages.getChildren().add(estimateMessages);
        errorMessages.setStyle("-fx-font: 14 arial; -fx-base: #f73636;");

        /* Setup for the Left Bottom of the bottom HBox! */
        HBox bottomLeft = new HBox(8);
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

        /* Setup for HBox that holds the bottom elements of the estimateLayout VBox! */
        HBox bottom = new HBox(8);
        bottom.getChildren().addAll(bottomLeft, bottomRight);

        /* Setup for the estimateLayout VBox and estimateScrollPane ScrollPane! */
        VBox estimateLayout = new VBox(0);
        estimateLayout.getChildren().addAll(dataInput, errorMessages, bottom);
        estimateScrollPane = new ScrollPane();
        estimateScrollPane.setContent(estimateLayout);
        estimateScrollPane.setFitToWidth(true);
        estimateScrollPane.setPrefHeight(window.heightProperty().doubleValue());

    }

    /* Method to check that data has been entered in each TextField of the Estimate form. If data hasn't been entered then an error message will be displayed! */
    private boolean checkEstimateDataInput() {

        checkEstimateDataInput = false;

        if (itemQuantityText.getText().isEmpty()) {

            estimateMessages.setText("Nothing was entered for Items Number");

        } else if (estimateItemNumber == 0) {

            estimateMessages.setText("No item number was ever entered");

        } else if (dateText.getValue() == null) {

            estimateMessages.setText("Date entered Incorrectly or not at all");

        } else if (invoiceText.getText().isEmpty()) {

            estimateMessages.setText("Nothing was entered for Invoice Number");

        } else if (companyNameText.getText().isEmpty()) {

            estimateMessages.setText("Nothing was entered for Name");

        } else if (addressText.getText().isEmpty()) {

            estimateMessages.setText("Nothing was entered for Address");

        } else if ((estimateItemNumber >= 1 && estimateItemNumber <= 15) && (item1Text.getText().isEmpty() || description1Text.getText().isEmpty() || qty1Text.getText().isEmpty() || rate1Text.getText().isEmpty())) {

            if (item1Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 1");

            } else if (description1Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 1");

            } else if (qty1Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 1");

            } else if (rate1Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 1");
            }

        } else if ((estimateItemNumber >= 2 && estimateItemNumber <= 15) && (item2Text.getText().isEmpty() || description2Text.getText().isEmpty() || qty2Text.getText().isEmpty() || rate2Text.getText().isEmpty())) {

            if (item2Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 2");

            } else if (description2Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 2");

            } else if (qty2Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 2");

            } else if (rate2Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 2");
            }

        } else if ((estimateItemNumber >= 3 && estimateItemNumber <= 15) && (item3Text.getText().isEmpty() || description3Text.getText().isEmpty() || qty3Text.getText().isEmpty() || rate3Text.getText().isEmpty())) {

            if (item3Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 3");

            } else if (description3Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 3");

            } else if (qty3Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 3");

            } else if (rate3Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 3");
            }

        } else if ((estimateItemNumber >= 4 && estimateItemNumber <= 15) && (item4Text.getText().isEmpty() || description4Text.getText().isEmpty() || qty4Text.getText().isEmpty() || rate4Text.getText().isEmpty())) {

            if (item4Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 4");

            } else if (description4Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 4");

            } else if (qty4Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 4");

            } else if (rate4Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 4");
            }

        } else if ((estimateItemNumber >= 5 && estimateItemNumber <= 15) && (item5Text.getText().isEmpty() || description5Text.getText().isEmpty() || qty5Text.getText().isEmpty() || rate5Text.getText().isEmpty())) {

            if (item5Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 5");

            } else if (description5Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 5");

            } else if (qty5Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 5");

            } else if (rate5Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 5");
            }

        } else if ((estimateItemNumber >= 6 && estimateItemNumber <= 15) && (item6Text.getText().isEmpty() || description6Text.getText().isEmpty() || qty6Text.getText().isEmpty() || rate6Text.getText().isEmpty())) {

            if (item6Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 6");

            } else if (description6Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 6");

            } else if (qty6Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 6");

            } else if (rate6Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 6");
            }

        } else if ((estimateItemNumber >= 7 && estimateItemNumber <= 15) && (item7Text.getText().isEmpty() || description7Text.getText().isEmpty() || qty7Text.getText().isEmpty() || rate7Text.getText().isEmpty())) {

            if (item7Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 7");

            } else if (description7Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 7");

            } else if (qty7Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 7");

            } else if (rate7Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 7");
            }

        } else if ((estimateItemNumber >= 8 && estimateItemNumber <= 15) && (item8Text.getText().isEmpty() || description8Text.getText().isEmpty() || qty8Text.getText().isEmpty() || rate8Text.getText().isEmpty())) {

            if (item8Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 8");

            } else if (description8Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 8");

            } else if (qty8Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 8");

            } else if (rate8Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 8");
            }

        } else if ((estimateItemNumber >= 9 && estimateItemNumber <= 15) && (item9Text.getText().isEmpty() || description9Text.getText().isEmpty() || qty9Text.getText().isEmpty() || rate9Text.getText().isEmpty())) {

            if (item9Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 9");

            } else if (description9Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 9");

            } else if (qty9Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 9");

            } else if (rate9Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 9");
            }

        } else if ((estimateItemNumber >= 10 && estimateItemNumber <= 15) && (item10Text.getText().isEmpty() || description10Text.getText().isEmpty() || qty10Text.getText().isEmpty() || rate10Text.getText().isEmpty())) {

            if (item10Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 10");

            } else if (description10Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 10");

            } else if (qty10Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 10");

            } else if (rate10Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 10");
            }

        } else if ((estimateItemNumber >= 11 && estimateItemNumber <= 15) && (item11Text.getText().isEmpty() || description11Text.getText().isEmpty() || qty11Text.getText().isEmpty() || rate11Text.getText().isEmpty())) {

            if (item11Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 11");

            } else if (description11Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 11");

            } else if (qty11Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 11");

            } else if (rate11Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 11");
            }

        } else if ((estimateItemNumber >= 12 && estimateItemNumber <= 15) && (item12Text.getText().isEmpty() || description12Text.getText().isEmpty() || qty12Text.getText().isEmpty() || rate12Text.getText().isEmpty())) {

            if (item12Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 12");

            } else if (description12Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 12");

            } else if (qty12Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 12");

            } else if (rate12Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 12");
            }

        } else if ((estimateItemNumber >= 13 && estimateItemNumber <= 15) && (item13Text.getText().isEmpty() || description13Text.getText().isEmpty() || qty13Text.getText().isEmpty() || rate13Text.getText().isEmpty())) {

            if (item13Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 13");

            } else if (description13Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 13");

            } else if (qty13Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 13");

            } else if (rate13Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 13");
            }

        } else if ((estimateItemNumber >= 14 && estimateItemNumber <= 15) && (item14Text.getText().isEmpty() || description14Text.getText().isEmpty() || qty14Text.getText().isEmpty() || rate14Text.getText().isEmpty())) {

            if (item14Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 14");

            } else if (description14Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 14");

            } else if (qty14Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 14");

            } else if (rate14Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 14");
            }

        } else if (estimateItemNumber == 15 && (item15Text.getText().isEmpty() || description15Text.getText().isEmpty() || qty15Text.getText().isEmpty() || rate15Text.getText().isEmpty())) {

            if (item15Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was entered for Item 15");

            } else if (description15Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for Description of Item 15");

            } else if (qty15Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Quantity of Item 15");

            } else if (rate15Text.getText().isEmpty()) {

                estimateMessages.setText("Nothing was enter for the Rate of Item 15");
            }

        } else {

            checkEstimateDataInput = true;
        }

        if (!dqProjectText.getText().isEmpty() && checkEstimateDataInput) {

            checkNumberFormat = functions.checkNumberFormat(dqProjectText.getText());

            if (!checkNumberFormat) {

                estimateMessages.setText("Number for DQ Project Number was entered wrong");

                checkEstimateDataInput = false;
            }
        }

        if (!invoiceText.getText().isEmpty() && checkEstimateDataInput) {

            checkNumberFormat = create.invoiceFormat(invoiceText.getText());

            if (!checkNumberFormat) {

                estimateMessages.setText("Number for Invoice Number was entered wrong");

                checkEstimateDataInput = false;
            }
        }

        return checkEstimateDataInput;
    }

    /* Method to calculate to total cost of each item if the Estimate form! */
    @SuppressWarnings("ConstantConditions")
    private boolean calculate(boolean check) {

        Double costTotalDouble;

        boolean estimateCalculationCheck = true;

        totalCost.setText("$00.00");

        if (check) {

            costTotalDouble = 00.00;

            if ((estimateItemNumber >= 1 && estimateItemNumber <= 15) && (!qty1Text.getText().isEmpty() || !rate1Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty1Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty1Text.getText());

                    if (checkCurrencyFormat) {

                        qty1 = Double.parseDouble(functions.removeComas(qty1Text.getText()));

                    } else {

                        qty1 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 1 Quantity");
                    }

                } else {

                    if (!qty1Text.getText().isEmpty()) {

                        qty1 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 1 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate1Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate1Text.getText());

                    if (checkCurrencyFormat) {

                        rate1 = Double.parseDouble(functions.removeComas(rate1Text.getText()));

                    } else {

                        rate1 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 1 Rate");
                    }

                } else {

                    if (!rate1Text.getText().isEmpty()) {

                        rate1 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 1 Rate");
                    }
                }

                if (qty1 != null && rate1 != null) {

                    costTotalDouble = (costTotalDouble + (qty1 * rate1));
                }
            }

            if ((estimateItemNumber >= 2 && estimateItemNumber <= 15) && (!qty2Text.getText().isEmpty() || !rate2Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty2Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty2Text.getText());

                    if (checkCurrencyFormat) {

                        qty2 = Double.parseDouble(functions.removeComas(qty2Text.getText()));

                    } else {

                        qty2 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 2 Quantity");
                    }

                } else {

                    if (!qty2Text.getText().isEmpty()) {

                        qty2 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 2 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate2Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate2Text.getText());

                    if (checkCurrencyFormat) {

                        rate2 = Double.parseDouble(functions.removeComas(rate2Text.getText()));

                    } else {

                        rate2 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 2 Rate");
                    }

                } else {

                    if (!rate2Text.getText().isEmpty()) {

                        rate2 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 2 Rate");
                    }
                }

                if (qty2 != null && rate2 != null) {

                    costTotalDouble = (costTotalDouble + (qty2 * rate2));
                }
            }

            if ((estimateItemNumber >= 3 && estimateItemNumber <= 15) && (!qty3Text.getText().isEmpty() || !rate3Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty3Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty3Text.getText());

                    if (checkCurrencyFormat) {

                        qty3 = Double.parseDouble(functions.removeComas(qty3Text.getText()));

                    } else {

                        qty3 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 3 Quantity");
                    }

                } else {

                    if (!qty3Text.getText().isEmpty()) {

                        qty3 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 3 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate3Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate3Text.getText());

                    if (checkCurrencyFormat) {

                        rate3 = Double.parseDouble(functions.removeComas(rate3Text.getText()));

                    } else {

                        rate3 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 3 Rate");
                    }

                } else {

                    if (!rate3Text.getText().isEmpty()) {

                        rate3 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 3 Rate");
                    }
                }

                if (qty3 != null && rate3 != null) {

                    costTotalDouble = (costTotalDouble + (qty3 * rate3));
                }
            }

            if ((estimateItemNumber >= 4 && estimateItemNumber <= 15) && (!qty4Text.getText().isEmpty() || !rate4Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty4Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty4Text.getText());

                    if (checkCurrencyFormat) {

                        qty4 = Double.parseDouble(functions.removeComas(qty4Text.getText()));

                    } else {

                        qty4 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 4 Quantity");
                    }

                } else {

                    if (!qty4Text.getText().isEmpty()) {

                        qty4 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 4 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate4Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate4Text.getText());

                    if (checkCurrencyFormat) {

                        rate4 = Double.parseDouble(functions.removeComas(rate4Text.getText()));

                    } else {

                        rate4 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 4 Rate");
                    }

                } else {

                    if (!rate4Text.getText().isEmpty()) {

                        rate4 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 4 Rate");
                    }
                }

                if (qty4 != null && rate4 != null) {

                    costTotalDouble = (costTotalDouble + (qty4 * rate4));
                }
            }

            if ((estimateItemNumber >= 5 && estimateItemNumber <= 15) && (!qty5Text.getText().isEmpty() || !rate5Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty5Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty5Text.getText());

                    if (checkCurrencyFormat) {

                        qty5 = Double.parseDouble(functions.removeComas(qty5Text.getText()));

                    } else {

                        qty5 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 5 Quantity");
                    }

                } else {

                    if (!qty5Text.getText().isEmpty()) {

                        qty5 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 5 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate5Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate5Text.getText());

                    if (checkCurrencyFormat) {

                        rate5 = Double.parseDouble(functions.removeComas(rate5Text.getText()));

                    } else {

                        rate5 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 5 Rate");
                    }

                } else {

                    if (!rate5Text.getText().isEmpty()) {

                        rate5 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 5 Rate");
                    }
                }

                if (qty5 != null && rate5 != null) {

                    costTotalDouble = (costTotalDouble + (qty5 * rate5));
                }
            }

            if ((estimateItemNumber >= 6 && estimateItemNumber <= 15) && (!qty6Text.getText().isEmpty() || !rate6Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty6Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty6Text.getText());

                    if (checkCurrencyFormat) {

                        qty6 = Double.parseDouble(functions.removeComas(qty6Text.getText()));

                    } else {

                        qty6 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 6 Quantity");
                    }

                } else {

                    if (!qty6Text.getText().isEmpty()) {

                        qty6 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 6 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate6Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate6Text.getText());

                    if (checkCurrencyFormat) {

                        rate6 = Double.parseDouble(functions.removeComas(rate6Text.getText()));

                    } else {

                        rate6 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 6 Rate");
                    }

                } else {

                    if (!rate6Text.getText().isEmpty()) {

                        rate6 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 6 Rate");
                    }
                }

                if (qty6 != null && rate6 != null) {

                    costTotalDouble = (costTotalDouble + (qty6 * rate6));
                }
            }

            if ((estimateItemNumber >= 7 && estimateItemNumber <= 15) && (!qty7Text.getText().isEmpty() || !rate7Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty7Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty7Text.getText());

                    if (checkCurrencyFormat) {

                        qty7 = Double.parseDouble(functions.removeComas(qty7Text.getText()));

                    } else {

                        qty7 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 7 Quantity");
                    }

                } else {

                    if (!qty7Text.getText().isEmpty()) {

                        qty7 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 7 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate7Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate7Text.getText());

                    if (checkCurrencyFormat) {

                        rate7 = Double.parseDouble(functions.removeComas(rate7Text.getText()));

                    } else {

                        rate7 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 7 Rate");
                    }

                } else {

                    if (!rate7Text.getText().isEmpty()) {

                        rate7 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 7 Rate");
                    }
                }

                if (qty7 != null && rate7 != null) {

                    costTotalDouble = (costTotalDouble + (qty7 * rate7));
                }
            }

            if ((estimateItemNumber >= 8 && estimateItemNumber <= 15) && (!qty8Text.getText().isEmpty() || !rate8Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty8Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty8Text.getText());

                    if (checkCurrencyFormat) {

                        qty8 = Double.parseDouble(functions.removeComas(qty8Text.getText()));

                    } else {

                        qty8 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 8 Quantity");
                    }

                } else {

                    if (!qty8Text.getText().isEmpty()) {

                        qty8 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 8 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate8Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate8Text.getText());

                    if (checkCurrencyFormat) {

                        rate8 = Double.parseDouble(functions.removeComas(rate8Text.getText()));

                    } else {

                        rate8 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 8 Rate");
                    }

                } else {

                    if (!rate8Text.getText().isEmpty()) {

                        rate8 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 8 Rate");
                    }
                }

                if (qty8 != null && rate8 != null) {

                    costTotalDouble = (costTotalDouble + (qty8 * rate8));
                }
            }

            if ((estimateItemNumber >= 9 && estimateItemNumber <= 15) && (!qty9Text.getText().isEmpty() || !rate9Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty9Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty9Text.getText());

                    if (checkCurrencyFormat) {

                        qty9 = Double.parseDouble(functions.removeComas(qty9Text.getText()));

                    } else {

                        qty9 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 9 Quantity");
                    }

                } else {

                    if (!qty9Text.getText().isEmpty()) {

                        qty9 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 9 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate9Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate9Text.getText());

                    if (checkCurrencyFormat) {

                        rate9 = Double.parseDouble(functions.removeComas(rate9Text.getText()));

                    } else {

                        rate9 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 9 Rate");
                    }

                } else {

                    if (!rate9Text.getText().isEmpty()) {

                        rate9 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 9 Rate");
                    }
                }

                if (qty9 != null && rate9 != null) {

                    costTotalDouble = (costTotalDouble + (qty9 * rate9));
                }
            }

            if ((estimateItemNumber >= 10 && estimateItemNumber <= 15) && (!qty10Text.getText().isEmpty() || !rate10Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty10Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty10Text.getText());

                    if (checkCurrencyFormat) {

                        qty10 = Double.parseDouble(functions.removeComas(qty10Text.getText()));

                    } else {

                        qty10 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 10 Quantity");
                    }

                } else {

                    if (!qty10Text.getText().isEmpty()) {

                        qty10 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 10 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate10Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate10Text.getText());

                    if (checkCurrencyFormat) {

                        rate10 = Double.parseDouble(functions.removeComas(rate10Text.getText()));

                    } else {

                        rate10 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 10 Rate");
                    }

                } else {

                    if (!rate10Text.getText().isEmpty()) {

                        rate10 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 10 Rate");
                    }
                }

                if (qty10 != null && rate10 != null) {

                    costTotalDouble = (costTotalDouble + (qty10 * rate10));
                }
            }

            if ((estimateItemNumber >= 11 && estimateItemNumber <= 15) && (!qty11Text.getText().isEmpty() || !rate11Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty11Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty11Text.getText());

                    if (checkCurrencyFormat) {

                        qty11 = Double.parseDouble(functions.removeComas(qty11Text.getText()));

                    } else {

                        qty11 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 11 Quantity");
                    }

                } else {

                    if (!qty11Text.getText().isEmpty()) {

                        qty11 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 11 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate11Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate11Text.getText());

                    if (checkCurrencyFormat) {

                        rate11 = Double.parseDouble(functions.removeComas(rate11Text.getText()));

                    } else {

                        rate11 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 11 Rate");
                    }

                } else {

                    if (!rate11Text.getText().isEmpty()) {

                        rate11 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 11 Rate");
                    }
                }

                if (qty11 != null && rate11 != null) {

                    costTotalDouble = (costTotalDouble + (qty11 * rate11));
                }
            }

            if ((estimateItemNumber >= 12 && estimateItemNumber <= 15) && (!qty12Text.getText().isEmpty() || !rate12Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty12Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty12Text.getText());

                    if (checkCurrencyFormat) {

                        qty12 = Double.parseDouble(functions.removeComas(qty12Text.getText()));

                    } else {

                        qty12 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 12 Quantity");
                    }

                } else {

                    if (!qty12Text.getText().isEmpty()) {

                        qty12 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 12 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate12Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate12Text.getText());

                    if (checkCurrencyFormat) {

                        rate12 = Double.parseDouble(functions.removeComas(rate12Text.getText()));

                    } else {

                        rate12 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 12 Rate");
                    }

                } else {

                    if (!rate12Text.getText().isEmpty()) {

                        rate12 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 12 Rate");
                    }
                }

                if (qty12 != null && rate12 != null) {

                    costTotalDouble = (costTotalDouble + (qty12 * rate12));
                }
            }

            if ((estimateItemNumber >= 13 && estimateItemNumber <= 15) && (!qty13Text.getText().isEmpty() || !rate13Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty13Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty13Text.getText());

                    if (checkCurrencyFormat) {

                        qty13 = Double.parseDouble(functions.removeComas(qty13Text.getText()));

                    } else {

                        qty13 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 13 Quantity");
                    }

                } else {

                    if (!qty13Text.getText().isEmpty()) {

                        qty13 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 13 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate13Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate13Text.getText());

                    if (checkCurrencyFormat) {

                        rate13 = Double.parseDouble(functions.removeComas(rate13Text.getText()));

                    } else {

                        rate13 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 13 Rate");
                    }

                } else {

                    if (!rate13Text.getText().isEmpty()) {

                        rate13 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 13 Rate");
                    }
                }

                if (qty13 != null && rate13 != null) {

                    costTotalDouble = (costTotalDouble + (qty13 * rate13));
                }
            }

            if ((estimateItemNumber >= 14 && estimateItemNumber <= 15) && (!qty14Text.getText().isEmpty() || !rate14Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty14Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty14Text.getText());

                    if (checkCurrencyFormat) {

                        qty14 = Double.parseDouble(functions.removeComas(qty14Text.getText()));

                    } else {

                        qty14 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 14 Quantity");
                    }

                } else {

                    if (!qty14Text.getText().isEmpty()) {

                        qty14 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 14 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate14Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate14Text.getText());

                    if (checkCurrencyFormat) {

                        rate14 = Double.parseDouble(functions.removeComas(rate14Text.getText()));

                    } else {

                        rate14 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 14 Rate");
                    }

                } else {

                    if (!rate14Text.getText().isEmpty()) {

                        rate14 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 14 Rate");
                    }
                }

                if (qty14 != null && rate14 != null) {

                    costTotalDouble = (costTotalDouble + (qty14 * rate14));
                }
            }

            if (estimateItemNumber == 15 && (!qty15Text.getText().isEmpty() || !rate15Text.getText().isEmpty())) {

                checkCurrencyFormat = functions.checkCurrencyFormat(qty15Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(qty15Text.getText());

                    if (checkCurrencyFormat) {

                        qty15 = Double.parseDouble(functions.removeComas(qty15Text.getText()));

                    } else {

                        qty15 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 15 Quantity");
                    }

                } else {

                    if (!qty15Text.getText().isEmpty()) {

                        qty15 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 15 Quantity");
                    }
                }

                checkCurrencyFormat = functions.checkCurrencyFormat(rate15Text.getText());

                if (checkCurrencyFormat) {

                    checkCurrencyFormat = functions.checkNumberFormat(rate15Text.getText());

                    if (checkCurrencyFormat) {

                        rate15 = Double.parseDouble(functions.removeComas(rate15Text.getText()));

                    } else {

                        rate15 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was formatted incorrectly for item 15 Rate");
                    }

                } else {

                    if (!rate15Text.getText().isEmpty()) {

                        rate15 = 0.00;
                        estimateCalculationCheck = false;
                        estimateMessages.setText("Number was entered wrong for item 15 Rate");
                    }
                }

                if (qty15 != null && rate15 != null) {

                    costTotalDouble = (costTotalDouble + (qty15 * rate15));
                }
            }

            if (estimateCalculationCheck) {

                try {
                    costTotalString = functions.addComasDouble(df.format(costTotalDouble));
                } catch ( StringInputException e ) {
                    e.printStackTrace();
                }

                totalCost.setText("$" + costTotalString);
            }
        }

        return estimateCalculationCheck;
    }

    /* Method to store entered information within the Estimate form! */
    @SuppressWarnings("ConstantConditions")
    private void saveItemData() {

        if (!dqProjectText.getText().isEmpty()) {

            estimateData[0] = dqProjectText.getText();
        }

        if (dateText.getValue() != null) {

            estimateData[1] = create.dateConverter(dateText.getValue().toString());
        }

        if (!invoiceText.getText().isEmpty()) {

            estimateData[2] = invoiceText.getText();
        }

        if (!companyNameText.getText().isEmpty()) {

            estimateData[3] = companyNameText.getText();
        }

        if (!addressText.getText().isEmpty()) {

            estimateData[4] = addressText.getText();
        }

        if (estimateSavedItemNumber >= 1) {

            estimateData[5] = item1Text.getText();
            estimateData[6] = description1Text.getText();
            estimateData[7] = qty1Text.getText();
            estimateData[8] = rate1Text.getText();
        }

        if (estimateSavedItemNumber >= 2) {

            estimateData[9] = item2Text.getText();
            estimateData[10] = description2Text.getText();
            estimateData[11] = qty2Text.getText();
            estimateData[12] = rate2Text.getText();
        }

        if (estimateSavedItemNumber >= 3) {

            estimateData[13] = item3Text.getText();
            estimateData[14] = description3Text.getText();
            estimateData[15] = qty3Text.getText();
            estimateData[16] = rate3Text.getText();
        }

        if (estimateSavedItemNumber >= 4) {

            estimateData[17] = item4Text.getText();
            estimateData[18] = description4Text.getText();
            estimateData[19] = qty4Text.getText();
            estimateData[20] = rate4Text.getText();
        }

        if (estimateSavedItemNumber >= 5) {

            estimateData[21] = item5Text.getText();
            estimateData[22] = description5Text.getText();
            estimateData[23] = qty5Text.getText();
            estimateData[24] = rate5Text.getText();
        }

        if (estimateSavedItemNumber >= 6) {

            estimateData[25] = item6Text.getText();
            estimateData[26] = description6Text.getText();
            estimateData[27] = qty6Text.getText();
            estimateData[28] = rate6Text.getText();
        }

        if (estimateSavedItemNumber >= 7) {

            estimateData[29] = item7Text.getText();
            estimateData[30] = description7Text.getText();
            estimateData[31] = qty7Text.getText();
            estimateData[32] = rate7Text.getText();
        }

        if (estimateSavedItemNumber >= 8) {

            estimateData[33] = item8Text.getText();
            estimateData[34] = description8Text.getText();
            estimateData[35] = qty8Text.getText();
            estimateData[36] = rate8Text.getText();
        }

        if (estimateSavedItemNumber >= 9) {

            estimateData[37] = item9Text.getText();
            estimateData[38] = description9Text.getText();
            estimateData[39] = qty9Text.getText();
            estimateData[40] = rate9Text.getText();
        }

        if (estimateSavedItemNumber >= 10) {

            estimateData[41] = item10Text.getText();
            estimateData[42] = description10Text.getText();
            estimateData[43] = qty10Text.getText();
            estimateData[44] = rate10Text.getText();
        }

        if (estimateSavedItemNumber >= 11) {

            estimateData[45] = item11Text.getText();
            estimateData[46] = description11Text.getText();
            estimateData[47] = qty11Text.getText();
            estimateData[48] = rate11Text.getText();
        }

        if (estimateSavedItemNumber >= 12) {

            estimateData[49] = item12Text.getText();
            estimateData[50] = description12Text.getText();
            estimateData[51] = qty12Text.getText();
            estimateData[52] = rate12Text.getText();
        }

        if (estimateSavedItemNumber >= 13) {

            estimateData[53] = item13Text.getText();
            estimateData[54] = description13Text.getText();
            estimateData[55] = qty13Text.getText();
            estimateData[56] = rate13Text.getText();
        }

        if (estimateSavedItemNumber >= 14) {

            estimateData[57] = item14Text.getText();
            estimateData[58] = description14Text.getText();
            estimateData[59] = qty14Text.getText();
            estimateData[60] = rate14Text.getText();
        }

        if (estimateSavedItemNumber == 15) {

            estimateData[61] = item15Text.getText();
            estimateData[62] = description15Text.getText();
            estimateData[63] = qty15Text.getText();
            estimateData[64] = rate15Text.getText();
        }
    }

    /* Method to set the data of each element within the Estimate form! */
    @SuppressWarnings("ConstantConditions")
    private void enterItemsData() {

        if (estimateItemNumber >= 1 && estimateItemNumber <= 15) {

            item1Text.setText(estimateData[5]);
            description1Text.setText(estimateData[6]);
            qty1Text.setText(estimateData[7]);
            rate1Text.setText(estimateData[8]);
        }

        if (estimateItemNumber >= 2 && estimateItemNumber <= 15) {

            item2Text.setText(estimateData[9]);
            description2Text.setText(estimateData[10]);
            qty2Text.setText(estimateData[11]);
            rate2Text.setText(estimateData[12]);
        }

        if (estimateItemNumber >= 3 && estimateItemNumber <= 15) {

            item3Text.setText(estimateData[13]);
            description3Text.setText(estimateData[14]);
            qty3Text.setText(estimateData[15]);
            rate3Text.setText(estimateData[16]);
        }

        if (estimateItemNumber >= 4 && estimateItemNumber <= 15) {

            item4Text.setText(estimateData[17]);
            description4Text.setText(estimateData[18]);
            qty4Text.setText(estimateData[19]);
            rate4Text.setText(estimateData[20]);
        }

        if (estimateItemNumber >= 5 && estimateItemNumber <= 15) {

            item5Text.setText(estimateData[21]);
            description5Text.setText(estimateData[22]);
            qty5Text.setText(estimateData[23]);
            rate5Text.setText(estimateData[24]);
        }

        if (estimateItemNumber >= 6 && estimateItemNumber <= 15) {

            item6Text.setText(estimateData[25]);
            description6Text.setText(estimateData[26]);
            qty6Text.setText(estimateData[27]);
            rate6Text.setText(estimateData[28]);
        }

        if (estimateItemNumber >= 7 && estimateItemNumber <= 15) {

            item7Text.setText(estimateData[29]);
            description7Text.setText(estimateData[30]);
            qty7Text.setText(estimateData[31]);
            rate7Text.setText(estimateData[32]);
        }

        if (estimateItemNumber >= 8 && estimateItemNumber <= 15) {

            item8Text.setText(estimateData[33]);
            description8Text.setText(estimateData[34]);
            qty8Text.setText(estimateData[35]);
            rate8Text.setText(estimateData[36]);
        }

        if (estimateItemNumber >= 9 && estimateItemNumber <= 15) {

            item9Text.setText(estimateData[37]);
            description9Text.setText(estimateData[38]);
            qty9Text.setText(estimateData[39]);
            rate9Text.setText(estimateData[40]);
        }

        if (estimateItemNumber >= 10 && estimateItemNumber <= 15) {

            item10Text.setText(estimateData[41]);
            description10Text.setText(estimateData[42]);
            qty10Text.setText(estimateData[43]);
            rate10Text.setText(estimateData[44]);
        }

        if (estimateItemNumber >= 11 && estimateItemNumber <= 15) {

            item11Text.setText(estimateData[45]);
            description11Text.setText(estimateData[46]);
            qty11Text.setText(estimateData[47]);
            rate11Text.setText(estimateData[48]);
        }

        if (estimateItemNumber >= 12 && estimateItemNumber <= 15) {

            item12Text.setText(estimateData[49]);
            description12Text.setText(estimateData[50]);
            qty12Text.setText(estimateData[51]);
            rate12Text.setText(estimateData[52]);
        }

        if (estimateItemNumber >= 13 && estimateItemNumber <= 15) {

            item13Text.setText(estimateData[53]);
            description13Text.setText(estimateData[54]);
            qty13Text.setText(estimateData[55]);
            rate13Text.setText(estimateData[56]);
        }

        if (estimateItemNumber >= 14 && estimateItemNumber <= 15) {

            item14Text.setText(estimateData[57]);
            description14Text.setText(estimateData[58]);
            qty14Text.setText(estimateData[59]);
            rate14Text.setText(estimateData[60]);
        }

        if (estimateItemNumber == 15) {

            item15Text.setText(estimateData[61]);
            description15Text.setText(estimateData[62]);
            qty15Text.setText(estimateData[63]);
            rate15Text.setText(estimateData[64]);
        }
    }

    /* Method to delete the needed information out of the array that stores entered information within the Estimate form.
     * Method will either delete a certain set of information or delete all information! */
    private void deleteItemData(boolean deleteThis) {

        if (!deleteThis) {

            for (int x = ((estimateItemNumber * 4) + 5); x < ((estimateSavedItemNumber * 4) + 5); x++) {

                estimateData[x] = "";

            }
        }

        if (deleteThis) {

            for (int y = 0; y <= 64; y++) {

                estimateData[y] = "";

            }
        }
    }

    /* Method that initializes the array for storing entered information within the Estimate form! */
    private void itemsDataInit() {

        for (int x = 0; x < 64; x++) {

            estimateData[x] = "";

        }
    }

    /* Method that will create the Traveler form window! */
    private void travelerFormCreate() {

        travelerFormRunning = true;

        travelerProcessesVBox = new VBox(5);
        travelerProcessesVBox.setPadding(new Insets(4));

        Label formDescription = new Label("Traveler Form");
        formDescription.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        HBox formLabel = new HBox(5);
        formLabel.setPadding(new Insets(5));
        formLabel.getChildren().add(formDescription);
        formLabel.setAlignment(Pos.CENTER);

        Label jobNumber = new Label("Job #:");
        jobNumberText = new TextField();
        jobNumberText.setPrefWidth(100);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(jobNumberText, autoCompleteList);
        }

        Label dateReceived = new Label("Date Received:");
        dateReceivedPicker = new DatePicker();
        dateReceivedPicker.setPrefWidth(130);
        dateReceivedPicker.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        Label customer = new Label("Customer:");
        customerText = new TextField();
        customerText.setPrefWidth(100);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(customerText, autoCompleteList);
        }

        HBox travelerFormRow1 = new HBox(15);
        travelerFormRow1.setPadding(new Insets(5));
        travelerFormRow1.setAlignment(Pos.CENTER_LEFT);
        travelerFormRow1.getChildren().addAll(jobNumber, jobNumberText, dateReceived, dateReceivedPicker, customer, customerText);

        Label quantity = new Label("Quantity:");
        quantityText = new TextField();
        quantityText.setPrefWidth(100);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(quantityText, autoCompleteList);
        }

        Label dateRequired = new Label("Date Required:");
        dateRequiredPicker = new DatePicker();
        dateRequiredPicker.setPrefWidth(130);
        dateRequiredPicker.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        Label partNumber = new Label("Part #:");
        partNumberText = new TextField();
        partNumberText.setPrefWidth(100);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(partNumberText, autoCompleteList);
        }

        HBox travelerFormRow2 = new HBox(15);
        travelerFormRow2.setPadding(new Insets(5));
        travelerFormRow2.setAlignment(Pos.CENTER_LEFT);
        travelerFormRow2.getChildren().addAll(quantity, quantityText, dateRequired, dateRequiredPicker, partNumber, partNumberText);

        Label poNumber = new Label("P.O.#:");
        poNumberText = new TextField();
        poNumberText.setPrefWidth(100);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(poNumberText, autoCompleteList);
        }

        Label completedDate = new Label("Completed Date:");
        completedDatePicker = new DatePicker();
        completedDatePicker.setPrefWidth(130);
        completedDatePicker.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        HBox travelerFormRow3 = new HBox(15);
        travelerFormRow3.setPadding(new Insets(5));
        travelerFormRow3.setAlignment(Pos.CENTER_LEFT);
        travelerFormRow3.getChildren().addAll(poNumber, poNumberText, completedDate, completedDatePicker);

        Label notes = new Label("Notes:");
        notesTextAreaTraveler = new TextArea();
        notesTextAreaTraveler.setWrapText(true);
        notesTextAreaTraveler.setPrefWidth(500);
        notesTextAreaTraveler.setPrefHeight(80);
        notesTextAreaTraveler.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        HBox notesHBox = new HBox(5);
        notesHBox.getChildren().addAll(notes, notesTextAreaTraveler);
        notesHBox.setAlignment(Pos.CENTER_LEFT);

        Label processes = new Label("Number of Processes:");
        processesNumberText = new TextField();
        processesNumberText.setPrefWidth(50);

        Button numberOfProcessesButton = new Button("Enter");
        numberOfProcessesButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        numberOfProcessesButton.setOnAction(e -> {

            if (processesNumberText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for the Process Number");

            } else {

                checkNumberFormat = functions.checkIntegerFormat(processesNumberText.getText());

                if (checkNumberFormat) {

                    /* Variable for the Process number entered, stores the number entered int the travelerProcessNumber variable! */
                    travelerProcessNumber = Integer.parseInt(processesNumberText.getText());

                    if (travelerProcessNumber <= 9 && travelerProcessNumber != 0) {

                        if (travelerProcessNumber >= travelerSavedProcessNumber && travelerProcessesGenerated) {

                            saveTravelerData();

                        } else {

                            saveTravelerData();
                            deleteTravelerData(false);

                            if (travelerProcessesGenerated) {

                                enterTravelerData();

                                /* Will re-calculate the total hours of each process when process number is decreased! */
                                travelerHoursCalculate();
                            }
                        }

                        if (travelerProcessNumber <= 9) {

                            travelerSavedProcessNumber = travelerProcessNumber;
                        }

                    } else if (travelerProcessNumber == 0) {

                        processesNumberText.setText(String.valueOf(travelerSavedProcessNumber));

                        travelerMessages.setText("Process number cannot be 0");

                    } else {

                        travelerMessages.setText("Process number entered was greater than the limit of 9");

                    }

                } else {

                    saveTravelerData();
                    travelerMessages.setText("Number of processes was enter wrong");
                }
            }

            if (travelerProcessNumber > 0 && travelerProcessNumber <= 9) {

                travelerProcessesVBox.getChildren().removeAll(travelerProcess1HBox, travelerProcess2HBox, travelerProcess3HBox, travelerProcess4HBox, travelerProcess5HBox, travelerProcess6HBox, travelerProcess7HBox, travelerProcess8HBox, travelerProcess9HBox);
            }

            if (travelerProcessNumber >= 1 && travelerProcessNumber <= 9) {

                Label process1DescriptionLabel = new Label("Description:");
                process1DescriptionText = new TextField();
                process1DescriptionText.setPrefWidth(150.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process1DescriptionText, autoCompleteList);
                }
                Label process1PlannedHoursLabel = new Label("Planned Hours:");
                process1PlannedHoursText = new TextField();
                process1PlannedHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process1PlannedHoursText, autoCompleteList);
                }
                Label process1ActualHoursLabel = new Label("Actual Hours:");
                process1ActualHoursText = new TextField();
                process1ActualHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process1ActualHoursText, autoCompleteList);
                }
                Label process1CompletedLabel = new Label("Completed:");
                process1CompletedText = new TextField();
                process1CompletedText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process1CompletedText, autoCompleteList);
                }

                travelerProcess1HBox = new HBox(8);
                travelerProcess1HBox.getChildren().addAll(process1DescriptionLabel, process1DescriptionText, process1PlannedHoursLabel, process1PlannedHoursText, process1ActualHoursLabel, process1ActualHoursText, process1CompletedLabel, process1CompletedText);

                travelerProcessesVBox.getChildren().add(travelerProcess1HBox);
            }

            if (travelerProcessNumber >= 2 && travelerProcessNumber <= 9) {

                Label process2DescriptionLabel = new Label("Description:");
                process2DescriptionText = new TextField();
                process2DescriptionText.setPrefWidth(150.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process2DescriptionText, autoCompleteList);
                }
                Label process2PlannedHoursLabel = new Label("Planned Hours:");
                process2PlannedHoursText = new TextField();
                process2PlannedHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process2PlannedHoursText, autoCompleteList);
                }
                Label process2ActualHoursLabel = new Label("Actual Hours:");
                process2ActualHoursText = new TextField();
                process2ActualHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process2ActualHoursText, autoCompleteList);
                }
                Label process2CompletedLabel = new Label("Completed:");
                process2CompletedText = new TextField();
                process2CompletedText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process2CompletedText, autoCompleteList);
                }

                travelerProcess2HBox = new HBox(8);
                travelerProcess2HBox.getChildren().addAll(process2DescriptionLabel, process2DescriptionText, process2PlannedHoursLabel, process2PlannedHoursText, process2ActualHoursLabel, process2ActualHoursText, process2CompletedLabel, process2CompletedText);

                travelerProcessesVBox.getChildren().add(travelerProcess2HBox);
            }

            if (travelerProcessNumber >= 3 && travelerProcessNumber <= 9) {

                Label process3DescriptionLabel = new Label("Description:");
                process3DescriptionText = new TextField();
                process3DescriptionText.setPrefWidth(150.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process3DescriptionText, autoCompleteList);
                }
                Label process3PlannedHoursLabel = new Label("Planned Hours:");
                process3PlannedHoursText = new TextField();
                process3PlannedHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process3PlannedHoursText, autoCompleteList);
                }
                Label process3ActualHoursLabel = new Label("Actual Hours:");
                process3ActualHoursText = new TextField();
                process3ActualHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process3ActualHoursText, autoCompleteList);
                }
                Label process3CompletedLabel = new Label("Completed:");
                process3CompletedText = new TextField();
                process3CompletedText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process3CompletedText, autoCompleteList);
                }

                travelerProcess3HBox = new HBox(8);
                travelerProcess3HBox.getChildren().addAll(process3DescriptionLabel, process3DescriptionText, process3PlannedHoursLabel, process3PlannedHoursText, process3ActualHoursLabel, process3ActualHoursText, process3CompletedLabel, process3CompletedText);

                travelerProcessesVBox.getChildren().add(travelerProcess3HBox);
            }

            if (travelerProcessNumber >= 4 && travelerProcessNumber <= 9) {

                Label process4DescriptionLabel = new Label("Description:");
                process4DescriptionText = new TextField();
                process4DescriptionText.setPrefWidth(150.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process4DescriptionText, autoCompleteList);
                }
                Label process4PlannedHoursLabel = new Label("Planned Hours:");
                process4PlannedHoursText = new TextField();
                process4PlannedHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process4PlannedHoursText, autoCompleteList);
                }
                Label process4ActualHoursLabel = new Label("Actual Hours:");
                process4ActualHoursText = new TextField();
                process4ActualHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process4ActualHoursText, autoCompleteList);
                }
                Label process4CompletedLabel = new Label("Completed:");
                process4CompletedText = new TextField();
                process4CompletedText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process4CompletedText, autoCompleteList);
                }

                travelerProcess4HBox = new HBox(8);
                travelerProcess4HBox.getChildren().addAll(process4DescriptionLabel, process4DescriptionText, process4PlannedHoursLabel, process4PlannedHoursText, process4ActualHoursLabel, process4ActualHoursText, process4CompletedLabel, process4CompletedText);

                travelerProcessesVBox.getChildren().add(travelerProcess4HBox);
            }

            if (travelerProcessNumber >= 5 && travelerProcessNumber <= 9) {

                Label process5DescriptionLabel = new Label("Description:");
                process5DescriptionText = new TextField();
                process5DescriptionText.setPrefWidth(150.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process5DescriptionText, autoCompleteList);
                }
                Label process5PlannedHoursLabel = new Label("Planned Hours:");
                process5PlannedHoursText = new TextField();
                process5PlannedHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process5PlannedHoursText, autoCompleteList);
                }
                Label process5ActualHoursLabel = new Label("Actual Hours:");
                process5ActualHoursText = new TextField();
                process5ActualHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process5ActualHoursText, autoCompleteList);
                }
                Label process5CompletedLabel = new Label("Completed:");
                process5CompletedText = new TextField();
                process5CompletedText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process5CompletedText, autoCompleteList);
                }

                travelerProcess5HBox = new HBox(8);
                travelerProcess5HBox.getChildren().addAll(process5DescriptionLabel, process5DescriptionText, process5PlannedHoursLabel, process5PlannedHoursText, process5ActualHoursLabel, process5ActualHoursText, process5CompletedLabel, process5CompletedText);

                travelerProcessesVBox.getChildren().add(travelerProcess5HBox);
            }

            if (travelerProcessNumber >= 6 && travelerProcessNumber <= 9) {

                Label process6DescriptionLabel = new Label("Description:");
                process6DescriptionText = new TextField();
                process6DescriptionText.setPrefWidth(150.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process6DescriptionText, autoCompleteList);
                }
                Label process6PlannedHoursLabel = new Label("Planned Hours:");
                process6PlannedHoursText = new TextField();
                process6PlannedHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process6PlannedHoursText, autoCompleteList);
                }
                Label process6ActualHoursLabel = new Label("Actual Hours:");
                process6ActualHoursText = new TextField();
                process6ActualHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process6ActualHoursText, autoCompleteList);
                }
                Label process6CompletedLabel = new Label("Completed:");
                process6CompletedText = new TextField();
                process6CompletedText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process6CompletedText, autoCompleteList);
                }

                travelerProcess6HBox = new HBox(8);
                travelerProcess6HBox.getChildren().addAll(process6DescriptionLabel, process6DescriptionText, process6PlannedHoursLabel, process6PlannedHoursText, process6ActualHoursLabel, process6ActualHoursText, process6CompletedLabel, process6CompletedText);

                travelerProcessesVBox.getChildren().add(travelerProcess6HBox);
            }

            if (travelerProcessNumber >= 7 && travelerProcessNumber <= 9) {

                Label process7DescriptionLabel = new Label("Description:");
                process7DescriptionText = new TextField();
                process7DescriptionText.setPrefWidth(150.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process7DescriptionText, autoCompleteList);
                }
                Label process7PlannedHoursLabel = new Label("Planned Hours:");
                process7PlannedHoursText = new TextField();
                process7PlannedHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process7PlannedHoursText, autoCompleteList);
                }
                Label process7ActualHoursLabel = new Label("Actual Hours:");
                process7ActualHoursText = new TextField();
                process7ActualHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process7ActualHoursText, autoCompleteList);
                }
                Label process7CompletedLabel = new Label("Completed:");
                process7CompletedText = new TextField();
                process7CompletedText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process7CompletedText, autoCompleteList);
                }

                travelerProcess7HBox = new HBox(8);
                travelerProcess7HBox.getChildren().addAll(process7DescriptionLabel, process7DescriptionText, process7PlannedHoursLabel, process7PlannedHoursText, process7ActualHoursLabel, process7ActualHoursText, process7CompletedLabel, process7CompletedText);

                travelerProcessesVBox.getChildren().add(travelerProcess7HBox);
            }

            if (travelerProcessNumber >= 8 && travelerProcessNumber <= 9) {

                Label process8DescriptionLabel = new Label("Description:");
                process8DescriptionText = new TextField();
                process8DescriptionText.setPrefWidth(150.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process8DescriptionText, autoCompleteList);
                }
                Label process8PlannedHoursLabel = new Label("Planned Hours:");
                process8PlannedHoursText = new TextField();
                process8PlannedHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process8PlannedHoursText, autoCompleteList);
                }
                Label process8ActualHoursLabel = new Label("Actual Hours:");
                process8ActualHoursText = new TextField();
                process8ActualHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process8ActualHoursText, autoCompleteList);
                }
                Label process8CompletedLabel = new Label("Completed:");
                process8CompletedText = new TextField();
                process8CompletedText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process8CompletedText, autoCompleteList);
                }

                travelerProcess8HBox = new HBox(8);
                travelerProcess8HBox.getChildren().addAll(process8DescriptionLabel, process8DescriptionText, process8PlannedHoursLabel, process8PlannedHoursText, process8ActualHoursLabel, process8ActualHoursText, process8CompletedLabel, process8CompletedText);

                travelerProcessesVBox.getChildren().add(travelerProcess8HBox);
            }

            if (travelerProcessNumber == 9) {

                Label process9DescriptionLabel = new Label("Description:");
                process9DescriptionText = new TextField();
                process9DescriptionText.setPrefWidth(150.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process9DescriptionText, autoCompleteList);
                }
                Label process9PlannedHoursLabel = new Label("Planned Hours:");
                process9PlannedHoursText = new TextField();
                process9PlannedHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process9PlannedHoursText, autoCompleteList);
                }
                Label process9ActualHoursLabel = new Label("Actual Hours:");
                process9ActualHoursText = new TextField();
                process9ActualHoursText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process9ActualHoursText, autoCompleteList);
                }
                Label process9CompletedLabel = new Label("Completed:");
                process9CompletedText = new TextField();
                process9CompletedText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(process9CompletedText, autoCompleteList);
                }

                travelerProcess9HBox = new HBox(8);
                travelerProcess9HBox.getChildren().addAll(process9DescriptionLabel, process9DescriptionText, process9PlannedHoursLabel, process9PlannedHoursText, process9ActualHoursLabel, process9ActualHoursText, process9CompletedLabel, process9CompletedText);

                travelerProcessesVBox.getChildren().add(travelerProcess9HBox);
            }

            if (travelerProcessesGenerated) {

                enterTravelerData();
            }

            travelerProcessesGenerated = true;

        });

        HBox travelerFormRow4 = new HBox(5);
        travelerFormRow4.setPadding(new Insets(10));
        travelerFormRow4.setAlignment(Pos.CENTER_RIGHT);
        travelerFormRow4.getChildren().addAll(processes, processesNumberText, numberOfProcessesButton);

        /* Setup for error message label! */
        travelerMessages = new Label();
        travelerMessages.setTextFill(Color.web("#f73636"));
        travelerMessages.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        travelerMessages.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!Objects.equals(newValue, "")) {

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> travelerMessages.setText(""));
                    }
                }, 5000);
            }
        });

        travelerMessages.setOnMouseEntered(event -> {
            travelerMessages.setScaleX(1.20);
            travelerMessages.setScaleY(1.20);
        });

        travelerMessages.setOnMouseExited(event -> {
            travelerMessages.setScaleX(1.00);
            travelerMessages.setScaleY(1.00);
        });

        /* Setup for the errorMessages VBox that displays errors within the Traveler form! */
        VBox errorMessages = new VBox(8);
        errorMessages.setPadding(new Insets(4));
        errorMessages.setAlignment(Pos.BOTTOM_CENTER);
        errorMessages.getChildren().add(travelerMessages);
        errorMessages.setStyle("-fx-font: 14 arial; -fx-base: #f73636;");

        Label partTime = new Label("Part Time:");
        partTime.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        plannedHoursTotalPartTime = new Label("0.00");
        plannedHoursTotalPartTime.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        Label totalTime = new Label("Total Time:");
        totalTime.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        plannedHoursTotalTotalTime = new Label("0.00");
        plannedHoursTotalTotalTime.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        HBox componentCompletionTimeVBoxRow1 = new HBox(5);
        componentCompletionTimeVBoxRow1.getChildren().addAll(partTime, plannedHoursTotalPartTime);
        componentCompletionTimeVBoxRow1.setAlignment(Pos.CENTER_LEFT);

        HBox componentCompletionTimeVBoxRow2Left = new HBox(5);
        componentCompletionTimeVBoxRow2Left.getChildren().addAll(totalTime, plannedHoursTotalTotalTime);
        componentCompletionTimeVBoxRow2Left.setAlignment(Pos.CENTER_LEFT);

        Label calculateTotalPartTime = new Label("Calculate Time:");
        calculateTotalPartTime.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        Button calculateTimeButton = new Button("Calculate");
        calculateTimeButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        calculateTimeButton.setOnAction(e -> travelerCalculationReturn = travelerHoursCalculate());

        HBox componentCompletionTimeVBoxRow2Right = new HBox(5);
        componentCompletionTimeVBoxRow2Right.getChildren().addAll(calculateTotalPartTime, calculateTimeButton);
        componentCompletionTimeVBoxRow2Right.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(componentCompletionTimeVBoxRow2Right, Priority.ALWAYS);

        HBox componentCompletionTimeVBoxRow2 = new HBox(5);
        componentCompletionTimeVBoxRow2.getChildren().addAll(componentCompletionTimeVBoxRow2Left, componentCompletionTimeVBoxRow2Right);

        VBox componentCompletionTimeVBox = new VBox(5);
        componentCompletionTimeVBox.setPadding(new Insets(4));
        componentCompletionTimeVBox.getChildren().addAll(componentCompletionTimeVBoxRow1, componentCompletionTimeVBoxRow2);

        /* Conditions for the Close button to close the Traveler window! */
        Button closeButton = new Button("Cancel");
        closeButton.setStyle("-fx-font: 13 arial; -fx-base: #C0C0C0;");
        closeButton.setOnAction((ActionEvent e) -> {

            double centerXPosition = window.getX();
            double centerYPosition = window.getY();
            double primaryStageWidth = window.getWidth();
            double primaryStageHeight = window.getHeight();

            boolean cancelForm = confirmProgramClose.cancelAcknowledged("Traveler Form", centerXPosition, centerYPosition, primaryStageWidth, primaryStageHeight);

            if (cancelForm) {

                travelerProcessNumber = 0;
                travelerProcessesGenerated = false;
                deleteTravelerData(true);

                content.getChildren().removeAll(travelerScrollPane);
                content.getChildren().add(contentPrompt);
                content.setAlignment(Pos.CENTER);
                contentPrompt.setText("Form Canceled");

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> contentPrompt.setText("Select a form to complete"));
                    }
                }, 5000);

                travelerFormRunning = false;
            }
        });

        /* Conditions for the Save button to save the entered information for the Traveler form! */
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        saveButton.setOnAction((ActionEvent e) -> {

            checkTravelerDataInput = checkTravelerDataInput();

            if (checkTravelerDataInput) {

                travelerCalculationReturn = travelerHoursCalculate();

                if (travelerCalculationReturn) {

                    saveTravelerData();

                    File saveFile = saveFileChooser(travelerData[0], "Save", "Traveler");

                    if (saveFile != null) {

                        try {

                            Statement statementInsertAuto = connection.createStatement();
                            statementInsertAuto.setQueryTimeout(10);

                            for (String aTravelerData : travelerData) {

                                if (!Objects.equals(aTravelerData, "") && aTravelerData != null) {

                                    boolean passed = true;

                                    for (String anAutoCompleteList : autoCompleteList) {

                                        if (aTravelerData.equals(anAutoCompleteList)) {

                                            passed = false;
                                        }
                                    }

                                    if (passed) {

                                        String insertAuto = String.format("INSERT INTO AUTO (WORD) VALUES ('%s')", aTravelerData);
                                        statementInsertAuto.execute(insertAuto);
                                        autoCompleteList.add(aTravelerData);
                                    }
                                }
                            }

                            statementInsertAuto.close();

                        } catch ( Exception e1 ) {
                            e1.printStackTrace();
                        }

                        inputWorkbookData.travelerExcel(travelerData, travelerSavedProcessNumber, saveFile.getAbsolutePath());

                        URL notificationImage = this.getClass().getResource("/sample/CustomerFiles/Images/Saved.gif");

                        TrayNotification notification = new TrayNotification("Traveler Formed Saved", "Saved To: " + saveFile.getAbsolutePath(), NotificationType.SUCCESS);
                        notification.setImage(new Image(String.valueOf(notificationImage)));
                        notification.setRectangleFill(Paint.valueOf("#46494f"));
                        notification.showAndDismiss(Duration.seconds(4));

                        travelerProcessNumber = 0;
                        travelerProcessesGenerated = false;
                        deleteTravelerData(true);

                        content.getChildren().removeAll(travelerScrollPane);
                        content.getChildren().add(contentPrompt);
                        content.setAlignment(Pos.CENTER);
                        contentPrompt.setText("Form Saved");

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> contentPrompt.setText("Select a form to complete"));
                                try {
                                    Desktop.getDesktop().open(new File(saveFile.getAbsolutePath()));
                                } catch ( IOException e1 ) {
                                    e1.printStackTrace();
                                }
                            }
                        }, 5000);

                        travelerFormRunning = false;
                    }
                }
            }
        });

        HBox bottom = new HBox(5);
        bottom.getChildren().addAll(closeButton, saveButton);
        bottom.setPadding(new Insets(5));
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.setMinHeight(65);

        VBox travelerLayout = new VBox(5);
        travelerLayout.setPadding(new Insets(4));
        travelerLayout.getChildren().addAll(formLabel, travelerFormRow1, travelerFormRow2, travelerFormRow3, notesHBox, travelerFormRow4, travelerProcessesVBox, errorMessages, componentCompletionTimeVBox, bottom);

        travelerScrollPane = new ScrollPane();
        travelerScrollPane.setContent(travelerLayout);
        travelerScrollPane.setFitToWidth(true);
        travelerScrollPane.setPrefHeight(window.heightProperty().doubleValue());

    }

    /* Method that will check the inputted data within the Traveler form for user error! */
    private boolean checkTravelerDataInput() {

        checkTravelerDataInput = false;

        if (processesNumberText.getText().isEmpty()) {

            travelerMessages.setText("Nothing was ever entered for the Number of Processes");

        } else if (travelerProcessNumber == 0) {

            travelerMessages.setText("You must actually enter a number for the number of Processes");

        } else if (jobNumberText.getText().isEmpty()) {

            travelerMessages.setText("Nothing was entered for Job Number");

        } else if (dateReceivedPicker.getValue() == null) {

            travelerMessages.setText("Date received date entered Incorrectly or not at all");

        } else if (customerText.getText().isEmpty()) {

            travelerMessages.setText("Nothing was entered for Customer");

        } else if (quantityText.getText().isEmpty()) {

            travelerMessages.setText("Nothing was entered for Quantity");

        } else if (partNumberText.getText().isEmpty()) {

            travelerMessages.setText("Nothing was entered for Part Number");

        } else if (dateRequiredPicker.getValue() == null) {

            travelerMessages.setText("Date required date entered Incorrectly or not at all");

        } else if (poNumberText.getText().isEmpty()) {

            travelerMessages.setText("Nothing was entered for P.O. Number");

        } else if ((travelerProcessNumber >= 1 && travelerProcessNumber <= 9) && (process1DescriptionText.getText().isEmpty() || process1PlannedHoursText.getText().isEmpty())) {

            if (process1DescriptionText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for Process 1 Description");

            } else if (process1PlannedHoursText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was enter for Process 1 Planned Hours");
            }

        } else if ((travelerProcessNumber >= 2 && travelerProcessNumber <= 9) && (process2DescriptionText.getText().isEmpty() || process2PlannedHoursText.getText().isEmpty())) {

            if (process2DescriptionText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for Process 2 Description");

            } else if (process2PlannedHoursText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was enter for Process 2 Planned Hours");
            }

        } else if ((travelerProcessNumber >= 3 && travelerProcessNumber <= 9) && (process3DescriptionText.getText().isEmpty() || process3PlannedHoursText.getText().isEmpty())) {

            if (process3DescriptionText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for Process 3 Description");

            } else if (process3PlannedHoursText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was enter for Process 3 Planned Hours");
            }

        } else if ((travelerProcessNumber >= 4 && travelerProcessNumber <= 9) && (process4DescriptionText.getText().isEmpty() || process4PlannedHoursText.getText().isEmpty())) {

            if (process4DescriptionText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for Process 4 Description");

            } else if (process4PlannedHoursText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was enter for Process 4 Planned Hours");
            }

        } else if ((travelerProcessNumber >= 5 && travelerProcessNumber <= 9) && (process5DescriptionText.getText().isEmpty() || process5PlannedHoursText.getText().isEmpty())) {

            if (process5DescriptionText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for Process 5 Description");

            } else if (process5PlannedHoursText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was enter for Process 5 Planned Hours");
            }

        } else if ((travelerProcessNumber >= 6 && travelerProcessNumber <= 9) && (process6DescriptionText.getText().isEmpty() || process6PlannedHoursText.getText().isEmpty())) {

            if (process6DescriptionText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for Process 6 Description");

            } else if (process6PlannedHoursText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was enter for Process 6 Planned Hours");
            }

        } else if ((travelerProcessNumber >= 7 && travelerProcessNumber <= 9) && (process7DescriptionText.getText().isEmpty() || process7PlannedHoursText.getText().isEmpty())) {

            if (process7DescriptionText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for Process 7 Description");

            } else if (process7PlannedHoursText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was enter for Process 7 Planned Hours");
            }

        } else if ((travelerProcessNumber >= 8 && travelerProcessNumber <= 9) && (process8DescriptionText.getText().isEmpty() || process8PlannedHoursText.getText().isEmpty())) {

            if (process8DescriptionText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for Process 8 Description");

            } else if (process8PlannedHoursText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was enter for Process 8 Planned Hours");
            }

        } else if (travelerProcessNumber == 9 && (process9DescriptionText.getText().isEmpty() || process9PlannedHoursText.getText().isEmpty())) {

            if (process9DescriptionText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for Process 9 Description");

            } else if (process9PlannedHoursText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was enter for Process 9 Planned Hours");
            }

        } else {

            checkTravelerDataInput = true;
        }

        if (!jobNumberText.getText().isEmpty() && checkTravelerDataInput) {

            checkCurrencyFormat = functions.checkNumberFormat(jobNumberText.getText());

            if (!checkCurrencyFormat) {

                travelerMessages.setText("Number wasn't entered correctly for Job Number");
                checkTravelerDataInput = false;
            }
        }

        if (!partNumberText.getText().isEmpty() && checkTravelerDataInput) {

            checkCurrencyFormat = functions.checkNumberFormat(partNumberText.getText());

            if (!checkCurrencyFormat) {

                travelerMessages.setText("Number wasn't entered correctly for Part Number");
                checkTravelerDataInput = false;
            }
        }

        if (!poNumberText.getText().isEmpty() && checkTravelerDataInput) {

            checkCurrencyFormat = functions.checkNumberFormat(poNumberText.getText());

            if (!checkCurrencyFormat) {

                travelerMessages.setText("Number wasn't entered correctly for P.O. Number");
                checkTravelerDataInput = false;
            }
        }

        return checkTravelerDataInput;
    }

    /* Method that will calculate the total hours needed for a process. Method also checks for user error on the inputted hour numbers! */
    private boolean travelerHoursCalculate() {

        Double hoursTotal = 0.0;

        boolean travelerCalculationCheck = true;

        if (!quantityText.getText().isEmpty()) {

            checkCurrencyFormat = functions.checkNumberFormat(quantityText.getText());

            if (checkCurrencyFormat) {

                if (travelerProcessNumber != 0) {

                    if ((travelerProcessNumber >= 1 && travelerProcessNumber <= 9) && !process1PlannedHoursText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(process1PlannedHoursText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(process1PlannedHoursText.getText());

                            if (checkCurrencyFormat) {

                                hoursTotal = (hoursTotal + Double.parseDouble(process1PlannedHoursText.getText()));

                            } else {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was formatted incorrectly for Process 1 Planned Hours");
                            }

                        } else {

                            if (!process1PlannedHoursText.getText().isEmpty()) {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was entered wrong for Process 1 Planned Hours");
                            }
                        }

                    } else if (travelerProcessNumber >= 1) {

                        if (process1PlannedHoursText.getText().isEmpty() && travelerCalculationCheck) {

                            travelerCalculationCheck = false;
                            travelerMessages.setText("Nothing was entered for Process 1 Planned Hours");
                        }
                    }

                    if ((travelerProcessNumber >= 2 && travelerProcessNumber <= 9) && !process2PlannedHoursText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(process2PlannedHoursText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(process2PlannedHoursText.getText());

                            if (checkCurrencyFormat) {

                                hoursTotal = (hoursTotal + Double.parseDouble(process2PlannedHoursText.getText()));

                            } else {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was formatted incorrectly for Process 2 Planned Hours");
                            }

                        } else {

                            if (!process2PlannedHoursText.getText().isEmpty()) {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was entered wrong for Process 2 Planned Hours");
                            }
                        }

                    } else if (travelerProcessNumber >= 2) {

                        if (process2PlannedHoursText.getText().isEmpty() && travelerCalculationCheck) {

                            travelerCalculationCheck = false;
                            travelerMessages.setText("Nothing was entered for Process 2 Planned Hours");
                        }
                    }

                    if ((travelerProcessNumber >= 3 && travelerProcessNumber <= 9) && !process3PlannedHoursText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(process3PlannedHoursText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(process3PlannedHoursText.getText());

                            if (checkCurrencyFormat) {

                                hoursTotal = (hoursTotal + Double.parseDouble(process3PlannedHoursText.getText()));

                            } else {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was formatted incorrectly for Process 3 Planned Hours");
                            }

                        } else {

                            if (!process3PlannedHoursText.getText().isEmpty()) {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was entered wrong for Process 3 Planned Hours");
                            }
                        }

                    } else if (travelerProcessNumber >= 3) {

                        if (process3PlannedHoursText.getText().isEmpty() && travelerCalculationCheck) {

                            travelerCalculationCheck = false;
                            travelerMessages.setText("Nothing was entered for Process 3 Planned Hours");
                        }
                    }

                    if ((travelerProcessNumber >= 4 && travelerProcessNumber <= 9) && !process4PlannedHoursText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(process4PlannedHoursText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(process4PlannedHoursText.getText());

                            if (checkCurrencyFormat) {

                                hoursTotal = (hoursTotal + Double.parseDouble(process4PlannedHoursText.getText()));

                            } else {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was formatted incorrectly for Process 4 Planned Hours");
                            }

                        } else {

                            if (!process4PlannedHoursText.getText().isEmpty()) {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was entered wrong for Process 4 Planned Hours");
                            }
                        }

                    } else if (travelerProcessNumber >= 4) {

                        if (process4PlannedHoursText.getText().isEmpty() && travelerCalculationCheck) {

                            travelerCalculationCheck = false;
                            travelerMessages.setText("Nothing was entered for Process 4 Planned Hours");
                        }
                    }

                    if ((travelerProcessNumber >= 5 && travelerProcessNumber <= 9) && !process5PlannedHoursText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(process5PlannedHoursText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(process5PlannedHoursText.getText());

                            if (checkCurrencyFormat) {

                                hoursTotal = (hoursTotal + Double.parseDouble(process5PlannedHoursText.getText()));

                            } else {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was formatted incorrectly for Process 5 Planned Hours");
                            }

                        } else {

                            if (!process5PlannedHoursText.getText().isEmpty()) {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was entered wrong for Process 5 Planned Hours");
                            }
                        }

                    } else if (travelerProcessNumber >= 5) {

                        if (process5PlannedHoursText.getText().isEmpty() && travelerCalculationCheck) {

                            travelerCalculationCheck = false;
                            travelerMessages.setText("Nothing was entered for Process 5 Planned Hours");
                        }
                    }

                    if ((travelerProcessNumber >= 6 && travelerProcessNumber <= 9) && !process6PlannedHoursText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(process6PlannedHoursText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(process6PlannedHoursText.getText());

                            if (checkCurrencyFormat) {

                                hoursTotal = (hoursTotal + Double.parseDouble(process6PlannedHoursText.getText()));

                            } else {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was formatted incorrectly for Process 6 Planned Hours");
                            }

                        } else {

                            if (!process6PlannedHoursText.getText().isEmpty()) {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was entered wrong for Process 6 Planned Hours");
                            }
                        }

                    } else if (travelerProcessNumber >= 6) {

                        if (process6PlannedHoursText.getText().isEmpty() && travelerCalculationCheck) {

                            travelerCalculationCheck = false;
                            travelerMessages.setText("Nothing was entered for Process 6 Planned Hours");
                        }
                    }

                    if ((travelerProcessNumber >= 7 && travelerProcessNumber <= 9) && !process7PlannedHoursText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(process7PlannedHoursText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(process7PlannedHoursText.getText());

                            if (checkCurrencyFormat) {

                                hoursTotal = (hoursTotal + Double.parseDouble(process7PlannedHoursText.getText()));

                            } else {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was formatted incorrectly for Process 7 Planned Hours");
                            }

                        } else {

                            if (!process7PlannedHoursText.getText().isEmpty()) {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was entered wrong for Process 7 Planned Hours");
                            }
                        }

                    } else if (travelerProcessNumber >= 7) {

                        if (process7PlannedHoursText.getText().isEmpty() && travelerCalculationCheck) {

                            travelerCalculationCheck = false;
                            travelerMessages.setText("Nothing was entered for Process 7 Planned Hours");
                        }
                    }

                    if ((travelerProcessNumber >= 8 && travelerProcessNumber <= 9) && !process8PlannedHoursText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(process8PlannedHoursText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(process8PlannedHoursText.getText());

                            if (checkCurrencyFormat) {

                                hoursTotal = (hoursTotal + Double.parseDouble(process8PlannedHoursText.getText()));

                            } else {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was formatted incorrectly for Process 8 Planned Hours");
                            }

                        } else {

                            if (!process8PlannedHoursText.getText().isEmpty()) {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was entered wrong for Process 8 Planned Hours");
                            }
                        }

                    } else if (travelerProcessNumber >= 8) {

                        if (process8PlannedHoursText.getText().isEmpty() && travelerCalculationCheck) {

                            travelerCalculationCheck = false;
                            travelerMessages.setText("Nothing was entered for Process 8 Planned Hours");
                        }
                    }

                    if (travelerProcessNumber == 9 && !process9PlannedHoursText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(process9PlannedHoursText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(process9PlannedHoursText.getText());

                            if (checkCurrencyFormat) {

                                hoursTotal = (hoursTotal + Double.parseDouble(process9PlannedHoursText.getText()));

                            } else {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was formatted incorrectly for Process 9 Planned Hours");
                            }

                        } else {

                            if (!process9PlannedHoursText.getText().isEmpty()) {

                                travelerCalculationCheck = false;
                                travelerMessages.setText("Number was entered wrong for Process 9 Planned Hours");
                            }
                        }

                    } else if (travelerProcessNumber >= 9) {

                        if (process9PlannedHoursText.getText().isEmpty() && travelerCalculationCheck) {

                            travelerCalculationCheck = false;
                            travelerMessages.setText("Nothing was entered for Process 9 Planned Hours");
                        }
                    }

                    if (travelerCalculationCheck) {

                        try {

                            plannedHoursTotalPartTime.setText(functions.addComasDouble(df.format(hoursTotal)));
                            plannedHoursTotalTotalTime.setText(functions.addComasDouble(df.format((hoursTotal * Double.parseDouble(quantityText.getText())))));

                        } catch ( StringInputException e ) {
                            e.printStackTrace();
                        }

                    } else if (!travelerCalculationCheck) {

                        plannedHoursTotalPartTime.setText("0.00");
                        plannedHoursTotalTotalTime.setText("0.00");
                    }

                } else {

                    travelerCalculationCheck = false;
                    travelerMessages.setText("A number needs to be entered for the number of Processes");
                }

            } else {

                travelerCalculationCheck = false;
                travelerMessages.setText("Number was formatted incorrectly for quantity");
            }

        } else {

            travelerCalculationCheck = false;
            travelerMessages.setText("A quantity must be entered to calculate total hours!");
        }

        return travelerCalculationCheck;
    }

    /* Method to store entered information within the Traveler form! */
    @SuppressWarnings("ConstantConditions")
    private void saveTravelerData() {

        if (!jobNumberText.getText().isEmpty()) {

            travelerData[0] = jobNumberText.getText();
        }

        if (dateReceivedPicker.getValue() != null) {

            travelerData[1] = create.dateConverter(dateReceivedPicker.getValue().toString());
        }

        if (!customerText.getText().isEmpty()) {

            travelerData[2] = customerText.getText();
        }

        if (!quantityText.getText().isEmpty()) {

            travelerData[3] = quantityText.getText();
        }

        if (!partNumberText.getText().isEmpty()) {

            travelerData[4] = partNumberText.getText();
        }

        if (dateRequiredPicker.getValue() != null) {

            travelerData[5] = create.dateConverter(dateRequiredPicker.getValue().toString());
        }

        if (!poNumberText.getText().isEmpty()) {

            travelerData[6] = poNumberText.getText();
        }

        if (completedDatePicker.getValue() != null) {

            travelerData[7] = create.dateConverter(completedDatePicker.getValue().toString());
        }

        if (travelerSavedProcessNumber >= 1) {

            travelerData[8] = process1DescriptionText.getText();
            travelerData[9] = process1PlannedHoursText.getText();
            travelerData[10] = process1ActualHoursText.getText();
            travelerData[11] = process1CompletedText.getText();
        }

        if (travelerSavedProcessNumber >= 2) {

            travelerData[12] = process2DescriptionText.getText();
            travelerData[13] = process2PlannedHoursText.getText();
            travelerData[14] = process2ActualHoursText.getText();
            travelerData[15] = process2CompletedText.getText();
        }

        if (travelerSavedProcessNumber >= 3) {

            travelerData[16] = process3DescriptionText.getText();
            travelerData[17] = process3PlannedHoursText.getText();
            travelerData[18] = process3ActualHoursText.getText();
            travelerData[19] = process3CompletedText.getText();
        }

        if (travelerSavedProcessNumber >= 4) {

            travelerData[20] = process4DescriptionText.getText();
            travelerData[21] = process4PlannedHoursText.getText();
            travelerData[22] = process4ActualHoursText.getText();
            travelerData[23] = process4CompletedText.getText();
        }

        if (travelerSavedProcessNumber >= 5) {

            travelerData[24] = process5DescriptionText.getText();
            travelerData[25] = process5PlannedHoursText.getText();
            travelerData[26] = process5ActualHoursText.getText();
            travelerData[27] = process5CompletedText.getText();
        }

        if (travelerSavedProcessNumber >= 6) {

            travelerData[28] = process6DescriptionText.getText();
            travelerData[29] = process6PlannedHoursText.getText();
            travelerData[30] = process6ActualHoursText.getText();
            travelerData[31] = process6CompletedText.getText();
        }

        if (travelerSavedProcessNumber >= 7) {

            travelerData[32] = process7DescriptionText.getText();
            travelerData[33] = process7PlannedHoursText.getText();
            travelerData[34] = process7ActualHoursText.getText();
            travelerData[35] = process7CompletedText.getText();
        }

        if (travelerSavedProcessNumber >= 8) {

            travelerData[36] = process8DescriptionText.getText();
            travelerData[37] = process8PlannedHoursText.getText();
            travelerData[38] = process8ActualHoursText.getText();
            travelerData[39] = process8CompletedText.getText();
        }

        if (travelerSavedProcessNumber == 9) {

            travelerData[40] = process9DescriptionText.getText();
            travelerData[41] = process9PlannedHoursText.getText();
            travelerData[42] = process9ActualHoursText.getText();
            travelerData[43] = process9CompletedText.getText();
        }

        if (!notesTextAreaTraveler.getText().isEmpty()) {

            travelerData[44] = notesTextAreaTraveler.getText();
        }
    }

    /* Method to set the data of each element within the Traveler form! */
    @SuppressWarnings("ConstantConditions")
    private void enterTravelerData() {

        if (travelerProcessNumber >= 1 && travelerProcessNumber <= 9) {

            process1DescriptionText.setText(travelerData[8]);
            process1PlannedHoursText.setText(travelerData[9]);
            process1ActualHoursText.setText(travelerData[10]);
            process1CompletedText.setText(travelerData[11]);
        }

        if (travelerProcessNumber >= 2 && travelerProcessNumber <= 9) {

            process2DescriptionText.setText(travelerData[12]);
            process2PlannedHoursText.setText(travelerData[13]);
            process2ActualHoursText.setText(travelerData[14]);
            process2CompletedText.setText(travelerData[15]);
        }

        if (travelerProcessNumber >= 3 && travelerProcessNumber <= 9) {

            process3DescriptionText.setText(travelerData[16]);
            process3PlannedHoursText.setText(travelerData[17]);
            process3ActualHoursText.setText(travelerData[18]);
            process3CompletedText.setText(travelerData[19]);
        }

        if (travelerProcessNumber >= 4 && travelerProcessNumber <= 9) {

            process4DescriptionText.setText(travelerData[20]);
            process4PlannedHoursText.setText(travelerData[21]);
            process4ActualHoursText.setText(travelerData[22]);
            process4CompletedText.setText(travelerData[23]);
        }

        if (travelerProcessNumber >= 5 && travelerProcessNumber <= 9) {

            process5DescriptionText.setText(travelerData[24]);
            process5PlannedHoursText.setText(travelerData[25]);
            process5ActualHoursText.setText(travelerData[26]);
            process5CompletedText.setText(travelerData[27]);
        }

        if (travelerProcessNumber >= 6 && travelerProcessNumber <= 9) {

            process6DescriptionText.setText(travelerData[28]);
            process6PlannedHoursText.setText(travelerData[29]);
            process6ActualHoursText.setText(travelerData[30]);
            process6CompletedText.setText(travelerData[31]);
        }

        if (travelerProcessNumber >= 7 && travelerProcessNumber <= 9) {

            process7DescriptionText.setText(travelerData[32]);
            process7PlannedHoursText.setText(travelerData[33]);
            process7ActualHoursText.setText(travelerData[34]);
            process7CompletedText.setText(travelerData[35]);
        }

        if (travelerProcessNumber >= 8 && travelerProcessNumber <= 9) {

            process8DescriptionText.setText(travelerData[36]);
            process8PlannedHoursText.setText(travelerData[37]);
            process8ActualHoursText.setText(travelerData[38]);
            process8CompletedText.setText(travelerData[39]);
        }

        if (travelerProcessNumber == 9) {

            process9DescriptionText.setText(travelerData[40]);
            process9PlannedHoursText.setText(travelerData[41]);
            process9ActualHoursText.setText(travelerData[42]);
            process9CompletedText.setText(travelerData[43]);
        }
    }

    /* Method to delete the needed information out of the array that stores entered information within the Traveler form.
    * Method will either delete a certain set of information or delete all information! */
    private void deleteTravelerData(boolean deleteThis) {

        if (!deleteThis) {

            for (int x = ((travelerProcessNumber * 4) + 8); x < ((travelerSavedProcessNumber * 4) + 8); x++) {

                travelerData[x] = "";

            }
        }

        if (deleteThis) {

            for (int y = 0; y <= 45; y++) {

                travelerData[y] = "";

            }
        }
    }

    /* Method that initializes the array for storing entered information within the Traveler form! */
    private void travelerDataInit() {

        for (int x = 0; x < 45; x++) {

            travelerData[x] = "";

        }
    }

    /* Method that will create the Packing List form window! */
    private void packingListFormCreate() {

        packingListFormRunning = true;

        packingListItemsVBox = new VBox(5);
        packingListItemsVBox.setPadding(new Insets(4));

        Label formDescription = new Label("Packing List Form");
        formDescription.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        HBox formLabel = new HBox(5);
        formLabel.setPadding(new Insets(5));
        formLabel.getChildren().add(formDescription);
        formLabel.setAlignment(Pos.CENTER);

        Label shipTo = new Label("Ship To:");
        shipToPackingList = new TextArea();
        shipToPackingList.setWrapText(true);
        shipToPackingList.setPrefWidth(250);
        shipToPackingList.setPrefHeight(80);
        shipToPackingList.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        Label billTo = new Label("Bill To:");
        billToPackingList = new TextArea();
        billToPackingList.setWrapText(true);
        billToPackingList.setPrefWidth(250);
        billToPackingList.setPrefHeight(80);
        billToPackingList.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        HBox packingListRow1 = new HBox(15);
        packingListRow1.getChildren().addAll(shipTo, shipToPackingList, billTo, billToPackingList);
        packingListRow1.setPadding(new Insets(5));
        packingListRow1.setAlignment(Pos.CENTER_LEFT);

        Label orderDate = new Label("Order Date:");
        orderDatePackingList = new DatePicker();
        orderDatePackingList.setPrefWidth(130);
        orderDatePackingList.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        Label orderNumber = new Label("Order Number:");
        orderNumberPackingList = new TextField();
        orderNumberPackingList.setPrefWidth(100);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(orderNumberPackingList, autoCompleteList);
        }

        Label job = new Label("Job:");
        jobPackingList = new TextField();
        jobPackingList.setPrefWidth(100);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(jobPackingList, autoCompleteList);
        }

        HBox packingListRow2 = new HBox(15);
        packingListRow2.getChildren().addAll(orderDate, orderDatePackingList, orderNumber, orderNumberPackingList, job, jobPackingList);
        packingListRow2.setPadding(new Insets(5));
        packingListRow2.setAlignment(Pos.CENTER_LEFT);

        Label packingListItems = new Label("Number of Items:");
        itemsNumberPackingList = new TextField();
        itemsNumberPackingList.setPrefWidth(50);

        Button numberOfItemsButton = new Button("Enter");
        numberOfItemsButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        numberOfItemsButton.setOnAction(e -> {

            if (itemsNumberPackingList.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for the Items Number");

            } else {

                checkNumberFormat = functions.checkIntegerFormat(itemsNumberPackingList.getText());

                if (checkNumberFormat) {

                    /* Variable for the Items number entered, stores the number entered into the packingListItemsNumber variable! */
                    packingListItemNumber = Integer.parseInt(itemsNumberPackingList.getText());

                    if (packingListItemNumber <= 24 && packingListItemNumber != 0) {

                        if (packingListItemNumber >= packingListSavedItemNumber && packingListItemsGenerated) {

                            savePackingListData();

                        } else {

                            savePackingListData();
                            deletePackingListData(false);

                            if (packingListItemsGenerated) {

                                enterPackingListData();
                            }
                        }

                        if (packingListItemNumber <= 24) {

                            packingListSavedItemNumber = packingListItemNumber;
                        }

                    } else if (packingListItemNumber == 0) {

                        itemsNumberPackingList.setText(String.valueOf(packingListSavedItemNumber));

                        packingListMessages.setText("Items number cannot be 0");

                    } else {

                        packingListMessages.setText("Items number entered was greater than the limit of 24");

                    }

                } else {

                    savePackingListData();
                    packingListMessages.setText("Number of items was enter wrong");
                }
            }

            if (packingListItemNumber > 0 && packingListItemNumber <= 24) {

                packingListItemsVBox.getChildren().removeAll(packingListItem1HBox, packingListItem2HBox, packingListItem3HBox, packingListItem4HBox, packingListItem5HBox, packingListItem6HBox, packingListItem7HBox, packingListItem8HBox, packingListItem9HBox, packingListItem10HBox,
                        packingListItem11HBox, packingListItem12HBox, packingListItem13HBox, packingListItem14HBox, packingListItem15HBox, packingListItem16HBox, packingListItem17HBox, packingListItem18HBox, packingListItem19HBox, packingListItem20HBox, packingListItem21HBox,
                        packingListItem22HBox, packingListItem23HBox, packingListItem24HBox);
            }

            if (packingListItemNumber >= 1 && packingListItemNumber <= 24) {

                Label item1NumberLabel = new Label("Item Number:");
                item1NumberText = new TextField();
                item1NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item1NumberText, autoCompleteList);
                }
                Label item1DescriptionLabel = new Label("Description:");
                item1DescriptionText = new TextField();
                item1DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item1DescriptionText, autoCompleteList);
                }
                Label item1QuantityLabel = new Label("Quantity:");
                item1QuantityText = new TextField();
                item1QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item1QuantityText, autoCompleteList);
                }

                packingListItem1HBox = new HBox(15);
                packingListItem1HBox.getChildren().addAll(item1NumberLabel, item1NumberText, item1DescriptionLabel, item1DescriptionText, item1QuantityLabel, item1QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem1HBox);
            }

            if (packingListItemNumber >= 2 && packingListItemNumber <= 24) {

                Label item2NumberLabel = new Label("Item Number:");
                item2NumberText = new TextField();
                item2NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item2NumberText, autoCompleteList);
                }
                Label item2DescriptionLabel = new Label("Description:");
                item2DescriptionText = new TextField();
                item2DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item2DescriptionText, autoCompleteList);
                }
                Label item2QuantityLabel = new Label("Quantity:");
                item2QuantityText = new TextField();
                item2QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item2QuantityText, autoCompleteList);
                }

                packingListItem2HBox = new HBox(15);
                packingListItem2HBox.getChildren().addAll(item2NumberLabel, item2NumberText, item2DescriptionLabel, item2DescriptionText, item2QuantityLabel, item2QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem2HBox);
            }

            if (packingListItemNumber >= 3 && packingListItemNumber <= 24) {

                Label item3NumberLabel = new Label("Item Number:");
                item3NumberText = new TextField();
                item3NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item3NumberText, autoCompleteList);
                }
                Label item3DescriptionLabel = new Label("Description:");
                item3DescriptionText = new TextField();
                item3DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item3DescriptionText, autoCompleteList);
                }
                Label item3QuantityLabel = new Label("Quantity:");
                item3QuantityText = new TextField();
                item3QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item3QuantityText, autoCompleteList);
                }

                packingListItem3HBox = new HBox(15);
                packingListItem3HBox.getChildren().addAll(item3NumberLabel, item3NumberText, item3DescriptionLabel, item3DescriptionText, item3QuantityLabel, item3QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem3HBox);
            }

            if (packingListItemNumber >= 4 && packingListItemNumber <= 24) {

                Label item4NumberLabel = new Label("Item Number:");
                item4NumberText = new TextField();
                item4NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item4NumberText, autoCompleteList);
                }
                Label item4DescriptionLabel = new Label("Description:");
                item4DescriptionText = new TextField();
                item4DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item4DescriptionText, autoCompleteList);
                }
                Label item4QuantityLabel = new Label("Quantity:");
                item4QuantityText = new TextField();
                item4QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item4QuantityText, autoCompleteList);
                }

                packingListItem4HBox = new HBox(15);
                packingListItem4HBox.getChildren().addAll(item4NumberLabel, item4NumberText, item4DescriptionLabel, item4DescriptionText, item4QuantityLabel, item4QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem4HBox);
            }

            if (packingListItemNumber >= 5 && packingListItemNumber <= 24) {

                Label item5NumberLabel = new Label("Item Number:");
                item5NumberText = new TextField();
                item5NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item5NumberText, autoCompleteList);
                }
                Label item5DescriptionLabel = new Label("Description:");
                item5DescriptionText = new TextField();
                item5DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item5DescriptionText, autoCompleteList);
                }
                Label item5QuantityLabel = new Label("Quantity:");
                item5QuantityText = new TextField();
                item5QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item5QuantityText, autoCompleteList);
                }

                packingListItem5HBox = new HBox(15);
                packingListItem5HBox.getChildren().addAll(item5NumberLabel, item5NumberText, item5DescriptionLabel, item5DescriptionText, item5QuantityLabel, item5QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem5HBox);
            }

            if (packingListItemNumber >= 6 && packingListItemNumber <= 24) {

                Label item6NumberLabel = new Label("Item Number:");
                item6NumberText = new TextField();
                item6NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item6NumberText, autoCompleteList);
                }
                Label item6DescriptionLabel = new Label("Description:");
                item6DescriptionText = new TextField();
                item6DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item6DescriptionText, autoCompleteList);
                }
                Label item6QuantityLabel = new Label("Quantity:");
                item6QuantityText = new TextField();
                item6QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item6QuantityText, autoCompleteList);
                }

                packingListItem6HBox = new HBox(15);
                packingListItem6HBox.getChildren().addAll(item6NumberLabel, item6NumberText, item6DescriptionLabel, item6DescriptionText, item6QuantityLabel, item6QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem6HBox);
            }

            if (packingListItemNumber >= 7 && packingListItemNumber <= 24) {

                Label item7NumberLabel = new Label("Item Number:");
                item7NumberText = new TextField();
                item7NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item7NumberText, autoCompleteList);
                }
                Label item7DescriptionLabel = new Label("Description:");
                item7DescriptionText = new TextField();
                item7DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item7DescriptionText, autoCompleteList);
                }
                Label item7QuantityLabel = new Label("Quantity:");
                item7QuantityText = new TextField();
                item7QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item7QuantityText, autoCompleteList);
                }

                packingListItem7HBox = new HBox(15);
                packingListItem7HBox.getChildren().addAll(item7NumberLabel, item7NumberText, item7DescriptionLabel, item7DescriptionText, item7QuantityLabel, item7QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem7HBox);
            }

            if (packingListItemNumber >= 8 && packingListItemNumber <= 24) {

                Label item8NumberLabel = new Label("Item Number:");
                item8NumberText = new TextField();
                item8NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item8NumberText, autoCompleteList);
                }
                Label item8DescriptionLabel = new Label("Description:");
                item8DescriptionText = new TextField();
                item8DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item8DescriptionText, autoCompleteList);
                }
                Label item8QuantityLabel = new Label("Quantity:");
                item8QuantityText = new TextField();
                item8QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item8QuantityText, autoCompleteList);
                }

                packingListItem8HBox = new HBox(15);
                packingListItem8HBox.getChildren().addAll(item8NumberLabel, item8NumberText, item8DescriptionLabel, item8DescriptionText, item8QuantityLabel, item8QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem8HBox);
            }

            if (packingListItemNumber >= 9 && packingListItemNumber <= 24) {

                Label item9NumberLabel = new Label("Item Number:");
                item9NumberText = new TextField();
                item9NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item9NumberText, autoCompleteList);
                }
                Label item9DescriptionLabel = new Label("Description:");
                item9DescriptionText = new TextField();
                item9DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item9DescriptionText, autoCompleteList);
                }
                Label item9QuantityLabel = new Label("Quantity:");
                item9QuantityText = new TextField();
                item9QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item9QuantityText, autoCompleteList);
                }

                packingListItem9HBox = new HBox(15);
                packingListItem9HBox.getChildren().addAll(item9NumberLabel, item9NumberText, item9DescriptionLabel, item9DescriptionText, item9QuantityLabel, item9QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem9HBox);
            }

            if (packingListItemNumber >= 10 && packingListItemNumber <= 24) {

                Label item10NumberLabel = new Label("Item Number:");
                item10NumberText = new TextField();
                item10NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item10NumberText, autoCompleteList);
                }
                Label item10DescriptionLabel = new Label("Description:");
                item10DescriptionText = new TextField();
                item10DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item10DescriptionText, autoCompleteList);
                }
                Label item10QuantityLabel = new Label("Quantity:");
                item10QuantityText = new TextField();
                item10QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item10QuantityText, autoCompleteList);
                }

                packingListItem10HBox = new HBox(15);
                packingListItem10HBox.getChildren().addAll(item10NumberLabel, item10NumberText, item10DescriptionLabel, item10DescriptionText, item10QuantityLabel, item10QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem10HBox);
            }

            if (packingListItemNumber >= 11 && packingListItemNumber <= 24) {

                Label item11NumberLabel = new Label("Item Number:");
                item11NumberText = new TextField();
                item11NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item11NumberText, autoCompleteList);
                }
                Label item11DescriptionLabel = new Label("Description:");
                item11DescriptionText = new TextField();
                item11DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item11DescriptionText, autoCompleteList);
                }
                Label item11QuantityLabel = new Label("Quantity:");
                item11QuantityText = new TextField();
                item11QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item11QuantityText, autoCompleteList);
                }

                packingListItem11HBox = new HBox(15);
                packingListItem11HBox.getChildren().addAll(item11NumberLabel, item11NumberText, item11DescriptionLabel, item11DescriptionText, item11QuantityLabel, item11QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem11HBox);
            }

            if (packingListItemNumber >= 12 && packingListItemNumber <= 24) {

                Label item12NumberLabel = new Label("Item Number:");
                item12NumberText = new TextField();
                item12NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item12NumberText, autoCompleteList);
                }
                Label item12DescriptionLabel = new Label("Description:");
                item12DescriptionText = new TextField();
                item12DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item12DescriptionText, autoCompleteList);
                }
                Label item12QuantityLabel = new Label("Quantity:");
                item12QuantityText = new TextField();
                item12QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item12QuantityText, autoCompleteList);
                }

                packingListItem12HBox = new HBox(15);
                packingListItem12HBox.getChildren().addAll(item12NumberLabel, item12NumberText, item12DescriptionLabel, item12DescriptionText, item12QuantityLabel, item12QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem12HBox);
            }

            if (packingListItemNumber >= 13 && packingListItemNumber <= 24) {

                Label item13NumberLabel = new Label("Item Number:");
                item13NumberText = new TextField();
                item13NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item13NumberText, autoCompleteList);
                }
                Label item13DescriptionLabel = new Label("Description:");
                item13DescriptionText = new TextField();
                item13DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item13DescriptionText, autoCompleteList);
                }
                Label item13QuantityLabel = new Label("Quantity:");
                item13QuantityText = new TextField();
                item13QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item13QuantityText, autoCompleteList);
                }

                packingListItem13HBox = new HBox(15);
                packingListItem13HBox.getChildren().addAll(item13NumberLabel, item13NumberText, item13DescriptionLabel, item13DescriptionText, item13QuantityLabel, item13QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem13HBox);
            }

            if (packingListItemNumber >= 14 && packingListItemNumber <= 24) {

                Label item14NumberLabel = new Label("Item Number:");
                item14NumberText = new TextField();
                item14NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item14NumberText, autoCompleteList);
                }
                Label item14DescriptionLabel = new Label("Description:");
                item14DescriptionText = new TextField();
                item14DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item14DescriptionText, autoCompleteList);
                }
                Label item14QuantityLabel = new Label("Quantity:");
                item14QuantityText = new TextField();
                item14QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item14QuantityText, autoCompleteList);
                }

                packingListItem14HBox = new HBox(15);
                packingListItem14HBox.getChildren().addAll(item14NumberLabel, item14NumberText, item14DescriptionLabel, item14DescriptionText, item14QuantityLabel, item14QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem14HBox);
            }

            if (packingListItemNumber >= 15 && packingListItemNumber <= 24) {

                Label item15NumberLabel = new Label("Item Number:");
                item15NumberText = new TextField();
                item15NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item15NumberText, autoCompleteList);
                }
                Label item15DescriptionLabel = new Label("Description:");
                item15DescriptionText = new TextField();
                item15DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item15DescriptionText, autoCompleteList);
                }
                Label item15QuantityLabel = new Label("Quantity:");
                item15QuantityText = new TextField();
                item15QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item15QuantityText, autoCompleteList);
                }

                packingListItem15HBox = new HBox(15);
                packingListItem15HBox.getChildren().addAll(item15NumberLabel, item15NumberText, item15DescriptionLabel, item15DescriptionText, item15QuantityLabel, item15QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem15HBox);
            }

            if (packingListItemNumber >= 16 && packingListItemNumber <= 24) {

                Label item16NumberLabel = new Label("Item Number:");
                item16NumberText = new TextField();
                item16NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item16NumberText, autoCompleteList);
                }
                Label item16DescriptionLabel = new Label("Description:");
                item16DescriptionText = new TextField();
                item16DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item16DescriptionText, autoCompleteList);
                }
                Label item16QuantityLabel = new Label("Quantity:");
                item16QuantityText = new TextField();
                item16QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item16QuantityText, autoCompleteList);
                }

                packingListItem16HBox = new HBox(15);
                packingListItem16HBox.getChildren().addAll(item16NumberLabel, item16NumberText, item16DescriptionLabel, item16DescriptionText, item16QuantityLabel, item16QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem16HBox);
            }

            if (packingListItemNumber >= 17 && packingListItemNumber <= 24) {

                Label item17NumberLabel = new Label("Item Number:");
                item17NumberText = new TextField();
                item17NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item17NumberText, autoCompleteList);
                }
                Label item17DescriptionLabel = new Label("Description:");
                item17DescriptionText = new TextField();
                item17DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item17DescriptionText, autoCompleteList);
                }
                Label item17QuantityLabel = new Label("Quantity:");
                item17QuantityText = new TextField();
                item17QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item17QuantityText, autoCompleteList);
                }

                packingListItem17HBox = new HBox(15);
                packingListItem17HBox.getChildren().addAll(item17NumberLabel, item17NumberText, item17DescriptionLabel, item17DescriptionText, item17QuantityLabel, item17QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem17HBox);
            }

            if (packingListItemNumber >= 18 && packingListItemNumber <= 24) {

                Label item18NumberLabel = new Label("Item Number:");
                item18NumberText = new TextField();
                item18NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item18NumberText, autoCompleteList);
                }
                Label item18DescriptionLabel = new Label("Description:");
                item18DescriptionText = new TextField();
                item18DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item18DescriptionText, autoCompleteList);
                }
                Label item18QuantityLabel = new Label("Quantity:");
                item18QuantityText = new TextField();
                item18QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item18QuantityText, autoCompleteList);
                }

                packingListItem18HBox = new HBox(15);
                packingListItem18HBox.getChildren().addAll(item18NumberLabel, item18NumberText, item18DescriptionLabel, item18DescriptionText, item18QuantityLabel, item18QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem18HBox);
            }

            if (packingListItemNumber >= 19 && packingListItemNumber <= 24) {

                Label item19NumberLabel = new Label("Item Number:");
                item19NumberText = new TextField();
                item19NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item19NumberText, autoCompleteList);
                }
                Label item19DescriptionLabel = new Label("Description:");
                item19DescriptionText = new TextField();
                item19DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item19DescriptionText, autoCompleteList);
                }
                Label item19QuantityLabel = new Label("Quantity:");
                item19QuantityText = new TextField();
                item19QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item19QuantityText, autoCompleteList);
                }

                packingListItem19HBox = new HBox(15);
                packingListItem19HBox.getChildren().addAll(item19NumberLabel, item19NumberText, item19DescriptionLabel, item19DescriptionText, item19QuantityLabel, item19QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem19HBox);
            }

            if (packingListItemNumber >= 20 && packingListItemNumber <= 24) {

                Label item20NumberLabel = new Label("Item Number:");
                item20NumberText = new TextField();
                item20NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item20NumberText, autoCompleteList);
                }
                Label item20DescriptionLabel = new Label("Description:");
                item20DescriptionText = new TextField();
                item20DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item20DescriptionText, autoCompleteList);
                }
                Label item20QuantityLabel = new Label("Quantity:");
                item20QuantityText = new TextField();
                item20QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item20QuantityText, autoCompleteList);
                }

                packingListItem20HBox = new HBox(15);
                packingListItem20HBox.getChildren().addAll(item20NumberLabel, item20NumberText, item20DescriptionLabel, item20DescriptionText, item20QuantityLabel, item20QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem20HBox);
            }

            if (packingListItemNumber >= 21 && packingListItemNumber <= 24) {

                Label item21NumberLabel = new Label("Item Number:");
                item21NumberText = new TextField();
                item21NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item21NumberText, autoCompleteList);
                }
                Label item21DescriptionLabel = new Label("Description:");
                item21DescriptionText = new TextField();
                item21DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item21DescriptionText, autoCompleteList);
                }
                Label item21QuantityLabel = new Label("Quantity:");
                item21QuantityText = new TextField();
                item21QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item21QuantityText, autoCompleteList);
                }

                packingListItem21HBox = new HBox(15);
                packingListItem21HBox.getChildren().addAll(item21NumberLabel, item21NumberText, item21DescriptionLabel, item21DescriptionText, item21QuantityLabel, item21QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem21HBox);
            }

            if (packingListItemNumber >= 22 && packingListItemNumber <= 24) {

                Label item22NumberLabel = new Label("Item Number:");
                item22NumberText = new TextField();
                item22NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item22NumberText, autoCompleteList);
                }
                Label item22DescriptionLabel = new Label("Description:");
                item22DescriptionText = new TextField();
                item22DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item22DescriptionText, autoCompleteList);
                }
                Label item22QuantityLabel = new Label("Quantity:");
                item22QuantityText = new TextField();
                item22QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item22QuantityText, autoCompleteList);
                }

                packingListItem22HBox = new HBox(15);
                packingListItem22HBox.getChildren().addAll(item22NumberLabel, item22NumberText, item22DescriptionLabel, item22DescriptionText, item22QuantityLabel, item22QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem22HBox);
            }

            if (packingListItemNumber >= 23 && packingListItemNumber <= 24) {

                Label item23NumberLabel = new Label("Item Number:");
                item23NumberText = new TextField();
                item23NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item23NumberText, autoCompleteList);
                }
                Label item23DescriptionLabel = new Label("Description:");
                item23DescriptionText = new TextField();
                item23DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item23DescriptionText, autoCompleteList);
                }
                Label item23QuantityLabel = new Label("Quantity:");
                item23QuantityText = new TextField();
                item23QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item23QuantityText, autoCompleteList);
                }

                packingListItem23HBox = new HBox(15);
                packingListItem23HBox.getChildren().addAll(item23NumberLabel, item23NumberText, item23DescriptionLabel, item23DescriptionText, item23QuantityLabel, item23QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem23HBox);
            }

            if (packingListItemNumber >= 24 && packingListItemNumber <= 24) {

                Label item24NumberLabel = new Label("Item Number:");
                item24NumberText = new TextField();
                item24NumberText.setPrefWidth(80.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item24NumberText, autoCompleteList);
                }
                Label item24DescriptionLabel = new Label("Description:");
                item24DescriptionText = new TextField();
                item24DescriptionText.setPrefWidth(200.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item24DescriptionText, autoCompleteList);
                }
                Label item24QuantityLabel = new Label("Quantity:");
                item24QuantityText = new TextField();
                item24QuantityText.setPrefWidth(50.0);
                if (autoCompletionState) {
                    TextFields.bindAutoCompletion(item24QuantityText, autoCompleteList);
                }

                packingListItem24HBox = new HBox(15);
                packingListItem24HBox.getChildren().addAll(item24NumberLabel, item24NumberText, item24DescriptionLabel, item24DescriptionText, item24QuantityLabel, item24QuantityText);

                packingListItemsVBox.getChildren().add(packingListItem24HBox);
            }

            if (packingListItemsGenerated) {

                enterPackingListData();
            }

            packingListItemsGenerated = true;

        });

        HBox packingListRow3 = new HBox(5);
        packingListRow3.setPadding(new Insets(5));
        packingListRow3.setAlignment(Pos.CENTER_RIGHT);
        packingListRow3.getChildren().addAll(packingListItems, itemsNumberPackingList, numberOfItemsButton);

        /* Setup for error message label! */
        packingListMessages = new Label();
        packingListMessages.setTextFill(Color.web("#f73636"));
        packingListMessages.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        packingListMessages.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!Objects.equals(newValue, "")) {

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> packingListMessages.setText(""));
                    }
                }, 5000);
            }
        });

        packingListMessages.setOnMouseEntered(event -> {
            packingListMessages.setScaleX(1.20);
            packingListMessages.setScaleY(1.20);
        });

        packingListMessages.setOnMouseExited(event -> {
            packingListMessages.setScaleX(1.00);
            packingListMessages.setScaleY(1.00);
        });

        /* Setup for the errorMessages VBox that displays errors within the Traveler form! */
        VBox errorMessages = new VBox(8);
        errorMessages.setPadding(new Insets(4));
        errorMessages.setAlignment(Pos.BOTTOM_CENTER);
        errorMessages.getChildren().add(packingListMessages);
        errorMessages.setStyle("-fx-font: 14 arial; -fx-base: #f73636;");

        /* Conditions for the Close button to close the Packing List Window! */
        Button closeButton = new Button("Cancel");
        closeButton.setStyle("-fx-font: 13 arial; -fx-base: #C0C0C0;");
        closeButton.setOnAction((ActionEvent e) -> {

            double centerXPosition = window.getX();
            double centerYPosition = window.getY();
            double primaryStageWidth = window.getWidth();
            double primaryStageHeight = window.getHeight();

            boolean cancelForm = confirmProgramClose.cancelAcknowledged("Packing List Form", centerXPosition, centerYPosition, primaryStageWidth, primaryStageHeight);

            if (cancelForm) {

                packingListItemNumber = 0;
                packingListItemsGenerated = false;
                deletePackingListData(true);

                content.getChildren().removeAll(packingListScrollPane);
                content.getChildren().add(contentPrompt);
                content.setAlignment(Pos.CENTER);
                contentPrompt.setText("Form Canceled");

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> contentPrompt.setText("Select a form to complete"));
                    }
                }, 5000);

                packingListFormRunning = false;
            }
        });

        /* Conditions for the Save button to save the entered information for the Packing List form! */
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        saveButton.setOnAction((ActionEvent e) -> {

            checkPackingListDataInput = checkPackingListDataInput();

            if (checkPackingListDataInput) {

                savePackingListData();

                File saveFile = saveFileChooser(packingListData[3], "Save", "PackingList");

                if (saveFile != null) {

                    try {

                        Statement statementInsertAuto = connection.createStatement();
                        statementInsertAuto.setQueryTimeout(10);

                        for (String aPackingListData : packingListData) {

                            if (!Objects.equals(aPackingListData, "") && aPackingListData != null) {

                                boolean passed = true;

                                for (String anAutoCompleteList : autoCompleteList) {

                                    if (aPackingListData.equals(anAutoCompleteList)) {

                                        passed = false;
                                    }
                                }

                                if (passed) {

                                    String insertAuto = String.format("INSERT INTO AUTO (WORD) VALUES ('%s')", aPackingListData);
                                    statementInsertAuto.execute(insertAuto);
                                    autoCompleteList.add(aPackingListData);
                                }
                            }
                        }

                        statementInsertAuto.close();

                    } catch ( Exception e1 ) {
                        e1.printStackTrace();
                    }

                    inputWorkbookData.packingListExcel(packingListData, packingListSavedItemNumber, saveFile.getAbsolutePath());

                    URL notificationImage = this.getClass().getResource("/sample/CustomerFiles/Images/Saved.gif");

                    TrayNotification notification = new TrayNotification("Packing List Formed Saved", "Saved To: " + saveFile.getAbsolutePath(), NotificationType.SUCCESS);
                    notification.setImage(new Image(String.valueOf(notificationImage)));
                    notification.setRectangleFill(Paint.valueOf("#46494f"));
                    notification.showAndDismiss(Duration.seconds(4));

                    packingListItemNumber = 0;
                    packingListItemsGenerated = false;
                    deletePackingListData(true);

                    content.getChildren().removeAll(packingListScrollPane);
                    content.getChildren().add(contentPrompt);
                    content.setAlignment(Pos.CENTER);
                    contentPrompt.setText("Form Saved");

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() ->
                                    contentPrompt.setText("Select a form to complete"));
                            try {
                                Desktop.getDesktop().open(new File(saveFile.getAbsolutePath()));
                            } catch ( IOException e1 ) {
                                e1.printStackTrace();
                            }
                        }
                    }, 5000);

                    packingListFormRunning = false;
                }
            }
        });

        HBox bottom = new HBox(5);
        bottom.getChildren().addAll(closeButton, saveButton);
        bottom.setPadding(new Insets(5));
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.setMinHeight(65);

        VBox packingListLayout = new VBox(5);
        packingListLayout.setPadding(new Insets(4));
        packingListLayout.getChildren().addAll(formLabel, packingListRow1, packingListRow2, packingListRow3, packingListItemsVBox, errorMessages, bottom);

        packingListScrollPane = new ScrollPane();
        packingListScrollPane.setContent(packingListLayout);
        packingListScrollPane.setFitToWidth(true);
        packingListScrollPane.setPrefHeight(window.heightProperty().doubleValue());

    }

    /* Method that will check the inputted data within the Packing List form for user error! */
    private boolean checkPackingListDataInput() {

        checkPackingListDataInput = false;

        if (packingListItemNumber == 0) {

            packingListMessages.setText("You must actually enter a number for the number of items");

        } else if (shipToPackingList.getText().isEmpty()) {

            packingListMessages.setText("Nothing was ever entered for the Ship To Address");

        } else if (billToPackingList.getText().isEmpty()) {

            packingListMessages.setText("Nothing was ever entered for the Bill To Address");

        } else if (orderDatePackingList.getValue() == null) {

            packingListMessages.setText("Nothing was ever entered for the Order Date");

        } else if (orderNumberPackingList.getText().isEmpty()) {

            packingListMessages.setText("Nothing was ever entered for the Order Number");

        } else if (jobPackingList.getText().isEmpty()) {

            packingListMessages.setText("Nothing was ever entered for the Job");

        } else if ((packingListItemNumber >= 1 && packingListItemNumber <= 24) && (item1NumberText.getText().isEmpty() || item1DescriptionText.getText().isEmpty() || item1QuantityText.getText().isEmpty())) {

            if (item1NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 1 Number");

            } else if (item1DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 1 Description");

            } else if (item1QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 1 Quantity");
            }

        } else if ((packingListItemNumber >= 2 && packingListItemNumber <= 24) && (item2NumberText.getText().isEmpty() || item2DescriptionText.getText().isEmpty() || item2QuantityText.getText().isEmpty())) {

            if (item2NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 2 Number");

            } else if (item2DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 2 Description");

            } else if (item2QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 2 Quantity");
            }

        } else if ((packingListItemNumber >= 3 && packingListItemNumber <= 24) && (item3NumberText.getText().isEmpty() || item3DescriptionText.getText().isEmpty() || item3QuantityText.getText().isEmpty())) {

            if (item3NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 3 Number");

            } else if (item3DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 3 Description");

            } else if (item3QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 3 Quantity");
            }

        } else if ((packingListItemNumber >= 4 && packingListItemNumber <= 24) && (item4NumberText.getText().isEmpty() || item4DescriptionText.getText().isEmpty() || item4QuantityText.getText().isEmpty())) {

            if (item4NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 4 Number");

            } else if (item4DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 4 Description");

            } else if (item4QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 4 Quantity");
            }

        } else if ((packingListItemNumber >= 5 && packingListItemNumber <= 24) && (item5NumberText.getText().isEmpty() || item5DescriptionText.getText().isEmpty() || item5QuantityText.getText().isEmpty())) {

            if (item5NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 5 Number");

            } else if (item5DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 5 Description");

            } else if (item5QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 5 Quantity");
            }

        } else if ((packingListItemNumber >= 6 && packingListItemNumber <= 24) && (item6NumberText.getText().isEmpty() || item6DescriptionText.getText().isEmpty() || item6QuantityText.getText().isEmpty())) {

            if (item6NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 6 Number");

            } else if (item6DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 6 Description");

            } else if (item6QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 6 Quantity");
            }

        } else if ((packingListItemNumber >= 7 && packingListItemNumber <= 24) && (item7NumberText.getText().isEmpty() || item7DescriptionText.getText().isEmpty() || item7QuantityText.getText().isEmpty())) {

            if (item7NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 7 Number");

            } else if (item7DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 7 Description");

            } else if (item7QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 7 Quantity");
            }

        } else if ((packingListItemNumber >= 8 && packingListItemNumber <= 24) && (item8NumberText.getText().isEmpty() || item8DescriptionText.getText().isEmpty() || item8QuantityText.getText().isEmpty())) {

            if (item8NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 8 Number");

            } else if (item8DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 8 Description");

            } else if (item8QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 8 Quantity");
            }

        } else if ((packingListItemNumber >= 9 && packingListItemNumber <= 24) && (item9NumberText.getText().isEmpty() || item9DescriptionText.getText().isEmpty() || item9QuantityText.getText().isEmpty())) {

            if (item9NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 9 Number");

            } else if (item9DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 9 Description");

            } else if (item9QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 9 Quantity");
            }

        } else if ((packingListItemNumber >= 10 && packingListItemNumber <= 24) && (item10NumberText.getText().isEmpty() || item10DescriptionText.getText().isEmpty() || item10QuantityText.getText().isEmpty())) {

            if (item10NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 10 Number");

            } else if (item10DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 10 Description");

            } else if (item10QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 10 Quantity");
            }

        } else if ((packingListItemNumber >= 11 && packingListItemNumber <= 24) && (item11NumberText.getText().isEmpty() || item11DescriptionText.getText().isEmpty() || item11QuantityText.getText().isEmpty())) {

            if (item11NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 11 Number");

            } else if (item11DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 11 Description");

            } else if (item11QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 11 Quantity");
            }

        } else if ((packingListItemNumber >= 12 && packingListItemNumber <= 24) && (item12NumberText.getText().isEmpty() || item12DescriptionText.getText().isEmpty() || item12QuantityText.getText().isEmpty())) {

            if (item12NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 12 Number");

            } else if (item12DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 12 Description");

            } else if (item12QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 12 Quantity");
            }

        } else if ((packingListItemNumber >= 13 && packingListItemNumber <= 24) && (item13NumberText.getText().isEmpty() || item13DescriptionText.getText().isEmpty() || item13QuantityText.getText().isEmpty())) {

            if (item13NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 13 Number");

            } else if (item13DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 13 Description");

            } else if (item13QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 13 Quantity");
            }

        } else if ((packingListItemNumber >= 14 && packingListItemNumber <= 24) && (item14NumberText.getText().isEmpty() || item14DescriptionText.getText().isEmpty() || item14QuantityText.getText().isEmpty())) {

            if (item14NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 14 Number");

            } else if (item14DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 14 Description");

            } else if (item14QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 14 Quantity");
            }

        } else if ((packingListItemNumber >= 15 && packingListItemNumber <= 24) && (item15NumberText.getText().isEmpty() || item15DescriptionText.getText().isEmpty() || item15QuantityText.getText().isEmpty())) {

            if (item15NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 15 Number");

            } else if (item15DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 15 Description");

            } else if (item15QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 1 Quantity");
            }

        } else if ((packingListItemNumber >= 16 && packingListItemNumber <= 24) && (item16NumberText.getText().isEmpty() || item16DescriptionText.getText().isEmpty() || item16QuantityText.getText().isEmpty())) {

            if (item16NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 16 Number");

            } else if (item16DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 16 Description");

            } else if (item16QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 16 Quantity");
            }

        } else if ((packingListItemNumber >= 17 && packingListItemNumber <= 24) && (item17NumberText.getText().isEmpty() || item17DescriptionText.getText().isEmpty() || item17QuantityText.getText().isEmpty())) {

            if (item17NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 17 Number");

            } else if (item17DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 17 Description");

            } else if (item17QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 17 Quantity");
            }

        } else if ((packingListItemNumber >= 18 && packingListItemNumber <= 24) && (item18NumberText.getText().isEmpty() || item18DescriptionText.getText().isEmpty() || item18QuantityText.getText().isEmpty())) {

            if (item18NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 18 Number");

            } else if (item18DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 18 Description");

            } else if (item18QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 18 Quantity");
            }

        } else if ((packingListItemNumber >= 19 && packingListItemNumber <= 24) && (item19NumberText.getText().isEmpty() || item19DescriptionText.getText().isEmpty() || item19QuantityText.getText().isEmpty())) {

            if (item19NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 19 Number");

            } else if (item19DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 19 Description");

            } else if (item19QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 19 Quantity");
            }

        } else if ((packingListItemNumber >= 20 && packingListItemNumber <= 24) && (item20NumberText.getText().isEmpty() || item20DescriptionText.getText().isEmpty() || item20QuantityText.getText().isEmpty())) {

            if (item20NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 20 Number");

            } else if (item20DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 20 Description");

            } else if (item20QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 20 Quantity");
            }

        } else if ((packingListItemNumber >= 21 && packingListItemNumber <= 24) && (item21NumberText.getText().isEmpty() || item21DescriptionText.getText().isEmpty() || item21QuantityText.getText().isEmpty())) {

            if (item21NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 21 Number");

            } else if (item21DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 21 Description");

            } else if (item21QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 21 Quantity");
            }

        } else if ((packingListItemNumber >= 22 && packingListItemNumber <= 24) && (item22NumberText.getText().isEmpty() || item22DescriptionText.getText().isEmpty() || item22QuantityText.getText().isEmpty())) {

            if (item22NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 22 Number");

            } else if (item22DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 22 Description");

            } else if (item22QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 22 Quantity");
            }

        } else if ((packingListItemNumber >= 23 && packingListItemNumber <= 24) && (item23NumberText.getText().isEmpty() || item23DescriptionText.getText().isEmpty() || item23QuantityText.getText().isEmpty())) {

            if (item23NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 23 Number");

            } else if (item23DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 23 Description");

            } else if (item23QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 23 Quantity");
            }

        } else if (packingListItemNumber == 24 && (item24NumberText.getText().isEmpty() || item24DescriptionText.getText().isEmpty() || item24QuantityText.getText().isEmpty())) {

            if (item24NumberText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for Item 24 Number");

            } else if (item24DescriptionText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 24 Description");

            } else if (item24QuantityText.getText().isEmpty()) {

                packingListMessages.setText("Nothing was enter for Item 24 Quantity");
            }

        } else {

            checkPackingListDataInput = true;
        }

        if (checkPackingListDataInput) {

            checkCurrencyFormat = functions.checkNumberFormat(orderNumberPackingList.getText());

            if (!checkCurrencyFormat) {

                packingListMessages.setText("Number wasn't entered correctly for Order Number");
                checkPackingListDataInput = false;

            } else {

                checkCurrencyFormat = functions.checkNumberFormat(jobPackingList.getText());

                if (!checkCurrencyFormat) {

                    packingListMessages.setText("Number wasn't entered correctly for Job Number");
                    checkPackingListDataInput = false;
                }

            }

            if (packingListItemsGenerated && checkPackingListDataInput) {

                if (packingListItemNumber >= 1 && packingListItemNumber <= 24) {

                    if (!item1QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item1QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item1QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 1 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 1 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 2 && packingListItemNumber <= 24) {

                    if (!item2QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item2QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item2QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 2 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 2 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 3 && packingListItemNumber <= 24) {

                    if (!item3QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item3QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item3QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 3 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 3 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 4 && packingListItemNumber <= 24) {

                    if (!item4QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item4QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item4QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 4 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 4 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 5 && packingListItemNumber <= 24) {

                    if (!item5QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item5QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item5QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 5 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 5 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 6 && packingListItemNumber <= 24) {

                    if (!item6QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item6QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item6QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 6 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 6 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 7 && packingListItemNumber <= 24) {

                    if (!item7QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item7QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item7QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 7 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 7 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 8 && packingListItemNumber <= 24) {

                    if (!item8QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item8QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item8QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 8 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 8 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 9 && packingListItemNumber <= 24) {

                    if (!item9QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item9QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item9QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 9 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 9 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 10 && packingListItemNumber <= 24) {

                    if (!item10QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item10QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item10QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 10 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 10 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 11 && packingListItemNumber <= 24) {

                    if (!item11QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item11QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item11QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 11 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 11 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 12 && packingListItemNumber <= 24) {

                    if (!item12QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item12QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item12QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 12 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 12 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 13 && packingListItemNumber <= 24) {

                    if (!item13QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item13QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item13QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 13 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 13 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 14 && packingListItemNumber <= 24) {

                    if (!item14QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item14QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item14QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 14 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 14 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 15 && packingListItemNumber <= 24) {

                    if (!item15QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item15QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item15QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 15 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 15 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 16 && packingListItemNumber <= 24) {

                    if (!item16QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item16QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item16QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 16 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 16 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 17 && packingListItemNumber <= 24) {

                    if (!item17QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item17QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item17QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 17 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 17 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 18 && packingListItemNumber <= 24) {

                    if (!item18QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item18QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item18QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 18 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 18 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 19 && packingListItemNumber <= 24) {

                    if (!item19QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item19QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item19QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 19 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 19 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 20 && packingListItemNumber <= 24) {

                    if (!item20QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item20QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item20QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 20 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 20 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 21 && packingListItemNumber <= 24) {

                    if (!item21QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item21QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item21QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 21 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 21 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 22 && packingListItemNumber <= 24) {

                    if (!item22QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item22QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item22QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 22 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 22 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 23 && packingListItemNumber <= 24) {

                    if (!item23QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item23QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item23QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 23 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 23 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }

                if (packingListItemNumber >= 24 && packingListItemNumber <= 24) {

                    if (!item24QuantityText.getText().isEmpty()) {

                        checkCurrencyFormat = functions.checkCurrencyFormat(item24QuantityText.getText());

                        if (checkCurrencyFormat) {

                            checkCurrencyFormat = functions.checkNumberFormat(item24QuantityText.getText());

                            if (!checkCurrencyFormat) {

                                packingListMessages.setText("Number wasn't entered correctly for Item 24 Quantity");
                                checkPackingListDataInput = false;
                            }

                        } else {

                            packingListMessages.setText("Number wasn't entered correctly for Item 24 Quantity");
                            checkPackingListDataInput = false;
                        }
                    }
                }
            }
        }

        return checkPackingListDataInput;
    }

    /* Method to store entered information within the Packing List form! */
    @SuppressWarnings("ConstantConditions")
    private void savePackingListData() {

        if (!shipToPackingList.getText().isEmpty()) {

            packingListData[0] = shipToPackingList.getText();
        }

        if (!billToPackingList.getText().isEmpty()) {

            packingListData[1] = billToPackingList.getText();
        }

        if (orderDatePackingList.getValue() != null) {

            packingListData[2] = create.dateConverter(orderDatePackingList.getValue().toString());
        }

        if (!orderNumberPackingList.getText().isEmpty()) {

            packingListData[3] = orderNumberPackingList.getText();
        }

        if (!jobPackingList.getText().isEmpty()) {

            packingListData[4] = jobPackingList.getText();
        }

        if (packingListSavedItemNumber >= 1) {

            packingListData[5] = item1NumberText.getText();
            packingListData[6] = item1DescriptionText.getText();
            packingListData[7] = item1QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 2) {

            packingListData[8] = item2NumberText.getText();
            packingListData[9] = item2DescriptionText.getText();
            packingListData[10] = item2QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 3) {

            packingListData[11] = item3NumberText.getText();
            packingListData[12] = item3DescriptionText.getText();
            packingListData[13] = item3QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 4) {

            packingListData[14] = item4NumberText.getText();
            packingListData[15] = item4DescriptionText.getText();
            packingListData[16] = item4QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 5) {

            packingListData[17] = item5NumberText.getText();
            packingListData[18] = item5DescriptionText.getText();
            packingListData[19] = item5QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 6) {

            packingListData[20] = item6NumberText.getText();
            packingListData[21] = item6DescriptionText.getText();
            packingListData[22] = item6QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 7) {

            packingListData[23] = item7NumberText.getText();
            packingListData[24] = item7DescriptionText.getText();
            packingListData[25] = item7QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 8) {

            packingListData[26] = item8NumberText.getText();
            packingListData[27] = item8DescriptionText.getText();
            packingListData[28] = item8QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 9) {

            packingListData[29] = item9NumberText.getText();
            packingListData[30] = item9DescriptionText.getText();
            packingListData[31] = item9QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 10) {

            packingListData[32] = item10NumberText.getText();
            packingListData[33] = item10DescriptionText.getText();
            packingListData[34] = item10QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 11) {

            packingListData[35] = item11NumberText.getText();
            packingListData[36] = item11DescriptionText.getText();
            packingListData[37] = item11QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 12) {

            packingListData[38] = item12NumberText.getText();
            packingListData[39] = item12DescriptionText.getText();
            packingListData[40] = item12QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 13) {

            packingListData[41] = item13NumberText.getText();
            packingListData[42] = item13DescriptionText.getText();
            packingListData[43] = item13QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 14) {

            packingListData[44] = item14NumberText.getText();
            packingListData[45] = item14DescriptionText.getText();
            packingListData[46] = item14QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 15) {

            packingListData[47] = item15NumberText.getText();
            packingListData[48] = item15DescriptionText.getText();
            packingListData[49] = item15QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 16) {

            packingListData[50] = item16NumberText.getText();
            packingListData[51] = item16DescriptionText.getText();
            packingListData[52] = item16QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 17) {

            packingListData[53] = item17NumberText.getText();
            packingListData[54] = item17DescriptionText.getText();
            packingListData[55] = item17QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 18) {

            packingListData[56] = item18NumberText.getText();
            packingListData[57] = item18DescriptionText.getText();
            packingListData[58] = item18QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 19) {

            packingListData[59] = item19NumberText.getText();
            packingListData[60] = item19DescriptionText.getText();
            packingListData[61] = item1QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 20) {

            packingListData[62] = item20NumberText.getText();
            packingListData[63] = item20DescriptionText.getText();
            packingListData[64] = item20QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 21) {

            packingListData[65] = item21NumberText.getText();
            packingListData[66] = item21DescriptionText.getText();
            packingListData[67] = item21QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 22) {

            packingListData[68] = item22NumberText.getText();
            packingListData[69] = item22DescriptionText.getText();
            packingListData[70] = item22QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 23) {

            packingListData[71] = item23NumberText.getText();
            packingListData[72] = item23DescriptionText.getText();
            packingListData[73] = item23QuantityText.getText();
        }

        if (packingListSavedItemNumber >= 24) {

            packingListData[74] = item24NumberText.getText();
            packingListData[75] = item24DescriptionText.getText();
            packingListData[76] = item24QuantityText.getText();
        }
    }

    /* Method to set the data of each element within the Packing List form! */
    @SuppressWarnings("ConstantConditions")
    private  void enterPackingListData() {

        if (packingListItemNumber >= 1 && packingListItemNumber <= 24) {

            item1NumberText.setText(packingListData[5]);
            item1DescriptionText.setText(packingListData[6]);
            item1QuantityText.setText(packingListData[7]);
        }

        if (packingListItemNumber >= 2 && packingListItemNumber <= 24) {

            item2NumberText.setText(packingListData[8]);
            item2DescriptionText.setText(packingListData[9]);
            item2QuantityText.setText(packingListData[10]);
        }

        if (packingListItemNumber >= 3 && packingListItemNumber <= 24) {

            item3NumberText.setText(packingListData[11]);
            item3DescriptionText.setText(packingListData[12]);
            item3QuantityText.setText(packingListData[13]);
        }

        if (packingListItemNumber >= 4 && packingListItemNumber <= 24) {

            item4NumberText.setText(packingListData[14]);
            item4DescriptionText.setText(packingListData[15]);
            item4QuantityText.setText(packingListData[16]);
        }

        if (packingListItemNumber >= 5 && packingListItemNumber <= 24) {

            item5NumberText.setText(packingListData[17]);
            item5DescriptionText.setText(packingListData[18]);
            item5QuantityText.setText(packingListData[19]);
        }

        if (packingListItemNumber >= 6 && packingListItemNumber <= 24) {

            item6NumberText.setText(packingListData[20]);
            item6DescriptionText.setText(packingListData[21]);
            item6QuantityText.setText(packingListData[22]);
        }

        if (packingListItemNumber >= 7 && packingListItemNumber <= 24) {

            item7NumberText.setText(packingListData[23]);
            item7DescriptionText.setText(packingListData[24]);
            item7QuantityText.setText(packingListData[25]);
        }

        if (packingListItemNumber >= 8 && packingListItemNumber <= 24) {

            item8NumberText.setText(packingListData[26]);
            item8DescriptionText.setText(packingListData[27]);
            item8QuantityText.setText(packingListData[28]);
        }

        if (packingListItemNumber >= 9 && packingListItemNumber <= 24) {

            item9NumberText.setText(packingListData[29]);
            item9DescriptionText.setText(packingListData[30]);
            item9QuantityText.setText(packingListData[31]);
        }

        if (packingListItemNumber >= 10 && packingListItemNumber <= 24) {

            item10NumberText.setText(packingListData[32]);
            item10DescriptionText.setText(packingListData[33]);
            item10QuantityText.setText(packingListData[34]);
        }

        if (packingListItemNumber >= 11 && packingListItemNumber <= 24) {

            item11NumberText.setText(packingListData[35]);
            item11DescriptionText.setText(packingListData[36]);
            item11QuantityText.setText(packingListData[37]);
        }

        if (packingListItemNumber >= 12 && packingListItemNumber <= 24) {

            item12NumberText.setText(packingListData[38]);
            item12DescriptionText.setText(packingListData[39]);
            item12QuantityText.setText(packingListData[40]);
        }

        if (packingListItemNumber >= 13 && packingListItemNumber <= 24) {

            item13NumberText.setText(packingListData[41]);
            item13DescriptionText.setText(packingListData[42]);
            item13QuantityText.setText(packingListData[43]);
        }

        if (packingListItemNumber >= 14 && packingListItemNumber <= 24) {

            item14NumberText.setText(packingListData[44]);
            item14DescriptionText.setText(packingListData[45]);
            item14QuantityText.setText(packingListData[46]);
        }

        if (packingListItemNumber >= 15 && packingListItemNumber <= 24) {

            item15NumberText.setText(packingListData[47]);
            item15DescriptionText.setText(packingListData[48]);
            item15QuantityText.setText(packingListData[49]);
        }

        if (packingListItemNumber >= 16 && packingListItemNumber <= 24) {

            item16NumberText.setText(packingListData[50]);
            item16DescriptionText.setText(packingListData[51]);
            item16QuantityText.setText(packingListData[52]);
        }

        if (packingListItemNumber >= 17 && packingListItemNumber <= 24) {

            item17NumberText.setText(packingListData[53]);
            item17DescriptionText.setText(packingListData[54]);
            item17QuantityText.setText(packingListData[55]);
        }

        if (packingListItemNumber >= 18 && packingListItemNumber <= 24) {

            item18NumberText.setText(packingListData[56]);
            item18DescriptionText.setText(packingListData[57]);
            item18QuantityText.setText(packingListData[58]);
        }

        if (packingListItemNumber >= 19 && packingListItemNumber <= 24) {

            item19NumberText.setText(packingListData[59]);
            item19DescriptionText.setText(packingListData[60]);
            item19QuantityText.setText(packingListData[61]);
        }

        if (packingListItemNumber >= 20 && packingListItemNumber <= 24) {

            item20NumberText.setText(packingListData[62]);
            item20DescriptionText.setText(packingListData[63]);
            item20QuantityText.setText(packingListData[64]);
        }

        if (packingListItemNumber >= 21 && packingListItemNumber <= 24) {

            item21NumberText.setText(packingListData[65]);
            item21DescriptionText.setText(packingListData[66]);
            item21QuantityText.setText(packingListData[67]);
        }

        if (packingListItemNumber >= 22 && packingListItemNumber <= 24) {

            item22NumberText.setText(packingListData[68]);
            item22DescriptionText.setText(packingListData[69]);
            item22QuantityText.setText(packingListData[70]);
        }

        if (packingListItemNumber >= 23 && packingListItemNumber <= 24) {

            item23NumberText.setText(packingListData[71]);
            item23DescriptionText.setText(packingListData[72]);
            item23QuantityText.setText(packingListData[73]);
        }

        if (packingListItemNumber >= 24 && packingListItemNumber <= 24) {

            item24NumberText.setText(packingListData[74]);
            item24DescriptionText.setText(packingListData[75]);
            item24QuantityText.setText(packingListData[76]);
        }
    }

    /* Method to delete the needed information out of the array that stores entered information within the Packing List form.
    * Method will either delete a certain set of information or delete all information! */
    private void deletePackingListData(boolean deleteThis) {

        if (!deleteThis) {

            for (int x = ((packingListItemNumber * 3) + 5); x < ((packingListSavedItemNumber * 3) + 5); x++) {

                packingListData[x] = "";
            }
        }

        if (deleteThis) {

            for (int y = 0; y <= 77; y++) {

                packingListData[y] = "";
            }
        }
    }

    /* Method that initializes the array for storing entered information within the Packing List form! */
    private void packingListDataInit() {

        for (int x = 0; x < 77; x++) {

            packingListData[x] = "";
        }
    }

    private void inputSalesReceipt() {}

    private void displayProTip() {}

    private void formLookUp() {}

    /* Method for displaying developer information! */
    private void displayDevInfo() {

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

    /* Method that will create the Settings window! */
    private void displaySettings() {

        /* HBox for storing searchWord TextField node! */
        HBox removeAutoCompletionWordLeft = new HBox(5);

        settingsPageFilling = true;

        Label pageDescription = new Label("Settings");
        pageDescription.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        /* Setup for the HBox that holds the form descriptor for the Estimate form! */
        HBox pageLabel = new HBox(8);
        pageLabel.setPadding(new Insets(5));
        pageLabel.getChildren().add(pageDescription);
        pageLabel.setAlignment(Pos.CENTER);

        estimateLocationFilePath = new Label("");
        estimateLocationFilePath.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        estimateLocationFilePath.setTextFill(Color.web("#f73636"));
        estimateLocationFilePath.setText(correctedFilePaths[0]);

        Label estimateFile = new Label("Estimate Form Default Path");
        estimateFile.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button estimateFileChoose = new Button("Choose");
        estimateFileChoose.setStyle("-fx-font: 14 arial; -fx-base: #b4ffa3;");
        estimateFileChoose.setOnAction(e -> {

            File estimateLocationChoose = saveFileChooser("", "Open", "Settings");

            if (Objects.equals(estimateLocationChoose, null) && (Objects.equals(correctedFilePaths[0], ("System.getProperty(\"user.home\") + \"/Desktop\"")))) {

                estimateLocationFilePath.setText(String.valueOf(System.getProperty("user.home") + "/Desktop/"));
                correctedFilePaths[0] = System.getProperty("user.home") + "/Desktop/";

            } else if (!Objects.equals(estimateLocationChoose, null)) {

                try {

                    String delete = "DELETE FROM SETINFO";
                    String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s')", functions.fileRemoveExtension(estimateLocationChoose), correctedFilePaths[1], correctedFilePaths[2], String.valueOf(autoCompletionState));

                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(10);
                    statement.executeUpdate(delete);
                    statement.executeUpdate(filePath);
                    statement.close();

                    correctedFilePaths[0] = functions.fileRemoveExtension(estimateLocationChoose);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                estimateLocationFilePath.setText(correctedFilePaths[0]);
            }
        });

        travelerLocationFilePath = new Label("");
        travelerLocationFilePath.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        travelerLocationFilePath.setTextFill(Color.web("#f73636"));
        travelerLocationFilePath.setText(correctedFilePaths[1]);

        Label travelerFile = new Label("Traveler Form Default Path");
        travelerFile.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button travelerFileChoose = new Button("Choose");
        travelerFileChoose.setStyle("-fx-font: 14 arial; -fx-base: #b4ffa3;");
        travelerFileChoose.setOnAction(e -> {

            File travelerLocationChoose = saveFileChooser("", "Open", "Settings");

            if (Objects.equals(travelerLocationChoose, null) && (Objects.equals(correctedFilePaths[1], ("System.getProperty(\"user.home\") + \"/Desktop\"")))) {

                travelerLocationFilePath.setText(String.valueOf(System.getProperty("user.home") + "/Desktop/"));
                correctedFilePaths[1] = System.getProperty("user.home") + "/Desktop/";

            } else if (!Objects.equals(travelerLocationChoose, null)) {

                try {

                    String delete = "DELETE FROM SETINFO";
                    String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s')", correctedFilePaths[0], functions.fileRemoveExtension(travelerLocationChoose), correctedFilePaths[2], String.valueOf(autoCompletionState));

                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(10);
                    statement.executeUpdate(delete);
                    statement.executeUpdate(filePath);
                    statement.close();

                    correctedFilePaths[1] = functions.fileRemoveExtension(travelerLocationChoose);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                travelerLocationFilePath.setText(correctedFilePaths[1]);
            }
        });

        packingListLocationFilePath = new Label("");
        packingListLocationFilePath.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        packingListLocationFilePath.setTextFill(Color.web("#f73636"));
        packingListLocationFilePath.setText(correctedFilePaths[2]);

        Label packingListFile = new Label("Packing List Form Default Path");
        packingListFile.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button packingListFileChoose = new Button("Choose");
        packingListFileChoose.setStyle("-fx-font: 14 arial; -fx-base: #b4ffa3;");
        packingListFileChoose.setOnAction(e -> {

            File packingListLocationChoose = saveFileChooser("", "Open", "Settings");

            if (Objects.equals(packingListLocationChoose, null) && (Objects.equals(correctedFilePaths[2], ("System.getProperty(\"user.home\") + \"/Desktop\"")))) {

                packingListLocationFilePath.setText(String.valueOf(System.getProperty("user.home") + "/Desktop/"));
                correctedFilePaths[2] = System.getProperty("user.home") + "/Desktop/";

            } else if (!Objects.equals(packingListLocationChoose, null)) {

                try {

                    String delete = "DELETE FROM SETINFO";
                    String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s')", correctedFilePaths[0], correctedFilePaths[1], functions.fileRemoveExtension(packingListLocationChoose), String.valueOf(autoCompletionState));

                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(10);
                    statement.executeUpdate(delete);
                    statement.executeUpdate(filePath);
                    statement.close();

                    correctedFilePaths[2] = functions.fileRemoveExtension(packingListLocationChoose);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                packingListLocationFilePath.setText(correctedFilePaths[2]);
            }
        });

        Label autoCompleteEnable = new Label("Enable Line Autocompletion");
        autoCompleteEnable.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        SwitchToggle enable = new SwitchToggle("ON", Color.web("#a7ef88"), "OFF", Color.web("#aeb0b2"), TransitionType.BUZZ);
        enable.setSelected(autoCompletionState);
        enable.switchOnProperty().addListener((observable, oldValue, newValue) -> {

            try {

                String delete = "DELETE FROM SETINFO";
                String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s')", correctedFilePaths[0], correctedFilePaths[1], correctedFilePaths[2], String.valueOf(enable.isSelected()));

                Statement statement = connection.createStatement();
                statement.setQueryTimeout(10);
                statement.executeUpdate(delete);
                statement.executeUpdate(filePath);
                statement.close();

                autoCompletionState = enable.isSelected();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Label deleteAutoCompletionWord = new Label("Remove From Auto Completion");
        deleteAutoCompletionWord.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        final TextField[] searchWord = {new TextField()};
        searchWord[0].setPrefWidth(130);
        TextFields.bindAutoCompletion(searchWord[0], autoCompleteList);
        Button deleteWord = new Button("Delete");
        deleteWord.setStyle("-fx-font: 14 arial; -fx-base: #b4ffa3;");
        deleteWord.setOnAction(e -> {

            if (estimateFormRunning || travelerFormRunning || packingListFormRunning) {

                if (estimateFormRunning) {

                    settingsMessages.setText("Estimate From Must Be Closed");

                } else if (travelerFormRunning) {

                    settingsMessages.setText("Traveler From Must Be Closed");

                } else {

                    settingsMessages.setText("Packing List From Must Be Closed");
                }

            } else {

                boolean exist = false;

                for (String anAutoCompleteList : autoCompleteList) {

                    if (Objects.equals(anAutoCompleteList, searchWord[0].getText())) {

                        exist = true;
                    }
                }

                if (!exist) {

                    settingsMessages.setText("Not In Auto Complete List");

                } else {

                    Collection<String> coll = autoCompleteList;
                    coll.removeIf(Predicate.isEqual(searchWord[0].getText()));
                    coll.removeIf(Predicate.isEqual("null"));

                    try {

                        String deleteAuto = "DELETE FROM AUTO";

                        Statement statementRedoAuto = connection.createStatement();
                        statementRedoAuto.setQueryTimeout(10);
                        statementRedoAuto.executeUpdate(deleteAuto);

                        for (String anAutoCompleteList : autoCompleteList) {

                            String insertAuto = String.format("INSERT INTO AUTO (WORD) VALUES ('%s')", anAutoCompleteList);
                            statementRedoAuto.execute(insertAuto);
                        }

                        statementRedoAuto.close();

                        searchWord[0].setText("");
                        settingsMessages.setText("Removed From Auto Complete List");

                        removeAutoCompletionWordLeft.getChildren().removeAll(deleteAutoCompletionWord, searchWord[0], deleteWord);
                        searchWord[0] = new TextField();
                        searchWord[0].setPrefWidth(130);
                        TextFields.bindAutoCompletion(searchWord[0], autoCompleteList);
                        removeAutoCompletionWordLeft.getChildren().addAll(deleteAutoCompletionWord, searchWord[0], deleteWord);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        /* Setup for error message label! */
        settingsMessages = new Label();
        settingsMessages.setTextFill(Color.web("#f73636"));
        settingsMessages.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        settingsMessages.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!Objects.equals(newValue, "")) {

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {Platform.runLater(() -> settingsMessages.setText(""));
                    }
                },5000);
            }
        });

        Button closeSettings = new Button("Close");
        closeSettings.setOnAction(e -> {

            content.getChildren().removeAll(settingsScrollPane, estimateScrollPane, travelerScrollPane, packingListScrollPane);
            content.getChildren().add(contentPrompt);
            content.setAlignment(Pos.CENTER);

            settingsPageFilling = false;
        });

        HBox estimateRow1First = new HBox(5);
        estimateRow1First.getChildren().addAll(estimateFile);
        estimateRow1First.setAlignment(Pos.BOTTOM_LEFT);

        HBox estimateRow1Second = new HBox(5);
        estimateRow1Second.getChildren().addAll(estimateFileChoose);
        estimateRow1Second.setAlignment(Pos.BOTTOM_RIGHT);
        HBox.setHgrow(estimateRow1Second, Priority.ALWAYS);

        HBox estimateRow = new HBox(5);
        estimateRow.getChildren().addAll(estimateRow1First, estimateRow1Second);
        estimateRow.setPadding(new Insets(10));
        estimateRow.setAlignment(Pos.BOTTOM_LEFT);

        HBox estimateLocationPath = new HBox(5);
        estimateLocationPath.getChildren().addAll(estimateLocationFilePath);
        estimateLocationPath.setPadding(new Insets(10));
        estimateLocationPath.setAlignment(Pos.BOTTOM_LEFT);

        HBox travelerRow1First = new HBox(5);
        travelerRow1First.getChildren().addAll(travelerFile);
        travelerRow1First.setAlignment(Pos.BOTTOM_LEFT);

        HBox travelerRow1Second = new HBox(5);
        travelerRow1Second.getChildren().addAll(travelerFileChoose);
        travelerRow1Second.setAlignment(Pos.BOTTOM_RIGHT);
        HBox.setHgrow(travelerRow1Second, Priority.ALWAYS);

        HBox travelerRow = new HBox(5);
        travelerRow.getChildren().addAll(travelerRow1First, travelerRow1Second);
        travelerRow.setPadding(new Insets(10));
        travelerRow.setAlignment(Pos.BOTTOM_LEFT);

        HBox travelerLocationPath = new HBox(5);
        travelerLocationPath.getChildren().addAll(travelerLocationFilePath);
        travelerLocationPath.setPadding(new Insets(10));
        travelerLocationPath.setAlignment(Pos.BOTTOM_LEFT);

        HBox packingListRow1First = new HBox(5);
        packingListRow1First.getChildren().addAll(packingListFile);
        packingListRow1First.setAlignment(Pos.BOTTOM_LEFT);

        HBox packingListRow1Second = new HBox(5);
        packingListRow1Second.getChildren().addAll(packingListFileChoose);
        packingListRow1Second.setAlignment(Pos.BOTTOM_RIGHT);
        HBox.setHgrow(packingListRow1Second, Priority.ALWAYS);

        HBox packingListRow = new HBox(5);
        packingListRow.getChildren().addAll(packingListRow1First, packingListRow1Second);
        packingListRow.setPadding(new Insets(10));
        packingListRow.setAlignment(Pos.BOTTOM_LEFT);

        HBox packingListLocationPath = new HBox(5);
        packingListLocationPath.getChildren().addAll(packingListLocationFilePath);
        packingListLocationPath.setPadding(new Insets(10));
        packingListLocationPath.setAlignment(Pos.BOTTOM_LEFT);

        HBox autoCompletion = new HBox(5);
        autoCompletion.getChildren().addAll(autoCompleteEnable, enable);
        autoCompletion.setPadding(new Insets(10));
        autoCompletion.setAlignment(Pos.BOTTOM_LEFT);

        removeAutoCompletionWordLeft.getChildren().addAll(deleteAutoCompletionWord, searchWord[0], deleteWord);
        removeAutoCompletionWordLeft.setAlignment(Pos.BOTTOM_LEFT);

        HBox removeAutoCompletionWordRight = new HBox(5);
        removeAutoCompletionWordRight.getChildren().addAll(settingsMessages);
        removeAutoCompletionWordRight.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(removeAutoCompletionWordRight, Priority.ALWAYS);

        HBox removeAutoCompletion = new HBox(5);
        removeAutoCompletion.getChildren().addAll(removeAutoCompletionWordLeft, removeAutoCompletionWordRight);
        removeAutoCompletion.setPadding(new Insets(10));
        removeAutoCompletion.setAlignment(Pos.BOTTOM_LEFT);

        HBox settingsBottom = new HBox(5);
        settingsBottom.getChildren().addAll(closeSettings);
        settingsBottom.setAlignment(Pos.BOTTOM_CENTER);

        VBox settingsLayout = new VBox(5);
        settingsLayout.setPadding(new Insets(5));
        settingsLayout.getChildren().addAll(pageLabel, estimateRow, estimateLocationPath, travelerRow, travelerLocationPath, packingListRow, packingListLocationPath, autoCompletion, removeAutoCompletion, settingsBottom);

        /* Setup for settingsScrollPane ScrollPane! */
        settingsScrollPane = new ScrollPane();
        settingsScrollPane.setContent(settingsLayout);
        settingsScrollPane.setFitToWidth(true);
        settingsScrollPane.setPrefHeight(window.heightProperty().doubleValue());

    }

    /* Method that displays a FileChooser for selecting where to save a generated file on the users device! */
    private File saveFileChooser(String fileName, String operation, String function) {

        File saveFile = new File("");
        int arrayLocation = 0;

        if (Objects.equals(function, "Estimate")) {

            arrayLocation = 0;

        } else if (Objects.equals(function, "Traveler")) {

            arrayLocation = 1;

        } else if (Objects.equals(function, "PackingList")) {

            arrayLocation = 2;
        }

        if (Objects.equals(operation, "Save")) {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(fileName);
            fileChooser.setInitialDirectory(new File(correctedFilePaths[arrayLocation]));

            fileChooser.setTitle("Chose Saved File Location");
            saveFile = fileChooser.showSaveDialog(window);

        } else if (Objects.equals(operation, "Open")) {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("Create");

            fileChooser.setTitle("Chose Default Location");
            saveFile = fileChooser.showSaveDialog(window);

        }

        return saveFile;
    }

    /* Method that will establish a connection to the local database or create the local database if it doesn't exist! */
    private void initDatabase () {

        String environment = System.getProperty("os.name");
        String home = System.getProperty("user.home");
        String databaseFileLocation = "";

        InputStream fileInput = this.getClass().getResourceAsStream("/sample/CustomerFiles/Databases/DesignQuest.sqlite");
        OutputStream fileOutput;

        byte[] buffer = new byte[1028];

        boolean check = false;
        boolean fileCopied = false;

        int length;

        if (environment.startsWith("Mac") || environment.startsWith("Windows")) {

            check = new File(home + "/DesignQuest", "DesignQuest.sqlite").exists();
            databaseFileLocation = (home + "/DesignQuest/DesignQuest.sqlite");
        }

        if (!check) {

            if (environment.startsWith("Mac") || environment.startsWith("Windows")) {

                try {

                    Path macPath = Paths.get(home + "/DesignQuest");
                    Files.createDirectories(macPath);

                    try {

                        File copyFile = new File(databaseFileLocation);

                        if (copyFile.createNewFile()) {

                            fileOutput = new FileOutputStream(copyFile);

                            while ((length = fileInput.read(buffer)) > 0) {

                                fileOutput.write(buffer, 0, length);
                            }

                            fileOutput.flush();

                            if (!Objects.equals(fileOutput, null)) {
                                fileOutput.close();
                            }
                            if (!Objects.equals(fileInput, null)) {
                                fileInput.close();
                            }

                            fileCopied = true;
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                } catch ( IOException e ) {
                    e.printStackTrace();
                }

            } else {

                contentPrompt.setText("Your not on a supported platform, must use Mac or Windows");
                fileCopied = false;
            }
        }

        File getPath = new File(databaseFileLocation);
        String connStr = String.format("jdbc:sqlite:%s", getPath.getAbsolutePath());

        if (check || fileCopied) {

            try {

                SQLiteConfig config = new SQLiteConfig();
                config.setEncoding(SQLiteConfig.Encoding.UTF8);
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(connStr, config.toProperties());

            } catch ( Exception e ) {

                connection = null;

                URL notificationImage = this.getClass().getResource("/sample/CustomerFiles/Images/Saved.gif");

                TrayNotification notification = new TrayNotification("DataBase Error", "Connection To Database Not Established", NotificationType.SUCCESS);
                notification.setImage(new Image(String.valueOf(notificationImage)));
                notification.setRectangleFill(Paint.valueOf("#46494f"));
                notification.showAndDismiss(Duration.seconds(7));

                contentPrompt.setText(String.valueOf("Will Not Be Able To Store Settings Information"));

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> contentPrompt.setText("Select a form to complete"));
                    }
                }, 1000000);
            }
        }
    }

    /* Method that will pull in stored user info from the local database! */
    private void initDatabaseArray() {

        try {

            if (connection != null) {

                String createTableSetInfo = "CREATE TABLE IF NOT EXISTS SETINFO (ID INTEGER PRIMARY KEY AUTOINCREMENT, ESTIMATEPATH TEXT(1000), TRAVELERPATH TEXT(1000), PACKINGLISTPATH TEXT (1000), STATE TEXT(7))";
                String createTableAuto = "CREATE TABLE IF NOT EXISTS AUTO (ID INTEGER PRIMARY KEY AUTOINCREMENT, WORD TEXT(1000))";
                String selectTableSetInfo = "SELECT * FROM SETINFO";

                Statement statementSetInfo = connection.createStatement();
                statementSetInfo.setQueryTimeout(10);
                statementSetInfo.execute(createTableSetInfo);
                statementSetInfo.close();

                Statement statementAuto = connection.createStatement();
                statementAuto.setQueryTimeout(10);
                statementAuto.execute(createTableAuto);
                statementAuto.close();

                Statement querySetInfo = connection.createStatement();
                ResultSet rsSetInfo = querySetInfo.executeQuery(selectTableSetInfo);

                correctedFilePaths[0] = rsSetInfo.getString(1);
                correctedFilePaths[1] = rsSetInfo.getString(2);
                correctedFilePaths[2] = rsSetInfo.getString(3);

                String state = rsSetInfo.getString(4);

                autoCompletionState = Objects.equals(state, "true");

                querySetInfo.close();

                String selectTableAuto = "SELECT * FROM AUTO";

                Statement queryAuto = connection.createStatement();
                ResultSet rsAuto = queryAuto.executeQuery(selectTableAuto);

                while (rsAuto.next()) {

                    autoCompleteList.add(rsAuto.getString(1));
                }

                queryAuto.close();

            }

        } catch (Exception e) {

            URL notificationImage = this.getClass().getResource("/sample/CustomerFiles/Images/Saved.gif");

            TrayNotification notification = new TrayNotification("DataBase Error", "Database Error Stored Information Not Downloaded", NotificationType.SUCCESS);
            notification.setImage(new Image(String.valueOf(notificationImage)));
            notification.setRectangleFill(Paint.valueOf("#46494f"));
            notification.showAndDismiss(Duration.seconds(7));

            contentPrompt.setText("Will Not Be Able To Store Settings Information");

            timer.schedule(new TimerTask() {
                @Override
                public void run() {Platform.runLater(() -> contentPrompt.setText("Select a form to complete"));}
            },10000);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    }

