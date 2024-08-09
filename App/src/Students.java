import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Students extends JFrame {
    public static ArrayList<Student> students = new ArrayList<>();
    private JList<String> studentList;
    private DefaultListModel<String> listModel;
    private JButton addStudentButton;
    private JButton homeButton;

    public Students() {
        setTitle("Students");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        listModel = new DefaultListModel<>();
        studentList = new JList<>(listModel);
        addStudentButton = new JButton("Add Student");
        homeButton = new JButton("Home");

        // Set layout and add components
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addStudentButton);
        buttonPanel.add(homeButton);
        mainPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Load initial students
        loadStudents();

        // Handlers for the add student button
        addStudentButton.addActionListener(e -> openAddStudentDialog());

        // Handler for the home button
        homeButton.addActionListener(e -> {
            new Home();
            dispose();
        });

        setVisible(true);
    }

    private void loadStudents() {
        for (Student student : students) {
            listModel.addElement(student.getName() + " (" + student.getId() + ")");
        }
    }

    private void openAddStudentDialog() {
        JDialog dialog = new JDialog(this, "Add Student", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new FlowLayout());

        JTextField nameTextField = new JTextField(20);
        JTextField idTextField = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        dialog.add(new JLabel("Name:"));
        dialog.add(nameTextField);
        dialog.add(new JLabel("ID:"));
        dialog.add(idTextField);
        dialog.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String id = idTextField.getText();
                if (name.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please enter a name and ID.");
                } else {
                    Student newStudent = new Student(name, id);
                    students.add(newStudent);
                    listModel.addElement(newStudent.getName() + " (" + newStudent.getId() + ")");
                    dialog.dispose();
                }
            }
        });

        dialog.setVisible(true);
    }

    public static class Student {
        private String name;
        private String id;

        public Student(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Students::new);
    }
}