package pe.edu.upc.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	public Resource load(String filename) throws MalformedURLException;//carga el recurso y el malfor... te devuelve un error de excepcion

	public String copy(MultipartFile file) throws IOException;//Copia tu archivo , que subes Multipartfile es una archivo que se usara momemntotanemante del disco  y el usuarios es responsable de lo que hace
	
	boolean delete(String filename);//elimina un archivo
}
