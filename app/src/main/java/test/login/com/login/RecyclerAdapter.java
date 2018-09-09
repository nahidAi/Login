package test.login.com.login;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Model> models = new ArrayList<>();
    private Context context;
    private MyDatabase myDatabase;

    public RecyclerAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_model, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Model model = models.get(position);

        holder.txtName.setText(model.getTxtUser());
        holder.txtPass.setText(model.getTxtPass());
        // حذف آیتم
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Message");
                builder.setMessage("آیا مایل به حذف اطلاعات هستید؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDatabase = new MyDatabase(context);
                        myDatabase.deleteRaw((int) model.getId());

                        Snackbar snackbar = Snackbar.make(v," کاربر با آیدی " +model.getId() + " از لیست حذف شد                                   ",Snackbar.LENGTH_LONG);
                        View view = snackbar.getView();
                        view.setBackgroundColor(context.getResources().getColor(R.color.colorYello));
                        TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.BLACK);
                        snackbar.show();
                        notifyDataSetChanged();
                        removeItem(position);

                    }
                });
                builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();


            }
        });


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("اطلاعات ویرایش شود؟");
                builder.setTitle("Message");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        String name = holder.txtName.getText().toString();
                        String pass = holder.txtPass.getText().toString();

                        intent.putExtra("NAME", name);
                        intent.putExtra("PASS", pass);
                        intent.putExtra("ID",model.getId());

                        context.startActivity(intent);


                    }
                });
                builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                builder.show();








            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Button btnDelete;
        private Button btnEdit;
        private TextView txtName;
        private TextView txtPass;
        private TextView namefix;
        private TextView passfix;


        public MyViewHolder(View itemView) {
            super(itemView);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "irsans_font.ttf");
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setTypeface(typeface);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnEdit.setTypeface(typeface);
            txtName = itemView.findViewById(R.id.txtNameInsert);
            txtName.setTypeface(typeface);
            txtPass = itemView.findViewById(R.id.txtPassInsert);
            txtPass.setTypeface(typeface);
            namefix = itemView.findViewById(R.id.txtName);
            namefix.setTypeface(typeface);
            passfix = itemView.findViewById(R.id.txtPass);
            passfix.setTypeface(typeface);

        }
    }

    public void removeItem(int position) {
        models.remove(position);
        notifyItemRemoved(position);
    }



}
