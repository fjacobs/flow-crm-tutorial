package com.example.application.argo.data.repository;

import com.example.application.argo.data.entity.Account;
import com.example.application.argo.data.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, String> { }
