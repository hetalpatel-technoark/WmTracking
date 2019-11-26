package com.wmtrucking.utils;

public enum Constant {

    //End Databse Config
    AUTHSESSION {
        @Override
        public String toString() {
            return "AuthSession";
        }

    },
    SESSION_TIME_OUT {
        @Override
        public String toString() {
            return "Session time out";
        }

    }, ACTIVE {
        @Override
        public String toString() {
            return "Active";
        }

    }, DETETED {
        @Override
        public String toString() {
            return "Deleted";
        }

    }, ERRORPARAM {
        @Override
        public String toString() {
            return "error";
        }

    }, DATEFORMAT {
        @Override
        public String toString() {
            return "MM-dd-yyyy";
        }

    }

}
