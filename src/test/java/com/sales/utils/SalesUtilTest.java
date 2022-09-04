package com.sales.utils;

import com.sales.model.Message;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SalesUtilTest {

    @Test
    public void testGetMessagesFromStream() throws IOException {
        SalesUtil salesUtil = new SalesUtil();

        String fileName = "salesTest1.txt";

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        List<Message> mList = SalesUtil.getMessagesFromStream(inputStream);

        assertEquals(9, mList.size());
        assertEquals("apple", mList.get(0).getProductName());
        assertEquals(4, mList.stream().filter(a -> a.getMessageType().equals("MessageType2")).count());
        assertEquals(5, mList.stream().filter(a -> a.getMessageType().equals("MessageType1")).count());

    }

    @Test
    public void testGetMessagesFromStreamWithException() throws RuntimeException {
        String fileName ="salesTestException.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SalesUtil.getMessagesFromStream(inputStream);
        });

        assertEquals("file data is incorrect", exception.getMessage());
    }

    @Test
    void testGetFileFromResourceAsStream() {
        String fileName = "salesTest1.txt";

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        SalesUtil salesUtil = new SalesUtil();

        InputStream inputStreamOut = salesUtil.getFileFromResourceAsStream(fileName);

        try {
            assertEquals(inputStream.readAllBytes().length, inputStreamOut.readAllBytes().length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetFileFromResourceAsStreamWithException() {
        String fileName = "salesTest_NoFile.txt";
        SalesUtil salesUtil = new SalesUtil();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            salesUtil.getFileFromResourceAsStream(fileName);
        });

        assertEquals("file not found! salesTest_NoFile.txt", exception.getMessage());
    }
}