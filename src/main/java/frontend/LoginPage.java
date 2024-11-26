package frontend;

import backend.database.UserHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage {
    public LoginPage(Dimension dimension) {
        JFrame loginFrame = new JFrame("Einloggen");
        loginFrame.setSize(dimension);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.setBackground(Color.DARK_GRAY);

        JButton backButton = new JButton("← Zurück");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        styleButton(backButton);
        topPanel.setBackground(Color.DARK_GRAY);
        loginFrame.add(topPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Benutzername:");
        styleLabel(userLabel);
        JTextField userField = new JTextField(15);
        userField.setBackground(Color.WHITE);
        userField.setForeground(Color.DARK_GRAY);

        JLabel passwordLabel = new JLabel("Passwort:");
        styleLabel(passwordLabel);
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.DARK_GRAY);

        JButton loginButton = new JButton("Einloggen");
        styleButton(loginButton);

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

                UserHandler userHandler = new UserHandler();
                boolean isValidUser = userHandler.validateUser(username, password);
                boolean isAdmin = userHandler.isAdmin(username);

                if (isValidUser) {
                    JOptionPane.showMessageDialog(loginFrame, "Erfolgreich eingeloggt!", "Login", JOptionPane.INFORMATION_MESSAGE);
                    loginFrame.dispose();
                    new HomePage(username, isAdmin);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Benutzername oder Passwort falsch.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                new LandingPage(new Dimension(800, 600));
            }
        });

        loginFrame.add(panel, BorderLayout.CENTER);
        loginFrame.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.LIGHT_GRAY);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
        });
    }
    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
