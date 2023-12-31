package cz.mg.refactoringtools.dialogs;

import cz.mg.annotations.classes.Component;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.panel.Panel;
import cz.mg.panel.settings.Alignment;
import cz.mg.panel.settings.Fill;
import cz.mg.refactoringtools.event.UserActionListener;

import javax.swing.*;

public @Component class MovePackageDialog extends JDialog {
    private static final String TITLE = "Move package";
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 240;
    private static final int MARGIN = 8;
    private static final int PADDING = 8;

    public MovePackageDialog(@Mandatory JFrame window) {
        super(window, true);
        setTitle(TITLE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);

        Panel panel = new Panel(MARGIN, PADDING, Alignment.TOP_LEFT);

        JLabel projectLabel = new JLabel("Source directory");
        JTextField projectField = new JTextField();
        JLabel fromLabel = new JLabel("From");
        JTextField fromField = new JTextField();
        JLabel toLabel = new JLabel("To");
        JTextField toField = new JTextField();
        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(new UserActionListener(this::move));

        panel.addVertical(projectLabel);
        panel.addVertical(projectField, 1, 0);
        panel.addVertical(fromLabel);
        panel.addVertical(fromField, 1, 0);
        panel.addVertical(toLabel);
        panel.addVertical(toField, 1, 0);
        panel.addVertical(moveButton, 0, 0, Alignment.RIGHT, Fill.NONE);

        panel.rebuild();

        getContentPane().add(panel);
    }

    private void move() {
        // TODO
    }

    public static void show(@Mandatory JFrame window) {
        new MovePackageDialog(window).setVisible(true);
    }
}
