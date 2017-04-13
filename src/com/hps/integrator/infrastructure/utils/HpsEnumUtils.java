package com.hps.integrator.infrastructure.utils;

import com.hps.integrator.abstractions.IByteConstant;
import com.hps.integrator.abstractions.IStringConstant;

public class HpsEnumUtils {
    public static <V extends Enum<V> & IByteConstant> boolean isDefined(Class<V> valueType, byte value){
        return parse(valueType, value) != null;
    }

    public static <V extends Enum<V> & IByteConstant> V parse(Class<V> valueType, byte value) {
        ReverseByteEnumMap<V> map = new ReverseByteEnumMap<V>(valueType);
        return map.get(value);
    }

    public static <V extends Enum<V> & IStringConstant> V parse(Class<V> valueType, String value) {
        ReverseStringEnumMap<V> map = new ReverseStringEnumMap<V>(valueType);
        return map.get(value);
    }
}
