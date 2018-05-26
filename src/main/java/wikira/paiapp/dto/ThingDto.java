package wikira.paiapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wikira.paiapp.model.User;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@EqualsAndHashCode(exclude = {"user"})
public class ThingDto {

    private Long id;

    private Long userId;

    @NotEmpty
    @NotNull
    @Size(min=2, max=512)
    private String description;

    @NotNull
    @NotEmpty
    private boolean done;

    @NotNull
    @NotEmpty
    private Date deadline;

    @NotEmpty
    @NotNull
    private String completionCondition;

}
