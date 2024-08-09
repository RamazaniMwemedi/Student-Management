import javax.swing.*;

public class Courses extends JFrame {
    public Courses() {
        setTitle("Courses");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Courses();
    }
}
