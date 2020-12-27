package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import unmarshaller.Len;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DOSHeader implements Serializable {
    private short magic;
    private short usedBytesInLastPage;
    private short fileSizeInPages;
    private short numRelocationItems;
    private short headerSizeInParagraphs;
    private short minExtraParagraphs;
    private short maxExtraParagraphs;
    private short initialSS;
    private short initialSP;
    private short checksum;
    private short initialIP;
    private short initialRelativeCS;
    private short addressOfRelocationTable;
    private short overlayNumber;
    @Len(4)
    private short[] reserved;
    @Len(10)
    private short[] reserved2;
    private short oemId;
    private short oemInfo;
    private short addressOfNewExeHeader;
    private short stubSize;
}
