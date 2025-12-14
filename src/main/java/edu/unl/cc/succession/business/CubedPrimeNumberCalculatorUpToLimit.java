package edu.unl.cc.succession.business;
import edu.unl.cc.succession.domain.Successionable;

/**
 * Representa Serie de primos elevados al cubo  hasta un limite
 * (S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ... + N^3)
 * @autor Jose Salazar
 */

public class CubedPrimeNumberCalculatorUpToLimit implements Successionable{
    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public CubedPrimeNumberCalculatorUpToLimit(Number limit) {
        this(0, limit);
    }

    public CubedPrimeNumberCalculatorUpToLimit(Number start, Number limit) {
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
    public void setLimit(Number limit) {
        if (limit.intValue() < 0){
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        this.limit = limit.intValue();
    }

    @Override
    public Number calculate() {
        long result = 0;

        while (this.currentTerm <= this.limit){
            long cube = (long) Math.pow(this.currentTerm, 3);

            this.printableTerms
                    .append(this.currentTerm)
                    .append("^3 + ");

            result = result + cube;
            this.currentTerm = nextTerm(this.currentTerm).intValue();
        }

        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}
