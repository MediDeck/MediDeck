package com.example.medideck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ciphers_sign_in extends AppCompatActivity {
    private Button nextpage;
    EditText Name, number, age, city ,email, pass;

    RadioButton radioDocButton,radioPatButton;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    private static final String TAG = "cipher_sign_in";
    private static final String USERS = "users";
    private UserHelperClass uhc;
    FirebaseAuth mAuth;
    public String type1;
    public String Emailkey;
    public String profileSave = "false";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cipher_sign_in);
        radioDocButton = (RadioButton) findViewById(R.id.doctor_radio);
        radioPatButton = (RadioButton) findViewById(R.id.patient_radio);
        Name = findViewById(R.id.name_si_edittext);
        number = findViewById(R.id.number_si_edittext);
        age = findViewById(R.id.age_si_edittext);
        city = findViewById(R.id.city_si_edittext);
        email = findViewById(R.id.email_si_edittext);
        pass = findViewById(R.id.password_si_edittext);
        nextpage = (Button) findViewById(R.id.signup_button);
        rootnode = FirebaseDatabase.getInstance();
        //reference = rootnode.getReference("USERS");
        mAuth = FirebaseAuth.getInstance();

        nextpage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {





                //rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("P_d");
                //mAuth = FirebaseAuth.getInstance();

                String Name1 = Name.getText().toString();
                String number1 = number.getText().toString();
                String age1 = age.getText().toString();
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();
                String city1 = city.getText().toString();
                Emailkey = email1.replace(".",",");
                Emailkey = Emailkey.replace("@",",");
                if(radioPatButton.isChecked()) {
                    type1 = radioPatButton.getText().toString();
                    System.out.println(type1);
                }else if (radioDocButton.isChecked()){
                    type1 = radioDocButton.getText().toString();
                    System.out.println(type1);
                }


                if (email1.isEmpty()) {
                    email.setError("Please enter email id");
                    email.requestFocus();
                } else if (pass1.isEmpty()) {
                    pass.setError("Please enter your password");
                    pass.requestFocus();
                } else if (number1.isEmpty()) {
                    number.setError("Please enter your Phone Number");
                    number.requestFocus();
                } else if (Name1.isEmpty()) {
                    Name.setError("Please enter your Full Name");
                    Name.requestFocus();
                } else if (age1.isEmpty()) {
                    age.setError("Please enter your Age");
                    age.requestFocus();
                } else if (city1.isEmpty()) {
                    city.setError("Please enter your City");
                    city.requestFocus();
                } else {

                    mAuth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ciphers_sign_in.this, "SignUp Successful", Toast.LENGTH_SHORT).show();

                                uhc = new UserHelperClass(Name1, number1, age1, city1, email1, pass1,type1,Emailkey,profileSave);
                                reference.child(Emailkey).setValue(uhc);

                                if(radioPatButton.isChecked()) {
                                    openPatPage();
                                }else if (radioDocButton.isChecked()){
                                    openDocPage();
                                }




                            } else {
                                Toast.makeText(ciphers_sign_in.this, "Could Not sign you Up", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }
            }
        });
    }



    private void openPatPage() {
        Intent intent3 = new Intent(this, patient_home_page.class);
        intent3.putExtra("emailidkey",Emailkey);
        startActivity(intent3);
    }

    private void openDocPage() {
        Intent intent11 = new Intent(this,doctor_home.class);
        intent11.putExtra("emailidkey",Emailkey);
        startActivity(intent11);
    }

}