package com.kuycoding.covid19.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.kuycoding.covid19.R;
import com.kuycoding.covid19.helper.JavaMailAPI;

import java.util.Objects;

public class ContactActivity extends AppCompatActivity {
    private EditText edNama, edEmail, edPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        edNama = findViewById(R.id.ed_nama);
        edEmail = findViewById(R.id.ed_email);
        edPesan = findViewById(R.id.ed_pesan);
        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> {
            final String nama = edNama.getText().toString().trim();
            final String email = edEmail.getText().toString().trim();
            final String pesan = edPesan.getText().toString().trim();
            if (TextUtils.isEmpty(nama)) {
                edNama.setError("Tidak boleh kosong!");
            } else if (TextUtils.isEmpty(email)) {
                edEmail.setError("Tidak boleh kosong!");
            } else if (TextUtils.isEmpty(pesan)) {
                edPesan.setError("Tidak boleh kosong!");
            } else {
                dialogConfirm();
            }
        });

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextAppearance(this, R.style.GoogleFontBold);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(item -> false);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hubungi Kami");
    }

    private void dialogConfirm() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.create();
        dialog.setTitle("Konfirmasi");
        dialog.setMessage("Data yang anda masukkan sudah benar?");
        dialog.setPositiveButton("Kirim", (dialog1, which) -> {
            String nama = edNama.getText().toString().trim();
            String email = edEmail.getText().toString().trim();
            String pesan = edPesan.getText().toString().trim();
            JavaMailAPI javaMailAPI = new JavaMailAPI(this, email, nama, pesan);
            javaMailAPI.execute();
        });
        dialog.setNegativeButton("Batal", (dialog12, which) -> dialog12.cancel());
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
