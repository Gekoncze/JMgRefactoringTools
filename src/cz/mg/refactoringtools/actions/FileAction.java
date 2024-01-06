package cz.mg.refactoringtools.actions;

import cz.mg.annotations.classes.Component;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.file.page.Page;

public @Component interface FileAction {
    /**
     * Runs an action to refactor given file.
     * @return true when file was changed
     */
    boolean run(@Mandatory Page page);
}
