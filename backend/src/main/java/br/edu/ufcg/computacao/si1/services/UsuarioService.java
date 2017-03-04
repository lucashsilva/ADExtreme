package br.edu.ufcg.computacao.si1.services;

import java.util.Collection;
import java.util.Optional;

import br.edu.ufcg.computacao.si1.exceptions.UsuarioJaExisteException;
import br.edu.ufcg.computacao.si1.models.Usuario;

public interface UsuarioService {
    Usuario create(Usuario usuario) throws UsuarioJaExisteException;

    Optional<Usuario> getUsuario(Long id);

    Optional<Usuario> getUsuario(String email);

    Collection<Usuario> getUsuarios();

    boolean update(Usuario usuario);

    boolean delete(Long id);

	boolean autenticar(String email, String senha);
}
