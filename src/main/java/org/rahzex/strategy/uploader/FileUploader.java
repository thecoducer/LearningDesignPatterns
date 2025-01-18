package org.rahzex.strategy.uploader;

import org.rahzex.strategy.FileUploaderFactory;
import org.rahzex.strategy.file.File;

public class FileUploader {

    public static void upload(File file){
        FileUploaderFactory factory = new FileUploaderFactory();
        factory.getInstance(file.getType()).upload(file);
    }
}
