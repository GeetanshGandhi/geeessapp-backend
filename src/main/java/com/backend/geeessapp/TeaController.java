package com.backend.geeessapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tea")
@CrossOrigin
public class TeaController {

    @Autowired
    TeaService teaService;

    @PostMapping("/save")
    public Tea save(@RequestParam("author") String author,
                     @RequestParam("content") String content){
        return teaService.save(author, content);
    }

    @GetMapping("/getAll")
    public List<Tea> getall(){
        return teaService.getAll();
    }

    @GetMapping("/deleteall")
    void deleteall(){
        teaService.deleteall();
    }
}
