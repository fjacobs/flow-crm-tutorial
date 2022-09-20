package nl.argo360.inventory.argo.data.repository;

import nl.argo360.inventory.argo.data.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, String> { }
