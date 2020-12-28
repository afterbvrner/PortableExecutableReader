package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import unmarshaller.Len;

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
    @Len(2)
    byte[] characteristics;
}
