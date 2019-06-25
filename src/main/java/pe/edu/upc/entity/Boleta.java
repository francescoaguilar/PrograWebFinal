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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "boletas") 
public class Boleta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBoleta;
	
	
	@Column(name = "monto", nullable = false)
	private double monto;
	
	@Column(name = "estado", nullable = true)
	private boolean estado;
	
	@Column(name = "nroTransaccion", nullable = false, length = 45, unique = true)
	private String nroTransaccion;
	
	
	@Pattern(regexp = "[0-9]+", message="Solo puede tener números ")
	@Column(name= "nroTarjeta", nullable= true, length=45)
	private String nroTarjeta;
	
	@Pattern(regexp = "[a-zA-Z]+", message="Solo puede tener letras")
	@Column(name="namePersona",nullable=true,length=45)
	private String namePersona;
	
	@Column(name="fechaPago")
	private Date fechaPago;
	
	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}


	@Column(name = "vecespagadas")
	private int vecespagadas;
	
	
	@ManyToOne
	@JoinColumn(name = "idContrato")
	private Contrato contrato;


	public int getVecespagadas() {
		return vecespagadas;
	}


	public void setVecespagadas(int vecespagadas) {
		this.vecespagadas = vecespagadas;
	}


	public Boleta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boleta(int idBoleta,  double monto, boolean estado, String nroTransaccion, String nroTarjeta,
			String namePersona, Date fechaPago, int vecespagadas, Contrato contrato) {
		super();
		this.idBoleta = idBoleta;
		this.monto = monto;
		this.estado = estado;
		this.nroTransaccion = nroTransaccion;
		this.nroTarjeta = nroTarjeta;
		this.namePersona = namePersona;
		this.fechaPago = fechaPago;
		this.vecespagadas = vecespagadas;
		this.contrato = contrato;
	}


	public int getIdBoleta() {
		return idBoleta;
	}


	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}


	public double getMonto() {
		return monto;
	}


	public void setMonto(double monto) {
		this.monto = monto;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public String getNroTransaccion() {
		return nroTransaccion;
	}


	public void setNroTransaccion(String nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}


	public Contrato getContrato() {
		return contrato;
	}


	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNroTarjeta() {
		return nroTarjeta;
	}


	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}


	public String getNamePersona() {
		return namePersona;
	}


	public void setNamePersona(String namePersona) {
		this.namePersona = namePersona;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idBoleta;
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
		Boleta other = (Boleta) obj;
		if (idBoleta != other.idBoleta)
			return false;
		return true;
	}

}
