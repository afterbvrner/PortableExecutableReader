package structure.directory;

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
    int base;
    int numberOfFunctions;
    int numberOfNames;
    int addressOfFunctions;
    int addressOfNames;
    int addressOfNameOrdinals;

    @Override
    public String toString() {
        return "\nImageExportDirectory{" +
                "characteristics=" + characteristics +
                ", timeDateStamp=" + timeDateStamp +
                ", majorVersion=" + majorVersion +
                ", minorVersion=" + minorVersion +
                ", name=" + name +
                ", base=" + base +
                ", numberOfFunctions=" + numberOfFunctions +
                ", numberOfNames=" + numberOfNames +
                ", addressOfFunctions=" + addressOfFunctions +
                ", addressOfNames=" + addressOfNames +
                ", addressOfNameOrdinals=" + addressOfNameOrdinals +
                '}';
    }
}
