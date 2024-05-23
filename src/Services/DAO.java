package Services;

import java.sql.SQLException;
import java.util.List;
public interface DAO<T> {
    void create(T t) throws SQLException;

    T read(int id) throws SQLException;
    void update(T t, int id) throws SQLException;
    void delete(int id) throws SQLException;
    List<T> getAll() throws SQLException;

}
