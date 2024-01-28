import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Contact {
    String name;
    String phone;
    String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

public class ContactManagerApp extends JFrame implements ActionListener {
    private JTextField nameField, phoneField, emailField;
    private JTextArea contactList;
    private JButton addButton, viewButton, backButton;
    private ArrayList<Contact> contacts;

    public ContactManagerApp() {
        setTitle("Contact Management System");
        setSize(400, 400);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240)); // Set background color

        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE); // Set background color
        add(mainPanel, BorderLayout.CENTER);

        JLabel headingLabel = new JLabel("Contact Management System");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headingLabel, BorderLayout.NORTH);

        mainPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        mainPanel.add(nameField);

        mainPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        mainPanel.add(phoneField);

        mainPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        mainPanel.add(emailField);

        addButton = new JButton("Add Contact");
        addButton.setBackground(new Color(0, 153, 51)); // Set button background color
        addButton.setForeground(Color.WHITE); // Set button text color
        addButton.addActionListener(this);
        mainPanel.add(addButton);

        viewButton = new JButton("View Contacts");
        viewButton.setBackground(new Color(0, 102, 204)); // Set button background color
        viewButton.setForeground(Color.WHITE); // Set button text color
        viewButton.addActionListener(this);
        mainPanel.add(viewButton);

        backButton = new JButton("Back");
        backButton.setBackground(new Color(204, 0, 0)); // Set button background color
        backButton.setForeground(Color.WHITE); // Set button text color
        backButton.addActionListener(this);
        backButton.setVisible(false);
        mainPanel.add(backButton);

        contactList = new JTextArea();
        contactList.setEditable(false);
        contactList.setBackground(new Color(255, 255, 204)); // Set text area background color
        JScrollPane scrollPane = new JScrollPane(contactList);
        add(scrollPane, BorderLayout.SOUTH);

        contacts = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                contacts.add(new Contact(name, phone, email));
                JOptionPane.showMessageDialog(this, "Contact added successfully.");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == viewButton) {
            if (contacts.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No contacts found.", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder contactInfo = new StringBuilder();
                for (Contact contact : contacts) {
                    contactInfo.append("Name: ").append(contact.name).append(", Phone: ").append(contact.phone)
                            .append(", Email: ").append(contact.email).append("\n");
                }
                contactList.setText(contactInfo.toString());
                backButton.setVisible(true);
                addButton.setEnabled(false);
                viewButton.setEnabled(false);
            }
        } else if (e.getSource() == backButton) {
            contactList.setText("");
            backButton.setVisible(false);
            addButton.setEnabled(true);
            viewButton.setEnabled(true);
        }
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactManagerApp::new);
    }
}
