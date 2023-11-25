package lk.ijse.dep11.todo.api;

import lk.ijse.dep11.todo.to.TaskTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskHttpController {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TaskTO createTask(@RequestBody String description){
        System.out.println("create task");
        return new TaskTO(1,"Created",true);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<TaskTO> getTask(){
        System.out.println("get all task");
        return new ArrayList<>();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void editTask(@PathVariable String id, @RequestBody Map<String,String> params){
        System.out.println("edit a task");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id){
        System.out.println("delete a task");
    }
}
