package br.edu.ufcg.computacao.si1.services;

import br.edu.ufcg.computacao.si1.exceptions.UserAlreadyExistsException;
import br.edu.ufcg.computacao.si1.exceptions.UserNotFoundException;
import br.edu.ufcg.computacao.si1.models.user.User;
import br.edu.ufcg.computacao.si1.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) throws UserAlreadyExistsException {
        if (!exists(user.getEmail())) {
            return this.userRepository.save(user);
        }

        throw new UserAlreadyExistsException();
    }

    private boolean exists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    public void addCash(long amount, User user) throws UserNotFoundException {
        if (user != null) {
            user.increaseCredit(amount);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public boolean update(User user) {
        if (userRepository.exists(user.getId())) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (userRepository.exists(id)) {
            userRepository.delete(id);
            return true;
        }
        return false;
    }

}
