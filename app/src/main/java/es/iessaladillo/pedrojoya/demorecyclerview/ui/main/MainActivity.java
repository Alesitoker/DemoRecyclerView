package es.iessaladillo.pedrojoya.demorecyclerview.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaladillo.pedrojoya.demorecyclerview.R;
import es.iessaladillo.pedrojoya.demorecyclerview.data.local.Database;
import es.iessaladillo.pedrojoya.demorecyclerview.data.local.model.Student;
import es.iessaladillo.pedrojoya.demorecyclerview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding b;
    private MainActivityViewModel viewModel;
    private MainActivityAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, new MainActivityViewModelFactory(new Database())).get(MainActivityViewModel.class);

        // TODO: Give viewModel to binding.
        // TODO: Give lifecycle to binding.
        setupViews();
        observeStudents();
    }

    private void observeStudents() {
        viewModel.getStudents(false).observe(this, this::refresherListAdapter);
    }

    private void refresherListAdapter(List<Student> students) {
        listAdapter.submitList(students);
        b.lblEmptyView.setVisibility(students.size() == 0 ? View.VISIBLE : View.INVISIBLE);
    }

    private void setupViews() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        listAdapter = new MainActivityAdapter(position -> showStudent(listAdapter.getItem(position)));
        // TODO: Set listeners of adapter.
        b.lstStudents.setHasFixedSize(true);
        b.lstStudents.setLayoutManager(new GridLayoutManager(this,
                getResources().getInteger(R.integer.main_lstStudents_columns)));
        b.lstStudents.setItemAnimator(new DefaultItemAnimator());
        b.lstStudents.setAdapter(listAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, /*ItemTouchHelper.START |*/ ItemTouchHelper.END) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        if (direction == ItemTouchHelper.END) {
                            viewModel.deleteStudent(listAdapter.getItem(viewHolder.getAdapterPosition()));
                        }
                        // prueba para direction no tiene sentido en este caso
//                        else {
//                            showStudent(listAdapter.getItem(viewHolder.getAdapterPosition()));
//                        }
                    }
                });
        itemTouchHelper.attachToRecyclerView(b.lstStudents);
    }

    private void showStudent(Student student) {
        Toast.makeText(this, student.getName(), Toast.LENGTH_SHORT).show();
    }

}
