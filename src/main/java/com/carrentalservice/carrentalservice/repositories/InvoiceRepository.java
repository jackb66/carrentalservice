package com.carrentalservice.carrentalservice.repositories;

import com.carrentalservice.carrentalservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
    @Repository
    public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
