package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LandingPage extends JFrame {

    public LandingPage(Dimension dimension) {
        JFrame startFrame = new JFrame("Willkommen zur TrainingsApp");
        startFrame.setSize(dimension);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton loginButton = new JButton("Einloggen");
        styleButton(loginButton);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(loginButton, gbc);

        JButton registerButton = new JButton("Registrieren");
        styleButton(registerButton);
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

    private void styleButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

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
}
