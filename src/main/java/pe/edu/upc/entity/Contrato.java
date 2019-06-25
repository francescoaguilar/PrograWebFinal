package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name= "contratos")
public class Contrato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idContrato;
	
	@NotNull(message = "La fecha es obligatoria")
	@Past(message = "La fecha debe estar en el pasado")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaContrato")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaContrato;
	
	@Min(15000)
	@Column(name = "valordelVehiculo", nullable = false)
	private double valordelVehiculo;
	
	@Min(10 / 100)
	@Column(name = "tasaMora", nullable = false)
	private double tasaMora;
	
	@Min(10 / 100)
	@Column(name = "tasaEfectivaAnual", nullable = false)
	private double tasaEfectivaAnual;
	
	@Min(10 / 100)
	@Column(name = "tasaSeguroDegravamen", nullable = false)
	private double tasaSeguroDegravamen;
	
	@Min(10 / 100)
	@Column(name = "tasaSeguroVehicular", nullable = false)
	private double tasaSeguroVehicular;
	
	@OneToOne
	@JoinColumn(name= "idEvaluacion")
	private Evaluacion evaluacion;


	public Contrato() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Contrato(int idContrato, Date fechaContrato, double valordelVehiculo, double tasaMora,
			double tasaEfectivaAnual, double tasaSeguroDegravamen, double tasaSeguroVehicular, Evaluacion evaluacion) {
		super();
		this.idContrato = idContrato;
		this.fechaContrato = fechaContrato;
		this.valordelVehiculo = valordelVehiculo;
		this.tasaMora = tasaMora;
		this.tasaEfectivaAnual = tasaEfectivaAnual;
		this.tasaSeguroDegravamen = tasaSeguroDegravamen;
		this.tasaSeguroVehicular = tasaSeguroVehicular;
		this.evaluacion = evaluacion;
	}


	public int getIdContrato() {
		return idContrato;
	}


	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}


	public Date getFechaContrato() {
		return fechaContrato;
	}


	public void setFechaContrato(Date fechaContrato) {
		this.fechaContrato = fechaContrato;
	}


	public double getValordelVehiculo() {
		return valordelVehiculo;
	}


	public void setValordelVehiculo(double valordelVehiculo) {
		this.valordelVehiculo = valordelVehiculo;
	}


	public double getTasaMora() {
		return tasaMora;
	}


	public void setTasaMora(double tasaMora) {
		this.tasaMora = tasaMora;
	}


	public double getTasaEfectivaAnual() {
		return tasaEfectivaAnual;
	}


	public void setTasaEfectivaAnual(double tasaEfectivaAnual) {
		this.tasaEfectivaAnual = tasaEfectivaAnual;
	}


	public double getTasaSeguroDegravamen() {
		return tasaSeguroDegravamen;
	}


	public void setTasaSeguroDegravamen(double tasaSeguroDegravamen) {
		this.tasaSeguroDegravamen = tasaSeguroDegravamen;
	}


	public double getTasaSeguroVehicular() {
		return tasaSeguroVehicular;
	}


	public void setTasaSeguroVehicular(double tasaSeguroVehicular) {
		this.tasaSeguroVehicular = tasaSeguroVehicular;
	}


	public Evaluacion getEvaluacion() {
		return evaluacion;
	}


	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idContrato;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrato other = (Contrato) obj;
		if (idContrato != other.idContrato)
			return false;
		return true;
	}
	
	
}
