package es.iessaladillo.pedrojoya.demorecyclerview.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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
        List<Student> students = viewModel.getStudents(false);
        listAdapter.submitList(students);
        b.lblEmptyView.setVisibility(students.size() == 0 ? View.VISIBLE : View.INVISIBLE);
    }

    private void setupViews() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        listAdapter = new MainActivityAdapter(position -> deleteStudent(listAdapter.getItem(position)));
        // TODO: Set listeners of adapter.
        b.lstStudents.setHasFixedSize(true);
        b.lstStudents.setLayoutManager(new GridLayoutManager(this,
                getResources().getInteger(R.integer.main_lstStudents_columns)));
        b.lstStudents.setItemAnimator(new DefaultItemAnimator());
        b.lstStudents.setAdapter(listAdapter);
    }

    private void deleteStudent(Student student) {
        viewModel.deleteStudent(student);
        List<Student> students = viewModel.getStudents(true);
        listAdapter.submitList(students);
        b.lblEmptyView.setVisibility(students.size() == 0 ? View.VISIBLE : View.INVISIBLE);
    }

}
