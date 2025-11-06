package com.isulan.financial.service;

import com.isulan.financial.model.Transaction;
import com.isulan.financial.model.User;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReceiptService {

    
    public byte[] generateReceiptPDF(Transaction transaction, User user) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        Paragraph header = new Paragraph("TRANSACTION RECEIPT")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(header);
        String receiptNumber = "RCP-" + transaction.getId() + "-" + 
                System.currentTimeMillis();
        String generatedDate = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss"));
        
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Receipt Number: " + receiptNumber).setBold());
        document.add(new Paragraph("Generated: " + generatedDate));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("USER INFORMATION").setBold().setFontSize(12));
        Table userTable = new Table(UnitValue.createPercentArray(new float[]{30, 70}));
        userTable.setWidth(UnitValue.createPercentValue(100));
        
        userTable.addCell("Name:");
        userTable.addCell(user.getName());
        userTable.addCell("Email:");
        userTable.addCell(user.getEmail());
        
        document.add(userTable);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("TRANSACTION DETAILS").setBold().setFontSize(12));
        Table transactionTable = new Table(UnitValue.createPercentArray(new float[]{30, 70}));
        transactionTable.setWidth(UnitValue.createPercentValue(100));
        
        transactionTable.addCell("Type:");
        transactionTable.addCell(transaction.getType());
        
        transactionTable.addCell("Category:");
        transactionTable.addCell(transaction.getCategory() != null ? 
                transaction.getCategory() : "N/A");
        
        transactionTable.addCell("Description:");
        transactionTable.addCell(transaction.getDescription() != null ? 
                transaction.getDescription() : "N/A");
        
        transactionTable.addCell("Amount:");
        transactionTable.addCell(String.format("₱%.2f", transaction.getAmount()));
        
        transactionTable.addCell("Date:");
        transactionTable.addCell(transaction.getDate().format(
                DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        
        document.add(transactionTable);
        document.add(new Paragraph("\n\n"));
        Paragraph footer = new Paragraph("This is a computer-generated receipt.\n" +
                "Personal Financial Management System")
                .setFontSize(8)
                .setTextAlignment(TextAlignment.CENTER)
                .setItalic();
        document.add(footer);

        document.close();
        return baos.toByteArray();
    }
}
