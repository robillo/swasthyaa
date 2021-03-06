package swasthyalehar.swasthya;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {


    private RecyclerView recyclerview;
    private RecyclerView.LayoutManager mLayoutManager;

    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activities, container, false);
        List<Data> data = fill_with_data();

        recyclerview = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(mLayoutManager);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getActivity().getApplication());
        recyclerview.setAdapter(adapter);

        return v;
    }

    public List<Data> fill_with_data() {

        List<Data> data = new ArrayList<>();
        data.add(new Data("Activity_1", "Description", R.drawable.dp));

        data.add(new Data("Activity_2", "Description", R.drawable.dp));

        data.add(new Data("Activity_3", "Description", R.drawable.dp));

        data.add(new Data("Activity_4", "Description", R.drawable.dp));

        data.add(new Data("Activity_5", "Description", R.drawable.dp));

        data.add(new Data("Activity_6", "Description", R.drawable.dp));

        data.add(new Data("Activity_7", "Description", R.drawable.dp));

        data.add(new Data("Activity_8", "Description", R.drawable.dp));

        data.add(new Data("Activity_9", "Description", R.drawable.dp));
        return data;
    }
}