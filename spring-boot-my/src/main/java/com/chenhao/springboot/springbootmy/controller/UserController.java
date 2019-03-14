package com.chenhao.springboot.springbootmy.controller;

import com.alibaba.fastjson.JSON;
import com.chenhao.springboot.springbootmy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登陆请求
     * @param request
     * @return
     */
    @RequestMapping("/user/login.do")
    public String login(HttpServletRequest request){
        String name=request.getParameter("name");
        String pwd=request.getParameter("pwd");
        Map<String,Object> result=null;
        if(name==null || "".equals(name)){
            result=new HashMap<String,Object>();
            result.put("code","03");
            result.put("msg","用户名不能为空");
            return JSON.toJSONString(result);
        }
        if(pwd==null || "".equals(pwd)){
            result=new HashMap<String,Object>();
            result.put("code","03");
            result.put("msg","密码不能为空");
            return JSON.toJSONString(result);
        }
        result=userService.login(name,pwd);
        String msg=(String)result.get("msg");
        return JSON.toJSONString(result);
    }

    /**
     * 查询全部请求
     * @param request
     * @return
     */
    @RequestMapping("/user/findAll.do")
    public String findAll(HttpServletRequest request){
        Map<String,Object> result=userService.findAll();
        return JSON.toJSONString(result);
    }


}
