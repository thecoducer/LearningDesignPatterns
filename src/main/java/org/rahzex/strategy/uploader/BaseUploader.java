package org.rahzex.strategy.uploader;

import lombok.extern.slf4j.Slf4j;
import org.rahzex.strategy.file.File;

public interface BaseUploader {
    void upload(File file);
}
