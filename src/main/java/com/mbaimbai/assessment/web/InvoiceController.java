package com.mbaimbai.assessment.web;

import com.mbaimbai.assessment.model.Invoice;
import com.mbaimbai.assessment.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(value = "/invoices", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Invoice addInvoice(@RequestBody Invoice invoice){
        return invoiceService.addInvoice(invoice);
    }

    @GetMapping(value = "/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Invoice> viewAllInvoices(){
        return invoiceService.viewAllInvoices();
    }

    @GetMapping(value = "/invoices/{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Invoice viewInvoice(@PathVariable Long invoiceId){
        return invoiceService.viewInvoice(invoiceId);
    }

}
