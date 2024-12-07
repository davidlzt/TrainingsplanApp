package frontend;

import backend.database.DatabaseSetup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrationPage {

    private JFrame frame;
    private JPanel panel;
    private CardLayout cardLayout;

    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JTextField gewichtField, ageField;
    private JTextField groesseField;
    private JComboBox<String> geschlechtBox;
    private JButton backButton = new JButton("← Zurück");

    private String username, email, password, confirmPassword;
    private double gewicht, groesse;
    private int age;
    private String geschlecht;

    public RegistrationPage(Dimension dimension) {
        DatabaseSetup dbSetup = new DatabaseSetup();
        dbSetup.createTables();

        frame = new JFrame("Benutzerregistrierung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(dimension);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        JPanel page1 = createPage1();
        page1.setBackground(Color.DARK_GRAY);
        JPanel page2 = createPage2();
        page2.setBackground(Color.DARK_GRAY);
        JPanel page3 = createPage3();
        page3.setBackground(Color.DARK_GRAY);
        JPanel page4 = createPage4();
        page4.setBackground(Color.DARK_GRAY);

        panel.add(page1, "Page1");
        panel.add(page2, "Page2");
        panel.add(page3, "Page3");
        panel.add(page4, "Page4");

        backButton.setVisible(false);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.add(backButton);
        frame.add(topPanel, BorderLayout.NORTH);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createPage1() {
        JPanel page = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Benutzername:");
        styleLabel(usernameLabel);
        usernameField = new JTextField(20);
        usernameField.setBackground(Color.WHITE);
        usernameField.setForeground(Color.DARK_GRAY);

        JLabel emailLabel = new JLabel("E-Mail:");
        styleLabel(emailLabel);
        emailField = new JTextField(20);
        emailField.setBackground(Color.WHITE);
        emailField.setForeground(Color.DARK_GRAY);

        JButton nextButton = new JButton("Weiter");
        styleButton(nextButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        page.add(usernameLabel, gbc);

        gbc.gridx = 1;
        page.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        page.add(emailLabel, gbc);

        gbc.gridx = 1;
        page.add(emailField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        page.add(nextButton, gbc);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                email = emailField.getText();

                if (username.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Bitte Benutzername und E-Mail ausfüllen.", "Fehler", JOptionPane.ERROR_MESSAGE);
                } else {
                    cardLayout.show(panel, "Page2");
                    backButton.setEnabled(true);
                    backButton.setVisible(true);
                    styleButton(backButton);
                    setBackButtonAction("Page1");
                }
            }
        });

        return page;
    }

    private JPanel createPage2() {
        JPanel page = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel passwordLabel = new JLabel("Passwort:");
        styleLabel(passwordLabel);
        passwordField = new JPasswordField(20);
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.DARK_GRAY);

        JLabel confirmPasswordLabel = new JLabel("Passwort bestätigen:");
        styleLabel(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setBackground(Color.WHITE);
        confirmPasswordField.setForeground(Color.DARK_GRAY);

        JButton nextButton = new JButton("Weiter");
        styleButton(nextButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        page.add(passwordLabel, gbc);

        gbc.gridx = 1;
        page.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        page.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        page.add(confirmPasswordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        page.add(nextButton, gbc);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                password = new String(passwordField.getPassword());
                confirmPassword = new String(confirmPasswordField.getPassword());

                if (password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Bitte beide Passwort-Felder ausfüllen.", "Fehler", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwörter stimmen nicht überein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                } else {
                    cardLayout.show(panel, "Page3");
                    setBackButtonAction("Page2");
                }
            }
        });

        setBackButtonAction("Page1");

        return page;
    }

    private JPanel createPage3() {
        JPanel page = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel gewichtLabel = new JLabel("Gewicht (kg):");
        styleLabel(gewichtLabel);
        gewichtField = new JTextField(20);
        gewichtField.setBackground(Color.WHITE);
        gewichtField.setForeground(Color.DARK_GRAY);

        JLabel ageLabel = new JLabel("Alter:");
        styleLabel(ageLabel);
        ageField = new JTextField(20);
        ageField.setBackground(Color.WHITE);
        ageField.setForeground(Color.DARK_GRAY);

        JButton nextButton = new JButton("Weiter");
        styleButton(nextButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        page.add(gewichtLabel, gbc);

        gbc.gridx = 1;
        page.add(gewichtField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        page.add(ageLabel, gbc);

        gbc.gridx = 1;
        page.add(ageField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        page.add(nextButton, gbc);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gewicht = Double.parseDouble(gewichtField.getText());
                    age = Integer.parseInt(ageField.getText());

                    if (gewicht <= 0 || age <= 0) {
                        JOptionPane.showMessageDialog(frame, "Bitte gültige Werte für Gewicht und Alter eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    } else {
                        cardLayout.show(panel, "Page4");
                        setBackButtonAction("Page3");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte gültige Zahlen für Gewicht und Alter eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setBackButtonAction("Page2");

        return page;
    }

    private JPanel createPage4() {
        JPanel page = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel groesseLabel = new JLabel("Größe (cm):");
        styleLabel(groesseLabel);
        groesseField = new JTextField(20);
        groesseField.setBackground(Color.WHITE);
        groesseField.setForeground(Color.DARK_GRAY);

        JLabel geschlechtLabel = new JLabel("Geschlecht:");
        styleLabel(geschlechtLabel);
        String[] geschlechter = {"Männlich", "Weiblich", "Andere"};
        geschlechtBox = new JComboBox<>(geschlechter);

        JButton submitButton = new JButton("Registrieren");
        styleButton(submitButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        page.add(groesseLabel, gbc);

        gbc.gridx = 1;
        page.add(groesseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        page.add(geschlechtLabel, gbc);

        gbc.gridx = 1;
        page.add(geschlechtBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        page.add(submitButton, gbc);

        setBackButtonAction("Page3");

        return page;
    }

    private void setBackButtonAction(String previousPage) {
        for (ActionListener al : backButton.getActionListeners()) {
            backButton.removeActionListener(al);
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, previousPage);
            }
        });

        styleButton(backButton);
        backButton.setVisible(true);
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
