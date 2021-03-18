package assignment5_000813686;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javax.swing.*;

/**
 * This class represents a wheel with different subdivisions of type WheelSlice, it is meant to
 * be used as a wheel of fortune. This class also uses the classes WheelSpinning and
 * WheelStopping to allow the well functioning of some of its behaviours. Finally, this class is
 * meant to be used by the class SpinTheWheel.
 *
 * @author Silvia Mariana Reyesvera Quijano
 */
public class Wheel {
    /**
     * Array of type WheelSlice that contains all of the subdivisions of the wheel.
     * The array has a length of 10, but the sum of all of it's percentages only sums up to 50,
     * since each subdivision appears twice in the wheel.
     *
     * NOTE: I realized now that "slices" isn't the best name for the subdivisions of the wheel,
     * I apologize for this terminology error.
     */
    private WheelSlice[] slices = new WheelSlice[10];

    /**
     * The x-coordinate of the location of the wheel in the Graphics Context, from the top-left
     * corner.
     */
    private double xValue;

    /**
     * The y-coordinate of the location of the wheel in the Graphics Context, from the top-left
     * corner.
     */
    private double yValue;

    /**
     * Wheel's radius.
     */
    private double radius;

    /**
     * Keeps track of the lastAngle used to draw the wheel.
     * It is initialized at 90, because the starting point is meant to be the top, and following
     * the polar coordinate system, this position is at 90º.
     */
    private double lastAngle = 90;

    /**
     * Keeps track of how much the user has won from the diverse wheel spins.
     */
    private double amountWon;

    /**
     * Timer that allows for the animation of the wheel spinning.
     */
    private javax.swing.Timer spinTimer;

    /**
     * Timer that allows for the animation of the wheel stopping.
     */
    private java.util.Timer stopTimer;

    /**
     * Delay to which spinTimer will be set to (amount of time in milliseconds between each
     * redrawing of the wheel).
     */
    private final int INITIAL_DELAY = 10;

    /**
     * First slice's default amount.
     */
    private final double DEFAULT_AMOUNT_ONE = 1000000;
    /**
     * First slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_ONE = 1;
    /**
     * First slice's default color.
     */
    private final Color DEFAULT_COLOR_ONE = Color.RED;

    /**
     * Second slice's default amount.
     */
    private final double DEFAULT_AMOUNT_TWO = 100000;
    /**
     * Second slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_TWO = 2;
    /**
     * Second slice's default color.
     */
    private final Color DEFAULT_COLOR_TWO = Color.ORANGE;

    /**
     * Third slice's default amount.
     */
    private final double DEFAULT_AMOUNT_THREE = 10000;
    /**
     * Third slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_THREE = 3;
    /**
     * Third slice's default color.
     */
    private final Color DEFAULT_COLOR_THREE = Color.YELLOW;

    /**
     * Fourth slice's default amount.
     */
    private final double DEFAULT_AMOUNT_FOUR = 1000;
    /**
     * Fourth slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_FOUR = 4;
    /**
     * Fourth slice's default color.
     */
    private final Color DEFAULT_COLOR_FOUR = Color.LIGHTGREEN;

    /**
     * Fifth slice's default amount.
     */
    private final double DEFAULT_AMOUNT_FIVE = 500;
    /**
     * Fifth slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_FIVE = 5;
    /**
     * Fifth slice's default color.
     */
    private final Color DEFAULT_COLOR_FIVE = Color.GREEN;

    /**
     * Sixth slice's default amount.
     */
    private final double DEFAULT_AMOUNT_SIX = 300;
    /**
     * Sixth slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_SIX = 5;
    /**
     * Sixth slice's default color.
     */
    private final Color DEFAULT_COLOR_SIX = Color.DARKCYAN;

    /**
     * Seventh slice's default amount.
     */
    private final double DEFAULT_AMOUNT_SEVEN = 200;
    /**
     * Seventh slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_SEVEN = 6;
    /**
     * Seventh slice's default color.
     */
    private final Color DEFAULT_COLOR_SEVEN = Color.CYAN;

