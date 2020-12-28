package structure.directory;

import lombok.Data;
import lombok.NoArgsConstructor;
import structure.thunk.ImageThunkData32;
import unmarshaller.Len;

@Data
@NoArgsConstructor
public class ImageImportDirectory {
    ImageThunkData32 originalFirstThunk;
    int timeDateStamp;
    int forwarderChain;
    @Len(4)
    char[] name;
    ImageThunkData32 firstThunk;
}
