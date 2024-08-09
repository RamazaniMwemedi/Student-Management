import javax.swing.*;

public class Home extends JFrame {
    private JPanel HomePanel;
    private JButton goButton;
    private JLabel uoplogo;
    private JRadioButton studentsRadioButton;
    private JRadioButton coursesRadioButton;
    private JRadioButton gradesRadioButton;

    public Home() {
        add(HomePanel);
        setTitle("Home");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        // Handlers for the radio buttons
        studentsRadioButton.addActionListener(e -> {
            new Students();
            dispose();
        });

        coursesRadioButton.addActionListener(e -> {
            new Courses();
            dispose();
        });

        gradesRadioButton.addActionListener(e -> {
            new Grades();
            dispose();
        });

    }

    public static void main(String[] args) {
        new Home();
    }

}
