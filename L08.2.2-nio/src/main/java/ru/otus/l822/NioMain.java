package ru.otus.l822;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class NioMain {
    private static final CharsetDecoder CHARSET_DECODER = Charset.forName("UTF-8").newDecoder();

    public static void main(String[] args) throws Exception {
        try (RandomAccessFile aFile = new RandomAccessFile("data/data.txt", "rw")) {
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(4);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                System.out.print(CHARSET_DECODER.decode(buf));

                System.out.print("\n");
                buf.clear();
                bytesRead = inChannel.read(buf);
            }
        }
    }
}
