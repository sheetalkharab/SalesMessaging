package com.sales.service;

import com.sales.model.Message;
import com.sales.utils.SalesUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SalesService {
    public boolean processSalesMessages(String fileName) throws URISyntaxException, IOException {
        boolean processCompleted=false;
        SalesUtil salesUtil = new SalesUtil();
        InputStream inputStream = salesUtil.getFileFromResourceAsStream(fileName);
        List<Message> mList = SalesUtil.getMessagesFromStream(inputStream);

        recordSalesMessages(fileName, mList);

        logSalesMessage(mList);
        processCompleted=true;
        return processCompleted;

    }

    private void logSalesMessage(List<Message> mList) {
        int messageCount = 10;

        IntStream.range(0, (mList.size() + messageCount - 1) / messageCount)
                .mapToObj(i -> mList.subList(i * messageCount, Math.min(messageCount * (i + 1), mList.size())))
                .forEach(mSubList -> {

                    HashMap<String, Set<Message>> messageMap = mSubList.stream().collect(Collectors.groupingBy(Message::getProductName, HashMap::new,
                            Collectors.toSet()));

                    messageMap.keySet().forEach(mapElement -> {

                        double total = messageMap.get(mapElement).stream()
                                .mapToDouble(msg -> msg.getTotal()).sum();

                        long count = messageMap.get(mapElement).stream()
                                .mapToLong(msg -> msg.getCount()).sum();

                        System.out.println(String.format("For Product: %s Count is: %d and price is: £%.2f", mapElement, count,total/100));

                    });
                    System.out.println("===================SubList size =============================" + mSubList.size());

                });
    }

    public void recordSalesMessages(String fileName, List<Message> mList) throws IOException {
        FileOutputStream f = new FileOutputStream(new Date().getTime() + ".txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        mList.forEach(ot -> {
            try {
                o.writeObject(ot.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        o.close();
        f.close();
    }

}
