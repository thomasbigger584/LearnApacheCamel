package com.twb.camel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

@Slf4j
@Getter
@AllArgsConstructor
public enum FileType {
    NOT_SUPPORTED(""), CSV("csv"), JSON("json"), XML("xml");

    private final String fileType;

    @Override
    public String toString() {
        return "FileType{" +
                "fileType='" + fileType + '\'' +
                '}';
    }

    public static FileType getFileType(File file) {
        if (file == null) {
            return NOT_SUPPORTED;
        }
        String extension = FilenameUtils.getExtension(file.getName());
        switch (extension) {
            case "csv": {
                return CSV;
            }
            case "json": {
                return JSON;
            }
            case "xml": {
                return XML;
            }
            default: {
                return NOT_SUPPORTED;
            }
        }
    }
}