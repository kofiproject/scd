package by.kofi.scd.business.download;

import by.kofi.scd.dto.UserContext;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;

/**
 * @author harchevnikov_m
 *         Date: 27/10/11
 *         Time: 21:53
 */
@Service
public class ReportGeneratorService implements ReportGenerator {
    private static final Logger LOGGER = Logger.getLogger(ReportGeneratorService.class);

    public File generateReport(Long accountNumber, UserContext userContext) throws SCDBusinessException {
        //todo implement
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String realPath = externalContext.getRealPath("/");
        File file = new File(realPath, "test.tmp");

        return file;
    }
}
