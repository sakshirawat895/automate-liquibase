package com.demo.automateliquibase.model;

import java.io.Serializable;
import java.util.Map;

public class AdditionalFields implements Serializable {
    private static final long serialVersionUID = -679931862393263841L;

    private Map<String, String> extras;

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "AdditionalFields{" +
                "extras=" + extras +
                '}';
    }
}
