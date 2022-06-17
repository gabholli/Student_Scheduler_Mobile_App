package com.example.c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private List<Terms> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termTextView;


        private TermViewHolder(View itemView) {
            super(itemView);
            termTextView = itemView.findViewById(R.id.termListItemTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Terms current = mTerms.get(position);
                    Intent intent = new Intent(context, TermDetail.class);
                    intent.putExtra("title", current.getTermTitle());
                    intent.putExtra("start", current.getStartDate());
                    intent.putExtra("end", current.getEndDate());
                    intent.putExtra("id", current.getTermId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list_row, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        if (mTerms != null) {
            Terms current = mTerms.get(position);
            String title = current.getTermTitle();
            holder.termTextView.setText(title);
        } else {
            holder.termTextView.setText("No Term Title");
        }
    }

    public void setTerms(List<Terms> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }


}
