package com.baeldung.lju;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class StaticCodeAnalysisTest {

    private static final int MAX_FILE_LENGTH = 300;

    @TestFactory
    Stream<DynamicTest> givenTheServicePackage_whenVerifyingAllFileLengths_thenTheyAreNotTooLong() {
        File root = new File("src/main/java", "com/baeldung/lju/service");

        return Stream.of(root.listFiles())
            .filter(file -> file.getName().endsWith(".java"))
            .map(javaFile -> verifyFileLength(javaFile));
    }

    //@formatter:off
    static DynamicTest verifyFileLength(File javaFile) {
        return DynamicTest.dynamicTest(
            String.format("%s < %s lines", javaFile.getName(), MAX_FILE_LENGTH),
            () -> {
                long fileLength = Files.lines(javaFile.toPath()).count();
                assertTrue(fileLength < MAX_FILE_LENGTH);
            }
        );
    }
    //@formatter:on

    @TestFactory
    Stream<DynamicTest> givenSourceFiles_whenVerifyingAllFileLengths_thenTheyAreNotTooLong() {
        return verifyAllFromFolder(
            new File("src/main/java", "com/baeldung/lju"));
    }

    static Stream<DynamicTest> verifyAllFromFolder(File root) {
        if (root.listFiles() == null) {
            return Stream.empty();
        }
        return Stream.of(root.listFiles())
            .flatMap(file -> {
                if (file.getName().endsWith(".java")) {
                    return Stream.of(verifyFileLength(file));
                } else {
                    return Stream.of(file.listFiles())
                        .flatMap(StaticCodeAnalysisTest::verifyAllFromFolder);
                }
            });
    }

    @TestFactory
    Stream<DynamicNode> givenSourceFiles_whenVerifyingAllNestedFiles_thenTheyAreNotTooLong() {
        return verifyAllNestedFolders(new File("src/main/java", "com/baeldung/lju"));
    }

    //@formatter:off
    static Stream<DynamicNode> verifyAllNestedFolders(File root) {
        if (root.listFiles() == null) {
            return Stream.empty();
        }
        return Stream.of(root.listFiles())
          .map(file -> {
              if (file.getName().endsWith(".java")) {
                  return verifyFileLength(file);
              } else {
                  return dynamicContainer(
                      file.getName(),
                      verifyAllNestedFolders(file)
                  );
              }
          });
    }
    //@formatter:on

}
