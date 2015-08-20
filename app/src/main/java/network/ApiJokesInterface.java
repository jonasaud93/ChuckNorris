package network;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import wrapper.Joke;

public interface ApiJokesInterface {
    @GET("/jokes/random?")
    void getJoke(Callback<Joke> callback);
}
