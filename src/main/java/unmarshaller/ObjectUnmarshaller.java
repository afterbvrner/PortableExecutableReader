package unmarshaller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import structure.directory.ImageDataDirectory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class ObjectUnmarshaller {

    private final InputStream inputStream;
    private final ByteOrder byteOrder;

    private long position = 0L;

    @SuppressWarnings("unchecked")
    public <T> T unmarshall(Class<T> type) throws IOException, ReflectiveOperationException {
        return (T) unmarshall(type, new UnmarshallHint());
    }

    @SneakyThrows
    public void skip(long n) {
        position += inputStream.skip(n);
    }

    private <T> Object unmarshall(Class<T> type, UnmarshallHint hint) throws IOException, ReflectiveOperationException {
        if (type.isAssignableFrom(byte.class) || type.isAssignableFrom(Byte.class))
            return readByte();
        if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class))
            return readShort();
        if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class))
            return readInt();
        if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class))
            return readLong();
        if (type.isAssignableFrom(char.class) || type.isAssignableFrom(Character.class)) {
            if (hint.getCharset() != null)
                return readChar(hint.getCharset());
            else
                return readChar();
        }

        T result = type.getConstructor().newInstance();

        for (Field field : type.getDeclaredFields())
            unmarshallField(result, field);

        return result;
    }

    private <T> T[] unmarshallArray(Class<T> elementType, int size) throws IOException, ReflectiveOperationException {
        return unmarshallArray(elementType, size, new UnmarshallHint());
    }

    @SuppressWarnings("unchecked")
    private <T> T[] unmarshallArray(Class<T> elementType, int size,
                                    UnmarshallHint hint) throws IOException, ReflectiveOperationException {
        T[] result = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = (T) unmarshall(elementType, hint);
        }
        return result;
    }

    private void unmarshallField(Object obj, Field field) throws IOException, ReflectiveOperationException {
        field.setAccessible(true);

        Position positionAnnotation = field.getAnnotation(Position.class);
        if (positionAnnotation != null && positionAnnotation.value() - position > 0)
            position += inputStream.skip(positionAnnotation.value() - position);

        GetPosition getPosition = field.getAnnotation(GetPosition.class);
        if (getPosition != null) {
            Method getPositionMethod = obj.getClass().getDeclaredMethod(getPosition.value());
            long newPos = (long) getPositionMethod.invoke(obj);
            position += inputStream.skip(newPos - position);
        }

        PositionAssert positionAssert = field.getAnnotation(PositionAssert.class);
        if (positionAssert != null) {
            Method assertionMethod = obj.getClass().getDeclaredMethod(positionAssert.value(), long.class);
            if (!(boolean) assertionMethod.invoke(obj, position))
                throw new AssertionError("Position assertion error");
        }

        Ignore ignore = field.getAnnotation(Ignore.class);
        if (ignore != null)
            return;

        Conditional conditional = field.getAnnotation(Conditional.class);
        if (conditional != null) {
            Method conditionalMethod = obj.getClass().getDeclaredMethod(conditional.value());
            if (!conditionalMethod.getReturnType().isAssignableFrom(boolean.class))
                throw new IllegalArgumentException("Conditional method should return boolean");
            boolean value = (boolean) conditionalMethod.invoke(obj);
            if (conditional.invert())
                value = !value;
            if (!value)
                return;
        }

        UnmarshallHint hint = new UnmarshallHint();
        unmarshaller.Charset charsetAnnotation =
                field.getAnnotation(unmarshaller.Charset.class);
        if (charsetAnnotation != null)
            hint.setCharset(Charset.forName(charsetAnnotation.value()));


        if (field.getType().isArray()) {
            int len;

            Len lenAnnotation = field.getAnnotation(Len.class);
            GetLen getLenAnnotation = field.getAnnotation(GetLen.class);

            if (lenAnnotation != null)
                len = lenAnnotation.value();
            else if (getLenAnnotation != null) {
                Method getLen = obj.getClass().getDeclaredMethod(getLenAnnotation.value());
                getLen.setAccessible(true);
                len = (int) getLen.invoke(obj);
            }
            else {
                throw new RuntimeException("No len info about field " + field);
            }

            Object[] result = unmarshallArray(field.getType().getComponentType(), len, hint);
            if (field.getType().getComponentType().isPrimitive()) {
                if (field.getType().getComponentType().isAssignableFrom(byte.class)) {
                    byte[] data = new byte[result.length];
                    for (int i = 0; i < data.length; i++)
                        data[i] = (byte) result[i];
                    field.set(obj, data);
                }
                else if (field.getType().getComponentType().isAssignableFrom(short.class)) {
                    short[] data = new short[result.length];
                    for (int i = 0; i < data.length; i++)
                        data[i] = (short) result[i];
                    field.set(obj, data);
                }
                else if (field.getType().getComponentType().isAssignableFrom(int.class)) {
                    int[] data = new int[result.length];
                    for (int i = 0; i < data.length; i++)
                        data[i] = (int) result[i];
                    field.set(obj, data);
                }
                else if (field.getType().getComponentType().isAssignableFrom(long.class)) {
                    long[] data = new long[result.length];
                    for (int i = 0; i < data.length; i++)
                        data[i] = (long) result[i];
                    field.set(obj, data);
                }
                else if (field.getType().getComponentType().isAssignableFrom(char.class)) {
                    char[] data = new char[result.length];
                    for (int i = 0; i < data.length; i++)
                        data[i] = (char) result[i];
                    field.set(obj, data);
                }
            } else {
                Object objArray = Array.newInstance(field.getType().getComponentType(), result.length);
                for (int i = 0; i < result.length; i++)
                    Array.set(objArray, i, result[i]);
                field.set(obj, objArray);
            }
        }
        else {
            field.set(obj, unmarshall(field.getType(), hint));
        }
    }

    private byte readByte() throws IOException {
        position += 1L;
        return (byte) inputStream.read();
    }

    private short readShort() throws IOException {
        byte[] data = inputStream.readNBytes(2);
        position += 2L;
        return ByteBuffer.wrap(data)
                .order(byteOrder)
                .getShort();
    }

    private int readInt() throws IOException {
        byte[] data = inputStream.readNBytes(4);
        position += 4L;
        return ByteBuffer.wrap(data)
                .order(byteOrder)
                .getInt();
    }

    private long readLong() throws IOException {
        byte[] data = inputStream.readNBytes(8);
        position += 8L;
        return ByteBuffer.wrap(data)
                .order(byteOrder)
                .getLong();
    }

    private char readChar() throws IOException {
        return readChar(StandardCharsets.US_ASCII);
    }

    private char readChar(Charset charset) throws IOException {
        if (charset == StandardCharsets.US_ASCII ||
                charset == StandardCharsets.UTF_8)
            return (char) readByte();
        else if (charset == StandardCharsets.UTF_16 ||
                charset == StandardCharsets.UTF_16BE ||
                charset == StandardCharsets.UTF_16LE) {
            byte[] data = inputStream.readNBytes(2);
            position += 2;
            return ByteBuffer.wrap(data)
                    .order(byteOrder)
                    .getChar();
        }
        throw new UnsupportedOperationException("Unsupported charset");
    }

    @Data
    private static class UnmarshallHint {
        private Charset charset;
    }
}
