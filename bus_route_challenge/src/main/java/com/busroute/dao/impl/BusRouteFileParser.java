package com.busroute.dao.impl;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * Created by dbatyuk
 */
public class BusRouteFileParser {

    private static final Logger log = LoggerFactory.getLogger(BusRouteFileParser.class);

    public Map<Integer, Set<Integer>> parseRoutes(String filePath) {
        Preconditions.checkArgument(!isNullOrEmpty(filePath), "filePath can't be null or empty");
        log.info("start parseRoutes file {}", filePath);

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            Map<Integer, Set<Integer>> stationRoutes = new HashMap<>();

            stream.filter(r -> r.indexOf(" ") > 0).forEach(route -> {
                String[] split = route.split(" ");

                int busRouteNumb = Integer.valueOf(split[0]);
                List<Integer> routeStations = Arrays.stream(split, 1, split.length).map(Integer::valueOf).collect(toList());

                if (routeStations != null && !routeStations.isEmpty()) {
                    routeStations.forEach(station -> {
                        Set<Integer> relatedRoutes = stationRoutes.getOrDefault(station, new HashSet<>());
                        relatedRoutes.add(busRouteNumb);
                        stationRoutes.put(station, relatedRoutes);
                    });
                }
            });

            log.info("finish parseRoutes, routes {},  file {}", stationRoutes.size(), filePath);
            return stationRoutes;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
