package com.wmtrucking.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionUtils {

    public SessionUtils() {
    }

    public synchronized void setSessionValue(HttpServletRequest request, String key, Object value) {
        HttpSession session = (HttpSession) request.getSession(true);
        session.setAttribute(key, value);
    }

    public synchronized Object getSessionValue(HttpServletRequest request, String key) {
        HttpSession session = (HttpSession) request.getSession(true);
        return session.getAttribute(key);
    }
}
