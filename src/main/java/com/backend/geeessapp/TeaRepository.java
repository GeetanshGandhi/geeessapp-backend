package com.backend.geeessapp;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TeaRepository extends JpaRepository<Tea, Integer> {

    List<Tea> findAllByOrderByDateDesc();

    //jpql query to delete items
    @Modifying
    @Transactional
    @Query("DELETE FROM Tea u WHERE u.date < :cutOffDate")
    void deleteOlderRecords(@Param("cutOffDate") Date cutOffDate);
}
