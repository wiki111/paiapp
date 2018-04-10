package wikira.paiapp.repositories;

import org.springframework.data.repository.CrudRepository;
import wikira.paiapp.model.ThingToDo;

import java.util.Optional;

//TODO refactor

public interface ThingRepository extends CrudRepository<ThingToDo, Long>{

    Optional<ThingToDo> findByUser_Id(Long id);

}
