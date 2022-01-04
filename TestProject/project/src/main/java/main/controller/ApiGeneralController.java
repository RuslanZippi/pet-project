package main.controller;

import lombok.RequiredArgsConstructor;
import main.api.response.InitResponse;
import main.api.response.SettingResponse;
import main.api.response.TagResponse;
import main.api.response.CalendarPostResponse;
import main.service.PostService;
import main.service.ServiceSetting;
import main.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiGeneralController {

    private final PostService postService;

    private final InitResponse initResponse;

    private final ServiceSetting serviceSetting;

    private final TagService tagService;


    @GetMapping("/init")
    private InitResponse init(){
        return initResponse;
    }

    @GetMapping("/settings")
    private SettingResponse setting(){
        return serviceSetting.getGlobalSetting();
    }

    @GetMapping("/tag")
    private TagResponse tag(){
        return tagService.getTags();
    }

    @GetMapping("/calendar")
    private CalendarPostResponse calendar(){
        return postService.getPostByCalendar();
    }
}
