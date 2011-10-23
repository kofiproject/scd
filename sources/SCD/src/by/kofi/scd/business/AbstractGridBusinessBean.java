package by.kofi.scd.business;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.dataservice.CRUDDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:56
 */
public abstract class AbstractGridBusinessBean extends AbstractBusinessBean {
    private int rowsPerPage = 2;
    private int currentPage = 1;
    private long selectedRowId;

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
}
