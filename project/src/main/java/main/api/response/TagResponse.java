package main.api.response;

import lombok.Data;
import main.api.response.dto.TagToResponse;

import java.util.List;

@Data
public class TagResponse {

    private List<TagToResponse> tags;
}
