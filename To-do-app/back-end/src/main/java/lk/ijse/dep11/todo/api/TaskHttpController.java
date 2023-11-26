package lk.ijse.dep11.todo.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lk.ijse.dep11.todo.to.TaskTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskHttpController {

    private HikariDataSource pool;
    public TaskHttpController() {
        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("9674");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/todo_app");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.addDataSourceProperty("maximumPoolSize",10);
        pool = new HikariDataSource(config);
    }

    @PreDestroy
    public void destroy(){
        pool.close();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public TaskTO createTask(@RequestBody @Validated(TaskTO.create.class) TaskTO task){
        try(Connection connection = pool.getConnection()) {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO task (description, status) VALUES (?,false)",
                    Statement.RETURN_GENERATED_KEYS);
            stm.setString(1,task.getDescription());
            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            task.setId(id);
            task.setStatus(false);
            return task;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<TaskTO> getTask(){
        try(Connection connection = pool.getConnection()) {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM task");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}", consumes = "application/json")
    public void editTask(@PathVariable int id,
                         @RequestBody @Validated(TaskTO.update.class) TaskTO task){
        try(Connection connection = pool.getConnection()) {
            // Business validation
            PreparedStatement stmExist = connection.prepareStatement("SELECT * FROM task WHERE id = ?");
            stmExist.setInt(1,id);
            if (!stmExist.executeQuery().next()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The task not found");
            }
            PreparedStatement stm = connection.prepareStatement("UPDATE task SET description = ?, status = ? WHERE id = ?");
            stm.setString(1,task.getDescription());
            stm.setBoolean(2, task.getStatus());
            stm.setInt(3,id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id){
        System.out.println("delete a task");
    }
}
