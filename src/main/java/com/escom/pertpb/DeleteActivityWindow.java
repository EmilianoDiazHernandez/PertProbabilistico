package com.escom.pertpb;

import javax.swing.*;
import java.awt.*;

public class DeleteActivityWindow extends JFrame {

    public DeleteActivityWindow(Project project) {
        super("Eliminar Actividad");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Ingrese el nombre de la actividad:");
        JTextField textField = new JTextField(20);
        JButton searchButton = new JButton("Buscar");
        JButton deleteButton = new JButton("Eliminar");
        deleteButton.setEnabled(false);

        JLabel infoLabel = new JLabel("<html><i>Información de la actividad aparecerá aquí...</i></html>");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(label, gbc);

        gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(textField, gbc);

        gbc.gridx = 1;
        panel.add(searchButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(infoLabel, gbc);

        gbc.gridy = 3;
        panel.add(deleteButton, gbc);

        searchButton.addActionListener(_ -> {
            String activityName = textField.getText().trim();
            Activity activity = project.activities().get(activityName);

            if (activity != null) {
                String info = String.format("""
                        <html>
                        <b>Actividad:</b> %s<br>
                        <b>Tiempo Optimista:</b> %.2f<br>
                        <b>Tiempo Más Probable:</b> %.2f<br>
                        <b>Tiempo Pesimista:</b> %.2f<br>
                        <b>Tiempo Esperado:</b> %.2f<br>
                        <b>Varianza:</b> %.4f
                        </html>
                        """, activityName,
                        activity.optimisticTime(),
                        activity.mostLikelyTime(),
                        activity.pessimisticTime(),
                        activity.getExpectedTime(),
                        activity.getVariance()
                );
                infoLabel.setText(info);
                deleteButton.setEnabled(true);
            } else {
                infoLabel.setText("<html><i>Actividad no encontrada.</i></html>");
                deleteButton.setEnabled(false);
            }
        });

        deleteButton.addActionListener(_ -> {
            String activityName = textField.getText().trim();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que deseas eliminar la actividad \"" + activityName + "\"?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                project.activities().remove(activityName);
                JOptionPane.showMessageDialog(this, "Actividad eliminada exitosamente.");
                textField.setText("");
                infoLabel.setText("<html><i>Información de la actividad aparecerá aquí...</i></html>");
                deleteButton.setEnabled(false);
            }
        });

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
