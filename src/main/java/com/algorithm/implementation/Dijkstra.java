package com.algorithm.implementation;

import com.algorithm.entity.City;
import com.algorithm.entity.Route;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Dijkstra {

    ArrayList<City> cities = new ArrayList<City>();
    City from;
    City to;

    /**
     * Default constructor. Loads the cities map.
     */
    public Dijkstra() {
        load();
    }

    /**
     * <p>
     * Execute the Dijkstra algorithm to search for the better route
     * between origin city and the desired destiny.
     * @param fromCityName The name of from city
     * @param toCityName The name of destiny city.
     */
    public void execute(String fromCityName, String toCityName) {

        // Get from city:
        this.from = getCityByName(fromCityName);

        // 1° Step:
        initializeEstimates();

        // 2° Step: (Loop)
        // -- While has open city...
        while (hasOpenedCity()) {
            // -- Get the city with the lowest estimate.
            City city = getLowestEstimate();
            // -- Check if exits.
            if (city == null)
                break;

            // Close the city.
            city.setOpen(false);
            // 3° Step
            relaxCity(city);
        }
        // Just print the better route...
        City destiny = getCityByName(toCityName);
        print(destiny, destiny.getEstimate());
    }

    /**
     * Prints the best route...
     * @param to The destiny city.
     */
    private void print(City to, int total) {
        // Get to city:
        if ( to == null || to.getFrom() == null ) {
            System.out.println("Destância total percorrida = " + total + "km");
            return;
        }
        Integer distance = to.getFrom().getPath(to.getName());
        System.out.println( "De " + to.getName() + " -> " + distance + "km" +  " -> " + to.getFrom().getName());
        print(to.getFrom(), total);
    }

    /**
     * Auxiliary function the find a city based on it's name
     * @param name The city name
     * @return City found. Otherwise, null.
     */
    private City getCityByName(String name) {
        for (City city: cities) {
            if (city.getName().equals(name))
                return city;
        }
        return null;
    }

    /**
     * <p>
     * Relax the city means, search for all routes based on the origin
     * city, and check if the destiny city has a lowest estimate
     * considering the path between those two cities. If the path is
     * shorted, then update the destiny city estimate and also save
     * where the path came from.
     * @param  from
     */
    private void relaxCity(City from) {
        for (Route route: from.getRoutes()) {
            // Get destiny city
            City to = getCityByName(route.getTo());
            
            // No route at all
                if (to == null)
            	return;
            
            // The distance between from and to city
            Integer distance = route.getDistance();
            // If the 'from' city estimate is infinity, means that
            // we don't have route to the destiny city. Just discard this estimate
            if (from.getEstimate() >= Integer.MAX_VALUE ) {
                continue;
            }
            // The total path
            Integer path = from.getEstimate() + distance;
            // It is a shorter route?
            if ( path <= to.getEstimate() ) {
                to.updateBetterEstimate(from, path);
            }
        }
    }

    /**
     * <p>
     * Search for the city with lowest estimate, it's also necessary
     * the city to be opened.
     * @return The city with the lowest estimate.
     */
    private City getLowestEstimate() {
        // Start estimate with infinity value.
        Integer lowestEstimate = Integer.MAX_VALUE;
        // Will represent the city with the lowest estimate.
        City lowestEstimateCity = null;
        // For each city...
        for (City city: cities) {
            // The city is opened? the city's estimate is lower than the current estimate?
            if ( city.isOpen() && city.getEstimate() <= lowestEstimate ) {
                // Update to the lowest value
                lowestEstimate = city.getEstimate();
                // Save the city with the lowest estimate
                lowestEstimateCity = city;
            }
        }
        // At the end, returns the city with the lowest estimate.
        return lowestEstimateCity;
    }

    /**
     * <p>
     * Check if still has opened city.
     * @return true with has opened city.
     */
    private boolean hasOpenedCity() {
        for (City city: cities) {
            if (city.isOpen())
                return true;
        }
        return false;
    }

    /**
     * <p>
     * 1° Step: Dijkstra Algorithm Initialization.
     * Set all cities estimate to infinity, except the from city, this
     * last one will set to zero.
     */
    private void initializeEstimates() {
        from.setAsFirstCity();
        for (City city: cities) {
            // The first city set the estimate to zero, the other to infinity.
            if ( !city.getName().equals( from.getName()) ) {
                city.initialize();
            }
        }
    }

    /**
     * Load cities Map.
     */
    private void load() {

        City v0 = new City("v0");
        City v1 = new City("v1");
        City v2 = new City("v2");
        City v3 = new City("v3");
        City v4 = new City("v4");
        City v5 = new City("v5");

        cities.add(v0);
        cities.add(v1);
        cities.add(v2);
        cities.add(v3);
        cities.add(v4);
        cities.add(v5);

        // For better memory allocation performance, set only the name
        // of the cities into route object.
        Route r1 = new Route(10, "v0", "v1");
        Route r2 = new Route(5, "v0", "v2");
        v0.populateRoutes(r1);
        v0.populateRoutes(r2);
        
        Route r3 = new Route(1, "v1", "v3");
        v1.populateRoutes(r3);
        
        Route r4 = new Route(3, "v2", "v1");
        Route r5 = new Route(8, "v2", "v3");
        Route r6 = new Route(2, "v2", "v4");
        v2.populateRoutes(r4);
        v2.populateRoutes(r5);
        v2.populateRoutes(r6);
        
        
        Route r7 = new Route(4, "v3", "v4");
        Route r8 = new Route(4, "v3", "v5");
        v3.populateRoutes(r7);
        v3.populateRoutes(r8);

        Route r9 = new Route(6, "v4", "v5");
        v4.populateRoutes(r9);
    }

}

