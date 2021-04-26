package com.studybean.studybeanadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    TextView dash;
    FirebaseUser user;
    String uid;
    FirebaseAuth mAuth;
    EditText setLimit;

    DatabaseReference limit;
    FirebaseDatabase database;

    Button cat1Id,cat2Id,cat3Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        assert user != null;
        uid = user.getUid();

        database = FirebaseDatabase.getInstance();
        limit = database.getReference("Limit");

        setLimit = findViewById(R.id.setLimitId);

        if(mAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        else
        {
            dash = findViewById(R.id.dashId);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayoutId,CategoryFragment.newInstance());
            transaction.commit();

            FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayoutId2,CategoryFragment2.newInstance());
            transaction2.commit();

            FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayoutId3,CategoryFragment3.newInstance());
            transaction3.commit();

            dash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String val = setLimit.getText().toString().trim();

                    limit.child(uid).setValue(val);
                }
            });

            cat1Id = findViewById(R.id.cat1Id);
            cat2Id = findViewById(R.id.cat2Id);
            cat3Id = findViewById(R.id.cat3Id);

            cat1Id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,AddCategory.class);
                    intent.putExtra("cat","Category");
                    startActivity(intent);
                }
            });

            cat2Id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,AddCategory.class);
                    intent.putExtra("cat","Category2");
                    startActivity(intent);
                }
            });

            cat3Id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this,AddCategory.class);
                    intent.putExtra("cat","Category3");
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        limit.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null)
                {
                    String val = snapshot.getValue(String.class);
                    setLimit.setText(val);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
