package wikira.paiapp.services;

import wikira.paiapp.dto.UserDto;
import wikira.paiapp.model.User;

public interface IUserService {
    User registerNewAccount(UserDto accountDto);
    User findByUsername(String username);
    User findById(Long id);
}
