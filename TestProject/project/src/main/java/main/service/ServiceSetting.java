package main.service;

import main.api.response.SettingResponse;
import main.repository.GlobalSettingRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceSetting {

    @Autowired
    private GlobalSettingRep globalSettingRep;



    public SettingResponse getGlobalSetting(){
        return new SettingResponse(globalSettingRep.findAll());
    }



}
