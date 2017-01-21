package main.java.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import main.java.dao.UploadDao;

/**
 * Servlet implementation class UploadOferta
 */
@WebServlet("/uploadoferta")
public class UploadOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UploadDao uploadd;
       CadastroServlet cadastro;
      // CadastroServlet cadastro = new CadastroServlet();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadOferta() {
        super();
        uploadd = new UploadDao();
        cadastro = new CadastroServlet();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		   
		  if(!ServletFileUpload.isMultipartContent(request)){
		   out.println("Selecione um arquivo");
		   return; 
		  }
		  FileItemFactory itemfactory = new DiskFileItemFactory(); 
		  ServletFileUpload upload = new ServletFileUpload(itemfactory);
		  try{
		   List<FileItem>  items = upload.parseRequest(request);
		   for(FileItem item:items){
		     
		    String contentType = item.getContentType();
		    if(!contentType.equals("image/png")){
		     out.println("Apenas imagens são aceitas");
		     continue;
		    }
		    File uploadDir = new File("C:/javaweb/MaoNaRoda/WebContent/img/fotodeperfil");
		    File file = File.createTempFile("img",".png",uploadDir);
		    item.write(file);
		    String imagem = ""+file;
		    Integer numeroimagem = imagem.length();
		    String valorimagem = imagem.substring(32,numeroimagem);
		    Integer id = cadastro.idofertaatual;
		    System.out.println(""+id);
			uploadd.alteraOferta(valorimagem, id);
		    response.sendRedirect("/MaoNaRoda/index/minhasofertas");
		   }
		  }
		  catch(FileUploadException e){
		    
		   out.println("Falha no upload");
		  }
		  catch(Exception ex){
		    
		   out.println("Erro ao salvar");
		  }
	}

}
