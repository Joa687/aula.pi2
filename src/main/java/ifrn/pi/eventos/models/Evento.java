package ifrn.pi.eventos.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	@NotBlank
	private String local;
	
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	@NotBlank
	private LocalTime horario;

		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public @NotBlank LocalDate getData() {
		return data;
	}

	public void setData(@NotBlank LocalDate data) {
		this.data = data;
	}

	public @NotBlank LocalTime getHorario() {
		return horario;
	}

	public void setHorario(@NotBlank LocalTime horario) {
		this.horario = horario;
	}

}
