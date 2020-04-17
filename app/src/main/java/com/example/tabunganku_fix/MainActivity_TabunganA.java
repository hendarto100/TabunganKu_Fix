package com.example.tabunganku_fix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.tabunganku_fix.adapter.TabunganA_Adapter;
import com.example.tabunganku_fix.api_helper.RetrofitClient;
import com.example.tabunganku_fix.fragment.PengaturanFragment;
import com.example.tabunganku_fix.models.JenisTabungan;
import com.example.tabunganku_fix.models.Transaksi;
import com.example.tabunganku_fix.models.User;
import com.example.tabunganku_fix.response.ProfileResponse;
import com.example.tabunganku_fix.server.SharedPrefManager;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_TabunganA extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Context mContext;
    TextView namaprofile;
    RecyclerView recyclerView;
    TabunganA_Adapter adapter;
    CircleImageView profile;
    Bitmap bmp, test;
    int page =0, width = 595, height = 842;

    User users = SharedPrefManager.getInstance(this).getUser();
    JenisTabungan jenis_tabungan = SharedPrefManager.getInstance(this).getJenisTabungan();
    String url = "http://192.168.100.10/TugasAkhir_TabunganKu/public/img/";
    List<Transaksi> transaksiList;
    Fragment fragmentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabungan_a);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icons1);
        test = Bitmap.createScaledBitmap(bmp, 36, 36, false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_tabungan_a);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View header = navigationView.getHeaderView(0);
        namaprofile = header.findViewById(R.id.namaProfil);
        profile = header.findViewById(R.id.imageView);
        requestNama();
        restart();
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
                    namaprofile.setText(SharedPrefManager.getInstance(MainActivity_TabunganA.this).getUser().getNama());
                    Picasso.with(MainActivity_TabunganA.this).load(url+SharedPrefManager.getInstance(MainActivity_TabunganA.this).getUser().
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           /* case R.id.action_profile:
                Intent intents = new Intent(MainActivity_TabunganA.this, ProfilSiswa.class);
                startActivity(intents);
                return true;*/
            case R.id.nav_tools:
                restart();
                return true;
            case R.id.action_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setMessage("Apakah anda yakin ingin kembali?");
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity_TabunganA.this, PilihTabungan.class);
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
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
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
                Intent intent = new Intent(MainActivity_TabunganA.this, PilihTabungan.class);
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

    protected void restart() {
        Fragment fragment = new PengaturanFragment();
        FragmentTransaction fragmentTrans = getSupportFragmentManager().beginTransaction();
        fragmentTrans.replace(R.id.nav_tools, fragment);
        fragmentTrans.detach(fragment);
        fragmentTrans.commit();
        //adapter.notifyDataSetChanged()
    }

    public void onCreatePDF(View view){
        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(width, height, page).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
        Paint myPaint = new Paint();
        Canvas canvas = myPage.getCanvas();
        canvas.drawBitmap(test, 12, 12, myPaint);

        //Nama Aplikasi
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

        //Tanggal Mengunduh Laporan
        Paint tanggal = new Paint();
        tanggal.setTextAlign(Paint.Align.LEFT);
        tanggal.setTextSize(10);
        canvas.drawText("Tanggal", 20, 160, tanggal);

        //Tanggal Mengunduh Laporan
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        Date date = new Date();
        Paint tanggal_download = new Paint();
        tanggal_download.setTextAlign(Paint.Align.LEFT);
        tanggal_download.setTextSize(10);
        canvas.drawText(dateFormat.format(date), 20, 175, tanggal_download);

        //Tahun Angkatan
        Paint tahun = new Paint();
        tahun.setTextAlign(Paint.Align.RIGHT);
        tahun.setTextSize(10);
        canvas.drawText("Angkatan", 560, 78, tahun);

        //Tahun Angkatan
        Paint tahun_angkatan = new Paint();
        tahun_angkatan.setTextAlign(Paint.Align.RIGHT);
        tahun_angkatan.setTextSize(10);
        canvas.drawText(String.valueOf(users.getAngkatan()), 560, 92, tahun_angkatan);

        //Jenis Tabungan
        Paint jenis = new Paint();
        jenis.setTextAlign(Paint.Align.RIGHT);
        jenis.setTextSize(10);
        canvas.drawText("Jenis Tabungan", 560, 118, jenis);

        //Jenis Tabungan
        Paint nama_tabungan = new Paint();
        nama_tabungan.setTextAlign(Paint.Align.RIGHT);
        nama_tabungan.setTextSize(10);
        canvas.drawText("Tabungan Reguler", 560, 131, nama_tabungan);

        //Tabel
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(2);
        canvas.drawRect(20, 200, 575,230,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("No.", 40, 220, myPaint);
        canvas.drawText("Tanggal", 100, 220, myPaint);
        canvas.drawText("Kegiatan Transaksi", 240, 220, myPaint);
        canvas.drawText("Nominal", 480, 220, myPaint);

        canvas.drawLine(80, 205, 80, 225, myPaint);
        canvas.drawLine(190, 205, 190, 225, myPaint);
        canvas.drawLine(400, 205, 400, 225, myPaint);



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
  /*String myString = editText.getText().toString();
        int x = 10, y = 25;

        for (String line : myString.split("\n")){
            myPage.getCanvas().drawText(line, x, y, myPaint);
            y+=myPaint.descent()-myPaint.ascent();
        }*/

