package br.com.caelum.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class CalculadorDeParcela {
	@WebMethod
	public double calculaParcela(
			@WebParam(name = "valorTotal") double valorTotal,
			@WebParam(name = "quantidade") int quantidade) {
		double oValorTotal = 0;
		if (quantidade < 0)
			oValorTotal = 0;
		else {
			oValorTotal = valorTotal * (1 + (quantidade / 100.0));
		}
		return oValorTotal;
	}
}
