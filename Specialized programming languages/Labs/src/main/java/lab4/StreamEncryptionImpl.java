package lab4;

public class StreamEncryptionImpl implements StreamEncryption {
    private static final byte KEY = 0b1111111;

    public static byte[] encrypt(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= KEY;
        }
        return bytes;
    }

    public static byte[] decrypt(byte[] bytes) {
        return encrypt(bytes);
    }
}
