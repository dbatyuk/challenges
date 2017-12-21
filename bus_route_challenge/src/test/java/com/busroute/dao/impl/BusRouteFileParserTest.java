package com.busroute.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by dbatyuk
 */
public class BusRouteFileParserTest {

    private BusRouteFileParser busRouteFileParser;

    @Before
    public void setUp() {
        busRouteFileParser = new BusRouteFileParser();
    }

    @Test
    public void test() {
        Map<Integer, List<Integer>> map = busRouteFileParser.parseRoutes(new File("src/test/resources/test").getAbsolutePath());
        assertEquals(1, map.size());
        List<Integer> stations = map.get(2);
        assertNotNull(stations);
        assertEquals(5, stations.get(0).intValue());
        assertEquals(142, stations.get(1).intValue());
        assertEquals(106, stations.get(2).intValue());
        assertEquals(11, stations.get(3).intValue());
    }

    @Test
    public void testWrongPath() {
        try {
            busRouteFileParser.parseRoutes("wrong-path");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("java.nio.file.NoSuchFileException: wrong-path", e.getMessage());
        }
    }

    @Test
    public void testNullPath() {
        try {
            busRouteFileParser.parseRoutes(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("filePath can't be null or empty", e.getMessage());
        }
    }

    @Test
    public void testEmptyPath() {
        try {
            busRouteFileParser.parseRoutes("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("filePath can't be null or empty", e.getMessage());
        }
    }

}