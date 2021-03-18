package assignment5_000813686;

import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This class represents a wheel stopping from spinning. It inherits from TimeTask, in
 * order to be used by a timer to perform a given task after each amount of time.
 * This class is meant to be used by Wheel.
 *
 * NOTE: java.util.Timer is used here inheriting TimeTask, instead of using java.swing.Timer and
 * implementing ActionListener. The techniques are similar, but I struggled at a
 * particular spot and the method used solved my problems.
 *
 * @author Silvia Mariana Reyesvera Quijano
 */
public class WheelStopping extends TimerTask {
    /**
     * Keeps track of the amount of times the task is performed (when the run() is called).
     */
    private int counter = 0;

    /**
     * Timer used to keep track of the stopping of the wheel.
     */
    private java.util.Timer stopTimer;

    /**
     * Timer used to keep track of the wheel spinning.
     */
    private javax.swing.Timer spinTimer;

    /**
     * Wheel that is spinning and is being stopped from spinning.
     */
    private Wheel wheelOfFortune;

    /**
     * amount Won label, where accumulated amount that has been won is displayed.
     */
    private Label amountWon;

    /**
     * Timer that keeps track of the title animation. Can be set to null if not used.
     */
    private javax.swing.Timer titleTimer;

    /**
     * Button that is used to turn on/off the title animation. Can be set to null if not used.
     */
    private Button titleButton;

    /**
     * Constructor
     * @param stopTimer Value that this.stopTimer will take
     * @param spinTimer Value that this.spinTimer will take
     * @param wheelOfFortune Value that this.wheelOfFortune will take
     * @param amountWon Value that this.amountWon will take
     * @param titleTimer Value that this.titleTimer will take
     * @param titleButton Value that this.titleButton will take
     */
    public WheelStopping(java.util.Timer stopTimer, javax.swing.Timer spinTimer,
                         Wheel wheelOfFortune, Label amountWon, javax.swing.Timer titleTimer,
                         Button titleButton){
        this.wheelOfFortune = wheelOfFortune;
        this.stopTimer = stopTimer;
        this.spinTimer = spinTimer;
        this.amountWon = amountWon;
        this.titleTimer = titleTimer;
        this.titleButton = titleButton;
    }

    /**
     * The action that is performed after each amount of time (based on the stopTimer).
     * In this case, it calls changeDelay and adds on to counter. It also checks if counter is
     * bigger than a random number between 10 and 20, if so it takes care of final changes before
     * stopping the stopTimer.
     */
    @Override
    public void run() {
        int amountOfChanges = (int)(Math.random()*10+10);

        changeDelay(); // Calls to change the delay of the spinTimer
        counter++;

        if (counter > amountOfChanges) {
            spinTimer.stop();
            lastChanges();
            if(titleTimer!= null)
                titleTimer.start();
            stopTimer.cancel();
        }
    }

    /**
     * With ever call it increases the delay of the spinTimer (controlling the spinning of the
     * wheel), in order to make it seem like the wheel is decelerating.
     */
    private void changeDelay() {
        // Create a random number between 1.4 and 1.6, which will be the number to which the
        // current delay will be multiplied. Increasing the delay with each call to the method,
        // hence 'slowing' the wheel.
        double delayChange = Math.random()*0.2+1.4;

        spinTimer.stop(); // stops the timer to change the delay
        spinTimer.setDelay((int)(spinTimer.getDelay()*delayChange)); // change the delay
        spinTimer.start(); // start the timer again

    }

    /**
     * Is called before when the wheel is stopped. It updates the winnings and therefore the
     * label that represents this value. It also enables the animation of the title.
     */
    private void lastChanges(){
        // The wheel is now stopped
        wheelOfFortune.updateWinnings(); // The winnings are updated
        Platform.runLater(() -> {
            amountWon.setText("$" + wheelOfFortune.getAmountWon()); // The label showing the
                                                                    // winnings is updated as well
            if(titleButton != null) // If there is a title Button
                titleButton.setDisable(false); // enable it, to start the title's animation once
                                                // again
        });
    }

}
