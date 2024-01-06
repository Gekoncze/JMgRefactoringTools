package cz.mg.refactoringtools;

import cz.mg.annotations.classes.Test;
import cz.mg.refactoringtools.filters.JavaFileFilterTest;
import cz.mg.refactoringtools.services.DirectoryReaderTest;

public @Test class AllTests {
    public static void main(String[] args) {
        // cz.mg.refactoringtools.filters
        JavaFileFilterTest.main(args);

        // cz.mg.refactoringtools.services
        DirectoryReaderTest.main(args);
    }
}
