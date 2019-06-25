package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tipopersonas")
public class TipoPersona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoPersona;
	
	@NotEmpty(message = "Ingrese el nombre")
	@Column(name="",nullable=false,length=45,unique=true)
	private String nombre;

	public TipoPersona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoPersona(int idTipoPersona, String nombre) {
		super();
		this.idTipoPersona = idTipoPersona;
		this.nombre = nombre;
	}

	public int getIdTipoPersona() {
		return idTipoPersona;
	}

	public void setIdTipoPersona(int idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoPersona;
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
		TipoPersona other = (TipoPersona) obj;
		if (idTipoPersona != other.idTipoPersona)
			return false;
		return true;
	}


	
}
