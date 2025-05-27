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

        StringBuilder sb = new StringBuilder();
        sb.append("Resumen del Proyecto:\n\n");
        sb.append(String.format("Tiempo Total Esperado: %.2f\n", project.getTotalExpectedTime()));
        sb.append(String.format("Varianza Total: %.2f\n", project.getTotalVariance()));
        sb.append(String.format("Desviación Estándar Total: %.2f\n", project.getTotalDeviation()));

        textArea.setText(sb.toString());
        add(new JScrollPane(textArea));

        setVisible(true);
    }
}
