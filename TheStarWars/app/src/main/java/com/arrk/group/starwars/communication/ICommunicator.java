package com.arrk.group.starwars.communication;

import com.arrk.group.starwars.communication.constants.AliasConstants;
import com.arrk.group.starwars.communication.models.PeopleResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author SANDY
 */
public interface ICommunicator {

    @GET(AliasConstants.PEOPLE_URL)
    Call<PeopleResponseModel> people(@Query("page") int page);

}