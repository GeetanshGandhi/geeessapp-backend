package com.backend.geeessapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdateRepository extends JpaRepository<Updates, Integer> {

    //to get latest update
    @Query(value = "SELECT * FROM updates WHERE author = :author ORDER BY creation_datetime DESC LIMIT 1", nativeQuery=
            true)
    Updates getLatestForUser(@Param("author") String author);

    //get all updates by user
    @Query(value = "SELECT * FROM updates WHERE author = :author", nativeQuery = true)
    List<Updates> getAllByUser(@Param("user") String user);
}
