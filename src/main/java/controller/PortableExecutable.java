package controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import structure.directory.ImageExportDirectory;
import structure.directory.ImageImportDirectory;
import structure.header.*;
import unmarshaller.GetLen;
import unmarshaller.GetPosition;
import unmarshaller.PositionAssert;

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
    @GetPosition("getImportPosition")
    ImageImportDirectory importDirectory;
    @GetPosition("getExportPosition")
    ImageExportDirectory exportDirectory;

    public int getStubSize() {
        return dosHeader.getAddressOfNewExeHeader() - 64;
    }

    public long getImportPosition() {
        return rvaToOff(optionalHeader.getDataDirectory()[1].getVirtualAddress());
    }

    public long getExportPosition() {
        return rvaToOff(optionalHeader.getDataDirectory()[0].getVirtualAddress());
    }

    public int getSectionsAmount() {
        return fileHeader.getNumberOfSections();
    }

    public boolean assertPe32HeaderPosition(long position) {
        return position == 64 + stub.length;
    }

    int rvaToOff(int rva)
    {
        int indexSection = defSection(rva);
        if (indexSection != -1)
            return rva - sectionHeader[indexSection].getVirtualAddress()
                    + sectionHeader[indexSection].getPointerToRawData();
        else
            return 0;
    }

    int defSection(int rva)
    {
        for (int i = 0; i < sectionHeader.length; ++i)
        {
            int start = sectionHeader[i].getVirtualAddress();
            int end = start + alignUp(sectionHeader[i].getMisc(), optionalHeader.getSectionAlignment());

            if (rva >= start && rva < end)
                return i;
        }
        return -1;
    }

    int alignDown(int x, int align) {
        return x & -align;
    }

    int alignUp(int x, int align) {
        return ((x & (align - 1)) != 0 ? alignDown(x, align) + align : x);
    }
}