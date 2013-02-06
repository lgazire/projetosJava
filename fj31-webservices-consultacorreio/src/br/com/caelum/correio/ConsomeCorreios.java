package br.com.caelum.correio;

import java.math.BigDecimal;
import java.util.List;

import br.com.caelum.correios.ws.CResultado;
import br.com.caelum.correios.ws.CServico;
import br.com.caelum.correios.ws.CalcPrazo;
import br.com.caelum.correios.ws.CalcPrecoPrazoWS;
import br.com.caelum.correios.ws.CalcPrecoPrazoWSSoap;

public class ConsomeCorreios {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalcPrecoPrazoWSSoap cliente = new CalcPrecoPrazoWS().getCalcPrecoPrazoWSSoap();
		
		CResultado resultado = cliente.calcPrecoPrazo("", "", "40010,41106", "06709095", "01013001", "20", 2, new BigDecimal(19), new BigDecimal(10), new BigDecimal(15), new BigDecimal(10), "S", BigDecimal.ZERO, "S");
		List<CServico> servico = resultado.getServicos().getCServico();
		for (CServico cServico : servico) {
			System.out.println(cServico.getCodigo());
			System.out.println(cServico.getValor());
		}

	}

}
