package swasthyalehar.swasthya;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by itstym on 15/8/16.
 */

/*To render the RecyclerView, we need an adapter class which inflates the leaderboard_profile_tempalate.xml by keeping
 appropriate information.
 */

public class LeaderBoardAdaptor extends RecyclerView.Adapter<LeaderBoardAdaptor.MyViewHolder> {

    private Context mContext;
    private List<LeaderboardProfile> LeaderBoardList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, TotalPoints;
        public ImageView ProfilePic;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.title);
            TotalPoints = (TextView) view.findViewById(R.id.count);
            ProfilePic = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public LeaderBoardAdaptor(Context mContext, List<LeaderboardProfile> LeaderBoardList)
     {
       this.mContext=mContext;
       this.LeaderBoardList=LeaderBoardList;
     }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.leaderboard_profile_tempalate, parent, false);

            return new MyViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            LeaderboardProfile leaderboard = LeaderBoardList.get(position);

            holder.name.setText(leaderboard.getName());
            holder.TotalPoints.setText(leaderboard.getCreditPoint() + " points");

            // loading album cover using Glide library
            Glide.with(mContext).load(leaderboard.getThumbnail()).into(holder.ProfilePic);
        }

        @Override
        public int getItemCount() {
            return LeaderBoardList.size();
        }

}

