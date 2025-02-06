package com.rafalesantos.blog.factories;

import com.rafalesantos.blog.dto.PublicationDTO;
import com.rafalesantos.blog.entities.Publication;

import java.time.Instant;

public class PublicationFactory {

    public static Publication createPublication() {
        Publication publication = new Publication();
        publication.setTitle("Exemplo de Post");
        publication.setContent("Este é um conteúdo de exemplo para testes.");
        publication.setCategory("Testes");

        Instant now = Instant.now();
        publication.setCreatedAt(now);
        publication.setUpdatedAt(now);

        return publication;
    }

    public static PublicationDTO createPublicationDTO() {
        Publication publication = createPublication();
        return new PublicationDTO(publication);
    }
}
