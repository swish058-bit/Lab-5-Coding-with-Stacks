package cs1632;

public class NumToken extends Token {
    protected int value;

    public NumToken(String contents) {
        super(contents);
        value = Integer.valueOf(contents);
    }
}
