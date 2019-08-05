package com.didala.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class SimpleNio {

    public  static void testReadFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/jiangwei/Documents/test.txt");

        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = channel.read(allocate);
        allocate.flip();
        char aChar = allocate.getChar();
        System.out.println(aChar);
        System.out.println(allocate);
    }

    public static void main(String[] args) throws IOException {
        testReadFile();
    }

}
