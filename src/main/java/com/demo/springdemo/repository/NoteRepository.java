package com.demo.springdemo.repository;

import com.demo.springdemo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    //JpaRepository provides basic CRUD operations, so we don't need to define any methods here for now.
}
