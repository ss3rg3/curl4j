package net.covers1624.curl4j;

import org.lwjgl.system.Callback;

import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * A function callback for handling curl headers.
 * <p>
 * See the curl <a href="https://curl.se/libcurl/c/CURLOPT_HEADERFUNCTION.html">documentation</a>.
 *
 * @author covers1624
 * @see CurlHeaderCallbackI
 */
public abstract class CurlHeaderCallback extends CURLCallback implements CurlHeaderCallbackI {

    public static CurlHeaderCallback create(long functionPointer) {
        CurlHeaderCallbackI instance = Callback.get(functionPointer);
        return instance instanceof CurlHeaderCallback ? (CurlHeaderCallback) instance : new Container(functionPointer, instance);
    }

    public static CurlHeaderCallback createSafe(long functionPointer) {
        return functionPointer == NULL ? null : create(functionPointer);
    }

    public static CurlHeaderCallback create(CurlHeaderCallbackI instance) {
        return instance instanceof CurlHeaderCallback ? (CurlHeaderCallback) instance : new Container(instance.address(), instance);
    }

    protected CurlHeaderCallback() {
        super(CIF);
    }

    CurlHeaderCallback(long address) {
        super(address);
    }

    private static final class Container extends CurlHeaderCallback {

        private final CurlHeaderCallbackI delegate;

        private Container(long functionPointer, CurlHeaderCallbackI delegate) {
            super(functionPointer);
            this.delegate = delegate;
        }

        @Override
        public void invoke(String header, long userdata) {
            delegate.invoke(header, userdata);
        }
    }
}