    /**
     * Eight slice's default amount.
     */
    private final double DEFAULT_AMOUNT_EIGHT = 100;
    /**
     * Eight slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_EIGHT = 6;
    /**
     * Eight slice's default color.
     */
    private final Color DEFAULT_COLOR_EIGHT = Color.LAVENDER;

    /**
     * Ninth slice's default amount.
     */
    private final double DEFAULT_AMOUNT_NINE = 50;
    /**
     * Ninth slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_NINE = 9;
    /**
     * Ninth slice's default color.
     */
    private final Color DEFAULT_COLOR_NINE = Color.PURPLE;

    /**
     * Tenth slice's default amount.
     */
    private final double DEFAULT_AMOUNT_TEN = 20;
    /**
     * Tenth slice's default percentage.
     */
    private final double DEFAULT_PERCENTAGE_TEN = 9;
    /**
     * Tenth slice's default color.
     */
    private final Color DEFAULT_COLOR_TEN = Color.LIGHTPINK;

    /**
     * Constructor.
     *
     * @param xValue Value that this.xValue will take
     * @param yValue Value that this.yValue will take
     * @param radius Value that this.radius will take
     */
    public Wheel(double xValue, double yValue, double radius){
        this.xValue = xValue;
        this.yValue = yValue;
        setRadius(radius); //calls setRadius method to validate radius
        setDefaultSlices(); //initializes slices with default values
        amountWon = 0;
    }

    /**
     * Initializing the slices with the default values.
     */
    private void setDefaultSlices(){
        slices[0] = new WheelSlice(DEFAULT_AMOUNT_ONE, DEFAULT_PERCENTAGE_ONE, DEFAULT_COLOR_ONE);
        slices[1] = new WheelSlice(DEFAULT_AMOUNT_TWO, DEFAULT_PERCENTAGE_TWO, DEFAULT_COLOR_TWO);
        slices[2] = new WheelSlice(DEFAULT_AMOUNT_THREE, DEFAULT_PERCENTAGE_THREE,
                DEFAULT_COLOR_THREE);
        slices[3] = new WheelSlice(DEFAULT_AMOUNT_FOUR, DEFAULT_PERCENTAGE_FOUR,
                DEFAULT_COLOR_FOUR);
        slices[4] = new WheelSlice(DEFAULT_AMOUNT_FIVE, DEFAULT_PERCENTAGE_FIVE,
                DEFAULT_COLOR_FIVE);
        slices[5] = new WheelSlice(DEFAULT_AMOUNT_SIX, DEFAULT_PERCENTAGE_SIX,
                DEFAULT_COLOR_SIX);
        slices[6] = new WheelSlice(DEFAULT_AMOUNT_SEVEN, DEFAULT_PERCENTAGE_SEVEN,
                DEFAULT_COLOR_SEVEN);
        slices[7] = new WheelSlice(DEFAULT_AMOUNT_EIGHT, DEFAULT_PERCENTAGE_EIGHT,
                DEFAULT_COLOR_EIGHT);
        slices[8] = new WheelSlice(DEFAULT_AMOUNT_NINE, DEFAULT_PERCENTAGE_NINE,
                DEFAULT_COLOR_NINE);
        slices[9] = new WheelSlice(DEFAULT_AMOUNT_TEN, DEFAULT_PERCENTAGE_TEN,
                DEFAULT_COLOR_TEN);

    }

