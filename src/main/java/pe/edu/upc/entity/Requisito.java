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

@Entity
@Table(name = "requisitos")
public class Requisito implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRequisito;
	
	@Column(name="documento1",nullable=false)
	private String documento1;
	
	@Column(name="documento2",nullable=false)
	private String documento2;
	
	@Column(name="documento3",nullable=false)
	private String documento3;
	
	@Column(name="documento4",nullable=true)
	private String documento4;
	
	@ManyToOne
	@JoinColumn(name = "idEvaluacion")
	private Evaluacion evaluacion;

	public Requisito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Requisito(int idRequisito, String documento1, String documento2, String documento3, String documento4,
			Evaluacion evaluacion) {
		super();
		this.idRequisito = idRequisito;
		this.documento1 = documento1;
		this.documento2 = documento2;
		this.documento3 = documento3;
		this.documento4 = documento4;
		this.evaluacion = evaluacion;
	}

	public int getIdRequisito() {
		return idRequisito;
	}

	public void setIdRequisito(int idRequisito) {
		this.idRequisito = idRequisito;
	}

	public String getDocumento1() {
		return documento1;
	}

	public void setDocumento1(String documento1) {
		this.documento1 = documento1;
	}

	public String getDocumento2() {
		return documento2;
	}

	public void setDocumento2(String documento2) {
		this.documento2 = documento2;
	}

	public String getDocumento3() {
		return documento3;
	}

	public void setDocumento3(String documento3) {
		this.documento3 = documento3;
	}

	public String getDocumento4() {
		return documento4;
	}

	public void setDocumento4(String documento4) {
		this.documento4 = documento4;
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
	
}
