package cz.mg.refactoringtools.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Required;
import cz.mg.annotations.storage.Value;

public @Entity class Result {
    private int modifiedFileCount;

    public Result() {
    }

    @Required @Value
    public int getModifiedFileCount() {
        return modifiedFileCount;
    }

    public void setModifiedFileCount(int modifiedFileCount) {
        this.modifiedFileCount = modifiedFileCount;
    }
}
