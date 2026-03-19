package com.demo.springdemo;


import com.demo.springdemo.model.NoteRequestDTO;
import com.demo.springdemo.model.NoteResponseDTO;
import com.demo.springdemo.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponseDTO> create(@Valid @RequestBody NoteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNode(dto));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getAll() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> update(@PathVariable Long id, @Valid @RequestBody NoteRequestDTO dto) {
        return ResponseEntity.ok(noteService.updateNote(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/summarize")
    public ResponseEntity<String> summarize(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.summarizeNoteContent(id));
    }
}
