package structure.directory;

import lombok.Data;
import lombok.NoArgsConstructor;
import structure.thunk.ImageThunkData32;
import unmarshaller.Len;

@Data
@NoArgsConstructor
public class ImageImportDirectory {
    int characteristics;
    int timeDateStamp;
    int forwarderChain;
    @Len(4)
    char[] name;
    ImageThunkData32 firstThunk;
}
