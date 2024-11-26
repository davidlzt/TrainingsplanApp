package frontend;

import backend.database.DatabaseHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    public LoginPage(Dimension dimension) {
        JFrame loginFrame = new JFrame("Einloggen");
        loginFrame.setSize(dimension);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new BorderLayout());

        // Zurück-Button oben links
        JButton backButton = new JButton("← Zurück");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        loginFrame.add(topPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Benutzername:");
        JTextField userField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Passwort:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Einloggen");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                DatabaseHandler dbHandler = new DatabaseHandler();
                boolean isValidUser = dbHandler.validateUser(username, password);
                boolean isAdmin = dbHandler.isAdmin(username);

                if (isValidUser) {
                    JOptionPane.showMessageDialog(loginFrame, "Erfolgreich eingeloggt!", "Login", JOptionPane.INFORMATION_MESSAGE);
                    loginFrame.dispose();
                    new HomePage(username, isAdmin);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Benutzername oder Passwort falsch.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener für den Zurück-Button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                new LandingPage(new Dimension(800, 600)); // Gehe zurück zur LandingPage
            }
        });

        loginFrame.add(panel, BorderLayout.CENTER);
        loginFrame.setVisible(true);
    }
}
