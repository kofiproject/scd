package com.dataservice;

/**
 * User: aaaa
 * Date: 05/06/11
 */
public class DataServiceBean implements IDataService {

    @Override
    public int getBalanceByCriterion(String criterion) {
        return criterion.length();
    }
}
