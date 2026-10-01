package br.edu.fiap.marketplace.repository;

import br.edu.fiap.marketplace.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

/** TODO adicionar consultas por usuário e status do carrinho. */
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
