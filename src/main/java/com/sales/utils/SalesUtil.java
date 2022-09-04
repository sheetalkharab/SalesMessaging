package com.sales.utils;

import com.sales.model.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SalesUtil {

    public static List<Message> getMessagesFromStream(InputStream is) throws IOException {

        Message message = null;
        List<Message> mList = new ArrayList<>();
        try {
            InputStreamReader streamReader = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(streamReader) ;

            String line;


            while ((line = reader.readLine()) != null) {
                String[] lineValues = line.split(",");
                message = new Message();

                if ("MessageType1".equals(lineValues[0])) {
                    message.setMessageType(lineValues[0]);
                    message.setProductName(lineValues[1]);
                    message.setProductPrice(Long.valueOf(lineValues[2].trim().substring(0, lineValues[2].length() - 1)));
                    message.setCount(1);

                    message.setTotal(message.getProductPrice());

                } else if ("MessageType2".equals(lineValues[0])) {
                    message.setMessageType(lineValues[0]);
                    message.setCount(Integer.valueOf(lineValues[1].trim()));
                    message.setProductName(lineValues[2]);
                    message.setProductPrice(Long.valueOf(lineValues[3].trim().substring(0, lineValues[3].length() - 1)));

                    message.setTotal(message.getCount() * message.getProductPrice());
                }
                mList.add(message);
            }

        } catch (IOException e) {
            throw new IOException("Parsing exception while reading data from file");
        }catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException en) {
            throw new RuntimeException("file data is incorrect");
        }

        return mList;
    }

    public  InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

}
