package org.rahzex.strategy;

import org.rahzex.strategy.enums.FileType;
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
