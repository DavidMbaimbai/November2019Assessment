package com.mbaimbai.assessment.model.repos;

import com.mbaimbai.assessment.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
