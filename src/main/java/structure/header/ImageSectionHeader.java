package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageSectionHeader {
    char[] Name;
    int misc;
    int virtualAddress;
    int sizeOfRawData;
    int pointerToRawData;
    int pointerToRelocations;
    int pointerToLineNumbers;
    short numberOfRelocations;
    short numberOfLineNumbers;
    short characteristics;
}
