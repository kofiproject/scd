package by.kofi.scd.dto;

import by.kofi.scd.entity.SCDUser;

/**
 * User context
 *
 * @author harchevnikov_m
 *         Date: 16/10/11
 *         Time: 16:23
 */
public class UserContext extends AbstractModel {
    private SCDUser user;

    public SCDUser getUser() {
        return user;
    }

    public void setUser(SCDUser user) {
        this.user = user;
    }
}
