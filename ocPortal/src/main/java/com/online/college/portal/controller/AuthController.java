package com.online.college.portal.controller;

//用户登录注册

import com.online.college.common.web.JsonView;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthUserService authUserService;

    //跳转注册页面
    @RequestMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("auth/register");
    }

    //实现注册
    @RequestMapping("/doRegister")
    @ResponseBody
    public String doRegister(AuthUser authUser){
        AuthUser temUser = authUserService.getByUsername(authUser.getUsername());
        if(temUser!=null){
            return JsonView.render(1);
        }else{
            authUserService.createSelectivity(authUser);
            return JsonView.render(0);
        }

    }
}
