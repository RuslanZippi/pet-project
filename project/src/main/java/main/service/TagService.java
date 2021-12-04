package main.service;

import main.api.response.TagResponse;
import main.api.response.dto.TagToResponse;
import main.model.Tag;
import main.model.enums.Status;
import main.repository.TagRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRep tagRep;

    public TagResponse getTags() {
        TagResponse tagResponse = new TagResponse();
        tagResponse.setTags(getTagList());
        return tagResponse;
    }

    public TagResponse getTagsByPostId(int id) {
        TagResponse tagResponse = new TagResponse();
        tagResponse.setTags(getTagListByPostId(id));

        return tagResponse;
    }

    private float getWeightTag(String tagName) {
        float weight;
        float dWeightMax;
        float dWeightNeedTag;
        float k;
        float count;
        float countNeedTag;

        count = tagRep.count(Status.ACCEPTED, true);

        countNeedTag = tagRep.countTagByName(tagName, Status.ACCEPTED, true);

        dWeightNeedTag = countNeedTag / count;

        dWeightMax = tagRep.countTagByName(tagRep.getMostPopularTag(Status.ACCEPTED, true, PageRequest.of(0, 1)).get(0).getName(), Status.ACCEPTED, true) / count;

        k = 1 / dWeightMax;

        weight = dWeightNeedTag * k;
        return weight;
    }

    private List<TagToResponse> getTagListByPostId(int id) {
        List<TagToResponse> tagToResponses = new ArrayList<>();
        List<Tag> tagList = tagRep.getTagByPostId(id);
        for (Tag t : tagList) {
            tagToResponses.add(puckingTags(t));
        }
        return tagToResponses;
    }

    private List<TagToResponse> getTagList() {
        List<TagToResponse> tagToResponses = new ArrayList<>();
        List<Tag> tagList = tagRep.findAll();
        for (Tag t : tagList) {
            tagToResponses.add(puckingTags(t));
        }
        return tagToResponses;
    }

    private TagToResponse puckingTags(Tag tag) {
        TagToResponse tagToResponse = new TagToResponse();
        tagToResponse.setName(tag.getName());
        tagToResponse.setWeight(getWeightTag(tagToResponse.getName()));

        return tagToResponse;
    }
}
