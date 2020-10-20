package main.service;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class MediaService {


    public  MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {

        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}
