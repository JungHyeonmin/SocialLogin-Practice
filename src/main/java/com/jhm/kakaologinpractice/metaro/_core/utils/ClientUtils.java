package com.jhm.kakaologinpractice.metaro._core.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientUtils {

    // ClientIP 추출
    public static String getClientIp(HttpServletRequest request) {

        String clientIp = null;
        boolean isIpInHeader = false;

        List<String> headerList = new ArrayList<>();
        headerList.add("X-Forwarded-For");
        headerList.add("HTTP_CLIENT_IP");
        headerList.add("HTTP_X_FORWARDED_FOR");
        headerList.add("HTTP_X_FORWARDED");
        headerList.add("HTTP_FORWARDED_FOR");
        headerList.add("HTTP_FORWARDED");
        headerList.add("Proxy-Client-IP");
        headerList.add("WL-Proxy-Client-IP");
        headerList.add("HTTP_VIA");
        headerList.add("IPV6_ADR");

        for (String header : headerList) {
            clientIp = request.getHeader(header);
            if (StringUtils.hasText(clientIp) && !clientIp.equals("unknown")) {
                isIpInHeader = true;
                break;
            }
        }

        if (!isIpInHeader) {
            clientIp = request.getRemoteAddr();
        }

        return clientIp;
    }
}