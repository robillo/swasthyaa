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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Signin extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    boolean flag=false;

    ProgressDialog progressDialog;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.inject(this);
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);
        progressDialog = new ProgressDialog(Signin.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        BackGround backGround=new BackGround();
        try {
            String l=backGround.execute(email,password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // TODO: Implement your own authentication logic here.

        /*new android.os.Handler().postDelayed(
               new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        //onLoginSuccess();
                        // onLoginFailed();
                       BackGround backGround=new BackGround();
                        backGround.execute(email,password);
                        progressDialog.dismiss();
                    }
                }, 4000);*///progressDialog.dismiss();
        /*if(flag)onLoginSuccess();
        else onLoginFailed();*/


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        progressDialog.dismiss();
        Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();
       /* Intent intent=new Intent(this, );
        startActivity(intent);*/
        Intent i = new Intent(this, NavDrawerActivity.class);
        startActivity(i);
    }
    public void onLoginFailed1() {
        Log.e("hiiiiiiiiii","double");
        progressDialog.dismiss();
        Log.e("hiiiiiiiiii","trouble");
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

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

        return valid;
    }
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {Log.e("hiiiiiiiiii","1");
                URL url = new URL("http://www.supergeek.16mb.com/login.php");
                String urlParams = "name="+name+"&password="+password;
                Log.e("hiiiiiiiiii",name+"  "+password);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                Log.v("hiiiiiiiiii","2");
                OutputStream os = httpURLConnection.getOutputStream();
                Log.e("hiiiiiiiiii","3");
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                Log.e("hiiiiiiiiii","4");
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                Log.e("hiiiiiiiiii","5");
                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {Log.e("hiiiiiiiiii","6");
            String err=null;
            String NAME,PASSWORD,EMAIL,PHONE=null;
            Log.e("hiiiiiiiiii","7");
            if(!s.equals("{\"user_data\":[]}")){Log.e("hiiiiiiiiii",s);
                try {
                    JSONObject root = new JSONObject(s);
                    JSONObject user_data = root.getJSONObject("user_data");
                    NAME = user_data.getString("name");
                    Log.e("hiiiiiiiiii",NAME);
                    PASSWORD = user_data.getString("password");Log.e("hiiiiiiiiii",PASSWORD);
                    EMAIL = user_data.getString("email");Log.e("hiiiiiiiiii",EMAIL);
                    PHONE=user_data.getString("phoneno");Log.e("hiiiiiiiiii",PHONE);
                    Log.e("hiiiiifvfvefvevii","shit");
                    onLoginSuccess();


                    //Toast.makeText(MainActivity.this,PHONE,Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: "+e.getMessage();
                }}
            else {Log.v("HEEEEEEEE","FFFFFFFFFF");onLoginFailed1();}

            /*Intent i = new Intent(ctx, Main2Activity.class);
            i.putExtra("name", NAME);
            i.putExtra("password", PASSWORD);
            i.putExtra("email", EMAIL);
            i.putExtra("phone",PHONE);
            i.putExtra("err", err);
            startActivity(i);*/

        }
    }
}