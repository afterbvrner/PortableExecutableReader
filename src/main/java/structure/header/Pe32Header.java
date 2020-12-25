package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pe32Header {
    char[] signature1;
    short signature2;
    ImageFileHeader fileHeader;
    ImageOptionalHeader optionalHeader;
}
