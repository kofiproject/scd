package by.kofi.scd.dto.manager;

import by.kofi.scd.dto.AbstractModel;

/**
 * @author harchevnikov_m
 *         Date: 25/12/11
 *         Time: 20:01
 */
public class CreditStatistics extends AbstractModel {
    private int newCreditsCount;
    private int closedCreditsCount;
    private int withPenaltyCreditsCount;

    public CreditStatistics(int newCreditsCount, int closedCreditsCount, int withPenaltyCreditsCount) {
        this.newCreditsCount = newCreditsCount;
        this.closedCreditsCount = closedCreditsCount;
        this.withPenaltyCreditsCount = withPenaltyCreditsCount;
    }

    public int getNewCreditsCount() {
        return newCreditsCount;
    }

    public void setNewCreditsCount(int newCreditsCount) {
        this.newCreditsCount = newCreditsCount;
    }

    public int getClosedCreditsCount() {
        return closedCreditsCount;
    }

    public void setClosedCreditsCount(int closedCreditsCount) {
        this.closedCreditsCount = closedCreditsCount;
    }

    public int getWithPenaltyCreditsCount() {
        return withPenaltyCreditsCount;
    }

    public void setWithPenaltyCreditsCount(int withPenaltyCreditsCount) {
        this.withPenaltyCreditsCount = withPenaltyCreditsCount;
    }
}
