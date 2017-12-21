package com.busroute.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busroute.dto.DirectResponseDTO;
import com.busroute.service.BusRouteService;

/**
 * Created by dbatyuk
 */
@RestController
public class BusRouteController {

    private static final Logger log = LoggerFactory.getLogger(BusRouteController.class);

    @Autowired
    private BusRouteService busRouteService;

    @RequestMapping(value = "/api/direct", method = RequestMethod.GET)
    public DirectResponseDTO direct(@RequestParam("dep_sid") int depSid, @RequestParam("arr_sid") int arrSid) {
        log.info("direct depSid {}, arrSid {}", depSid, arrSid);

        return busRouteService.checkIfDirect(depSid, arrSid);
    }
}