    /**
     * Resetting the slices to their default values.
     */
    public void resetDefault(){
        slices[0].setAmount(DEFAULT_AMOUNT_ONE);
        slices[0].setPercentage(DEFAULT_PERCENTAGE_ONE);
        slices[0].setColor(DEFAULT_COLOR_ONE);

        slices[1].setAmount(DEFAULT_AMOUNT_TWO);
        slices[1].setPercentage(DEFAULT_PERCENTAGE_TWO);
        slices[1].setColor(DEFAULT_COLOR_TWO);

        slices[2].setAmount(DEFAULT_AMOUNT_THREE);
        slices[2].setPercentage(DEFAULT_PERCENTAGE_THREE);
        slices[2].setColor(DEFAULT_COLOR_THREE);

        slices[3].setAmount(DEFAULT_AMOUNT_FOUR);
        slices[3].setPercentage(DEFAULT_PERCENTAGE_FOUR);
        slices[3].setColor(DEFAULT_COLOR_FOUR);

        slices[4].setAmount(DEFAULT_AMOUNT_FIVE);
        slices[4].setPercentage(DEFAULT_PERCENTAGE_FIVE);
        slices[4].setColor(DEFAULT_COLOR_FIVE);

        slices[5].setAmount(DEFAULT_AMOUNT_SIX);
        slices[5].setPercentage(DEFAULT_PERCENTAGE_SIX);
        slices[5].setColor(DEFAULT_COLOR_SIX);

        slices[6].setAmount(DEFAULT_AMOUNT_SEVEN);
        slices[6].setPercentage(DEFAULT_PERCENTAGE_SEVEN);
        slices[6].setColor(DEFAULT_COLOR_SEVEN);

        slices[7].setAmount(DEFAULT_AMOUNT_EIGHT);
        slices[7].setPercentage(DEFAULT_PERCENTAGE_EIGHT);
        slices[7].setColor(DEFAULT_COLOR_EIGHT);

        slices[8].setAmount(DEFAULT_AMOUNT_NINE);
        slices[8].setPercentage(DEFAULT_PERCENTAGE_NINE);
        slices[8].setColor(DEFAULT_COLOR_NINE);

        slices[9].setAmount(DEFAULT_AMOUNT_TEN);
        slices[9].setPercentage(DEFAULT_PERCENTAGE_TEN);
        slices[9].setColor(DEFAULT_COLOR_TEN);
    }


    /**
     * Setting xValue
     * @param xValue value that this.xValue will take
     */
    public void setXValue(double xValue) {
        this.xValue = xValue;
    }

    /**
     * Setting yValue
     * @param yValue value that this.yValue will take
     */
    public void setYValue(double yValue) {
        this.yValue = yValue;
    }

    /**
     * Setting radius
     * @param radius value that this.radius will take
     */
    public void setRadius(double radius) {
        if(radius<= 0)
            radius = 100;
        this.radius = radius;
    }

    /**
     * @return xValue
     */
    public double getXValue() {
        return xValue;
    }

    /**
     * @return yValue
     */
    public double getYValue() {
        return yValue;
    }

    /**
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @return INITIAL_DELAY
     */
    public int getINITIAL_DELAY(){
        return INITIAL_DELAY;
    }

    /**
     * Sets a specific slice's color
     * @param index Index in which the slice is located within slices
     * @param color Color to which the slice's color will be set
     */
    public void setSliceColor(int index, Color color){
        slices[index].setColor(color);
    }

    /**
     * Sets a specific slice's amount
     * @param index Index in which the slice is located within slices
     * @param amount Amount to which the slice's amount will be set
     */
    public void setSliceAmount(int index, double amount){
        slices[index].setAmount(amount);
    }

    /**
     * Sets a specific slice's percentage
     * @param index Index in which the slice is located within slices
     * @param percentage Percentage to which the slice's percentage will be set
     */
    public void setSlicePercentage(int index, double percentage){
        slices[index].setPercentage(percentage);
    }

    /**
     * Sets a specific slice's firstLastAngle
     * @param index Index in which the slice is located within slices
     * @param fla Value that the slice's firstLastAngle will be set to
     */
    public void setSliceFLA(int index, double fla){
        slices[index].setFirstLastAngle(fla);
    }

    /**
     * Sets a specific slice's secondLastAngle
     * @param index Index in which the slice is located within slices
     * @param sla value that the slice's secondLastAngle will be set to
     */
    public void setSliceSLA(int index, double sla){
        slices[index].setSecondLastAngle(sla);
    }

