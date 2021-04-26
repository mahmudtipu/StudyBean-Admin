package com.studybean.studybeanadmin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCategory extends AppCompatActivity {
    EditText shopName;
    Button confirm;

    DatabaseReference ShopName;
    FirebaseDatabase database;
    String name;
    int i=0,val;
    String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Bundle bundle = getIntent().getExtras();
        cat = bundle.getString("cat");

        database = FirebaseDatabase.getInstance();
        ShopName = database.getReference(cat);

        shopName = findViewById(R.id.shopNameId);

        confirm = findViewById(R.id.confirmId);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = shopName.getText().toString().trim();

                if(name.isEmpty())
                {
                    shopName.setError("item name is required");
                    shopName.requestFocus();
                    return;
                }

                if(!name.isEmpty())
                {
                    ShopName.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getValue()!=null)
                            {
                                for(DataSnapshot snapshot1:snapshot.getChildren())
                                {
                                    val=Integer.parseInt(snapshot1.getKey());
                                }
                                val++;

                                ShopName.child(String.format("%s",val)).child("Name").setValue(name);

                                shopName.setText("");
                            }
                            else
                            {
                                val=1;
                                ShopName.child(String.format("%s",val)).child("Name").setValue(name);

                                shopName.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}
