import controller.PortableExecutable;
import lombok.SneakyThrows;
import structure.header.DOSHeader;
import unmarshaller.ObjectUnmarshaller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, ReflectiveOperationException {
        ObjectUnmarshaller unmarshaller = new ObjectUnmarshaller(
                new FileInputStream("src/main/resources/BasePC.exe"),
                ByteOrder.LITTLE_ENDIAN
        );
        PortableExecutable executable = unmarshaller.unmarshall(PortableExecutable.class);
        System.out.println(executable);
    }
}
