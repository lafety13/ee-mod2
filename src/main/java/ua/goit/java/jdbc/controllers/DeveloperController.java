package ua.goit.java.jdbc.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.jdbc.models.Entities.Developer;
import ua.goit.java.jdbc.models.jdbc.DeveloperDao;
import ua.goit.java.jdbc.views.Output;

import java.util.List;

public class DeveloperController {
    private PlatformTransactionManager txtManager;
    private DeveloperDao developerDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public void getAllDevelopers() {
        List<Developer> result = developerDAO.getAll();
        Output.printEntity(result);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void getDeveloperById(int id) {
        Developer developer = developerDAO.getById(id);
        Output.printEntity(developer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(int id, String name, float salary) {
        Developer developer = developerDAO.update(id, name, salary);
        Output.printEntity(developer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(int id) {
        return developerDAO.delete(id);
    }


    public boolean create(Developer entity) {
        return developerDAO.create(entity);
    }

    public void setTxtManager(PlatformTransactionManager txtManager) {
        this.txtManager = txtManager;
    }


    public void setDeveloperDAO(DeveloperDao developerDAO) {
        this.developerDAO = developerDAO;
    }
}
