package Models;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PDF {

    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);


    public void createPDF(String Property_ID) {
        String FILE = "src/PDFFiles/Property's information.pdf";
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addImage(document);
            addTitlePage(document);
            createTable(document, Property_ID);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        //  Here we add the big header.
        preface.add(new Paragraph("Information about the property", catFont));

        addEmptyLine(preface, 1);

        // Here we add the main text.
        preface.add(new Paragraph(
                "Welcome to DREAM HOUSE\nWe would like to thank you for choosing Dream House" +
                        "\nWe would like to provide you with information on the property you have requested",
                smallBold));

        addEmptyLine(preface, 4);

        document.add(preface);

    }


    private void createTable(Document document, String Property_ID) throws DocumentException, SQLException {
        PdfPTable table = new PdfPTable(6);

        PdfPCell c1 = new PdfPCell(new Phrase("Property number", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Region", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Address", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Area", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fees", smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);


        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        PreparedStatement preparedStatement = null;

        String query = "Select Property_ID, Region, Address, Area, Price, fees from property where Property_ID = " + Property_ID;
        ResultSet resultSet = dataBaseHandler.execQuery(query);

        try {
            while (resultSet.next()) {
                int propertyID = resultSet.getInt("Property_ID");
                String region = resultSet.getString("Region");
                String address = resultSet.getString("Address");
                int area = resultSet.getInt("Area");
                int price = resultSet.getInt("Price");
                int fees = resultSet.getInt("fees");


                table.addCell(String.valueOf(propertyID));
                table.addCell(region);
                table.addCell(address);
                table.addCell(String.valueOf(area));
                table.addCell(String.valueOf(price));
                table.addCell(String.valueOf(fees));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        document.add(table);
        message(document);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));

        }
    }

    private void addImage(Document document) throws IOException, DocumentException {
        document.add(Image.getInstance("src/Resources/Logo.png"));

    }

    private void message(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
        paragraph.add(new Paragraph(
                "For more information contact our customer service via telephone: 076-000 00 00" +
                        "\nor via E-mail dreamhousesup@gmail.com"
                        + "\n\n\nDREAM HOUSE.",
                smallBold));
        document.add(paragraph);
    }
}

