package com.busroute.dao;

import java.util.Set;

/**
 * Created by dbatyuk
 */
public interface BusRouteDao {

    Set<Integer> getStationRoutes(int station);
}
