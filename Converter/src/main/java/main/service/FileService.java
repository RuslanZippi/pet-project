package main.service;

import lombok.SneakyThrows;
import main.base.currency.HistoryBase;
import main.base.user.User;
import main.reposiroty.currency.HistoryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Autowired
    private HistoryRep historyRep;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private MediaService mediaService;

    private final Path rootLocation = Paths.get("src/main/historyFile");


    @SneakyThrows
    public File fileCreateAndWriter(User user) {
        File file = new File("src/main/historyFile/history.txt");
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        Iterable<HistoryBase> historyBases = historyRep.findAllByUser(user);
        String row;

        String firstRow = "Дата" + "\t" + "\t" + "\t" + "Покупка" + "\t" + "\t" + "Продажа" + "\t" + "\t" + "Количество покупки" + "\t" + "\t" + "Количество продажи" + "\n";
        fileWriter.write(firstRow);
        for (HistoryBase base : historyBases) {
            row = base.getDate() + "\t" + "\t"
                    + base.getInNameValue() + "\t" + "\t"
                    + base.getOutNameValue() + "\t" + "\t"
                    + base.getInValue() + "\t" + "\t"
                    + base.getOutValue();
            fileWriter.write(row + "\n");
        }
        fileWriter.close();
        return file;
    }

    private Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public MediaType getMediaType(String fileName){
        MediaType mediaType = mediaService.getMediaTypeForFileName(servletContext,fileName);
        return mediaType;
    }
}
