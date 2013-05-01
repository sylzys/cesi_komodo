package classes;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;
import java.awt.Desktop;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyPdf {
    
    public static String prefix = "";

    private String FILE = "c:/temp/";
    
    public String id;
    
    public PdfWriter instance;
    
    public String name;

    public String getFILE() {
        return FILE;
    }
    public Document document;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public MyPdf(String name) {       
        this.name = name;
    }
    
    public void setInstance(){
    try {
            document = new Document();
            this.id = name;
            Date date = new Date();
            instance = PdfWriter.getInstance(document, new FileOutputStream(FILE + "/"+prefix +"-" + name + "-"+ date.getTime()+  ".pdf"));
            
            document.open();
        } catch (Exception e) {
        }
    }

    public void createQrcode(String key) {
        Image qr_image = null;
        BarcodeQRCode my_code = new BarcodeQRCode(key, 100, 100, null);
        try {
            qr_image = my_code.getImage();
            document.add(qr_image);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    public void addMetaData() {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    public void addTitlePage(Date date, String ref, String description)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        String header = "PlastProd\n";
        header += "Date :" + date.toLocaleString() + "\n";
        header += "Réf: " + ref + "\n";
        header += "Description : " + description + "\n";


        preface.add(new Paragraph(header, catFont));
        addEmptyLine(preface, 2);

        document.add(preface);
    }

    public void addTable(String[][] data) throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Référence"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Désignation"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantité"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        for (int i = 0; i < data.length; i++) {
            table.addCell(data[i][0]);
            table.addCell(data[i][1]);
            table.addCell(data[i][2]);
        }
        try {
            document.add(table);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addRow(String text) {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph(text, catFont));
        addEmptyLine(preface, 2);
        try {
            document.add(preface);
        } catch (DocumentException ex) {
            Logger.getLogger(MyPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    public void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void savePdf(String path) {
        File file = new File(path);
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
                //Runtime.getRuntime().exec(file.getAbsolutePath()); 
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
               instance.close();
            }

        } else {
            System.err.println("Desktop not supported");
        }
    }
}