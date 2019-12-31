package com.wmtrucking.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CheckInput {

    public String checkValue(String value) {
        if (value != null) {
            return value;
        }
        return "";
    }

    public String checkValue(Object value) {
        if (value != null) {
            return value.toString();
        }
        return "";
    }

    public String checkValue(Double value) {
        if (value != null) {
            return value.toString();
        }
        return "";
    }

    public String checkValue(Long value) {
        if (value != null) {
            return value.toString();
        }
        return "";
    }

    public String checkValueEdit(String value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value;
                }
                return valueReq;
            } else {
                return value;
            }
        }
        return "";
    }

    public String checkValueEdit(Object value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(BigDecimal value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(Double value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(Long value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(BigInteger value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(Integer value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

   

    public String checkCSRFValue(Object value, String valueReq) {

        if (value != null) {
            return value.toString();
        }
        if (valueReq != null) {
            return valueReq;
        }
        return "";
    }

}
