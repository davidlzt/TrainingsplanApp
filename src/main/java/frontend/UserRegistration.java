package frontend;

import database.DatabaseHandler; // Importiere die DatabaseHandler-Klasse

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistration {

    private JFrame frame;
    private JPanel panel;
    private CardLayout cardLayout;

    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JTextField gewichtField, ageField;
    private JTextField groesseField;
    private JComboBox<String> geschlechtBox;

    private String username, email, password, confirmPassword;
    private double gewicht, groesse;
    private int age;
    private String geschlecht;

    public UserRegistration() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.createTable(); // Tabelle erstellen

        frame = new JFrame("Benutzerregistrierung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Fenster zentrieren

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        JPanel page1 = createPage1();
        // Schritt 2: Passwort und Passwort-Bestätigung
        JPanel page2 = createPage2();
        JPanel page3 = createPage3();
        // Schritt 4: Größe und Geschlecht
        JPanel page4 = createPage4();

        panel.add(page1, "Page1");
        panel.add(page2, "Page2");
        panel.add(page3, "Page3");
        panel.add(page4, "Page4");

        frame.add(panel);
        frame.setVisible(true);
    }
    // Seite 1: Benutzername und E-Mail
    private JPanel createPage1() {
        JPanel page = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Benutzername:");
        usernameField = new JTextField(20);

        JLabel emailLabel = new JLabel("E-Mail:");
        emailField = new JTextField(20);

        JButton nextButton = new JButton("Weiter");

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
                }
            }
        });

        return page;
    }

    // Seite 2: Passwort und Passwort-Bestätigung
    private JPanel createPage2() {
        JPanel page = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel passwordLabel = new JLabel("Passwort:");
        passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Passwort bestätigen:");
        confirmPasswordField = new JPasswordField(20);

        JButton nextButton = new JButton("Weiter");

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
                }
            }
        });

        return page;
    }

    private JPanel createPage3() {
        JPanel page = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel gewichtLabel = new JLabel("Gewicht (kg):");
        gewichtField = new JTextField(20);

        JLabel ageLabel = new JLabel("Alter:");
        ageField = new JTextField(20);

        JButton nextButton = new JButton("Weiter");

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
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte gültige Zahlen für Gewicht und Alter eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return page;
    }

    // Seite 4: Größe und Geschlecht
    private JPanel createPage4() {
        JPanel page = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel groesseLabel = new JLabel("Größe (cm):");
        groesseField = new JTextField(20);

        JLabel geschlechtLabel = new JLabel("Geschlecht:");
        String[] geschlechter = {"Männlich", "Weiblich", "Andere"};
        geschlechtBox = new JComboBox<>(geschlechter);

        JButton submitButton = new JButton("Registrieren");

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

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    groesse = Double.parseDouble(groesseField.getText());
                    geschlecht = (String) geschlechtBox.getSelectedItem();

                    if (groesse <= 0) {
                        JOptionPane.showMessageDialog(frame, "Bitte eine gültige Größe eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Benutzer in die Datenbank einfügen
                        DatabaseHandler dbHandler = new DatabaseHandler();
                        dbHandler.insertUser(username, email, password, gewicht, age, groesse, geschlecht);

                        JOptionPane.showMessageDialog(frame, "Registrierung erfolgreich!", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose(); // Fenster schließen
                        new MainApp(); // Zur Hauptanwendung wechseln
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte eine gültige Zahl für die Größe eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return page;
    }
}