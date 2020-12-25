package structures.directory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDataDirectory {
    int virtualAddress;
    int size;
}
