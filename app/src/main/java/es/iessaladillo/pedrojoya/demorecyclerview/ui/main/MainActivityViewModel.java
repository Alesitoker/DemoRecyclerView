package es.iessaladillo.pedrojoya.demorecyclerview.ui.main;

import java.util.List;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.demorecyclerview.data.local.Database;
import es.iessaladillo.pedrojoya.demorecyclerview.data.local.model.Student;

public class MainActivityViewModel extends ViewModel {

    private final Database database;
    private List<Student> students;

    public MainActivityViewModel(Database database) {
        this.database = database;
    }

    public List<Student> getStudents(boolean forceLoad) {
        if (students == null || forceLoad) {
            students = database.getStudents();
        }
        return students;
    }

    public void deleteStudent(Student student) {
        database.deleteStudent(student);
    }
    // TODO: Define data needs to be retained during configuration change (state)
}
