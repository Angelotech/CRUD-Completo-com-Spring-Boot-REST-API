package br.com.springboot.curso_jdev_springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_jdev_springboot.model.Usuario;

/*extendesse para outra interface, parametos passados são o usuario e tipo da chave*/


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	/*incrementação para buscar pelo nome na lista*/
	/* para salvar no bd*/
	/*u para usuario assim apontando para tabela, o like serve para pesquisar por partes, o ? e a quantidadde de parametros*/
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> buscarPorNome(String name);

}
