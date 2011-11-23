package by.kofi.scd.business;

import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:55
 */
@Service
public class UserBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(UserBusinessBean.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public SCDUser getUserByIdentityId(Long id) throws SCDBusinessException {
        try {
            SCDUser user = getCRUDDataService().find(SCDUser.class, id);
            return user;
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }
}
