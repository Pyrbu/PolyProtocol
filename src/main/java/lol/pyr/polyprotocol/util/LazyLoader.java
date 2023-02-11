package lol.pyr.polyprotocol.util;

import java.util.ArrayList;
import java.util.List;

public class LazyLoader <T> {
    private final static List<LazyLoader<?>> allLoaders = new ArrayList<>();

    private final Provider<T> provider;
    private T value;
    private boolean initialized = false;

    public LazyLoader(Provider<T> provider) {
        this.provider = provider;
        synchronized (allLoaders) {
            allLoaders.add(this);
        }
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        if (!initialized) initialize();
        return value;
    }

    private void initialize() {
        if (initialized) throw new IllegalStateException("Tried to initialize a lazily loaded value twice!");
        this.initialized = true;
        this.value = provider.provide();
    }

    public static void initializeAll() {
        synchronized (allLoaders) {
            for (LazyLoader<?> loader : allLoaders) if (!loader.initialized) loader.initialize();
        }
    }

    @FunctionalInterface
    public interface Provider <T> {
        T provide();
    }
}
