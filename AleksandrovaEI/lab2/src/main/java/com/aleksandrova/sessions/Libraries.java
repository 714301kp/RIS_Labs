package com.zabello.sessions;

import com.zabello.entities.Library;

import javax.ejb.Singleton;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Singleton
public class Libraries {

    static ArrayList<Library> libraries = new ArrayList<>(
            Arrays.asList(
                    new Library(1L, "firstLibrary", "scince", new BigDecimal("100")),
                    new Library(2L, "secondLibrary", "child", new BigDecimal("110")),
                    new Library(3L, "thirdLibrary", "history", new BigDecimal("120")),
                    new Library(4L, "fourthLibrary", "history", new BigDecimal("150"))
                    )
    );

    public List<Library> getLibrariesByCount() {
        libraries.sort(comparing(Library::getReadersCount));
        List<Library> foundLibraries = libraries.stream()
                .skip(1)
                .collect(Collectors.toList());

        return foundLibraries;
    }

    public List<Library> getLibrariesByStatus(String status) {
        List<Library> foundLibraries = new ArrayList<>();
        for (Library libraries : libraries) {
            if (libraries.getStatus().compareTo(status) == 0) {
                foundLibraries.add(libraries);
            }
        }

        return foundLibraries;
    }

}