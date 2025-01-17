package org.rahzex.strategy;

import org.rahzex.strategy.enums.FileType;
import org.rahzex.strategy.uploader.*;

public class FileUploaderFactory {

    public FileUploader getFileUploader(FileType fileType) {
        return switch (fileType) {
            case XML -> new XMLFileUploader();
            case CSV -> new CSVFileUploader();
            case JSON -> new JSONFileUploader();
            case PDF -> new PDFFileUploader();
        };
    }
}
