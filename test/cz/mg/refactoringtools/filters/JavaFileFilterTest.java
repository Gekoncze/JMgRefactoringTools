package cz.mg.refactoringtools.filters;

import cz.mg.annotations.classes.Test;
import cz.mg.test.Assert;

import java.nio.file.Path;

public @Test class JavaFileFilterTest {
    public static void main(String[] args) {
        System.out.print("Running " + JavaFileFilterTest.class.getSimpleName() + " ... ");

        JavaFileFilterTest test = new JavaFileFilterTest();
        test.testFilter();

        System.out.println("OK");
    }

    private void testFilter() {
        JavaFileFilter filter = new JavaFileFilter();
        Assert.assertEquals(true, filter.matches(Path.of("main.java")));
        Assert.assertEquals(true, filter.matches(Path.of("/main.java")));
        Assert.assertEquals(true, filter.matches(Path.of("src/main.java")));
        Assert.assertEquals(true, filter.matches(Path.of("/src/main.java")));
        Assert.assertEquals(true, filter.matches(Path.of(".java")));
        Assert.assertEquals(false, filter.matches(Path.of("java")));
        Assert.assertEquals(false, filter.matches(Path.of("/java")));
        Assert.assertEquals(false, filter.matches(Path.of("src/java")));
        Assert.assertEquals(false, filter.matches(Path.of("/src/java")));
        Assert.assertEquals(false, filter.matches(Path.of("main.c")));
        Assert.assertEquals(false, filter.matches(Path.of("/main.c")));
        Assert.assertEquals(false, filter.matches(Path.of("src/main.c")));
        Assert.assertEquals(false, filter.matches(Path.of("/src/main.c")));
        Assert.assertEquals(false, filter.matches(Path.of(".c")));
    }
}
