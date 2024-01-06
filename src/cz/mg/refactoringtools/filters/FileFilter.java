package cz.mg.refactoringtools.filters;

import cz.mg.annotations.classes.Component;
import cz.mg.annotations.requirement.Mandatory;

import java.nio.file.Path;

public @Component interface FileFilter {
    boolean matches(@Mandatory Path path);
}
