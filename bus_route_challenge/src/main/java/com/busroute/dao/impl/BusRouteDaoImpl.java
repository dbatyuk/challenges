package com.busroute.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.busroute.dao.BusRouteDao;
import com.google.common.base.Preconditions;

/**
 * Created by dbatyuk
 */
@Repository
public class BusRouteDaoImpl implements BusRouteDao {

    private static final Logger log = LoggerFactory.getLogger(BusRouteDaoImpl.class);

    private final Map<Integer, Set<Integer>> stationRoutes = new ConcurrentHashMap<>();

    private boolean initiated;

    public void init(Map<Integer, List<Integer>> routes) {
        log.info("start init, routes {}", routes.size());

        routes.forEach((route, stations) -> {
            if (route % 1000 == 0) {
                System.out.println(route);
            }
            stations.forEach(station -> {
                Set<Integer> relatedRoutes = stationRoutes.getOrDefault(station, new HashSet<>());
                relatedRoutes.add(route);
                stationRoutes.put(station, relatedRoutes);
            });
        });

        log.info("finish init, stationRoutes ", stationRoutes.size());
        initiated = true;
    }

    @Override
    public Set<Integer> getStationRoutes(int station) {
        Preconditions.checkArgument(initiated, "station routes not initiated");

        return stationRoutes.get(station);
    }
}