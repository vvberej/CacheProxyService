import java.io.Serializable;

public class CalculatorImpl implements Calculator , Serializable {
    @Override
    public int calc(int val) {
        return val * val;
    }

    @Override
    public double pow(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}