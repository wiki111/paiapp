package wikira.paiapp.repositories;

import org.springframework.data.repository.CrudRepository;
import wikira.paiapp.model.User;

//TODO refactor

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}
