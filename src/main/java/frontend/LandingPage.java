package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame{

    public LandingPage(Dimension dimension) {
        JFrame startFrame = new JFrame("Willkommen zur TrainingsApp");
        startFrame.setSize(dimension);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton loginButton = new JButton("Einloggen");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(loginButton, gbc);

        JButton registerButton = new JButton("Registrieren");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(registerButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();
                new LoginPage(new Dimension(800, 600));
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();
                new RegistrationPage(new Dimension(800, 600));
            }
        });
        startFrame.add(panel);
        startFrame.setVisible(true);
    }
}
