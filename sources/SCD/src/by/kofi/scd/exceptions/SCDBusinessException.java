package by.kofi.scd.exceptions;

/**
 * Common class for exception raised at Business layer
 *
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 16:35
 */
public class SCDBusinessException extends SCDException {
    /**
     * {@inheritDoc}
     */
    public SCDBusinessException(String s) {
        super(s);
    }

    /**
     * {@inheritDoc}
     */
    public SCDBusinessException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * {@inheritDoc}
     */
    public SCDBusinessException(Throwable throwable) {
        super(throwable);
    }
}
