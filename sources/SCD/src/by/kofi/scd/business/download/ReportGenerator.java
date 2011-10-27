package by.kofi.scd.business.download;

import by.kofi.scd.dto.UserContext;
import by.kofi.scd.exceptions.SCDBusinessException;

import java.io.File;

/**
 * Report generator interface
 *
 * @author harchevnikov_m
 *         Date: 27/10/11
 *         Time: 21:50
 */
public interface ReportGenerator {
    /**
     * Generate report for accountNo and store it in temp directory
     *
     * @param accountNumber Account number
     * @param userContext   user context
     * @throws SCDBusinessException error
     * @return generated File
     */
    public File generateReport(Long accountNumber, UserContext userContext) throws SCDBusinessException;


}
