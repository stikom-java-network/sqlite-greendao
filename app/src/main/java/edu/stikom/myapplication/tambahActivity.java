package edu.stikom.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambahActivity extends AppCompatActivity {
private EditText nama, harga;
private Button simpan;
private DaoSession dbSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        getSupportActionBar().setTitle("Insert Data Barang");
        dbSession = dbhelper.getInstance(tambahActivity.this);
        nama = findViewById(R.id.edtnama);
        harga = findViewById(R.id.edtharga);
        simpan = findViewById(R.id.simpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama1 = nama.getText().toString();
                String harga1 = harga.getText().toString();
                if (nama1.isEmpty() && harga1.isEmpty()){
                    Toast.makeText(tambahActivity.this,"inputkan datanya",Toast.LENGTH_SHORT).show();
                }else {
                    barang b = new barang();
                    b.setNama(nama1);
                    b.setHarga(Integer.parseInt(harga1));
                    dbSession.getBarangDao().insert(b);
                    Toast.makeText(tambahActivity.this,"Berhasil memasukan datanya",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(tambahActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
