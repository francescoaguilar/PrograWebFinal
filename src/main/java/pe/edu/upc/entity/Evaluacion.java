package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvaluacion;
	
	@Min(10 / 100)
	@Column(name = "cuotaInicial", nullable = false)
	private double cuotaInicial;
	
	@Min(10 / 100)
	@Column(name = "financiamiento", nullable = false)
	private double financiamiento;
	
	@Past(message = "La fecha debe estar en el pasado")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaEvaluacion", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaEvaluacion;

	@Min(24)
	@Max(36)
	@Column(name = "nroCuotas", nullable = false)
	private int nroCuotas;
	
	@ManyToOne
	@JoinColumn(name = "idPersona")
	private Persona persona;
	
	
	public int getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(int idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public double getCuotaInicial() {
		return cuotaInicial;
	}

	public void setCuotaInicial(double cuotaInicial) {
		this.cuotaInicial = cuotaInicial;
	}

	public double getFinanciamiento() {
		return financiamiento;
	}

	public void setFinanciamiento(double financiamiento) {
		this.financiamiento = financiamiento;
	}

	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}

	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}

	public int getNroCuotas() {
		return nroCuotas;
	}

	public void setNroCuotas(int nroCuotas) {
		this.nroCuotas = nroCuotas;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Evaluacion(int idEvaluacion, double cuotaInicial, double financiamiento, Date fechaEvaluacion, int nroCuotas,
			Persona persona) {
		super();
		this.idEvaluacion = idEvaluacion;
		this.cuotaInicial = cuotaInicial;
		this.financiamiento = financiamiento;
		this.fechaEvaluacion = fechaEvaluacion;
		this.nroCuotas = nroCuotas;
		this.persona = persona;
	}

	public Evaluacion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
