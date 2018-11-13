package steiner.bisley.shellhint;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private String[] names;
    private int[] imageIDs;
    private Listonier listonieros1;

    interface Listonier {
        void onClick(int position);
    }

    public void setListonier(Listonier l1) {
        listonieros1 = l1;
    }

    public CardAdapter(String[] ns, int[] ids) {
        names = ns;
        imageIDs = ids;
    }

    @Override
    public int getItemCount() {
        return names.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_platform, parent, false);
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        CardView cv = holder.cardView;

        ImageView imag1 = cv.findViewById(R.id.card_image);
        Drawable drawable = ContextCompat.getDrawable(cv.getContext(), imageIDs[position]);
        imag1.setImageDrawable(drawable);
        imag1.setContentDescription(names[position]);

        TextView text1 = cv.findViewById(R.id.card_text);
        text1.setText(names[position]);

        // Bind Listener
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listonieros1 != null) {
                    listonieros1.onClick(position);
                }
            }
        });

    }
}
