package com.busroute.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Map;
import java.util.Set;

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
        Map<Integer, Set<Integer>> map = busRouteFileParser.parseRoutes(new File("src/test/resources/test").getAbsolutePath());

        assertEquals(5, map.size());
        assertEquals(1, map.get(6).size());
        assertTrue(map.get(6).contains(1));

        assertEquals(1, map.get(106).size());
        assertTrue(map.get(106).contains(1));

        assertEquals(1, map.get(11).size());
        assertTrue(map.get(11).contains(1));

        assertEquals(1, map.get(150).size());
        assertTrue(map.get(150).contains(4));

        assertEquals(2, map.get(142).size());
        assertTrue(map.get(142).contains(1));
        assertTrue(map.get(142).contains(4));
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