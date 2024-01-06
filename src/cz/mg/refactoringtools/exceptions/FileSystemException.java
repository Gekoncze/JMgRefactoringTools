package cz.mg.refactoringtools.exceptions;

import cz.mg.annotations.classes.Error;
import cz.mg.annotations.requirement.Mandatory;

public @Error class FileSystemException extends RuntimeException {
    public FileSystemException(@Mandatory String message, @Mandatory Throwable cause) {
        super(message, cause);
    }
}
