package assignment5_000813686;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.swing.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;


/**
 * This class represents the view of the game of Spin the Wheel. It uses the classes Wheel, and
 * ChangingColors, as well as many GUI components.
 *
 * @author Silvia Mariana Reyesvera Quijano
 */
public class SpinTheWheel extends Application {
    /**
     * Graphics context in which components will be drawn.
     */
    GraphicsContext gc;

    /**
     * The wheel of fortune of the game.
     */
    Wheel wheelOfFortune;

    /**
     * Label array containing the letters of the title.
     */
    Label [] spinTitle;

    /**
     * Label for the accumulated amount won.
     */
    Label amountWon;

    /**
     * Label for the instructions to change the colors of the slices.
     */
    Label changeColorLabel;

    /**
     * Button that makes the wheel start spinning.
     */
    Button startSpin;

    /**
     * Button that stops the wheel after it's already spinning.
     */
    Button stopSpin;

    /**
     * Button that resets the amount won to 0.
     */
    Button resetAmount;

    /**
     * Button that turns on/off the animation of the title.
     */
    Button title;

    /**
     * Array with buttons that allow the appearance of the window that allows changing the colors
     * of the slices (one button per slice).
     */
    Button [] legendButtons;

    /**
     * Button that resets the wheel's slices to its default values. In this case it only resets
     * the color to default (since I didn't add the possibility to make changes of other values)
     */
    Button defaultValuesLegend;

    /**
     * Timer that takes care of the changing of colors in the title animation.
     */
    javax.swing.Timer lightingTitle;


    /**
     * Resets the wheel, legend and title to the default colors of the slices.
     * Called when the button defaultValuesLegend is pressed.
     * @param e Not used
     */
    private void resetDefaultLegend(ActionEvent e){
        wheelOfFortune.resetDefault(); // resets to default values
        wheelOfFortune.drawWheel(gc); // draws again the wheel to see the changes applied
        wheelOfFortune.drawLegend(gc, 100, 300); // draws again the legend to see the
                                                                // changes applied
        updateTitleColors(); // update title colors to see changes applied
    }

    /**
     * Sets the wheel spinning.
     * Called when the button startSpin is pressed.
     * @param e Not used
     */
    private void spinTheWheel(ActionEvent e){
        lightingTitle.stop(); // stops the title animation, because it caused the wheel to lag
        stopSpin.setDisable(false); // enable the stop Spin button
        title.setDisable(true); // disable to button tu turn the title animation on/off
        wheelOfFortune.spinTheWheel(gc); // starts the wheel spinning
    }

    /**
     * Stops the wheel after it had started spinning.
     * Called when the button stopSpin is pressed.
     * @param e Not used
     */
    private void stopSpinning(ActionEvent e) {
        title.setDisable(true); // makes sure the title is disabled when the wheel is stopping
        // sets the procedure to stop the wheel
        wheelOfFortune.stopSpinning(amountWon, stopSpin, lightingTitle, title);

    }

    /**
     * Resets the accumulated amount won to 0.
     * Called when the button resetAmount is pressed.
     * @param e Not used
     */
    private void resetAmount(ActionEvent e){
        wheelOfFortune.resetAmountWon(); // reset amount won to 0
        amountWon.setText("$" + wheelOfFortune.getAmountWon()); // update the amountWon label
    }

    /**
     * Checks if the title animation is on, and if so it turns it off. Otherwise it turns it on.
     * Called when the button title is pressed.
     * @param e Not used
     */
    private void controlTitle(ActionEvent e){
        if(lightingTitle.isRunning()) // if the timer is running
            lightingTitle.stop(); // stop it
        else
            lightingTitle.start(); // else start it
    }

