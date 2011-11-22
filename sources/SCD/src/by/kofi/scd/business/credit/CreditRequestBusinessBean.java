package by.kofi.scd.business.credit;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.dataservice.credit.request.CreditRequestDataService;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.CreditRequestStateEnum;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:55
 */
@Service
public class CreditRequestBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(CreditRequestBusinessBean.class);

    @Autowired
    private CreditRequestDataService creditRequestDataService;

    @Transactional(propagation = Propagation.REQUIRED)
    public CreditRequest storeCreditRequest(CreditRequest creditRequest) throws SCDBusinessException {
        try {
            return getCRUDDataService().merge(creditRequest);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    /**
     * If exist in DB creditRequest in process for specific client and credit
     *
     * @param credit credit
     * @param client client
     * @return exist credit request
     * @throws SCDBusinessException dataService layer error
     */
    public boolean existCreditRequestInProcess(Credit credit, Client client) throws SCDBusinessException {
        try {
            List<CreditRequest> creditRequests = creditRequestDataService.getCreditRequests(client.getClientId(), credit.getCreditId(),
                    CreditRequestStateEnum.IN_PROCESS);

            return !creditRequests.isEmpty();
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CreditRequest getCreditRequestById(Long creditRequestId) throws SCDBusinessException {
        try {
            return getCRUDDataService().find(CreditRequest.class, creditRequestId);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }
}
