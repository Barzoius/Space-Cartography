package Services;

import java.sql.SQLException;
import java.util.List;

public class StarService {
    private static StarService instance;
    private DAO<Star> starDAO;

    private StarService(DAO<Star> starDAO) {
        this.starDAO = starDAO;
    }

    public static synchronized StarService getInstance(DAO<Star> starDAO) {
        if (instance == null) {
            instance = new StarService(starDAO);
        }
        return instance;
    }

    public void createStar(Star star) throws SQLException {
        starDAO.create(star);
    }

    public Star readStar(int id) throws SQLException {
        return starDAO.read(id);
    }

    public void updateStar(Star star, int id) throws SQLException {
        starDAO.update(star, id);
    }

    public void deleteStar(int id) throws SQLException {
        starDAO.delete(id);
    }

    public List<Star> getAllStars() throws SQLException {
        return starDAO.getAll();
    }
}
