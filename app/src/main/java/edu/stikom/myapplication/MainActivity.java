package edu.stikom.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements adapter.barangadapterfungsi,ubahActivity.ubahActivityListener{

    private RecyclerView rvbarang;
    private DaoSession dbsesion;
    private adapter brgadapter;
    private List<barang> lis_barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Data Barang");
        dbsesion = dbhelper.getInstance(MainActivity.this);
        rvbarang=findViewById(R.id.rvbrg);

        //tampil data ke recycle
        lis_barang=dbsesion.getBarangDao().queryBuilder().list();
        brgadapter = new adapter(lis_barang,MainActivity.this);
        rvbarang.setLayoutManager(new LinearLayoutManager(this));

        rvbarang.setAdapter(brgadapter);
        brgadapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, tambahActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onUpdate(int position) {
        long id = lis_barang.get (position).getIdbarang();
        String nama = lis_barang.get (position).getNama();
        int harga = lis_barang.get (position).getHarga();

        FragmentManager fm = getSupportFragmentManager();
        ubahActivity fragment = ubahActivity.newintance(id,nama,harga);
        fragment.show(fm,"dialog ubah");


    }

    @Override
    public void onDelete(int position) {

        String nama = lis_barang.get (position).getNama();
        showDialogHapus(position,nama);

    }

    @Override
    public void requestUpdate(long id, String nama, int harga) {
        barang b = dbsesion.getBarangDao().load(id);
        b.setNama(nama);
        b.setHarga(harga);
        dbsesion.getBarangDao().update(b);
        brgadapter.notifyDataSetChanged();
    }

    private void showDialogHapus(final int position, String nama) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Yakin ingin menghapus barang "+ nama + " ?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*
                        Fungsi delete suatu data bedasarkan idnya.
                         */
                        long idBrg = lis_barang.get(position).getIdbarang();
                        dbsesion.getBarangDao().deleteByKey(idBrg);

                        lis_barang.remove(position);
                        brgadapter.notifyItemRemoved(position);
                        brgadapter.notifyItemRangeChanged(position, lis_barang.size());

                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }


}


