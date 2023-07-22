package com.info.javajediprimerapp.repository.publisher;

import com.info.javajediprimerapp.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID>{
}
