package com.address.myip.app.mapper;

import com.address.myip.app.model.IpInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IpInfoMapper {
    IpInfoVO selectCountryInfo(IpInfoVO ipInfoVO);

}
