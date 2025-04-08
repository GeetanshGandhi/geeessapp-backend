package com.backend.geeessapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class TeaService {
    @Autowired
    private TeaRepository teaRepository;

    public void save(String author, String content){
        List<Tea> teas = teaRepository.findAll();
        boolean[] hs = new boolean[150];
        int id = -1;
        for(Tea i: teas){
            hs[i.getId()] = true;
        }
        for(int i = 0; i<150; i++){
            if(!hs[i]){
                id = i;
                break;
            }
        }
        Tea tea = new Tea(id, author, content, new Date());
        teaRepository.save(tea);
    }

    List<Tea> getAll(){
        return teaRepository.findAll();
    }
}
