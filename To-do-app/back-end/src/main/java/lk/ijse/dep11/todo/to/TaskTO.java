package lk.ijse.dep11.todo.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskTO implements Serializable {
    @Null(message = "Id should be empty")
    private Integer id;
    @NotBlank (message = "Description shouldn't be empty")
    private String description;
    @Null (message = "Status should be empty",groups = create.class)
    @NotNull(message = "Status shouldn't be empty",groups = update.class)
    private Boolean status;

    public interface update extends Default {}
    public interface create extends Default {}
}
