package br.com.diego.notafiscal;

import java.math.BigDecimal;

public class ICMS implements Imposto{

	@Override
	public BigDecimal valorImposto() {
		return BigDecimal.valueOf(0.11);
	}

}
