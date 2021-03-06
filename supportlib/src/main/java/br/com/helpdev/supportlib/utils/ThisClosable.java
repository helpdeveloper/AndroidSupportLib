package br.com.helpdev.supportlib.utils;

import android.os.Build;
import android.util.Log;

import java.io.Closeable;

/**
 * @deprecated Depreciado para api >=19
 * Created by Guilherme Biff Zarelli on 16/02/16.
 */
@Deprecated
public final class ThisClosable {
    private ThisClosable() {
        throw new RuntimeException("No ThisClosable!");
    }

    private static final String LOG = "ThisClosable";


    public static void close(Object closeable) {
        if (closeable != null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    ((Closeable) closeable).close();
                } else {
                    closeable.getClass().getMethod("close").invoke(closeable);
                }
            } catch (Throwable e) {
                Log.e(LOG, "close(Closeable closeable)", e);
            }
        }
    }

    public static void close(Object closeable, Object... closeables) {
        close(closeable);
        if (closeables != null) {
            for (Object closeable1 : closeables) {
                close(closeable1);
            }
        }
    }
}
