package Services;

import Objects.Planet;

import java.sql.SQLException;
import java.util.List;

public class PlanetService {
    private static PlanetService instance;
    private DAO<Planet> planetDAO;

    private PlanetService(DAO<Planet> planetDAO) {
        this.planetDAO = planetDAO;
    }

    public static synchronized PlanetService getInstance(DAO<Planet> planetDAO) {
        if (instance == null) {
            instance = new PlanetService(planetDAO);
        }
        return instance;
    }

    public void createPlanet(Planet planet) throws SQLException {
        planetDAO.create(planet);
    }

    public Planet readPlanet(int id) throws SQLException {
        return planetDAO.read(id);
    }

    public void updatePlanet(Planet planet, int id) throws SQLException {
        planetDAO.update(planet, id);
    }

    public void deletePlanet(int id) throws SQLException {
        planetDAO.delete(id);
    }

    public List<Planet> getAllPlanets() throws SQLException {
        return planetDAO.getAll();
    }
}
