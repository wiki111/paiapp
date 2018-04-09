package wikira.paiapp.repositories;

import org.springframework.data.repository.CrudRepository;
import wikira.paiapp.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByEmail(String email);

}
