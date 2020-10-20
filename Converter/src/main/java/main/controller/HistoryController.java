package main.controller;

import lombok.SneakyThrows;
import main.base.user.User;
import main.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;

@Controller
public class HistoryController {

    @Autowired
    private FileService fileService;


    @SneakyThrows
    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<InputStreamResource> download(@AuthenticationPrincipal User user) {

        File file = fileService.fileCreateAndWriter(user);
        MediaType mediaType = fileService.getMediaType(file.getName());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; History=\"" + file.getName() + "\"")
                .contentType(mediaType)
                .body(resource);
    }
}
