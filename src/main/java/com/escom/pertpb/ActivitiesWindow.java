package com.escom.pertpb;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ActivitiesWindow extends JFrame {

    public ActivitiesWindow(Project project) {
        super("Actividad");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel activityLabel = new JLabel("Actividad:");
        JTextField activityField = new JTextField();

        JLabel oLabel = new JLabel("Optimista (O):");
        JTextField oField = new JTextField();

        JLabel mLabel = new JLabel("Más probable (M):");
        JTextField mField = new JTextField();

        JLabel pLabel = new JLabel("Pesimista (P):");
        JTextField pField = new JTextField();

        JButton calculateButton = new JButton("Agregar actividad");

        JLabel resultLabel = new JLabel("Tiempo esperado (Te): ");
        JLabel varianceLabel = new JLabel("Varianza: ");

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(activityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(activityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(oLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(oField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(mLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(mField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(pLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(pField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(calculateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(resultLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(varianceLabel, gbc);


        calculateButton.addActionListener(_ -> {
            String key = activityField.getText().trim();
            try {
                if (key.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre de la actividad no puede estar vacío.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double o = Double.parseDouble(oField.getText());
                double m = Double.parseDouble(mField.getText());
                double p = Double.parseDouble(pField.getText());

                if (isValidInput(o, m, p, this)) {

                    int option = JOptionPane.YES_OPTION;
                    if (project.getActivities().containsKey(key)) {
                        option = JOptionPane.showConfirmDialog(this, "La actividad ya existe. ¿Desea reemplazarla?", "Input Error", JOptionPane.YES_NO_OPTION);
                    }

                    if (option == JOptionPane.NO_OPTION) {
                        resultLabel.setText("Tiempo esperado (Te): " + project.getActivities().get(key).getExpectedTime());
                        varianceLabel.setText("Varianza: " + project.getActivities().get(key).getVariance());
                        return;
                    }

                    Activity activity = new Activity(o, m, p);
                    project.getActivities().put(activityField.getText().trim(), activity);

                    resultLabel.setText("Tiempo esperado (Te): " + activity.getExpectedTime());
                    varianceLabel.setText("Varianza: " + activity.getVariance());
                }

                oField.setText("");
                mField.setText("");
                pField.setText("");
                activityField.setText("");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static boolean isValidInput(double o, double m, double p, JFrame frame) {
        if (o < 0 || m < 0 || p < 0) {
            showError("Los valores no pueden ser negativos!", frame);
            return false;
        }
        if (!(o <= m && m <= p)) {
            showError("Debe cumplirse: O ≤ M ≤ P", frame);
            return false;
        }
        return true;
    }

    private static void showError(String message, JFrame frame) {
        JOptionPane.showMessageDialog(frame, message, "Error de entrada", JOptionPane.ERROR_MESSAGE);
    }
}