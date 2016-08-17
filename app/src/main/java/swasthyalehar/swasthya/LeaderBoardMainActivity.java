package swasthyalehar.swasthya;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itstym on 15/8/16.
 */
public class LeaderBoardMainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LeaderBoardAdaptor adapter;
    private List<LeaderboardProfile> LeaderBoardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_main_activity);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        //initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LeaderBoardList= new ArrayList<>();
        adapter = new LeaderBoardAdaptor(this, LeaderBoardList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareLeaderBoard();

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */

  /*  private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }*/


    /**
     * Adding few leaderboard for testing
     */
    private void prepareLeaderBoard() {
        int[] covers = new int[]{
                R.drawable.dp,
                R.drawable.dp,
                R.drawable.dp,
                R.drawable.dp,
                R.drawable.dp,
                R.drawable.dp,
                R.drawable.dp,
                R.drawable.dp,
                R.drawable.dp,
                R.drawable.dp };



        LeaderboardProfile a = new LeaderboardProfile("Profile_1", 13, covers[0]);
        LeaderBoardList.add(a);

        a = new LeaderboardProfile("Profile_2", 8, covers[1]);
        LeaderBoardList.add(a);

        a = new LeaderboardProfile("Profile_3", 8, covers[2]);
        LeaderBoardList.add(a);

        a = new LeaderboardProfile("Profile_4", 8, covers[3]);
        LeaderBoardList.add(a);

        a = new LeaderboardProfile("Profile_5", 8, covers[4]);
        LeaderBoardList.add(a);

        a = new LeaderboardProfile("Profile_6", 8, covers[5]);
        LeaderBoardList.add(a);

        a = new LeaderboardProfile("Profile_7", 8, covers[6]);
        LeaderBoardList.add(a);

        a = new LeaderboardProfile("Profile_8", 8, covers[7]);
        LeaderBoardList.add(a);

        a = new LeaderboardProfile("Profile_9", 8, covers[8]);
        LeaderBoardList.add(a);

        a = new LeaderboardProfile("Profile_10", 8, covers[9]);
        LeaderBoardList.add(a);


        adapter.notifyDataSetChanged();
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
