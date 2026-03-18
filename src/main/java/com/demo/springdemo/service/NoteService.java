package com.demo.springdemo.service;

import com.demo.springdemo.model.Note;
import com.demo.springdemo.model.NoteRequestDTO;
import com.demo.springdemo.model.NoteResponseDTO;
import com.demo.springdemo.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final OpenAiService openAiService;

    public NoteResponseDTO createNode(NoteRequestDTO dto) {
        Note note = Note.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        return toDTO(note);
    }

    public List<NoteResponseDTO> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public NoteResponseDTO getNoteById(Long id) {
        return toDTO(findOrThrow(id));
    }

    public NoteResponseDTO updateNote(Long id, NoteRequestDTO dto) {
        Note note = findOrThrow(id);
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        return toDTO(noteRepository.save(note));
    }

    public String summarizeNoteContent(Long id) {
        Note note = findOrThrow(id);
        return openAiService.summarize(note.getContent());
    }

    
    // Helper method to find a note by ID or throw an exception if not found
    private Note findOrThrow(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
    }

    // Helper method to convert Note entity to NoteResponseDTO
    private NoteResponseDTO toDTO(Note note) {
        return NoteResponseDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .build();
    }
}
