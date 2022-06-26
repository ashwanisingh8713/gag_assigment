package com.gag.assignment.model;

/**
 * Created by ashwanisingh on 25/06/22.
 */

public  class CustomAttributesDTO {
    private String attribute_code;
    private Object value;

    public String getAttribute_code() {
        return attribute_code;
    }

    public void setAttribute_code(String attribute_code) {
        this.attribute_code = attribute_code;
    }

    public Object getValue() {
        return value;
    }

}
