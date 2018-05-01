package br.com.teatrou.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "evento")
public @Data @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull(message = "Codigo do usuario é obrigatorio.")
	@ManyToOne
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;
	
	@NotNull
	private String titulo;
	
	@NotNull
	private String descricao;
	
	@NotNull
	@Column(name = "data_evento")
	private LocalDate dataEvento;
	
	@NotNull
	@Column(name = "hora_inicial")
	private String horaInicial;
	
	@NotNull
	@Column(name = "hora_final")
	private String horaFinal;
	
	@NotNull
	private String tema;
	
	@NotNull
	private String endereco;
	
	@NotNull
	@Column(name = "quantidade_ingresso")
	private Integer quantidadeIngresso;

	@NotNull
	@Column(name = "valor_ingresso")
	private BigDecimal valorIngresso;
	
	@NotNull
	private @Getter(AccessLevel.NONE) Boolean ativo;
	
	
}
