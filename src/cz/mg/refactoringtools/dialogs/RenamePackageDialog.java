package cz.mg.refactoringtools.dialogs;

import cz.mg.annotations.classes.Component;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.panel.Panel;
import cz.mg.panel.settings.Alignment;
import cz.mg.panel.settings.Fill;
import cz.mg.refactoringtools.actions.RenamePackageAction;
import cz.mg.refactoringtools.entities.Result;
import cz.mg.refactoringtools.event.UserActionListener;
import cz.mg.refactoringtools.filters.JavaFileFilter;
import cz.mg.refactoringtools.services.Refactoring;

import javax.swing.*;
import java.nio.file.Path;

public @Component class RenamePackageDialog extends JDialog {
    private static final String TITLE = "Rename package";
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 240;
    private static final int MARGIN = 8;
    private static final int PADDING = 8;

    private final JTextField projectField;
    private final JTextField fromField;
    private final JTextField toField;

    public RenamePackageDialog(@Mandatory JFrame window) {
        super(window, true);
        setTitle(TITLE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);

        Panel panel = new Panel(MARGIN, PADDING, Alignment.TOP_LEFT);

        JLabel projectLabel = new JLabel("Project directory");
        projectField = new JTextField();
        JLabel fromLabel = new JLabel("From");
        fromField = new JTextField();
        JLabel toLabel = new JLabel("To");
        toField = new JTextField();
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
        showResult(
            Refactoring.getInstance().refactor(
                Path.of(projectField.getText()),
                new JavaFileFilter(),
                new RenamePackageAction(
                    fromField.getText(),
                    toField.getText()
                )
            )
        );
    }

    private void showResult(@Mandatory Result result) {
        String message = result.getModifiedFileCount() > 0
            ? "Modified " + result.getModifiedFileCount() + " files."
            : "Files are up to date.";

        JOptionPane.showMessageDialog(
            this,
            message,
            "Refactoring",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void show(@Mandatory JFrame window) {
        new RenamePackageDialog(window).setVisible(true);
    }
}
