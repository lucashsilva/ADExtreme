package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.computacao.si1.exceptions.UserAlreadyExistsException;
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
    public User create(User user) throws UserAlreadyExistsException {
    	if(!exists(user.getEmail())) {
    		return this.userRepository.save(user);
    	}
    	 
    	throw new UserAlreadyExistsException();
    }

    private boolean exists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	@Override
    public Optional<User> getUser(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUser(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
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
	public boolean autenticar(String email, String password) {
		Optional<User> user = getUser(email);
		
		return user.isPresent() && user.get().authenticate(password);
	}
}
