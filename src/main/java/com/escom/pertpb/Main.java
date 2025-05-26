package com.escom.pertpb;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        setDefaultFont(new Font("Dialog", Font.PLAIN, 18));
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("PERT Probabilistico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel oLabel = new JLabel("Optimista (O):");
        JTextField oField = new JTextField();

        JLabel mLabel = new JLabel("Más probable (M):");
        JTextField mField = new JTextField();

        JLabel pLabel = new JLabel("Pesimista (P):");
        JTextField pField = new JTextField();

        JButton calculateButton = new JButton("Calcular");

        JLabel resultLabel = new JLabel("Tiempo esperado (Te): ");
        JLabel varianceLabel = new JLabel("Varianza: ");

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(oLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(oField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(mLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(mField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(pLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(pField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(calculateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(resultLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(varianceLabel, gbc);


        calculateButton.addActionListener(e -> {
            try {
                double o = Double.parseDouble(oField.getText());
                double m = Double.parseDouble(mField.getText());
                double p = Double.parseDouble(pField.getText());

                if (isValidInput(o, m, p, frame)) {

                    double te = (o + 4 * m + p) / 6;
                    double var = Math.pow((p - o) / 6, 2);

                    resultLabel.setText(String.format("Tiempo esperado (Te): %.2f", te));
                    varianceLabel.setText(String.format("Varianza: %.4f", var));

                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

    private static void setDefaultFont(Font font) {
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("Button.font", font);
    }
}