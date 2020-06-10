package net.dhg.jdbc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/loginUser")
@Slf4j
public class RouterController {
    @RequestMapping({"/login"})
    public ModelAndView login(HttpServletResponse response, HttpServletRequest request) throws Exception {
        return new ModelAndView("login");
    }

    @RequestMapping({"/index", "/"})
    public ModelAndView index(HttpServletResponse response,HttpServletRequest request) throws Exception {

        return new ModelAndView("index");
    }

    @RequestMapping("/level{level}/{id}")
    public ModelAndView toLevel(@PathVariable("level") String level, @PathVariable("id") String id, HttpServletRequest request) {
        String url = String.format("level%s/%s", level, id);
        System.out.println("debug====>" + url);
        return new ModelAndView(url);
    }
}
