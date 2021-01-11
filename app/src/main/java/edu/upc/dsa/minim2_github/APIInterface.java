package edu.upc.dsa.minim2_github;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIInterface {

    @GET("{user}")
    Call<Usuario> getUserInfo(@Path("user") String user);

    @GET ("{user}/followers")
    Call<List<Usuario>> getFollowers(@Path("user") String user);

    @GET ("{user}/repos")
    Call<List<Repo>> getPublic_repos(@Path("user") String user);

}
