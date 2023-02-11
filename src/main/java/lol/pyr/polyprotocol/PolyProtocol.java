package lol.pyr.polyprotocol;

import lol.pyr.polyprotocol.util.LazyLoader;

@SuppressWarnings("unused")
public class PolyProtocol {
    /**
     * This function forcefully initializes all lazily-initialized instances
     * calling this during the startup of your program may positively impact
     * performance shortly after the program has started up and may prevent
     * some performance hiccups when a new part of the library is being warmed
     * up for use however, this will initialize <b>EVERYTHING</b> meaning that
     * <b>memory usage will be significantly higher</b> than if you allowed
     * only the useful instances to initialize.
     */
    public static void forceInitialize() {
        LazyLoader.initializeAll();
    }
}
