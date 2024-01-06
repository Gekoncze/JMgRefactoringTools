package cz.mg.refactoringtools.actions;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.file.page.Page;
import cz.mg.test.Assert;

import java.nio.file.Path;

public @Test class RenamePackageActionTest {
    public static void main(String[] args) {
        System.out.print("Running " + RenamePackageActionTest.class.getSimpleName() + " ... ");

        RenamePackageActionTest test = new RenamePackageActionTest();
        test.testRun();

        System.out.println("OK");
    }

    private void testRun() {
        Assert.assertThatCollections(
            new List<>(
                "package cz.mg.foobar",
                "",
                "import com.example.foo",
                "import eu.mg.collections.List",
                "import eu.mg.collections.Array",
                "import eu.mg.foobar.Foo",
                "",
                "public class FooBar {",
                "}"
            ),
            testRun(new RenamePackageAction(
                "cz",
                "eu"
            ))
        ).areEqual();

        Assert.assertThatCollections(
            new List<>(
                "package cz.mg.foobar",
                "",
                "import com.example.foo",
                "import cz.mg.data.List",
                "import cz.mg.data.Array",
                "import cz.mg.foobar.Foo",
                "",
                "public class FooBar {",
                "}"
            ),
            testRun(new RenamePackageAction(
                "cz.mg.collections",
                "cz.mg.data"
            ))
        ).areEqual();

        Assert.assertThatCollections(
            new List<>(
                "package cz.mg.foobar",
                "",
                "import com.example2.foo",
                "import cz.mg.collections.List",
                "import cz.mg.collections.Array",
                "import cz.mg.foobar.Foo",
                "",
                "public class FooBar {",
                "}"
            ),
            testRun(new RenamePackageAction(
                "com.example",
                "com.example2"
            ))
        ).areEqual();
    }

    private @Mandatory List<String> testRun(@Mandatory FileAction action) {
        Page page = createTestPage();
        action.run(page);
        return page.getLines();
    }

    private @Mandatory Page createTestPage() {
        return new Page(Path.of("test.java"), new List<>(
            "package cz.mg.foobar",
            "",
            "import com.example.foo",
            "import cz.mg.collections.List",
            "import cz.mg.collections.Array",
            "import cz.mg.foobar.Foo",
            "",
            "public class FooBar {",
            "}"
        ));
    }
}
