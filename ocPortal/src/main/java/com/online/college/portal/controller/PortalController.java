package com.online.college.portal.controller;

import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping()
public class PortalController {

    @Autowired
    private IConstsSiteCarouselService constsSiteCarouselService;

    @Autowired
    private IPortalBusiness portalBusiness;

    //首页
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");

        //加载轮播
        List<ConstsSiteCarousel> carouselList = constsSiteCarouselService.queryCarousels(4);
        modelAndView.addObject("carouselList",carouselList);


        //课程分类(一级分类）
        List<ConstsClassifyVO> classifys = portalBusiness.queryAllClassify();

        //课程推荐
        portalBusiness.prepareRecomdCourses(classifys);
        modelAndView.addObject("classifys", classifys);

        return modelAndView;
    }

}
