package es.iessaladillo.pedrojoya.demorecyclerview.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaladillo.pedrojoya.demorecyclerview.R;
import es.iessaladillo.pedrojoya.demorecyclerview.data.local.model.Student;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private List<Student> data = new ArrayList<>();
    private final OnStudentClickListener onStudentClickListener;

    public MainActivityAdapter(OnStudentClickListener onStudentClickListener) {
        this.onStudentClickListener = onStudentClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item, parent, false), onStudentClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    public Student getItem(int position) {
       return data.get(position);
    }

    public void submitList(@NonNull List<Student> newData) {
        data = newData;
        notifyDataSetChanged();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView lblName;
        private final TextView lblAge;

        public ViewHolder(View itemView, OnStudentClickListener onStudentClickListener) {
            super(itemView);
            lblName = ViewCompat.requireViewById(itemView, R.id.lblName);
            lblAge = ViewCompat.requireViewById(itemView, R.id.lblAge);
            itemView.setOnClickListener(v -> onStudentClickListener.onItemClick(getAdapterPosition()));
        }

        public void bind(Student student) {
            lblName.setText(student.getName());
            lblAge.setText(String.valueOf(student.getAge()));
        }
    }

    // TODO: Define interfaces to communicate to Activity
    // TODO: Define listeners and setters.

}
