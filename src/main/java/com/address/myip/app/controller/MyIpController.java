package com.address.myip.app.controller;

import com.address.myip.app.model.IpInfoVO;
import com.address.myip.app.service.IpInfoService;
import org.intellij.lang.annotations.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;


@Controller
public class MyIpController {
    private final IpInfoService ipInfoService;

    @Autowired
    public MyIpController(IpInfoService ipInfoService){
        this.ipInfoService = ipInfoService;
    }


    @GetMapping("/my-ip-address")
    public String checkMyIpAddress(HttpServletRequest request, Model model, IpInfoVO ipInfoVO, String redirectMsg) throws IOException {
        System.out.println("Get 요청");

        //1. ip 주소 반환
        String ip =ipInfoService.getIpInfo(request,ipInfoVO);
        System.out.println("ip = " + ip);

        //2. ip,국가 코드,이름 반환
        IpInfoVO myIpInfo = ipInfoService.selectCountryInfo(ip);

        model.addAttribute("myIpInfo",myIpInfo);
        model.addAttribute("redirectMsg",redirectMsg);

        return "ip/my_ip_address";
    }
    @PostMapping("/my-ip-address")
    public String checkMyIpAddressPost(HttpServletRequest request, Model model, IpInfoVO ipInfoVO,RedirectAttributes redirectAttributes) throws IOException {
        System.out.println("Post요청");

        //0. ip 유효성 검사
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        boolean isValid = ipInfoVO.getIp().matches(PATTERN);
        if(isValid==false){
            redirectAttributes.addAttribute("redirectMsg","ip가 유효하지 않습니다");
            return "redirect:/my-ip-address";
        }

        //1. ip 주소 반환
        String ip =ipInfoService.getIpInfo(request,ipInfoVO);
        System.out.println("ip = " + ip);

        //2. ip,국가 코드,이름 반환
        IpInfoVO myIpInfo = ipInfoService.selectCountryInfo(ip);

        model.addAttribute("myIpInfo",myIpInfo);

        return "ip/my_ip_address";
    }
}
