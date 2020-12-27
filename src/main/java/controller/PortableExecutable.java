package controller;

import lombok.Data;
import structure.directory.ImageExportDirectory;
import structure.directory.ImageImportDirectory;
import structure.header.*;
import structure.image.DOSStub;
import unmarshaller.ObjectUnmarshaller;

import java.io.*;
import java.nio.ByteOrder;

@Data
public class PortableExecutable {
    DOSHeader dosHeader;
    DOSStub stub;
    Pe32PlusHeader pe32Header;
    ImageFileHeader fileHeader;
    ImageOptionalHeader32p optionalHeader;
    ImageSectionHeader sectionHeader;
    ImageExportDirectory exportDirectory;
    ImageImportDirectory importDirectory;

    public PortableExecutable(File file) throws IOException, ReflectiveOperationException {
        ObjectUnmarshaller unmarshaller = new ObjectUnmarshaller(
                new FileInputStream(file),
                ByteOrder.LITTLE_ENDIAN
        );
        dosHeader = unmarshaller.unmarshall(DOSHeader.class);
        unmarshaller.skip(dosHeader.getAddressOfNewExeHeader() - 64);
        pe32Header = unmarshaller.unmarshall(Pe32PlusHeader.class);
        fileHeader = unmarshaller.unmarshall(ImageFileHeader.class);
        optionalHeader = unmarshaller.unmarshall(ImageOptionalHeader32p.class);
        sectionHeader = unmarshaller.unmarshall(ImageSectionHeader.class);
    }
}
