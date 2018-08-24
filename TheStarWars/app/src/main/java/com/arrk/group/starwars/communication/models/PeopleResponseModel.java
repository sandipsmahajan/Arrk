package com.arrk.group.starwars.communication.models;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author SANDY
 */
public class PeopleResponseModel extends BaseResponseModel {

    @Expose
    private List<PeopleModel> results;

    public List<PeopleModel> getResults() {
        return results;
    }

    public void setResults(List<PeopleModel> results) {
        this.results = results;
    }
}
