package com.escom.pertpb;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ProjectSummaryWindow extends JFrame {

    ProjectSummaryWindow(Project project) {
        super("Resumen del Proyecto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBorder(new EmptyBorder(20, 20, 20, 20));

        String sb = "Resumen del Proyecto:\n\n" +
                String.format("Tiempo Total Esperado: %.2f\n", project.getTotalExpectedTime()) +
                String.format("Varianza Total: %.2f\n", project.getTotalVariance()) +
                String.format("Desviación Estándar Total: %.2f\n", project.getTotalDeviation());

        textArea.setText(sb);
        add(new JScrollPane(textArea));

        setVisible(true);
    }
}
