package com.rafalesantos.blog.repositories;

import com.rafalesantos.blog.entities.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

    @Query("SELECT obj FROM Publication obj " +
            "WHERE UPPER(obj.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    Page<Publication> searchByTitle(String title, Pageable pageable);
}
