import javax.swing.*;

/**
 * This class is a reuseable component that display messages to user
 *
 * @author Mide Ibraheem
 * @version v1.0
 */
public class DisplayMessage {
    private String message; // the message to display
    private  boolean auto_close; // determines if the popup should close automatically
    private int milliSeconds; // determines how many milliseconds until the popup closes

    /**
     * Constructor of the DisplayMessage class
     * @param message - the message to display
     * @param auto_close - determines if the popup should close automatically
     * @param milliSeconds - determines how many milliseconds until the popup closes
     */
    public DisplayMessage(String message, boolean auto_close, int milliSeconds) {
        this.message = message;
        this.auto_close = auto_close;
        this.milliSeconds = 0;

        if (this.auto_close) {
            this.milliSeconds = milliSeconds;
        }

        JOptionPane optionPane = new JOptionPane(this.message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Sudoku");

        if (this.auto_close) {
            Timer timer = new Timer(this.milliSeconds, evt -> {
                dialog.setVisible(false);
                dialog.dispose();
            });

            timer.setRepeats(false);
            timer.start();
        }

        dialog.setVisible(true);
    }


}
