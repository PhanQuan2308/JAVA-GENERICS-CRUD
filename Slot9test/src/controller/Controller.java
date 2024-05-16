    package controller;

    import entity.Entity;
    import entity.Products;
    import model.Model;

    import java.sql.SQLException;
    import java.util.List;
    import java.util.Objects;

    public class Controller<T extends Entity<?>>     {
        private Model<T> model;

        public Controller(Model<T> model) {
            this.model = model;
        }

        public void create(T entity) {
            try {
                model.create(entity);
            } catch (SQLException e) {
                System.out.println("Error while creating entity: " + e.getMessage());
            }
        }

        public T findById(Class<T> entityClass,int id) {
            try {
                return model.findById(entityClass,id);
            } catch (SQLException e) {
                System.out.println("Error while finding entity by ID: " + e.getMessage());
                return null;
            }
        }

        public void update(T entity) {
            try {
                model.update(entity);
            } catch (SQLException e) {
                System.out.println("Error while updating entity: " + e.getMessage());
            }
        }

        public void delete(Class<T> entityClass,int id) {
            try {
                model.delete(entityClass,id);
            } catch (SQLException e) {
                System.out.println("Error while deleting entity: " + e.getMessage());
            }
        }

        public List<T> showAll(Class<T> entityClass) {
            try {
                return model.showAll(entityClass);
            } catch (SQLException e) {
                System.out.println("Error while fetching all entities: " + e.getMessage());
                return null;
            }
        }
    }

