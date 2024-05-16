package model;

import dao.DbConnection;
import entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Model<T extends Entity<?>> {
    private final InsertData insertData = new InsertData();
    private final UpdateData updateData = new UpdateData();
    private final DeleteData deleteData = new DeleteData();
    private final FindByIdData findByIdData = new FindByIdData();
    private final ShowAllData showAllData = new ShowAllData();
    private Connection conn;



    public Model() {
        try {
            this.conn = DbConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(T entity) throws SQLException {
        insertData.insert(entity);
    }

    public void update(T entity) throws SQLException {
        updateData.update(entity);
    }

    public void delete(Class<T> entityClass, int id) throws SQLException {
        deleteData.delete(entityClass,id);
    }

    public T findById(Class<T> entityClass, int id) throws SQLException {
        return findByIdData.findById(entityClass, id);
    }


    public List<T> showAll(Class<T> entityClass) throws SQLException {
        return showAllData.showAllData(entityClass);
    }

}
