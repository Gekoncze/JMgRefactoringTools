package cz.mg.refactoringtools.actions;

import cz.mg.annotations.classes.Component;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.ListItem;
import cz.mg.file.page.Page;

public @Component class MoveDirectoryAction implements FileAction {
    private final @Mandatory String sourcePackage;
    private final @Mandatory String targetPackage;

    public MoveDirectoryAction(String sourcePackage, String targetPackage) {
        this.sourcePackage = sourcePackage;
        this.targetPackage = targetPackage;
    }

    @Override
    public boolean run(Page page) {
        boolean modified = false;

        for (ListItem<String> item = page.getLines().getFirstItem(); item != null; item = item.getNextItem()) {
            String line = item.get();
            String leadingPart = "import " + sourcePackage;
            if (line.startsWith(leadingPart)) {
                String trailingPart = line.substring(leadingPart.length());
                String modifiedLine = "import " + targetPackage + trailingPart;
                item.set(modifiedLine);
                modified = true;
            }
        }

        return modified;
    }
}
