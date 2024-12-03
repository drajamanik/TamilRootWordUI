package org.wotsoc.tamilroot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
  
@Controller
public class MainController {
  
    @RequestMapping("/HeadWord")
    public String welcome() {
        return "index";
    }
}