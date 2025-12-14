package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Serie de primos elevados a la raiz de numeros pares hasta un limite
 * (S = 1^(1/2) + 3^(1/4) + 5^(1/6) + 7^(1/8) + 11^(1/10) + 13^(1/12) ... + N):
 * @author Leonel Lima (LMess)
 */
public class EvenRootPrimeNumberCalculatorUpToLimit implements Successionable {

    private Integer currentTerm;
    private Integer limit;
    private StringBuilder printableTerms;

    public EvenRootPrimeNumberCalculatorUpToLimit(Number limit) {
        this(0,limit);
    }

    public EvenRootPrimeNumberCalculatorUpToLimit(Number start, Number limit) {
        if (start.intValue() < 0) {
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        this.currentTerm = start.intValue();
        this.printableTerms = new StringBuilder("S = ");
    }

    private Boolean isPrime(Integer number) {
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
        if (limit.intValue() < 0){
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limit.intValue();
    }

    @Override
    public Number nextTerm(Number currentTerm) {
        currentTerm = currentTerm.intValue() + 1;
        while (!isPrime(currentTerm.intValue())){
            currentTerm = currentTerm.intValue() + 1;
        }
        return currentTerm;
    }

    @Override
    public Number calculate() {
        double result = 0.0;
        int counterTerm = 0;
        int currentTerm = this.currentTerm > 0 ? this.currentTerm : 2;

        while (counterTerm < limit) {
            currentTerm = nextTerm(currentTerm).intValue();
            int evenRootExponent = (counterTerm * 2) + 2;
            this.printableTerms.append(currentTerm).append("^(1/").append(evenRootExponent).append(")");
            if (counterTerm < limit - 1) {
                this.printableTerms.append(" + ");
            }
            result += Math.pow(currentTerm, 1.0 / evenRootExponent);
            counterTerm++;
        }
        return result;
    }


    @Override
    public String print() {
        return printableTerms.toString();
    }

}
