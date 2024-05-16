package model;

import dao.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

public class ShowAllData {

    public <T> List<T> showAllData(Class<T> entityClass) {
        String tableName = getTableName(entityClass);

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ");
        queryBuilder.append(tableName);

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(queryBuilder.toString())) {

            try (ResultSet resultSet = statement.executeQuery()) {
                List<T> entities = new ArrayList<>();
                while (resultSet.next()) {
                    T entity = entityClass.newInstance();
                    Field[] fields = entityClass.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        field.set(entity, resultSet.getObject(field.getName()));
                    }
                    entities.add(entity);
                }
                return entities;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTableName(Class<?> entityClass) {
        String tableName = entityClass.getSimpleName();
        return tableName;
    }
}
