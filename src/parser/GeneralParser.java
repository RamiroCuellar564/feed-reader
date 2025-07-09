package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;



/*Esta clase modela los atributos y metodos comunes a todos los distintos tipos de parser existentes en la aplicacion*/
public abstract class GeneralParser {
	protected String filePath;
	protected File file;
	
	protected GeneralParser(String filePath, File file) {
        this.filePath = filePath;
        this.file = file;
    }
	
	public  String getFilePath() {
		return filePath;
	}
	
}
