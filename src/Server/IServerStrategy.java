package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {
    /* apply strategy of any kind of server */
    void applyStrategy(InputStream inputStream, OutputStream outputStream);
}
