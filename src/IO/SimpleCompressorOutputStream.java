package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {

    private static int MAX = 255;
    private static int LAST_CHAR = 0;

    private int counter;
    private OutputStream out;

    // Constructor
    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
        this.counter = 0;
    }

    @Override
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        if (b != null && b.length > 0) {
            // byte list is not null && >0
            for (int i = 0; i < 8; i++) {
                out.write(b[i]); // write 8 bytes of maze properties, as is.
            }
            int idx = 8;
            while (idx < b.length) {
                // get next byte to write
                byte currByte = b[idx];
                idx++; // increment the index in bytes array

                // check if amount of byte shown is >= 255
                if (counter >= MAX) {
                    // 1) add the counter=255 to the out
                    // 2) then add 0
                    // 3) finally, reset counter
                    out.write(255);
                    out.write(0);
                    this.counter = 0; // reset the counter
                }

                if (currByte == LAST_CHAR) // same char as before, increment the counter
                    this.counter++;

                else {    // Not same char as before
                    // 1) add current
                    // 2) update LAST_CHAR to current value
                    // 3) reset counter
                    out.write(counter);
                    LAST_CHAR = currByte;
                    this.counter = 1;
                }
            }
            // write last counter
            out.write(counter);
        }
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
}
