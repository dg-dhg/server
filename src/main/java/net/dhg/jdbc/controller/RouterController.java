package net.dhg.jdbc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/loginUser")
public class RouterController {
    @RequestMapping({"/login"})
    public ModelAndView login(HttpServletResponse response, HttpServletRequest request) throws Exception {
        return new ModelAndView("login");
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpServletResponse response) throws Exception {
        return new ModelAndView("index");
    }

    @RequestMapping("/level{level}/{id}")
    public  ModelAndView  toLevel(@PathVariable("level") String level,@PathVariable("id") String id ,HttpServletResponse response) throws Exception{
        String url=String.format("level%s/%s",level,id);
        System.out.println("debug====>"+url);
        return new ModelAndView(url);
    }
}
