package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.tv.TvContract;
import android.net.Uri;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import static com.example.docsapp.R.id.Drawer_Doctor;
import static com.example.docsapp.R.id.Drawer_Home;
import static com.example.docsapp.R.id.Home;
import static com.example.docsapp.R.id.Profile;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation_bar);
        toolbar=findViewById(R.id.Toolbar);
        Auth=FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.drawer_navigation_view);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Toolbar_cart:
                        Intent cart=new Intent(MainActivity.this,Cart.class);
                        startActivity(cart);
                        break;
                    case R.id.Toolbar_Notification_bell:
                        Intent notification=new Intent(MainActivity.this,AllNotificationsActivity.class);
                        startActivity(notification);
                        break;
                    case R.id.Toolbar_orders:
                        Intent orderList=new Intent(MainActivity.this,OrdersList.class);
                        startActivity(orderList);
                        break;
                    case R.id.Toolbar_share:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,"Hello ! we are Pshop and we are providing you a platform which will deliver item to you within 1 hour");
                        sendIntent.setType("text/plain");
                        Intent.createChooser(sendIntent,"Share via");
                        startActivity(sendIntent);
                        break;
                }
                return true;
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){

                    case Drawer_Doctor:
                        fragment= new Doctor();
                        bottomNavigationView.setSelectedItemId(R.id.Doctor);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Drawer_Home:
                        fragment= new home();
                        bottomNavigationView.setSelectedItemId(R.id.Home);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.Drawer_Profile:
                        fragment= new profile();
                        bottomNavigationView.setSelectedItemId(Profile);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.Drawer_lab_test:
                        fragment= new lab_test();
                        bottomNavigationView.setSelectedItemId(R.id.lab_test);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.Drawer_store:
                        fragment= new store();
                        bottomNavigationView.setSelectedItemId(R.id.store);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.Drawer_share_app:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,"Hello ! we are Pshop and we are providing you a platform which will deliver item to you within 1 hour");
                        sendIntent.setType("text/plain");
                        Intent.createChooser(sendIntent,"Share via");
                        startActivity(sendIntent);
                        break;
                    case R.id.Drawer_login_again:
                        Intent login=new Intent(MainActivity.this,Login_1.class);
                        startActivity(login);

                        break;
                    case R.id.Drawer_logout:
                        FirebaseAuth.getInstance().signOut();
                        Auth.signOut();
                        Toast.makeText(MainActivity.this, "Loged out Successfully", Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(MainActivity.this,FirstLogin.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.Drawer_Chat_with_us:
                        Intent Query=new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+"8218655014"));
                        startActivity(Query);
                        break;
                    case R.id.Drawer_Call_us:
                        Intent call=new Intent(Intent.ACTION_CALL);
                        call.setData(Uri.parse("tel:" +"8218305987"));
                        startActivity(call);
                        break;


                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                if (fragment!=null)
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,fragment ).commit();
                return true;
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,new home() ).commit();

    }



    public BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment SelectedFragment = null;
            switch (item.getItemId()){
                case Profile:
                    SelectedFragment=new profile();
                    break;
                case R.id.Doctor:
                    SelectedFragment=new Doctor();
                    break;
                case R.id.lab_test:
                    SelectedFragment=new lab_test();
                    break;
                case R.id.store:
                    SelectedFragment=new store();
                    break;
                case R.id.Home:
                    SelectedFragment=new home();
                    break;
                default:
                    SelectedFragment=new home();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,SelectedFragment).commit();
            return true;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_options,menu);
        return true;
    }

}