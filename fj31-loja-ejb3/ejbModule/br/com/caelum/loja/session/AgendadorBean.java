package br.com.caelum.loja.session;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Stateless
@Remote(Agendador.class)
public class AgendadorBean implements Agendador{
 
	@Resource
	TimerService timer;
	@Override
	public void agenda(String expressaoMinutos, String expressaoSegundos) {
		ScheduleExpression expression = new ScheduleExpression();
		expression.hour("*");
		expression.minute(expressaoMinutos);
		expression.second(expressaoSegundos);
		
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo(expression.toString());
		timerConfig.setPersistent(false);
		
		this.timer.createCalendarTimer(expression, timerConfig);
		
		System.out.println("Agendamento: " + expression);		
	}
	
	@Timeout
	public void realizaTransacaoBancaria(Timer timer){
		System.out.println(timer.getInfo());
	}

}
