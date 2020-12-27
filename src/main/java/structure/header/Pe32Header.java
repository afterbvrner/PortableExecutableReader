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

    @Override
    public String toString() {
        return "\nPe32Header{" +
                "signature1=" + Arrays.toString(signature1) +
                ", signature2=" + signature2 +
                '}';
    }
}
