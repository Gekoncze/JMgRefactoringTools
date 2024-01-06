package cz.mg.refactoringtools.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.refactoringtools.exceptions.FileSystemException;
import cz.mg.refactoringtools.filters.FileFilter;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public @Service class DirectoryReader {
    private static volatile @Service DirectoryReader instance;

    public static @Service DirectoryReader getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new DirectoryReader();
                }
            }
        }
        return instance;
    }

    private DirectoryReader() {
    }

    /**
     * Reads list of file paths that are contained in given directory recursively.
     * Files are filtered by given filter.
     * Symbolic links are ignored.
     */
    public @Mandatory List<Path> read(@Mandatory Path directory, @Mandatory FileFilter filter) {
        List<Path> files = new List<>();
        read(directory, filter, files);
        return files;
    }

    private void read(@Mandatory Path directory, @Mandatory FileFilter filter, @Mandatory List<Path> files) {
        try (DirectoryStream<Path> nodes = Files.newDirectoryStream(directory)) {
            for (Path node : nodes) {
                if (!Files.isSymbolicLink(node)) {
                    if (Files.isDirectory(node)) {
                        read(node, filter, files);
                    } else if (filter.matches(node)) {
                        files.addLast(node);
                    }
                }
            }
        } catch (IOException e) {
            throw new FileSystemException("Could not read directory " + directory.toAbsolutePath() + ".", e);
        }
    }
}
