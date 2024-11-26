package frontend;

import backend.database.DeviceHandler;
import backend.models.Device;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDeviceWindow extends JFrame {
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField typeField;
    private JTextField imageField;

    private DeviceHandler deviceHandler;

    public AddDeviceWindow() {
        deviceHandler = new DeviceHandler();

        setTitle("Gerät hinzufügen");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Beschreibung:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Typ:"));
        typeField = new JTextField();
        inputPanel.add(typeField);

        inputPanel.add(new JLabel("Bild-URL:"));
        imageField = new JTextField();
        inputPanel.add(imageField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Hinzufügen");
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDevice();
            }
        });

        setVisible(true);
    }

    private void addDevice() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String type = typeField.getText();
        String image = imageField.getText();

        Device device = new Device(name, description, type, image, 0); // 0 als Platzhalter für ID

        deviceHandler.insertDevice(device);

        JOptionPane.showMessageDialog(this, "Gerät erfolgreich hinzugefügt!", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
