package by.kofi.scd.business.grid;

import java.io.Serializable;

/**
 * Grid result row class
 *
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 22:54
 */
public interface ResultRow extends Serializable {

    public Long getRowId();

    public long getCreditId();

    public Long getClientId();

}
