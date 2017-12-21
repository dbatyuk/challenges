package com.busroute.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dbatyuk
 */
public class BusRouteDaoImplTest {

    private static final Logger log = LoggerFactory.getLogger(BusRouteDaoImplTest.class);

    private BusRouteDaoImpl busRouteDao;

    @Before
    public void setUp() {
        busRouteDao = new BusRouteDaoImpl();
    }

    @Test
    public void test() {
        busRouteDao.init(getTestMap());
    }

    private Map<Integer, List<Integer>> getTestMap() {
        log.info("start init route map");
        Random random = new Random();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < 100_000; i++) {
            List<Integer> stations = new ArrayList<>();
            int stationsSize = random.nextInt(1_000);
            for (int j = 0; j < stationsSize; j++) {
                stations.add(random.nextInt(1_000_000));
            }

            map.put(i, stations);
        }

        System.out.println(map.get(0));
        log.info("finish init route map");
        return map;
    }
}