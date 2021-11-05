package com.unifap.caronaeunifap.models.modelsforjson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.unifap.caronaeunifap.models.User;

public class FacebookFriendForJson {

    @SerializedName("mutual_friends")
    private List<User> mutualFriends;
    @SerializedName("total_count")
    private int totalCount;

    public List<User> getMutualFriends() {
        return mutualFriends;
    }

    public int getTotalCount() {
        return totalCount;
    }

}
