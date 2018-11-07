package es.iessaladillo.pedrojoya.demorecyclerview.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.iessaladillo.pedrojoya.demorecyclerview.data.local.Database;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    private final Database database;

    public MainActivityViewModelFactory(Database database) {
        this.database = database;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(database);
    }
}
