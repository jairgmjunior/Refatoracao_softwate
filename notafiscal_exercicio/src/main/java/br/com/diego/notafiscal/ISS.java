package br.com.diego.notafiscal;

import java.math.BigDecimal;

public class ISS implements Imposto{

	@Override
	public BigDecimal valorImposto() {
		return BigDecimal.valueOf(0.1);
	}

	
	public BigDecimal valorImposto1() {
		return BigDecimal.valueOf(0.1);
	}
	
}
