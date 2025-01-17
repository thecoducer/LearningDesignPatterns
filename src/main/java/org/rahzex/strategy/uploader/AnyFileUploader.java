package org.rahzex.strategy.uploader;

import org.rahzex.strategy.file.File;

public class AnyFileUploader {
    private FileUploader fileUploader;

    public AnyFileUploader(FileUploader fileUploader){
      this.fileUploader = fileUploader;
    }

    public void upload(File file){
        fileUploader.upload(file);
    }
}
