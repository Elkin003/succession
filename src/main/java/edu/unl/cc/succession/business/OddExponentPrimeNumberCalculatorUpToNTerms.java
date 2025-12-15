package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Representa el cálculo de la Serie de primos elevados a impares hasta N térmimos
 * S = 2^1 + 3^3 + 5^5 + 7^7 + 11^9 + 13^11 + ...
 *
 * @author Cristian Guaman
 */
public class OddExponentPrimeNumberCalculatorUpToNTerms implements Successionable {
    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public OddExponentPrimeNumberCalculatorUpToNTerms(Number limit) {
        this(0, limit);
    }

    public OddExponentPrimeNumberCalculatorUpToNTerms(Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        currentTerm = nextTerm(start).intValue();
        this.printableTerms = new StringBuilder("S = ");
    }

    private boolean isPrime(Integer number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setLimit(Number limit) {
        if (limit.intValue() <= 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limit.intValue();
    }

    @Override
    public Number nextTerm(Number currentTerm) {
        currentTerm = currentTerm.intValue() + 1;
        boolean isPrime = false;
        while (!isPrime) {
            isPrime = isPrime(currentTerm.intValue());
            if (!isPrime) {
                currentTerm = currentTerm.intValue() + 1;
            }
        }
        return currentTerm;
    }

    @Override
    public Number calculate() {
        long result = 0;
        int counterTerm = 0;
        int exponent = 1;
        int currentTerm = this.currentTerm > 0 ? this.currentTerm - 1 : 0;
        while (counterTerm < limit) {
            currentTerm = nextTerm(currentTerm).intValue();
            this.printableTerms.append(currentTerm).append("^")
                    .append(exponent).append(" + ");
            result = (long) (result + Math.pow(currentTerm, exponent));
            counterTerm++;
            exponent += 2;
        }
        return result;
    }

    @Override
    public String print() {
        if (printableTerms.length() > 4) {
            printableTerms.setLength(printableTerms.length() - 3);
        }
        return printableTerms.toString();
    }
}
