package by.kofi.scd.entity;

import java.io.Serializable;

/**
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 18:36
 */
public abstract class AbstractEntity {

    public abstract Serializable getEntityId();

    public abstract boolean entityEquals(Object o);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractEntity entity =  (AbstractEntity) o;
        Serializable entityId =  entity.getEntityId();
        if(entityId == null || getEntityId() == null) {
            return  entityEquals(o);
        }

        return getEntityId().equals(entity.getEntityId());
    }

        public abstract int entityHashCode();

    @Override
    public int hashCode() {
        if(getEntityId() != null) {
            return getEntityId().hashCode();
        }

        return entityHashCode();
    }
}
