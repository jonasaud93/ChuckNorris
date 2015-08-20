package wrapper;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jonas on 20/08/2015.
 */
public class Value {

    @SerializedName("joke")
    private String joke;

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
