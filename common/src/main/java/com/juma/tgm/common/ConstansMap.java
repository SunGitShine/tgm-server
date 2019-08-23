package com.juma.tgm.common;

import java.util.HashMap;
import java.util.Map;

public class ConstansMap {

    public static final Map<Integer, String> VALUATION_WAY_MAP = new HashMap<Integer, String>();

    static {
        VALUATION_WAY_MAP.put(1, "byDay");
        VALUATION_WAY_MAP.put(2, "byWeight");
        VALUATION_WAY_MAP.put(3, "byTicket");
        VALUATION_WAY_MAP.put(4, "byTime");
        VALUATION_WAY_MAP.put(5, "byPoints");
        VALUATION_WAY_MAP.put(6, "byTruck");
        VALUATION_WAY_MAP.put(7, "byKm");

    }
}
