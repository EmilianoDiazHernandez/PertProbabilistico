package com.escom.pertpb;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ActivitiesViewer extends JFrame {

    ActivitiesViewer(Project project) {
        super("Actividades del Proyecto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBorder(new EmptyBorder(20, 20, 20, 20));

        StringBuilder sb = new StringBuilder();
        sb.append("Actividades del Proyecto:\n\n");

        for (String activityName : project.getActivities().keySet()) {
            Activity activity = project.getActivities().get(activityName);
            sb.append(String.format("Actividad: %s\n", activityName));
            sb.append(String.format("Tiempo Optimista (O): %.2f\n", activity.getOptimisticTime()));
            sb.append(String.format("Tiempo Más Probable (M): %.2f\n", activity.getMostLikelyTime()));
            sb.append(String.format("Tiempo Pesimista (P): %.2f\n", activity.getPessimisticTime()));
            sb.append(String.format("Tiempo Esperado (Te): %.2f\n", activity.getExpectedTime()));
            sb.append(String.format("Varianza: %.2f\n", activity.getVariance()));
            sb.append("\n");
        }

        textArea.setText(sb.toString());
        add(new JScrollPane(textArea));

        setVisible(true);
    }
}
