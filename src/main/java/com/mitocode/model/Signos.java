package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="signos")
public class Signos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSignos;
	
	@ManyToOne //(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="id_paciente", nullable= false, foreignKey = @ForeignKey(name ="FK_signos_paciente"))
	private Paciente paciente;
	
	@Column(name ="fecha")
	private LocalDateTime fecha;
	
	@Column(name="temperatura", nullable = false, length = 10)
	private String temperatura;
	
	@Column(name="pulso", nullable= false, length = 10)
	private String pulso;
	
	@Column(name="ritmo", nullable= false, length = 10)
	private String ritmo;

	public Integer getIdSignos() {
		return idSignos;
	}

	public void setId(Integer idSignos) {
		this.idSignos = idSignos;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getPulso() {
		return pulso;
	}

	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	public String getRitmo() {
		return ritmo;
	}

	public void setRitmo(String ritmo) {
		this.ritmo = ritmo;
	}
	
}
