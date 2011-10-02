package by.kofi.scd.exceptions;

/**
 * Common class for all exceptions
 *
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 16:35
 */
public class SCDException extends Exception {

    /**
     * {@inheritDoc}
     */

    public SCDException(String s) {
        super(s);
    }

    /**
     * {@inheritDoc}
     */

    public SCDException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * {@inheritDoc}
     */
    public SCDException(Throwable throwable) {
        super(throwable);
    }
}
