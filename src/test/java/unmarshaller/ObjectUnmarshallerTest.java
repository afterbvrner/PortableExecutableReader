package unmarshaller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.ByteOrder;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ObjectUnmarshallerTest {

    @Test
    public void unmarshallerTest() {
        byte[] data = new byte[] {
                0x00,
                (byte) 0xFF,
                0x00,
                (byte) 0xFF,
                0x10,
                0x00,
                'X',
                'U',
                'Y',
                'A',
                'B',
                'C',
                'D'
        };
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ObjectUnmarshaller unmarshaller = new ObjectUnmarshaller(inputStream, ByteOrder.BIG_ENDIAN);

        try {
            TestData result = unmarshaller.unmarshall(TestData.class);
            assertEquals( 0xFF00FF, result.i1);
            assertEquals(0x1000, result.s2);
            assertArrayEquals("XUY".toCharArray(), result.str);
            assertArrayEquals("ABCD".toCharArray(), result.str2Longer);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Data
    @NoArgsConstructor
    public static class TestData {
        int i1;
        short s2;
        @Charset(value = "UTF-8")
        @Len(3)
        char[] str;
        @Conditional("strIsXyz")
        @Len(3)
        char[] str2;
        @Conditional(value = "strIsXyz", invert = true)
        @Len(4)
        char[] str2Longer;

        boolean strIsXyz() {
            return Arrays.equals(str, "XYZ".toCharArray());
        }
    }
}