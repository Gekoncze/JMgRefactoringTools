package cz.mg.refactoringtools.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.components.Capacity;
import cz.mg.collections.list.List;
import cz.mg.collections.set.Set;
import cz.mg.refactoringtools.filters.JavaFileFilter;
import cz.mg.test.Assert;

import java.nio.file.Path;

public @Test class DirectoryReaderTest {
    private static volatile @Test DirectoryReaderTest instance;

    public static @Test DirectoryReaderTest getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new DirectoryReaderTest();
                    instance.reader = DirectoryReader.getInstance();
                }
            }
        }
        return instance;
    }

    private @Service DirectoryReader reader;

    private DirectoryReaderTest() {
    }

    public static void main(String[] args) {
        System.out.print("Running " + DirectoryReaderTest.class.getSimpleName() + " ... ");

        DirectoryReaderTest test = getInstance();
        test.testRead();

        System.out.println("OK");
    }

    private void testRead() {
        Path sampleDirectoryPath = Path.of("test/cz/mg/refactoringtools/sample");
        Set<String> actualNames = collectNames(reader.read(sampleDirectoryPath, new JavaFileFilter()));

        Assert.assertEquals(true, actualNames.contains("main.java"));
        Assert.assertEquals(true, actualNames.contains("outer.java"));
        Assert.assertEquals(true, actualNames.contains("inner.java"));

        Assert.assertEquals(false, actualNames.contains("main"));
        Assert.assertEquals(false, actualNames.contains("main.c"));
        Assert.assertEquals(false, actualNames.contains("inner"));
        Assert.assertEquals(false, actualNames.contains("inner.c"));
    }

    private @Mandatory Set<String> collectNames(@Mandatory List<Path> paths) {
        Set<String> names = new Set<>(new Capacity(20));
        for (Path path : paths) {
            names.set(path.getFileName().toString());
        }
        return names;
    }
}
