package br.edu.ufcg.computacao.si1.services;

import br.edu.ufcg.computacao.si1.exceptions.UserAlreadyExistsException;
import br.edu.ufcg.computacao.si1.models.user.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    User create(User usuario) throws UserAlreadyExistsException;

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getUsers();

    boolean update(User usuario);

    boolean delete(Long id);

}
