package ru.bets.sport;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    public String[] mColors = {"#fafafa","#ffffff"};
    private final List<State> states;

    StateAdapter(Context context, List<State> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
        State state = states.get(position);
        holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 2]));
        holder.namberView.setText(state.getNamber());
        holder.nameView.setText(state.getName());
        holder.iView.setText(state.getI());
        holder.vView.setText(state.getVv());
        holder.nView.setText(state.getN());
        holder.pView.setText(state.getP());
        holder.ballsView.setText(state.getBalls());
        holder.oView.setText(state.getScore());

    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView namberView, nameView, iView, vView,nView,pView,ballsView,oView;
        ViewHolder(View view){
            super(view);
            namberView = view.findViewById(R.id.id1);
            nameView = view.findViewById(R.id.id3);
            iView = view.findViewById(R.id.id4);
            vView = view.findViewById(R.id.id5);
            nView = view.findViewById(R.id.id6);
            pView = view.findViewById(R.id.id7);
            ballsView = view.findViewById(R.id.id8);
            oView = view.findViewById(R.id.id9);

        }
    }
}
