package ru.otus.l161.app;

import ru.otus.l161.channel.Blocks;

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

    void close();
}
