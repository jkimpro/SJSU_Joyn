package com.example.junhyuk.sjsu_client;

import org.json.JSONObject;

public class UserProfileVO {

    private int userID;
    private JSONObject sport;
    private int[] favorite;
    private int[] friends;
    private int[] matchID;

    public int getUserID() {
        return userID;
    }

    public JSONObject getSport() {
        return sport;
    }

    public int[] getFavorite() {
        return favorite;
    }

    public int[] getFriends() {
        return friends;
    }

    public int[] getMatchID() {
        return matchID;
    }

    public void setUserID(int id) {
        this.userID = id;
    }

    public void setSport(JSONObject sport) {
        this.sport = sport;
    }

    public void setFavorite(int[] favorite) {
        this.favorite = favorite;
    }

    public void setFriends(int[] friends) {
        this.friends = friends;
    }

    public void setMatchID(int[] matchID) {
        this.matchID = matchID;
    }

}
