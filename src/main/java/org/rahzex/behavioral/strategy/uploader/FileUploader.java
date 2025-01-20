package org.rahzex.behavioral.strategy.uploader;

import org.rahzex.behavioral.strategy.FileUploaderFactory;
import org.rahzex.behavioral.strategy.file.File;

public class FileUploader {

  public static void upload(File file) {
    FileUploaderFactory factory = new FileUploaderFactory();
    factory.getInstance(file.getType()).upload(file);
  }
}
