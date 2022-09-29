package com.appInfoSystem.pojo;

public class dataDictionary {
    private int valueId;
    private String valueName;

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    @Override
    public String toString() {
        return "dataDictionary{" +
                "valueId=" + valueId +
                ", valueName=" + valueName +
                '}';
    }
}
