package com.mbaimbai.assessment.service;

import com.mbaimbai.assessment.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice addInvoice(Invoice invoice);

    List<Invoice> viewAllInvoices();

    Invoice viewInvoice(Long invoiceId);
}
