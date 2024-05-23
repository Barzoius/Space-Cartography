import java.sql.SQLException;
import java.util.List;

public class SatelliteService {
    private static SatelliteService instance;
    private DAO<Satellite> satelliteDAO;

    private SatelliteService(DAO<Satellite> satelliteDAO) {
        this.satelliteDAO = satelliteDAO;
    }
    public static synchronized SatelliteService getInstance(DAO<Satellite> satelliteDAO) {
        if (instance == null) {
            instance = new SatelliteService(satelliteDAO);
        }
        return instance;
    }

    public void createSatellite(Satellite satellite) throws SQLException {
        satelliteDAO.create(satellite);
    }

    public Satellite readSatellite(int id) throws SQLException {
        return satelliteDAO.read(id);
    }

    public void updateSatellite(Satellite satellite, int id) throws SQLException {
        satelliteDAO.update(satellite, id);
    }

    public void deleteSatellite(int id) throws SQLException {
        satelliteDAO.delete(id);
    }

    public List<Satellite> getAllSatellites() throws SQLException {
        return satelliteDAO.getAll();
    }
}
