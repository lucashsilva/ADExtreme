package br.edu.ufcg.computacao.si1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.computacao.si1.exceptions.FailedAuthenticationException;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.models.UserCredentials;
import br.edu.ufcg.computacao.si1.repositories.UserRepository;

@Service
@Transactional
public class AuthenticationService {
	private UserRepository userRepository;
	
	@Autowired
	public AuthenticationService(UserRepository userRepository) {
	   this.userRepository = userRepository;
	}

	public Optional<User> authenticate(UserCredentials credentials) throws FailedAuthenticationException {
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(credentials.getEmail()));

		if (user.isPresent() && user.get().authenticate(credentials.getPassword())) {
			return user;
		}

		throw new FailedAuthenticationException();
	}

}
