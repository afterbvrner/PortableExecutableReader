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
        PortableExecutable executable = new PortableExecutable(new File("src/main/resources/BasePC.exe"));
        System.out.println(executable);
    }
}
