package com.backend.geeessapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TeaService {
    @Autowired
    private TeaRepository teaRepository;

    public Tea save(String author, String content){
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
        return teaRepository.save(tea);
    }

    List<Tea> getAll(){
        //delete older tea first
        LocalDateTime cutoff = LocalDateTime.now().minusHours(36);
        Date cutoffDate = java.sql.Timestamp.valueOf(cutoff);
        teaRepository.deleteOlderRecords(cutoffDate);
        return teaRepository.findAllByOrderByDateDesc();
    }

    void deleteall(){
        teaRepository.deleteAll();
    }
}
