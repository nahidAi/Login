package test.login.com.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Fragment1 extends Fragment {

    private EditText edtName;
    private EditText edtPass;
    private ImageView circleImageView;
    private TextView customText;
    private Button saveButton;
    private MyDatabase myDatabase;
    public static ArrayList<Model> models;
    public static RecyclerAdapter recyclerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        //  گرفتن یک نمونه از کلاس دیتابیس
        myDatabase = new MyDatabase(getContext());

        edtName = (EditText) view.findViewById(R.id.edtname);
        edtPass = (EditText) view.findViewById(R.id.edtPass);
        circleImageView = (CircleImageView) view.findViewById(R.id.circleImageView);
        customText = view.findViewById(R.id.customText);
        models = new ArrayList<>();
        saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!edtName.getText().toString().isEmpty() && !edtPass.getText().toString().isEmpty()) {
                    long result = myDatabase.addInfo(edtName.getText().toString(), edtPass.getText().toString(), 0);
                    Model model = new Model();
                    model.setId(result);
                    model.setTxtUser(edtName.getText().toString());
                    model.setTxtPass(edtPass.getText().toString());
                    models.add(model);
                    recyclerAdapter = new RecyclerAdapter(models, getContext());
                    Fragment2.recyclerView.setAdapter(recyclerAdapter);


                    Snackbar snackbar = Snackbar.make(v, "  در دیتابیس با آیدی  " + result + "  ذخیره شد                                       ", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    view.setBackgroundColor(getResources().getColor(R.color.colorYello));
                    TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.BLACK);
                    snackbar.show();


                    edtName.getText().clear();
                    edtPass.getText().clear();


                } else {
                    Toast.makeText(getContext(), "فیلدهای خالی را پر کنید", Toast.LENGTH_SHORT).show();

                }


            }

        });


        return view;


    }


}
