package wrapper;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonas.chucknorris.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 20/08/2015.
 */
public class JokeAdapter extends ArrayAdapter<Joke> {

    public JokeAdapter(Context context, List<Joke> jokes) {
        super(context, 0, jokes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Joke joke = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.joke_item, parent, false);
        }
        // Lookup view for data population
        TextView txtJoke = (TextView) convertView.findViewById(R.id.txtJoke);
        ImageView imgChuck = (ImageView) convertView.findViewById(R.id.imgChuck);
        Picasso.with(getContext())
                .load(Uri.parse("http://media.washtimes.com.s3.amazonaws.com/media/image/2009/03/26/20090325-202152-pic-172579909.jpg"))
                .resize(100,100)
                .into(imgChuck);
        // Populate the data into the template view using the data object
        txtJoke.setText(joke.getValue().getJoke());
        // Return the completed view to render on screen
        return convertView;
    }
}

