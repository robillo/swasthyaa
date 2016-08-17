package swasthyalehar.swasthya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Signup extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private boolean flag=false;
    @InjectView(R.id.input_name)
    EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.phoneno) EditText _phone;
    @InjectView(R.id.btn_signup)
    Button _signupButton;
    @InjectView(R.id.link_login)
    TextView _loginLink;
    @InjectView(R.id.confirm) TextView confirmpass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        flag=false;
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        final String phone=_phone.getText().toString();
        // TODO: Implement your own signup logic here.
        final BackGround b=new BackGround();
        b.execute(name,email,password,phone);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                       /* try {
                            String s= b.execute(name,email,password,phone).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }*/
                        if(flag)
                        {onSignupSuccess();
                            progressDialog.dismiss();
                            Intent intent=new Intent(Signup.this,Waiting.class);
                            startActivity(intent);
                        }
                        else {onSignupFailed();
                            progressDialog.dismiss();}
                        // onSignupFailed();
                      /*  progressDialog.dismiss();
                        Intent intent=new Intent(Signup.this,Waiting.class);
                        startActivity(intent);*/
                    }
                },3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        //finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String phone=_phone.getText().toString();
        String confirm=confirmpass.getText().toString();
        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        if(phone.isEmpty()||phone.length()!=10)
        {
            _phone.setError("Incorrect Mobile no.");
            valid=false;
        }
        if(!confirm.equals(password))
        {
            confirmpass.setError("Password Don't Match.");
            valid=false;
        }
        else
        {
            _phone.setError(null);
        }

        return valid;
    }
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String email = params[1];
            String password = params[2];
            String phone=params[3];
            String data="";
            int tmp;

            try {//Toast.makeText(SignupActivity.this,"1",Toast.LENGTH_LONG).show();
                String urlParams = "name="+name+"&email="+email+"&password="+password+"&phone="+phone;
                URL url = new URL("http://www.supergeek.16mb.com/register.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                // Toast.makeText(SignupActivity.this,"3",Toast.LENGTH_LONG).show();
                OutputStream os = httpURLConnection.getOutputStream();
                //Toast.makeText(SignupActivity.this,"4",Toast.LENGTH_LONG).show();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                // Toast.makeText(SignupActivity.this,"2",Toast.LENGTH_LONG).show();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                // Toast.makeText(SignupActivity.this,"7",Toast.LENGTH_LONG).show();
                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();//Toast.makeText(SignupActivity.this,"error",Toast.LENGTH_LONG).show();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();//Toast.makeText(SignupActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                //  s="Signup successful.";
                flag=true;
                Toast.makeText(Signup.this,"Signup Successful",Toast.LENGTH_LONG).show();
            }
            //  Toast.makeText(SignupActivity.this,s,Toast.LENGTH_LONG).show();
        }
    }

}
