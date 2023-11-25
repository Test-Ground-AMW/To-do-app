package lk.ijse.dep11.todo.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lk.ijse.dep11.todo.to.TaskTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public TaskTO createTask(@RequestBody TaskTO task){
        System.out.println("create task");
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<TaskTO> getTask(){
        System.out.println("get all task");
        return new ArrayList<>();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}", consumes = "application/json")
    public void editTask(@PathVariable String id, @RequestBody TaskTO task){
        System.out.println("edit a task");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id){
        System.out.println("delete a task");
    }
}