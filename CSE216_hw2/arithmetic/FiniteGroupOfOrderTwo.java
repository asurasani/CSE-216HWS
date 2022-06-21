package arithmetic;

public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne>{

    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne one, PlusOrMinusOne other) {
        return new PlusOrMinusOne(one.val * other.val);
    }

    @Override
    public PlusOrMinusOne identity() {
        return new PlusOrMinusOne(1);
    }

    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne plusOrMinusOne) {
        return plusOrMinusOne;
    }

    @Override
    public PlusOrMinusOne exponent(PlusOrMinusOne plusOrMinusOne, int k) {
        return Group.super.exponent(plusOrMinusOne, k);
    }
}
