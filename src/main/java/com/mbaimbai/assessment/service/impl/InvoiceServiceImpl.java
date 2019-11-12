package com.mbaimbai.assessment.service.impl;

import com.mbaimbai.assessment.model.Invoice;
import com.mbaimbai.assessment.model.LineItem;
import com.mbaimbai.assessment.model.repos.InvoiceRepository;
import com.mbaimbai.assessment.model.repos.LineItemRepository;
import com.mbaimbai.assessment.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private LineItemRepository lineItemRepository;

    @Override
    public Invoice addInvoice(Invoice invoice) {
        Invoice saveInvoice = invoiceRepository.save(invoice);
        for (LineItem li:invoice.getLineItems()) {
            li.setInvoice(saveInvoice);
            lineItemRepository.save(li);
        }

        saveInvoice.setSubTotal(invoice.getSubTotal());
        saveInvoice.setVat(invoice.getVat());
        saveInvoice.setTotal(invoice.getTotal());
        return saveInvoice;
    }

    @Override
    public List<Invoice> viewAllInvoices() {
        List<Invoice> allInvoices = invoiceRepository.findAll();
        allInvoices.forEach(invoice -> {
            invoice.setSubTotal(invoice.getSubTotal());
            invoice.setVat(invoice.getVat());
            invoice.setTotal(invoice.getTotal());
        });
        return allInvoices;
    }

    @Override
    public Invoice viewInvoice(Long invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if(optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setSubTotal(invoice.getSubTotal());
            invoice.setVat(invoice.getVat());
            invoice.setTotal(invoice.getTotal());
            return invoice;
        }
        return null;
    }
}
