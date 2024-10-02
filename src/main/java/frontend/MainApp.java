package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {

    public MainApp() {
        JFrame landingFrame = new JFrame("TrainingsApp");
        landingFrame.setSize(600, 400);
        landingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        landingFrame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Datei");
        JMenuItem exitItem = new JMenuItem("Beenden");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        JMenu trainingMenu = new JMenu("Trainingsplan");
        JMenuItem viewPlanItem = new JMenuItem("Plan anzeigen");
        JMenuItem createPlanItem = new JMenuItem("Neuen Plan erstellen");
        trainingMenu.add(viewPlanItem);
        trainingMenu.add(createPlanItem);

        JMenu settingsMenu = new JMenu("Einstellungen");
        JMenuItem userSettingsItem = new JMenuItem("Benutzereinstellungen");
        settingsMenu.add(userSettingsItem);

        menuBar.add(fileMenu);
        menuBar.add(trainingMenu);
        menuBar.add(settingsMenu);

        landingFrame.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Willkommen in der TrainingsApp!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        viewPlanItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                JLabel planLabel = new JLabel("Dein Trainingsplan:", SwingConstants.CENTER);
                planLabel.setFont(new Font("Arial", Font.BOLD, 16));
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
                mainPanel.add(settingsLabel, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        landingFrame.add(mainPanel);
        landingFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainApp();
    }
}
