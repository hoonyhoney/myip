package com.address.myip.app.service;

import com.address.myip.app.model.IpInfoVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.UnknownHostException;

@Service
public interface IpInfoService {
    String getIpInfo(HttpServletRequest request, IpInfoVO ipInfoVO) throws UnknownHostException;

    IpInfoVO selectCountryInfo(String ip) throws IOException;
}
