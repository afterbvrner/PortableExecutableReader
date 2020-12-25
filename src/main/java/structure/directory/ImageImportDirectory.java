package structure.directory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageImportDirectory {
    int characteristics;
    int timeDateStamp;
    int forwarderChain;
    int name;
    int firstThunk;
}
