package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import structure.directory.ImageDataDirectory;
import unmarshaller.Len;

import java.util.Arrays;

@Data
@NoArgsConstructor
// Size: 108 + 128 = 236
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
    @Len(16) ImageDataDirectory[] dataDirectory;

    @Override
    public String toString() {
        return "\nImageOptionalHeader{" +
                "magic=" + magic +
                ", majorLinkerVersion=" + majorLinkerVersion +
                ", minorLinkerVersion=" + minorLinkerVersion +
                ", sizeOfCode=" + sizeOfCode +
                ", sizeOfInitializedData=" + sizeOfInitializedData +
                ", sizeOfUninitializedData=" + sizeOfUninitializedData +
                ", addressOfEntryPoint=" + addressOfEntryPoint +
                ", baseOfCode=" + baseOfCode +
                ", baseOfData=" + baseOfData +
                ", imageBase=" + imageBase +
                ", sectionAlignment=" + sectionAlignment +
                ", fileAlignment=" + fileAlignment +
                ", majorOperatingSystemVersion=" + majorOperatingSystemVersion +
                ", minorOperatingSystemVersion=" + minorOperatingSystemVersion +
                ", majorImageVersion=" + majorImageVersion +
                ", minorImageVersion=" + minorImageVersion +
                ", majorSubsystemVersion=" + majorSubsystemVersion +
                ", minorSubsystemVersion=" + minorSubsystemVersion +
                ", win32VersionValue=" + win32VersionValue +
                ", sizeOfImage=" + sizeOfImage +
                ", sizeOfHeaders=" + sizeOfHeaders +
                ", checkSum=" + checkSum +
                ", subsystem=" + subsystem +
                ", dllCharacteristics=" + dllCharacteristics +
                ", sizeOfStackReserve=" + sizeOfStackReserve +
                ", sizeOfStackCommit=" + sizeOfStackCommit +
                ", sizeOfHeapReserve=" + sizeOfHeapReserve +
                ", sizeOfHeapCommit=" + sizeOfHeapCommit +
                ", loaderFlags=" + loaderFlags +
                ", numberOfRvaAndSizes=" + numberOfRvaAndSizes +
                ", dataDirectory=" + Arrays.toString(dataDirectory) +
                '}';
    }
}
