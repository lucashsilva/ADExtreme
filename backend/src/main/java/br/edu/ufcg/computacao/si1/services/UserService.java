package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.exceptions.UserAlredyExistException;
import br.edu.ufcg.computacao.si1.models.User;

public interface UserService {
    User create(User usuario) throws UserAlredyExistException;

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getUsers();

    boolean update(User usuario);

    boolean delete(Long id);

	boolean autenticar(String email, String senha);
}
