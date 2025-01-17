package org.rahzex.strategy.test;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.rahzex.strategy.FileUploaderFactory;
import org.rahzex.strategy.enums.FileType;
import org.rahzex.strategy.file.*;
import org.rahzex.strategy.uploader.AnyFileUploader;
import org.rahzex.strategy.uploader.FileUploader;

import java.util.stream.Stream;

public class AnyFileUploaderTest {
    private final LogCaptor fileLogCaptor = LogCaptor.forClass(FileUploader.class);
    private FileUploaderFactory fileUploaderFactory;

    @BeforeEach
    void init() {
        fileUploaderFactory = new FileUploaderFactory();
    }

    private static Stream<Arguments> provideFiles() {
        return Stream.of(
                Arguments.of(CSVFile.builder().type(FileType.CSV).build()),
                Arguments.of(XMLFile.builder().type(FileType.XML).build()),
                Arguments.of(PDFFile.builder().type(FileType.PDF).build()),
                Arguments.of(JSONFile.builder().type(FileType.JSON).build())
        );
    }

    @ParameterizedTest
    @MethodSource("provideFiles")
    public void testAnyFileUploader(File file) {
        AnyFileUploader anyFileUploader = new AnyFileUploader(fileUploaderFactory.getFileUploader(file.getType()));
        anyFileUploader.upload(file);

        Assertions.assertTrue(fileLogCaptor.getInfoLogs().getFirst().contains(file.getType().toString()));
    }
}
