package com.wmtrucking.exception;

public class CSRFException extends Exception {

    private static final long serialVersionUID = 3048968244216044344L;

    public CSRFException(String msg) {
        super(msg);
    }
}
