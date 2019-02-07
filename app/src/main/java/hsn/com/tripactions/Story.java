package hsn.com.tripactions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Story {

    private static final String DEFAULT_THUMB = "https://yt3.ggpht.com/a-/AAuE7mAOoaEYRwPz4fFQaDOGHUaQ-Al2Fp9bqWbGtg=s288-mo-c-c0xffffffff-rj-k-no";

    private String title;
    private String short_url;
    private String thumbnail;
    private String largethumbnail;

    public Story(JSONObject rep){

        if (rep.opt("title")!=null)
            this.title =(String) rep.opt("title");

        if (rep.opt("short_url")!=null)
            this.short_url =(String) rep.opt("short_url");
        if (rep.opt("multimedia")!=null) {
            JSONArray stories = rep.optJSONArray("multimedia");
            if (stories.length()>0) {
                try {
                JSONObject smallimg = stories.getJSONObject(0);
                    if (smallimg.opt("url")!=null)
                        this.thumbnail =(String) smallimg.opt("url");
                } catch (JSONException e) {
                    this.thumbnail = DEFAULT_THUMB;
                }
            }
            if (stories.length()>1) {
                try {
                    JSONObject bigimg = stories.getJSONObject(1);
                    if (bigimg.opt("url")!=null)
                        this.largethumbnail =(String) bigimg.opt("url");
                } catch (JSONException e) {
                    this.thumbnail = DEFAULT_THUMB;
                }
            }
        }
        else
        this.thumbnail = DEFAULT_THUMB ;
    }

    public String getShort_url() {
        return short_url;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getLargethumbnail() {
        return largethumbnail;
    }
}
