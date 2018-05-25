package srkr.csea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UpdatesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String e1,college_url,class_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        e1 = intent.getStringExtra("message");
        college_url = "http://prasadguttula68.000webhostapp.com/showupdate.php";
        class_url = "http://prasadguttula68.000webhostapp.com/class.php";
        WebView webView = (WebView) findViewById(R.id.webpage);
        if (e1.equals("College")) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(college_url);
            webView.setWebViewClient(new MyBrowser());
        } else if (e1.equals("Class")) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(class_url);
            webView.setWebViewClient(new MyBrowser());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView,String view_url){
            webView.loadUrl(view_url);
            return true;
        }
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
        getMenuInflater().inflate(R.menu.updates, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.Home)
        {
            Intent i1=new Intent(UpdatesActivity.this,MainActivity.class);
            startActivity(i1);
        }
        if(id==R.id.newupdate)
        {
            Intent i2=new Intent(UpdatesActivity.this,NewUpdateActivity.class);
            startActivity(i2);
        }

        if(id==R.id.quit)
        {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
