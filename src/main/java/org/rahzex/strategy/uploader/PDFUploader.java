package org.rahzex.strategy.uploader;

import lombok.extern.slf4j.Slf4j;
import org.rahzex.strategy.file.File;

@Slf4j
public class PDFUploader implements BaseUploader {

    @Override
    public void upload(File file) {
        log.info("Uploading {} file...", file.getType());
    }
}
