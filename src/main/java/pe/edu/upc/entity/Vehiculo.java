package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "vehiculos")
public class Vehiculo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVehiculo;
	
	@NotEmpty(message="Ingrese el modelo")
	@Column(name="modelo",nullable=false,length=45)
	private String modelo;
	
	@NotEmpty(message="Ingrese la marca")
	@Column(name="marca",nullable=false,length=45)
	private String marca;
	
	@NotEmpty(message="Ingrese la placa")
	@Column(name="placa",nullable=false,length=45)
	private String placa;
	
	@NotEmpty(message="Ingrese el color")
	@Column(name="color",nullable=false,length=45)
	private String color;
	
	@NotEmpty(message="Ingrese el proveedor")
	@Column(name="proveedor",nullable=false,length=45)
	private String proveedor;

	private String foto;
	@ManyToOne
	@JoinColumn(name = "idEvaluacion")
	private Evaluacion evaluacion;
	
	

	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Vehiculo(int idVehiculo, @NotEmpty(message = "Ingrese el modelo") String modelo,
			@NotEmpty(message = "Ingrese la marca") String marca, @NotEmpty(message = "Ingrese la placa") String placa,
			@NotEmpty(message = "Ingrese el color") String color,
			@NotEmpty(message = "Ingrese el proveedor") String proveedor, String foto, Evaluacion evaluacion) {
		super();
		this.idVehiculo = idVehiculo;
		this.modelo = modelo;
		this.marca = marca;
		this.placa = placa;
		this.color = color;
		this.proveedor = proveedor;
		this.foto = foto;
		this.evaluacion = evaluacion;
	}


	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idVehiculo;
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
		Vehiculo other = (Vehiculo) obj;
		if (idVehiculo != other.idVehiculo)
			return false;
		return true;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
