package cs1632;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class PrefixEvalTest {

    private int run(String... parts) {
        ArrayList<Token> tokens = new ArrayList<>();
        for (String p : parts) {
            if (p.matches("\\d+")) {
                tokens.add(new NumToken(p));
            } else {
                tokens.add(new OpToken(p.charAt(0)));
            }
        }
        return Main.evalPrefix(tokens);
    }

    @Test
    public void testAdd() {
        assertEquals(5, run("+", "2", "3", ";"));
    }

    @Test
    public void testMultiply() {
        assertEquals(20, run("*", "4", "5", ";"));
    }

    @Test
    public void testNested() {
        assertEquals(14, run("+", "2", "*", "3", "4", ";"));
    }

    @Test
    public void testSecondExample() {
        assertEquals(2, run("-", "*", "2", "3", "4", ";"));
    }

    @Test
    public void testDivide() {
        assertEquals(4, run("/", "20", "5", ";"));
    }
}
