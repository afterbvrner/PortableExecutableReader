package controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import structure.directory.ImageExportDirectory;
import structure.directory.ImageImportDirectory;
import structure.header.*;
import unmarshaller.GetLen;
import unmarshaller.PositionAssert;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PortableExecutable {
    DOSHeader dosHeader;
    @GetLen("getStubSize")
    char[] stub;
    @PositionAssert("assertPe32HeaderPosition")
    Pe32Header pe32Header;
    ImageFileHeader fileHeader;
    ImageOptionalHeader optionalHeader;
    @GetLen("getSectionsAmount")
    ImageSectionHeader[] sectionHeader;
    ImageExportDirectory exportDirectory;
    ImageImportDirectory importDirectory;

    @Override
    public String toString() {
        return "PortableExecutable{" +
                "dosHeader=" + dosHeader +
                ", \nstub=" + String.valueOf(stub) +
                ", \npe32Header=" + pe32Header +
                ", \nfileHeader=" + fileHeader +
                ", \noptionalHeader=" + optionalHeader +
                ", \nsectionHeader=\n" + Arrays.stream(sectionHeader)
                                            .map(Object::toString)
                                            .collect(Collectors.joining("\n")) +
                ", \nexportDirectory=" + exportDirectory +
                ", \nimportDirectory=" + importDirectory +
                '}';
    }

    public int getStubSize() {
        return dosHeader.getAddressOfNewExeHeader() - 64;
    }

    public int getSectionsAmount() {
        return fileHeader.getNumberOfSections();
    }

    public boolean assertPe32HeaderPosition(long position) {
        return position == 64 + stub.length;
    }
}