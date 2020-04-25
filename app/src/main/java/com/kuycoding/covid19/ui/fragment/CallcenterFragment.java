package com.kuycoding.covid19.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.kuycoding.covid19.R;
import com.kuycoding.covid19.helper.JavaMailAPI;
import com.kuycoding.covid19.ui.activities.ContactActivity;
import com.kuycoding.covid19.ui.activities.bantuan.HotlineActivity;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallcenterFragment extends Fragment implements View.OnClickListener {
    private LinearLayout layout_hotline, layout_rs, layout_qna, layout_contact;
    private EditText edNama, edEmail, edPesan;
    private ImageButton btnClose;

    public CallcenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_callcenter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout_hotline = view.findViewById(R.id.layout_hotline);
        layout_hotline.setOnClickListener(this);

        layout_rs = view.findViewById(R.id.layout_rs);
        layout_rs.setOnClickListener(this);

        layout_qna = view.findViewById(R.id.layout_qna);
        layout_qna.setOnClickListener(this);

        layout_contact = view.findViewById(R.id.layout_contact_us);
        layout_contact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_hotline:
                startActivity(new Intent(getContext(), HotlineActivity.class));
                break;
            case R.id.layout_rs:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/d/u/0/viewer?mid=1G0xfB8QRIFsLkZHLCHVX4faL7RxDLPdG&ll=-6.696208893380595%2C111.37281458515122&z=8"));
                startActivity(intent);
                break;
            case R.id.layout_qna:
                String url = "http://www.covid19.kuycoding.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.layout_contact_us:
                //dialogContact();
                startActivity(new Intent(getContext(), ContactActivity.class));
                break;
        }
    }

    private void dialogContact() {
        AlertDialog dialog = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme).create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ViewGroup viewGroup = Objects.requireNonNull(getView()).findViewById(android.R.id.content);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_contact, viewGroup, false);
        dialog.setView(view);
        edNama = view.findViewById(R.id.ed_nama);
        edEmail = view.findViewById(R.id.ed_email);
        edPesan = view.findViewById(R.id.ed_pesan);
        btnClose = view.findViewById(R.id.btnClose);
        Button btnSend = view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> {
            String nama = edNama.getText().toString().trim();
            String email = edEmail.getText().toString().trim();
            String pesan = edPesan.getText().toString().trim();
            JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(), email, nama, pesan);
            javaMailAPI.execute();
            dialog.dismiss();
        });
        btnClose.setOnClickListener(v -> {
            dialog.cancel();
        });
        dialog.show();
    }
}
