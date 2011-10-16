package by.kofi.scd.entity;

import by.kofi.scd.common.i18n.I18nSupport;

/**
 * @author harchevnikov_m
 *         Date: 09/10/11
 *         Time: 17:16
 */
public enum GenderEnum {
    MALE("registration.gender.male"),
    FEMALE("registration.gender.female");

    private String bundleKey;

    private GenderEnum(String bundleKey) {
        this.bundleKey = bundleKey;
    }


    @Override
    public String toString() {
        return I18nSupport.getText(this.bundleKey);
    }
}
