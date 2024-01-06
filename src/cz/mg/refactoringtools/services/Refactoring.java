package cz.mg.refactoringtools.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.file.page.Page;
import cz.mg.file.page.PageReader;
import cz.mg.file.page.PageWriter;
import cz.mg.refactoringtools.actions.FileAction;
import cz.mg.refactoringtools.filters.FileFilter;

import java.nio.file.Path;

public @Service class Refactoring {
    private static volatile @Service Refactoring instance;

    public static @Service Refactoring getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new Refactoring();
                    instance.directoryReader = DirectoryReader.getInstance();
                    instance.pageReader = PageReader.getInstance();
                    instance.pageWriter = PageWriter.getInstance();
                }
            }
        }
        return instance;
    }

    private @Service DirectoryReader directoryReader;
    private @Service PageReader pageReader;
    private @Service PageWriter pageWriter;

    private Refactoring() {
    }

    /**
     * Runs given action for all files contained in given project directory.
     */
    public void refactor(@Mandatory Path project, @Mandatory FileFilter filter, @Mandatory FileAction action) {
        for (Path path : directoryReader.read(project, filter)) {
            Page page = pageReader.read(path);
            boolean modified = action.run(page);
            if (modified) {
                pageWriter.write(page);
            }
        }
    }
}
