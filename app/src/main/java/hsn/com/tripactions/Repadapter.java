package hsn.com.tripactions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class Repadapter extends RecyclerView.Adapter<Repadapter.MyviewHolder> {

    private List<Story> stories;
    private Context mContext;

    public Repadapter(List<Story> L , Context context ){
        this.stories = L;
        this.mContext = context;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        private TextView ttitle;
        private TextView tlink;
        private TextView tstar;
        private ImageView imageView;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            ttitle = itemView.findViewById(R.id.txttitle);
            tlink = itemView.findViewById(R.id.txtlink);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public void setdata(List<Story> L){
        this.stories = L;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw, viewGroup , false);
        MyviewHolder holder = new MyviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
        myviewHolder.ttitle.setText(stories.get(i).getTitle());
        myviewHolder.tlink.setText(stories.get(i).getShort_url());

        String imageUrl = stories.get(i).getThumbnail();
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(mContext)
                    .load(imageUrl)
                    .into(myviewHolder.imageView);

        }
        animate(myviewHolder);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mContext, R.anim.anticipate_overshoot_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