    /**
     * Initializes the labels of the spinTitle array with their corresponding letter as their text.
     * It also styles and locates each label appropriately.
     */
    private void settingTitle(){

        // Initialize the labels in spinTitle
        spinTitle[0] = new Label("S");
        spinTitle[1] = new Label("P");
        spinTitle[2] = new Label("I");
        spinTitle[3] = new Label("N");
        spinTitle[4] = new Label("T");
        spinTitle[5] = new Label("H");
        spinTitle[6] = new Label("E");
        spinTitle[7] = new Label("W");
        spinTitle[8] = new Label("H");
        spinTitle[9] = new Label("E");
        spinTitle[10] = new Label("E");
        spinTitle[11] = new Label("L");
        spinTitle[12] = new Label("!");

        int regularCount = 0; // keeps count of the loops
        int colorCount = 0; // keeps count of the loops within a range of [0, slices.length). If
                            // it reaches the maximum, it starts again
        double xValue = 70; // set the x-coordinate of the start of the title
        for(Label letter: spinTitle){
            // set the color of the letter to the color of the slice in the corresponding
            // position of the array slices
            letter.setTextFill(wheelOfFortune.getSliceColor(colorCount));

            // style font
            letter.setFont(Font.font("Verdana", FontWeight.BOLD, 90));

            // position
            letter.setLayoutX(xValue);
            letter.setLayoutY(25);

            // add to the x-coordinate depending on the letter
            switch (regularCount) {
                case (0): case(1):
                    xValue += 70;
                    break;
                case(2): case(12):
                    xValue += 50;
                    break;
                case(3): case(6):
                    xValue += 150;
                    break;
                case(5): case(8):
                    xValue += 80;
                    break;
                case(7):
                    xValue += 110;
                    break;
                case(9): case(10): case(11): case(4):
                    xValue += 65;
                    break;
            }

            colorCount++; // increase colorCount by 1
            // keep colorCount in range
            colorCount = colorCount % wheelOfFortune.getSlicesLength();

            regularCount++; // increase regularCount by 1
        }
    }

    /**
     * Sets the color of each of the letter labels in spinTitle to each of the slice's colors.
     * Each letter's color is set based on the index order of the wheel's slices array.
     */
    public void updateTitleColors(){
        int colorCount = 0; // keeps count of the loops within a range of [0, slices.length). If
                            // it reaches the maximum, it starts again
        for(Label letter: spinTitle){
            // set the color of the letter to the color of the slice in the corresponding
            // position of the array slices
            letter.setTextFill(wheelOfFortune.getSliceColor(colorCount));
            colorCount++; // increase colorCount by 1
            // keep colorCount in range
            colorCount = colorCount % wheelOfFortune.getSlicesLength();
        }
    }

    /**
     * Sets the animation of the title's colors.
     */
    private void titleAnimation(){
        ChangingColors titleAnimation = new ChangingColors(spinTitle);
        lightingTitle = new Timer(20, titleAnimation);
        lightingTitle.start();
    }

    /**
     * Sets the buttons in the positions wheel's slices' legends that allow the appearance of
     * the window that allows changing the colors of the slices (one button per slice).
     * @param xValue Initial x-coordinate of the start location of the buttons, top-left corner
     * @param yValue Initial y-coordinate of the start location of the buttons, top-left corner
     */
    private void setLegendButtons( int xValue, int yValue){
        xValue +=10; // adds left padding to the legend start
        for(int i=0; i<legendButtons.length; i++){
            // Initializes and styles each of the buttons in legendButtons
            legendButtons[i] = new Button("");
            legendButtons[i].setStyle("-fx-background-color: transparent;"); // sets them to
                                // transparent, so they aren't visible and the drawn legend is seen.
            legendButtons[i].setLayoutX(xValue);
            legendButtons[i].setLayoutY(yValue);
            legendButtons[i].setPrefSize(10,10);
            yValue += 20; // add to the y-coordinate, so the next legend is drawn below this one
        }
    }

