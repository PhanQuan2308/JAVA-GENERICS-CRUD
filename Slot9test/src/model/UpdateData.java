package model;

import dao.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.lang.reflect.Field;

public class UpdateData {

    public <T> boolean update(T entity) {
        String tableName = getTableName(entity.getClass());

        StringBuilder queryBuilder = new StringBuilder("UPDATE ");
        queryBuilder.append(tableName).append(" SET ");

        Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            if (i > 1) {
                queryBuilder.append(", ");
            }
            queryBuilder.append(fields[i].getName()).append(" = ?");
        }

        queryBuilder.append(" WHERE ").append(fields[0].getName()).append(" = ?");

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(entity);
                statement.setObject(parameterIndex++, value);
            }
            fields[0].setAccessible(true);
            Object idValue = fields[0].get(entity);
            statement.setObject(parameterIndex, idValue);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getTableName(Class<?> entityClass) {
        String tableName = entityClass.getSimpleName();
        return tableName;
    }
}
