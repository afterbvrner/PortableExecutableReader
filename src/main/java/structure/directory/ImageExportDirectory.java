package structure.directory;

import lombok.Data;
import lombok.NoArgsConstructor;
import structure.thunk.ImageThunkData32;

@Data
@NoArgsConstructor
public class ImageExportDirectory {
    ImageThunkData32 characteristics;
    int timeDateStamp;
    short majorVersion;
    short minorVersion;
    int name;
    int base;
    int numberOfFunctions;
    int numberOfNames;
    int addressOfFunctions;
    int addressOfNames;
    int addressOfNameOrdinals;
}
