package com.busroute.dao.impl;

import java.util.Map;
import java.util.Set;

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

    private Map<Integer, Set<Integer>> stationRoutes;
    private boolean initiated;

    public void init(Map<Integer, Set<Integer>> stationRoutes) {
        log.info("init, stationRoutes {}", stationRoutes.size());

        this.stationRoutes = stationRoutes;
        initiated = true;
    }

    @Override
    public Set<Integer> getStationRoutes(int station) {
        Preconditions.checkArgument(initiated, "station routes not initiated");

        return stationRoutes.get(station);
    }
}