package br.com.springboot.curso_jdev_springboot.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.springboot.curso_jdev_springboot.model.Usuario;
import br.com.springboot.curso_jdev_springboot.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
/*responsavel por intercepitar os daddos da requisição*/
@RestController
public class GreetingsController {
	
	
	@Autowired /*injeção de dependencias*/
	private UsuarioRepository usuarioRepository ;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
	/*mapeamento intercepitando a requisição da url*/
    @RequestMapping(value = "/mostra/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(name);
    	usuarioRepository.save(usuario);/*grava no BD*/
    	
    	
        return "Hello " + name + "!";
    }
    
    @RequestMapping(value = "/segundoteste/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String test(@PathVariable String name) {
        return "tudo certo " + name + "!";
    }
    
    /*primeiro metodo da api o lista*/
    
    /*Responseentity serve para retorna uma lista de usuarios (api)*/
    /*estabelendo a anotação*/
    @GetMapping(value = "listatodos")/*primeiro metodo da API (o buscar todos)*/
    @ResponseBody /*retorna os dados para o corpo da resposta*/
	public ResponseEntity<List<Usuario>> listausuario(){
    	
    	/*findall retorna uma lista de usuarios*/
		List<Usuario> usuarios = usuarioRepository.findAll();/*executa consulta no BD*/
	
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*retorna a lista json (automaticamente ira consulta o BD)*/	
    }
    
    /*segundo metodo da api o SALVAR*/
    
    /*envia todos os dados da requisição todos os metodos que preciso*/
    @PostMapping(value = "salvar") 
    @ResponseBody/*descrição das respostas*/
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){/*recebe os dados para salvar*/
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    /*terceiro metodo da api delete*/
    
    
    @DeleteMapping(value = "delete") 
    @ResponseBody/*descrição das respostas*/
    public ResponseEntity<String> delete (@RequestParam Long iduser){/*recebe qual o valor do id para ser excluido*/
    	
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("deletado com sucesso",HttpStatus.OK);
    
    }
    
    
    /*terceiro metodo buscar usuarios*/
    @GetMapping(value = "buscaruserid")
    @ResponseBody/*descrição das respostas*/
    public ResponseEntity<Usuario> buscaruserid( @RequestParam(name = "iduser")long iduser){ /*buscar usuario por id*/
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();/*passar como parametro o codigo buscado usar se um get para saber o que esta sendo buscando*/
    	
    	return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
    }
    
    /*quarto metodo atualizar*/
    
    @PutMapping(value = "atualizar")/*mapear a url*/ 
    @ResponseBody/*descrição das respostas*/
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){/*recebe os dados para atualizar*/
    	if (usuario.getId() <= 0) {
    		return new ResponseEntity<String>("valor de Id invalido", HttpStatus.OK);
			
		} else {
			Usuario user = usuarioRepository.saveAndFlush(usuario);/*saveflush salva e roda diretamente no banco de dados sem finalizar a requisição*/
	    	return new ResponseEntity<Usuario>(user, HttpStatus.OK); 

		}
    }
    
    /*quinto metodo buscar por nome*/
    @GetMapping(value ="buscarpornome")
    @ResponseBody/*descrição da resposta*/
    public ResponseEntity<List<Usuario>> buscarpornome(@RequestParam(name = "name")String name){ /*buscar usuario por nome*/
    	/*o trim serve para retirar espaço caso o usuario digite com, e uppercase passar tudo para maiuscula mudança deve ser feita tanto no get, quanto usuario repository*/
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase()); /*passar como parametro o codigo buscado usar se um get para saber o que esta sendo buscando*/
    	
    	return new ResponseEntity<List<Usuario>>(usuario,HttpStatus.OK);
    }
    	
}
    
    
    
    
    

