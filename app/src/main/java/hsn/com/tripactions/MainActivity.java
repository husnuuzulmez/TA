package hsn.com.tripactions;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    MyViewModel viewmodel;
    RecyclerView recyclerView;
    Repadapter repadapter;
    String searchkey="science";

   public List<Story> L=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewmodel = ViewModelProviders.of(this).get(MyViewModel.class);

        repadapter = new Repadapter( L , MainActivity.this );

        recyclerView = findViewById(R.id.repview);
        RecyclerView.LayoutManager Lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(Lm);
        recyclerView.setAdapter(repadapter);

        recyclerView.addOnItemTouchListener(new MyRvItemTouchListener(this, recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(MainActivity.this, StoryDetailActivity.class);
                Story story =  viewmodel.getReplist().getValue().get(position);
                intent.putExtra("title" ,story.getTitle() );
                intent.putExtra("largethumbnail" ,story.getLargethumbnail() );
                intent.putExtra("url" ,story.getShgort_url() );
                //heytyttt
                //heyooo1236
                //abc123
                //hehehehe
                startActivity(intent);
            }

        }));




        String search =  "https://api.nytimes.com/svc/topstories/v2/"+searchkey+".json?api-key="+ getResources().getString(R.string.api_key);
        viewmodel.updatedata(search);

        viewmodel.getReplist().observe(this, new Observer<List<Story>>() {
            @Override
            public void onChanged(@Nullable List<Story> stories) {
                repadapter.setdata(stories);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String search =  "https://api.nytimes.com/svc/topstories/v2/"+s+".json?api-key="+ getResources().getString(R.string.api_key);
                viewmodel.updatedata(search);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }


}
