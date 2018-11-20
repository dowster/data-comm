package net.dowster.school.datacomm.program3;

import java.math.BigInteger;

/**
 * Static methods to perform math operations on big integers.
 */
public class BigIntegerMath
{
   /**
    * Computes the factorial of a BigInteger
    * @param initial to factorize
    * @return factorial of initial
    */
   public static BigInteger fac(BigInteger initial) {
      BigInteger factorial = BigInteger.ONE;

      while (!initial.equals(BigInteger.ZERO))
      {
         factorial = factorial.multiply(initial);
         initial = initial.subtract(BigInteger.ONE);
      }

      return factorial;
   }
}
