package structure.image;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OffsetField {
    // Вообще-то тут 2 поля на 12 и 4 бит, но понятия не имею, как это адекватно сделать
    short data;
}
