package u.in.ac.bmsce.rjfoundation.Gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import u.in.ac.bmsce.rjfoundation.Interface.ItemClickListener;
import u.in.ac.bmsce.rjfoundation.R;
import u.in.ac.bmsce.rjfoundation.ViewHolder.MenuViewHolder;
import u.in.ac.bmsce.rjfoundation.models.Category;

public class GalleryActivity extends AppCompatActivity {



    FirebaseDatabase database;
    DatabaseReference category;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    TextView textFullName;

    FirebaseRecyclerAdapter<Category,MenuViewHolder> adptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery2);


//Auth
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");
        recycler_menu = findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
       recycler_menu.setLayoutManager(layoutManager);

        loadMenu();
    }

    private void loadMenu(){

        adptor = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {

                viewHolder.textMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                final Category clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

//                        Toast.makeText(GalleryActivity.this,""+clickItem.getName(),Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(GalleryActivity.this, FoodList.class);
//                        intent.putExtra("CategoryId",adptor.getRef(position).getKey());
//                        startActivity(intent);

                    }
                });
            }
        };
        recycler_menu.setAdapter(adptor);
    }
}
