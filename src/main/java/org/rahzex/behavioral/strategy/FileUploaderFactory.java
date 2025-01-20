package org.rahzex.behavioral.strategy;

import org.rahzex.behavioral.strategy.enums.FileType;
import org.rahzex.behavioral.strategy.uploader.BaseUploader;
import org.rahzex.behavioral.strategy.uploader.CSVUploader;
import org.rahzex.behavioral.strategy.uploader.JSONUploader;
import org.rahzex.behavioral.strategy.uploader.PDFUploader;
import org.rahzex.behavioral.strategy.uploader.XMLUploader;
import org.rahzex.strategy.uploader.*;

public class FileUploaderFactory {

  public BaseUploader getInstance(FileType fileType) {
    return switch (fileType) {
      case XML -> new XMLUploader();
      case CSV -> new CSVUploader();
      case JSON -> new JSONUploader();
      case PDF -> new PDFUploader();
    };
  }
}
