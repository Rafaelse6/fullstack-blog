package com.rafalesantos.blog.services;

import com.rafalesantos.blog.dto.PublicationDTO;
import com.rafalesantos.blog.entities.Publication;
import com.rafalesantos.blog.repositories.PublicationRepository;
import com.rafalesantos.blog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Transactional(readOnly = true)
    public Page<PublicationDTO> findAll(String title, Pageable pageable){
        Page<Publication> list = publicationRepository.searchByTitle(title, pageable);
        return list.map(PublicationDTO::new);
    }

    @Transactional
    public PublicationDTO findById(Long id){
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new PublicationDTO(publication);
    }
}
