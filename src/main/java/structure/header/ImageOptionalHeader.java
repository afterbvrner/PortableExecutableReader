package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import structure.directory.ImageDataDirectory;
import unmarshaller.Len;

@Data
@NoArgsConstructor
public class ImageOptionalHeader {
    short magic;
    char majorLinkerVersion;
    char minorLinkerVersion;
    int sizeOfCode;
    int sizeOfInitializedData;
    int sizeOfUninitializedData;
    int addressOfEntryPoint;
    int baseOfCode;
    int baseOfData;
    int imageBase;
    int sectionAlignment;
    int fileAlignment;
    short majorOperatingSystemVersion;
    short minorOperatingSystemVersion;
    short majorImageVersion;
    short minorImageVersion;
    short majorSubsystemVersion;
    short minorSubsystemVersion;
    int min32VersionValue;
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
    @Len(16) ImageDataDirectory[] dataDirectory;
}
