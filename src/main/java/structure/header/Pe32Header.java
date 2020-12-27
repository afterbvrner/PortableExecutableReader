package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import unmarshaller.Len;

import java.util.Arrays;

@Data
@NoArgsConstructor
// Size:
public class Pe32Header {
    @Len(2) char[] signature1;
    short signature2;
}
