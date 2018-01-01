package sample;

/*
 * Created by Matthew Ashley on 1/10/17.
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;

public class ConfirmMessages {

    private boolean answer; /* Variable for confirming if the program has been closed, used to store true if the program will be closed and false if the program won't be closed! */

    /* Method to display when a error has occurred, is the default popup window for every error! */
    public void infoEnteredError(String message, String example) {

        URL warningImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/Warning.png");

        Stage errorWindow = new Stage();

        errorWindow.initModality(Modality.APPLICATION_MODAL);
        errorWindow.setTitle("File Creation Error");
        errorWindow.setMinWidth(250);
        errorWindow.setMinHeight(150);

        /* Setup for items error icon! */
        ImageView warning = new ImageView(new Image(String.valueOf(warningImagePath)));
        warning.setFitHeight(100);
        warning.setFitWidth(100);

        /* Label for Company information! */
        Label errorMessage = new Label(message);
        errorMessage.setStyle("-fx-font: 18 arial; -fx-base: #3178ea;");
        errorMessage.setPadding(new Insets(10));

        /* Setup for example help message! */
        Label exampleHelp = new Label(example);
        exampleHelp.setStyle("-fx-font: 14 arial; -fx-base: #3178ea;");

        /* Conditions for the Close button to close the Developer Information window! */
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> errorWindow.close());
        closeButton.setStyle("-fx-font: 13 arial; -fx-base: #C0C0C0;");

        /* Setup for the Bottom of the BorderPane! */
        HBox bottom = new HBox(8);
        bottom.setPadding(new Insets(10));
        bottom.getChildren().addAll(closeButton);
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.setMinHeight(65);

        /* Setup for the Top of the BorderPane! */
        HBox top = new HBox(8);
        top.setPadding(new Insets(10));
        top.getChildren().addAll(warning, errorMessage);

        /* Setup for the Center of the BorderPane! */
        HBox center = new HBox(8);
        center.setPadding(new Insets(10));
        center.getChildren().addAll(exampleHelp);
        center.setAlignment(Pos.CENTER_RIGHT);

        /* Setup for the devLayout BorderPane and the locations of the different elements within the BorderPane! */
        BorderPane itemsErrorLayout = new BorderPane();

        itemsErrorLayout.setTop(top);
        itemsErrorLayout.setCenter(center);
        itemsErrorLayout.setBottom(bottom);

        Scene devScene = new Scene(itemsErrorLayout);
        errorWindow.setScene(devScene);
        errorWindow.setResizable(false);
        errorWindow.showAndWait();

    }

    /* Method for close program acknowledge popup window! */
    public boolean display(double primaryStageXPosition, double primaryStageYPosition, double primaryStageWidth, double primaryStageHeight) {

        Stage messageWindow = new Stage();

        messageWindow.initModality(Modality.APPLICATION_MODAL);
        messageWindow.setTitle("Quest Input");
        messageWindow.setMinWidth(250);
        messageWindow.setMinHeight(100);

        /* Label is the past in parameter message from the closeProgram method in the Main class! */
        Label label = new Label();
        label.setText("    Are you sure you want to close the program?    ");
        label.setAlignment(Pos.BOTTOM_CENTER);
        label.setStyle("-fx-font: 14 arial; -fx-base: #FFFFFF;");
        label.setTextFill(Color.LIGHTGRAY);

        /* label1 is the title for the messageWindow. Asking to Confirm Exit! */
        Label label1 = new Label("Confirm Exit?");
        label1.setStyle("-fx-font: 16 arial; -fx-base: #FFFFFF;");
        label1.setTextFill(Color.LIGHTGRAY);

        /* Conditions for the Exit button! */
        Button yesButton = new Button("Exit");
        /* yesButton.setEffect(shadow);   possible shadow effect */
        yesButton.setOnAction(e -> {
            answer = true;
            messageWindow.close();
        });
        yesButton.setStyle("-fx-font: 14 arial; -fx-base: #b4ffa3;");

        /* Conditions for the Cancel button! */
        Button noButton = new Button("Cancel");
        /* noButton.setEffect(shadow);    possible shadow effect */
        noButton.setOnAction(e -> {
            answer = false;
            messageWindow.close();
        });
        noButton.setStyle("-fx-font: 13 arial; -fx-base: #C0C0C0;");

        /* Setup for the Bottom of the BorderPane! */
        HBox bottom = new HBox(8);
        bottom.setPadding(new Insets(10));
        bottom.getChildren().addAll(noButton, yesButton);
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.setMinHeight(65);

        /* Setup for the Center of the BorderPane! */
        StackPane center = new StackPane();
        center.getChildren().add(label);
        center.setMinHeight(35);

        /* Setup for the Top of the BorderPane! */
        StackPane top = new StackPane();
        top.getChildren().add(label1);
        top.setMinHeight(25);

        /* Setup for the Left of the BorderPane! */
        URL displayWarningImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/Warning.png");

        ImageView warning = new ImageView(new Image(String.valueOf(displayWarningImagePath)));
        warning.setFitHeight(100);
        warning.setFitWidth(100);

        VBox left = new VBox();
        left.getChildren().add(warning);
        left.setTranslateX(7);

        /* Setup for the BorderPane and the locations of the different layouts used in the BorderPane! */
        BorderPane layout = new BorderPane();
        layout.setBottom(bottom);
        layout.setCenter(center);
        layout.setTop(top);
        layout.setLeft(left);
        layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #2a2d33, #2a2d33)");

        Scene scene = new Scene(layout);
        messageWindow.setScene(scene);
        messageWindow.centerOnScreen();
        messageWindow.setResizable(false);

        messageWindow.heightProperty().addListener(observable -> messageWindow.setY(((primaryStageHeight - messageWindow.getHeight()) / 2d) + primaryStageYPosition));
        messageWindow.widthProperty().addListener(observable -> messageWindow.setX(((primaryStageWidth - messageWindow.getWidth()) / 2d) + primaryStageXPosition));
        messageWindow.showAndWait();

        return answer;
    }

    /* Method for closing estimate form acknowledge popup window! */
    public boolean cancelAcknowledged(String formFilling, double primaryStageXPosition, double primaryStageYPosition, double primaryStageWidth, double primaryStageHeight) {

        Stage messageWindow = new Stage();
        
        Label question, label;

        messageWindow.initModality(Modality.APPLICATION_MODAL);
        messageWindow.setTitle(formFilling);
            
        /* Label for consequences to user! */
        label = new Label();
        label.setText("    All progress will be lost!    ");
        label.setAlignment(Pos.BOTTOM_CENTER);
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        /* Label for question to user! */
        question = new Label("Are you sure you want to cancel this form?");
        question.setPadding(new Insets(10));
        question.setTextFill(Color.BLACK);
        question.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        /* Conditions for the Yes button! */
        Button yesButton = new Button("Yes");
        /* yesButton.setEffect(shadow);   possible shadow effect */
        yesButton.setOnAction(e -> {
            answer = true;
            messageWindow.close();
        });
        yesButton.setStyle("-fx-font: 14 arial; -fx-base: #b4ffa3;");

        /* Conditions for the Cancel button! */
        Button noButton = new Button("Cancel");
        /* noButton.setEffect(shadow);    possible shadow effect */
        noButton.setOnAction(e -> {
            answer = false;
            messageWindow.close();
        });
        noButton.setStyle("-fx-font: 13 arial; -fx-base: #bfd2f2;");

        /* Setup for the top of the VBox! */
        StackPane top = new StackPane();
        top.getChildren().add(question);
        top.setMinHeight(35);

        URL displayWarningImagePath = this.getClass().getResource("/sample/CustomerFiles/Images/Warning.png");

        ImageView warning = new ImageView(new Image(String.valueOf(displayWarningImagePath)));
        warning.setFitHeight(100);
        warning.setFitWidth(100);

        HBox center = new HBox(4);
        center.getChildren().addAll(warning, label);
        center.setMinHeight(25);
        center.setPadding(new Insets(10));

        /* Setup for the center of the VBox! */
        HBox bottom = new HBox(8);
        bottom.setPadding(new Insets(5));
        bottom.getChildren().addAll(noButton, yesButton);
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.setMinHeight(65);

        /* Setup for the BorderPane and the locations of the different layouts used in the BorderPane! */
        BorderPane layout = new BorderPane();
        layout.setTop(top);
        layout.setLeft(center);
        layout.setBottom(bottom);
        layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #9b9ea3, #ffffff)");

        Scene scene = new Scene(layout);
        messageWindow.setScene(scene);
        messageWindow.setResizable(false);

        messageWindow.heightProperty().addListener(observable -> messageWindow.setY(((primaryStageHeight - messageWindow.getHeight()) / 2d) + primaryStageYPosition));
        messageWindow.widthProperty().addListener(observable -> messageWindow.setX(((primaryStageWidth - messageWindow.getWidth()) / 2d) + primaryStageXPosition));
        messageWindow.showAndWait();

        return answer;
    }



} /* Closing bracket for ConfirmMessages class! */
