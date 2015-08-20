package com.example.jonas.chucknorris;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import network.JokeService;
import wrapper.Joke;
import wrapper.JokeAdapter;

public class MainActivity extends AppCompatActivity {

    private Button btnFetch;
    private JokeService jokeService;
    private boolean isBound;
    private Intent intent;
    private List<Joke> jokes = new ArrayList<>();
    private JokeAdapter jokeAdapter;
    private ListView lstJokes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this, JokeService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(intent);
        jokeAdapter = new JokeAdapter(this, jokes);
        lstJokes = (ListView) findViewById(R.id.lstJokes);
        lstJokes.setAdapter(jokeAdapter);

        btnFetch = (Button) findViewById(R.id.btnFetch);
        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int originalSize = jokes.size();
                while(jokes.size() == originalSize)
                    if(jokeService.getJoke() != null)
                        jokes.add(jokeService.getJoke());

                startService(intent);
                jokeAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            JokeService.JokeBinder binder = (JokeService.JokeBinder) service;
            jokeService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
