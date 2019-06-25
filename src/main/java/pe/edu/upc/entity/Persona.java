package pe.edu.upc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "personas")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersona;
	
	@NotNull(message="ingrese el nombre de la persona")
	@Column(name = "nombrePersona", length = 45)
	private String nombrePersona;
	
	@Past(message = "La fecha debe estar en el pasado")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaNacimiento",nullable = true)//
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private	Date fechaNacimiento;
	
	@NotEmpty(message="Ingrese el DNI")
	@Column(name = "dniPersona", nullable = false, length = 45, unique = true)
	private String dniPersona;
	
	@NotNull(message = "La fecha es obligatoria")
	@Column(name = "direccionPersona", nullable = true, length = 75)
	private String direccionPersona;
	
	@Digits(fraction = 9, integer = 9)
	@Column(name = "telefonoPersona", nullable = true, length = 8)
	private int telefonoPersona;
	
	@Email(message= "ingrese un correo valido")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@Column(name = "emailCliente", nullable = false)//emailPersona
	private String emailPersona;

	@Column(name = "contraseña", nullable = true)
	private String contraseña;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipoPersona")
	private TipoPersona tipoPersona;

	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Persona(int idPersona, String nombrePersona, Date fechaNacimiento, String dniPersona,
			String direccionPersona, int telefonoPersona, String emailPersona, String contraseña, TipoPersona tipoPersona) {
		super();
		this.idPersona = idPersona;
		this.nombrePersona = nombrePersona;
		this.fechaNacimiento = fechaNacimiento;
		this.dniPersona = dniPersona;
		this.direccionPersona = direccionPersona;
		this.telefonoPersona = telefonoPersona;
		this.emailPersona = emailPersona;
		this.contraseña = contraseña;
		this.tipoPersona = tipoPersona;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDniPersona() {
		return dniPersona;
	}

	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}

	public String getDireccionPersona() {
		return direccionPersona;
	}

	public void setDireccionPersona(String direccionPersona) {
		this.direccionPersona = direccionPersona;
	}

	public int getTelefonoPersona() {
		return telefonoPersona;
	}

	public void setTelefonoPersona(int telefonoPersona) {
		this.telefonoPersona = telefonoPersona;
	}

	public String getEmailPersona() {
		return emailPersona;
	}

	public void setEmailPersona(String emailPersona) {
		this.emailPersona = emailPersona;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dniPersona == null) ? 0 : dniPersona.hashCode());
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
		Persona other = (Persona) obj;
		if (dniPersona == null) {
			if (other.dniPersona != null)
				return false;
		} else if (!dniPersona.equals(other.dniPersona))
			return false;
		return true;
	}

	
	
}
