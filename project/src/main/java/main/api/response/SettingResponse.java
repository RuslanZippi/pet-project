package main.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import main.model.GlobalSetting;

import java.util.Map;

@Data
public class SettingResponse {

    @JsonProperty("MULTIUSER_MODE")
    private boolean multiuserMode;

    @JsonProperty("POST_PREMODERATION")
    private boolean postPremoderation;

    @JsonProperty("STATISTICS_IS_PUBLIC")
    private boolean statisticInPublic;

    public SettingResponse(Iterable<GlobalSetting> lists) {
        for (GlobalSetting globalSetting : lists){
            if (globalSetting.getCode().equals("MULTIUSER_MODE")){
                this.multiuserMode = globalSetting.getValue().equals("1");
            }
            else if (globalSetting.getCode().equals("POST_PREMODERATION")){
                this.postPremoderation = globalSetting.getValue().equals("1");
            }
            else {
                this.statisticInPublic = globalSetting.getValue().equals("1");
            }
        }
    }


}