package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;


    public MyCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    /**
     * Compressing through Deflater class
     * Deflater class is build-in class in Java.
     *
     * @param b maze represented by byte array
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(b);
        deflater.finish();

        byte[] buffer = new byte[b.length];
        int count = deflater.deflate(buffer);

        // Write compressed data
        out.write(buffer, 0, count);
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
        super.flush();
    }

    @Override
    public void close() throws IOException {
        this.out.close();
        super.close();
    }

    public OutputStream getOut() {
        return out;
    }
}
