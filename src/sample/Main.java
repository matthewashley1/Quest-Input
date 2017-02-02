package sample;

/*
 * Created by Matthew Ashley on 12/15/16.
 */

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private Scene scene1;
    private Stage window;

    /* Initializer for DisplayWindows class! */
    private final DisplayWindows display = new DisplayWindows();

    private final DoubleProperty fontSize = new SimpleDoubleProperty(10);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        window = primaryStage;

        /* Setup for Design Quest Image on Home Screen! */
        ImageView logo = new ImageView(new Image("file:///Users/matthewashley/Documents/IdeaProjects/Quest Input/Customer Files/image001.png"));
        logo.fitWidthProperty().bind(window.widthProperty());
        logo.setFitHeight(300);

        /* Conditions for the Quote button and it's content! */
        ImageView estimateButtonImage = new ImageView(new Image("file:///Users/matthewashley/Documents/IdeaProjects/Quest Input/Customer Files/Quote.png"));
        estimateButtonImage.setFitHeight(70);
        estimateButtonImage.setFitWidth(70);

        Button estimate = new Button("Estimate");
        estimate.setOnAction(e -> display.inputEstimate());

        estimate.setStyle("-fx-font: 18 arial; -fx-base: #3178ea;");
        estimate.setGraphic(estimateButtonImage);
        estimate.setContentDisplay(ContentDisplay.RIGHT);
        estimate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        estimate.setMinHeight(100);
        estimate.setPrefWidth(300);

        /* Conditions for the Invoice button and it's content! */
        ImageView invoiceButton = new ImageView(new Image("file:///Users/matthewashley/Documents/IdeaProjects/Quest Input/Customer Files/Invoice.png"));
        invoiceButton.setFitHeight(70);
        invoiceButton.setFitWidth(70);

        Button invoice = new Button("Invoice");
        invoice.setOnAction(e -> display.inputInvoice());

        invoice.setStyle("-fx-font: 18 arial; -fx-base: #03c417;");
        invoice.setGraphic(invoiceButton);
        invoice.setContentDisplay(ContentDisplay.RIGHT);
        invoice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        invoice.setMinHeight(100);
        invoice.setPrefWidth(300);

        /* Conditions for MenuBar and it's content! */
        MenuBar menuBar = new MenuBar();
        menuBar.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        Menu menuInfo = new Menu("Info");
        MenuItem devInfo = new MenuItem("Dev Info");
        MenuItem proTip = new MenuItem("Pro Tip");

        devInfo.setOnAction(e -> display.displayDevInfo());

        proTip.setOnAction(e -> display.displayProTip());

        menuInfo.getItems().addAll(devInfo, proTip);
        menuBar.getMenus().addAll(menuInfo);

        /* Conditions for Main Scene and content locations! */
        BorderPane mainScreen = new BorderPane();

        VBox information = new VBox();
        information.getChildren().addAll(menuBar);

        VBox mainVBox = new VBox();
        mainVBox.getChildren().add(logo);

        HBox input = new HBox();
        input.getChildren().addAll(estimate, invoice);
        input.setTranslateY(-360);

        mainScreen.setTop(information);
        mainScreen.setLeft(mainVBox);
        mainScreen.setBottom(input);
        //mainScreen.setBottom(company);
        //mainScreen.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));  Sets the background color of the primary Scene!

        scene1 = new Scene(mainScreen, 900, 900);

        /* Starting parameters for primaryStage! */
        window.setScene(scene1);
        window.setTitle("Quest Input");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        window.show();

        /* Lambda expression for a ChangeListener of the width of the primaryStage.
           This expression auto resizes the width of the buttons within the primaryStage. */
        window.widthProperty().addListener((observable, oldStageWidth, newStageWidth) -> {

            HBox.setHgrow(estimate, Priority.ALWAYS);
            HBox.setHgrow(invoice, Priority.ALWAYS);

            Double newWidth = (newStageWidth.doubleValue() / 3.0);

            estimate.setMaxWidth(newWidth);
            invoice.setMaxWidth(newWidth);

            fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(100));

            estimate.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";" ,"-fx-base: #3178ea;"));
            invoice.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";" , "-fx-base: #03c417;"));

        });

    }

    private void closeProgram() {
        Boolean answer = ConfirmMessages.display();
        if(answer) {
            window.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}

