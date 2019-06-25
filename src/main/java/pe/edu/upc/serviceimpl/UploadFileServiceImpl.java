package pe.edu.upc.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.service.IUploadFileService;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	// CONSTANTE DEL ARCHIVO UPLOADS
	private final static String UPLOADS_FOLDER = "fotosvehi";//le indica el folder donde se va a guardar los archivos 

	@Override
	public Resource load(String filename) throws MalformedURLException {//carga el archivo
		Path pathFoto = getPath(filename);//es tipo path porque utilizara un archivo que existe dentro del equipo
		log.info("pathFoto: " + pathFoto);//el log carga un texto , pero en este caso lo hace con la imagen

		Resource recurso = new UrlResource(pathFoto.toUri());

		if (!recurso.exists() || !recurso.isReadable()) {//si no es un recurso posible de cargar , mostrara un error
			throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
		}
		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		// sirve para qe los archivos no tengan el mismo nombre
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		// Te brinda la ruta
		Path rootPath = getPath(uniqueFilename);

		log.info("rootPath: " + rootPath);//cargala ruta

		// obtiene los bytes de la imagen
		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		// obtiene la ruta del archivo
		Path rootPath = getPath(filename);
		// obtiene el archivo
		File archivo = rootPath.toFile();

		if (archivo.exists() && archivo.canRead()) {//elimina el archivo
			if (archivo.delete()) {
				return true;
			}
		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();//funciopn que indica la direccion 
	}

}
