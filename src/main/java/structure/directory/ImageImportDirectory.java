package structure.directory;

import lombok.Data;
import lombok.NoArgsConstructor;
import structure.thunk.ImageThunkData32;

@Data
@NoArgsConstructor
public class ImageImportDirectory {
    int characteristics;
    int timeDateStamp;
    int forwarderChain;
    int name;
    ImageThunkData32 firstThunk;
}
