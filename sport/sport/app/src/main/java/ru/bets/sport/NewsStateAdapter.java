package ru.bets.sport;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsStateAdapter extends RecyclerView.Adapter<NewsStateAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    public String[] mColors = {"#fafafa","#ffffff"};
    private final List<NewsState> states;

    NewsStateAdapter(Context context, List<NewsState> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public NewsStateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.news_item, parent, false);
        return new NewsStateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsStateAdapter.ViewHolder holder, int position) {
        NewsState state = states.get(position);
        holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 2]));
        holder.header.setText(state.getHeader());
        holder.date.setText(state.getDate());
        holder.text.setText(state.getText());
        holder.webView.loadUrl(state.getImage());
        WebSettings settings = holder.webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        holder.webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        holder.webView.setScrollbarFadingEnabled(false);


    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final WebView webView;
        final TextView header, date, text;
        ViewHolder(View view){
            super(view);
            webView = view.findViewById(R.id.webView);
            header = view.findViewById(R.id.news_header);
            date = view.findViewById(R.id.news_date);
            text = view.findViewById(R.id.news_text);


        }
    }
}