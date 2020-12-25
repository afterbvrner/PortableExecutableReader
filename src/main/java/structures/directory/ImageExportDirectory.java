package structures.directory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageExportDirectory {
    int characteristics;
    int timeDateStamp;
    short majorVersion;
    short minorVersion;
    int name;
    int Base;
    int numberOfFunctions;
    int numberOfNames;
    int addressOfFunctions;
    int addressOfNames;
    int addressOfNameOrdinals;
}
