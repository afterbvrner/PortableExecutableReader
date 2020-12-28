package structure.header;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;
import lombok.NoArgsConstructor;
import unmarshaller.Len;

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
    int characteristics;

    @JsonGetter
    public String getName() {
        return String.valueOf(name)
                .replaceAll("\\u0000", "");
    }
}
