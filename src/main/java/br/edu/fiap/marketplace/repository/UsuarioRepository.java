package br.edu.fiap.marketplace.repository;

import br.edu.fiap.marketplace.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/** TODO adicionar as consultas derivadas necessárias ao cadastro e ao login. */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
