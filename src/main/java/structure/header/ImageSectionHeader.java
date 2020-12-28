package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import unmarshaller.Len;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class ImageSectionHeader {
    @Len(8) char[] name;
    int misc;
    int virtualAddress;
    int sizeOfRawData;
    int pointerToRawData;
    int pointerToRelocations;
    int pointerToLineNumbers;
    short numberOfRelocations;
    short numberOfLineNumbers;
    @Len(4)
    char[] characteristics;
}
