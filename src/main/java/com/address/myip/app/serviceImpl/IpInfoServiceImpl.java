package com.address.myip.app.serviceImpl;

import com.address.myip.app.mapper.IpInfoMapper;
import com.address.myip.app.model.IpInfoVO;
import com.address.myip.app.service.IpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

@Service
public class IpInfoServiceImpl implements IpInfoService {

    private final IpInfoMapper ipInfoMapper;

    @Autowired
    public IpInfoServiceImpl(IpInfoMapper ipInfoMapper) {
        this.ipInfoMapper = ipInfoMapper;
    }

    @Override
    public String getIpInfo(HttpServletRequest request, IpInfoVO ipInfoVO) throws UnknownHostException {
        String ip = ipInfoVO.getIp();

        if(ip !=null){
            ip = ipInfoVO.getIp();
            return ip;
        }

        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("address = " + address);
            ip = address.getHostAddress();
        }
        return ip;
    }

    @Override
    public IpInfoVO selectCountryInfo(String ip){

        // 1. ip를 int로 변환하기
        Long ipLong = convertIpToLong(ip);
        IpInfoVO ipInfoVO = new IpInfoVO();
        ipInfoVO.setIpLong(ipLong);
        ipInfoVO.setIp(ip);
        // 2. 변환된 값으로 국가정보 가져오기

        IpInfoVO myIpInfo = ipInfoMapper.selectCountryInfo(ipInfoVO);
        if(myIpInfo !=null) {
            ipInfoVO.setCountryCode(myIpInfo.getCountryCode());
            ipInfoVO.setCountryName(myIpInfo.getCountryName());
        }

        return ipInfoVO;
    }

    public long convertIpToLong(String ip){
        long ipLong = 0;
        String[] ipArray = ip.split("\\.");
        for (int i = 0; i < ipArray.length; i++) {
            ipLong += Integer.parseInt(ipArray[i]) * Math.pow(256,3 - i); //pow 제곱합수
        }
        System.out.println("ipLong = " + ipLong);
        return ipLong;
    }

    private static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public static boolean validate(final String ip) {
        return PATTERN.matcher(ip).matches();
    }


}
