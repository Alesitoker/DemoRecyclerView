package es.iessaladillo.pedrojoya.demorecyclerview.data.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import es.iessaladillo.pedrojoya.demorecyclerview.data.local.model.Student;

public class Database {

    // TODO: Define Database as a singleton.

    private ArrayList<Student> students;
    private MutableLiveData<List<Student>> studentsLiveData = new MutableLiveData<>();

    public Database() {
        students = new ArrayList<>(Arrays.asList(
                new Student((long) 0, "Baldo", 23),
                new Student((long) 1, "German", 28)
        ));
        studentsLiveData.setValue(new ArrayList<>(students));
    }

    public LiveData<List<Student>> getStudents() {
        return studentsLiveData;
    }

    public void deleteStudent(Student student) {
        students.remove(student);
        studentsLiveData.setValue(new ArrayList<>(students));
    }

// TODO: Add method to query students table.

}
