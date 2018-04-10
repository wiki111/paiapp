package wikira.paiapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wikira.paiapp.model.User;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@EqualsAndHashCode(exclude = {"user"})
public class ThingDto {

    private Long id;

    private Long userId;

    @NotEmpty
    @NotNull
    private String description;

    @NotNull
    private boolean done;

    @NotNull
    private Date deadline;

    @NotEmpty
    @NotNull
    private String completionCondition;

}
