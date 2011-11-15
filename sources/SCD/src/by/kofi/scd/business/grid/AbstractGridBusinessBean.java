package by.kofi.scd.business.grid;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.business.client.ClientBusinessBean;
import by.kofi.scd.business.credit.CreditBusinessBean;
import by.kofi.scd.business.download.FileDownloadService;
import by.kofi.scd.business.download.ReportGenerator;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.context.FacesContext;
import java.io.File;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:56
 */
public abstract class AbstractGridBusinessBean<T extends ResultRow> extends AbstractBusinessBean {
    private int rowsPerPage = 15;
    private int currentPage = 1;
    private long selectedRowId;
    private List<T> resultList;
    private File generatedReport;

    private Credit credit;
    private Client client;
    private Long creditId;
    private Long clientId;

    @Autowired
    private ReportGenerator reportGenerator;
    @Autowired
    private FileDownloadService fileDownloadService;
    @Autowired
    private CreditBusinessBean creditBusinessBean;
    @Autowired
    private ClientBusinessBean clientBusinessBean;

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

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * Return searched earlier items list of executeSearch if collection  == null
     *
     * @return items list
     * @throws SCDBusinessException executeSearch error
     */
    public List<T> getResultList() throws SCDBusinessException {
//         if (!FacesContext.getCurrentInstance().getRenderResponse()) {
//	        return null;
//	    }
//        if (resultList == null) {
        this.resultList = executeSearch();
//        }
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
     * return set of resultRows fields names
     *
     * @return cells properties
     */
    public abstract GridColumn[] getColumns();


    /**
     * Generate report by account No
     *
     * @throws SCDBusinessException report generatioin error
     */
    public void generateReport() throws SCDBusinessException {
        UserContext userContext = FacesUtil.getUserContext();

        File file = reportGenerator.generateReport(getSelectedRowId(), userContext);
        setGeneratedReport(file);
    }

    /**
     * Write report in http response and remove file
     *
     * @throws SCDBusinessException report download error
     */
    public void downloadReport() throws SCDBusinessException {
        UserContext userContext = FacesUtil.getUserContext();
        fileDownloadService.downloadFile(getGeneratedReport(), userContext);
    }

    /**
     * Load credit by name to show details in popUp
     *
     * @throws SCDBusinessException credit retrieve error
     */
    public void loadCredit() throws SCDBusinessException {
        Credit creditById = this.creditBusinessBean.getCreditById(getCreditId());
        setCredit(creditById);
    }

    /**
     * Load credit by name to show details in popUp
     *
     * @throws SCDBusinessException credit retrieve error
     */
    public void loadClient() throws SCDBusinessException {
        Client clientById = this.clientBusinessBean.getClientById(getClientId());
        setClient(clientById);
    }

}