    /**
     * @param index Index in which the slice is located within slices
     * @return The slice's color
     */
    public Color getSliceColor(int index){
        return slices[index].getColor();
    }

    /**
     * @param index Index in which the slice is located within slices
     * @return The slice's amount
     */
    public double getSliceAmount(int index){
        return slices[index].getAmount();
    }

    /**
     * @param index Index in which the slice is located within slices
     * @return The slice's percentage
     */
    public double getSlicePercentage(int index){
        return slices[index].getPercentage();
    }

    /**
     * @param index Index in which the slice is located within slices
     * @return The slice's firstLastAngle
     */
    public double getSliceFLA(int index){
        return slices[index].getFirstLastAngle();
    }

    /**
     * @param index Index in which the slice is located within slices
     * @return The slice's secondLastAngle
     */
    public double getSliceSLA(int index){
        return slices[index].getSecondLastAngle();
    }

    /**
     * @return The length of the slices array
     */
    public int getSlicesLength(){
        return slices.length;
    }

    /**
     * @return slices
     */
    public WheelSlice [] getSlices(){
        // Create a new array with the same values as slices to return without privacy leaks
        WheelSlice [] toReturn = new WheelSlice[slices.length];

        for(int i =0; i<slices.length; i++){
            toReturn[i] = new WheelSlice(slices[i].getAmount(), slices[i].getPercentage(),
                    slices[i].getColor());
            toReturn[i].setFirstLastAngle(slices[i].getFirstLastAngle());
            toReturn[i].setSecondLastAngle(slices[i].getSecondLastAngle());
        }

        return toReturn;
    }

    /**
     * @return amountWon
     */
    public double getAmountWon() { return amountWon;}

    /**
     * Adds one to lastAngle.
     */
    public void addOneLastAngle(){
        lastAngle ++;
    }

    /**
     * @return lastAngle
     */
    public double getLastAngle(){
        return lastAngle;
    }

    /**
     * Makes sure that lastAngle is always within a range of [0,360), in order to represent a
     * circle's angles accurately. This method is called whenever the value of lastAngle changes.
     */
    private void resetLastAngle(){
        lastAngle = lastAngle % 360;
    }

    /**
     * Draws the initial wheel with the default values.
     * @param gc Graphics context in which the wheel will be drawn
     */
    public void drawDefault(GraphicsContext gc){
        lastAngle = ((DEFAULT_PERCENTAGE_ONE/100)*360)/2;
        drawWheel(gc);
    }

    /**
     * Draws the wheel starting based on the lastAngle's value.
     * @param gc Graphics context in which the wheel will be drawn
     */
    public void drawWheel(GraphicsContext gc){

        // Draws the slices for the first half of the wheel
        for (WheelSlice slice : slices) {
            slice.draw(gc, lastAngle, xValue, yValue, radius);
            // set firstLastAngle to lastAngle in order to later on determine the slice that is
            // in the winning position
            slice.setFirstLastAngle(lastAngle);
            //Adds the slice's angle to lastAngle so that the next slice start angle is the end
            // angle of this slice
            lastAngle += slice.getAngle();
            resetLastAngle(); // keep last angle within range
        }

        // Draws the repeated slices for the second half of the wheel
        for (WheelSlice slice : slices) {
            slice.draw(gc, lastAngle, xValue, yValue, radius);
            // set secondLastAngle to lastAngle in order to later on determine the slice that is
            // in the winning position
            slice.setSecondLastAngle(lastAngle);
            //Adds the slice's angle to lastAngle so that the next slice start angle is the end
            // angle of this slice
            lastAngle += slice.getAngle();
            resetLastAngle(); // keep last angle within range
        }

        // Drawing the triangle at the top of the wheel, indicating the winning slice
        gc.setFill(Color.rgb(230, 78, 78));
        double [] xPoints = {xValue + radius -10, xValue + radius, xValue + radius +10};
        double [] yPoints = {yValue-30, yValue +5,
                yValue -30};
        gc.fillPolygon(xPoints, yPoints, 3);
        gc.setStroke(Color.BLACK);
        gc.strokePolygon(xPoints, yPoints, 3);
    }

