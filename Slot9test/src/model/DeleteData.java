package model;

import dao.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.lang.reflect.Field;

public class DeleteData {

    public <T> boolean delete(Class<T> entityClass, int id) {
        String tableName = getTableName(entityClass);

        Field[] fields = entityClass.getDeclaredFields();
        if (fields.length == 0) {
            throw new IllegalArgumentException("No fields found in entity class");
        }

        String idColumnName = fields[0].getName();

        StringBuilder queryBuilder = new StringBuilder("DELETE FROM ");
        queryBuilder.append(tableName).append(" WHERE ").append(idColumnName).append(" = ?");

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(queryBuilder.toString())) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getTableName(Class<?> entityClass) {
        String tableName = entityClass.getSimpleName();
        return tableName;
    }
}