    /**
     * Sets up the stage and starts the main thread. Your drawing code should
     * NOT go here.
     *
     * @param stage The first stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Spin the Wheel!"); // window title here
        Canvas canvas = new Canvas(1200, 700); // canvas size here
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
        gc = canvas.getGraphicsContext2D();

        scene.setFill(Color.BLACK); // black background

        // Create the wheel
        wheelOfFortune = new Wheel(350, 200, 200);

        wheelOfFortune.drawDefault(gc); // set wheel's slices to default values
        wheelOfFortune.drawLegend(gc, 100, 300); // draw legend

        // Create the legend buttons array
        legendButtons = new Button[10];
        // Initializes and sets the buttons in the provided coordinates
        setLegendButtons(100, 300);

        // Create the title labels' array
        spinTitle = new Label[13];
        settingTitle(); // Setting the title
        titleAnimation(); // Starting the title's animation

        // Initializing, setting and styling the title on/off button
        title = new Button("title on/off");
        title.setPrefWidth(150);
        title.setPrefHeight(20);
        title.setTextFill(Color.WHITE);
        title.setFont(new Font("Verdana", 14));
        title.setStyle("-fx-background-color: #3283a8; -fx-background-radius:  10px;");

        // Initializing, setting and styling the SPIN! button
        startSpin = new Button("SPIN!");
        startSpin.setPrefWidth(80);
        startSpin.setPrefHeight(80);
        startSpin.setTextFill(Color.WHITE);
        startSpin.setFont(new Font("Verdana", 18));
        startSpin.setStyle("-fx-background-color: #4cb04f; -fx-background-radius:  40px;");

        // Initializing, setting and styling the STOP! button
        stopSpin = new Button("STOP!");
        stopSpin.setPrefWidth(80);
        stopSpin.setPrefHeight(80);
        stopSpin.setTextFill(Color.WHITE);
        stopSpin.setFont(new Font("Verdana", 18));
        stopSpin.setStyle("-fx-background-color: #cc0624; -fx-background-radius:  40px;");

        // Initializing, setting and styling the RESET button
        resetAmount = new Button("RESET");
        resetAmount.setPrefWidth(150);
        resetAmount.setPrefHeight(50);
        resetAmount.setTextFill(Color.WHITE);
        resetAmount.setFont(new Font("Verdana", 18));
        resetAmount.setStyle("-fx-background-color: #3283a8; -fx-background-radius:  10px;");

        // Initializing, setting and styling the "reset to default colors" button
        defaultValuesLegend = new Button("reset to default colors");
        defaultValuesLegend.setPrefWidth(200);
        defaultValuesLegend.setPrefHeight(30);
        defaultValuesLegend.setTextFill(Color.WHITE);
        defaultValuesLegend.setFont(new Font("Verdana", 14));
        defaultValuesLegend.setStyle("-fx-background-color: #3283a8; -fx-background-radius:  10px;");

        // Initializing, setting and styling the amount won label
        amountWon = new Label("$" + wheelOfFortune.getAmountWon());
        amountWon.setPrefSize(300, 50);
        amountWon.setStyle("-fx-background-color: #f0f56e; -fx-background-radius:  20px;");
        amountWon.setFont(new Font("Verdana", 20));
        amountWon.setAlignment(Pos.CENTER);

        // Initializing, setting and styling the instructions label to change the color of the
        // wheel's slices
        changeColorLabel = new Label("To change the color of each part of the\nwheel, click on " +
                "its color box below.");
        changeColorLabel.setTextFill(Color.WHITE);
        changeColorLabel.setPrefSize(300, 100);
        changeColorLabel.setFont(new Font("Verdana", 12));
        changeColorLabel.setAlignment(Pos.CENTER);

        // Positioning the components

        title.setLayoutX(1000);
        title.setLayoutY(600);

        startSpin.setLayoutX(850);
        startSpin.setLayoutY(400);

        stopSpin.setLayoutX(970);
        stopSpin.setLayoutY(400);

        resetAmount.setLayoutX(875);
        resetAmount.setLayoutY(300);

        defaultValuesLegend.setLayoutX(50);
        defaultValuesLegend.setLayoutY(600);

        amountWon.setLayoutX(800);
        amountWon.setLayoutY(200);

        changeColorLabel.setLayoutX(50);
        changeColorLabel.setLayoutY(200);

        // Adding the components to the root
        root.getChildren().addAll(startSpin, stopSpin, amountWon, resetAmount, title,
                defaultValuesLegend, changeColorLabel);
        for(Label letter: spinTitle){
            root.getChildren().add(letter);
        }

        for(Button button: legendButtons){
            root.getChildren().add(button);
        }

        // Setting the setOnAction method each of the buttons to its corresponding method
        startSpin.setOnAction(this::spinTheWheel);
        stopSpin.setOnAction(this::stopSpinning);
        resetAmount.setOnAction(this::resetAmount);
        defaultValuesLegend.setOnAction(this::resetDefaultLegend);
        title.setOnAction(this::controlTitle);

        // Creating a new stage which will appear when the user wishes to change the color of a
        // slice.
        Stage editWindow = new Stage();

        // Sets the setOnAction method of each of the buttons in the legendButtons array
        for(int i=0; i<legendButtons.length; i++){
            int index = i;
            // The event that is presented is in lambda form. IntelliJ suggested to
            // present it that way. I don't know enough about lambda expressions, but followed
            // the suggestion.
            legendButtons[i].setOnAction(event -> {
                lightingTitle.stop(); // stop the title animation
                updateTitleColors(); // update the title colors (also resets to original order)

                // setting all new elements for the window/stage
                editWindow.setTitle("Editing");
                Group secondRoot = new Group();
                Scene secondScene = new Scene(secondRoot, 300, 120);
                editWindow.setScene(secondScene);

                // positioning the window/stage
                editWindow.setX(stage.getX() + 50);
                editWindow.setY(stage.getY() + 550);

                // Setting the background of the scene to the color of the slice legend that was
                // selected.
                secondScene.setFill(wheelOfFortune.getSliceColor(index));

                // Initialize, set and style the "Choose the Color" label
                Label chooseColor = new Label("Choose the Color");
                chooseColor.setPrefWidth(300);
                chooseColor.setAlignment(Pos.CENTER);
                chooseColor.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                chooseColor.setTextFill(Color.BLACK);
                chooseColor.setLayoutY(20);

                // Initializing, setting and positioning the colorPicker, which will be used to
                // allow the user to pick a color.
                final ColorPicker colorPicker = new ColorPicker();
                colorPicker.setValue(wheelOfFortune.getSliceColor(index));
                colorPicker.setLayoutX(80);
                colorPicker.setLayoutY(60);

                // Setting the setOnAction method for the colorPicker.
                // The event that is presented is in lambda form. IntelliJ suggested to
                // present it that way. I don't know enough about lambda expressions, but followed
                // the suggestion. I don't understand the warning, will look into it later on.
                colorPicker.setOnAction((EventHandler) e -> {
                    // When a color is chosen

                    // Setting the wheel's slice's color to the chosen color
                    wheelOfFortune.setSliceColor(index, colorPicker.getValue());
                    // redraw wheel and legend to see changes applied
                    wheelOfFortune.drawWheel(gc);
                    wheelOfFortune.drawLegend(gc, 100, 300);
                    // set the scene's background to the chosen color
                    secondScene.setFill(wheelOfFortune.getSliceColor(index));
                    updateTitleColors(); // Update title colors to see changes applied
                });

                // Adding the label chooseColor and the colorPicker tot he root
                secondRoot.getChildren().addAll(chooseColor, colorPicker);

                editWindow.show(); // show the second stage (editWindow)
            });

        }

        // Disable the stopSpin button, in order to avoid the user clicking the button when the
        // user shouldn't
        stopSpin.setDisable(true);

        stage.show(); // show the stage

    }

    /**
     * Exits the app completely when the window is closed. This is necessary to
     * kill the animation thread.
     */
    @Override
    public void stop() {
        System.exit(0);
    }

    /**
     * Launches the app
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
