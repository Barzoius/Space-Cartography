package Services;

import Services.DAO;
import Objects.Coordinates;
import Objects.Star;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;


public class StarDAO implements DAO<Star> {

    private Connection connection;

    public StarDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(Star star) throws SQLException{
        String sql = "INSERT INTO star" + "(Name, Diameter, Gravpull) VALUES" + "(?, ?, ?);";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, star.getName());
            preparedStatement.setDouble(2, star.getDiameter());
            preparedStatement.setDouble(3, star.getGravitationalPull());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {

            e.printStackTrace();
            //connection.rollback();
        }
    }

    private void SelectActionInsert()throws SQLException
    {
        String callProcedureSql = "CALL star_select();";

        try (CallableStatement callStmt = connection.prepareCall(callProcedureSql))
        {
            callStmt.execute();

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override

    public Star read(int id) throws SQLException {
        String sql = "SELECT * FROM Star WHERE id = ?";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)
        ) {
            SelectActionInsert();

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Star(rs.getString("Name"),
                        rs.getDouble("Diameter"),
                        rs.getDouble("GravPull"),
                        new Coordinates(0,0,0));
            }

            //SelectActionInsert();

        } catch (SQLException e) {

            e.printStackTrace();
            //connection.rollback();
        }
        return null;
    }


    @Override
    public void update(Star star, int id) throws SQLException {
        String sql = "UPDATE Star SET name = ?, diameter = ?, gravpull = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, star.getName());
            pstmt.setDouble(2, star.getDiameter());
            pstmt.setDouble(3, star.getGravitationalPull());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();

        }catch (SQLException e) {

            e.printStackTrace();
            //connection.rollback();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Star WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {

            e.printStackTrace();
            //connection.rollback();
        }
    }

    @Override
    public List<Star> getAll() throws SQLException {
        List<Star> stars = new ArrayList<>();
        String sql = "SELECT * FROM Star";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                stars.add(new Star(
                        rs.getString("name"),
                        rs.getDouble("diameter"),
                        rs.getDouble("gravpull"),
                        new Coordinates(0,0,0)));
            }

        }catch (SQLException e) {

            e.printStackTrace();
            //connection.rollback();
        }
        return stars;
    }
}
