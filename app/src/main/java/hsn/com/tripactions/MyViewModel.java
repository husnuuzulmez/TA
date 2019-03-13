package hsn.com.tripactions;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {

//ABC1123
    //HAHAHAHAHA
    private MutableLiveData<List<Story>> replist;

    public MutableLiveData<List<Story>> getReplist() {
        if (replist==null)
        {
            replist = new MutableLiveData<>();

        }

        return replist;
    }

    public void updatedata(String searchkey) {
        new Myasync().execute(searchkey);
    }



    class Myasync extends AsyncTask<String,Integer,Integer> {
        private List<Story> L = new ArrayList<>();

        @Override
        protected Integer doInBackground(String... params) {

            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString(),L);
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {

            }
            return result; //"Failed to fetch data!";

        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
            replist.setValue(L);

        }

    }

    private void parseResult(String result,List<Story> L) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray stories = response.optJSONArray("results");

            for (int i = 0; i < stories.length(); i++) {
                JSONObject story = stories.getJSONObject(i);
                L.add(new Story(story));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
