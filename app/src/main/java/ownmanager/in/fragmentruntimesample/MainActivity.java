package ownmanager.in.fragmentruntimesample;

/**Aim : To create a RunTimeFragment with buttons inside fragment activities
 *      commit 2 : Adding navigation
 *          implement .addToBackStack() before commit
 *      commit 3 : Fragment communication
 *          Communication between fragment and activity interfaces
 *          code implemented in SecondFragment and MainActivity
 * Specify a container on the MainActivity layout file
 * */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SecondFragment.OnMessageReadListner { //implement the OnMessageReadListner

    public static FragmentManager fragmentManager;
    FrameLayout fragmentConatiner;
    TextView messageDisplayTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentConatiner = findViewById(R.id.fragment_container);
        messageDisplayTV = findViewById(R.id.displayTV);

        fragmentManager = getSupportFragmentManager(); // object of fragment manager

        /**if you want to add a fragment into the container onCreate*/
        if(fragmentConatiner != null){ // check whether there is container or not

            if(savedInstanceState != null){ // prevents overlapping of fragments on recreating activities eg: on Navigation
                return;
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();// object of fragment transaction
            fragmentTransaction.add(R.id.fragment_container,new HomeFragment(),null);// Replacing container with homeFragment
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onMessageRead(String message) {
        messageDisplayTV.setText(message);
    }
}
