package by.kofi.scd.business.grid;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.business.download.FileDownloadService;
import by.kofi.scd.business.download.ReportGenerator;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:56
 */
public abstract class AbstractGridBusinessBean<T extends ResultRow> extends AbstractBusinessBean {
    private int rowsPerPage = 2;
    private int currentPage = 1;
    private long selectedRowId;
    private List<T> resultList;
    private File generatedReport;

    @Autowired
    private ReportGenerator reportGenerator;
    @Autowired
    private FileDownloadService fileDownloadService;

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getSelectedRowId() {
        return selectedRowId;
    }

    public void setSelectedRowId(long selectedRowId) {
        this.selectedRowId = selectedRowId;
    }

    /**
     * Return searched earlier items list of executeSearch if collection  == null
     *
     * @return items list
     * @throws SCDBusinessException executeSearch error
     */
    public List<T> getResultList() throws SCDBusinessException {
        if (resultList == null) {
            this.resultList = executeSearch();
        }
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public File getGeneratedReport() {
        return generatedReport;
    }

    public void setGeneratedReport(File generatedReport) {
        this.generatedReport = generatedReport;
    }

    /**
     * retrieve grid rows
     *
     * @return list of items
     * @throws SCDBusinessException technical error
     */
    public abstract List<T> executeSearch() throws SCDBusinessException;

    /**
     * Return array of enums to represent grid headers
     *
     * @return headers
     */
    public abstract GridHeader[] getHeaders();

    /**
     * return set of resultRows fields names
     *
     * @return cells properties
     */
    public abstract ResultRowField[] getFields();


    /**
     * Generate report by account No
     *
     * @throws SCDBusinessException report generatioin error
     */
    public void generateReport() throws SCDBusinessException {
        UserContext userContext = getUserContext();

        File file = reportGenerator.generateReport(getSelectedRowId(), userContext);
        setGeneratedReport(file);
    }

    /**
     * Write report in http response and remove file
     *
     * @throws SCDBusinessException report download error
     */
    public void downloadReport() throws SCDBusinessException {
        UserContext userContext = getUserContext();
        fileDownloadService.downloadFile(getGeneratedReport(), userContext);
    }

    /**
     * @return userContext from session
     */
    private UserContext getUserContext() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (UserContext) session.getAttribute("userContext");
    }
}
