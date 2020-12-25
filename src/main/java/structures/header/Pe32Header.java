package structures.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import structures.header.ImageFileHeader;
import structures.header.ImageOptionalHeader;

@Data
@NoArgsConstructor
public class Pe32Header {
    char[] signature1;
    short signature2;
    ImageFileHeader fileHeader;
    ImageOptionalHeader optionalHeader;
}
