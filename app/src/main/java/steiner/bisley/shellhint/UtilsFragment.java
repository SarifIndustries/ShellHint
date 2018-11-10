package steiner.bisley.shellhint;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UtilsFragment extends Fragment {

    public UtilsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView platformRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_platforms, container, false);

        String[] platformNames = new String[PlatformData.platformArray.length];
        for(int i = 0; i < platformNames.length; i++) {
            platformNames[i] = PlatformData.platformArray[i].getName();
        }

        int[] platformIDs = new int[PlatformData.platformArray.length];
        for(int i = 0; i < platformIDs.length; i++) {
            platformIDs[i] = PlatformData.platformArray[i].getImageID();
        }

        CardAdapter adap1 = new CardAdapter(platformNames, platformIDs);
        platformRecycler.setAdapter(adap1);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        platformRecycler.setLayoutManager(layoutManager);
        return platformRecycler;
    }

}
