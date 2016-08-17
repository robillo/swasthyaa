package swasthyalehar.swasthya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//vv
        setContentView(R.layout.activity_main);//
    }
    public void signin(View view)
    {
        Intent intent=new Intent(this,Signin.class);
        startActivity(intent);
    }
    public void signout(View view)
    {
        Intent intent=new Intent(this,Signup.class);
        startActivity(intent);
    }

    public void leaderboard(View view)
    {
        //Go to Leaderboard page
        startActivity(new Intent(this,LeaderBoardMainActivity.class));
    }
}
