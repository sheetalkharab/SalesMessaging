package com.sales.service;

import com.sales.utils.SalesUtil;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SalesServiceTest {

    @Test
    void testProcessSalesMessages() throws IOException, URISyntaxException {
        //give
        String fileName = "salesTest3.txt";
        SalesUtil salesUtil = new SalesUtil();

        SalesService salesService = new SalesService();
        boolean result = salesService.processSalesMessages(fileName);
        assertEquals(true, result);

    }
}