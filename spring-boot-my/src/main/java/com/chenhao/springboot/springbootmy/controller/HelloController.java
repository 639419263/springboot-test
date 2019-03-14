package com.chenhao.springboot.springbootmy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chenhao.springboot.springbootmy.entity.User;
@RestController
public class HelloController {
    @RequestMapping("/hello.do")
    public String index(){
        StringBuilder sb=new StringBuilder();
        sb.append("<html>");
        sb.append("<head><meta http-equiv='content-type' content='text/html;charset=utf-8'/></head>");
        sb.append("<body style='background-color:green'>");
        sb.append("  <h2 align='center'>hello world and hello shenzhen</h2>");
        sb.append("<table>");
        sb.append("<tr><td>名称</td><td>年龄</td><td>性别</td><td>配偶</td></tr>");
        sb.append("<tr><td>貂蝉</td><td>25</td><td>女</td><td>吕布，董卓</td></tr>");
        sb.append("<tr><td>王昭君</td><td>22</td><td>女</td><td>匈奴父子</td></tr>");
        sb.append("<tr><td>西施</td><td>23</td><td>女</td><td>吴王勾践</td></tr>");
        sb.append("<tr><td>杨贵妃</td><td>26</td><td>女</td><td>唐玄宗</td></tr>");
        sb.append("<tr><td>陈圆圆</td><td>26</td><td>女</td><td>吴三桂</td></tr>");
        sb.append("</body></html>");
        System.out.println("打印结果::"+sb.toString());
        return sb.toString();
    }
    @RequestMapping("/user.do")
    public String showMsg(){
         User user=new User();
         user.setAge(30);
         user.setGender("f");
         user.setHome("美国达拉斯");
         user.setJob("颠沛流离，四处奔波谋生存的打工者");
         user.setName("码农");
         user.setPwd("1234567890");
         return user.toString();
    }


}
