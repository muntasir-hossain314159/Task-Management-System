package sample;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import java.awt.Font;

//todo make sure to change the calendar buttons in such a way that when a user clicks on it it will query the task table on the database and give all the appointments of the user on that date
//so we need to use a for loop to fill up a "table" with months that will be buttons. The user will type in the year. This will call calendarview and this will populate "month and year chooser" the fields with the particular month and year the user chose
//and display the calendar for that month and year
// the user can also click on a date and view all the task in that date. That is equal to the page that the user will get if they search for a particular date after they press the  task button
//the user should also be able to add tasks by clicking on the add button which will take the user to same page when they press the task button

public class CalendarView {

    private JFrame frame;

    public void run()
    {
        try
        {
            CalendarView window = new CalendarView();
            window.frame.setVisible(true);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Create the application.
     */
    public CalendarView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{67, 0};
        gridBagLayout.rowHeights = new int[]{18, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("Calendar");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

        JCalendar calendar = new JCalendar();
        GridBagConstraints gbc_calendar = new GridBagConstraints();
        gbc_calendar.fill = GridBagConstraints.BOTH;
        gbc_calendar.gridx = 0;
        gbc_calendar.gridy = 1;
        frame.getContentPane().add(calendar, gbc_calendar);

    }

}
