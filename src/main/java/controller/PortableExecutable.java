package controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import structure.directory.ImageExportDirectory;
import structure.directory.ImageImportDirectory;
import structure.header.*;
import unmarshaller.GetLen;

@Data
@NoArgsConstructor
public class PortableExecutable {
    DOSHeader dosHeader;
    @GetLen("getStubSize")
    char[] stub;
    Pe32Header pe32Header;
    ImageFileHeader fileHeader;
    ImageOptionalHeader optionalHeader;
    @GetLen("getSectionsAmount")
    ImageSectionHeader sectionHeader;
    ImageExportDirectory exportDirectory;
    ImageImportDirectory importDirectory;

    @Override
    public String toString() {
        return "PortableExecutable{" +
                "dosHeader=" + dosHeader +
                ", stub=" + String.valueOf(stub) +
                ", pe32Header=" + pe32Header +
                ", fileHeader=" + fileHeader +
                ", optionalHeader=" + optionalHeader +
                ", sectionHeader=" + sectionHeader +
                ", exportDirectory=" + exportDirectory +
                ", importDirectory=" + importDirectory +
                '}';
    }

    public int getStubSize() {
        return dosHeader.getAddressOfNewExeHeader() - 64;
    }

    public int getSectionsAmount() {
        return fileHeader.getNumberOfSections();
    }
}