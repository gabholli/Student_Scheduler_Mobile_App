package com.example.c196.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Entity.Assessments;
import com.example.c196.R;
import com.example.c196.UI.AssessmentList;

import java.util.List;

public class AssessmentAdapterFromCourse extends RecyclerView.Adapter<AssessmentAdapterFromCourse.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;
        private final TextView assessmentItemView2;

        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessmentListTitleRow);
            assessmentItemView2 = itemView.findViewById(R.id.assessmentListIdRow);
            itemView.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assessments current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentList.class);
                    intent.putExtra("id", current.getAssessmentId());
                    intent.putExtra("name", current.getAssessmentName());
                    intent.putExtra("start", current.getStartDate());
                    intent.putExtra("end", current.getEndDate());
                    intent.putExtra("type", current.getAssessmentType());
                    intent.putExtra("courseId", current.getCourseId());
                    context.startActivity(intent);
                }
            }));
        }
    }

    private List<Assessments> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapterFromCourse(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_row, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessments current = mAssessments.get(position);
            String name = current.getAssessmentName();
            String type = current.getAssessmentType();
            holder.assessmentItemView.setText(name);
            holder.assessmentItemView2.setText(type);
        } else {
            holder.assessmentItemView.setText("No Assessment Name");
            holder.assessmentItemView2.setText("No Assessment Type");
        }
    }

    public void setAssessments(List<Assessments> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }
}

