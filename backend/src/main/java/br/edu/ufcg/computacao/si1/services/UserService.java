package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.exceptions.UserAlreadyExistsException;
import br.edu.ufcg.computacao.si1.models.User;

public interface UserService {
    User create(User usuario) throws UserAlreadyExistsException;

    Optional<User> getUser(Long id);

    Optional<User> getUser(String email);

    Collection<User> getUsers();

    boolean update(User usuario);

    boolean delete(Long id);

	boolean autenticar(String email, String senha);
}
