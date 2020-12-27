package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import unmarshaller.Len;

@Data
@NoArgsConstructor
public class Pe32PlusHeader {
    @Len(2) char[] signature1;
    short signature2;
    ImageFileHeader fileHeader;
    ImageOptionalHeader optionalHeader;
}
