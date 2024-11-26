package frontend;

import backend.database.ExerciseHandler;
import backend.models.Device;
import backend.models.Exercise;
import backend.models.Muscle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class AddExerciseWindow extends JFrame {
    private JTextField nameField;
    private JTextField difficultyField;
    private JTextField imageField;
    private JTextArea descriptionArea;
    private JList<Muscle> muscleList;
    private JList<Device> deviceList;

    private ExerciseHandler exerciseHandler;

    public AddExerciseWindow() {
        exerciseHandler = new ExerciseHandler();

        setTitle("Übung hinzufügen");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Schwierigkeitsgrad:"));
        difficultyField = new JTextField();
        inputPanel.add(difficultyField);

        inputPanel.add(new JLabel("Bild-URL:"));
        imageField = new JTextField();
        inputPanel.add(imageField);

        inputPanel.add(new JLabel("Beschreibung:"));
        descriptionArea = new JTextArea(5, 20);
        inputPanel.add(new JScrollPane(descriptionArea));

        inputPanel.add(new JLabel("Zielmuskeln:"));
        muscleList = new JList<>(Muscle.values());
        muscleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        inputPanel.add(new JScrollPane(muscleList));

        inputPanel.add(new JLabel("Geräte:"));
        List<Device> deviceOptions = Arrays.asList();
        deviceList = new JList<>(deviceOptions.toArray(new Device[0]));
        deviceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        inputPanel.add(new JScrollPane(deviceList));

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Hinzufügen");
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExercise();
            }
        });

        setVisible(true);
    }

    private void addExercise() {
        String name = nameField.getText();
        String difficulty = difficultyField.getText();
        String image = imageField.getText();
        String description = descriptionArea.getText();

        List<Muscle> targetMuscles = muscleList.getSelectedValuesList();
        List<Device> devices = deviceList.getSelectedValuesList();

        Exercise exercise = new Exercise(name, difficulty, image, targetMuscles, description, devices);

        exerciseHandler.insertExercise(exercise);

        JOptionPane.showMessageDialog(this, "Übung erfolgreich hinzugefügt!", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
