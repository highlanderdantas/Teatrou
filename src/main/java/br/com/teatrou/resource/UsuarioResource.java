package br.com.teatrou.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.teatrou.config.RestApiController;
import br.com.teatrou.model.Usuario;
import br.com.teatrou.model.dto.UsuarioDTO;
import br.com.teatrou.repository.UsuarioRepository;
import br.com.teatrou.service.UsuarioService;

@RestApiController("usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;


	/**
	 * <p>
	 *  Método que cria um usuário
	 * </p>
	 * @param usuario
	 * @return usuário criado
	 */
	@PostMapping
	public ResponseEntity<Usuario> salvar(@Valid @RequestBody UsuarioDTO usuario) {
		return new ResponseEntity<Usuario>(usuarioService.validarSalvar(usuario), HttpStatus.CREATED);
	}


	/**
	 * <p>
	 *  Busca um usuário em específico
	 * </p>
	 * @param codigo
	 * @return usuário
	 */
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO')")
	public ResponseEntity<Usuario> buscar(@PathVariable(required = true) Long codigo) {
		Usuario usuario = usuarioRepository.findOne(codigo);
		return usuario == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(usuario);
	}


	/**
	 * <p>
	 *  Atualiza um usuário
	 * </p>
	 * @param codigo
	 * @return usuário atualizado
	 */
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_USUARIO')")
	public ResponseEntity<Usuario> alterar(@PathVariable(required = true) Long codigo,
			@Valid @RequestBody Usuario usuario) {
		return new ResponseEntity<>(usuarioService.atualizar(codigo, usuario), HttpStatus.OK);
	}


	/**
	 * <p>
	 *  Deleta um usuário em específico
	 * </p>
	 * @param codigo
	 * @return
	 */
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_EXCLUIR_USUARIO')")
	public ResponseEntity<Usuario> deletar(@PathVariable(required = true) Long codigo) {
		usuarioRepository.delete(codigo);
		return ResponseEntity.noContent().build();
	}

}
