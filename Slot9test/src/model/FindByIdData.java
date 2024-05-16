package model;

import dao.DbConnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.reflect.Field;

public class FindByIdData {

    public <T> T findById(Class<T> entityClass, int id) {
        String tableName = getTableName(entityClass);

        Field[] fields = entityClass.getDeclaredFields();
        if (fields.length == 0) {
            throw new IllegalArgumentException("No fields found in entity class");
        }

        String idColumnName = fields[0].getName();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ");
        queryBuilder.append(tableName).append(" WHERE ").append(idColumnName).append(" = ?");

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(queryBuilder.toString())) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    T entity = entityClass.getDeclaredConstructor().newInstance();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        field.set(entity, resultSet.getObject(field.getName()));
                    }
                    return entity;
                }
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTableName(Class<?> entityClass) {
        String tableName = entityClass.getSimpleName();
        return tableName;
    }
}
