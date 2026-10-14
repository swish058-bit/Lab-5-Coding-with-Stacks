package cs1632;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    private static Boolean endsSequence(Token tok) {
        if (tok == null) return true;
        if (tok instanceof OpToken) {
            if (((OpToken) tok).getOperator() == ';') return true;
            if (((OpToken) tok).getOperator() == '.') return true;
        }
        return false;
    }

    private static ArrayList<Token> getNextSequence(Tokenizer toknizr) throws IOException {
        ArrayList<Token> tokens = new ArrayList<>();
        Token tok;
        do {
            tok = toknizr.nextToken();
            if (tok != null) tokens.add(tok);
        } while (!endsSequence(tok));

        if (tok == null) return null;
        if (tok instanceof OpToken) {
            if (((OpToken) tok).getOperator() == '.') {
                return null;
            }
        }
        return tokens;
    }

    private static int evalPrefix(ArrayList<Token> tokens) {
        Stack<Integer> stack = new Stack<>();

        for (int i = tokens.size() - 1; i >= 0; i--) {
            Token tok = tokens.get(i);

            if (tok instanceof OpToken && ((OpToken) tok).getOperator() == ';') {
                continue;
            }

            if (tok instanceof NumToken) {
                stack.push(((NumToken) tok).value);
            }
            else if (tok instanceof OpToken) {
                char op = (char)((OpToken) tok).getOperator();
                int a = stack.pop();
                int b = stack.pop();

                if (op == '+') stack.push(a + b);
                else if (op == '-') stack.push(a - b);
                else if (op == '*') stack.push(a * b);
                else if (op == '/') stack.push(a / b);
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        try {
            Tokenizer myScanner = new Tokenizer(System.in);
            ArrayList<Token> tokenSequence;

            do {
                System.out.print("Equation: ");
                tokenSequence = getNextSequence(myScanner);

                if (tokenSequence != null) {

                    int result = evalPrefix(tokenSequence);
                    System.out.println("Result: " + result);
                }

            } while (tokenSequence != null);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
