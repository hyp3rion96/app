package app.exercise.testing;

import app.exercise.algebra.Arithmetic;
import app.exercise.algebra.CompRational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Executable class that reads in reversed polish notation from standard input and evaluates the expression.
 * @author Robin Hundt
 * @version 1.0
 */
public class RPN {
    /**
     * Stack of {@link Arithmetic} objects to store the operands of the reversed polish notation.
     */
    private Stack<Arithmetic> stack;
    /**
     * Used to store the individual operands and operators of the RPN.
     */
    private String[] rpn;

    /**
     * Constructor for the reverse polish notation object.
     */
    public RPN() {
        stack = new Stack<>();
    }

    /**
     * The Format of the input String is first checked with the {@link #checkInput(String)} method. If the check passes, the input String is split into the String array
     * {@link #rpn} with whitespace as delimeter. Throws IllegalArgumentException if the format of the input String is wrong. Then evaluates the russian polish notation
     * by using the {@link #stack} and the operators and operands in {@link #rpn}. The operators
     * +, -, *, / correspond to {@link CompRational#add(Arithmetic)}, {@link CompRational#sub(Arithmetic)}, {@link CompRational#mul(Arithmetic)} and
     * {@link CompRational#div(Arithmetic)}..
     * @return {@link Arithmetic} object that is the result of the RPN.
     * @throws IllegalArgumentException if the input String has the wrong format or the expression cannot be evaluated.
     */

    public Arithmetic evaluate (String input) {
        if(!checkInput(input))
            throw new IllegalArgumentException("Input had wrong format. Only positive integers " +
                    "and operators +, *, /, * are allowed.");
        else
            rpn = input.split("\\s");
        for(String s : rpn) {
            if(s.matches("\\d+")) {
                int d = Integer.parseInt(s);
                stack.push(new CompRational(d, 1));
            } else {
                Arithmetic op1, op2;
                if(stack.size() >= 2) {
                    switch (s) {
                        case "+":
                            op2 = stack.pop();
                            op1 = stack.pop();
                            op1.add(op2);
                            stack.push(op1);
                            break;
                        case "-":
                            op2 = stack.pop();
                            op1 = stack.pop();
                            op1.sub(op2);
                            stack.push(op1);
                            break;
                        case "*":
                            op2 = stack.pop();
                            op1 = stack.pop();
                            op1.mul(op2);
                            stack.push(op1);
                            break;
                        case "/":
                            op2 = stack.pop();
                            op1 = stack.pop();
                            op1.div(op2);
                            stack.push(op1);
                            break;
                    }
                } else {
                    throw new IllegalArgumentException("Cannot evaluate expression.");
                }

            }
        }
        if(stack.size()==1){
            return stack.pop();
        } else {
            for(Arithmetic a : stack)
                System.out.println(a);
            throw new IllegalArgumentException("Wrong Expression. Possibly unmatched operators or operands.");
        }
    }

    /**
     * checkInput uses regular expressions to check if the input String only consists of positive integers and operators
     * +, -, *, / .
     * @param input String to check.
     * @return true if the content of the passed String matches the RPN specifications, otherwise false.
     */
    private static boolean checkInput(String input) {
        String[] s = input.split("\\s");
        for(String elem : s) {
            if(!elem.matches("(\\+||\\*||\\/||\\-)||\\d+"))
                return false;
        }
        return true;
    }

    /**
     * Main method to evaluate reverse polish notation expressions. Uses a BufferedReader to read lines from standard input.
     * @param args
     */
    public static void main(String[] args) {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in)))
        {
            RPN rpn = new RPN();
            while (true) {
                String input = in.readLine();
                try {
                    System.out.println(rpn.evaluate(input));
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
