package com.jnb.episodecounter.api.controllers;

import com.jnb.episodecounter.api.dtos.ContentRecordDto;
import com.jnb.episodecounter.api.models.ContentModel;
import com.jnb.episodecounter.api.repositories.ContentRepository;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ContentController {

    @Autowired
    ContentRepository contentRepository;

    @PostMapping("/contents")
    public ResponseEntity<ContentModel> saveContent(@RequestBody @Valid ContentRecordDto contentRecordDto) {
        var contentModel = new ContentModel();
        BeanUtils.copyProperties(contentRecordDto, contentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(contentRepository.save(contentModel));
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @GetMapping("/contents")
    public ResponseEntity<List<ContentModel>> getCatalog() {
        List<ContentModel> catalogList = contentRepository.findAll();
        if (!catalogList.isEmpty()) {
            for (ContentModel content : catalogList) {
                UUID id = content.getCod();
                content.add(linkTo(methodOn(ContentController.class).getContent(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(catalogList);
    }
    @GetMapping("/contents/{id}")
    public ResponseEntity<Object> getContent(@PathVariable(value = "id") UUID id) {
        Optional<ContentModel> content = contentRepository.findById(id);
        if (content.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found.");
        }
        content.get().add(linkTo(methodOn(ContentController.class).getCatalog()).withRel("Catalog List"));
        return ResponseEntity.status(HttpStatus.OK).body(content.get());
    }

    @PutMapping("/contents/{id}")
    public ResponseEntity<Object> updateContent(@PathVariable(value = "id") UUID id, @RequestBody @Valid ContentRecordDto contentRecordDto) {
        Optional<ContentModel> content = contentRepository.findById(id);
        if (content.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found.");
        }
        var contentModel = content.get();
        BeanUtils.copyProperties(contentRecordDto, contentModel);
        return ResponseEntity.status(HttpStatus.OK).body(contentRepository.save(contentModel));
    }

    @DeleteMapping("/contents/{id}")
    public ResponseEntity<Object> deleteContent(@PathVariable(value = "id") UUID id) {
        Optional<ContentModel> content = contentRepository.findById(id);
        if (content.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found.");
        }
        contentRepository.delete(content.get());
        return ResponseEntity.status(HttpStatus.OK).body("Content deleted successfully");
    }
}
