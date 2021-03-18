package assignment5_000813686;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 * This class represents on of the subdivisions of a wheel of fortune. It is meant to be used
 * with the class Wheel. Within Wheel, each WheelSlice is meant to represent actually two
 * subdivisions of the wheel.
 *
 * @author Silvia Mariana Reyesvera Quijano
 */
public class WheelSlice {
    /**
     * Amount to be won if this slice is the winning slice after a spin.
     */
    private double amount;

    /**
     * Percentage of the wheel that one slice represents.
     * When used in Wheel, each slice is used twice, hence the actual percentage of the whole
     * will be double of value.
     */
    private double percentage;

    /**
     * The equivalent of the percentage but in degrees.
     */
    private double angle;

    /**
     * Keeps track of the angle (in the polar coordinate system) of the location of the start of
     * the first slice's appearance (first being identified as that appears first in the
     * coordinate system, or seen as the lowest angular value).
     */
    private double firstLastAngle;

    /**
     * Keeps track of the angle (in the polar coordinate system) of the location of the start of
     * the second slice's appearance (second being identified as that appears second in the
     * coordinate system, or seen as the highest angular value).
     */
    private double secondLastAngle;

    /**
     * Represents the color that the slice will have when drawn.
     */
    private Color color;

    /**
     * Constructor.
     * @param amount Value that this.amount will take
     * @param percentage Value that this.percentage will take
     * @param color Value that this.color will take
     */
    public WheelSlice(double amount, double percentage, Color color){
        this.amount = amount;
        setPercentage(percentage);
        this.color = color;

        setAngle();
    }

    /**
     * Calculates the equivalent of the percentage into degrees.
     */
    private void setAngle() {
        angle = 360*(percentage/100);
    }

    /**
     * Sets amount
     * @param amount Value that this.amount will take
     */
    public void setAmount(double amount){
        this.amount = amount;
    }

    /**
     * Sets percentage
     * @param percentage Value that this.percentage will take
     */
    public void setPercentage(double percentage){
        if(percentage<0)
            percentage = 0;
        else if(percentage>100)
            percentage = 100;

        this.percentage = percentage;
        setAngle();
    }

    /**
     * Sets color
     * @param color Value that this.color will take
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Sets firstLastAngle
     * @param x Value that firstLastAngle will take
     */
    public void setFirstLastAngle( double x) {
        firstLastAngle = x;
    }

    /**
     * Sets secondLastAngle
     * @param x Value that secondLastAngle will take
     */
    public void setSecondLastAngle( double x) {
        secondLastAngle = x;
    }

    /**
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * @return angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @return firstLastAngle
     */
    public double getFirstLastAngle() {
        return firstLastAngle;
    }

    /**
     * @return secondLastAngle
     */
    public double getSecondLastAngle() {
        return secondLastAngle;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Drawing the slice as an arc in a graphics context.
     * @param gc Graphics context in which the slice will be drawn.
     * @param startAngle The start angle of the drawn slice.
     * @param xValue The x-coordinate of the location of the slice, top-left corner of the CIRCLE
     *              in which the round arc is located.
     * @param yValue The y-coordinate of the location of the slice, top-left corner of the CIRCLE
     *              in which the round arc is located.
     * @param radius The radius of the CIRCLE taken as a base for drawing the arc (slice).
     */
    public void draw(GraphicsContext gc, double startAngle, double xValue, double yValue,
                     double radius){
        // Draws the slice represented as an arc in an imaginary circle

        gc.setFill(color);

        // xValue and yValue are the x-coordinate and y-coordinate respectively of the location of
        // the top-left corner of the imaginary CIRCLE in which the round arc is located.
        // The radius is that of the circle as well. Therefore the width and height is twice the
        // radius
        gc.fillArc(xValue, yValue, radius*2, radius*2, startAngle, angle, ArcType.ROUND);
        gc.setStroke(Color.BLACK);
        gc.strokeArc(xValue, yValue, radius*2, radius*2, startAngle, angle, ArcType.ROUND);
    }

    /**
     * Draws the legend of the slice.
     * @param gc Graphics context in which the legend will be drawn
     * @param xValue The x-coordinate of the location of the legend, top-left corner
     * @param yValue The y-coordinate of the location of the legend, top-left corner
     */
    public void drawLegend(GraphicsContext gc, double xValue, double yValue){
        // Draws the legend of the slice in the provided gc
        gc.setFill(color);
        gc.fillRect(xValue, yValue, 10, 10);
        gc.setStroke(Color.WHITE);
        gc.strokeText(Double.toString(amount), xValue + 20, yValue + 10);
    }

}
