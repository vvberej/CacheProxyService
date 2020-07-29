import cache.Cache;

public interface Calculator {
    @Cache
    int calc(int val);

    double pow(double base, double exponent);
}
