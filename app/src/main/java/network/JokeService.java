package network;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import wrapper.Joke;

public class JokeService extends IntentService {

    private String TAG = JokeService.class.getSimpleName();
    private IBinder serviceBinder = new JokeBinder();

    private Joke joke;

    public Joke getJoke() {
        return joke;
    }

    public JokeService() {
        super("JokeService");
    }

    @Override
    public IBinder onBind(Intent intent){
        return serviceBinder;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        JokeRestAdapter adapter = new JokeRestAdapter();
        ApiJokesInterface api = adapter.getApi();

        api.getJoke(new Callback<Joke>() {
            @Override
            public void success(Joke joke, Response response) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                    String line = "";
                    StringBuffer sb = new StringBuffer();

                    while ((line = reader.readLine()) != null)
                        sb.append(line);

                    Gson gson = new Gson();
                    JokeService.this.joke = gson.fromJson(sb.toString(), Joke.class);

                    Log.i(TAG, "new joke: " + joke);
                } catch (IOException e) {
                    Log.e(TAG, "IO exception: " + joke);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Couldnt fetch joke: " + error);
            }
        });
    }


    public class JokeBinder extends Binder {
        public JokeService getService(){
            return JokeService.this;
        }
    }
}
