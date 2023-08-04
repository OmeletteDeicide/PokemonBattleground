package projet.jonathan_simon.pokemon.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PDFController {

    @GetMapping("/download-pdf")
    public ResponseEntity<Resource> downloadPdf() throws IOException {
        // Chemin vers le fichier PDF
        String filePath = "src/main/resources/static/pdf/Jonathan_MARTIN_Simon_BARTHE_CDC.pdf";

        // Lisez le fichier en tant que ressource
        Resource resource = new org.springframework.core.io.UrlResource(Paths.get(filePath).toUri());

        // Déterminez le type de contenu MIME du fichier PDF
        String contentType = "application/pdf";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}




