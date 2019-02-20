package com.sehinc.common.db.hibernate;

import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class AliasToEntityLinkedMapResultTransformer extends AliasToEntityMapResultTransformer implements Serializable
{
    public static final AliasToEntityLinkedMapResultTransformer INSTANCE = new AliasToEntityLinkedMapResultTransformer();

    public AliasToEntityLinkedMapResultTransformer() {

    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        Map
            result = new LinkedHashMap(tuple.length);
        for (int i = 0; i < tuple.length; i++) {
            String alias = aliases[i];
            if (alias != null) {
                result.put(alias, tuple[i]);
            }
        }

        return result;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public boolean equals(Object other) {
        return other != null && AliasToEntityLinkedMapResultTransformer.class.isInstance(other);
    }

    public int hashCode() {
        return getClass().getName().hashCode();
    }
}