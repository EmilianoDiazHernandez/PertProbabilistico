package com.escom.pertpb;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainWindow extends JFrame {
    private final Project project;

    public MainWindow() {
        super("PERT Probabilístico");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        project = new Project(new HashMap<>());

        JButton btnActivities = new JButton("Actividades");
        JButton btnSeeActivities = new JButton("Ver actividades");
        JButton btnProject = new JButton("Proyecto");

        btnActivities.addActionListener(_ -> new ActivitiesWindow(project));
        btnSeeActivities.addActionListener(_ -> new ActivitiesViewer(project));
        btnProject.addActionListener(e -> new ProjectSummaryWindow(project));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        btnActivities.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSeeActivities.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnProject.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(btnActivities);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(btnSeeActivities);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(btnProject);
        panel.add(Box.createVerticalGlue());

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        setDefaultFont(new Font("Dialog", Font.PLAIN, 18));
        SwingUtilities.invokeLater(MainWindow::new);
    }

    private static void setDefaultFont(Font font) {
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("Button.font", font);
    }
}
