package by.kofi.scd.dataservice.client;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 21:06
 */
@Service
public class ClientDataServiceBean extends AbstractDataServiceBean implements ClientDataService {

    @Transactional(propagation = Propagation.MANDATORY)
    public Client getClientByPassportData(String passportSeries, Long passportNo) throws SCDTechnicalException {
        try {
            int index = 0;
            Client result = (Client) getSession().createQuery("from Client cl where upper(cl.passportSeries) = upper(?) and cl.passportNo = ?")
                    .setString(index++, passportSeries)
                    .setLong(index, passportNo).uniqueResult();

            return result;
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

}