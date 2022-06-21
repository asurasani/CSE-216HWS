package arithmetic;

public class PlusOrMinusOne {
    private static PlusOrMinusOne[] ref = {new PlusOrMinusOne(-1), new PlusOrMinusOne(1)};
    public int val;
    public PlusOrMinusOne(int v) {
        val = v;
    }

    public static PlusOrMinusOne[] values() {
        return ref;
    }
}
