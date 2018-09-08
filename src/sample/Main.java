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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
    private ScrollPane settingsScrollPane; /* ScrollPane for the Settings page! */

    /* HBoxes used within each form! */
    private HBox estimateItem1HBox1, estimateItem1HBox2, estimateItem1HBox3, estimateItem1HBox4, estimateItem1HBox5, estimateItem2HBox1, estimateItem2HBox2, estimateItem2HBox3, estimateItem2HBox4, estimateItem2HBox5,
            estimateItem3HBox1, estimateItem3HBox2, estimateItem3HBox3, estimateItem3HBox4, estimateItem3HBox5, estimateItem4HBox1, estimateItem4HBox2, estimateItem4HBox3, estimateItem4HBox4, estimateItem4HBox5,
            estimateItem5HBox1, estimateItem5HBox2, estimateItem5HBox3, estimateItem5HBox4, estimateItem5HBox5, estimateItem6HBox1, estimateItem6HBox2, estimateItem6HBox3, estimateItem6HBox4, estimateItem6HBox5,
            estimateItem7HBox1, estimateItem7HBox2, estimateItem7HBox3, estimateItem7HBox4, estimateItem7HBox5, estimateItem8HBox1, estimateItem8HBox2, estimateItem8HBox3, estimateItem8HBox4, estimateItem8HBox5,
            estimateItem9HBox1, estimateItem9HBox2, estimateItem9HBox3, estimateItem9HBox4, estimateItem9HBox5, estimateItem10HBox1, estimateItem10HBox2, estimateItem10HBox3, estimateItem10HBox4, estimateItem10HBox5,
            estimateItem11HBox1, estimateItem11HBox2, estimateItem11HBox3, estimateItem11HBox4, estimateItem11HBox5, estimateItem12HBox1, estimateItem12HBox2, estimateItem12HBox3, estimateItem12HBox4, estimateItem12HBox5,
            estimateItem13HBox1, estimateItem13HBox2, estimateItem13HBox3, estimateItem13HBox4, estimateItem13HBox5, estimateItem14HBox1, estimateItem14HBox2, estimateItem14HBox3, estimateItem14HBox4, estimateItem14HBox5,
            estimateItem15HBox1, estimateItem15HBox2, estimateItem15HBox3, estimateItem15HBox4, estimateItem15HBox5,
            travelerProcess1HBox, travelerProcess2HBox, travelerProcess3HBox, travelerProcess4HBox, travelerProcess5HBox, travelerProcess6HBox, travelerProcess7HBox, travelerProcess8HBox, travelerProcess9HBox,
            packingListItem1HBox, packingListItem2HBox, packingListItem3HBox, packingListItem4HBox, packingListItem5HBox, packingListItem6HBox, packingListItem7HBox, packingListItem8HBox,
            packingListItem9HBox, packingListItem10HBox, packingListItem11HBox, packingListItem12HBox, packingListItem13HBox, packingListItem14HBox, packingListItem15HBox, packingListItem16HBox,
            packingListItem17HBox, packingListItem18HBox, packingListItem19HBox, packingListItem20HBox, packingListItem21HBox, packingListItem22HBox, packingListItem23HBox, packingListItem24HBox;

    /* VBoxes used within each form! */
    private VBox estimateItemsVBox, travelerProcessesVBox, packingListItemsVBox, content;

    /* TextFields used within each form! */
    private TextField item1Text, item2Text, item3Text, item4Text, item5Text, item6Text, item7Text, item8Text, item9Text, item10Text, item11Text, item12Text, item13Text, item14Text, item15Text,
            description1Text, description2Text, description3Text, description4Text, description5Text, description6Text, description7Text, description8Text, description9Text, description10Text, description11Text, description12Text, description13Text, description14Text, description15Text,
            qty1Text, qty2Text, qty3Text, qty4Text, qty5Text, qty6Text, qty7Text, qty8Text, qty9Text, qty10Text, qty11Text, qty12Text, qty13Text, qty14Text, qty15Text,
            inHoursText1, inHoursText2, inHoursText3, inHoursText4, inHoursText5, inHoursText6, inHoursText7, inHoursText8, inHoursText9, inHoursText10, inHoursText11, inHoursText12, inHoursText13, inHoursText14, inHoursText15,
            adminRateText1, adminRateText2, adminRateText3, adminRateText4, adminRateText5, adminRateText6, adminRateText7, adminRateText8, adminRateText9, adminRateText10, adminRateText11, adminRateText12, adminRateText13, adminRateText14, adminRateText15,
            materialPerPart1, materialPerPart2, materialPerPart3, materialPerPart4, materialPerPart5, materialPerPart6, materialPerPart7, materialPerPart8, materialPerPart9, materialPerPart10, materialPerPart11, materialPerPart12, materialPerPart13, materialPerPart14, materialPerPart15,
            materialMarkUp1, materialMarkUp2, materialMarkUp3, materialMarkUp4, materialMarkUp5, materialMarkUp6, materialMarkUp7, materialMarkUp8, materialMarkUp9, materialMarkUp10, materialMarkUp11, materialMarkUp12, materialMarkUp13, materialMarkUp14, materialMarkUp15,
            laborInHours1, laborInHours2, laborInHours3, laborInHours4, laborInHours5, laborInHours6, laborInHours7, laborInHours8, laborInHours9, laborInHours10, laborInHours11, laborInHours12, laborInHours13, laborInHours14, laborInHours15,
            laborRate1, laborRate2, laborRate3, laborRate4, laborRate5, laborRate6, laborRate7, laborRate8, laborRate9, laborRate10, laborRate11, laborRate12, laborRate13, laborRate14, laborRate15,
            services1PerPart1, services1PerPart2, services1PerPart3, services1PerPart4, services1PerPart5, services1PerPart6, services1PerPart7, services1PerPart8, services1PerPart9, services1PerPart10, services1PerPart11, services1PerPart12, services1PerPart13, services1PerPart14, services1PerPart15,
            services1TotalParts1, services1TotalParts2, services1TotalParts3, services1TotalParts4, services1TotalParts5, services1TotalParts6, services1TotalParts7, services1TotalParts8, services1TotalParts9, services1TotalParts10, services1TotalParts11, services1TotalParts12, services1TotalParts13, services1TotalParts14, services1TotalParts15,
            services2PerPart1, services2PerPart2, services2PerPart3, services2PerPart4, services2PerPart5, services2PerPart6, services2PerPart7, services2PerPart8, services2PerPart9, services2PerPart10, services2PerPart11, services2PerPart12, services2PerPart13, services2PerPart14, services2PerPart15,
            services2TotalParts1, services2TotalParts2, services2TotalParts3, services2TotalParts4, services2TotalParts5, services2TotalParts6, services2TotalParts7, services2TotalParts8, services2TotalParts9, services2TotalParts10, services2TotalParts11, services2TotalParts12, services2TotalParts13, services2TotalParts14, services2TotalParts15,
            invoiceText, companyNameText, addressText, itemQuantityText, dqProjectText, leadTimeText, jobNumberText, customerText, quantityText, partNumberText, poNumberText, processesNumberText,
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
    private TextArea notesTextAreaEstimate, notesTextAreaTraveler, shipToPackingList, billToPackingList;

    private DatePicker dateText; /* Setup for the Date Picker being used in the Estimate window! */
    private DatePicker dateReceivedPicker; /* Setup for the Date Picker used for the date received in the Traveler window! */
    private DatePicker dateRequiredPicker; /* Setup for the Date Picker used for the date required in the Traveler window! */
    private DatePicker completedDatePicker; /* Setup for the Date Picker used for the date completed in the Traveler window! */
    private DatePicker orderDatePackingList; /* Setup for the Date Picker used for the order date in the Packing List window! */

    /* Labels used for items with each form! */
    private Label item1Label, description1Label, qty1Label, adminTime1, adminInHours1, adminRate1, material1, perPart1, markUp1, labor1, inHoursLabor1, rateLabor1, outside1services1, services1PerPart1Label, services1TotalParts1Label, outside2services1, services2PerPart1Label, services2TotalParts1Label,
            item2Label, description2Label, qty2Label, adminTime2, adminInHours2, adminRate2, material2, perPart2, markUp2, labor2, inHoursLabor2, rateLabor2, outside1services2, services1PerPart2Label, services1TotalParts2Label, outside2services2, services2PerPart2Label, services2TotalParts2Label,
            item3Label, description3Label, qty3Label, adminTime3, adminInHours3, adminRate3, material3, perPart3, markUp3, labor3, inHoursLabor3, rateLabor3, outside1services3, services1PerPart3Label, services1TotalParts3Label, outside2services3, services2PerPart3Label, services2TotalParts3Label,
            item4Label, description4Label, qty4Label, adminTime4, adminInHours4, adminRate4, material4, perPart4, markUp4, labor4, inHoursLabor4, rateLabor4, outside1services4, services1PerPart4Label, services1TotalParts4Label, outside2services4, services2PerPart4Label, services2TotalParts4Label,
            item5Label, description5Label, qty5Label, adminTime5, adminInHours5, adminRate5, material5, perPart5, markUp5, labor5, inHoursLabor5, rateLabor5, outside1services5, services1PerPart5Label, services1TotalParts5Label, outside2services5, services2PerPart5Label, services2TotalParts5Label,
            item6Label, description6Label, qty6Label, adminTime6, adminInHours6, adminRate6, material6, perPart6, markUp6, labor6, inHoursLabor6, rateLabor6, outside1services6, services1PerPart6Label, services1TotalParts6Label, outside2services6, services2PerPart6Label, services2TotalParts6Label,
            item7Label, description7Label, qty7Label, adminTime7, adminInHours7, adminRate7, material7, perPart7, markUp7, labor7, inHoursLabor7, rateLabor7, outside1services7, services1PerPart7Label, services1TotalParts7Label, outside2services7, services2PerPart7Label, services2TotalParts7Label,
            item8Label, description8Label, qty8Label, adminTime8, adminInHours8, adminRate8, material8, perPart8, markUp8, labor8, inHoursLabor8, rateLabor8, outside1services8, services1PerPart8Label, services1TotalParts8Label, outside2services8, services2PerPart8Label, services2TotalParts8Label,
            item9Label, description9Label, qty9Label, adminTime9, adminInHours9, adminRate9, material9, perPart9, markUp9, labor9, inHoursLabor9, rateLabor9, outside1services9, services1PerPart9Label, services1TotalParts9Label, outside2services9, services2PerPart9Label, services2TotalParts9Label,
            item10Label, description10Label, qty10Label, adminTime10, adminInHours10, adminRate10, material10, perPart10, markUp10, inHoursLabor10, labor10, rateLabor10, outside1services10, services1PerPart10Label, services1TotalParts10Label, outside2services10, services2PerPart10Label, services2TotalParts10Label,
            item11Label, description11Label, qty11Label, adminTime11, adminInHours11, adminRate11, material11, perPart11, markUp11, inHoursLabor11, labor11, rateLabor11, outside1services11, services1PerPart11Label, services1TotalParts11Label, outside2services11, services2PerPart11Label, services2TotalParts11Label,
            item12Label, description12Label, qty12Label, adminTime12, adminInHours12, adminRate12, material12, perPart12, markUp12, inHoursLabor12, labor12, rateLabor12, outside1services12, services1PerPart12Label, services1TotalParts12Label, outside2services12, services2PerPart12Label, services2TotalParts12Label,
            item13Label, description13Label, qty13Label, adminTime13, adminInHours13, adminRate13, material13, perPart13, markUp13, inHoursLabor13, labor13, rateLabor13, outside1services13, services1PerPart13Label, services1TotalParts13Label, outside2services13, services2PerPart13Label, services2TotalParts13Label,
            item14Label, description14Label, qty14Label, adminTime14, adminInHours14, adminRate14, material14, perPart14, markUp14, inHoursLabor14, labor14, rateLabor14, outside1services14, services1PerPart14Label, services1TotalParts14Label, outside2services14, services2PerPart14Label, services2TotalParts14Label,
            item15Label, description15Label, qty15Label, adminTime15, adminInHours15, adminRate15, material15, perPart15, markUp15, inHoursLabor15, labor15, rateLabor15, outside1services15, services1PerPart15Label, services1TotalParts15Label, outside2services15, services2PerPart15Label, services2TotalParts15Label,
            process1DescriptionLabel, process1PlannedHoursLabel, process1ActualHoursLabel, process1CompletedLabel,
            process2DescriptionLabel, process2PlannedHoursLabel, process2ActualHoursLabel, process2CompletedLabel,
            process3DescriptionLabel, process3PlannedHoursLabel, process3ActualHoursLabel, process3CompletedLabel,
            process4DescriptionLabel, process4PlannedHoursLabel, process4ActualHoursLabel, process4CompletedLabel,
            process5DescriptionLabel, process5PlannedHoursLabel, process5ActualHoursLabel, process5CompletedLabel,
            process6DescriptionLabel, process6PlannedHoursLabel, process6ActualHoursLabel, process6CompletedLabel,
            process7DescriptionLabel, process7PlannedHoursLabel, process7ActualHoursLabel, process7CompletedLabel,
            process8DescriptionLabel, process8PlannedHoursLabel, process8ActualHoursLabel, process8CompletedLabel,
            process9DescriptionLabel, process9PlannedHoursLabel, process9ActualHoursLabel, process9CompletedLabel,
            item1NumberLabel, item1DescriptionLabel, item1QuantityLabel, item2NumberLabel, item2DescriptionLabel, item2QuantityLabel,
            item3NumberLabel, item3DescriptionLabel, item3QuantityLabel, item4NumberLabel, item4DescriptionLabel, item4QuantityLabel,
            item5NumberLabel, item5DescriptionLabel, item5QuantityLabel, item6NumberLabel, item6DescriptionLabel, item6QuantityLabel,
            item7NumberLabel, item7DescriptionLabel, item7QuantityLabel, item8NumberLabel, item8DescriptionLabel, item8QuantityLabel,
            item9NumberLabel, item9DescriptionLabel, item9QuantityLabel, item10NumberLabel, item10DescriptionLabel, item10QuantityLabel,
            item11NumberLabel, item11DescriptionLabel, item11QuantityLabel, item12NumberLabel, item12DescriptionLabel, item12QuantityLabel,
            item13NumberLabel, item13DescriptionLabel, item13QuantityLabel, item14NumberLabel, item14DescriptionLabel, item14QuantityLabel,
            item15NumberLabel, item15DescriptionLabel, item15QuantityLabel, item16NumberLabel, item16DescriptionLabel, item16QuantityLabel,
            item17NumberLabel, item17DescriptionLabel, item17QuantityLabel, item18NumberLabel, item18DescriptionLabel, item18QuantityLabel,
            item19NumberLabel, item19DescriptionLabel, item19QuantityLabel, item20NumberLabel, item20DescriptionLabel, item20QuantityLabel,
            item21NumberLabel, item21DescriptionLabel, item21QuantityLabel, item22NumberLabel, item22DescriptionLabel, item22QuantityLabel,
            item23NumberLabel, item23DescriptionLabel, item23QuantityLabel, item24NumberLabel, item24DescriptionLabel, item24QuantityLabel;

    private Label totalCost; /* Label used for displaying the total cost of each item in the Estimate form! */
    private Label plannedHoursTotalPartTime; /* Label used for displaying the total hours for one part in the Traveler form! */
    private Label plannedHoursTotalTotalTime; /* Label used for displaying the total hours for all parts in the Traveler form! */
    private Label estimateMessagesTop; /* Label used for displaying error estimateMessages! */
    private Label estimateMessagesBottom; /* Label used for displaying error estimateMessages! */
    private Label travelerMessages; /* Label used for displaying error travelerMessages! */
    private Label packingListMessages; /* Label used for displaying error packingListMessages! */
    private Label settingsMessages; /* Label used for displaying messages on the Settings window! */
    private Label contentPrompt; /* Label used for displaying a prompt message for selecting a form to complete! */
    private Label estimateLocationFilePath; /* Label used for displaying the default save location for the Estimate form! */
    private Label travelerLocationFilePath; /* Label used for displaying the default save location for the Traveler form! */
    private Label packingListLocationFilePath; /* Label used for displaying the default save location for the Packing List form! */

    private final String datePattern = "MM-dd-yyyy"; /* Sets the date pattern for the StringConverter of each DatePicker! */

    private final String[] estimateData = new String[202]; /* Array for storing user input for items data on the Estimate form! */
    private final String[] travelerData = new String[45]; /* Array for storing user input for process data on the Traveler form! */
    private final String[] packingListData = new String[77]; /* Array for storing user input for items data on the Packing List form! */
    private final String[] defaultDirectories = new String[3]; /* Array for storing the default directory locations for saving each file! */

    /* Array used for storing TextField data within the Estimate form! */
    private TextField[] textFieldAreaEstimate = {item1Text, item2Text, item3Text, item4Text, item5Text, item6Text, item7Text, item8Text, item9Text, item10Text, item11Text, item12Text, item13Text, item14Text, item15Text,
            description1Text, description2Text, description3Text, description4Text, description5Text, description6Text, description7Text, description8Text, description9Text, description10Text, description11Text, description12Text, description13Text, description14Text, description15Text,
            qty1Text, qty2Text, qty3Text, qty4Text, qty5Text, qty6Text, qty7Text, qty8Text, qty9Text, qty10Text, qty11Text, qty12Text, qty13Text, qty14Text, qty15Text,
            inHoursText1, inHoursText2, inHoursText3, inHoursText4, inHoursText5, inHoursText6, inHoursText7, inHoursText8, inHoursText9, inHoursText10, inHoursText11, inHoursText12, inHoursText13, inHoursText14, inHoursText15,
            adminRateText1, adminRateText2, adminRateText3, adminRateText4, adminRateText5, adminRateText6, adminRateText7, adminRateText8, adminRateText9, adminRateText10, adminRateText11, adminRateText12, adminRateText13, adminRateText14, adminRateText15,
            materialPerPart1, materialPerPart2, materialPerPart3, materialPerPart4, materialPerPart5, materialPerPart6, materialPerPart7, materialPerPart8, materialPerPart9, materialPerPart10, materialPerPart11, materialPerPart12, materialPerPart13, materialPerPart14, materialPerPart15,
            materialMarkUp1, materialMarkUp2, materialMarkUp3, materialMarkUp4, materialMarkUp5, materialMarkUp6, materialMarkUp7, materialMarkUp8, materialMarkUp9, materialMarkUp10, materialMarkUp11, materialMarkUp12, materialMarkUp13, materialMarkUp14, materialMarkUp15,
            laborInHours1, laborInHours2, laborInHours3, laborInHours4, laborInHours5, laborInHours6, laborInHours7, laborInHours8, laborInHours9, laborInHours10, laborInHours11, laborInHours12, laborInHours13, laborInHours14, laborInHours15,
            laborRate1, laborRate2, laborRate3, laborRate4, laborRate5, laborRate6, laborRate7, laborRate8, laborRate9, laborRate10, laborRate11, laborRate12, laborRate13, laborRate14, laborRate15,
            services1PerPart1, services1PerPart2, services1PerPart3, services1PerPart4, services1PerPart5, services1PerPart6, services1PerPart7, services1PerPart8, services1PerPart9, services1PerPart10, services1PerPart11, services1PerPart12, services1PerPart13, services1PerPart14, services1PerPart15,
            services1TotalParts1, services1TotalParts2, services1TotalParts3, services1TotalParts4, services1TotalParts5, services1TotalParts6, services1TotalParts7, services1TotalParts8, services1TotalParts9, services1TotalParts10, services1TotalParts11, services1TotalParts12, services1TotalParts13, services1TotalParts14, services1TotalParts15,
            services2PerPart1, services2PerPart2, services2PerPart3, services2PerPart4, services2PerPart5, services2PerPart6, services2PerPart7, services2PerPart8, services2PerPart9, services2PerPart10, services2PerPart11, services2PerPart12, services2PerPart13, services2PerPart14, services2PerPart15,
            services2TotalParts1, services2TotalParts2, services2TotalParts3, services2TotalParts4, services2TotalParts5, services2TotalParts6, services2TotalParts7, services2TotalParts8, services2TotalParts9, services2TotalParts10, services2TotalParts11, services2TotalParts12, services2TotalParts13, services2TotalParts14, services2TotalParts15,
    };
    
    /* Array used for storing TextField data within the Traveler form! */
    private TextField[] textFieldAreaTraveler = {process1DescriptionText, process2DescriptionText, process3DescriptionText, process4DescriptionText, process5DescriptionText, process6DescriptionText, process7DescriptionText, process8DescriptionText, process9DescriptionText,
            process1PlannedHoursText, process2PlannedHoursText, process3PlannedHoursText, process4PlannedHoursText, process5PlannedHoursText, process6PlannedHoursText, process7PlannedHoursText, process8PlannedHoursText, process9PlannedHoursText,
            process1ActualHoursText, process2ActualHoursText, process3ActualHoursText, process4ActualHoursText, process5ActualHoursText, process6ActualHoursText, process7ActualHoursText, process8ActualHoursText, process9ActualHoursText,
            process1CompletedText, process2CompletedText, process3CompletedText, process4CompletedText, process5CompletedText, process6CompletedText, process7CompletedText, process8CompletedText, process9CompletedText,
    };

    /* Array used for storing TextField data within the PackingList form! */
    private TextField[] textFieldAreaPackingList = {item1NumberText, item2NumberText, item3NumberText, item4NumberText, item5NumberText, item6NumberText, item7NumberText, item8NumberText, item9NumberText, item10NumberText, item11NumberText, item12NumberText,
            item13NumberText, item14NumberText, item15NumberText, item16NumberText, item17NumberText, item18NumberText, item19NumberText, item20NumberText, item21NumberText, item22NumberText, item23NumberText, item24NumberText,
            item1DescriptionText, item2DescriptionText, item3DescriptionText, item4DescriptionText, item5DescriptionText, item6DescriptionText, item7DescriptionText, item8DescriptionText, item9DescriptionText, item10DescriptionText, item11DescriptionText, item12DescriptionText, 
            item13DescriptionText, item14DescriptionText, item15DescriptionText, item16DescriptionText, item17DescriptionText, item18DescriptionText, item19DescriptionText, item20DescriptionText, item21DescriptionText, item22DescriptionText, item23DescriptionText, item24DescriptionText,
            item1QuantityText, item2QuantityText, item3QuantityText, item4QuantityText, item5QuantityText, item6QuantityText, item7QuantityText, item8QuantityText, item9QuantityText, item10QuantityText, item11QuantityText, item12QuantityText, 
            item13QuantityText, item14QuantityText, item15QuantityText, item16QuantityText, item17QuantityText, item18QuantityText, item19QuantityText, item20QuantityText, item21QuantityText, item22QuantityText, item23QuantityText, item24QuantityText
    };

    private final List<String> autoCompleteList = new ArrayList<>(); /* List for storing suggested inputs for TextFields! */
    private final List<String> addressList = new ArrayList<>(); /* List for storing suggested address inputs for TextFields! */

    private int estimateItemNumber; /* Variable for the items number entered, stores the number entered on the EstimateWindow for items! */
    private int estimateSavedItemNumber; /* Variable for the last item number entered, stores the last number entered on the EstimateWindow for items! */
    private int travelerProcessNumber; /* Variable for the number of processes entered, stores the number entered on the TravelerWindow for processes! */
    private int travelerSavedProcessNumber; /* Variable for the last item number entered, stores the last number entered on the TravelerWindow for processes! */
    private int packingListItemNumber; /* Variable for the items number entered, stores the number entered on the PackingListWindow for items! */
    private int packingListSavedItemNumber; /* Variable for the last item number entered, stores the last number entered on the PackingListWindow for items! */

    private boolean checkEstimateDataInput; /* Variable for evaluating the entered data for a Estimate form! */
    private boolean checkTravelerDataInput; /* Variable for evaluating the entered data for a Traveler form! */
    private boolean checkPackingListDataInput; /* Variable for evaluating the entered data for a PackingList form! */
    private boolean estimateItemsGenerated; /* Variable for checking if items elements have already been created, will store true if items elements have been created in the Estimate form! */
    private boolean travelerProcessesGenerated; /* Variable for checking if process elements have already been created, will store true if process elements have been created in the Traveler form! */
    private boolean packingListItemsGenerated; /* Variable for checking if item elements have already been created, will store true if item elements have been created in the Packing List form! */
    private boolean estimateFormRunning; /* Variable for storing if an Estimate form is being filled! */
    private boolean travelerFormRunning; /* Variable for storing if the Traveler form is being filled! */
    private boolean packingListFormRunning; /* Variable for storing if the Packing List form is being filled! */
    private boolean settingsPageFilling; /* Variable for storing if the settings page is open or not! */
    private boolean autoCompletionState; /* Variable for storing if Auto Completion should be done for TextFields! */
    private boolean textFillState; /* Variable for storing if TextField checking should be done! */
    private boolean formOpenState; /* Variable for storing if forms should be opened after completion! */
    private boolean estimateCalculationComplete; /* Variable for storing if a estimate form calculation has been completed! */
    private boolean travelerCalculationComplete; /* Variable for storing if a traveler form calculation has been completed! */

    private final Functions functions = new Functions(); /* Initializer for the VTFXcontrols Function class! */
    private final CreatedFunctions create = new CreatedFunctions(); /* Initializer for CreatedFunctions class! */
    private final ExcelEditing inputWorkbookData = new ExcelEditing(); /* Initializer for ExcelEditing class! */
    private final ConfirmMessages confirmProgramClose = new ConfirmMessages(); /* Initializer for ConfirmMessages class! */
    private final DecimalFormat df = new DecimalFormat("0.00"); /* Initializer for a DecimalFormat instance, will format a Double variable to always show two decimal places! */
    private final Timer timer = new Timer(); /* Initializer for a timer instance! */
    private final DoubleProperty fontSize = new SimpleDoubleProperty(10); /* Initializer for a DoubleProperty variable, used to bind the font size of buttons relative to the main Stage width! */
    private final DoubleProperty imageSize = new SimpleDoubleProperty(10); /* Initializer for a DoubleProperty variable, used to bind the image size of buttons relative to the main Stage height! */

    private Connection connectionMain = null; /* Connection variable used to store the local database connectionMain information! */

    public Main() {

        estimateItem1HBox1 = estimateItem1HBox2 = estimateItem1HBox3 = estimateItem1HBox4 = estimateItem1HBox5 = estimateItem2HBox1 = estimateItem2HBox2 = estimateItem2HBox3 = estimateItem2HBox4 = estimateItem2HBox5 =
                estimateItem3HBox1 = estimateItem3HBox2 = estimateItem3HBox3 = estimateItem3HBox4 = estimateItem3HBox5 = estimateItem4HBox1 = estimateItem4HBox2 = estimateItem4HBox3 = estimateItem4HBox4 = estimateItem4HBox5 =
                estimateItem5HBox1 = estimateItem5HBox2 = estimateItem5HBox3 = estimateItem5HBox4 = estimateItem5HBox5 = estimateItem6HBox1 = estimateItem6HBox2 = estimateItem6HBox3 = estimateItem6HBox4 = estimateItem6HBox5 =
                estimateItem7HBox1 = estimateItem7HBox2 = estimateItem7HBox3 = estimateItem7HBox4 = estimateItem7HBox5 = estimateItem8HBox1 = estimateItem8HBox2 = estimateItem8HBox3 = estimateItem8HBox4 = estimateItem8HBox5 =
                estimateItem9HBox1 = estimateItem9HBox2 = estimateItem9HBox3 = estimateItem9HBox4 = estimateItem9HBox5 = estimateItem10HBox1 = estimateItem10HBox2 = estimateItem10HBox3 = estimateItem10HBox4 = estimateItem10HBox5 =
                estimateItem11HBox1 = estimateItem11HBox2 = estimateItem11HBox3 = estimateItem11HBox4 = estimateItem11HBox5 = estimateItem12HBox1 = estimateItem12HBox2 = estimateItem12HBox3 = estimateItem12HBox4 = estimateItem12HBox5 =
                estimateItem13HBox1 = estimateItem13HBox2 = estimateItem13HBox3 = estimateItem13HBox4 = estimateItem13HBox5 = estimateItem14HBox1 = estimateItem14HBox2 = estimateItem14HBox3 = estimateItem14HBox4 = estimateItem14HBox5 =
                estimateItem15HBox1 = estimateItem15HBox2 = estimateItem15HBox3 = estimateItem15HBox4 = estimateItem15HBox5  =
                travelerProcess1HBox = travelerProcess2HBox = travelerProcess3HBox = travelerProcess4HBox = travelerProcess5HBox = travelerProcess6HBox = travelerProcess7HBox = travelerProcess8HBox = travelerProcess9HBox =
                packingListItem1HBox = packingListItem2HBox = packingListItem3HBox = packingListItem4HBox = packingListItem5HBox = packingListItem6HBox = packingListItem7HBox = packingListItem8HBox =
                packingListItem9HBox = packingListItem10HBox = packingListItem11HBox = packingListItem12HBox = packingListItem13HBox = packingListItem14HBox = packingListItem15HBox = packingListItem16HBox =
                packingListItem17HBox = packingListItem18HBox = packingListItem19HBox = packingListItem20HBox = packingListItem21HBox = packingListItem22HBox = packingListItem23HBox = packingListItem24HBox = new HBox();
        
        item1Text = item2Text = item3Text = item4Text = item5Text = item6Text = item7Text = item8Text = item9Text = item10Text = item11Text = item12Text = item13Text = item14Text = item15Text =
                description1Text = description2Text = description3Text = description4Text = description5Text = description6Text = description7Text = description8Text = description9Text = description10Text = description11Text = description12Text = description13Text = description14Text = description15Text =
                qty1Text = qty2Text = qty3Text = qty4Text = qty5Text = qty6Text = qty7Text = qty8Text = qty9Text = qty10Text = qty11Text = qty12Text = qty13Text = qty14Text = qty15Text =
                inHoursText1 = inHoursText2 = inHoursText3 = inHoursText4 = inHoursText5 = inHoursText6 = inHoursText7 = inHoursText8 = inHoursText9 = inHoursText10 = inHoursText11 = inHoursText12 = inHoursText13 = inHoursText14 = inHoursText15 =
                adminRateText1 = adminRateText2 = adminRateText3 = adminRateText4 = adminRateText5 = adminRateText6 = adminRateText7 = adminRateText8 = adminRateText9 = adminRateText10 = adminRateText11 = adminRateText12 = adminRateText13 = adminRateText14 = adminRateText15 =
                materialPerPart1 = materialPerPart2 = materialPerPart3 = materialPerPart4 = materialPerPart5 = materialPerPart6 = materialPerPart7 = materialPerPart8 = materialPerPart9 = materialPerPart10 = materialPerPart11 = materialPerPart12 = materialPerPart13 = materialPerPart14 = materialPerPart15 =
                materialMarkUp1 = materialMarkUp2 = materialMarkUp3 = materialMarkUp4 = materialMarkUp5 = materialMarkUp6 = materialMarkUp7 = materialMarkUp8 = materialMarkUp9 = materialMarkUp10 = materialMarkUp11 = materialMarkUp12 = materialMarkUp13 = materialMarkUp14 = materialMarkUp15 =
                laborInHours1 = laborInHours2 = laborInHours3 = laborInHours4 = laborInHours5 = laborInHours6 = laborInHours7 = laborInHours8 = laborInHours9 = laborInHours10 = laborInHours11 = laborInHours12 = laborInHours13 = laborInHours14 = laborInHours15 =
                laborRate1 = laborRate2 = laborRate3 = laborRate4 = laborRate5 = laborRate6 = laborRate7 = laborRate8 = laborRate9 = laborRate10 = laborRate11 = laborRate12 = laborRate13 = laborRate14 = laborRate15 =
                services1PerPart1 = services1PerPart2 = services1PerPart3 = services1PerPart4 = services1PerPart5 = services1PerPart6 = services1PerPart7 = services1PerPart8 = services1PerPart9 = services1PerPart10 = services1PerPart11 = services1PerPart12 = services1PerPart13 = services1PerPart14 = services1PerPart15 =
                services1TotalParts1 = services1TotalParts2 = services1TotalParts3 = services1TotalParts4 = services1TotalParts5 = services1TotalParts6 = services1TotalParts7 = services1TotalParts8 = services1TotalParts9 = services1TotalParts10 = services1TotalParts11 = services1TotalParts12 = services1TotalParts13 = services1TotalParts14 = services1TotalParts15 =
                services2PerPart1 = services2PerPart2 = services2PerPart3 = services2PerPart4 = services2PerPart5 = services2PerPart6 = services2PerPart7 = services2PerPart8 = services2PerPart9 = services2PerPart10 = services2PerPart11 = services2PerPart12 = services2PerPart13 = services2PerPart14 = services2PerPart15 =
                services2TotalParts1 = services2TotalParts2 = services2TotalParts3 = services2TotalParts4 = services2TotalParts5 = services2TotalParts6 = services2TotalParts7 = services2TotalParts8 = services2TotalParts9 = services2TotalParts10 = services2TotalParts11 = services2TotalParts12 = services2TotalParts13 = services2TotalParts14 = services2TotalParts15 =
                process1DescriptionText = process2DescriptionText = process3DescriptionText = process4DescriptionText = process5DescriptionText = process6DescriptionText = process7DescriptionText = process8DescriptionText = process9DescriptionText =
                process1PlannedHoursText = process2PlannedHoursText = process3PlannedHoursText = process4PlannedHoursText = process5PlannedHoursText = process6PlannedHoursText = process7PlannedHoursText = process8PlannedHoursText = process9PlannedHoursText =
                process1ActualHoursText = process2ActualHoursText = process3ActualHoursText = process4ActualHoursText = process5ActualHoursText = process6ActualHoursText = process7ActualHoursText = process8ActualHoursText = process9ActualHoursText =
                process1CompletedText = process2CompletedText = process3CompletedText = process4CompletedText = process5CompletedText = process6CompletedText = process7CompletedText = process8CompletedText = process9CompletedText =
                item1NumberText = item2NumberText = item3NumberText = item4NumberText = item5NumberText = item6NumberText = item7NumberText = item8NumberText = item9NumberText = item10NumberText = item11NumberText = item12NumberText = item13NumberText = item14NumberText =
                item15NumberText = item16NumberText = item17NumberText = item18NumberText = item19NumberText = item20NumberText = item21NumberText = item22NumberText = item23NumberText = item24NumberText =
                item1DescriptionText = item2DescriptionText = item3DescriptionText = item4DescriptionText = item5DescriptionText = item6DescriptionText = item7DescriptionText = item8DescriptionText = item9DescriptionText = item10DescriptionText = item11DescriptionText =
                item12DescriptionText = item13DescriptionText = item14DescriptionText = item15DescriptionText = item16DescriptionText = item17DescriptionText = item18DescriptionText = item19DescriptionText = item20DescriptionText = item21DescriptionText = item22DescriptionText = item23DescriptionText = item24DescriptionText =
                item1QuantityText = item2QuantityText = item3QuantityText = item4QuantityText = item5QuantityText = item6QuantityText = item7QuantityText = item8QuantityText = item9QuantityText = item10QuantityText = item11QuantityText = item12QuantityText = item13QuantityText =
                item14QuantityText = item15QuantityText = item16QuantityText = item17QuantityText = item18QuantityText = item19QuantityText = item20QuantityText = item21QuantityText = item22QuantityText = item23QuantityText = item24QuantityText = new TextField();

        item1Label = description1Label = qty1Label = adminTime1 = adminInHours1 = adminRate1 = material1 = perPart1 = markUp1 = labor1 = inHoursLabor1 = rateLabor1 = outside1services1 = services1PerPart1Label = services1TotalParts1Label = outside2services1 = services2PerPart1Label = services2TotalParts1Label = 
                item2Label = description2Label = qty2Label = adminTime2 = adminInHours2 = adminRate2 = material2 = perPart2 = markUp2 = labor2 = inHoursLabor2 = rateLabor2 = outside1services2 = services1PerPart2Label = services1TotalParts2Label = outside2services2 = services2PerPart2Label = services2TotalParts2Label =
                item3Label = description3Label = qty3Label = adminTime3 = adminInHours3 = adminRate3 = material3 = perPart3 = markUp3 = labor3 = inHoursLabor3 = rateLabor3 = outside1services3 = services1PerPart3Label = services1TotalParts3Label = outside2services3 = services2PerPart3Label = services2TotalParts3Label =
                item4Label = description4Label = qty4Label = adminTime4 = adminInHours4 = adminRate4 = material4 = perPart4 = markUp4 = labor4 = inHoursLabor4 = rateLabor4 = outside1services4 = services1PerPart4Label = services1TotalParts4Label = outside2services4 = services2PerPart4Label = services2TotalParts4Label =
                item5Label = description5Label = qty5Label = adminTime5 = adminInHours5 = adminRate5 = material5 = perPart5 = markUp5 = labor5 = inHoursLabor5 = rateLabor5 = outside1services5 = services1PerPart5Label = services1TotalParts5Label = outside2services5 = services2PerPart5Label = services2TotalParts5Label =
                item6Label = description6Label = qty6Label = adminTime6 = adminInHours6 = adminRate6 = material6 = perPart6 = markUp6 = labor6 = inHoursLabor6 = rateLabor6 = outside1services6 = services1PerPart6Label = services1TotalParts6Label = outside2services6 = services2PerPart6Label = services2TotalParts6Label =
                item7Label = description7Label = qty7Label = adminTime7 = adminInHours7 = adminRate7 = material7 = perPart7 = markUp7 = labor7 = inHoursLabor7 = rateLabor7 = outside1services7 = services1PerPart7Label = services1TotalParts7Label = outside2services7 = services2PerPart7Label = services2TotalParts7Label =
                item8Label = description8Label = qty8Label = adminTime8 = adminInHours8 = adminRate8 = material8 = perPart8 = markUp8 = labor8 = inHoursLabor8 = rateLabor8 = outside1services8 = services1PerPart8Label = services1TotalParts8Label = outside2services8 = services2PerPart8Label = services2TotalParts8Label =
                item9Label = description9Label = qty9Label = adminTime9 = adminInHours9 = adminRate9 = material9 = perPart9 = markUp9 = labor9 = inHoursLabor9 = rateLabor9 = outside1services9 = services1PerPart9Label = services1TotalParts9Label = outside2services9 = services2PerPart9Label = services2TotalParts9Label =
                item10Label = description10Label = qty10Label = adminTime10 = adminInHours10 = adminRate10 = material10 = perPart10 = markUp10 = inHoursLabor10 = labor10 = rateLabor10 = outside1services10 = services1PerPart10Label = services1TotalParts10Label = outside2services10 = services2PerPart10Label = services2TotalParts10Label =
                item11Label = description11Label = qty11Label = adminTime11 = adminInHours11 = adminRate11 = material11 = perPart11 = markUp11 = inHoursLabor11 = labor11 = rateLabor11 = outside1services11 = services1PerPart11Label = services1TotalParts11Label = outside2services11 = services2PerPart11Label = services2TotalParts11Label =
                item12Label = description12Label = qty12Label = adminTime12 = adminInHours12 = adminRate12 = material12 = perPart12 = markUp12 = inHoursLabor12 = labor12 = rateLabor12 = outside1services12 = services1PerPart12Label = services1TotalParts12Label = outside2services12 = services2PerPart12Label = services2TotalParts12Label =
                item13Label = description13Label = qty13Label = adminTime13 = adminInHours13 = adminRate13 = material13 = perPart13 = markUp13 = inHoursLabor13 = labor13 = rateLabor13 = outside1services13 = services1PerPart13Label = services1TotalParts13Label = outside2services13 = services2PerPart13Label = services2TotalParts13Label =
                item14Label = description14Label = qty14Label = adminTime14 = adminInHours14 = adminRate14 = material14 = perPart14 = markUp14 = inHoursLabor14 = labor14 = rateLabor14 = outside1services14 = services1PerPart14Label = services1TotalParts14Label = outside2services14 = services2PerPart14Label = services2TotalParts14Label =
                item15Label = description15Label = qty15Label = adminTime15 = adminInHours15 = adminRate15 = material15 = perPart15 = markUp15 = inHoursLabor15 = labor15 = rateLabor15 = outside1services15 = services1PerPart15Label = services1TotalParts15Label = outside2services15 = services2PerPart15Label = services2TotalParts15Label =
                process1DescriptionLabel = process1PlannedHoursLabel = process1ActualHoursLabel = process1CompletedLabel =
                process2DescriptionLabel = process2PlannedHoursLabel = process2ActualHoursLabel = process2CompletedLabel =
                process3DescriptionLabel = process3PlannedHoursLabel = process3ActualHoursLabel = process3CompletedLabel =
                process4DescriptionLabel = process4PlannedHoursLabel = process4ActualHoursLabel = process4CompletedLabel =
                process5DescriptionLabel = process5PlannedHoursLabel = process5ActualHoursLabel = process5CompletedLabel =
                process6DescriptionLabel = process6PlannedHoursLabel = process6ActualHoursLabel = process6CompletedLabel =
                process7DescriptionLabel = process7PlannedHoursLabel = process7ActualHoursLabel = process7CompletedLabel =
                process8DescriptionLabel = process8PlannedHoursLabel = process8ActualHoursLabel = process8CompletedLabel =
                process9DescriptionLabel = process9PlannedHoursLabel = process9ActualHoursLabel = process9CompletedLabel =
                item1NumberLabel = item1DescriptionLabel = item1QuantityLabel = item2NumberLabel = item2DescriptionLabel = item2QuantityLabel =
                item3NumberLabel = item3DescriptionLabel = item3QuantityLabel = item4NumberLabel = item4DescriptionLabel = item4QuantityLabel =
                item5NumberLabel = item5DescriptionLabel = item5QuantityLabel = item6NumberLabel = item6DescriptionLabel = item6QuantityLabel =
                item7NumberLabel = item7DescriptionLabel = item7QuantityLabel = item8NumberLabel = item8DescriptionLabel = item8QuantityLabel =
                item9NumberLabel = item9DescriptionLabel = item9QuantityLabel = item10NumberLabel = item10DescriptionLabel = item10QuantityLabel =
                item11NumberLabel = item11DescriptionLabel = item11QuantityLabel = item12NumberLabel = item12DescriptionLabel = item12QuantityLabel =
                item13NumberLabel = item13DescriptionLabel = item13QuantityLabel = item14NumberLabel = item14DescriptionLabel = item14QuantityLabel =
                item15NumberLabel = item15DescriptionLabel = item15QuantityLabel = item16NumberLabel = item16DescriptionLabel = item16QuantityLabel =
                item17NumberLabel = item17DescriptionLabel = item17QuantityLabel = item18NumberLabel = item18DescriptionLabel = item18QuantityLabel =
                item19NumberLabel = item19DescriptionLabel = item19QuantityLabel = item20NumberLabel = item20DescriptionLabel = item20QuantityLabel =
                item21NumberLabel = item21DescriptionLabel = item21QuantityLabel = item22NumberLabel = item22DescriptionLabel = item22QuantityLabel =
                item23NumberLabel = item23DescriptionLabel = item23QuantityLabel = item24NumberLabel = item24DescriptionLabel = item24QuantityLabel =new Label();
        
        /* Initializes array for user input storage for the Estimate form! */
        for (int x = 0; x < 202; x++) {

            estimateData[x] = "";
        }

        /* Initializes array for user input storage for the Traveler form! */
        for (int y = 0; y < 45; y++) {

            travelerData[y] = "";
        }

        /* Initializes array for user input storage for the PackingList form! */
        for (int z = 0; z < 77; z++) {

            packingListData[z] = "";
        }

        /* Database setup and info pull! */
        initDatabase();
        initDatabaseData();
    }

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;

        /* Variable used to get the bounds of the device screen! */
        Rectangle2D ScreenBounds = Screen.getPrimary().getVisualBounds();

        /* Setup for each image used, will get path of image were it is stored on the users machine! */
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

                estimateFormCreate();

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, travelerScrollPane, packingListScrollPane);
                content.getChildren().add(estimateScrollPane);
                content.setAlignment(Pos.CENTER_LEFT);

            } else {

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, travelerScrollPane, estimateScrollPane, packingListScrollPane);
                content.getChildren().add(estimateScrollPane);
                content.setAlignment(Pos.CENTER_LEFT);
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

                travelerFormCreate();

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, estimateScrollPane, packingListScrollPane);
                content.getChildren().add(travelerScrollPane);
                content.setAlignment(Pos.CENTER_LEFT);

            } else {

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, estimateScrollPane, travelerScrollPane, packingListScrollPane);
                content.getChildren().add(travelerScrollPane);
                content.setAlignment(Pos.CENTER_LEFT);
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

                packingListFormCreate();

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, estimateScrollPane, travelerScrollPane);
                content.getChildren().add(packingListScrollPane);
                content.setAlignment(Pos.CENTER_LEFT);

            } else {

                content.getChildren().removeAll(contentPrompt, settingsScrollPane, estimateScrollPane, travelerScrollPane, packingListScrollPane);
                content.getChildren().add(packingListScrollPane);
                content.setAlignment(Pos.CENTER_LEFT);
            }
        });

        /* Conditions for another form, possible new feature! */
