package wikira.paiapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@EqualsAndHashCode(exclude = {"user"})
public class ThingToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String description;
    private boolean done;
    private Date deadline;
    private String completionCondition;

}
