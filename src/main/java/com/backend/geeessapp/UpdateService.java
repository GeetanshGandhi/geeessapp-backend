package com.backend.geeessapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
            ZonedDateTime zonal = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
            Instant inst = zonal.toInstant();
            update.setDate(Date.from(inst));
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
            System.out.println("Error in saving: ");
            E.printStackTrace();
        }
    }

    public Updates getLatestForUser(String user){
        //deleting older records
        LocalDateTime cutoff = LocalDateTime.now().minusHours(36);
        Date cutoffDate = java.sql.Timestamp.valueOf(cutoff);
        updateRepository.deleteOlderRecords(cutoffDate);

        //actual getLatestForUser starts here
        String author = user.equals("geetansh")? "shreshtha":"geetansh";
        Updates update = updateRepository.getLatestForUser(author);
        if(update == null){
            return new Updates();
        }
        return update;
    }

    public void deleteall(){
        updateRepository.deleteAll();
    }
    public List<Updates> getall(){
        List<Updates> all = updateRepository.findAll();
        all.sort((a1, a2) -> a2.getDate().compareTo(a1.getDate()));
        return all;
    }

    public void temp(){
        List<Updates> all = getall();
        Updates curr = all.get(0);
        curr.setAuthor("shreshtha");
        updateRepository.save(curr);
    }
}
