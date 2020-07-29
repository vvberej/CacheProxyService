package cache;

import java.io.Externalizable;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Динамический прокси
 */
public class CacheHandler implements InvocationHandler, Serializable {
    private static final long serialVersionUID = 1L;
    private Object original;
    private Map<Integer, Integer> cache;

    public CacheHandler(Object original, int cacheSize) {
        this.original = original;
        this.cache = new LinkedHashMap<>(cacheSize);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(args == null || args.length == 0)
            throw new IllegalArgumentException("Parameter required");

        if(method.isAnnotationPresent(Cache.class)) {
            int arg = (int) args[0];
            Integer result = cache.get(arg);
            if (result == null) {
                result = (Integer) method.invoke(original, arg);
                cache.put(arg, result);
            }
            else {
                System.out.println("Value from cache");
            }
            return result;
        }
        return method.invoke(original, args);
    }

    public Object getOriginal() {
        return original;
    }

    public void setOriginal(Object original) {
        this.original = original;
    }

    public Map<Integer, Integer> getCache() {
        return cache;
    }

    public void setCache(Map<Integer, Integer> cache) {
        this.cache = cache;
    }

    @Override
    public String toString() {
        return "CacheHandler{" +
                "original='" + original.toString() + '\'' +
                ", cache=" + cache.toString() +
                '}';
    }
}