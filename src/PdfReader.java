import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfReader {
    public static void main(String[] args) {
        try {
            // Load PDF file from local path
            File pdfFile = new File("path_to_your_pdf"); //for example: C:/Users/MY PC/Downloads/mypdf.pdf
            PDDocument document = PDDocument.load(pdfFile);
            PDFTextStripper pdfTextStripper=new PDFTextStripper();
            String pdftextdata=pdfTextStripper.getText(document);
            System.out.println(pdftextdata);
            // Print total number of pages
//            System.out.println("PDF Loaded Successfully. Number of Pages: " + document.getNumberOfPages());

            // Close document to free resources
            document.close();
        } catch (IOException e) {
            System.out.println("Error loading PDF: " + e.getMessage());
        }
    }
}
