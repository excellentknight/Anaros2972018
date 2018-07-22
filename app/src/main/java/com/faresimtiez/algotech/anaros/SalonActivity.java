package com.faresimtiez.algotech.anaros;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class SalonActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewFlipper viewFlipper;
    Button next,pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper);

        next=(Button)findViewById(R.id.btn_next_day);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
            }
        });
        pre=(Button)findViewById(R.id.btn_previous_day);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.salon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(SalonActivity.this, ServicesListActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_salon) {
            Intent i = new Intent(SalonActivity.this, SalonActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_work) {

        } else if (id == R.id.nav_admin) {
            Intent i = new Intent(SalonActivity.this, AdminLoginActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("client:"));
            String[] recipents = {"contact@anarosinstitut.com"};
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, recipents);
            intent.putExtra(Intent.EXTRA_SUBJECT, "ANAROS Reviews");
            Intent chooser = Intent.createChooser(intent, "Send Feedback Via");
            startActivity(chooser);
        } else if (id == R.id.nav_share_ex) {
            Intent i = new Intent(SalonActivity.this, ShareExActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setData(Uri.parse("ANAROS:"));
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_SUBJECT, "ANAROS");
            System.out.println(""+R.string.email_content);
            intent.putExtra(Intent.EXTRA_TEXT, ""+getText(R.string.email_content)+getText(R.string.link));
            Intent chooser = Intent.createChooser(intent, "Share using");
            startActivity(chooser);
        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(SalonActivity.this, ContactActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
