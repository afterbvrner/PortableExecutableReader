package structures.image;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseRelocationBlock {
    int pageRva;
    int blockSize;
}
