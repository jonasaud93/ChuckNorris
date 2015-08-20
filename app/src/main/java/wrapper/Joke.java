package wrapper;

import android.renderscript.Sampler;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jonas on 20/08/2015.
 */

public class Joke {


    @SerializedName("value")
    private Value value;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
