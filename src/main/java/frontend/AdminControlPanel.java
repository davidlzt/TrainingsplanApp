package frontend;

import backend.database.DatabaseHandler;
import backend.models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminControlPanel {
    private JFrame frame;
    private DatabaseHandler dbHandler;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public AdminControlPanel() {
        dbHandler = new DatabaseHandler();
        frame = new JFrame("Admin Control Panel");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Username", "Email", "Role"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Nutzer laden");
        JButton updateRoleButton = new JButton("Rolle aktualisieren");

        buttonPanel.add(refreshButton);
        buttonPanel.add(updateRoleButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUsers();
            }
        });

        updateRoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserRole();
            }
        });

        loadUsers();

        frame.setVisible(true);
    }

    private void loadUsers() {
        tableModel.setRowCount(0);
        List<User> users = dbHandler.getAllUsers();
        for (User user : users) {
            tableModel.addRow(new Object[]{user.getId(), user.getUsername(), user.getEmail(), user.getRole()});
        }
    }

    private void updateUserRole() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            int userId = (int) tableModel.getValueAt(selectedRow, 0);
            String userName = (String) tableModel.getValueAt(selectedRow, 1);
            String currentRole = (String) tableModel.getValueAt(selectedRow, 8);

            String[] roles = {"admin", "moderator", "user", "vip"};
            String newRole = (String) JOptionPane.showInputDialog(
                    frame,
                    "Wähle eine neue Rolle:",
                    "Rolle aktualisieren",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    roles,
                    currentRole
            );

            if (newRole != null && !newRole.equals(currentRole)) {
                dbHandler.updateUserRole(userName, userId, newRole);
                loadUsers();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Bitte wähle einen Nutzer aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
}
