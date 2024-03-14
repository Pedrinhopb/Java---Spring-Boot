package br.ecommerce.pedro.fase1javaspring.repositorio;

import br.ecommerce.pedro.fase1javaspring.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository <Produto, Long> {
}
