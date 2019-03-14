package com.chenhao.springboot.springbootmy.service.impl;

import com.chenhao.springboot.springbootmy.entity.User;
import com.chenhao.springboot.springbootmy.repository.UserReposity;
import com.chenhao.springboot.springbootmy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserReposity userReposity;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Map<String, Object> save(User user) {
        Map<String,Object> retMap=new HashMap<String,Object>();
        try{
            userReposity.insert(user);
            retMap.put("code","00");
            retMap.put("msg","新增成功");
        }catch(Exception e){
            retMap.put("code","01");
            retMap.put("msg","新增失败");
        }
        return retMap;
    }

    @Override
    public Map<String, Object> update(Map<String,Object> param,Object key) {
        System.out.println("param::"+param.toString()+"   id::"+key);
        Map<String,Object> retMap=new HashMap<String,Object>();
        try{
            Set<String> keys=param.keySet();
            for(String kk:keys){
                Query query=new Query(Criteria.where("id").is(key));
                Update update=new Update();
                update.addToSet(kk,param.get(kk));
                mongoTemplate.updateFirst(query,update,User.class);
            }
            retMap.put("code","00");
            retMap.put("msg","修改成功");
        }catch(Exception e){
            retMap.put("code","01");
            retMap.put("msg","修改失败");
        }
        return retMap;
    }

    @Override
    public Map<String, Object> findAll() {
        Map<String,Object> retMap=new HashMap<String,Object>();
        try{
            List<User> list=userReposity.findAll();
            retMap.put("code","00");
            retMap.put("msg","查询成功");
            retMap.put("data",list);
        }catch(Exception e){
            retMap.put("code","01");
            retMap.put("msg","查询失败");
        }
        return retMap;
    }

    @Override
    public Map<String, Object> findByName(String name) {
        Map<String,Object> retMap=new HashMap<String,Object>();
        try{
            Query query=new Query(Criteria.where("name").is(name));
            List<User> list=mongoTemplate.find(query,User.class);
            retMap.put("code","00");
            retMap.put("msg","新增成功");
            retMap.put("data",list);
        }catch(Exception e){
            retMap.put("code","01");
            retMap.put("msg","新增失败");
        }
        return retMap;
    }

    @Override
    public Map<String, Object> login(String name, String pwd) {
        Map<String,Object> retMap=new HashMap<String,Object>();
        try{
            User user=userReposity.findByName(name);
            if(user==null){
                retMap.put("msg","用户不存在");
                retMap.put("code","-00");
            }else{
                if(!pwd.equals(user.getPwd())){
                    retMap.put("msg","密码错误");
                    retMap.put("code","-00");
                }else{
                    retMap.put("msg","登陆成功");
                    retMap.put("data",user);
                    retMap.put("code","00");
                }
            }
        }catch(Exception e){
            retMap.put("code","01");
            retMap.put("msg","登陆失败");
        }
        return retMap;
    }
}
