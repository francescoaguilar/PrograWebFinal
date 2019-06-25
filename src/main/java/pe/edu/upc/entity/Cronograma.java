package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;
public class Cronograma implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int idCronograma;//
	

	private double seguroDegrav;//
	
	private double Saldo;
	private double Cuota;
	public double getCuota() {
		return Cuota;
	}

	public void setCuota(double cuota) {
		Cuota = cuota;
	}

	private double Envio;
	
	private double seguroVeh;
	
	private double amortizacion;
	
	private Evaluacion Evaluacion;
	
	private Contrato Contrato;
	
	public Evaluacion getEvaluacion() {
		return Evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		Evaluacion = evaluacion;
	}

	public Contrato getContrato() {
		return Contrato;
	}

	public void setContrato(Contrato contrato) {
		Contrato = contrato;
	}

	public double getSaldo() {
		return Saldo;
	}

	public void setSaldo(double saldo) {
		Saldo = saldo;
	}

	public double getEnvio() {
		return Envio;
	}

	public void setEnvio(double envio) {
		Envio = envio;
	}


	private Date fechaFacturacion;
	
	private double interes;
	
	public Cronograma() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Cronograma(int idCronograma, int seguroDegrav , int seguroVeh,
			double amortizacion,Date fechaFacturacion, int interes) {
		super();
		this.idCronograma = idCronograma;
		this.seguroDegrav = seguroVeh;
		this.seguroVeh = seguroVeh;
		this.amortizacion= amortizacion;
		this.fechaFacturacion = fechaFacturacion;
		this.interes=interes;
	}

	public int getIdCronograma() {
		return idCronograma;
	}

	public void setIdCronograma(int idCronograma) {
		this.idCronograma = idCronograma;
	}

	public double getSeguroDegrav() {
		return seguroDegrav;
	}

	public void setSeguroDegrav(double seguroDegrav) {
		this.seguroDegrav = seguroDegrav;
	}

	public double getSeguroVeh() {
		return seguroVeh;
	}

	public void setSeguroVeh(double seguroVeh) {
		this.seguroVeh = seguroVeh;
	}

	public double getAmortizacion() {
		return amortizacion;
	}

	public void setAmortizacion(double amortizacion) {
		this.amortizacion = amortizacion;
	}

	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}


	


}