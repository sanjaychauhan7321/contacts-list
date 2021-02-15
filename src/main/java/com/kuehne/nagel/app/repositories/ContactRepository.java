package com.kuehne.nagel.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuehne.nagel.app.models.ContactEntity;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

	Page<ContactEntity> findByName(String name, Pageable pageable);

}
