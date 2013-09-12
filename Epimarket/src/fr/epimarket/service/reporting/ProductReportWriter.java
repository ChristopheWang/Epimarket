package fr.epimarket.service.reporting;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;

import fr.epimarket.model.Client;
import fr.epimarket.model.Orderline;

public class ProductReportWriter  extends AbstractDocumentWriter
{
    private String                          FILE_PATH                   = "./";


    public ProductReportWriter()        {}

    
    //for Web side
    public InputStream generate(List<Orderline> listFacture, Client c, String totalPrice) throws Exception
    {
        ByteArrayOutputStream buffer            = new ByteArrayOutputStream(1024);
        
        startDocument(buffer, PageSize.A4.rotate());
        
        document.open();
        publish(listFacture, c, totalPrice);
        //document.newPage();
        
        endDocument(document);
        buffer.close();
        
        return new ByteArrayInputStream(buffer.toByteArray());
    }
    
    public void publish(List<Orderline> listFacture, Client c, String totalPrice)
    {
        try
        {
            addNewParagraph("\r\n\r\nFacture Epimarket", fontBigBold, document, new Integer(Element.ALIGN_CENTER));
            addNewParagraph("\n", fontBigBold, document, new Integer(Element.ALIGN_CENTER));
            if (c.getSexe().equalsIgnoreCase("homme"))
            	addNewParagraph("Monsieur " + c.getLastName() + " " + c.getFirstName() + "\n", fontPlainBold, document, new Integer(Element.ALIGN_LEFT)); 
            else
            	addNewParagraph("Madame " + c.getLastName() + " " + c.getFirstName() + "\n", fontPlainBold, document, new Integer(Element.ALIGN_LEFT)); 
            addNewParagraph(c.getEmail() + "\n", fontPlainBold, document, new Integer(Element.ALIGN_LEFT)); 
            addNewParagraph(c.getPhone() + "\n", fontPlainBold, document, new Integer(Element.ALIGN_LEFT));
            addNewParagraph(c.getStreetNumber() + " " + c.getStreetName() + "\n", fontPlainBold, document, new Integer(Element.ALIGN_LEFT));
            addNewParagraph(c.getZipCode() + " " + c.getCity() + "\n", fontPlainBold, document, new Integer(Element.ALIGN_LEFT));
            addNewParagraph(c.getCountry() + "\n", fontPlainBold, document, new Integer(Element.ALIGN_LEFT));
            addNewParagraph(listFacture.get(0).getDate() + "\n\n\n", fontPlainBold, document, new Integer(Element.ALIGN_LEFT));
            PdfPTable t0     = getNewDefaultPTable(4);

            t0.setTotalWidth(new float[] {25, 45, 10, 20});

            for (Orderline o : listFacture)
            {
            	addCellToTable(t0, o.getProduct().getDesignation(),			fontPlainBold,  new Integer(Element.ALIGN_LEFT), 0);
            	addCellToTable(t0, o.getProduct().getDescription(),			fontPlainBold,  new Integer(Element.ALIGN_LEFT), 0);
            	addCellToTable(t0, "Quantité : " + o.getQuantity(), 		fontPlain,      new Integer(Element.ALIGN_LEFT), 0);
            	addCellToTable(t0, "Prix unitaire : " + o.getProduct().getPrice(),   fontPlain,      new Integer(Element.ALIGN_LEFT), 0);
            }
            addTableToContainer(t0, document);
            addNewParagraph("\r\n\r\nPrix total : " + totalPrice + " euros", fontPlain, document, new Integer(Element.ALIGN_RIGHT));  
            addNewParagraph("\r\n\r\nNous vous remercions de votre fidélité et à très bientôt !", fontBigBold, document, new Integer(Element.ALIGN_CENTER));  
            addNewParagraph("\r\n", fontPlain, document);
        }
        catch (Exception e) {e.printStackTrace();}        
    }

}
