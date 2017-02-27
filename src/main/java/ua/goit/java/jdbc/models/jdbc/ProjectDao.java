package ua.goit.java.jdbc.models.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.java.jdbc.models.Entities.Project;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao implements DAO<Project> {
    private DataSource dataSource;
    private final static Logger LOGGER = LoggerFactory.getLogger(ProjectDao.class);

    @Override
    public List<Project> getAll() {
        List<Project> result = new ArrayList<>();
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Successfully conected to DB");
            String sql = "SELECT * FROM projects";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("project_name"));
                project.setCost(resultSet.getFloat("cost"));
                result.add(project);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Project getById(int id) {
        Project project = new Project();
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE id = ?")) {

            LOGGER.info("Successfully conected to DB");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("project_name"));
                project.setCost(resultSet.getFloat("cost"));
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public Project update(int id, String name, float cost) {
        Project project = new Project();
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE projects SET name = ?, cost = ? WHERE id = ?")) {

            LOGGER.info("Successfully conected to DB");
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, cost);
            preparedStatement.setInt(3, id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                project.setId(result.getInt("id"));
                project.setName(result.getString("developer_name"));
                project.setCost(result.getFloat("salary"));
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM projects WHERE id = ?")) {

            LOGGER.info("Successfully conected to DB");
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(Project entity) {
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO projects (project_name, cost) VALUES(?, ?)")) {

            LOGGER.info("Successfully conected to DB");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setFloat(2, entity.getCost());
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
    }

    public boolean addDeveloperTo(int developerId, int projectId) {
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO project_developers (project_id, developer_id) VALUES(?, ?)")) {

            LOGGER.info("Successfully conected to DB");
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, developerId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
    }

    public boolean deleteDeveloperFrom(int developerId, int projectId) {
        LOGGER.info("Connectiong to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM project_developers WHERE project_id = ? AND developer_id = ? ")) {

            LOGGER.info("Successfully conected to DB");
            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, developerId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB " + e);
            throw new RuntimeException(e);
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
