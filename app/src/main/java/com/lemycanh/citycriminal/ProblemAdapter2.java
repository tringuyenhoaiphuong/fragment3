package com.lemycanh.citycriminal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProblemAdapter2 extends RecyclerView.Adapter<ProblemAdapter2.ViewHolder> {

    Context context;
    String title[];
    String content[];
    int resolved[];
    Problem problem[];

    public ProblemAdapter2(Context context) {
        this.context = context;
        title = context.getResources().getStringArray(R.array.problemtitles);
        content = context.getResources().getStringArray(R.array.problemcontent);
        resolved = context.getResources().getIntArray(R.array.problemresolved);
        problem = new Problem[title.length];
        for (int i=0; i<title.length; i++) {
            problem[i] = new Problem(title[i], content[i], resolved[i] == 1);
            problem[i].setTimestamp(new Date());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.problem_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Problem p = this.problem[i];
        viewHolder.bindView(p);
    }

    @Override
    public int getItemCount() {
        return problem.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_problem_item_title)
        TextView tvTitle;

        @BindView(R.id.tv_problem_item_timestamp)
        TextView tvTimestamp;

        @BindView(R.id.tv_problem_item_resolved)
        CheckBox ckResolved;
        private View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bindView(Problem p) {
            tvTitle.setText(p.getTitle());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            tvTimestamp.setText(dateFormat.format(p.getTimestamp()));
            ckResolved.setChecked(p.isResolved());

            itemView.setOnClickListener(v -> EventBus.getDefault().post(new ListChangedEvent(p)));
            ckResolved.setOnClickListener(v -> {
                p.setResolved(ckResolved.isChecked());
                EventBus.getDefault().post(new ListChangedEvent(p));
            });
        }
    }
}
