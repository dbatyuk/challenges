package com.busroute.dao.impl;

import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by dbatyuk
 */
public class BusRouteDaoImplTest {

    private BusRouteDaoImpl busRouteDao;

    @Before
    public void setUp() {
        busRouteDao = new BusRouteDaoImpl();

        Map<Integer, Set<Integer>> map = new HashMap<>();
        map.put(0, newHashSet(77008, 55539, 51702, 17241, 6891, 25951));

        busRouteDao.init(map);
    }

    @Test
    public void test() {
        Set<Integer> stationRoutes = busRouteDao.getStationRoutes(0);
        assertEquals(6, stationRoutes.size());
        assertTrue(stationRoutes.contains(77008));
        assertTrue(stationRoutes.contains(55539));
        assertTrue(stationRoutes.contains(51702));
        assertTrue(stationRoutes.contains(17241));
        assertTrue(stationRoutes.contains(6891));
        assertTrue(stationRoutes.contains(25951));
    }

    @Test
    public void testNotExistingStation() {
        assertNull(busRouteDao.getStationRoutes(1));
    }

    @Test
    public void testNotInitiated() {
        try {
            new BusRouteDaoImpl().getStationRoutes(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("station routes not initiated", e.getMessage());
        }
    }
}