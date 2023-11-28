package GraphAlgo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IslandArchipelago {
    private static Map<String, City> cityMap = new HashMap<>();

    // Task 1: To implement a class 'City'
    static class City {
        String name;
        int population;
        List<City> connectedCities;

        public City(String name, int population) {
            this.name = name;
            this.population = population;
            this.connectedCities = new ArrayList<>();
        }
    }

    // Task 2: Construct graph
    public static void constructGraph(String populationFile, String roadFile) throws IOException {
        BufferedReader populationReader = new BufferedReader(new FileReader(populationFile));
        BufferedReader roadReader = new BufferedReader(new FileReader(roadFile));
        String line;
        int population = 0;

        // Reading city population data
        while ((line = populationReader.readLine()) != null) {
            String[] parts = line.split(" : "); // reading the file data
            String cityName = parts[0];
            int cityPopulation = Integer.parseInt(parts[1]);
            City city = new City(cityName, cityPopulation);

            population += cityPopulation;
            cityMap.put(cityName, city);
        }

        System.out.println(population);

        // Connecting cities based on roads
        while ((line = roadReader.readLine()) != null) {
            String[] parts = line.split(" : ");
            String city1Name = parts[0]; // city1
            String city2Name = parts[1]; // city2

            City city1 = cityMap.get(city1Name);
            City city2 = cityMap.get(city2Name);

            city1.connectedCities.add(city2);
            city2.connectedCities.add(city1);
        }

        populationReader.close();
        roadReader.close();
    }

    // Task 3
    public static int numberOfIslands(List<City> cities) { // DFS
        int count = 0;
        Set<City> visited = new HashSet<>();

        for (City city : cities) {
            if (!visited.contains(city)) {
                exploreIsland(city, visited);
                count++;
            }
        }
        return count;
    }

    private static void exploreIsland(City city, Set<City> visited) {
        visited.add(city);
        for (City connectedCity : city.connectedCities) {
            if (!visited.contains(connectedCity)) {
                exploreIsland(connectedCity, visited);
            }
        }
    }

    // Task 4
    public static Map<Integer, Integer> populationOfIslands(List<City> cities) {
        Map<Integer, Integer> islandPopulationMap = new HashMap<>();
        Set<City> visited = new HashSet<>();
        int islandId = 1;

        for (City city : cities) {
            if (!visited.contains(city)) {
                int populationSum = exploreIslandAndGetPopulation(city, visited);
                islandPopulationMap.put(islandId, populationSum);
                islandId++;
            }
        }
        return islandPopulationMap;
    }

    private static int exploreIslandAndGetPopulation(City city, Set<City> visited) {
        visited.add(city);
        int populationSum = city.population;
        for (City connectedCity : city.connectedCities) {
            if (!visited.contains(connectedCity)) {
                populationSum += exploreIslandAndGetPopulation(connectedCity, visited);
            }
        }
        return populationSum;
    }

    // Task 5
    public static int minimumHighways(City startCity, City endCity) { // BFS
        Set<City> visited = new HashSet<>();
        Queue<City> queue = new LinkedList<>();
        Map<City, Integer> distanceMap = new HashMap<>();

        queue.offer(startCity);
        visited.add(startCity);
        distanceMap.put(startCity, 0);

        while (!queue.isEmpty()) {
            City currentCity = queue.poll();
            int distance = distanceMap.get(currentCity);

            if (currentCity.equals(endCity)) {
                return distance;
            }

            for (City connectedCity : currentCity.connectedCities) {
                if (!visited.contains(connectedCity)) {
                    queue.offer(connectedCity);
                    visited.add(connectedCity);
                    distanceMap.put(connectedCity, distance + 1);
                }
            }
        }

        // If endCity is not reachable from startCity
        return -1;
    }

    public static void main(String[] args) {
        try {
            constructGraph("GraphAlgo/city_population.txt", "GraphAlgo/road_network.txt");

            // Task 3
            List<City> cities = new ArrayList<>(cityMap.values());
            int numberOfIslands = numberOfIslands(cities);
            System.out.println("Number of islands: " + numberOfIslands);

            // Task 4
            Map<Integer, Integer> islandPopulationMap = populationOfIslands(cities);
            System.out.println("Population of each island: " + islandPopulationMap);

            // Task 5
            City cityA = cityMap.get("Scranton");
            City cityB = cityMap.get("Springfield");
            int minimumHighways = minimumHighways(cityA, cityB);
            System.out.println("Minimum number of highways between City A and City B: " + minimumHighways);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
