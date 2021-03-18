package assignment5_000813686;

import javafx.scene.canvas.GraphicsContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a wheel spinning. It implements the class ActionListener, in order to
 * allow for a certain action when something happens. In this case, this allows a timer to redraw
 * the wheel every certain time, giving the illusion of a wheel spinning. This class is meant to
 * be used by Wheel.
 *
 * @author Silvia Mariana Reyesvera Quijano
 */
public class WheelSpinning implements ActionListener {

    /**
     * The wheel (of the class Wheel) that will be spinning.
     */
    private Wheel wheelSpinning;
    /**
     * Graphics context in which the wheel will be drawn spinning.
     */
    private GraphicsContext gc;

    /**
     * Constructor.
     * @param wheel Value that wheelSpinning will take
     * @param gc Value that this.gc will take
     */
    public WheelSpinning(Wheel wheel, GraphicsContext gc) {
        wheelSpinning = wheel;
        this.gc = gc;
    }

    /**
     * Draws the wheel based on the wheel's lastAngle, and adds one to this variable, allowing
     * that when this action is performed the next time, the wheel is drawn one angle afterwards.
     * This action in repetition gives the spinning of the wheel.
     * @param e Not used
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        wheelSpinning.addOneLastAngle(); // Moves the lastAngle one ahead so that when the wheel
                                         // is redrawn next, it is moved one degree
        wheelSpinning.drawWheel(gc); // Draws the wheel
    }
}
