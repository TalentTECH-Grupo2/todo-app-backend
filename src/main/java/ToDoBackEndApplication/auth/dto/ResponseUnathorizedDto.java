package ToDoBackEndApplication.auth.dto;

import lombok.*;

@AllArgsConstructor
@Data
@ToString
@Builder
public class ResponseUnathorizedDto {

    private String message;
    private int status;
}
