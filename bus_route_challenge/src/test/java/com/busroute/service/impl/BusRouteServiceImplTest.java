package com.busroute.service.impl;

import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.busroute.dao.impl.BusRouteDaoImpl;
import com.busroute.dto.DirectResponseDTO;
import com.busroute.service.BusRouteService;

/**
 * Created by dbatyuk
 */
public class BusRouteServiceImplTest {

    private static BusRouteService busRouteService;

    @BeforeClass
    public static void setUp() {
        BusRouteDaoImpl busRouteDao = new BusRouteDaoImpl();
        busRouteDao.init(getTestMap());

        busRouteService = new BusRouteServiceImpl(busRouteDao);
    }

    @Test
    public void testHasDirect() {
        DirectResponseDTO response = busRouteService.checkIfDirect(17241, 55539);
        assertEquals(17241, response.depSid);
        assertEquals(55539, response.arrSid);
        assertTrue(response.directBusRoute);
    }

    @Test
    public void testSameStation() {
        DirectResponseDTO response = busRouteService.checkIfDirect(17241, 17241);
        assertEquals(17241, response.depSid);
        assertEquals(17241, response.arrSid);
        assertTrue(response.directBusRoute);
    }

    @Test
    public void testHasDirectViceVersa() {
        DirectResponseDTO response = busRouteService.checkIfDirect(55539, 17241);
        assertEquals(55539, response.depSid);
        assertEquals(17241, response.arrSid);
        assertTrue(response.directBusRoute);
    }

    @Test
    public void testHasNotDirect() {
        DirectResponseDTO response = busRouteService.checkIfDirect(55539, 7541);
        assertEquals(55539, response.depSid);
        assertEquals(7541, response.arrSid);
        assertFalse(response.directBusRoute);
    }

    @Test
    public void testHasNotDirectNotExistingStation() {
        DirectResponseDTO response = busRouteService.checkIfDirect(100_001, 7541);
        assertEquals(100_001, response.depSid);
        assertEquals(7541, response.arrSid);
        assertFalse(response.directBusRoute);
    }

    @Test
    public void testHasNotDirectNotExistingStations() {
        DirectResponseDTO response = busRouteService.checkIfDirect(100_001, 100_002);
        assertEquals(100_001, response.depSid);
        assertEquals(100_002, response.arrSid);
        assertFalse(response.directBusRoute);
    }

    private static Map<Integer, Set<Integer>> getTestMap() {
        Random random = new Random();

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < 100_000; i++) {
            Set<Integer> routes = new HashSet<>();
            int routesSize = random.nextInt(10) + 1;
            for (int j = 0; j < routesSize; j++) {
                routes.add(random.nextInt(100_000));
            }

            map.put(i, routes);
        }

        map.put(17241, newHashSet(77008, 45539, 51702, 6891, 25951));
        map.put(55539, newHashSet(32321, 1339, 31702, 27241, 6892, 25951));
        map.put(7541, newHashSet(17008, 95539, 41702, 9891, 25451));

        return map;
    }

}