package com.example.tabunganku_fix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabunganku_fix.activity.PilihTabungan;
import com.example.tabunganku_fix.api_helper.RetrofitClient;
import com.example.tabunganku_fix.models.User;
import com.example.tabunganku_fix.response.ProfileResponse;
import com.example.tabunganku_fix.server.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_TabunganC extends AppCompatActivity {
    Context mContext;
    TextView namaprofile;
    CircleImageView profile;
    Bitmap bmp, test;
    int page =0, width = 595, height = 842;

    User users = SharedPrefManager.getInstance(this).getUser();
    String url = "http://192.168.100.10/TugasAkhir_TabunganKu/public/img/";

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabungan_c);
        Toolbar toolbar = findViewById(R.id.toolbar_tabungan_c);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_tabungan_c);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icons1);
        test = Bitmap.createScaledBitmap(bmp, 36, 36, false);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_c);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View header = navigationView.getHeaderView(0);
        namaprofile = header.findViewById(R.id.namaProfil);
        profile = header.findViewById(R.id.imageView);
        requestNama();
    }
    private void requestNama(){
        String token = SharedPrefManager.getInstance(this).getUser().getToken();
        Call<ProfileResponse> call;
        call = RetrofitClient
                .getInstance()
                .getApi()
                .ProfileSiswa(token);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()){
                    namaprofile.setText(SharedPrefManager.getInstance(MainActivity_TabunganC.this).getUser().getNama());
                    Picasso.with(MainActivity_TabunganC.this).load(url+SharedPrefManager.getInstance(MainActivity_TabunganC.this).getUser().
                            getAvatar()).into(profile);
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setMessage("Apakah anda yakin ingin kembali?");
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity_TabunganC.this, PilihTabungan.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_c);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Apakah anda yakin ingin kembali?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity_TabunganC.this, PilihTabungan.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void onCreatePDF(View view){
        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(width, height, page).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
        Paint myPaint = new Paint();
        Canvas canvas = myPage.getCanvas();
        canvas.drawBitmap(test, 12, 12, myPaint);

        //Nama Aplikas
        Paint aplikasi = new Paint();
        aplikasi.setTextAlign(Paint.Align.LEFT);
        aplikasi.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        aplikasi.setTextSize(14);
        canvas.drawText("TabunganKu", 50, 35, aplikasi);

        //Nama Pengguna
        Paint pengguna = new Paint();
        pengguna.setTextAlign(Paint.Align.LEFT);
        pengguna.setTextSize(10);
        canvas.drawText("Nama Pengguna", 20, 75, pengguna);

        //Nama Pengguna
        Paint user = new Paint();
        user.setTextAlign(Paint.Align.LEFT);
        user.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        user.setTextSize(14);
        canvas.drawText(users.getNama(), 20, 90, user);

        //Kelas Pengguna
        Paint kelas = new Paint();
        kelas.setTextAlign(Paint.Align.LEFT);
        kelas.setTextSize(10);
        canvas.drawText("Kelas Pengguna", 20, 118, kelas);

        //Kelas Pengguna
        Paint kelas_user = new Paint();
        kelas_user.setTextAlign(Paint.Align.LEFT);
        kelas_user.setTextSize(10);
        canvas.drawText(users.getKelas(), 20, 131, kelas_user);

        //Jenis Tabungan
        Paint jenis = new Paint();
        jenis.setTextAlign(Paint.Align.RIGHT);
        jenis.setTextSize(10);
        canvas.drawText("Jenis Tabungan", 560, 118, jenis);

        //Jenis Tabungan
        Paint nama_tabungan = new Paint();
        nama_tabungan.setTextAlign(Paint.Align.RIGHT);
        nama_tabungan.setTextSize(10);
        canvas.drawText("Tabungan Wisata", 560, 131, nama_tabungan);

        myPdfDocument.finishPage(myPage);

        String myFilePath = Environment.getExternalStorageDirectory().getPath()+"/Laporan_Tabungan_Reguler.pdf";
        File myFile = new File(myFilePath);

        try {
            myPdfDocument.writeTo(new FileOutputStream(myFile));
            Toast.makeText(getApplicationContext(),"Laporan berhasil tersimpan", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Terjadi keselahan", Toast.LENGTH_SHORT).show();
            //editText.setText("ERROR");
        }
        myPdfDocument.close();
    }
}
