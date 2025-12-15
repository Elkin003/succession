package edu.unl.cc;

import edu.unl.cc.succession.business.*;
import edu.unl.cc.succession.domain.Successionable;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Elkin Jiménez
 */
public class Main {

    private static void printMenu() {
        System.out.println("CALCULADORA DE SERIES");
        System.out.println("1. Serie de números pares hasta un límite (S = 2 + 4 + 6 + 8 + ... N): ");
        System.out.println("2. Serie de primos elevados al cubo hasta un límite (S = 2^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ... + N^3): ");
        System.out.println("3. Serie de primos elevados al cubo hasta N términos (S = 2^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ...): ");
        System.out.println("4. Serie de primos elevados por pares hasta un límite (S = 2^2 + 3^4 + 5^6 + 7^8 + 11^10 + 13^(12) ... + N): ");
        System.out.println("5. Serie de primos elevados a impares hasta n términos (S = 2^1 + 3^3 + 5^5 + 7^7 + 11^9 + 13^11 ..): ");
        System.out.println("6. Serie de primos elevados a la raíz de números pares hasta un límite (S = 2^(1/2) + 3^(1/4) + 5^(1/6) + 7^(1/8) + 11^(1/10) + 13^(1/12) ... + N): ");
        System.out.println("7. Serie de primos elevados a la raíz de números impares hasta un N términos (S = 2^(1/1) + 3^(1/3) + 5^(1/5) + 7^(1/7) + 11^(1/9) + 13^(1/11)): ");
        System.out.println("8. Serie de primos elevados a la raíz cúbica hasta un n términos (S = 2^(1/3) + 3^(1/3) + 5^(1/3) + 7^(1/3) + 11^(1/3) + 13^(1/3)): ");
        System.out.println("9. Serie de primos elevados a la raíz cuadrada hasta un límite (S = 2^(1/2) + 3^(1/2) + 5^(1/2) + 7^(1/2) + 11^(1/2) + 13^(1/2)+ .. + N^(1/2): ");
        System.out.println("10. Serie de primos hasta un límite (S = 2 + 3 + 5 + 7 + 11 + 13 + .. + N: ");
        System.out.println("0. Salir del programa.");
    }

    private static int readOption(Scanner scanner) {
        int option;
        printMenu();
        System.out.print("Elija una opción: ");
        while (true) {
            try {
                option = scanner.nextInt();
                return option;
            } catch (InputMismatchException e) {
                System.out.print("Debe ingresar un número: ");
                scanner.nextLine();
            }
        }
    }

    private static int readLimit(Scanner scanner) {
        int limit;
        System.out.print("Ingrese el límite/N términos: ");
        while (true) {
            try {
                limit = scanner.nextInt();
                return limit;
            } catch (InputMismatchException e) {
                System.out.print("Debe ingresar un número: ");
                scanner.nextLine();
            }
        }
    }

    private static Successionable createSuccession(int option, int limit) {
        switch (option) {
            case 1:
                return new EvenNumberCalculatorUpToLimit(limit);

            case 2:
                return new CubedPrimeNumberCalculatorUpToLimit(limit);

            case 3:
                return new CubedPrimeNumberCalculatorUpToNTerms(limit);

            case 4:
                return new EvenExponentPrimeNumberCalculatorUpToLimit(limit);

            case 5:
                return new OddExponentPrimeNumberCalculatorUpToNTerms(limit);

            case 6:
                return new EvenRootPrimeNumberCalculatorUpToLimit(limit);

            case 7:
                return new PrimeNumberWithPowCalculatorWithTerm(limit);

            case 8:
                return new CubeRootPrimeNumberCalculatorUpToNTerms(limit);

            case 9:
                return new SquareRootPrimeNumberCalculatorUpToLimit(limit);

            case 10:
                return new PrimeNumberCalculatorUpToLimit(limit);

            default:
                System.out.println("Opción inválida.\n");
                return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option = readOption(scanner);

        while (option != 0) {
            int limit = readLimit(scanner);

            try {
                Successionable successionable = createSuccession(option, limit);

                if (successionable != null) {
                    Number result = successionable.calculate();
                    System.out.println(successionable.print());
                    System.out.println("S = " + result + "\n");
                }

            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage() + "\n");
            }

            option = readOption(scanner);
        }

        System.out.println("Saliendo del programa.");
    }
}