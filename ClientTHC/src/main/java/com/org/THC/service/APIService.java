package com.org.THC.service;

import com.org.THC.model.APIName;
import com.org.THC.model.PageAPIExecuation;
import com.org.THC.model.PageLocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class APIService {
    private RestTemplate restTemplate;
    List<APIName> apiNameList;
    @Value("${url.to.serverthc}")
    private String serverURL;

    String url="api/";

    public APIService(RestTemplate restTemplate,List<APIName> apiNameList){
        this.restTemplate=restTemplate;
        this.apiNameList=apiNameList;
    }
    public PageAPIExecuation getAllpage(Integer pageNo, Integer pageSize, String showbyname,String showbydate) {
        System.out.print(url);
        PageAPIExecuation pageLocation=restTemplate.getForObject(serverURL+url+"page?pageNo="+pageNo+"&pageSize="+pageSize+"&sortBy=id&showbyname="+showbyname+"&showbydate="+showbydate,PageAPIExecuation.class);
        return pageLocation;
    }
    public List<APIName> getAllNames(){
        return restTemplate.getForObject(serverURL+url+"list",apiNameList.getClass());
    }
}
