package com.org.THC.service;

import com.org.THC.model.APIName;
import com.org.THC.model.PageAPIExecuation;
import com.org.THC.model.PageLocation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class APIService {
    private RestTemplate restTemplate;
    List<APIName> apiNameList;
    String url="http://localhost:8081/api/";

    public APIService(RestTemplate restTemplate,List<APIName> apiNameList){
        this.restTemplate=restTemplate;
        this.apiNameList=apiNameList;
    }
    public PageAPIExecuation getAllpage(Integer pageNo, Integer pageSize, String showbyname,String showbydate) {
        PageAPIExecuation pageLocation=restTemplate.getForObject(url+"page?pageNo="+pageNo+"&pageSize="+pageSize+"&sortBy=id&showbyname="+showbyname+"&showbydate="+showbydate,PageAPIExecuation.class);
        return pageLocation;
    }
    public List<APIName> getAllNames(){
        return restTemplate.getForObject(url+"list",apiNameList.getClass());
    }
}
