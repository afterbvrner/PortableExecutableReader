import controller.PortableExecutable;
import unmarshaller.ObjectUnmarshaller;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteOrder;

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
