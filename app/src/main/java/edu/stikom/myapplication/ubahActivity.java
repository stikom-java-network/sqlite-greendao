package edu.stikom.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ubahActivity extends DialogFragment {
    private EditText ednama, edharga;
    private Button ubah;

    private long idbrang;
    private String namabarang;
    private int hargabarang;

    private ubahActivityListener mlistener;

    //constructor
    public ubahActivity() {
    }


    //interface
    public interface ubahActivityListener{
        void requestUpdate (long id, String nama, int harga);
    }

    //function
    public static ubahActivity newintance(long id, String nama, int harga){
        ubahActivity a = new ubahActivity();
        Bundle b = new Bundle();
        b.putLong("ID",id);
        b.putString("Nama", nama);
        b.putInt("Harga", harga);
        a.setArguments(b);
        return a;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            idbrang= getArguments().getLong("ID");
            namabarang= getArguments().getString("Nama");
            hargabarang= getArguments().getInt("Harga");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_ubah, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ednama = view.findViewById(R.id.edtnama);
        edharga = view.findViewById(R.id.edtharga);
        ubah = view.findViewById(R.id.simpan);

        ednama.setText(namabarang);
        edharga.setText(String.valueOf(hargabarang));

        getDialog().setTitle("Ubah Data Barang");
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = ednama.getText().toString();
                String harga = edharga.getText().toString();

                mlistener.requestUpdate(idbrang,nama, Integer.parseInt(harga));
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mlistener = (ubahActivityListener)context;
    }
}
