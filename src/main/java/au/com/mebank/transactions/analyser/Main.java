package au.com.mebank.transactions.analyser;

import au.com.mebank.transactions.analyser.engines.AccountEngineKt;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(AccountEngineKt.balance(args[0], args[1], args[2], args[3]));
        } catch (Exception e) {
            System.out.println("An error occurred:");
            System.out.println("\t" + e);
            System.out.println("Please verify your parameters and try again.");
        }
    }
}
