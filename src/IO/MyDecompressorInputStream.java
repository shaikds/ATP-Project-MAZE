package IO;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(@NotNull byte[] b) throws IOException {
        // read all the bytes from the input stream
        byte[] compressedData = in.readAllBytes();

        // decompress the decoded data
        Inflater inflater = new Inflater();
        inflater.setInput(compressedData);
        try {
            inflater.inflate(b);
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        }
        inflater.end();
        return b.length;
    }


    @Override
    public void close() throws IOException {
        this.in.close();
        super.close();
    }
}
