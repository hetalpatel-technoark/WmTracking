package com.wmtrucking.utils;

public enum Constant {

    MODE {
        @Override
        public String toString() {
              return "Live";
//            return "Local";
        }
    },
    FCM_SERVERKEY {
        @Override
        public String toString() {
            //return "AAAAg6l6Eoc:APA91bGeuJ9TC5_sx0vX958itBR_Ad9h_f7W_8PBHeeoxf3dRgBs2DaYcN-8476JHiK-A0-5UmdBt8_k6CQqO_gK43ORsyTg1Ib7mz-NsamGlOdfDU3UkotNwS5hv-yYjA-d-x_ERhv6";
            return "AAAAg6l6Eoc:APA91bHUtyeJPNM_KJf4ZMkO1d8dVhIc5dCqi-gHmM7zV4e-ssKXHRvr3vCmSpjIoy1XpMdBt7lGksfqS55ezznOQoHPdDK3-7N6tM3HM4Mz0NGETWdugXKeBlvFLACwWeIzD4RT8jcO";
        }
    },
    //End Databse Config
    AUTHSESSION {
        @Override
        public String toString() {
            return "AuthSession";
        }

    },
    PUSH_CERTIFICATE {
        @Override
        public String toString() {
            return MODE.toString().equals("Local") ? "push_certi_dev.p12" : "push_certi_dist.p12";
        }
    },
    PUSH_CERTIFICATE_PASSWORD {
        @Override
        public String toString() {
            return "Kb!zzP12";
        }
    },
    COOKIE_NAME {
        @Override
        public String toString() {
            return "WmTrucking";
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

    }, PENDING {
        @Override
        public String toString() {
            return "Pending";
        }

    }, STARTED {
        @Override
        public String toString() {
            return "Started";
        }

    },
    ENDED {
        @Override
        public String toString() {
            return "Ended";
        }

    },
    ERRORPARAM {
        @Override
        public String toString() {
            return "error";
        }

    }, DATEFORMAT {
        @Override
        public String toString() {
            return "MM-dd-yyyy";
        }

    },
    AESENCRYPTKEY {
        @Override
        public String toString() {
            return "78666"; //To change body of generated methods, choose Tools | Templates.
        }
    },

}
