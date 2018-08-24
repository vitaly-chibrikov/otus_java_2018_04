package ru.otus.l161.app;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by tully.
 */
public interface MsgWorker extends Closeable {
    void send(Msg msg);

    Msg pool();

    @Blocks
    Msg take() throws InterruptedException;

    void close() throws IOException;
}
