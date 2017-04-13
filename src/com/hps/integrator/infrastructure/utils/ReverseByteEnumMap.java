package com.hps.integrator.infrastructure.utils;

import com.hps.integrator.abstractions.IByteConstant;
import java.util.*;

public class ReverseByteEnumMap<V extends Enum<V> & IByteConstant> {
    private Map<Byte, V> map = new HashMap();

    public ReverseByteEnumMap(Class<V> valueType) {
        for(V v: valueType.getEnumConstants()) {
            map.put(v.getByte(), v);
        }
    }

    public V get(byte value) {
        return map.get(value);
    }
}