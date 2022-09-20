package ru.bets.sport;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StatsStateAdapter extends RecyclerView.Adapter<StatsStateAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    public String[] mColors = {"#fafafa","#ffffff"};
    private final List<StatsState> states;

    StatsStateAdapter(Context context, List<StatsState> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StatsStateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.stats_item, parent, false);
        return new StatsStateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatsStateAdapter.ViewHolder holder, int position) {
        StatsState state = states.get(position);
        holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 2]));
        holder.command.setText(state.getCommand());
        holder.tour.setText(state.getTour());
        holder.hit.setText(state.getHit());
        holder.pick.setText(state.getPick());

    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView command, tour, hit, pick;
        ViewHolder(View view){
            super(view);
            command = view.findViewById(R.id.textView1);
            tour = view.findViewById(R.id.textView2);
            hit = view.findViewById(R.id.textView3);
            pick = view.findViewById(R.id.textView4);

        }
    }
}
