package cz.mg.refactoringtools.actions;

import cz.mg.annotations.classes.Component;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.ListItem;
import cz.mg.file.page.Page;

public @Component class RenamePackageAction implements FileAction {
    private final @Mandatory String oldName;
    private final @Mandatory String newName;

    public RenamePackageAction(String oldName, String newName) {
        this.oldName = oldName;
        this.newName = newName;
    }

    @Override
    public boolean run(Page page) {
        boolean modified = false;

        for (ListItem<String> item = page.getLines().getFirstItem(); item != null; item = item.getNextItem()) {
            String line = item.get();
            String leadingPart = "import " + oldName;
            if (line.startsWith(leadingPart)) {
                String trailingPart = line.substring(leadingPart.length());
                String modifiedLine = "import " + newName + trailingPart;
                item.set(modifiedLine);
                modified = true;
            }
        }

        return modified;
    }
}
