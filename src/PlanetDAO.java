import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PlanetDAO implements DAO<Planet> {

    private Connection connection;

    public PlanetDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(Planet planet) throws SQLException{
        String sql = "INSERT INTO planet" + "(Name, Diameter, Gravpull, posslife) VALUES" + "(?, ?, ?, ?);";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, planet.getName());
            preparedStatement.setDouble(2, planet.getDiameter());
            preparedStatement.setDouble(3, planet.getGravitationalPull());
            preparedStatement.setBoolean(4, planet.getPossibleLife());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Planet read(int id) throws SQLException {
        String sql = "SELECT * FROM Planet WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Planet(rs.getString("Name"),
                                  rs.getDouble("Diameter"),
                                  rs.getDouble("GravPull"),
                                  rs.getBoolean("PossLife"),
                                  new Coordinates(0,0,0) );
            }
        }
        return null;
    }


    @Override
    public void update(Planet planet, int id) throws SQLException {
        String sql = "UPDATE Planet SET name = ?, diameter = ?, gravpull = ?, posslife = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, planet.getName());
            pstmt.setDouble(2, planet.getDiameter());
            pstmt.setDouble(3, planet.getGravitationalPull());
            pstmt.setBoolean(4, planet.getPossibleLife());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Planet WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Planet> getAll() throws SQLException {
        List<Planet> planets = new ArrayList<>();
        String sql = "SELECT * FROM Planet";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                planets.add(new Planet(
                                       rs.getString("name"),
                                       rs.getDouble("diameter"),
                                       rs.getDouble("gravpull"),
                                       rs.getBoolean("posslife"),
                                       new Coordinates(0,0,0)));
            }
        }
        return planets;
    }
}
