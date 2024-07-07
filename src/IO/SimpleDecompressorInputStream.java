package IO;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;


    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }
    @Override
    public int read(@NotNull byte[] b) throws IOException {
        if (in == null) {
            throw new IOException("Input stream is null");
        }

        // Read all bytes from the input stream
        byte[] allBytes = in.readAllBytes();
        if (allBytes.length < 8) {
            throw new IOException("Insufficient data in input stream");
        }

        // Ensure the provided byte array is large enough
        if (b.length < allBytes.length) {
            throw new IOException("Provided byte array is too small");
        }

        // Copy the first 8 bytes to b
        System.arraycopy(allBytes, 0, b, 0, 8);

        byte currByte = 0; // Start with byte 0
        int dynamicIdx = 8; // Index in b to start writing decompressed data

        // Iterate over the remaining bytes in the compressed data
        for (int i = 8; i < allBytes.length; i++) {
            int amount = Byte.toUnsignedInt(allBytes[i]); // Correctly handle unsigned values

            // Ensure we do not write beyond the end of b
            if (dynamicIdx + amount > b.length) {
                throw new IOException("Insufficient space in output byte array");
            }

            // Write `amount` of `currByte` to b
            for (int j = 0; j < amount; j++) {
                b[dynamicIdx++] = currByte;
            }

            // Toggle currByte between 0 and 1
            currByte = (byte) (currByte == 0 ? 1 : 0);
        }

        // Return the number of bytes written to b
        return dynamicIdx;
    }
    @Override
    public void close() throws IOException {
        this.in.close();
        super.close();
    }

}
