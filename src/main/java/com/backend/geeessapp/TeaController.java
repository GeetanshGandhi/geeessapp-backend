package com.backend.geeessapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tea")
@CrossOrigin
public class TeaController {

    @Autowired
    TeaService teaService;

    @PostMapping("/save")
    public void save(@RequestParam("author") String author,
                     @RequestParam("content") String content){
        teaService.save(author, content);
    }
}
