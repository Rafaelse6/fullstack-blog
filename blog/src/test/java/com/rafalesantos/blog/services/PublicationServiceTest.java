package com.rafalesantos.blog.services;

import com.rafalesantos.blog.dto.PublicationDTO;
import com.rafalesantos.blog.entities.Publication;
import com.rafalesantos.blog.repositories.PublicationRepository;
import com.rafalesantos.blog.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class PublicationServiceTest {

    @InjectMocks
    private PublicationService service;

    @Mock
    private PublicationRepository repository;

    private long existingPublicationId, nonExistingPublicationId;
    private String publicationTitle;
    private Publication publication;
    private PublicationDTO publicationDTO;

    private PageImpl<Publication> page;

    @BeforeEach
    void setUp() throws Exception {
        existingPublicationId = 1L;
        nonExistingPublicationId = 2L;

        publicationTitle = "Exemplo de Título";
        publication = new Publication(existingPublicationId, publicationTitle, "Conteúdo de Exemplo", "Categoria", Instant.now(), Instant.now());
        publicationDTO = new PublicationDTO(publication);

        page = new PageImpl<>(List.of(publication));

        // Mocking do repositório
        Mockito.when(repository.findById(existingPublicationId)).thenReturn(Optional.of(publication));
        Mockito.when(repository.findById(nonExistingPublicationId)).thenReturn(Optional.empty());

        // Mocking da busca por título
        Mockito.when(repository.searchByTitle(any(), any())).thenReturn(page);
    }

    @Test
    public void findByIdShouldReturnPublicationDTOWhenIdExists() {
        // Teste para verificar o retorno do PublicationDTO
        PublicationDTO result = service.findById(existingPublicationId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existingPublicationId);
        Assertions.assertEquals(result.getTitle(), publication.getTitle());
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        // Teste para verificar a exceção quando a publicação não é encontrada
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(nonExistingPublicationId));
    }

    @Test
    public void findAllShouldReturnPageOfPublicationDTO() {
        // Teste para verificar se o método findAll retorna a página de PublicationDTO corretamente
        Pageable pageable = PageRequest.of(0, 10);
        Page<PublicationDTO> result = service.findAll(publicationTitle, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getTotalElements() > 0);  // Verifica se a página contém elementos
        Assertions.assertEquals(result.getContent().get(0).getTitle(), publication.getTitle());  // Verifica o título da publicação
    }
}

