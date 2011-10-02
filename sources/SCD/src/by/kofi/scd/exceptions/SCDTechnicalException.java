package by.kofi.scd.exceptions;

/**
 * Common class for exception raised at Data Service  layer
 *
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 16:35
 */
public class SCDTechnicalException extends SCDException {
    /**
     * {@inheritDoc}
     */
    public SCDTechnicalException(String s) {
        super(s);
    }

    /**
     * {@inheritDoc}
     */
    public SCDTechnicalException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * {@inheritDoc}
     */
    public SCDTechnicalException(Throwable throwable) {
        super(throwable);
    }
}
