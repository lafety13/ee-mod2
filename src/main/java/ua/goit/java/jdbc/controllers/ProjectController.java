package ua.goit.java.jdbc.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.jdbc.models.Entities.Project;
import ua.goit.java.jdbc.models.jdbc.ProjectDao;
import ua.goit.java.jdbc.views.Output;

import java.util.List;

public class ProjectController {
    private PlatformTransactionManager txtManager;
    private ProjectDao projectDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public void getAllProjects() {
        List<Project> result = projectDAO.getAll();
        Output.printEntity(result);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void getProjectById(int id) {
        Project result = projectDAO.getById(id);
        Output.printEntity(result);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update() {

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void create(Project entity) {
        projectDAO.create(entity);
        Output.printEntity("Developer was successfully created");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        projectDAO.delete(id);
        Output.printEntity("Developer was successfully deleted");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDeveloperTo(int developerId, int projectId) {
        projectDAO.addDeveloperTo(developerId, projectId);
        Output.printEntity("Developer was successfully added");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDeveloperFrom(int developerId, int projectId) {
        projectDAO.deleteDeveloperFrom(developerId, projectId);
        Output.printEntity("Developer was successfully deleted from project");
    }

    public void setTxtManager(PlatformTransactionManager txtManager) {
        this.txtManager = txtManager;
    }

    public void setProjectDAO(ProjectDao projectDAO) {
        this.projectDAO = projectDAO;
    }
}
