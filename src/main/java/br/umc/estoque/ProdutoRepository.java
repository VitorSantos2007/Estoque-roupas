package br.umc.estoque;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Query Method: busca por parte do nome, sem diferenciar maiúsculas/minúsculas
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
