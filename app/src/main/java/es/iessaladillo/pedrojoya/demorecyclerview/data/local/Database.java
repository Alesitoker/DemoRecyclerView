package es.iessaladillo.pedrojoya.demorecyclerview.data.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.iessaladillo.pedrojoya.demorecyclerview.data.local.model.Student;

public class Database {

    // TODO: Define Database as a singleton.

    private ArrayList<Student> students = new ArrayList<>(Arrays.asList(
            new Student((long) 0, "Baldo", 23),
            new Student((long) 1, "German", 28)
    ));

    public List<Student> getStudents() {
        return students;
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }

// TODO: Add method to query students table.

}
