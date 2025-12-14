package edu.unl.cc;

import edu.unl.cc.succession.business.*;
import edu.unl.cc.succession.domain.Successionable;

import java.util.Scanner;

/**
 * @author Elkin Jiménez
 */
public class Main {

    private static void printMenu() {
        System.out.println("CALCULADORA DE SERIES");
        System.out.println("1. Serie de números pares hasta un limite (S = 2 + 4 + 6 + 8 + ... N): ");
        System.out.println("2. Serie de primos elevados al cubo  hasta un limite (S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ... + N^3): ");
        System.out.println("4. Serie de primos elevados por pares hasta un limite (S = 1^2 + 3^4 + 5^6 + 7^8 + 11^10 + 13^(12) ... + N): ");
        System.out.println("5.  Serie de primos elevados a impares hasta n térmimos (S = S = 1^1 + 3^3 + 5^5 + 7^7 + 11^9 + 13^11 ..): ");
        System.out.println("7. Serie de primos elevados a la raiz de numeros impares hasta un n términos (S = 1^(1/1) + 3^(1/3) + 5^(1/5) + 7^(1/7) + 11^(1/9) + 13^(1/11)): ");
        System.out.println("10. Serie de primos hasta un limite (S = 1 + 2 + 3 + 5 + 7 + 11 + 13 + .. + N: ");
    }

    private static int readOption(Scanner scanner) {
        //validaciones poner
        int option;
        printMenu();
        System.out.print("Elija la opción de la serie que desea calcular: ");
        option = scanner.nextInt();
        return option;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = readOption(scanner);
        System.out.print("Debe ingresar un límite/N términos: ");
        int limit =  scanner.nextInt();
        scanner.nextLine();
        Successionable successionable = null;
        switch (option) {
            case 1:
                successionable = new EvenNumberCalculatorUpToLimit(limit);
                break;

            case 2:
                //ToDO
                break;
            case 4:
                successionable = new EvenExponentPrimeNumberCalculatorUpToLimit(limit);
                break;
            case 5:
                successionable = new OddExponentPrimeNumberCalculatorUpToNTerms(limit);
                break;

            case 7:
                successionable = new PrimeNumberWithPowCalculatorWithTerm(limit);
                break;

            case 10:
                successionable = new PrimeNumberCalculatorUpToLimit(limit);
                break;

            default:
                System.out.println("Opción Invalida");
        }
        if (successionable != null) {
            Number result = successionable.calculate();
            System.out.println(successionable.print());
            System.out.println("S = " + result + "\n");
        }
    }

}