package com.busroute;

import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.busroute.dao.impl.BusRouteDaoImpl;
import com.busroute.dao.impl.BusRouteFileParser;

/**
 * Created by dbatyuk
 */
@SpringBootApplication
public class BusRouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusRouteApplication.class, args);
    }

    @Bean
    public CommandLineRunner getRunner(ApplicationContext ctx) {
        return (args) -> {
            BusRouteDaoImpl bean = ctx.getBean(BusRouteDaoImpl.class);
            Map<Integer, List<Integer>> routes = new BusRouteFileParser().parseRoutes(args[0]);
            //            Map<Integer, List<Integer>> routes = getTestMap();
            bean.init(routes);
        };
    }
}
