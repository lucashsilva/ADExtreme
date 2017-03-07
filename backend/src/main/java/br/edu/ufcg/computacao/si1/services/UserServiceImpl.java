package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.computacao.si1.exceptions.UserAlredyExistException;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) throws UserAlredyExistException {
    	if(!exists(user.getUsername())) {
    		return this.userRepository.save(user);
    	}
    	 
    	throw new UserAlredyExistException();
    }

    private boolean exists(String username) {
		return userRepository.findByUsername(username) != null;
	}

	@Override
    public Optional<User> getUser(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUser(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
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

	@Override
	public boolean autenticate(String username, String password) {
		Optional<User> user = getUser(username);
		
		return user.isPresent() && user.get().authenticate(password);
	}
}