    /**
     * Draws the legend for the subdivisions of the wheel.
     * @param gc Graphics context in which the legend will be drawn
     * @param xValue x-coordinate of the legend's location, top-left corner
     * @param yValue y-coordinate of the legend's location, top-left corner
     */
    public void drawLegend(GraphicsContext gc, double xValue, double yValue){
        xValue +=10;
        yValue +=10;
        for (WheelSlice slice : slices) {
            // call each slice's drawLegend method
            slice.drawLegend(gc, xValue, yValue);
            // adds to the y-coordinate so that the next legend is drawn below the last one
            yValue += 20;
        }
    }

    /**
     * After the wheel stops it locates the slice that is in the wining position, retrieves its
     * amount and adds it to amountWon.
     */
    public void updateWinnings() {
        // since the next for loop uses the current index and the next one to determine the
        // winning index, the last slice won't be able to be compared, it is assumed it's the
        // winning index and changed if otherwise.
        int winningIndex = slices.length-1;

        // goes through slices from 0 to the penultimate one
        for(int i = 0; i<slices.length-1; i++){
            // Checks for both appearances of the slice if its start angle is less or equal to 90
            // and if the next slice's angle (the ending angle of this slice) is higher or equal to
            // 90. This would put the slice in the winning position.
            if((slices[i].getFirstLastAngle()<=90 && slices[i+1].getFirstLastAngle()>=90)||
                    (slices[i].getSecondLastAngle()<=90 && slices[i+1].getSecondLastAngle()>=90))
                winningIndex = i;
        }

        // the amount of the slice is added to the amountWon, accumulating it
        amountWon += slices[winningIndex].getAmount();
    }

    /**
     * Resets amountWon to 0.
     */
    public void resetAmountWon(){
        amountWon = 0;
    }

    /**
     * Takes care of the process through which the wheel goes through when spinning.
     * @param gc Graphics context in which the spinning wheel will be drawn
     */
    public void spinTheWheel(GraphicsContext gc){
        // Create a WheelSpinning object that will be used by the timer to create the animation
        // of the wheel spinning
        WheelSpinning spinningWheel = new WheelSpinning(this, gc);

        // Creates a new timer that does the spinningWheel action performed each INITIAL_DELAY
        // amount
        spinTimer = new javax.swing.Timer(INITIAL_DELAY, spinningWheel);
        spinTimer.start(); // start the timer
    }

    /**
     * Takes care of the process through which the wheel goes through when stopping.
     *
     * @param amountWon Label that displays amountWon
     * @param stopSpin Button that stops the wheel spinning
     * @param titleTimer Timer that takes care of the title animation
     * @param titleB Button that turns on/off the title animation
     */
    public void stopSpinning(Label amountWon, Button stopSpin, Timer titleTimer, Button titleB) {
        /*
            NOTE: The view is a little too implemented into the model here. However, I'm still
            getting familiar to managing events and wasn't able to separate them completely at
            the moment.
         */

        // Disable the stopSpin button to avoid the user from clicking it again and messing up
        // the process.
        stopSpin.setDisable(true);

        // Create the timer that will take care of stopping the wheel after it has been spinning
        stopTimer = new java.util.Timer();

        // Create a WheelStopping object that will be used by the timer to stop the wheel
        WheelStopping stopTask = new WheelStopping(stopTimer, spinTimer, this, amountWon,
                titleTimer, titleB);
        final int BEGINNING_DELAY = 0; // Delay before starting
        final int INTERVAL_DELAY = 500; // Delay between each task performed

        // Timer is scheduled... starts after BEGINNING_DELAY
        stopTimer.schedule(stopTask, BEGINNING_DELAY, INTERVAL_DELAY);
    }
}
