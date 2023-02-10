package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {
}
