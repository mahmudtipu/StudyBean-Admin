package com.studybean.studybeanadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.studybean.studybeanadmin.Common.Common;
import com.studybean.studybeanadmin.Model.Question;

import java.util.Collections;
import java.util.UUID;

public class PreStart3 extends AppCompatActivity {

    Button imageFalse,imageTrue;
    CardView cardImage;

    EditText question,answerA,answerB,answerC,answerD,description;

    Button correctA,correctB,correctC,correctD;
    String correctAnswer,imgQuestion;

    Button confirm;

    FirebaseDatabase database;
    DatabaseReference questions;

    String value;
    ImageView upImage;
    Uri imageUri;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_start);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        database = FirebaseDatabase.getInstance();

        questions = database.getReference("Questions3");

        upImage = findViewById(R.id.imageId);

        upImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });

        confirm = findViewById(R.id.confirmId);

        cardImage = findViewById(R.id.cardImageId);

        imageFalse = findViewById(R.id.imageFalseId);
        imageTrue = findViewById(R.id.imageTrueId);

        imageFalse.setBackgroundColor(Color.parseColor("#2196F3"));
        imageFalse.setTextColor(getResources().getColor(R.color.black));
        cardImage.setVisibility(View.GONE);
        imgQuestion = "false";

        imageTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgQuestion = "true";
                imageFalse.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageFalse.setTextColor(getResources().getColor(R.color.black));
                cardImage.setVisibility(View.VISIBLE);

                imageTrue.setBackgroundColor(Color.parseColor("#2196F3"));
                imageTrue.setTextColor(getResources().getColor(R.color.black));
            }
        });

        imageFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgQuestion = "false";
                imageTrue.setBackgroundColor(Color.parseColor("#FFFFFF"));
                imageTrue.setTextColor(getResources().getColor(R.color.black));
                cardImage.setVisibility(View.GONE);

                imageFalse.setBackgroundColor(Color.parseColor("#2196F3"));
                imageFalse.setTextColor(getResources().getColor(R.color.black));
            }
        });

        question = findViewById(R.id.questionId);
        answerA = findViewById(R.id.btnAnswerA);
        answerB = findViewById(R.id.btnAnswerB);
        answerC = findViewById(R.id.btnAnswerC);
        answerD = findViewById(R.id.btnAnswerD);
        description = findViewById(R.id.desId);

        correctA = findViewById(R.id.correctAId);
        correctB = findViewById(R.id.correctBId);
        correctC = findViewById(R.id.correctCId);
        correctD = findViewById(R.id.correctDId);

        correctA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctAnswer = answerA.getText().toString().trim();

                if(!correctAnswer.isEmpty())
                {

                    correctA.setBackgroundColor(Color.parseColor("#2196F3"));
                    correctA.setTextColor(getResources().getColor(R.color.black));

                    correctB.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctB.setTextColor(getResources().getColor(R.color.black));

                    correctC.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctC.setTextColor(getResources().getColor(R.color.black));

                    correctD.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctD.setTextColor(getResources().getColor(R.color.black));
                }
                else
                {
                    answerA.setError("Option A is required");
                    answerA.requestFocus();
                    return;
                }
            }
        });

        correctB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctAnswer = answerB.getText().toString().trim();

                if(!correctAnswer.isEmpty())
                {
                    correctB.setBackgroundColor(Color.parseColor("#2196F3"));
                    correctB.setTextColor(getResources().getColor(R.color.black));

                    correctA.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctA.setTextColor(getResources().getColor(R.color.black));

                    correctC.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctC.setTextColor(getResources().getColor(R.color.black));

                    correctD.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctD.setTextColor(getResources().getColor(R.color.black));
                }
                else
                {
                    answerB.setError("Option B is required");
                    answerB.requestFocus();
                    return;
                }
            }
        });

        correctC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctAnswer = answerC.getText().toString().trim();

                if(!correctAnswer.isEmpty())
                {
                    correctC.setBackgroundColor(Color.parseColor("#2196F3"));
                    correctC.setTextColor(getResources().getColor(R.color.black));

                    correctB.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctB.setTextColor(getResources().getColor(R.color.black));

                    correctA.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctA.setTextColor(getResources().getColor(R.color.black));

                    correctD.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctD.setTextColor(getResources().getColor(R.color.black));
                }
                else
                {
                    answerC.setError("Option C is required");
                    answerC.requestFocus();
                    return;
                }
            }
        });

        correctD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                correctAnswer = answerD.getText().toString().trim();

                if(!correctAnswer.isEmpty())
                {
                    correctD.setBackgroundColor(Color.parseColor("#2196F3"));
                    correctD.setTextColor(getResources().getColor(R.color.black));

                    correctB.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctB.setTextColor(getResources().getColor(R.color.black));

                    correctC.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctC.setTextColor(getResources().getColor(R.color.black));

                    correctA.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    correctA.setTextColor(getResources().getColor(R.color.black));
                }
                else
                {
                    answerD.setError("Option D is required");
                    answerD.requestFocus();
                    return;
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ansA = answerA.getText().toString().trim();
                final String ansB = answerB.getText().toString().trim();
                final String ansC = answerC.getText().toString().trim();
                final String ansD = answerD.getText().toString().trim();
                final String cat = Common.categoryId;
                final String correctAns = correctAnswer;
                final String imgQ = imgQuestion;
                final String des = description.getText().toString().trim();
                final String ques = question.getText().toString().trim();

                if (ansA.isEmpty())
                {
                    answerA.setError("Option A is required");
                    answerA.requestFocus();
                    return;
                }

                if (ansB.isEmpty())
                {
                    answerB.setError("Option B is required");
                    answerB.requestFocus();
                    return;
                }

                if (ansC.isEmpty())
                {
                    answerC.setError("Option C is required");
                    answerC.requestFocus();
                    return;
                }

                if (ansD.isEmpty())
                {
                    answerD.setError("Option D is required");
                    answerD.requestFocus();
                    return;
                }

                if (des.isEmpty())
                {
                    description.setError("description is required");
                    description.requestFocus();
                    return;
                }

                if (ques.isEmpty())
                {
                    question.setError("question is required");
                    question.requestFocus();
                    return;
                }

                if(!ansA.isEmpty()&&!ansB.isEmpty()&&!ansC.isEmpty()&&!ansD.isEmpty()&&!des.isEmpty()&&!ques.isEmpty())
                {
                    questions.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getValue()!=null)
                            {
                                for(DataSnapshot detailSnapshot: snapshot.getChildren()){

                                    value = detailSnapshot.getKey();
                                }

                                assert value != null;
                                int i = Integer.parseInt(value)+1;

                                questions.child(String.valueOf(i)).child("AnswerA").setValue(ansA);
                                questions.child(String.valueOf(i)).child("AnswerB").setValue(ansB);
                                questions.child(String.valueOf(i)).child("AnswerC").setValue(ansC);
                                questions.child(String.valueOf(i)).child("AnswerD").setValue(ansD);
                                questions.child(String.valueOf(i)).child("CategoryId").setValue(cat);
                                questions.child(String.valueOf(i)).child("CorrectAnswer").setValue(correctAns);
                                questions.child(String.valueOf(i)).child("IsImageQuestion").setValue(imgQ);
                                questions.child(String.valueOf(i)).child("Des").setValue(des);
                                questions.child(String.valueOf(i)).child("Question").setValue(ques);

                                answerA.setText("");
                                answerB.setText("");
                                answerC.setText("");
                                answerD.setText("");
                                question.setText("");
                                description.setText("");

                                Toast.makeText(PreStart3.this, "Question added", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                int i=1;

                                questions.child(String.valueOf(i)).child("AnswerA").setValue(ansA);
                                questions.child(String.valueOf(i)).child("AnswerB").setValue(ansB);
                                questions.child(String.valueOf(i)).child("AnswerC").setValue(ansC);
                                questions.child(String.valueOf(i)).child("AnswerD").setValue(ansD);
                                questions.child(String.valueOf(i)).child("CategoryId").setValue(cat);
                                questions.child(String.valueOf(i)).child("CorrectAnswer").setValue(correctAns);
                                questions.child(String.valueOf(i)).child("IsImageQuestion").setValue(imgQ);
                                questions.child(String.valueOf(i)).child("Des").setValue(des);
                                questions.child(String.valueOf(i)).child("Question").setValue(ques);

                                answerA.setText("");
                                answerB.setText("");
                                answerC.setText("");
                                answerD.setText("");
                                question.setText("");
                                description.setText("");

                                Toast.makeText(PreStart3.this, "Question added", Toast.LENGTH_SHORT).show();
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

    private void choosePicture()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri = data.getData();
            upImage.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading image..");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        final StorageReference riversRef = storageReference.child(randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded ",Snackbar.LENGTH_LONG).show();

                        pd.dismiss();

                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                question.setText(String.valueOf(uri.toString()));
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(),"Failed to upload",Toast.LENGTH_LONG).show();
                        pd.dismiss();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercentage = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Progress: "+progressPercentage+"%");
                    }
                });
    }
}
