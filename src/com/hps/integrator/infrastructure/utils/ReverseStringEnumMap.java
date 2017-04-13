package com.hps.integrator.infrastructure.utils;

import com.hps.integrator.abstractions.IStringConstant;

import java.util.HashMap;
import java.util.Map;

public class ReverseStringEnumMap<V extends Enum<V> & IStringConstant> {
    private Map<String, V> map = new HashMap<String, V>();

    public ReverseStringEnumMap(Class<V> valueType) {
        for(V v: valueType.getEnumConstants()) {
            map.put(v.getValue(), v);
        }
    }

    public V get(String value) {
        return map.get(value);
    }
}