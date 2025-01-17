package org.rahzex.strategy.uploader;

import lombok.extern.slf4j.Slf4j;
import org.rahzex.strategy.file.File;

@Slf4j
public abstract class FileUploader {
    public void upload(File file) {
        log.info("Uploading {} file...", file.getType());
    }
}
