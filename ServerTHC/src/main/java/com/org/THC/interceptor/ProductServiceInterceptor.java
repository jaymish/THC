package com.org.THC.interceptor;

import com.org.THC.model.APIExecutionTime;
import com.org.THC.model.APIName;
import com.org.THC.repo.APIExecutionTimeRepo;
import com.org.THC.repo.APINameRepo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class ProductServiceInterceptor implements HandlerInterceptor {
    private APIExecutionTimeRepo apiExecutionTimeRepo;
    private APINameRepo apiNameRepo;
    public ProductServiceInterceptor(APINameRepo apiNameRepo,APIExecutionTimeRepo apiExecutionTimeRepo){
        this.apiExecutionTimeRepo=apiExecutionTimeRepo;
        this.apiNameRepo=apiNameRepo;
    }

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        long startTime = (Long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        String nameOfAPI;
//        if(handler.toString().contains("#")) {
//            nameOfAPI = handler.toString().split("#")[1].split("\\(")[0];
//        }
//        else{
        nameOfAPI = handler.toString();

        APIExecutionTime apiExecutionTime=new APIExecutionTime();
        APIName apiName=new APIName();
        apiName.setName(nameOfAPI);
        if(apiNameRepo.findByName(nameOfAPI)!=null){
            apiExecutionTime.setApiName(apiNameRepo.findByName(nameOfAPI));
        }
        else {
            apiExecutionTime.setApiName(apiName);
        }
        Date date=Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        apiExecutionTime.setExecutionTime(executeTime);
        apiExecutionTime.setExecutionDate(dateFormat.format(date));
        apiExecutionTimeRepo.save(apiExecutionTime);
    }
}
