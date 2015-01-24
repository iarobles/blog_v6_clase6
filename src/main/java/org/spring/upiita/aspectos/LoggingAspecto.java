package org.spring.upiita.aspectos;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspecto {
	//definimos nuestros consejos como simples metodos
	
	public void antesDeInvocar(JoinPoint joinPoint){		
		System.out.println("antes de invocar metodo:" + joinPoint.getSignature().getName());
		
		System.out.println("argumentos del punto de corte:" + Arrays.asList(joinPoint.getArgs()));
	}
	
	public void  despuesDeInvocar(){
		System.out.println("despues de invocar metodo");
	}
	
	public Object alrededor(ProceedingJoinPoint joinPoint) throws Throwable{
		Object resultado = null;
		
		System.out.println("antes de invocar(alrededor)");
		//invoca al punto de corte en el cual este consejo se haya aplicado
		//en nuestro caso va invocar ArticuloDAO.buscaPorTitulo()
		joinPoint.proceed();		
		System.out.println("despues de invocar(alrededor)");
		
		return resultado;
	}

}
