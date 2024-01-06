package cz.mg.refactoringtools.filters;

import cz.mg.annotations.classes.Component;

import java.nio.file.Path;

public @Component class JavaFileFilter implements FileFilter {
    @Override
    public boolean matches(Path path) {
        return path.endsWith(".java");
    }
}
