
import static util.Logger.log;

import java.math.BigInteger;

import org.junit.Test;

public class OverflowTutor {
	/**
	 * Перемножает 2 числа, возбуждая ArithmeticException в случае переполнения
	 */
	public Integer multiply(int a, int b) {
		BigInteger value = BigInteger.valueOf(a).
			multiply(BigInteger.valueOf(b));
		if ((value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE))==-1) 
			|| (value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE))==1)) {
		    throw new ArithmeticException("Integer overflow: actual multuply result="+value.toString());
		  }
		return value.intValue();
	}
	
	@Test
	public void testOverflow() {
		int i1 = 34524235;
		int i2 = 23423423;
		int overflowed = i1*i2;
		log("i1*i2="+overflowed);
		try {
			multiply(i1, i2);
		} catch(ArithmeticException e) {
			log(e.getMessage());
		}
	}
	
	@Test
	public void testSum() {
		Double d = Double.POSITIVE_INFINITY*10;
		log("doubleMultiply="+d);
	}

}
