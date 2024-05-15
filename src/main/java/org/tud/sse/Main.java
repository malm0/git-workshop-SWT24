package org.tud.sse;

import org.tud.sse.control.InputHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        final InputHandler handler = new InputHandler();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter a command or type 'help':");
            System.out.print("> ");
            String input = br.readLine();

            while(!input.equals("exit")){
                System.out.println();
                handler.handleInput(input);
                System.out.println();
                System.out.println("Enter next command:");
                System.out.print("> ");
                input = br.readLine();
            }

            System.out.println("Shutting down...");
        } catch (IOException iox) {
            System.err.println("Something went wrong:");
            iox.printStackTrace();
        }
    }



}