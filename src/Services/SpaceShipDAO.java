package Services;

import Services.DAO;

import Objects.Coordinates;
import Objects.SpaceShip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceShipDAO implements DAO<SpaceShip> {

    private Connection connection;

    public SpaceShipDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(SpaceShip spaceShip) throws SQLException{
        String sql = "INSERT INTO spaceship" + "(Name, Speed, DirAngle, Distance) VALUES" + "(?, ?, ?, ?);";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, spaceShip.getName());
            preparedStatement.setDouble(2, spaceShip.getSpeed());
            preparedStatement.setDouble(3, spaceShip.getDirectionAngle());
            preparedStatement.setDouble(4, spaceShip.getDistance());
            preparedStatement.executeUpdate();
        }
    }

    private void SelectActionInsert()throws SQLException
    {
        String callProcedureSql = "CALL spaceship_select();";

        try (CallableStatement callStmt = connection.prepareCall(callProcedureSql))
        {
            callStmt.execute();

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override

    public SpaceShip read(int id) throws SQLException {
        String sql = "SELECT * FROM SpaceShip WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            SelectActionInsert();

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new SpaceShip(rs.getString("Name"),
                                     rs.getDouble("Speed"),
                                     rs.getDouble("DirAngle"));

            }
        }
        return null;
    }


    @Override
    public void update(SpaceShip spaceShip, int id) throws SQLException {
        String sql = "UPDATE spaceship SET name = ?, speed = ?, dirAngle = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, spaceShip.getName());
            pstmt.setDouble(2, spaceShip.getSpeed());
            pstmt.setDouble(3, spaceShip.getDirectionAngle());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {

            e.printStackTrace();
            //connection.rollback();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Spaceship WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<SpaceShip> getAll() throws SQLException {
        List<SpaceShip> spaceShips = new ArrayList<>();
        String sql = "SELECT * FROM Spaceship";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                spaceShips.add(new SpaceShip(
                        rs.getString("name"),
                        rs.getDouble("speed"),
                        rs.getDouble("dirAngle")));
            }
        }
        return spaceShips;
    }
}
