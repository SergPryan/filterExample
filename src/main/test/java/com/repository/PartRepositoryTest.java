package com.repository;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class PartRepositoryTest {


    @Test
    public void testCreateSqlFilterRequest() {
        Map<String, String[]> map = new HashMap<>();
        String[] number = {"www"};
        map.put("number",number);
        String[] partName = {"zzz"};
        map.put("name",partName);
        String[] vendor = {"vendor"};
        map.put("vendor",vendor);
        String[] qty = {"222"};
        map.put("qty",qty);
        String[] shippedAfter = {"1990-01-01"};
        map.put("shippedAfter",shippedAfter);
        String[] shippedBefore = {"1991-01-01"};
        map.put("shippedBefore",shippedBefore);
        String[] receivedAfter = {"1992-01-01"};
        map.put("receivedAfter",receivedAfter);
        String[] receivedBefore = {"1993-01-01"};
        map.put("receivedBefore",receivedBefore);
        String actual = PartRepository.createSqlFilterRequest(map);
        String excepted = "SELECT * FROM part WHERE \"partName\" LIKE '%zzz%' AND \"partNumber\" LIKE '%www%' AND vendor LIKE '%vendor%' AND qty >= 222 AND received >= '1992-01-01' AND received <= '1993-01-01' AND shipped >= '1990-01-01' AND shipped <= '1991-01-01'";
        assertEquals(excepted,actual);
    }
}