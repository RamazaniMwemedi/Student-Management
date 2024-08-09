import javax.swing.*;

public class Grades extends JFrame {
    public Grades() {
        setTitle("Grades");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Grades();
    }
}
