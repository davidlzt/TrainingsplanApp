package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private String username;
    private boolean isAdmin;
    private boolean darkmode = false;

    public HomePage(String username, boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;

        JFrame landingFrame = new JFrame("TrainingsApp");
        landingFrame.setSize(600, 400);
        landingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        landingFrame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Exit");
        JMenuItem exitItem = new JMenuItem("Beenden");
        JMenuItem logout = new JMenuItem("LogOut");

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                landingFrame.dispose();
                showLoginScreen();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(logout);
        fileMenu.add(exitItem);

        JMenu trainingMenu = new JMenu("Trainingsplan");
        JMenuItem viewPlanItem = new JMenuItem("Plan anzeigen");
        JMenuItem createPlanItem = new JMenuItem("Neuen Plan erstellen");
        trainingMenu.add(viewPlanItem);
        trainingMenu.add(createPlanItem);

        JMenu settingsMenu = new JMenu("Einstellungen");
        JMenuItem userSettingsItem = new JMenuItem("Benutzereinstellungen");
        settingsMenu.add(userSettingsItem);

        JMenuItem toggleDarkModeItem = new JMenuItem("Dark Mode aktivieren");
        toggleDarkModeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                darkmode = !darkmode;
                updateUI(landingFrame);
                toggleDarkModeItem.setText(darkmode ? "Dark Mode deaktivieren" : "Dark Mode aktivieren");
            }
        });
        settingsMenu.add(toggleDarkModeItem);

        if (isAdmin) {
            JMenu adminMenu = new JMenu("Admin");
            JMenuItem adminPanelItem = new JMenuItem("Admin-Control-Panel");
            adminPanelItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(landingFrame, "Admin-Control-Panel geöffnet!");
                    new AdminControlPanel();
                }
            });
            adminMenu.add(adminPanelItem);
            menuBar.add(adminMenu);
        }

        menuBar.add(fileMenu);
        menuBar.add(trainingMenu);
        menuBar.add(settingsMenu);

        landingFrame.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(darkmode ? Color.BLACK : Color.WHITE);

        JLabel welcomeLabel = new JLabel("Willkommen in der TrainingsApp, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(darkmode ? Color.WHITE : Color.BLACK);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        viewPlanItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                JLabel planLabel = new JLabel("Dein Trainingsplan:", SwingConstants.CENTER);
                planLabel.setFont(new Font("Arial", Font.BOLD, 16));
                planLabel.setForeground(darkmode ? Color.WHITE : Color.BLACK);
                mainPanel.add(planLabel, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        createPlanItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                JLabel createLabel = new JLabel("Erstelle einen neuen Trainingsplan:", SwingConstants.CENTER);
                createLabel.setFont(new Font("Arial", Font.BOLD, 16));
                createLabel.setForeground(darkmode ? Color.WHITE : Color.BLACK);
                mainPanel.add(createLabel, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        userSettingsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                JLabel settingsLabel = new JLabel("Benutzereinstellungen", SwingConstants.CENTER);
                settingsLabel.setFont(new Font("Arial", Font.BOLD, 16));
                settingsLabel.setForeground(darkmode ? Color.WHITE : Color.BLACK);
                mainPanel.add(settingsLabel, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        landingFrame.add(mainPanel);
        landingFrame.setVisible(true);
    }

    private void updateUI(JFrame frame) {
        if (darkmode) {
            frame.getContentPane().setBackground(Color.BLACK);
            for (Component comp : frame.getContentPane().getComponents()) {
                comp.setForeground(Color.WHITE);
                if (comp instanceof JPanel) {
                    comp.setBackground(Color.BLACK);
                }
                if (comp instanceof JMenuBar) {
                    comp.setForeground(Color.WHITE);
                }
            }
        } else {
            frame.getContentPane().setBackground(Color.WHITE);
            for (Component comp : frame.getContentPane().getComponents()) {
                comp.setForeground(Color.BLACK);
                if (comp instanceof JPanel) {
                    comp.setBackground(Color.WHITE);
                }
                if (comp instanceof JMenuBar) {
                    comp.setForeground(Color.BLACK);
                }
            }
        }
        frame.revalidate();
        frame.repaint();
    }

    private void showLoginScreen() {
        new LandingPage(new Dimension(800, 600));
    }
}
