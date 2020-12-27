package structure.header;

import lombok.Data;
import lombok.NoArgsConstructor;
import unmarshaller.Len;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DOSHeader implements Serializable {
    @Len(2)
    char[] magic;
    short usedBytesInLastPage;
    short fileSizeInPages;
    short numRelocationItems;
    short headerSizeInParagraphs;
    short minExtraParagraphs;
    short maxExtraParagraphs;
    short initialSS;
    short initialSP;
    short checksum;
    short initialIP;
    short initialRelativeCS;
    short addressOfRelocationTable;
    short overlayNumber;
    @Len(4)
    private short[] reserved;
    private short oemId;
    private short oemInfo;
    @Len(10)
    private short[] reserved2;
    private int addressOfNewExeHeader;
}
