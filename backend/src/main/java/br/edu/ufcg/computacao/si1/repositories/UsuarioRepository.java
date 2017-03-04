package br.edu.ufcg.computacao.si1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufcg.computacao.si1.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Usuario findByEmail(String email);
}
