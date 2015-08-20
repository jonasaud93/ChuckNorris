package network;

import retrofit.RestAdapter;
import wrapper.Joke;

/**
 * Created by Jonas on 20/08/2015.
 */
public class JokeRestAdapter {
    private static final String ENDPOINT = "http://api.icndb.com";
    private ApiJokesInterface api;

    public ApiJokesInterface getApi(){
        return api;
    }

    public JokeRestAdapter(){
        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        api = adapter.create(ApiJokesInterface.class);
    }
}
