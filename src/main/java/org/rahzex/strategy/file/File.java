package org.rahzex.strategy.file;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.rahzex.strategy.enums.FileType;

@Getter
@SuperBuilder(toBuilder = true)
public class File {
    private FileType type;
}
