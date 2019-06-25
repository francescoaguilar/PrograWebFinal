package pe.edu.upc.repository;

import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Evaluacion;
import pe.edu.upc.entity.Contrato;
import pe.edu.upc.entity.Cronograma;
import pe.edu.upc.repository.EvaluacionRepository;
import pe.edu.upc.repository.ContratoRepository;

import java.lang.Math;

@Repository
public class CronogramaRepository {

	@Autowired
	public EvaluacionRepository eR;
	@Autowired
	public ContratoRepository cR;

	private double CalcInteresMensual(double tea, double S1, double dias) {
		double tna = (Math.pow(1 + tea, 1 / 12.0) - 1) * (12 * 365) / 360;
		double i = (tna * dias) / 365;
		double im = S1 * i;
		return im;
	}

	private double CalcSegurDegravamen(double td, int dias, double S1) {
		double d = (td * 12 * dias) / 365;
		double Sd = S1 * d;
		return Sd;
	}

	private double CalcSegurVehicular(double tv, int dias, double valVehiculo) {
		double n = (tv * dias) / 365;
		double Sv = valVehiculo * n;
		return Sv;
	}

	private double CalcAmortizacion(double Ci, double interesi) {
		return Ci - interesi;
	}

	private double Ci(double S, double i, double n) {
		double ci = S * (i / ((1 - Math.pow((1.0 + i), -n))));
		return ci;
	}

	public List<Cronograma> findAll() {

		Evaluacion eval = eR.findAll().get(0);
		Contrato contr = cR.findAll().get(0);
		List<Cronograma> l = new ArrayList<Cronograma>();
		int i = 0;
		double tea = contr.getTasaEfectivaAnual() / 100;
		double cuotaInicial = eval.getCuotaInicial();
		double tasa_degrav = contr.getTasaSeguroDegravamen() / 100;
		Date fechaFacturacion = eval.getFechaEvaluacion();
		double tasa_veh = contr.getTasaSeguroVehicular() / 100;
		double valor_venta = contr.getValordelVehiculo();
		double Saldoini = valor_venta - cuotaInicial;
		double FinancC = eval.getFinanciamiento();
		double cuotaFija = 0;
		double cuotaFinal = valor_venta - (cuotaInicial + FinancC);
		int tiempo = 31;
		int ncuotas = eval.getNroCuotas();
		double amortizacion = CalcAmortizacion(
				Ci(FinancC, CalcInteresMensual(tea, tiempo, Saldoini) / Saldoini, ncuotas),
				CalcInteresMensual(tea, tiempo, FinancC));
		Cronograma mes1 = new Cronograma();
		mes1.setSaldo(Math.round(Saldoini * 100.0) / 100.0);
		l.add(mes1);
		while (i <= ncuotas) {
			Cronograma e = new Cronograma();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaFacturacion);			
			
			e.setFechaFacturacion(calendar.getTime());
			
			calendar.add(Calendar.MONTH, 1);
			fechaFacturacion = calendar.getTime();	
			// Calculo de Dias
			Date fechaFinal = calendar.getTime();
			calendar.add(Calendar.MONTH, -1);
			Date fechaInicial = calendar.getTime();
			
			int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
			tiempo=dias;
			
			
			if (i == 0) {
			e.setIdCronograma(i+1);			
			e.setSeguroDegrav(Math.round(CalcSegurDegravamen(tasa_degrav, tiempo, Saldoini) * 100.0) / 100.0);
			e.setSeguroVeh(Math.round(CalcSegurVehicular(tasa_veh, tiempo, valor_venta) * 100.0) / 100.0);
			e.setInteres(Math.round(CalcInteresMensual(tea, tiempo, Saldoini) * 100.0) / 100.0);
			e.setEnvio(10);
				e.setAmortizacion(Math.round(amortizacion * 100.0) / 100.0);
				e.setSaldo(Math.round((Saldoini-e.getAmortizacion()) * 100.0) / 100.0);
				Saldoini=e.getSaldo();
				e.setCuota(
						e.getInteres() + e.getSeguroDegrav() + e.getSeguroVeh() + e.getAmortizacion() + e.getEnvio());
				cuotaFija = e.getCuota();
			} else {
				e.setIdCronograma(i+1);			
				e.setSeguroDegrav(Math.round(CalcSegurDegravamen(tasa_degrav, tiempo, Saldoini) * 100.0) / 100.0);
				e.setSeguroVeh(Math.round(CalcSegurVehicular(tasa_veh, tiempo, valor_venta) * 100.0) / 100.0);
				e.setInteres(Math.round(CalcInteresMensual(tea, tiempo, Saldoini) * 100.0) / 100.0);
				e.setEnvio(10);
				if (i != ncuotas ) {
					e.setCuota(Math.round(cuotaFija * 100.0) / 100.0);
					amortizacion = cuotaFija - (e.getInteres() + e.getSeguroDegrav() + e.getSeguroVeh() + e.getEnvio());
					e.setAmortizacion(Math.round(amortizacion * 100.0) / 100.0);
					e.setSaldo(Math.round((Saldoini-e.getAmortizacion()) * 100.0) / 100.0);
					Saldoini=e.getSaldo();
				} else {
					e.setCuota(Math.round(cuotaFinal * 100.0) / 100.0);
					amortizacion = cuotaFinal- (e.getInteres() + e.getSeguroDegrav() + e.getSeguroVeh() + e.getEnvio());
					e.setAmortizacion(Math.round(amortizacion * 100.0) / 100.0);
					e.setSaldo(Math.round((Saldoini-e.getAmortizacion()) * 100.0) / 100.0);
					Saldoini=e.getSaldo();
				}
			}
			l.add(e);
			i++;
		}
		return l;
	}

}