package edu.unl.cc.succession.business;
import edu.unl.cc.succession.domain.Successionable;

/**
 * Serie de primos elevados al cubo  hasta N términos (
 * S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ...)
 * @autor Jose Salazar
 */

public class CubedPrimeNumberCalculatorUpToNTerms implements Successionable {
    private Integer currentTerm;
    private Integer limit;
    private StringBuilder printableTerms;

    public CubedPrimeNumberCalculatorUpToNTerms(Number limit) {
        this(0, limit);
    }

    public CubedPrimeNumberCalculatorUpToNTerms(Number start, Number limit) {
        if (start.intValue() < 0){
            throw new IllegalArgumentException("Start must be greater than 0");
        }
        setLimit(limit);
        this.currentTerm = start.intValue();
        this.printableTerms = new StringBuilder("S = ");
    }

    private Boolean isPrime(Integer number){
        if (number < 2) {
            return false;
        }
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
        while (!isPrime(currentTerm.intValue())){
            currentTerm = currentTerm.intValue() + 1;
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
        double result = 0.0;
        int counterTerm = 0;
        int currentTerm = this.currentTerm > 0 ? this.currentTerm - 1 : 0;

        while (counterTerm < limit){
            currentTerm = nextTerm(currentTerm).intValue();

            this.printableTerms
                    .append(currentTerm)
                    .append("^3 + ");

            result += Math.pow(currentTerm, 3);
            counterTerm++;
        }

        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}
