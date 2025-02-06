package com.rafalesantos.blog.controllers;

import com.rafalesantos.blog.dto.PublicationDTO;
import com.rafalesantos.blog.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/publications")
public class PublicationController {

    @Autowired
    public PublicationService publicationService;


    @GetMapping
    public ResponseEntity<Page<PublicationDTO>> findAll(@RequestParam(name = "title", defaultValue = "") String title,
                                                        Pageable pageable){

        Page<PublicationDTO> dto = publicationService.findAll(title, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PublicationDTO> findById(@PathVariable Long id){
        PublicationDTO dto = publicationService.findById(id);
        return ResponseEntity.ok(dto);
    }
}
