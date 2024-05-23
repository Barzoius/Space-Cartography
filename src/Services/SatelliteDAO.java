package Services;

import Objects.Coordinates;
import Objects.Satellite;
import Services.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SatelliteDAO implements DAO<Satellite> {

    private Connection connection;

    public SatelliteDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(Satellite satellite) throws SQLException {
        String sql = "INSERT INTO Satellite" + "(Name, Diameter, Gravpull) VALUES" + "(?, ?, ?);";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, satellite.getName());
            preparedStatement.setDouble(2, satellite.getDiameter());
            preparedStatement.setDouble(3, satellite.getGravitationalPull());
            preparedStatement.executeUpdate();
        }
    }

    private void SelectActionInsert()throws SQLException
    {
        String callProcedureSql = "CALL satellite_select();";

        try (CallableStatement callStmt = connection.prepareCall(callProcedureSql))
        {
            callStmt.execute();

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public Satellite read(int id) throws SQLException {
        String sql = "SELECT * FROM Satellite WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            SelectActionInsert();

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Satellite(rs.getString("Name"),
                        rs.getDouble("Diameter"),
                        rs.getDouble("GravPull"),
                        new Coordinates(0,0,0) );
            }
        }
        return null;
    }


    @Override
    public void update(Satellite satellite, int id) throws SQLException {
        String sql = "UPDATE Satellite SET name = ?, diameter = ?, gravpull = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, satellite.getName());
            pstmt.setDouble(2, satellite.getDiameter());
            pstmt.setDouble(3, satellite.getGravitationalPull());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Satellite WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Satellite> getAll() throws SQLException {
        List<Satellite> satellites = new ArrayList<>();
        String sql = "SELECT * FROM Satellite";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                satellites.add(new Satellite(
                        rs.getString("name"),
                        rs.getDouble("diameter"),
                        rs.getDouble("gravpull"),
                        new Coordinates(0,0,0)));
            }
        }
        return satellites;
    }
}
