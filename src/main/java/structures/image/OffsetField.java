package structures.image;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OffsetField {
    // Вообще-то тут 2 поля на 12 и 4 байт, но понятия не имею, как это адекватно сделать
    long data;
}
