package steiner.bisley.shellhint;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class UtilsFragment extends ListFragment {

    public UtilsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayAdapter<String> adap1 = new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.UtList));
        setListAdapter(adap1);
        View view = super.onCreateView(inflater, container, savedInstanceState);
        int paddingDp = 26;
        float density = inflater.getContext().getResources().getDisplayMetrics().density;
        int paddingPixel = (int)(paddingDp * density);
        view.setPadding(paddingPixel, 0, paddingPixel, 0);
        return view;
    }

}
