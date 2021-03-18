package assignment5_000813686;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a the title's letters animation, changing constantly their colors. It
 * implements the class ActionListener, in order to allow for a certain action when something
 * happens. In this case, this allows a timer to change each of the letter's color to the one of
 * the previous letter. This class is meant to be used by SpinTheWheel.
 *
 * @author Silvia Mariana Reyesvera Quijano
 */
public class ChangingColors implements ActionListener {
    /**
     * Label array that contains the letters of the title
     */
    private Label[] letters;

    /**
     * Constructor.
     * @param letters Value that this.letters will take
     */
    public ChangingColors(Label [] letters){
        this.letters = letters;
    }

    /**
     * Action performed after each amount of time, in this case based on a timer that uses this
     * class. It changes each of the letter's color to the one of the previous letter.
     * @param e Not used
     */
    @Override
    public void actionPerformed(ActionEvent e){
        // set's lastColor to the last letter's color
        Paint lastColor = letters[letters.length-1].getTextFill();
        Paint temp; // temporary variable that will be used for the switch
        for(Label letter: letters){ // goes through the letters array
            temp = letter.getTextFill(); // set temp to the letter's color
            letter.setTextFill(lastColor); // set the letter's color to lastColor
            lastColor = temp; // set lastColor to the temp's color
        }
    }
}
