package br.vjps.tsi.crms.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet que permite o download de arquivos com base em um parâmetro "file" fornecido na URL. 
 * Configura a resposta HTTP para enviar o arquivo como um anexo para download.
 * 
 * @author Vinícius  J P Silva
 */

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém o parâmetro "file" da URL
        String filePath = request.getParameter("file");
        String fileName = getFileName(filePath);
        
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = "APPLICATION/OCTET-STREAM";
        if (null != fileName && !"".equals(fileName)) {
          mimeType = mimeTypesMap.getContentType(fileName);
        } else {
          fileName = "file.unknown";
        }
        
        if (filePath != null) {
        	response.setContentType(mimeType);
        	response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        	OutputStream out = response.getOutputStream();
        	FileInputStream in = new FileInputStream(filePath);
        	byte[] buffer = new byte[4096];
        	int length;
        	while ((length = in.read(buffer)) > 0) {
        		out.write(buffer, 0, length);
        	}
        	in.close();
        	out.flush();
    	} else {
    		response.getOutputStream().println("400 Bad Request, forneça os parâmetros necessários: /download?file=...");
        }
    }

    /**
     * Obtém o nome do arquivo a partir do caminho completo.
     *
     * @param filePath O caminho completo do arquivo.
     * @return O nome do arquivo extraído do caminho.
     */
    private String getFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf('/') + 1);
    }
}
