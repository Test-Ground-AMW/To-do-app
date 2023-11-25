package lk.ijse.dep11.app.to;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskHttpController {
    @PostMapping
    public void createTask(){
        System.out.println("create task");
    }

    @GetMapping
    public void getTask(){
        System.out.println("get all task");
    }

    @PatchMapping("{id}")
    public void editTask(){
        System.out.println("edit a task");
    }

    @DeleteMapping("{id}")
    public void deleteTask(){
        System.out.println("delete a task");
    }
}
