package test.login.com.login;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {
    public static RecyclerView recyclerView;
    public static RecyclerAdapter recyclerAdapter;
    private MyDatabase myDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment2, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        myDatabase = new MyDatabase(getContext());
        readDataFromDatabase();
        Fragment1.recyclerAdapter = new RecyclerAdapter(Fragment1.models, getContext());
        recyclerView.setAdapter(Fragment1.recyclerAdapter);


        return view;


    }


    public void readDataFromDatabase() {
        myDatabase = new MyDatabase(getContext());
        Cursor cursor = myDatabase.getInfo();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String pass = cursor.getString(3);


            Model model = new Model();
            model.setId(id);
            model.setTxtUser(name);
            model.setTxtPass(pass);
            Fragment1.models.add(model);


        }


    }


}
