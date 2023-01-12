package com.gopi.nycschools.ui.schoollist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.gopi.nycschools.R;
import com.gopi.nycschools.data.model.School;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.SchoolListViewHolder> {

    private List<School> items;
    private SchoolListener listener;

    public interface SchoolListener {
        void onSchoolSelected(School school);
    }

    public SchoolListAdapter(SchoolListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    public void setItems(List<School> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public SchoolListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.school_list_item, parent, false);
        return new SchoolListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SchoolListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private School getItem(int position) {
        return items.get(position);
    }

    public class SchoolListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.school_name) TextView schoolName;
        @BindView(R.id.school_details) TextView schoolDetails;
        @BindView(R.id.school_grade) TextView schoolGrade;

        SchoolListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            School school = getItem(position);

            setClickListener(school);
            //Set text
            this.schoolName.setText(school.getSchool_name());
            this.schoolDetails.setText(school.getCity() + ","+school.getState_code());
            this.schoolGrade.setText("Grades : "+school.getFinalgrades());

        }

        //Set Listener
        private void setClickListener(School school) {
            itemView.setTag(school);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onSchoolSelected((School) view.getTag());
        }
    }
}