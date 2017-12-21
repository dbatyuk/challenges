package com.busroute.service;

import com.busroute.dto.DirectResponseDTO;

/**
 * Created by dbatyuk
 */
public interface BusRouteService {
    DirectResponseDTO checkIfDirect(int depSid, int arrSid);
}
