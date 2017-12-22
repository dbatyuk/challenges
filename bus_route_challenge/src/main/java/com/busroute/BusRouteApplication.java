package com.busroute;

import java.util.Map;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.busroute.dao.impl.BusRouteDaoImpl;
import com.busroute.dao.impl.BusRouteFileParser;
import com.google.common.base.Preconditions;

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
            Preconditions.checkArgument(args.length > 0, "file path not provided");
            Map<Integer, Set<Integer>> routes = new BusRouteFileParser().parseRoutes(args[0]);
            bean.init(routes);
        };
    }
}