package ToDoBackEndApplication.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@Document(collection = "todos")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class ToDo {
    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String title;

    private String description;

    @NotNull(message = "El userId no puede ser nulo") // Aseguramos que siempre haya un userId
    private String userId; // Este es el enlace al usuario

    private Boolean completed = false;

    private Date createdAt = new Date();

    public ToDo() {
        super();
    }

    @Override
    public String toString() {
        return String.format(
                "ToDo[id=%s, title='%s', completed='%s', userId='%s']",
                id, title, completed, userId);
    }
}
