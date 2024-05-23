package Services;

import Objects.SpaceShip;

import java.sql.SQLException;
import java.util.List;

public class SpaceShipService {
    private static SpaceShipService instance;
    private DAO<SpaceShip> spaceShipDAO;

    private SpaceShipService(DAO<SpaceShip> spaceShipDAO) {
        this.spaceShipDAO = spaceShipDAO;
    }

    public static synchronized SpaceShipService getInstance(DAO<SpaceShip> spaceShipDAO) {
        if (instance == null) {
            instance = new SpaceShipService(spaceShipDAO);
        }
        return instance;
    }

    public void createSpaceShip(SpaceShip spaceShip) throws SQLException {
        spaceShipDAO.create(spaceShip);
    }

    public SpaceShip readSpaceShip(int id) throws SQLException {
        return spaceShipDAO.read(id);
    }

    public void updateSpaceShip(SpaceShip spaceShip, int id) throws SQLException {
        spaceShipDAO.update(spaceShip, id);
    }

    public void deleteSpaceShip(int id) throws SQLException {
        spaceShipDAO.delete(id);
    }

    public List<SpaceShip> getAllSpaceships() throws SQLException {
        return spaceShipDAO.getAll();
    }
}
