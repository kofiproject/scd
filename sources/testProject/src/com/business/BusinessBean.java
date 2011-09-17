package com.business;

import com.dataservice.IDataService;

/**
 * User: aaaa
 * Date: 05/06/11
 */
public class BusinessBean implements IBusiness {

    private IDataService dataService;

    public void setDataService(IDataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public int calculateBalance(String... criterions) {
        if (criterions == null) {
            throw new IllegalArgumentException("Criterion is null");
        }

        int result = 0;

        for (String criterion : criterions) {
            result += this.dataService.getBalanceByCriterion(criterion);
        }

        return result;
    }
}
