package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// Size: 20
public class ImageFileHeader {
    short machine;
    short numberOfSections;
    int timeDateStamp;
    int pointerToSymbolTable;
    int numberOfSymbols;
    short sizeOfOptionalHeader;
    short characteristics;
}
