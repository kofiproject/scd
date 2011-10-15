package by.kofi.scd.business;

import by.kofi.scd.entity.Role;
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
@Scope("session")
public class RoleBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(RoleBusinessBean.class);
    /**
     * Client role id in database
     */
    public static final Long CLIENT_ROLE_ID = 1L;
    /**
     * Credit expert role id in database
     */
    public static final Long CREDIT_EXPERT_ID = 2L;
    /**
     * Operator role id in database
     */
    public static final Long OPERATOR_ID = 3L;

    /**
     * Return role from DB by id.
     * It's recommended to use constants from UserBusinessBean.class.
     *
     * @param id role id
     * @return role
     * @throws SCDBusinessException dataService error
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Role getRoleById(Long id) throws SCDBusinessException {
        try {
            return getCRUDDataService().find(Role.class, id);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }
}
