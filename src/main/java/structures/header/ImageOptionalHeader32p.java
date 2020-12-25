package structures.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import structures.directory.ImageDataDirectory;

@Data
@NoArgsConstructor
public class ImageOptionalHeader32p {
    short magic;
    char majorLinkerVersion;
    char minorLinkerVersion;
    int sizeOfCode;
    int sizeOfInitializedData;
    int sizeOfUninitializedData;
    int addressOfEntryPoint;
    int baseOfCode;
    int imageBase;
    int sectionAlignment;
    int fileAlignment;
    short majorOperatingSystemVersion;
    short minorOperatingSystemVersion;
    short majorImageVersion;
    short minorImageVersion;
    short majorSubsystemVersion;
    short minorSubsystemVersion;
    int win32VersionValue;
    int sizeOfImage;
    int sizeOfHeaders;
    int checkSum;
    short subsystem;
    short dllCharacteristics;
    int sizeOfStackReserve;
    int sizeOfStackCommit;
    int sizeOfHeapReserve;
    int sizeOfHeapCommit;
    int loaderFlags;
    int numberOfRvaAndSizes;
    ImageDataDirectory[] dataDirectory;
}
