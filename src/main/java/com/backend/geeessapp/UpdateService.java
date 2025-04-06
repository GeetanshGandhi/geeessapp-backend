package com.backend.geeessapp;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.URIParameter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UpdateService {

    @Autowired
    UpdateRepository updateRepository;

    public void saveUpdate(String caption, String author, MultipartFile image){
        try{
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg",output);
            Updates update = new Updates();
            update.setImage(output.toByteArray());
            update.setCaption(caption);
            update.setAuthor(author);
            update.setDate(new Date());
            List<Updates> updates = updateRepository.findAll();
            HashSet<Integer> hs = new HashSet<>();
            for(Updates u: updates){
                hs.add(u.getId());
            }
            for(int i = 1; i<=100; i++){
                if(!hs.contains(i)){
                    update.setId(i);break;
                }
            }
            updateRepository.save(update);
        } catch (IOException E){
            E.printStackTrace();
        }
    }

    public Updates getLatestForUser(String user){
        String author = user.equals("geetansh")? "shreshtha":"geetansh";
        Updates update = updateRepository.getLatestForUser(author);
        if(update == null){
            return new Updates();
        }
        return update;
    }
    public List<Updates> getAllForUser(String user){
        String author = user.equals("geetansh")? "shreshtha":"geetansh";
        return updateRepository.getAllByUser(author);
    }
    public void deleteall(){
        updateRepository.deleteAll();
    }
    public List<Updates> getall(){
        return updateRepository.findAll();
    }

    public void tempUpdate(){
        Updates update = updateRepository.findById(2).get();
        update.setAuthor("shreshtha");
        updateRepository.save(update);
    }
}
