package com.lemycanh.citycriminal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lemycanh on 13/11/2019.
 */

public class ProblemAdapter extends BaseAdapter {

    Context context;
    String title[];
    String content[];
    int resolved[];
    Problem problem[];

    public ProblemAdapter(Context context) {
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

    @Override
    public int getCount() {
        return problem.length;
    }

    @Override
    public Object getItem(int position) {
        return problem[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.problem_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.TvTitle = convertView.findViewById(R.id.tv_problem_item_title);
            viewHolder.TvTimestamp = convertView.findViewById(R.id.tv_problem_item_timestamp);
            viewHolder.CkResolved = convertView.findViewById(R.id.tv_problem_item_resolved);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Problem p = problem[position];
        viewHolder.TvTitle.setText(p.getTitle());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        viewHolder.TvTimestamp.setText(dateFormat.format(p.getTimestamp()));
        viewHolder.CkResolved.setChecked(p.isResolved());
        return convertView;
    }

    private class ViewHolder {
        public TextView TvTitle;
        public TextView TvTimestamp;
        public CheckBox CkResolved;
    }
}
