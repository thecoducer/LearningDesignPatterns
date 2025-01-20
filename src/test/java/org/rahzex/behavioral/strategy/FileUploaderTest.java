package org.rahzex.behavioral.strategy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.rahzex.behavioral.strategy.enums.FileType;
import org.rahzex.behavioral.strategy.file.CSVFile;
import org.rahzex.behavioral.strategy.file.File;
import org.rahzex.behavioral.strategy.file.JSONFile;
import org.rahzex.behavioral.strategy.file.PDFFile;
import org.rahzex.behavioral.strategy.file.XMLFile;
import org.rahzex.behavioral.strategy.uploader.CSVUploader;
import org.rahzex.behavioral.strategy.uploader.FileUploader;
import org.rahzex.behavioral.strategy.uploader.JSONUploader;
import org.rahzex.behavioral.strategy.uploader.PDFUploader;
import org.rahzex.behavioral.strategy.uploader.XMLUploader;

public class FileUploaderTest {
  private final LogCaptor XMLLogCaptor = LogCaptor.forClass(XMLUploader.class);
  private final LogCaptor PDFLogCaptor = LogCaptor.forClass(PDFUploader.class);
  private final LogCaptor JSONLogCaptor = LogCaptor.forClass(JSONUploader.class);
  private final LogCaptor CSVLogCaptor = LogCaptor.forClass(CSVUploader.class);

  private static Stream<Arguments> provideFiles() {
    return Stream.of(
        Arguments.of(CSVFile.builder().type(FileType.CSV).build()),
        Arguments.of(XMLFile.builder().type(FileType.XML).build()),
        Arguments.of(PDFFile.builder().type(FileType.PDF).build()),
        Arguments.of(JSONFile.builder().type(FileType.JSON).build()));
  }

  private LogCaptor getLogCaptor(FileType type) {
    return switch (type) {
      case XML -> XMLLogCaptor;
      case CSV -> CSVLogCaptor;
      case PDF -> PDFLogCaptor;
      case JSON -> JSONLogCaptor;
    };
  }

  @ParameterizedTest
  @MethodSource("provideFiles")
  public void testAnyFileUploader(File file) {
    FileUploader.upload(file);
    assertTrue(
        getLogCaptor(file.getType()).getInfoLogs().getFirst().contains(file.getType().toString()));
  }
}
