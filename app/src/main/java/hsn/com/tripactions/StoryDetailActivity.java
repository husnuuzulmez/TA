package hsn.com.tripactions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class StoryDetailActivity extends AppCompatActivity {

    String title;
    String largethumbnail;
    String url;
    TextView txtTitle;
    ImageView imgThumb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        txtTitle = findViewById(R.id.txttitle);
        imgThumb = findViewById(R.id.imgthumb);

        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        largethumbnail = intent.getExtras().getString("largethumbnail");
        url = intent.getExtras().getString("url");

        txtTitle.setText(title);

        if (!TextUtils.isEmpty(largethumbnail)) {
            Glide.with(StoryDetailActivity.this)
                    .load(largethumbnail)
                    .into(imgThumb);

        }


     findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Intent share = new Intent(android.content.Intent.ACTION_SEND);
             share.setType("text/plain");

             // Add data to the intent, the receiving app will decide
             // what to do with it.
             share.putExtra(Intent.EXTRA_SUBJECT, "The New York Times Story");
             share.putExtra(Intent.EXTRA_TEXT,title + "\n"  + "\n"  + url);

             startActivity(Intent.createChooser(share, "Share link!"));

         }
     });

    }
}
