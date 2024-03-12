package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 1;
        for(int i = 0; i < args.length;i++){
            numberOfThreads = Integer.parseInt(args[i]);
        }
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        PrimeQueue primeQueue = new PrimeQueue();
        ResultQueue resultQueue = new ResultQueue();
        PrimeCheck[] primeChecks = new PrimeCheck[numberOfThreads];
        for(int i = 0; i < numberOfThreads; i++) {
            primeChecks[i] = new PrimeCheck(primeQueue, resultQueue);
            primeChecks[i].start();
        }
        System.out.println("Enter a number to check if it is prime or type 'exit' to stop the program");
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            if(input.equals("exit")){
                for(int i = 0; i < numberOfThreads; i++) {
                    primeChecks[i].interrupt();
                }
                break;
            }
            try {
                int number = Integer.parseInt(input);
                primeQueue.add(number);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
        scanner.close();
    }
}