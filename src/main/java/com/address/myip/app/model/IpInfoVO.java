package com.address.myip.app.model;

import lombok.Data;

@Data
public class IpInfoVO {
    private String ip;
    private Long ipLong;
    private String countryCode;
    private String countryName;

}
