package cz.mg.refactoringtools;

import cz.mg.annotations.classes.Component;
import cz.mg.panel.Panel;
import cz.mg.panel.settings.Alignment;
import cz.mg.refactoringtools.dialogs.MovePackageDialog;
import cz.mg.refactoringtools.event.UserActionListener;

import javax.swing.*;

public @Component class MainWindow extends JFrame {
    private static final Version VERSION = new Version(0, 1, 0);
    private static final String NAME = "JMgRefactoringTools";
    private static final String TITLE = NAME + " " + VERSION;
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;
    private static final int MARGIN = 8;
    private static final int PADDING = 8;

    public MainWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITLE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);

        Panel panel = new Panel(MARGIN, PADDING, Alignment.MIDDLE);

        JButton movePackageButton = new JButton("Move package");
        movePackageButton.addActionListener(new UserActionListener(this::movePackage));
        panel.addVertical(movePackageButton);

        panel.rebuild();

        getContentPane().add(panel);
    }

    private void movePackage() {
        MovePackageDialog.show(this);
    }

    public static void main(String[] args) {
        new MainWindow().setVisible(true);
    }
}