//        /* Conditions for the Sales Receipt button and it's content! */
//        ImageView salesReceiptButton = new ImageView(new Image(String.valueOf(salesReceiptButtonImagePath)));
//        salesReceiptButton.setFitHeight(70);
//        salesReceiptButton.setFitWidth(70);
//
//        Button salesReceipt = new Button("Sales Receipt");
//        salesReceipt.setStyle("-fx-font: 18 arial; -fx-base: #efefef;");
//        salesReceipt.setGraphic(salesReceiptButton);
//        salesReceipt.setContentDisplay(ContentDisplay.RIGHT);
//        salesReceipt.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        salesReceipt.setPrefHeight((ScreenBounds.getHeight() - (ScreenBounds.getHeight() / 6)) / 6);
//        salesReceipt.setPrefWidth(window.widthProperty().doubleValue() / 3);
//        salesReceipt.setOnAction(e -> inputSalesReceipt());

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
                content.setAlignment(Pos.CENTER_LEFT);

            } else {

                content.getChildren().removeAll(contentPrompt, estimateScrollPane, travelerScrollPane, packingListScrollPane, settingsScrollPane);
                content.getChildren().add(settingsScrollPane);
                content.setAlignment(Pos.CENTER_LEFT);
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
        input.getChildren().addAll(logo, estimate, traveler, packingList);

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

            /* If program is closing, the database connectionMain will be closed! */
            if (confirmProgramClose.display("Program", window.getX(), window.getY(), window.getWidth(), window.getHeight())) {

                try {
                    connectionMain.close();
                } catch ( SQLException ignored ) {}

                window.close();
                Platform.exit();
                System.exit(0);
            }

        });
        window.show();

        /* Lambda expression for a ChangeListener of the width of the primaryStage.
           This expression auto resizes the width of the buttons within the primaryStage, as well as the button's text font size and image size! */
        window.widthProperty().addListener((observable, oldStageWidth, newStageWidth) -> {

            double newWidth = (newStageWidth.doubleValue() / 3.0);

            estimate.setMaxWidth(newWidth);
            traveler.setMaxWidth(newWidth);
            packingList.setMaxWidth(newWidth);
            //salesReceipt.setMaxWidth(newWidth);

            fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(100));
            imageSize.bind(window.widthProperty().add(window.heightProperty()).divide(26));

            estimate.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #3178ea;"));
            traveler.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #31eaa6;"));
            packingList.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #eab231;"));
            //salesReceipt.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #efefef;"));

            estimateButtonImage.fitHeightProperty().bind(imageSize);
            travelerButton.fitHeightProperty().bind(imageSize);
            packingListButton.fitHeightProperty().bind(imageSize);
            //salesReceiptButton.fitHeightProperty().bind(imageSize);

            estimateButtonImage.fitWidthProperty().bind(imageSize);
            travelerButton.fitWidthProperty().bind(imageSize);
            packingListButton.fitWidthProperty().bind(imageSize);
            //salesReceiptButton.fitWidthProperty().bind(imageSize);


        });

        /* Lambda expression for a ChangeListener of the height of the primaryStage.
           This expression auto resizes the height of the buttons within the primaryStage, as well as the button's text font size and image size! */
        window.heightProperty().addListener((observable, oldStageHeight, newStageHeight) -> {

            double newHeight = (newStageHeight.doubleValue() / 6.0);

            estimate.setMaxHeight(newHeight);
            traveler.setMaxHeight(newHeight);
            packingList.setMaxHeight(newHeight);
            //salesReceipt.setMaxHeight(newHeight);

            fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(100));
            imageSize.bind(window.widthProperty().add(window.heightProperty()).divide(26));

            estimate.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #3178ea;"));
            traveler.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #31eaa6;"));
            packingList.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #eab231;"));
            //salesReceipt.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-base: #efefef;"));

            estimateButtonImage.fitHeightProperty().bind(imageSize);
            travelerButton.fitHeightProperty().bind(imageSize);
            packingListButton.fitHeightProperty().bind(imageSize);
            //salesReceiptButton.fitHeightProperty().bind(imageSize);

            estimateButtonImage.fitWidthProperty().bind(imageSize);
            travelerButton.fitWidthProperty().bind(imageSize);
            packingListButton.fitWidthProperty().bind(imageSize);
            //salesReceiptButton.fitWidthProperty().bind(imageSize);

        });
    }

    /* Method that will create the Estimate form window! */
    private void estimateFormCreate() {

        final HBox[] hBoxItems = new HBox[]{estimateItem1HBox1, estimateItem1HBox2, estimateItem1HBox3, estimateItem1HBox4, estimateItem1HBox5, estimateItem2HBox1, estimateItem2HBox2, estimateItem2HBox3, estimateItem2HBox4, estimateItem2HBox5,
                estimateItem3HBox1, estimateItem3HBox2, estimateItem3HBox3, estimateItem3HBox4, estimateItem3HBox5, estimateItem4HBox1, estimateItem4HBox2, estimateItem4HBox3, estimateItem4HBox4, estimateItem4HBox5,
                estimateItem5HBox1, estimateItem5HBox2, estimateItem5HBox3, estimateItem5HBox4, estimateItem5HBox5, estimateItem6HBox1, estimateItem6HBox2, estimateItem6HBox3, estimateItem6HBox4, estimateItem6HBox5,
                estimateItem7HBox1, estimateItem7HBox2, estimateItem7HBox3, estimateItem7HBox4, estimateItem7HBox5, estimateItem8HBox1, estimateItem8HBox2, estimateItem8HBox3, estimateItem8HBox4, estimateItem8HBox5,
                estimateItem9HBox1, estimateItem9HBox2, estimateItem9HBox3, estimateItem9HBox4, estimateItem9HBox5, estimateItem10HBox1, estimateItem10HBox2, estimateItem10HBox3, estimateItem10HBox4, estimateItem10HBox5,
                estimateItem11HBox1, estimateItem11HBox2, estimateItem11HBox3, estimateItem11HBox4, estimateItem11HBox5, estimateItem12HBox1, estimateItem12HBox2, estimateItem12HBox3, estimateItem12HBox4, estimateItem12HBox5,
                estimateItem13HBox1, estimateItem13HBox2, estimateItem13HBox3, estimateItem13HBox4, estimateItem13HBox5, estimateItem14HBox1, estimateItem14HBox2, estimateItem14HBox3, estimateItem14HBox4, estimateItem14HBox5,
                estimateItem15HBox1, estimateItem15HBox2, estimateItem15HBox3, estimateItem15HBox4, estimateItem15HBox5};

        final Label[] labelNames = new Label[]{item1Label, description1Label, qty1Label, adminTime1, adminInHours1, adminRate1, material1, perPart1, markUp1, labor1, inHoursLabor1, rateLabor1, outside1services1, services1PerPart1Label, services1TotalParts1Label, outside2services1, services2PerPart1Label, services2TotalParts1Label,
                item2Label, description2Label, qty2Label, adminTime2, adminInHours2, adminRate2, material2, perPart2, markUp2, labor2, inHoursLabor2, rateLabor2, outside1services2, services1PerPart2Label, services1TotalParts2Label, outside2services2, services2PerPart2Label, services2TotalParts2Label,
                item3Label, description3Label, qty3Label, adminTime3, adminInHours3, adminRate3, material3, perPart3, markUp3, labor3, inHoursLabor3, rateLabor3, outside1services3, services1PerPart3Label, services1TotalParts3Label, outside2services3, services2PerPart3Label, services2TotalParts3Label,
                item4Label, description4Label, qty4Label, adminTime4, adminInHours4, adminRate4, material4, perPart4, markUp4, labor4, inHoursLabor4, rateLabor4, outside1services4, services1PerPart4Label, services1TotalParts4Label, outside2services4, services2PerPart4Label, services2TotalParts4Label,
                item5Label, description5Label, qty5Label, adminTime5, adminInHours5, adminRate5, material5, perPart5, markUp5, labor5, inHoursLabor5, rateLabor5, outside1services5, services1PerPart5Label, services1TotalParts5Label, outside2services5, services2PerPart5Label, services2TotalParts5Label,
                item6Label, description6Label, qty6Label, adminTime6, adminInHours6, adminRate6, material6, perPart6, markUp6, labor6, inHoursLabor6, rateLabor6, outside1services6, services1PerPart6Label, services1TotalParts6Label, outside2services6, services2PerPart6Label, services2TotalParts6Label,
                item7Label, description7Label, qty7Label, adminTime7, adminInHours7, adminRate7, material7, perPart7, markUp7, labor7, inHoursLabor7, rateLabor7, outside1services7, services1PerPart7Label, services1TotalParts7Label, outside2services7, services2PerPart7Label, services2TotalParts7Label,
                item8Label, description8Label, qty8Label, adminTime8, adminInHours8, adminRate8, material8, perPart8, markUp8, labor8, inHoursLabor8, rateLabor8, outside1services8, services1PerPart8Label, services1TotalParts8Label, outside2services8, services2PerPart8Label, services2TotalParts8Label,
                item9Label, description9Label, qty9Label, adminTime9, adminInHours9, adminRate9, material9, perPart9, markUp9, labor9, inHoursLabor9, rateLabor9, outside1services9, services1PerPart9Label, services1TotalParts9Label, outside2services9, services2PerPart9Label, services2TotalParts9Label,
                item10Label, description10Label, qty10Label, adminTime10, adminInHours10, adminRate10, material10, perPart10, markUp10, inHoursLabor10, labor10, rateLabor10, outside1services10, services1PerPart10Label, services1TotalParts10Label, outside2services10, services2PerPart10Label, services2TotalParts10Label,
                item11Label, description11Label, qty11Label, adminTime11, adminInHours11, adminRate11, material11, perPart11, markUp11, inHoursLabor11, labor11, rateLabor11, outside1services11, services1PerPart11Label, services1TotalParts11Label, outside2services11, services2PerPart11Label, services2TotalParts11Label,
                item12Label, description12Label, qty12Label, adminTime12, adminInHours12, adminRate12, material12, perPart12, markUp12, inHoursLabor12, labor12, rateLabor12, outside1services12, services1PerPart12Label, services1TotalParts12Label, outside2services12, services2PerPart12Label, services2TotalParts12Label,
                item13Label, description13Label, qty13Label, adminTime13, adminInHours13, adminRate13, material13, perPart13, markUp13, inHoursLabor13, labor13, rateLabor13, outside1services13, services1PerPart13Label, services1TotalParts13Label, outside2services13, services2PerPart13Label, services2TotalParts13Label,
                item14Label, description14Label, qty14Label, adminTime14, adminInHours14, adminRate14, material14, perPart14, markUp14, inHoursLabor14, labor14, rateLabor14, outside1services14, services1PerPart14Label, services1TotalParts14Label, outside2services14, services2PerPart14Label, services2TotalParts14Label,
                item15Label, description15Label, qty15Label, adminTime15, adminInHours15, adminRate15, material15, perPart15, markUp15, inHoursLabor15, labor15, rateLabor15, outside1services15, services1PerPart15Label, services1TotalParts15Label, outside2services15, services2PerPart15Label, services2TotalParts15Label};

        final String[] labelStrings = {"Item", "Description:", "Qty:", "Admin Time:", "In Hours:", "Rate:", "Material:", "Per Part:", "MarkUp %:",
                "Labor / Machine Time:", "In Hours:", "Rate:", "Outside Services1:", "Per Part:", "Total Parts:", "Outside Services2:", "Per Part:", "Total Parts:"};

        estimateItemNumber = 0;
        estimateSavedItemNumber = 0;
        estimateItemsGenerated = false;
        estimateCalculationComplete = false;
        estimateFormRunning = true;
        deleteItemData(true);

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
        dqProjectLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dqProjectText = new TextField();
        dqProjectText.setPrefWidth(100.0);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(dqProjectText, autoCompleteList);
        }

        Label itemQuantityLabel = new Label("Number of Items:");
        itemQuantityLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        itemQuantityText = new TextField();
        itemQuantityText.setPrefWidth(50.0);

        /* Conditions for the item number enter button, within the action for the button, the appropriate number of element will be created and added to estimateScrollPane! */
        Button itemQuantityButton = new Button("Enter");
        itemQuantityButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        itemQuantityButton.setOnAction((ActionEvent e) -> {

            boolean enteredNumberPass = true;

            if (itemQuantityText.getText().isEmpty()) {

                estimateMessagesTop.setText("Nothing was entered for the items Number");
                estimateMessagesBottom.setText("Nothing was entered for the items Number");

            } else {

                if (functions.checkIntegerFormat(itemQuantityText.getText())) {

                    int parseToInt = Integer.parseInt(itemQuantityText.getText());

                    if (parseToInt <= 15 && parseToInt > 0) {

                        /* Variable for the items number entered, stores the number entered on the estimateWindow for items! */
                        estimateItemNumber = parseToInt;

                        if (estimateItemNumber < estimateSavedItemNumber && estimateItemsGenerated) {

                            deleteItemData(false);

                            if (estimateItemsGenerated) {

                                if (estimateCalculationComplete) {

                                    /* Will calculate the total cost of each item when items number is decreased! */
                                    estimateCalculate();
                                }
                            }
                        }

                    } else {

                        enteredNumberPass = false;

                        if (estimateItemNumber == 0) {

                            estimateMessagesTop.setText("Items number cannot be 0");
                            estimateMessagesBottom.setText("Items number cannot be 0");

                        } else {

                            estimateMessagesTop.setText("Items number entered was greater than the limit of 15");
                            estimateMessagesBottom.setText("Items number entered was greater than the limit of 15");
                        }

                        if (estimateItemsGenerated) {

                            itemQuantityText.setText(String.valueOf(estimateItemNumber));
                        }
                    }

                } else {

                    enteredNumberPass = false;
                    estimateMessagesTop.setText("Number of items was enter wrong");
                    estimateMessagesBottom.setText("Number of items was enter wrong");

                    if (estimateItemsGenerated) {

                        itemQuantityText.setText(String.valueOf(estimateItemNumber));
                    }
                }
            }

            if (estimateItemNumber > 0 && estimateItemNumber <= 15 && enteredNumberPass) {

                if (estimateItemNumber < estimateSavedItemNumber) {

                    for (int g = estimateItemNumber; g < estimateSavedItemNumber; g++) {

                        for (int h = 0; h < 5; h++) {

                            estimateItemsVBox.getChildren().remove(hBoxItems[(g * 5) + h]);
                        }
                    }

                } else {

                    /* Logic for generating Estimate form items! */
                    for (int x = estimateSavedItemNumber; x < estimateItemNumber; x++) {

                        for (int y = 0; y < 6; y++) {

                            if (y == 0) {

                                labelNames[(x * 18)] = new Label(String.format("%s %s:", labelStrings[y], (x + 1)));
                                labelNames[(x * 18)].setFont(Font.font("Arial", FontWeight.BOLD, 14));
                                textFieldAreaEstimate[x] = new TextField();
                                textFieldAreaEstimate[x].setPrefWidth(100.0);
                                if (autoCompletionState) {
                                    TextFields.bindAutoCompletion(textFieldAreaEstimate[x], autoCompleteList);
                                }

                                for (int z = 1; z < 3; z++) {

                                    labelNames[(x * 18) + z] = new Label(labelStrings[z]);
                                    labelNames[(x * 18) + z].setFont(Font.font("Arial", FontWeight.BOLD, 14));
                                    textFieldAreaEstimate[(15 * z) + x] = new TextField();
                                    textFieldAreaEstimate[(15 * z) + x].setPrefWidth(200 / (Math.pow(z, 2)));

                                    if (autoCompletionState) {
                                        TextFields.bindAutoCompletion(textFieldAreaEstimate[(15 * z) + x], autoCompleteList);
                                    }
                                }

                                hBoxItems[(x * 5)] = new HBox(8);
                                hBoxItems[(x * 5)].getChildren().addAll(labelNames[x * 18], textFieldAreaEstimate[x], labelNames[(x * 18) + 1], textFieldAreaEstimate[15 + x], labelNames[(x * 18) + 2], textFieldAreaEstimate[30 + x]);
                                hBoxItems[(x * 5)].setAlignment(Pos.CENTER_LEFT);

                            } else {

                                labelNames[(x * 18) + (y * 3)] = new Label(labelStrings[y * 3]);
                                labelNames[(x * 18) + (y * 3)].setFont(Font.font("Arial", FontWeight.BOLD, 14));
                                labelNames[(x * 18) + (y * 3)].setPadding(new Insets(20));
                                labelNames[(x * 18) + (y * 3) + 1] = new Label(labelStrings[(y * 3) + 1]);
                                textFieldAreaEstimate[(((y * 2) + 1) * 15) + x] = new TextField();
                                textFieldAreaEstimate[(((y * 2) + 1) * 15) + x].setPrefWidth(50.0);
                                if (autoCompletionState) {
                                    TextFields.bindAutoCompletion(textFieldAreaEstimate[(((y * 2) + 1) * 15) + x], autoCompleteList);
                                }
                                labelNames[(x * 18) + (y * 3) + 2] = new Label(labelStrings[(y * 3) + 2]);
                                textFieldAreaEstimate[(((y * 2) + 2) * 15) + x] = new TextField();
                                textFieldAreaEstimate[(((y * 2) + 2) * 15) + x].setPrefWidth(50.0);
                                if (autoCompletionState) {
                                    TextFields.bindAutoCompletion(textFieldAreaEstimate[(((y * 2) + 2) * 15) + x], autoCompleteList);
                                }

                                if (y == 2) {

                                    hBoxItems[(x * 5) + 1] = new HBox(4);
                                    hBoxItems[(x * 5) + 1].getChildren().addAll(labelNames[(x * 18) + 3], labelNames[(x * 18) + 4], textFieldAreaEstimate[45 + x],
                                            labelNames[(x * 18) + 5], textFieldAreaEstimate[60 + x], labelNames[(x * 18) + 6], labelNames[(x * 18) + 7],
                                            textFieldAreaEstimate[75 + x], labelNames[(x * 18) + 8], textFieldAreaEstimate[90 + x]);
                                    hBoxItems[(x * 5) + 1].setAlignment(Pos.CENTER_LEFT);

                                } else if (y >= 3) {

                                    hBoxItems[(x * 5) + (y - 1)] = new HBox(4);
                                    hBoxItems[(x * 5) + (y - 1)].getChildren().addAll(labelNames[(x * 18) + (y * 3)], labelNames[(x * 18) + (y * 3) + 1],
                                            textFieldAreaEstimate[(((y * 2) + 1) * 15) + x], labelNames[(x * 18) + (y * 3) + 2], textFieldAreaEstimate[(((y * 2) + 2) * 15) + x]);
                                    hBoxItems[(x * 5) + (y - 1)].setAlignment(Pos.CENTER_LEFT);
                                }
                            }
                        }

                        for (int w = (x * 5); w < ((x * 5) + 5); w++) {

                            estimateItemsVBox.getChildren().add(hBoxItems[w]);
                        }
                    }
                }

                estimateSavedItemNumber = estimateItemNumber;
                estimateItemsGenerated = true;
            }
        });

        HBox dqProjectHBox = new HBox(8);
        dqProjectHBox.setPadding(new Insets(5));
        dqProjectHBox.getChildren().addAll(dqProjectLabel, dqProjectText, itemQuantityLabel, itemQuantityText, itemQuantityButton);
        dqProjectHBox.setAlignment(Pos.CENTER_LEFT);

        Label dateLabel = new Label("Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dateText = new DatePicker();
        dateText.setPrefWidth(130.0);
        dateText.setConverter(new StringConverter<>() {

            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

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

        Label leadTime = new Label("Lead Time:");
        leadTime.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        leadTimeText = new TextField();
        leadTimeText.setPrefWidth(75.0);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(leadTimeText, autoCompleteList);
        }

        HBox dateHBox = new HBox(8);
        dateHBox.setPadding(new Insets(5));
        dateHBox.getChildren().addAll(dateLabel, dateText, leadTime, leadTimeText);
        dateHBox.setAlignment(Pos.CENTER_LEFT);

        Label invoiceLabel = new Label("Invoice #:");
        invoiceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        invoiceText = new TextField();
        invoiceText.setPrefWidth(100.0);
        invoiceText.setText(create.getInvoiceString());

        Label notes = new Label("Notes:");
        notes.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        notesTextAreaEstimate = new TextArea();
        notesTextAreaEstimate.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        notesTextAreaEstimate.setWrapText(true);
        notesTextAreaEstimate.setPrefWidth(350);
        notesTextAreaEstimate.setPrefHeight(50);

        HBox invoiceHBox = new HBox(8);
        invoiceHBox.setPadding(new Insets(5));
        invoiceHBox.getChildren().addAll(invoiceLabel, invoiceText, notes, notesTextAreaEstimate);
        invoiceHBox.setAlignment(Pos.CENTER_LEFT);

        Label companyNameLabel = new Label("Name:");
        companyNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        companyNameText = new TextField();
        companyNameText.setPrefWidth(175.0);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(companyNameText, autoCompleteList);
        }

        Label addressLabel = new Label("Address:");
        addressLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        addressText = new TextField();
        addressText.setPrefWidth(175.0);
        if (autoCompletionState) {
            TextFields.bindAutoCompletion(addressText, addressList);
        }

        HBox nameAddressHBox = new HBox(8);
        nameAddressHBox.setPadding(new Insets(5));
        nameAddressHBox.getChildren().addAll(companyNameLabel, companyNameText, addressLabel, addressText);
        nameAddressHBox.setAlignment(Pos.CENTER_LEFT);

        /* Setup for error message label top! */
        estimateMessagesTop = new Label();
        estimateMessagesTop.setTextFill(Color.web("#f73636"));
        estimateMessagesTop.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        estimateMessagesTop.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!Objects.equals(newValue, "")) {

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> estimateMessagesTop.setText(""));
                    }
                }, 5000);
            }
        });

        estimateMessagesTop.setOnMouseEntered(event -> {
            estimateMessagesTop.setScaleX(1.20);
            estimateMessagesTop.setScaleY(1.20);
        });

        estimateMessagesTop.setOnMouseExited(event -> {
            estimateMessagesTop.setScaleX(1.00);
            estimateMessagesTop.setScaleY(1.00);
        });

        /* Setup for error message label bottom! */
        estimateMessagesBottom = new Label();
        estimateMessagesBottom.setTextFill(Color.web("#f73636"));
        estimateMessagesBottom.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        estimateMessagesBottom.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!Objects.equals(newValue, "")) {

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> estimateMessagesBottom.setText(""));
                    }
                }, 5000);
            }
        });

        estimateMessagesBottom.setOnMouseEntered(event -> {
            estimateMessagesBottom.setScaleX(1.20);
            estimateMessagesBottom.setScaleY(1.20);
        });

        estimateMessagesBottom.setOnMouseExited(event -> {
            estimateMessagesBottom.setScaleX(1.00);
            estimateMessagesBottom.setScaleY(1.00);
        });

        /* Conditions for the Close button to close the Estimate window! */
        Button closeButton = new Button("Cancel");
        closeButton.setStyle("-fx-font: 13 arial; -fx-base: #C0C0C0;");
        closeButton.setOnAction((ActionEvent e) -> {

            boolean cancelForm = confirmProgramClose.display("Estimate Form", window.getX(), window.getY(), window.getWidth(), window.getHeight());

            if (cancelForm) {

                estimateFormRunning = false;

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

            }
        });

        /* Conditions for the Save button to save the entered information for the Estimate form! */
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        saveButton.setOnAction((ActionEvent e) -> {

            if (textFillState) {

                checkEstimateDataInput = checkEstimateDataInput();
            }

            if (!textFillState || checkEstimateDataInput) {

                saveItemData();

                File saveFile = saveFileChooser(estimateData[2], "Save", fileNames.ESTIMATE);

                if (Objects.nonNull(saveFile)) {

                    try {

                        Statement statementInsertAuto = connectionMain.createStatement();
                        statementInsertAuto.setQueryTimeout(10);

                        for (String anEstimateData : estimateData) {

                            if (!Objects.equals(anEstimateData, "") && Objects.nonNull(anEstimateData) && !anEstimateData.equals(estimateData[1])
                                    && !anEstimateData.equals(estimateData[4])) {

                                if (!autoCompleteList.contains(anEstimateData)) {

                                    String insertAuto = String.format("INSERT INTO AUTO (WORD) VALUES ('%s')", anEstimateData);
                                    statementInsertAuto.execute(insertAuto);
                                    autoCompleteList.add(anEstimateData);
                                }
                            }
                        }

                        if (!addressList.contains(estimateData[4]) && !Objects.equals(estimateData[4], "") && Objects.nonNull(estimateData[4])) {

                            String insertAuto = String.format("INSERT INTO AUTO (ADDRESS) VALUES ('%s')", estimateData[4]);
                            statementInsertAuto.execute(insertAuto);
                            addressList.add(estimateData[4]);
                        }

                        statementInsertAuto.close();

                    } catch ( Exception e1 ) {
                        e1.printStackTrace();
                    }

                    if (inputWorkbookData.estimateExcel(estimateData, estimateItemNumber, saveFile.getAbsolutePath(), textFillState)) {

                        URL notificationImage = this.getClass().getResource("/sample/CustomerFiles/Images/Saved.gif");

                        TrayNotification notification = new TrayNotification("Estimate Formed Saved", "Saved To: " + saveFile.getAbsolutePath(), NotificationType.SUCCESS);
                        notification.setImage(new Image(String.valueOf(notificationImage)));
                        notification.setRectangleFill(Paint.valueOf("#46494f"));
                        notification.showAndDismiss(Duration.seconds(4));

                        estimateFormRunning = false;

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
                                    if (formOpenState) {
                                        Desktop.getDesktop().open(new File(saveFile.getAbsolutePath()));
                                    }
                                } catch ( IOException e1 ) {
                                    e1.printStackTrace();
                                }
                            }
                        }, 5000);
                    }
                }
            }
        });

        /* Conditions for the Calculate button to calculate the total cost of each item! */
        Button calCost = new Button("Calculate");
        calCost.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        calCost.setOnAction((ActionEvent e) -> estimateCalculate());

        /* Setup of Label to display the total cost of each item for an Estimate! */
        totalCost = new Label("$00.00");
        totalCost.setFont(Font.font("Arial", 16));

        Label total = new Label("Total:");
        total.setFont(Font.font("Arial", 16));

        /* Setup for the middle portion of the estimateLayout VBox that displays error estimateMessages! */
        VBox errorMessagesTop = new VBox(8);
        errorMessagesTop.setPadding(new Insets(4));
        errorMessagesTop.setAlignment(Pos.CENTER);
        errorMessagesTop.getChildren().add(estimateMessagesTop);
        errorMessagesTop.setStyle("-fx-font: 14 arial; -fx-base: #f73636;");

        /* Setup for the middle portion of the estimateLayout VBox that displays error estimateMessages! */
        VBox errorMessagesBottom = new VBox(8);
        errorMessagesBottom.setPadding(new Insets(4));
        errorMessagesBottom.setAlignment(Pos.CENTER);
        errorMessagesBottom.getChildren().add(estimateMessagesBottom);
        errorMessagesBottom.setStyle("-fx-font: 14 arial; -fx-base: #f73636;");

        /* Setup for the top portion of the estimateLayout VBox! */
        VBox dataInput = new VBox(4);
        dataInput.setPadding(new Insets(4));
        dataInput.getChildren().addAll(formLabel, errorMessagesTop, dqProjectHBox, dateHBox, invoiceHBox, nameAddressHBox, estimateItemsVBox);

        /* Setup for the Left Bottom of the bottom HBox! */
        HBox bottomLeft = new HBox(8);
        bottomLeft.setPadding(new Insets(10));
        bottomLeft.getChildren().addAll(calCost, total, totalCost);
        bottomLeft.setAlignment(Pos.CENTER_LEFT);
        bottomLeft.setMinHeight(65);

        /* Setup for the Right Bottom of the bottom HBox! */
        HBox bottomRight = new HBox(8);
        bottomRight.setPadding(new Insets(10));
        bottomRight.getChildren().addAll(closeButton, saveButton);
        bottomRight.setAlignment(Pos.CENTER_RIGHT);
        bottomRight.setMinHeight(65);
        HBox.setHgrow(bottomRight, Priority.ALWAYS);

        /* Setup for HBox that holds the bottom elements of the estimateLayout VBox! */
        HBox bottom = new HBox(8);
        bottom.getChildren().addAll(bottomLeft, bottomRight);

        /* Setup for the estimateLayout VBox and estimateScrollPane ScrollPane! */
        VBox estimateLayout = new VBox(0);
        estimateLayout.getChildren().addAll(dataInput, errorMessagesBottom, bottom);

        estimateScrollPane = new ScrollPane();
        estimateScrollPane.setContent(estimateLayout);
        estimateScrollPane.setFitToWidth(true);
        estimateScrollPane.setPrefHeight(window.heightProperty().doubleValue());

    }

    /* Method to check that data has been entered in each TextField of the Estimate form. If data hasn't been entered then an error message will be displayed! */
    private boolean checkEstimateDataInput() {

        String[] textFieldLabel = {"Item", "Description", "Quantity", "Admin Hours", "Admin Rate", "Material Per Part", "Material Mark up %", "Labor in Hours",
                "Labor Rate", "Outside Services1 Total Parts", "Outside Services2 Total Parts", "Outside Services1 Per Part", "Outside Services2 Per Part"};

        int count;
        int elementCount = 0;

        checkEstimateDataInput = false;

        if (itemQuantityText.getText().isEmpty()) {

            estimateMessagesTop.setText("Nothing was entered for Items Number");
            estimateMessagesBottom.setText("Nothing was entered for Items Number");

        } else if (estimateItemNumber == 0) {

            estimateMessagesTop.setText("No item number was ever entered");
            estimateMessagesBottom.setText("No item number was ever entered");

        } else if (dateText.getValue() == null) {

            estimateMessagesTop.setText("Date entered Incorrectly or not at all");
            estimateMessagesBottom.setText("Date entered Incorrectly or not at all");

        } else if (invoiceText.getText().isEmpty()) {

            estimateMessagesTop.setText("Nothing was entered for Invoice Number");
            estimateMessagesBottom.setText("Nothing was entered for Invoice Number");

        } else if (notesTextAreaEstimate.getText().isEmpty()) {

            estimateMessagesTop.setText("Nothing was entered for the Notes");
            estimateMessagesBottom.setText("Nothing was entered for the Notes");

        } else if (dqProjectText.getText().isEmpty()) {

            estimateMessagesTop.setText("Nothing was entered for a DQ Project Number");
            estimateMessagesBottom.setText("Nothing was entered for a DQ Project Number");

        } else if (companyNameText.getText().isEmpty()) {

            estimateMessagesTop.setText("Nothing was entered for Name");
            estimateMessagesBottom.setText("Nothing was entered for Name");

        } else if (addressText.getText().isEmpty()) {

            estimateMessagesTop.setText("Nothing was entered for Address");
            estimateMessagesBottom.setText("Nothing was entered for Address");

        } else if (leadTimeText.getText().isEmpty()){

            estimateMessagesTop.setText("Nothing was entered for a Lead Time");
            estimateMessagesBottom.setText("Nothing was entered for a Lead Time");

        } else {

            try {

                for (int x = 0; x < estimateItemNumber; x++) {

                    count = 0;

                    for (int y = elementCount; y < 195; y += 15) {

                        if (textFieldAreaEstimate[y].getText().isEmpty()) {

                            String errorMessage = textFieldLabel[count];

                            if (y <= 14) {

                                estimateMessagesTop.setText(String.format("Nothing was entered for %s %s", errorMessage, (x + 1)));
                                estimateMessagesBottom.setText(String.format("Nothing was entered for %s %s", errorMessage, (x + 1)));
                                throw new Exception();

                            } else {

                                estimateMessagesTop.setText(String.format("Nothing was entered for %s of Item %s", errorMessage, (x + 1)));
                                estimateMessagesBottom.setText(String.format("Nothing was entered for %s of Item %s", errorMessage, (x + 1)));
                                throw new Exception();
                            }
                        }

                        count++;
                    }

                    elementCount++;
                }

                checkEstimateDataInput = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!leadTimeText.getText().isEmpty() && checkEstimateDataInput) {

            if (!functions.checkNumberFormat(leadTimeText.getText())) {

                estimateMessagesTop.setText("Number for Lead Time was entered wrong");
                estimateMessagesBottom.setText("Number for Lead Time was entered wrong");

                checkEstimateDataInput = false;
            }
        }

        if (!dqProjectText.getText().isEmpty() && checkEstimateDataInput) {

            if (!functions.checkNumberFormat(dqProjectText.getText())) {

                estimateMessagesTop.setText("Number for DQ Project Number was entered wrong");
                estimateMessagesBottom.setText("Number for DQ Project Number was entered wrong");

                checkEstimateDataInput = false;
            }
        }

        if (!invoiceText.getText().isEmpty() && checkEstimateDataInput) {

            if (!create.invoiceFormat(invoiceText.getText())) {

                estimateMessagesTop.setText("Number for Invoice Number was entered wrong");
                estimateMessagesBottom.setText("Number for Invoice Number was entered wrong");

                checkEstimateDataInput = false;
            }
        }

        if (checkEstimateDataInput) {

            for (int x = 0; x < estimateItemNumber; x++) {

                count = 2;

                for (int y = (30 + x); y < 195; y += 15) {

                    if (!functions.checkNumberFormat(textFieldAreaEstimate[y].getText())) {

                        String errorMessage = textFieldLabel[count];

                        estimateMessagesTop.setText(String.format("The value for %s of Item %s was entered incorrectly", errorMessage, (x + 1)));
                        estimateMessagesBottom.setText(String.format("The value for %s of Item %s was entered incorrectly", errorMessage, (x + 1)));
                        checkEstimateDataInput = false;
                        break;
                    }

                    count++;
                }
            }
        }

        return checkEstimateDataInput;
    }

    /* Method to calculate to total cost of each item if the Estimate form! */
    @SuppressWarnings("ConstantConditions")
    private void estimateCalculate() {

        int elementCount = 0;
        double costTotalDouble = 00.00;

        /* String Array for doing error messages! */
        final String[] textFieldLabel = {"Quantity", "Admin Hours", "Admin Rate", "Material Per Part", "Material Mark up %", "Labor in Hours",
                "Labor Rate", "Outside Services 1", "Outside Services 2"};

        totalCost.setText("$00.00");

        if (estimateItemsGenerated) {

            try {

                for (int x = 0; x < estimateItemNumber; x++) {

                    for (int y = (30 + elementCount); y < 180; y += 15) {

                        if (y >= 150 && y < 165) { y += 15; }

                        if (!functions.checkNumberFormat(textFieldAreaEstimate[y].getText()) || textFieldAreaEstimate[y].getText().isEmpty()) {
                            throw new Exception(textFieldAreaEstimate[y].getText());
                        }
                    }

                    costTotalDouble += ((((Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 60].getText())) * Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 45].getText()))) /
                            Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 30].getText()))) + (Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 90].getText())) *
                            Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 75].getText()))) + (Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 105].getText())) *
                            Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 120].getText()))) + (Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 135].getText()))) +
                            (Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 165].getText())))) * (Double.parseDouble(functions.removeComas(textFieldAreaEstimate[x + 30].getText()))));

                    elementCount++;
                }

                try {

                    estimateCalculationComplete = true;
                    totalCost.setText("$" + functions.addComasDouble(df.format(costTotalDouble)));

                } catch ( StringInputException e ) {
                    e.printStackTrace();
                }

            } catch (Exception e) {

                int count = 0;

                for (int y = (30 + elementCount); y < 180; y += 15) {

                    if (y >= 150 && y < 165) { y += 15; }

                    if (textFieldAreaEstimate[y].getText().equals(e.getMessage())) {

                        String errorMessage = textFieldLabel[count];

                        estimateMessagesTop.setText(String.format("Calculation Error: A value was inputted incorrectly for %s of Item %s!", errorMessage, elementCount + 1));
                        estimateMessagesBottom.setText(String.format("Calculation Error: A value was inputted incorrectly for %s of Item %s!", errorMessage, elementCount + 1));

                        break;
                    }

                    count++;
                }
            }

        } else {

            estimateMessagesTop.setText("No Items have been generated");
            estimateMessagesBottom.setText("No Items have been generated");
        }
    }

    /* Method to store entered information within the Estimate form! */
    @SuppressWarnings("ConstantConditions")
    private void saveItemData() {

        int elementCount = 0;
        int count = 7;

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

        if (!leadTimeText.getText().isEmpty()) {

            estimateData[5] = leadTimeText.getText();
        }

        if (!notesTextAreaEstimate.getText().isEmpty()) {

            estimateData[6] = notesTextAreaEstimate.getText();
        }

        for (int x = 0; x < estimateItemNumber; x++) {

            for (int y = elementCount; y < 195; y += 15) {

                estimateData[count] = textFieldAreaEstimate[y].getText();

                count++;
            }

            elementCount++;
        }
    }

    /* Method to delete the needed information out of the array that stores entered information within the Estimate form.
     * Method will either delete a certain set of information or delete all information! */
    private void deleteItemData(boolean deleteThis) {

        if (!deleteThis) {

            for (int x = ((estimateItemNumber * 13) + 7); x < ((estimateSavedItemNumber * 13) + 7); x++) {

                estimateData[x] = "";

            }
        }

        if (deleteThis) {

            for (int y = 0; y < 202; y++) {

                estimateData[y] = "";

            }
        }
    }

    /* Method that will create the Traveler form window! */
    private void travelerFormCreate() {
        
        final HBox[] hBoxItems = new HBox[]{travelerProcess1HBox, travelerProcess2HBox, travelerProcess3HBox, travelerProcess4HBox, travelerProcess5HBox,
                travelerProcess6HBox, travelerProcess7HBox, travelerProcess8HBox, travelerProcess9HBox};

        final Label[] labelNames = new Label[]{process1DescriptionLabel, process1PlannedHoursLabel, process1ActualHoursLabel, process1CompletedLabel,
                process2DescriptionLabel, process2PlannedHoursLabel, process2ActualHoursLabel, process2CompletedLabel,
                process3DescriptionLabel, process3PlannedHoursLabel, process3ActualHoursLabel, process3CompletedLabel,
                process4DescriptionLabel, process4PlannedHoursLabel, process4ActualHoursLabel, process4CompletedLabel,
                process5DescriptionLabel, process5PlannedHoursLabel, process5ActualHoursLabel, process5CompletedLabel,
                process6DescriptionLabel, process6PlannedHoursLabel, process6ActualHoursLabel, process6CompletedLabel,
                process7DescriptionLabel, process7PlannedHoursLabel, process7ActualHoursLabel, process7CompletedLabel,
                process8DescriptionLabel, process8PlannedHoursLabel, process8ActualHoursLabel, process8CompletedLabel,
                process9DescriptionLabel, process9PlannedHoursLabel, process9ActualHoursLabel, process9CompletedLabel};

        final String[] labelStrings = {"Description:", "Planned Hours:", "Actual Hours:", "Completed:"};
        
        travelerProcessNumber = 0;
        travelerSavedProcessNumber = 0;
        travelerProcessesGenerated = false; 
        travelerCalculationComplete = false;
        travelerFormRunning = true;
        deleteTravelerData(true);
        
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
        dateReceivedPicker.setConverter(new StringConverter<>() {

            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

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
        dateRequiredPicker.setConverter(new StringConverter<>() {

            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

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
        completedDatePicker.setConverter(new StringConverter<>() {

            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

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

            boolean enteredNumberPass = true;

            if (processesNumberText.getText().isEmpty()) {

                travelerMessages.setText("Nothing was entered for the Process Number");

            } else {

                if (functions.checkIntegerFormat(processesNumberText.getText())) {

                    int parseToInt = Integer.parseInt(processesNumberText.getText());

                    if (parseToInt <= 9 && parseToInt > 0) {

                        /* Variable for the Process number entered, stores the number entered int the travelerProcessNumber variable! */
                        travelerProcessNumber = parseToInt;

                        if (travelerProcessNumber < travelerSavedProcessNumber && travelerProcessesGenerated) {

                            deleteTravelerData(false);

                            if (travelerProcessesGenerated) {

                                if (travelerCalculationComplete) {

                                    /* Will re-calculate the total hours of each process when process number is decreased! */
                                    travelerHoursCalculate();
                                }
                            }
                        }

                    } else {

                        enteredNumberPass = false;

                        if (travelerProcessNumber == 0) {

                            travelerMessages.setText("Process number cannot be 0");

                        } else {

                            travelerMessages.setText("Process number entered was greater than the limit of 9");
                        }

                        if (travelerProcessesGenerated) {

                            processesNumberText.setText(String.valueOf(travelerProcessNumber));
                        }
                    }

                } else {

                    enteredNumberPass = false;
                    travelerMessages.setText("Number of processes was enter wrong");

                    if (travelerProcessesGenerated) {

                        processesNumberText.setText(String.valueOf(travelerProcessNumber));
                    }
                }
            }

            if (travelerProcessNumber <= 9 && travelerProcessNumber > 0 && enteredNumberPass) {

                if (travelerProcessNumber < travelerSavedProcessNumber) {

                    for (int g = travelerProcessNumber; g < travelerSavedProcessNumber; g++) {

                        travelerProcessesVBox.getChildren().remove(hBoxItems[g]);
                    }

                } else {

                    for (int x = travelerSavedProcessNumber; x < travelerProcessNumber; x++) {

                        hBoxItems[x] = new HBox(8);
                        hBoxItems[x].setPadding(new Insets(2));

                        for (int y = 0; y < 4; y++) {

                            labelNames[(x * 4) + y] = new Label(labelStrings[y]);
                            textFieldAreaTraveler[(y * 9) + x] = new TextField();
                            textFieldAreaTraveler[(y * 9) + x].setPrefWidth(65.0);
                            if (autoCompletionState) {
                                TextFields.bindAutoCompletion(textFieldAreaTraveler[(y * 9) + x], autoCompleteList);
                            }

                            hBoxItems[x].getChildren().addAll(labelNames[(x * 4) + y], textFieldAreaTraveler[(y * 9) + x]);
                        }

                        travelerProcessesVBox.getChildren().add(hBoxItems[x]);
                    }
                }

                travelerSavedProcessNumber = travelerProcessNumber;
                travelerProcessesGenerated = true;
            }
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
        calculateTimeButton.setOnAction(e -> travelerHoursCalculate());

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

            boolean cancelForm = confirmProgramClose.display("Traveler Form", window.getX(), window.getY(), window.getWidth(), window.getHeight());

            if (cancelForm) {

                travelerFormRunning = false;

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

            }
        });

        /* Conditions for the Save button to save the entered information for the Traveler form! */
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        saveButton.setOnAction((ActionEvent e) -> {

            if (textFillState) {

                checkTravelerDataInput = checkTravelerDataInput();
            }

            if (!textFillState || checkTravelerDataInput) {

                saveTravelerData();

                File saveFile = saveFileChooser(travelerData[0], "Save", fileNames.TRAVELER);

                if (Objects.nonNull(saveFile)) {

                    try {

                        Statement statementInsertAuto = connectionMain.createStatement();
                        statementInsertAuto.setQueryTimeout(10);

                        for (String aTravelerData : travelerData) {

                            if (!Objects.equals(aTravelerData, "") && Objects.nonNull(aTravelerData) && !aTravelerData.equals(travelerData[1])
                                    && !aTravelerData.equals(travelerData[5]) && !aTravelerData.equals(travelerData[7])) {

                                if (!autoCompleteList.contains(aTravelerData)) {

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

                    if (inputWorkbookData.travelerExcel(travelerData, travelerSavedProcessNumber, saveFile.getAbsolutePath(), textFillState)) {

                        URL notificationImage = this.getClass().getResource("/sample/CustomerFiles/Images/Saved.gif");

                        TrayNotification notification = new TrayNotification("Traveler Formed Saved", "Saved To: " + saveFile.getAbsolutePath(), NotificationType.SUCCESS);
                        notification.setImage(new Image(String.valueOf(notificationImage)));
                        notification.setRectangleFill(Paint.valueOf("#46494f"));
                        notification.showAndDismiss(Duration.seconds(4));

                        travelerFormRunning = false;

                        content.getChildren().removeAll(travelerScrollPane);
                        content.getChildren().add(contentPrompt);
                        content.setAlignment(Pos.CENTER);
                        contentPrompt.setText("Form Saved");

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> contentPrompt.setText("Select a form to complete"));
                                try {
                                    if (formOpenState) {
                                        Desktop.getDesktop().open(new File(saveFile.getAbsolutePath()));
                                    }
                                } catch ( IOException e1 ) {
                                    e1.printStackTrace();
                                }
                            }
                        }, 5000);
                    }
                }
            }
        });

        HBox bottom = new HBox(5);
        bottom.getChildren().addAll(closeButton, saveButton);
        bottom.setPadding(new Insets(5));
        bottom.setAlignment(Pos.CENTER_RIGHT);
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

        } else {

            try {

                for (int x = 0; x < travelerProcessNumber; x++) {

                    for (int y = 0; y < 2; y++) {

                        if (textFieldAreaTraveler[x].getText().isEmpty()) {

                            travelerMessages.setText(String.format("Nothing was entered for Process %s Description", (x + 1)));
                            throw new Exception();

                        } else if (textFieldAreaTraveler[(x + 9)].getText().isEmpty()) {

                            travelerMessages.setText(String.format("Nothing was enter for Process %s Planned Hours", (x + 1)));
                            throw new Exception();
                        }
                    }
                }

                checkTravelerDataInput = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!jobNumberText.getText().isEmpty() && checkTravelerDataInput) {

            if (!functions.checkNumberFormat(jobNumberText.getText())) {

                travelerMessages.setText("Number wasn't entered correctly for Job Number");
                checkTravelerDataInput = false;
            }
        }

        if (!partNumberText.getText().isEmpty() && checkTravelerDataInput) {

            if (!functions.checkNumberFormat(partNumberText.getText())) {

                travelerMessages.setText("Number wasn't entered correctly for Part Number");
                checkTravelerDataInput = false;
            }
        }

        if (!poNumberText.getText().isEmpty() && checkTravelerDataInput) {

            if (!functions.checkNumberFormat(poNumberText.getText())) {

                travelerMessages.setText("Number wasn't entered correctly for P.O. Number");
                checkTravelerDataInput = false;
            }
        }

        return checkTravelerDataInput;
    }

    /* Method that will calculate the total hours needed for a process! */
    private void travelerHoursCalculate() {

        int elementCount = 0;
        double hoursTotal = 0.0;

        plannedHoursTotalPartTime.setText("0.00");
        plannedHoursTotalTotalTime.setText("0.00");

        if (travelerProcessesGenerated) {

            try {

                for (int x = 0; x < travelerProcessNumber; x++) {

                    if(!functions.checkNumberFormat(textFieldAreaTraveler[(x + 9)].getText()) || textFieldAreaTraveler[(x + 9)].getText().isEmpty()) {
                        throw new Exception(textFieldAreaTraveler[(x + 9)].getText());
                    }

                    hoursTotal += Double.parseDouble(textFieldAreaTraveler[(x + 9)].getText());
                    elementCount++;
                }

                try {

                    if (quantityText.getText().isEmpty() || !functions.checkNumberFormat(quantityText.getText())) {

                        travelerMessages.setText("Nothing was entered for Quantity or the number for Quantity was entered wrong");

                    } else {

                        travelerCalculationComplete = true;
                        plannedHoursTotalPartTime.setText(functions.addComasDouble(df.format(hoursTotal)));
                        plannedHoursTotalTotalTime.setText(functions.addComasDouble(df.format((hoursTotal * Double.parseDouble(quantityText.getText())))));
                    }

                } catch (StringInputException e1) {
                    e1.printStackTrace();
                }

            } catch (Exception e) {

                travelerMessages.setText(String.format("Number was entered wrong for Process %s Planned Hours", (elementCount + 1)));
            }

        } else {

            travelerMessages.setText("No Process Items have been generated");
        }
    }

    /* Method to store entered information within the Traveler form! */
    @SuppressWarnings("ConstantConditions")
    private void saveTravelerData() {

        int count = 8;

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

        for (int x = 0; x < travelerProcessNumber; x++) {

            for (int y = 0; y < 4; y++) {

                travelerData[count] = textFieldAreaTraveler[(y * 9) + x].getText();

                count++;
            }
        }

        if (!notesTextAreaTraveler.getText().isEmpty()) {

            travelerData[44] = notesTextAreaTraveler.getText();
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

            for (int y = 0; y < 45; y++) {

                travelerData[y] = "";
            }
        }
    }

    /* Method that will create the Packing List form window! */
    private void packingListFormCreate() {

        final HBox[] hBoxItems = new HBox[]{packingListItem1HBox, packingListItem2HBox, packingListItem3HBox, packingListItem4HBox, packingListItem5HBox, packingListItem6HBox, packingListItem7HBox, packingListItem8HBox,
                packingListItem9HBox, packingListItem10HBox, packingListItem11HBox, packingListItem12HBox, packingListItem13HBox, packingListItem14HBox, packingListItem15HBox, packingListItem16HBox,
                packingListItem17HBox, packingListItem18HBox, packingListItem19HBox, packingListItem20HBox, packingListItem21HBox, packingListItem22HBox, packingListItem23HBox, packingListItem24HBox};

        final Label[] labelNames = new Label[]{item1NumberLabel, item1DescriptionLabel, item1QuantityLabel, item2NumberLabel, item2DescriptionLabel, item2QuantityLabel,
                item3NumberLabel, item3DescriptionLabel, item3QuantityLabel, item4NumberLabel, item4DescriptionLabel, item4QuantityLabel,
                item5NumberLabel, item5DescriptionLabel, item5QuantityLabel, item6NumberLabel, item6DescriptionLabel, item6QuantityLabel,
                item7NumberLabel, item7DescriptionLabel, item7QuantityLabel, item8NumberLabel, item8DescriptionLabel, item8QuantityLabel,
                item9NumberLabel, item9DescriptionLabel, item9QuantityLabel, item10NumberLabel, item10DescriptionLabel, item10QuantityLabel,
                item11NumberLabel, item11DescriptionLabel, item11QuantityLabel, item12NumberLabel, item12DescriptionLabel, item12QuantityLabel,
                item13NumberLabel, item13DescriptionLabel, item13QuantityLabel, item14NumberLabel, item14DescriptionLabel, item14QuantityLabel,
                item15NumberLabel, item15DescriptionLabel, item15QuantityLabel, item16NumberLabel, item16DescriptionLabel, item16QuantityLabel,
                item17NumberLabel, item17DescriptionLabel, item17QuantityLabel, item18NumberLabel, item18DescriptionLabel, item18QuantityLabel,
                item19NumberLabel, item19DescriptionLabel, item19QuantityLabel, item20NumberLabel, item20DescriptionLabel, item20QuantityLabel,
                item21NumberLabel, item21DescriptionLabel, item21QuantityLabel, item22NumberLabel, item22DescriptionLabel, item22QuantityLabel,
                item23NumberLabel, item23DescriptionLabel, item23QuantityLabel, item24NumberLabel, item24DescriptionLabel, item24QuantityLabel};

        final String[] labelStrings = {"Item Number:", "Description:", "Quantity:"};

        packingListItemNumber = 0;
        packingListSavedItemNumber = 0;
        packingListItemsGenerated = false;
        packingListFormRunning = true;
        deletePackingListData(true);

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
        orderDatePackingList.setConverter(new StringConverter<>() {

            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

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

            boolean enteredNumberPass = true;

            if (itemsNumberPackingList.getText().isEmpty()) {

                packingListMessages.setText("Nothing was entered for the Items Number");

            } else {

                if (functions.checkIntegerFormat(itemsNumberPackingList.getText())) {

                    int parseToInt = Integer.parseInt(itemsNumberPackingList.getText());

                    if (parseToInt <= 24 && parseToInt > 0) {

                        packingListItemNumber = parseToInt;

                        if (packingListItemNumber > packingListSavedItemNumber && packingListItemsGenerated) {

                            deletePackingListData(false);
                        }

                    } else {

                        enteredNumberPass = false;

                        if (packingListItemNumber == 0) {

                            packingListMessages.setText("Items number cannot be 0");

                        } else {

                            packingListMessages.setText("Items number entered was greater than the limit of 24");
                        }

                        if (packingListItemsGenerated) {

                            itemsNumberPackingList.setText(String.valueOf(packingListItemNumber));
                        }
                    }

                } else {

                    enteredNumberPass = false;
                    packingListMessages.setText("Number of items was enter wrong");

                    if (packingListItemsGenerated) {

                        itemsNumberPackingList.setText(String.valueOf(packingListItemNumber));
                    }

                }
            }

            if (packingListItemNumber <= 24 && packingListItemNumber > 0 && enteredNumberPass) {

                if (packingListItemNumber < packingListSavedItemNumber) {

                    for (int g = packingListItemNumber; g < packingListSavedItemNumber; g++) {

                        packingListItemsVBox.getChildren().remove(hBoxItems[g]);
                    }

                } else {

                    for (int x = packingListSavedItemNumber; x < packingListItemNumber; x++) {

                        hBoxItems[x] = new HBox(10);
                        hBoxItems[x].setPadding(new Insets(2));

                        for (int y = 0; y < 3; y++) {

                            labelNames[(x * 3) + y] = new Label(labelStrings[y]);
                            textFieldAreaPackingList[(y * 24) + x] = new TextField();
                            if (y == 1) {
                                textFieldAreaPackingList[(y * 24) + x].setPrefWidth(200.0);
                            } else {
                                textFieldAreaPackingList[(y * 24) + x].setPrefWidth(100.0);
                            }
                            if (autoCompletionState) {
                                TextFields.bindAutoCompletion(textFieldAreaPackingList[(y * 24) + x], autoCompleteList);
                            }

                            hBoxItems[x].getChildren().addAll(labelNames[(x * 3) + y], textFieldAreaPackingList[(y * 24) + x]);
                        }

                        packingListItemsVBox.getChildren().add(hBoxItems[x]);
                    }
                }

                packingListSavedItemNumber = packingListItemNumber;
                packingListItemsGenerated = true;
            }
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

            boolean cancelForm = confirmProgramClose.display("Packing List Form", window.getX(), window.getY(), window.getWidth(), window.getHeight());

            if (cancelForm) {

                packingListFormRunning = false;

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

            }
        });

        /* Conditions for the Save button to save the entered information for the Packing List form! */
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font: 13 arial; -fx-base: #b4ffa3;");
        saveButton.setOnAction((ActionEvent e) -> {

            if (textFillState) {

                checkPackingListDataInput = checkPackingListDataInput();
            }

            if (!textFillState || checkPackingListDataInput) {

                savePackingListData();

                File saveFile = saveFileChooser(packingListData[3], "Save", fileNames.PACKINGLIST);

                if (Objects.nonNull(saveFile)) {

                    try {

                        Statement statementInsertAuto = connectionMain.createStatement();
                        statementInsertAuto.setQueryTimeout(10);

                        for (String aPackingListData : packingListData) {

                            if (!Objects.equals(aPackingListData, "") && Objects.nonNull(aPackingListData) && !aPackingListData.equals(packingListData[0])
                                    && !aPackingListData.equals(packingListData[1]) && !aPackingListData.equals(packingListData[2])) {

                                if (!autoCompleteList.contains(aPackingListData)) {

                                    String insertAuto = String.format("INSERT INTO AUTO (WORD) VALUES ('%s')", aPackingListData);
                                    statementInsertAuto.execute(insertAuto);
                                    autoCompleteList.add(aPackingListData);
                                }
                            }
                        }

                        if ((!Objects.equals(packingListData[0], "") && Objects.nonNull(packingListData[0]))) {

                            if (!addressList.contains(packingListData[0])) {

                                String insertAuto = String.format("INSERT INTO AUTO (ADDRESS) VALUES ('%s')", packingListData[0]);
                                statementInsertAuto.execute(insertAuto);
                                addressList.add(packingListData[0]);
                            }
                        }

                        if (!Objects.equals(packingListData[1], "") && Objects.nonNull(packingListData[1])) {

                            if (!addressList.contains(packingListData[1])) {

                                String insertAuto = String.format("INSERT INTO AUTO (ADDRESS) VALUES ('%s')", packingListData[1]);
                                statementInsertAuto.execute(insertAuto);
                                addressList.add(packingListData[1]);
                            }
                        }

                        statementInsertAuto.close();

                    } catch ( Exception e1 ) {
                        e1.printStackTrace();
                    }

                    if (inputWorkbookData.packingListExcel(packingListData, packingListSavedItemNumber, saveFile.getAbsolutePath(), textFillState)) {

                        URL notificationImage = this.getClass().getResource("/sample/CustomerFiles/Images/Saved.gif");

                        TrayNotification notification = new TrayNotification("Packing List Formed Saved", "Saved To: " + saveFile.getAbsolutePath(), NotificationType.SUCCESS);
                        notification.setImage(new Image(String.valueOf(notificationImage)));
                        notification.setRectangleFill(Paint.valueOf("#46494f"));
                        notification.showAndDismiss(Duration.seconds(4));

                        packingListFormRunning = false;

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
                                    if (formOpenState) {
                                        Desktop.getDesktop().open(new File(saveFile.getAbsolutePath()));
                                    }
                                } catch ( IOException e1 ) {
                                    e1.printStackTrace();
                                }
                            }
                        }, 5000);
                    }
                }
            }
        });

        HBox bottom = new HBox(5);
        bottom.getChildren().addAll(closeButton, saveButton);
        bottom.setPadding(new Insets(5));
        bottom.setAlignment(Pos.CENTER_RIGHT);
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

        final String[] textFieldLabel = {"Number", "Description", "Quantity"};

        boolean checkPackingListDataInput = false;

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

        } else {

            try {

                for (int x = 0; x < packingListItemNumber; x++) {

                    for (int y = 0; y < 3; y++) {

                        if (textFieldAreaPackingList[(y * 24) + x].getText().isEmpty()) {

                            packingListMessages.setText(String.format("Nothing was entered for Item %s %s", (x + 1), textFieldLabel[y]));
                            throw new Exception();
                        }
                    }
                }

                checkPackingListDataInput = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (checkPackingListDataInput) {

            if (!functions.checkNumberFormat(orderNumberPackingList.getText())) {

                packingListMessages.setText("Number wasn't entered correctly for Order Number");
                checkPackingListDataInput = false;
            }

            if (!functions.checkNumberFormat(jobPackingList.getText())) {

                    packingListMessages.setText("Number wasn't entered correctly for Job Number");
                    checkPackingListDataInput = false;
            }

            if (packingListItemsGenerated && checkPackingListDataInput) {

                for (int z = 0; z < packingListItemNumber; z++) {

                    if (!functions.checkNumberFormat(textFieldAreaPackingList[(z + 48)].getText())) {

                        packingListMessages.setText(String.format("Number wasn't entered correctly for Item %s Quantity", (z + 1)));
                        checkPackingListDataInput = false;
                    }
                }
            }
        }

        return checkPackingListDataInput;
    }

    /* Method to store entered information within the Packing List form! */
    @SuppressWarnings("ConstantConditions")
    private void savePackingListData() {

        int count = 5;

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

        for (int x = 0; x < packingListItemNumber; x++) {

            for (int y = 0; y < 3; y++) {

                packingListData[count] = textFieldAreaPackingList[(y * 24) + x].getText();

                count++;
            }
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

            for (int y = 0; y < 77; y++) {

                packingListData[y] = "";
            }
        }
    }

    //private void inputSalesReceipt() {}

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
        bottom.setAlignment(Pos.CENTER_RIGHT);
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
        devWindow.heightProperty().addListener(observable -> devWindow.setY(((window.getHeight() - devWindow.getHeight()) / 2d) + window.getY()));
        devWindow.widthProperty().addListener(observable -> devWindow.setX(((window.getWidth() - devWindow.getWidth()) / 2d) + window.getX()));
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
        estimateLocationFilePath.setText(defaultDirectories[0]);

        Label estimateFile = new Label("Estimate Form Default Path");
        estimateFile.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button estimateFileChoose = new Button("Choose");
        estimateFileChoose.setStyle("-fx-font: 14 arial; -fx-base: #b4ffa3;");
        estimateFileChoose.setOnAction(e -> {

            File estimateLocationChoose = saveFileChooser("", "Open", fileNames.ESTIMATE);

            if (!Objects.equals(estimateLocationChoose, null)) {

                try {

                    String delete = "DELETE FROM SETINFO";
                    String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", functions.fileRemoveExtension(estimateLocationChoose), defaultDirectories[1], defaultDirectories[2], autoCompletionState, textFillState, formOpenState);

                    Statement statement = connectionMain.createStatement();
                    statement.setQueryTimeout(10);
                    statement.executeUpdate(delete);
                    statement.executeUpdate(filePath);
                    statement.close();

                    defaultDirectories[0] = functions.fileRemoveExtension(estimateLocationChoose);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                estimateLocationFilePath.setText(defaultDirectories[0]);
            }
        });

        travelerLocationFilePath = new Label("");
        travelerLocationFilePath.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        travelerLocationFilePath.setTextFill(Color.web("#f73636"));
        travelerLocationFilePath.setText(defaultDirectories[1]);

        Label travelerFile = new Label("Traveler Form Default Path");
        travelerFile.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button travelerFileChoose = new Button("Choose");
        travelerFileChoose.setStyle("-fx-font: 14 arial; -fx-base: #b4ffa3;");
        travelerFileChoose.setOnAction(e -> {

            File travelerLocationChoose = saveFileChooser("", "Open", fileNames.TRAVELER);

            if (!Objects.equals(travelerLocationChoose, null)) {

                try {

                    String delete = "DELETE FROM SETINFO";
                    String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", defaultDirectories[0], functions.fileRemoveExtension(travelerLocationChoose), defaultDirectories[2], autoCompletionState, textFillState, formOpenState);

                    Statement statement = connectionMain.createStatement();
                    statement.setQueryTimeout(10);
                    statement.executeUpdate(delete);
                    statement.executeUpdate(filePath);
                    statement.close();

                    defaultDirectories[1] = functions.fileRemoveExtension(travelerLocationChoose);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                travelerLocationFilePath.setText(defaultDirectories[1]);
            }
        });

        packingListLocationFilePath = new Label("");
        packingListLocationFilePath.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        packingListLocationFilePath.setTextFill(Color.web("#f73636"));
        packingListLocationFilePath.setText(defaultDirectories[2]);

        Label packingListFile = new Label("Packing List Form Default Path");
        packingListFile.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button packingListFileChoose = new Button("Choose");
        packingListFileChoose.setStyle("-fx-font: 14 arial; -fx-base: #b4ffa3;");
        packingListFileChoose.setOnAction(e -> {

            File packingListLocationChoose = saveFileChooser("", "Open", fileNames.PACKINGLIST);

            if (!Objects.equals(packingListLocationChoose, null)) {

                try {

                    String delete = "DELETE FROM SETINFO";
                    String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", defaultDirectories[0], defaultDirectories[1], functions.fileRemoveExtension(packingListLocationChoose), autoCompletionState, textFillState, formOpenState);

                    Statement statement = connectionMain.createStatement();
                    statement.setQueryTimeout(10);
                    statement.executeUpdate(delete);
                    statement.executeUpdate(filePath);
                    statement.close();

                    defaultDirectories[2] = functions.fileRemoveExtension(packingListLocationChoose);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                packingListLocationFilePath.setText(defaultDirectories[2]);
            }
        });

        Label formOpenCheck = new Label("Enable Form Opening");
        formOpenCheck.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        SwitchToggle enableFormOpening = new SwitchToggle("ON", Color.web("#a7ef88"), "OFF", Color.web("#aeb0b2"), TransitionType.BUZZ);
        enableFormOpening.setSelected(formOpenState);
        enableFormOpening.switchOnProperty().addListener((observable, oldValue, newValue) -> {

            if (estimateFormRunning || travelerFormRunning || packingListFormRunning) {

                enableFormOpening.setSelected(formOpenState);

                if (estimateFormRunning) {

                    settingsMessages.setText("Estimate From Must Be Closed");

                } else if (travelerFormRunning) {

                    settingsMessages.setText("Traveler From Must Be Closed");

                } else {

                    settingsMessages.setText("Packing List From Must Be Closed");
                }

            } else {

                try {

                    String delete = "DELETE FROM SETINFO";
                    String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", defaultDirectories[0], defaultDirectories[1], defaultDirectories[2], autoCompletionState, textFillState, String.valueOf(enableFormOpening.isSelected()));

                    Statement statement = connectionMain.createStatement();
                    statement.setQueryTimeout(10);
                    statement.executeUpdate(delete);
                    statement.executeUpdate(filePath);
                    statement.close();

                    formOpenState = enableFormOpening.isSelected();

                } catch ( Exception e1 ) {
                    e1.printStackTrace();
                }
            }
        });

        Label textFillCheck = new Label("Enable TextField Check");
        textFillCheck.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        SwitchToggle enableTextFill = new SwitchToggle("ON", Color.web("#a7ef88"), "OFF", Color.web("#aeb0b2"), TransitionType.BUZZ);
        enableTextFill.setSelected(textFillState);
        enableTextFill.switchOnProperty().addListener((observable, oldValue, newValue) -> {

            if (estimateFormRunning || travelerFormRunning || packingListFormRunning) {

                enableTextFill.setSelected(textFillState);

                if (estimateFormRunning) {

                    settingsMessages.setText("Estimate From Must Be Closed");

                } else if (travelerFormRunning) {

                    settingsMessages.setText("Traveler From Must Be Closed");

                } else {

                    settingsMessages.setText("Packing List From Must Be Closed");
                }

            } else {

                try {

                    String delete = "DELETE FROM SETINFO";
                    String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", defaultDirectories[0], defaultDirectories[1], defaultDirectories[2], autoCompletionState, String.valueOf(enableTextFill.isSelected()), formOpenState);

                    Statement statement = connectionMain.createStatement();
                    statement.setQueryTimeout(10);
                    statement.executeUpdate(delete);
                    statement.executeUpdate(filePath);
                    statement.close();

                    textFillState = enableTextFill.isSelected();

                } catch ( Exception e1 ) {
                    e1.printStackTrace();
                }
            }
        });

        Label autoCompleteEnable = new Label("Enable Line Autocompletion");
        autoCompleteEnable.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        SwitchToggle enableAutoComplete = new SwitchToggle("ON", Color.web("#a7ef88"), "OFF", Color.web("#aeb0b2"), TransitionType.BUZZ);
        enableAutoComplete.setSelected(autoCompletionState);
        enableAutoComplete.switchOnProperty().addListener((observable, oldValue, newValue) -> {

            if (estimateFormRunning || travelerFormRunning || packingListFormRunning) {

                enableAutoComplete.setSelected(autoCompletionState);

                if (estimateFormRunning) {

                    settingsMessages.setText("Estimate From Must Be Closed");

                } else if (travelerFormRunning) {

                    settingsMessages.setText("Traveler From Must Be Closed");

                } else {

                    settingsMessages.setText("Packing List From Must Be Closed");
                }

            } else {

                try {

                    String delete = "DELETE FROM SETINFO";
                    String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", defaultDirectories[0], defaultDirectories[1], defaultDirectories[2], String.valueOf(enableAutoComplete.isSelected()), textFillState, formOpenState);

                    Statement statement = connectionMain.createStatement();
                    statement.setQueryTimeout(10);
                    statement.executeUpdate(delete);
                    statement.executeUpdate(filePath);
                    statement.close();

                    autoCompletionState = enableAutoComplete.isSelected();

                } catch ( Exception e1 ) {
                    e1.printStackTrace();
                }
            }
        });

        /* List to store all auto fill data for checking to be deleted! */
        List<String> deleteList = new ArrayList<>();
        deleteList.addAll(autoCompleteList);
        deleteList.addAll(addressList);

        Label deleteAutoCompletionWord = new Label("Remove From Auto Completion");
        deleteAutoCompletionWord.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        final TextField[] searchWord = {new TextField()};
        searchWord[0].setPrefWidth(130);
        TextFields.bindAutoCompletion(searchWord[0], deleteList);
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

                if (autoCompleteList.contains(searchWord[0].getText()) || addressList.contains(searchWord[0].getText())) {

                    exist = true;
                }

                if (!exist) {

                    settingsMessages.setText("Not In Auto Complete List");

                } else {

                    deleteList.removeAll(autoCompleteList);
                    deleteList.removeAll(addressList);

                    Collection<String> collAuto = autoCompleteList;
                    collAuto.removeIf(Predicate.isEqual(searchWord[0].getText()));
                    collAuto.removeIf(Predicate.isEqual("null"));

                    Collection<String> collAddress = addressList;
                    collAddress.removeIf(Predicate.isEqual(searchWord[0].getText()));
                    collAddress.removeIf(Predicate.isEqual("null"));

                    deleteList.addAll(autoCompleteList);
                    deleteList.addAll(addressList);

                    try {

                        String deleteAuto = "DELETE FROM AUTO";

                        Statement statementRedoAuto = connectionMain.createStatement();
                        statementRedoAuto.setQueryTimeout(10);
                        statementRedoAuto.executeUpdate(deleteAuto);

                        for (String anAutoCompleteList : autoCompleteList) {

                            String insertAutoWord = String.format("INSERT INTO AUTO (WORD) VALUES ('%s')", anAutoCompleteList);
                            statementRedoAuto.execute(insertAutoWord);
                        }

                        for (String anAddressList : addressList) {

                            String insertAutoAddress = String.format("INSERT INTO AUTO (ADDRESS) VALUES ('%s')", anAddressList);
                            statementRedoAuto.execute(insertAutoAddress);
                        }

                        statementRedoAuto.close();

                        searchWord[0].setText("");
                        settingsMessages.setText("Removed From Auto Complete List");

                        removeAutoCompletionWordLeft.getChildren().removeAll(deleteAutoCompletionWord, searchWord[0], deleteWord);
                        searchWord[0] = new TextField();
                        searchWord[0].setPrefWidth(130);
                        TextFields.bindAutoCompletion(searchWord[0], deleteList);
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
        estimateRow1First.setAlignment(Pos.CENTER_LEFT);

        HBox estimateRow1Second = new HBox(5);
        estimateRow1Second.getChildren().addAll(estimateFileChoose);
        estimateRow1Second.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(estimateRow1Second, Priority.ALWAYS);

        HBox estimateRow = new HBox(5);
        estimateRow.getChildren().addAll(estimateRow1First, estimateRow1Second);
        estimateRow.setPadding(new Insets(10));
        estimateRow.setAlignment(Pos.CENTER_LEFT);

        HBox estimateLocationPath = new HBox(5);
        estimateLocationPath.getChildren().addAll(estimateLocationFilePath);
        estimateLocationPath.setPadding(new Insets(10));
        estimateLocationPath.setAlignment(Pos.CENTER_LEFT);

        HBox travelerRow1First = new HBox(5);
        travelerRow1First.getChildren().addAll(travelerFile);
        travelerRow1First.setAlignment(Pos.CENTER_LEFT);

        HBox travelerRow1Second = new HBox(5);
        travelerRow1Second.getChildren().addAll(travelerFileChoose);
        travelerRow1Second.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(travelerRow1Second, Priority.ALWAYS);

        HBox travelerRow = new HBox(5);
        travelerRow.getChildren().addAll(travelerRow1First, travelerRow1Second);
        travelerRow.setPadding(new Insets(10));
        travelerRow.setAlignment(Pos.CENTER_LEFT);

        HBox travelerLocationPath = new HBox(5);
        travelerLocationPath.getChildren().addAll(travelerLocationFilePath);
        travelerLocationPath.setPadding(new Insets(10));
        travelerLocationPath.setAlignment(Pos.CENTER_LEFT);

        HBox packingListRow1First = new HBox(5);
        packingListRow1First.getChildren().addAll(packingListFile);
        packingListRow1First.setAlignment(Pos.CENTER_LEFT);

        HBox packingListRow1Second = new HBox(5);
        packingListRow1Second.getChildren().addAll(packingListFileChoose);
        packingListRow1Second.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(packingListRow1Second, Priority.ALWAYS);

        HBox packingListRow = new HBox(5);
        packingListRow.getChildren().addAll(packingListRow1First, packingListRow1Second);
        packingListRow.setPadding(new Insets(10));
        packingListRow.setAlignment(Pos.CENTER_LEFT);

        HBox packingListLocationPath = new HBox(5);
        packingListLocationPath.getChildren().addAll(packingListLocationFilePath);
        packingListLocationPath.setPadding(new Insets(10));
        packingListLocationPath.setAlignment(Pos.CENTER_LEFT);

        HBox openState = new HBox(5);
        openState.getChildren().addAll(formOpenCheck, enableFormOpening);
        openState.setPadding(new Insets(10));
        openState.setAlignment(Pos.CENTER_LEFT);

        HBox fillCompletion = new HBox(5);
        fillCompletion.getChildren().addAll(textFillCheck, enableTextFill);
        fillCompletion.setPadding(new Insets(10));
        fillCompletion.setAlignment(Pos.CENTER_LEFT);

        HBox autoCompletion = new HBox(5);
        autoCompletion.getChildren().addAll(autoCompleteEnable, enableAutoComplete);
        autoCompletion.setPadding(new Insets(10));
        autoCompletion.setAlignment(Pos.CENTER_LEFT);

        removeAutoCompletionWordLeft.getChildren().addAll(deleteAutoCompletionWord, searchWord[0], deleteWord);
        removeAutoCompletionWordLeft.setAlignment(Pos.CENTER_LEFT);

        HBox removeAutoCompletionWordRight = new HBox(5);
        removeAutoCompletionWordRight.getChildren().addAll(settingsMessages);
        removeAutoCompletionWordRight.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(removeAutoCompletionWordRight, Priority.ALWAYS);

        HBox removeAutoCompletion = new HBox(5);
        removeAutoCompletion.getChildren().addAll(removeAutoCompletionWordLeft, removeAutoCompletionWordRight);
        removeAutoCompletion.setPadding(new Insets(10));
        removeAutoCompletion.setAlignment(Pos.CENTER_LEFT);

        HBox settingsBottom = new HBox(5);
        settingsBottom.getChildren().addAll(closeSettings);
        settingsBottom.setAlignment(Pos.BOTTOM_CENTER);

        VBox settingsLayout = new VBox(5);
        settingsLayout.setPadding(new Insets(5));
        settingsLayout.getChildren().addAll(pageLabel, estimateRow, estimateLocationPath, travelerRow, travelerLocationPath, packingListRow, packingListLocationPath, openState, fillCompletion, autoCompletion, removeAutoCompletion, settingsBottom);

        /* Setup for settingsScrollPane ScrollPane! */
        settingsScrollPane = new ScrollPane();
        settingsScrollPane.setContent(settingsLayout);
        settingsScrollPane.setFitToWidth(true);
        settingsScrollPane.setPrefHeight(window.heightProperty().doubleValue());

    }

    /* Names for types of files possible for generation! */
    public enum fileNames {
        ESTIMATE,
        TRAVELER,
        PACKINGLIST
    }

    /* Method that displays a FileChooser for selecting where to save a generated file on the users device! */
    private File saveFileChooser(String fileName, String operation, fileNames function) {

        File saveFile = new File("");
        int arrayLocation = 0;

        switch (function) {

            case ESTIMATE:

                arrayLocation = 0;
                break;

            case TRAVELER:

                arrayLocation = 1;
                break;

            case PACKINGLIST:

                arrayLocation = 2;
                break;
        }

        if (Objects.equals(operation, "Save")) {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(fileName);
            fileChooser.setInitialDirectory(new File(defaultDirectories[arrayLocation]));

            fileChooser.setTitle("Chose Saved File Location");
            saveFile = fileChooser.showSaveDialog(window);

        } else if (Objects.equals(operation, "Open")) {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("Create");
            fileChooser.setInitialDirectory(new File(defaultDirectories[arrayLocation]));

            fileChooser.setTitle("Chose Default Location");
            saveFile = fileChooser.showSaveDialog(window);
        }

        return saveFile;
    }

    /* Method that will establish a connectionMain to the local database or create the local database if it doesn't exist! */
    private void initDatabase () {

        String environment = System.getProperty("os.name");
        String home = System.getProperty("user.home");
        String databaseFileLocation = "";

        InputStream fileInput = this.getClass().getResourceAsStream("/sample/CustomerFiles/Databases/DesignQuest3.sqlite");
        OutputStream fileOutput;

        byte[] buffer = new byte[1028];

        boolean check = false;
        boolean fileCopied = false;

        int length;

        if (environment.startsWith("Mac") || environment.startsWith("Windows")) {

            check = new File(home + "/DesignQuest", "DesignQuest3.sqlite").exists();
            databaseFileLocation = (home + "/DesignQuest/DesignQuest3.sqlite");
        }

        if (!check) {

            if (environment.startsWith("Mac") || environment.startsWith("Windows")) {

                try {

                    Path basePath = Paths.get(home + "/DesignQuest");
                    Files.createDirectories(basePath);

                    try {

                        File copyFile = new File(databaseFileLocation);

                        if (copyFile.createNewFile()) {

                            fileOutput = new FileOutputStream(copyFile);

                            while ((length = fileInput.read(buffer)) > 0) {

                                fileOutput.write(buffer, 0, length);
                            }

                            fileOutput.flush();

                            if (Objects.nonNull(fileOutput)) { fileOutput.close(); }

                            if (Objects.nonNull(fileInput)) { fileInput.close(); }

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
                connectionMain = DriverManager.getConnection(connStr, config.toProperties());

            } catch (Exception e) {

                e.printStackTrace();

                connectionMain = null;

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
    private void initDatabaseData() {

        try {

            if (Objects.nonNull(connectionMain)) {

                if (new File(System.getProperty("user.home") + "/DesignQuest", "DesignQuest.sqlite").exists()) {

                    try {

                        File getPath = new File(String.format(System.getProperty("user.home") + "%s", "/DesignQuest/DesignQuest.sqlite"));
                        String connStr = String.format("jdbc:sqlite:%s", getPath.getAbsolutePath());

                        Connection connectionPrevious1; /* Connection variable used to store the local database connectionPrevious1 information! */

                        try {

                            SQLiteConfig config = new SQLiteConfig();
                            config.setEncoding(SQLiteConfig.Encoding.UTF8);
                            Class.forName("org.sqlite.JDBC");
                            connectionPrevious1 = DriverManager.getConnection(connStr, config.toProperties());

                        } catch (Exception e) {

                            e.printStackTrace();

                            connectionPrevious1 = null;

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

                        if (Objects.nonNull(connectionPrevious1)) {

                            String createTableSetInfo = "CREATE TABLE IF NOT EXISTS SETINFO (ID INTEGER PRIMARY KEY AUTOINCREMENT, ESTIMATEPATH TEXT(1000), TRAVELERPATH TEXT(1000), PACKINGLISTPATH TEXT (1000), STATE TEXT(7), FILL TEXT(7), OPEN TEXT(7))";
                            String createTableAuto = "CREATE TABLE IF NOT EXISTS AUTO (ID INTEGER PRIMARY KEY AUTOINCREMENT, WORD TEXT(1000), ADDRESS(1000))";

                            Statement statementSetInfo = connectionMain.createStatement();
                            statementSetInfo.setQueryTimeout(10);
                            statementSetInfo.execute(createTableSetInfo);
                            statementSetInfo.close();

                            Statement statementAuto = connectionMain.createStatement();
                            statementAuto.setQueryTimeout(10);
                            statementAuto.execute(createTableAuto);
                            statementAuto.close();

                            String selectTableSetInfo = "SELECT * FROM SETINFO";

                            Statement querySetInfo = connectionPrevious1.createStatement();
                            ResultSet rsSetInfo = querySetInfo.executeQuery(selectTableSetInfo);

                            String delete = "DELETE FROM SETINFO";
                            String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", rsSetInfo.getString(1), rsSetInfo.getString(2), rsSetInfo.getString(3), rsSetInfo.getString(4), "false", "false");

                            querySetInfo.close();

                            Statement statement = connectionMain.createStatement();
                            statement.setQueryTimeout(10);
                            statement.executeUpdate(delete);
                            statement.executeUpdate(filePath);
                            statement.close();

                            String selectTableAuto = "SELECT * FROM AUTO";

                            Statement queryAuto = connectionPrevious1.createStatement();
                            ResultSet rsAuto = queryAuto.executeQuery(selectTableAuto);

                            Statement statementInsertAuto = connectionMain.createStatement();

                            while (rsAuto.next()) {

                                String insertAuto = String.format("INSERT INTO AUTO (WORD) VALUES ('%s')", rsAuto.getString(1));
                                statementInsertAuto.execute(insertAuto);
                            }

                            queryAuto.close();
                            statementInsertAuto.close();

                            connectionPrevious1.close();

                            Files.deleteIfExists(Paths.get(System.getProperty("user.home") + "/DesignQuest/DesignQuest.sqlite"));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (new File(System.getProperty("user.home") + "/DesignQuest", "DesignQuest2.sqlite").exists()) {

                    try {

                        File getPath = new File(String.format(System.getProperty("user.home") + "%s", "/DesignQuest/DesignQuest2.sqlite"));
                        String connStr = String.format("jdbc:sqlite:%s", getPath.getAbsolutePath());

                        Connection connectionPrevious1; /* Connection variable used to store the local database connectionPrevious1 information! */

                        try {

                            SQLiteConfig config = new SQLiteConfig();
                            config.setEncoding(SQLiteConfig.Encoding.UTF8);
                            Class.forName("org.sqlite.JDBC");
                            connectionPrevious1 = DriverManager.getConnection(connStr, config.toProperties());

                        } catch (Exception e) {

                            e.printStackTrace();

                            connectionPrevious1 = null;

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

                        if (Objects.nonNull(connectionPrevious1)) {

                            String createTableSetInfo = "CREATE TABLE IF NOT EXISTS SETINFO (ID INTEGER PRIMARY KEY AUTOINCREMENT, ESTIMATEPATH TEXT(1000), TRAVELERPATH TEXT(1000), PACKINGLISTPATH TEXT (1000), STATE TEXT(7), FILL TEXT(7), OPEN TEXT(7))";
                            String createTableAuto = "CREATE TABLE IF NOT EXISTS AUTO (ID INTEGER PRIMARY KEY AUTOINCREMENT, WORD TEXT(1000), ADDRESS TEXT(1000))";

                            Statement statementSetInfo = connectionMain.createStatement();
                            statementSetInfo.setQueryTimeout(10);
                            statementSetInfo.execute(createTableSetInfo);
                            statementSetInfo.close();

                            Statement statementAuto = connectionMain.createStatement();
                            statementAuto.setQueryTimeout(10);
                            statementAuto.execute(createTableAuto);
                            statementAuto.close();

                            String selectTableSetInfo = "SELECT * FROM SETINFO";

                            Statement querySetInfo = connectionPrevious1.createStatement();
                            ResultSet rsSetInfo = querySetInfo.executeQuery(selectTableSetInfo);

                            String delete = "DELETE FROM SETINFO";
                            String filePath = String.format("INSERT INTO SETINFO VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", rsSetInfo.getString(1), rsSetInfo.getString(2), rsSetInfo.getString(3), rsSetInfo.getString(4), "false", "false");

                            querySetInfo.close();

                            Statement statement = connectionMain.createStatement();
                            statement.setQueryTimeout(10);
                            statement.executeUpdate(delete);
                            statement.executeUpdate(filePath);
                            statement.close();

                            String selectTableAuto = "SELECT * FROM AUTO";

                            Statement queryAuto = connectionPrevious1.createStatement();
                            ResultSet rsAuto = queryAuto.executeQuery(selectTableAuto);

                            Statement statementInsertAuto = connectionMain.createStatement();

                            while (rsAuto.next()) {

                                String insertAuto = String.format("INSERT INTO AUTO (WORD) VALUES ('%s')", rsAuto.getString(1));
                                statementInsertAuto.execute(insertAuto);
                            }

                            queryAuto.close();
                            statementInsertAuto.close();

                            connectionPrevious1.close();

                            Files.deleteIfExists(Paths.get(System.getProperty("user.home") + "/DesignQuest/DesignQuest2.sqlite"));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                String createTableSetInfo = "CREATE TABLE IF NOT EXISTS SETINFO (ID INTEGER PRIMARY KEY AUTOINCREMENT, ESTIMATEPATH TEXT(1000), TRAVELERPATH TEXT(1000), PACKINGLISTPATH TEXT (1000), STATE TEXT(7), FILL TEXT(7), OPEN TEXT(7))";
                String createTableAuto = "CREATE TABLE IF NOT EXISTS AUTO (ID INTEGER PRIMARY KEY AUTOINCREMENT, WORD TEXT(1000), ADDRESS TEXT(1000))";
                String selectTableSetInfo = "SELECT * FROM SETINFO";

                Statement statementSetInfo = connectionMain.createStatement();
                statementSetInfo.setQueryTimeout(10);
                statementSetInfo.execute(createTableSetInfo);
                statementSetInfo.close();

                Statement statementAuto = connectionMain.createStatement();
                statementAuto.setQueryTimeout(10);
                statementAuto.execute(createTableAuto);
                statementAuto.close();

                Statement querySetInfo = connectionMain.createStatement();
                ResultSet rsSetInfo = querySetInfo.executeQuery(selectTableSetInfo);

                defaultDirectories[0] = rsSetInfo.getString(1);
                defaultDirectories[1] = rsSetInfo.getString(2);
                defaultDirectories[2] = rsSetInfo.getString(3);

                if (Objects.equals(defaultDirectories[0], "")) {
                    defaultDirectories[0] = System.getProperty("user.home") + "/Desktop/";
                }

                if (Objects.equals(defaultDirectories[1], "")) {
                    defaultDirectories[1] = System.getProperty("user.home") + "/Desktop/";
                }

                if (Objects.equals(defaultDirectories[2], "")) {
                    defaultDirectories[2] = System.getProperty("user.home") + "/Desktop/";
                }

                autoCompletionState = Objects.equals(rsSetInfo.getString(4), "true");
                textFillState = Objects.equals(rsSetInfo.getString(5), "true");
                formOpenState = Objects.equals(rsSetInfo.getString(6), "true");

                querySetInfo.close();

                String selectTableAuto = "SELECT * FROM AUTO";

                Statement queryAuto = connectionMain.createStatement();
                ResultSet rsAuto = queryAuto.executeQuery(selectTableAuto);

                while (rsAuto.next()) {

                    if (Objects.nonNull(rsAuto.getString(1))) {
                        autoCompleteList.add(rsAuto.getString(1));
                    }

                    if (Objects.nonNull(rsAuto.getString(2))) {
                        addressList.add(rsAuto.getString(2));
                    }
                }

                queryAuto.close();
            }

        } catch (Exception e) {

            e.printStackTrace();

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


} /* Closing bracket for class Main! */

