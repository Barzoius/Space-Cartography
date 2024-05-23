import java.sql.*;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;

//import java.util.*;
public class Main {
    public static void main(String[] args) {

        try{
            //String url = "jdbc:mysql://127.9.0.1:3306/spacecartography";
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.9.0.1:3306/spacecartography",
                    "root",
                    "barzoius");

//            PlanetDAO planetDAO = new PlanetDAO(connection);
//            PlanetService planetService = PlanetService.getInstance(planetDAO);
//
//            Planet vn = new Planet("Venus", 5.97, 12.2, false, new Coordinates(0,0,0));
//            planetService.createPlanet(vn);
//
//            Planet id1 = planetService.readPlanet(1);
//            System.out.println(id1);
//            Planet vn2 = new Planet("Venus2", 6.97, 22.2, false, new Coordinates(0,0,0));
//            planetService.updatePlanet(vn2, 2);
//
//            List<Planet> planete = planetService.getAllPlanets();
//
//            planetService.deletePlanet(2);
//
//            List<Planet> planete2 = planetService.getAllPlanets();

            StarDAO starDAO = new StarDAO(connection);
            StarService starService = StarService.getInstance(starDAO);
            System.out.println("DDADADAADAD:");
            starService.readStar(3);
//            Star st = new Star("SUN", 1322.23, 254.2, new Coordinates(0,0,0));
//            starService.createStar(st);
//            Star st2 = new Star("SUN2", 1322.23, 254.2, new Coordinates(0,0,0));
//            starService.createStar(st2);
//            LocalDateTime currentDateTime = LocalDateTime.now();
//            Instant instant = Instant.now() ;
//            System.out.println(instant);
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//            String formattedDateTime = currentDateTime.format(formatter);


            System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM planet");

            while(resultSet.next()){
                System.out.println(resultSet.getString("Name"));
                System.out.println(resultSet.getDouble("Diameter"));
                System.out.println(resultSet.getDouble("GravPull"));
                System.out.println(resultSet.getBoolean("PossLife"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }



        ServiceClass service = new ServiceClass();

        Planet planet1 = new Planet("Earth", 31241, 13.2, true, new Coordinates(12.2, 32.3, 0.0));
        Planet planet2 = new Planet("Mars", 52241, 6.2, false, new Coordinates(24.2, 21.3, 0.0));
        Planet planet3 = new Planet("Jupiter", 2231241, 32.2, false, new Coordinates(37.2, 31.3, 0.0));


        Satellite sat1 = new Satellite("Moon", 3212, 4.4, new Coordinates(12.1, 30.3, 0.0));
        Satellite sat2 = new Satellite("Titan", 1232, 1.2, new Coordinates(23.2, 12.3, 0.0));
        Satellite sat3 = new Satellite("Eres", 7332.2, 5.0, new Coordinates(25.2, 14.3, 0.0));


        PlanetSystem planetSystem1 = new PlanetSystem("Earth's system");
        planetSystem1.setPlanet(planet1);
        planetSystem1.addSatellite(sat1);

        PlanetSystem planetSystem2 = new PlanetSystem("Mars's system");
        planetSystem2.setPlanet(planet2);

        PlanetSystem planetSystem3 = new PlanetSystem("Jupiter's system");
        planetSystem3.setPlanet(planet3);
        planetSystem3.addSatellite(sat2);
        planetSystem3.addSatellite(sat3);

        Star star1 = Star.createStar("Sun", 321213.32, 453.9, new Coordinates(78.2, 98.2, 0.0));
        StarSystem starSystem1 = new StarSystem("SolarSystem");
        starSystem1.setStar(star1);
        starSystem1.addPlanetSystem(planetSystem1);
        starSystem1.addPlanetSystem(planetSystem2);
        starSystem1.addPlanetSystem(planetSystem3);


        Planet planet4 = new Planet("Voros", 131241, 33.2, true, new Coordinates(12.2, 32.3, 0.0));

        PlanetSystem planetSystem4 = new PlanetSystem("Voros's system");
        planetSystem4.setPlanet(planet4);

        Star star2 = Star.createStar("X56F", 211323.2, 31.2, new Coordinates(23.1, 21.2, 0.0));


        StarSystem starSystem2 = new StarSystem("NobuSystem");
        starSystem2.setStar(star2);
        starSystem2.addPlanetSystem(planetSystem4);

        Star dwarfStar = Star.createStar("FistDwarf", 231.2, 12.2, new Coordinates(21.2, 3.2, 0.0));

        service.m_planetsArray.add(planet1);
        service.m_planetsArray.add(planet2);
        service.m_planetsArray.add(planet3);
        service.m_planetsArray.add(planet4);

        service.m_satellitesArray.add(sat1);
        service.m_satellitesArray.add(sat2);
        service.m_satellitesArray.add(sat3);

        service.m_planetsystemsArray.add(planetSystem1);
        service.m_planetsystemsArray.add(planetSystem2);
        service.m_planetsystemsArray.add(planetSystem3);
        service.m_planetsystemsArray.add(planetSystem4);

        service.m_starsArray.add(star1);
        service.m_starsArray.add(star2);
        service.m_starsArray.add(dwarfStar);

        service.m_starSystemList.add(starSystem1);
        service.m_starSystemList.add(starSystem2);


        SpaceShip ROCKET = new SpaceShip("ROCKET", 4121.23, 213.32);
        ROCKET.setDestinationCoordinates(planet1.getCoordinates()); //Destination = Earth
        ROCKET.setSpeed(ROCKET.getSpeed() + 12); // update speed


        Coordinates test_coords = new Coordinates(12.2, 32.2, 0.0);
        service.polarizeCoordinates(test_coords);

        //computeDistance() arunca o eroare dar nu o tratez
        //service.computeDistance(planet1.getCoordinates(), planet4.getCoordinates());

        service.aproxTimeToDestination(ROCKET, planet4.getCoordinates());

        service.isInArea(starSystem1, ROCKET);

        service.sustainablePlanets();

        service.aproxTimeToDestination(ROCKET, planet4.getCoordinates());

        System.out.println("-------------------");
        service.showPlanets();
        System.out.println("-------------------");
        service.showSatellites();
        System.out.println("-------------------");
        service.showPlanetSystems();
        System.out.println("-------------------");
        service.showStars();
        System.out.println("-------------------");
        service.showStarSystems();

        //pritare info obiect
        System.out.println(ROCKET);


        //MENU
   //     MenuClass menu = MenuClass.getInstance();
   //     menu.processChoice();

    }
}