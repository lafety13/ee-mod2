package ua.goit.java.jdbc.models.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.java.jdbc.models.Entities.Developer;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDao implements DAO<Developer> {
    private DataSource dataSource;
    private final static Logger LOGGER = LoggerFactory.getLogger(DeveloperDao.class);

    public List<Developer> getAll() {
        List<Developer> result = new ArrayList<>();
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Successfully conected to DB");
            String sql = "    SELECT developers.id, developer_name, project_name, salary FROM project_developers\n" +
                         "    RIGHT JOIN developers ON project_developers.developer_id = developers.id\n" +
                         "    LEFT JOIN projects ON project_developers.project_id = projects.id";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Developer developers = new Developer();
                developers.setId(resultSet.getInt("id"));
                developers.setName(resultSet.getString("developer_name"));
                developers.setSalary(resultSet.getFloat("salary"));
                developers.setProject(resultSet.getString("project_name"));
                result.add(developers);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public Developer getById(int id) {
        Developer developer = new Developer();
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM developers WHERE id = ?")) {

            LOGGER.info("Successfully conected to DB");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                developer.setId(result.getInt("id"));
                developer.setName(result.getString("developer_name"));
                developer.setSalary(result.getFloat("salary"));
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
        return developer;
    }

    public Developer update(int id, String name, float salary) {
        Developer developer = new Developer();
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE developers SET name = ?, salary = ? WHERE id = ?")) {

            LOGGER.info("Successfully conected to DB");
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, salary);
            preparedStatement.setInt(3, id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                developer.setId(result.getInt("id"));
                developer.setName(result.getString("developer_name"));
                developer.setSalary(result.getFloat("salary"));
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
        return developer;
    }

    public boolean delete(int id) {
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM developers WHERE id = ?")) {

            LOGGER.info("Successfully conected to DB");
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
    }

    public boolean create(Developer entity) {
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO developers (developer_name, salary) VALUES(?, ?)")) {

            LOGGER.info("Successfully conected to DB");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setFloat(2, entity.getSalary());
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}
