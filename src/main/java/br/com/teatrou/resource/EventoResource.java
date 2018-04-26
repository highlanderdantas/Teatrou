package br.com.teatrou.resource;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teatrou.model.Evento;
import br.com.teatrou.repository.EventoRepository;
import br.com.teatrou.repository.filter.EventoFilter;
import br.com.teatrou.service.EventoService;

@RestController
@RequestMapping(value = "/evento")
public class EventoResource {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private EventoRepository eventoRepository;

	@GetMapping
	public ResponseEntity<Page<Evento>> listar(EventoFilter eventoFilter, Pageable pageable) {
		return new ResponseEntity<>(eventoRepository.filtrar(eventoFilter, pageable), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Evento> salvar(@Valid @RequestBody Evento evento) {
		return new ResponseEntity<Evento>(eventoService.salvar(evento), HttpStatus.CREATED);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Evento> buscar(@PathVariable(required = true) Long codigo) {
		Evento evento = eventoRepository.findOne(codigo);
		return evento == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(evento);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Evento> delete(@PathVariable(required = true) Long codigo) {
		eventoRepository.delete(codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Evento> alterar(@PathVariable(required = true) Long codigo,
			@Valid @RequestBody Evento evento) {
		return new ResponseEntity<>(eventoService.atualizar(codigo, evento), HttpStatus.OK);
	}

	@PutMapping("/{codigo}/data-pagamento")
	public ResponseEntity<Evento> alterarDataPagamento(@PathVariable(required = true) Long codigo,
			@RequestBody LocalDate dataEvento) {
		return new ResponseEntity<>(eventoService.atualizarDataEvento(codigo, dataEvento), HttpStatus.OK);
	}

	@PutMapping("/{codigo}/ativar")
	public ResponseEntity<Evento> alterarDataPagamento(@PathVariable(required = true) Long codigo) {
		return new ResponseEntity<>(eventoService.ativarEvento(codigo), HttpStatus.OK);
	}

	@PutMapping("/{codigo}/descricao")
	public ResponseEntity<Evento> alterarDescricao(@PathVariable(required = true) Long codigo,
			@Valid @RequestBody String descricao) {
		return new ResponseEntity<>(eventoService.atualizarDescricao(codigo, descricao), HttpStatus.OK);
	}

}
