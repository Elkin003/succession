package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Representa el cálculo de la Serie de primos elevados a la raiz cuadrada hasta un límite
 * S = 1^(1/2) + 3^(1/2) + 5^(1/2) + 7^(1/2) + 11^(1/2) + 13^(1/2)+ ... + N^(1/2)
 * @author Elkin Jiménez
 */
public class SquareRootPrimeNumberCalculatorUpToLimit implements Successionable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public SquareRootPrimeNumberCalculatorUpToLimit(Number limit) {
        this(0,  limit);
    }

    public SquareRootPrimeNumberCalculatorUpToLimit(Number start, Number limit) {
        if (start.intValue() < 0){
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        this.currentTerm = nextTerm(start).intValue();
        this.printableTerms = new StringBuilder("S = ");
    }

    private Boolean isPrime(Integer number){
        for (int i = 2 ; i < number; i++){
            if (number % i == 0){
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
        boolean isPrime = false;
        while (!isPrime){
            isPrime = isPrime(currentTerm.intValue());
            if (!isPrime){
                currentTerm = currentTerm.intValue() + 1;
            }
        }
        return currentTerm;
    }

    @Override
    public Number calculate() {
        double result = 0;
        while (this.currentTerm <= this.limit){
            this.printableTerms.append(this.currentTerm).append("^(1/2) + ");
            result = result + Math.pow(this.currentTerm, 1.0/2.0);
            this.currentTerm = nextTerm(this.currentTerm).intValue();
        }
        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}