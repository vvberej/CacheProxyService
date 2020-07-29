

import cache.CacheHandler;
import serialization.SerializerCache;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Calculator calculator = new CalculatorImpl();

        Class<?> proxyClass = Proxy.getProxyClass(
                Calculator.class.getClassLoader(),
                Calculator.class
        );

        try {
            CacheHandler cacheHandler = new CacheHandler(calculator, 10);
            Calculator calculatorProxy = (Calculator) proxyClass.getConstructor(InvocationHandler.class)
                    .newInstance(cacheHandler);

            int result;
            double resultD;
            result = calculatorProxy.calc(2);
            resultD = calculatorProxy.pow(2, 5);
            result = calculatorProxy.calc(4);
            result = calculatorProxy.calc(2);
            resultD = calculatorProxy.pow(3, 2);
            result = calculatorProxy.calc(1);

            SerializerCache serializerCache = new SerializerCache();
            serializerCache.serialize(cacheHandler);
            CacheHandler cacheHandler2 = serializerCache.deserialize();
            System.out.println(cacheHandler2.toString());

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

        Calculator calculator1 = new CalculatorImpl();
        int result;
        double resultD;
        result = calculator1.calc(2);
        resultD = calculator1.pow(2, 5);
        result = calculator1.calc(4);
        result = calculator1.calc(2);
        resultD = calculator1.pow(3, 2);
        result = calculator1.calc(1);
    }
}
