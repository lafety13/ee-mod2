package ua.goit.java.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.java.jdbc.controllers.DeveloperController;
import ua.goit.java.jdbc.controllers.ProjectController;
import ua.goit.java.jdbc.models.Entities.Developer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private DeveloperController employeeController = new DeveloperController();
    private ProjectController projectController = new ProjectController();

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Main main = context.getBean(Main.class);
        main.start();
    }

    private void start() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        while (true) {
            String line = reader.readLine();
            if (line.equals("get")) {
                System.out.println("enter the index");
                int id = Integer.parseInt(reader.readLine());
                employeeController.getDeveloperById(id);
            } else if (line.equals("show")) {
                employeeController.getAllDevelopers();
            } else if (line.equals("add")) {
                System.out.println("enter the name and salary");
                String name = reader.readLine();
                float salary = Float.parseFloat(reader.readLine());
                System.out.println("you should enter 'save' to save");
                String save = reader.readLine();
                if (save.equals("save")) {
                    Developer developer = new Developer(name, salary);
                    employeeController.create(developer);
                }

            } else if (line.equals("delete")) {
                System.out.println("enter the index");
                int id = Integer.parseInt(reader.readLine());
                employeeController.delete(id);
            } else if (line.equals("update")) {
                int id = Integer.parseInt(reader.readLine());
                String name = reader.readLine();
                float salary = Float.parseFloat(reader.readLine());
                employeeController.update(id, name, salary);
            } else if (line.equals("add to project")) {
                System.out.println("enter the index of developer");
                int developerId = Integer.parseInt(reader.readLine());
                System.out.println("enter the index of project");
                int projectId = Integer.parseInt(reader.readLine());
                projectController.addDeveloperTo(developerId, projectId);
            } else if (line.equals("delete from project")) {
                System.out.println("enter the index of developer");
                int developerId = Integer.parseInt(reader.readLine());
                System.out.println("enter the index of project");
                int projectId = Integer.parseInt(reader.readLine());
                projectController.deleteDeveloperFrom(developerId, projectId);
            } else if (line.equals("show projects")) {
                projectController.getAllProjects();
            }

        }

    }

    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }

    public void setEmployeeController(DeveloperController employeeController) {
        this.employeeController = employeeController;
    }
}
