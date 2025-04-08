package com.backend.geeessapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/update")
public class UpdateController{
    @Autowired
    UpdateService updateService;
    @PostMapping("/save")
    public void saveUpdate(@RequestParam("caption") String caption,
                           @RequestParam("image") MultipartFile image,
                           @RequestParam("author") String author){
        updateService.saveUpdate(caption, author, image);
    }

    @PostMapping("/latestForUser")
    public Updates getLatestForUser(@RequestParam("user") String user){
        return updateService.getLatestForUser(user);
    }
    @GetMapping("/getall")
    public List<Updates> getAll(){
        return updateService.getall();
    }
    @GetMapping("/deleteall")
    public void deleteall(){
        updateService.deleteall();
    }

    @GetMapping("/temp")
    public void temp(){
        updateService.temp();
    }
}
