package com.busroute.service.impl;

import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busroute.dao.BusRouteDao;
import com.busroute.dto.DirectResponseDTO;
import com.busroute.service.BusRouteService;

/**
 * Created by dbatyuk
 */
@Service
public class BusRouteServiceImpl implements BusRouteService {

    private static final Logger log = LoggerFactory.getLogger(BusRouteServiceImpl.class);

    private final BusRouteDao busRouteDao;

    @Autowired
    public BusRouteServiceImpl(BusRouteDao busRouteDao) {
        this.busRouteDao = busRouteDao;
    }

    @Override
    public DirectResponseDTO checkIfDirect(int depSid, int arrSid) {
        log.info("checkIfDirect depSid {}, arrSid {}", depSid, arrSid);

        DirectResponseDTO dto = new DirectResponseDTO();
        dto.depSid = depSid;
        dto.arrSid = arrSid;
        dto.directBusRoute = directBusRoute(depSid, arrSid);

        return dto;
    }

    private boolean directBusRoute(int depSid, int arrSid) {
        Set<Integer> depRoutes = busRouteDao.getStationRoutes(depSid);
        Set<Integer> arrRoutes = busRouteDao.getStationRoutes(arrSid);

        return depRoutes != null && arrRoutes != null && !Collections.disjoint(depRoutes, arrRoutes);
    }
}
