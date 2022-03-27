package tn.esprit.spring.womanarea51.services;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.spring.womanarea51.entities.Contract;


public class ContractPDFExporter {

	private List<Contract> listContracts ;
	
	
	public ContractPDFExporter(List<Contract> listContracts) {
		this.listContracts = listContracts;
	}

	public void writeTableHeader(PdfPTable table){
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(8);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Offer", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("company", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("salaire", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("Contract", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("startDate", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("endtDate", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("email", font));
        table.addCell(cell);
     
	}

	private void writeTableData(PdfPTable table) {
		for(Contract contract :listContracts){
		
			table.addCell(contract.getUserName());
			table.addCell(contract.getOfferTitle());
			table.addCell(contract.getCompanyName());
			table.addCell(contract.getSalaire());
			table.addCell(String.valueOf(contract.getTypeContract()));
			table.addCell(String.valueOf(contract.getStartDate()));
			table.addCell(String.valueOf(contract.getEndDate()));
			table.addCell(contract.getEmail());
		}
		     
		
	}
	 public void export(HttpServletResponse response) throws DocumentException, IOException {
		 Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("List of Contracts", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(8);
	        table.setWidthPercentage(100f);
	        
	        table.setSpacingBefore(30);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	 }
	
}
