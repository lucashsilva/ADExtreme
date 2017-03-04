package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufcg.computacao.si1.exceptions.UsuarioJaExisteException;
import br.edu.ufcg.computacao.si1.models.Usuario;
import br.edu.ufcg.computacao.si1.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario create(Usuario usuario) throws UsuarioJaExisteException {
    	if(!exists(usuario.getEmail())) {
    		return this.usuarioRepository.save(usuario);
    	}
    	 
    	throw new UsuarioJaExisteException();
    }

    private boolean exists(String email) {
		return usuarioRepository.findByEmail(email) != null;
	}

	@Override
    public Optional<Usuario> getUsuario(Long id) {
        return Optional.ofNullable(usuarioRepository.findOne(id));
    }

    @Override
    public Optional<Usuario> getUsuario(String email) {
        return Optional.ofNullable(usuarioRepository.findByEmail(email));
    }

    @Override
    public Collection<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public boolean update(Usuario usuario) {
        if (usuarioRepository.exists(usuario.getId())) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (usuarioRepository.exists(id)) {
            usuarioRepository.delete(id);
            return true;
        }
        return false;
    }

	@Override
	public boolean autenticar(String email, String senha) {
		Optional<Usuario> usuario = getUsuario(email);
		
		return usuario.isPresent() && usuario.get().autenticar(senha);
	}
}
