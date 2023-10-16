package br.vjps.tsi.crms.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Fornece métodos para gerar um arquivo PDF a partir de imagens
 * e salvá-lo em um diretório específico.
 */
public final class PDFGenerator {
	
	public static final String PDF_DIRECTORY = "/src/main/resources/pdfs", 
			IMG_DIRECTORY_ECHOCARDIOGRAM = "/src/main/resources/images/echocardiogram/",
			IMG_DIRECTORY_ELECTROCARDIOGRAM = "/src/main/resources/images/electrocardiogram/";
	
	// Impede a instanciação de objetos desta classe.
	private PDFGenerator() {}
	
    /**
     * Gera um arquivo PDF a partir de uma lista de caminhos de imagem e o salva em um diretório
     * específico com um nome de arquivo fornecido.
     *
     * @param imagePaths      A lista de caminhos das imagens a serem incluídas no PDF.
     * @param aditionalData   Mensagem adicional a ser incluída no PDF.
     * @param outputPath      O diretório de saída onde o PDF será salvo.
     * @param outputFileName  O nome do arquivo PDF de saída.
     * 
     * @return O caminho completo para o arquivo PDF gerado.
     */
    public static String generatePDF(List<String> imagePaths, String aditionalData,  String outputPath, String outputFileName) {
        String outputPdfPath = outputPath + "/" + outputFileName;

        // Inicializa o documento PDF
        Document document = new Document(PageSize.A4);
        
        try {
            // Cria um objeto PdfWriter para gravar o PDF no arquivo de saída
            PdfWriter.getInstance(document, new FileOutputStream(outputPdfPath));

            // Abre o documento para escrita
            document.open();
            
            Paragraph title = new Paragraph("Sistema de Laudos de Cardiologia - Imagens do Exame\n\n");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            if(aditionalData != null)
            	document.add(new Paragraph(aditionalData));

            // Adiciona imagens ao documento
            for (String imagePath : imagePaths) {
                addImageToPdf(document, imagePath);
                document.add(new Paragraph("\n\n"));
            }

            // Fecha o documento
            document.close();

            return outputPdfPath;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void addImageToPdf(Document document, String imagePath) throws DocumentException, IOException {
        // Carrega a imagem do arquivo
        Image image = Image.getInstance(imagePath);

        // Ajusta o tamanho da imagem, se necessário
        image.scaleToFit(400, 300); // Largura x Altura
        image.setAlignment(Image.ALIGN_CENTER);

        // Adiciona a imagem ao documento
        document.add(image);
    }

}
