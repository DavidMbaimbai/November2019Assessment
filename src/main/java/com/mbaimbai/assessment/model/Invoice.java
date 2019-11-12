package com.mbaimbai.assessment.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "INVOICE")
public class Invoice implements Serializable {

    private static final Long serialId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name="CLIENT")
    private String client;

    @Column(name = "VAT_RATE")
    private Long vatRate;

    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date invoiceDate;

    @Transient
    private BigDecimal vat;

    @Transient
    private BigDecimal subTotal;

    @Transient
    private BigDecimal total;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<LineItem> lineItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public BigDecimal getVat(){
        BigDecimal v = getSubTotal().multiply(new BigDecimal(vatRate / 100.00));
        v.setScale(2, RoundingMode.HALF_UP);
        return v;
    }

    public BigDecimal getTotal(){
        BigDecimal t = getSubTotal().add(getVat());
        t.setScale(2, RoundingMode.HALF_UP);
        return t;
    }

    public BigDecimal getSubTotal(){
        BigDecimal subT = new BigDecimal(0);
        lineItems.forEach(lineItem -> {
            subT.add(lineItem.getLineItemTotal());
        });
        subT.setScale(2, RoundingMode.HALF_UP);
        return subT;
    }
}
