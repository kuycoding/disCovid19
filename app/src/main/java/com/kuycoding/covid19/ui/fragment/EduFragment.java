package com.kuycoding.covid19.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kuycoding.covid19.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EduFragment extends Fragment implements View.OnClickListener {
    private CardView card_mengenal;
    private LinearLayout btnMengenalDown;
    private LinearLayout expand_mengenal;
    private ImageView arrowUpMengenal;

    private CardView card_mencegah;
    private LinearLayout btnMencegahDown,expand_mencegah;
    private ImageView arrowUpMencegah;

    private CardView card_mengobati;
    private LinearLayout btnMengobatiDown,expand_mengobati;
    private ImageView arrowUpMengobati;

    private CardView card_penyebab;
    private LinearLayout btnPenyebabDown,expand_penyebab;
    private ImageView arrowUpPenyebab;

    private CardView card_gejala;
    private LinearLayout btnGejalaDown,expand_gejala;
    private ImageView arrowUpGejala;

    public EduFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        card_mengenal = view.findViewById(R.id.cardView_mengenal);
        btnMengenalDown = view.findViewById(R.id.btn_mengenal);
        expand_mengenal = view.findViewById(R.id.expandable_mengenal);
        arrowUpMengenal = view.findViewById(R.id.arrow_up_mengenal);
        btnMengenalDown.setOnClickListener(this);

        card_mencegah = view.findViewById(R.id.card_mencegah);
        btnMencegahDown = view.findViewById(R.id.btn_mencegah);
        expand_mencegah = view.findViewById(R.id.expandable_mencegah);
        arrowUpMencegah = view.findViewById(R.id.arrup_up_mencegah);
        btnMencegahDown.setOnClickListener(this);

        card_mengobati = view.findViewById(R.id.card_mengobatan);
        btnMengobatiDown = view.findViewById(R.id.btn_mengobati);
        expand_mengobati = view.findViewById(R.id.expandable_mengobati);
        arrowUpMengobati = view.findViewById(R.id.arrow_up_mengobati);
        btnMengobatiDown.setOnClickListener(this);

        card_penyebab = view.findViewById(R.id.card_penyebab);
        btnPenyebabDown = view.findViewById(R.id.btn_penyebab);
        expand_penyebab = view.findViewById(R.id.expandable_penyebab);
        arrowUpPenyebab = view.findViewById(R.id.arrow_up_penyebab);
        btnPenyebabDown.setOnClickListener(this);

        card_gejala = view.findViewById(R.id.cardView_gejala);
        btnGejalaDown = view.findViewById(R.id.btn_gejala);
        expand_gejala = view.findViewById(R.id.expandable_gejala);
        arrowUpGejala = view.findViewById(R.id.arrow_up_gejala);
        btnGejalaDown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mengenal:
                mengenalVirus();
                break;
            case R.id.btn_mencegah:
                mencegahVirus();
                break;
            case R.id.btn_mengobati:
                mengobatiVirus();
                break;
            case R.id.btn_penyebab:
                penyebabVirus();
                break;
            case R.id.btn_gejala:
                gejalaVirus();
                break;

        }
    }

    private void gejalaVirus() {
        if (expand_gejala.getVisibility()==View.GONE) {
            TransitionManager.beginDelayedTransition(card_gejala, new AutoTransition());
            expand_gejala.setVisibility(View.VISIBLE);
            arrowUpGejala.setBackgroundResource(R.drawable.ic_arrow_up);
        } else {
            expand_gejala.setVisibility(View.GONE);
            arrowUpGejala.setBackgroundResource(R.drawable.ic_arrow_down);
        }
    }

    private void penyebabVirus() {
        if (expand_penyebab.getVisibility()==View.GONE) {
            TransitionManager.beginDelayedTransition(card_penyebab, new AutoTransition());
            expand_penyebab.setVisibility(View.VISIBLE);
            arrowUpPenyebab.setBackgroundResource(R.drawable.ic_arrow_up);
        } else {
            expand_penyebab.setVisibility(View.GONE);
            arrowUpPenyebab.setBackgroundResource(R.drawable.ic_arrow_down);
        }
    }

    private void mengobatiVirus() {
        if (expand_mengobati.getVisibility()==View.GONE) {
            TransitionManager.beginDelayedTransition(card_mengobati, new AutoTransition());
            expand_mengobati.setVisibility(View.VISIBLE);
            arrowUpMengobati.setBackgroundResource(R.drawable.ic_arrow_up);
        } else {
            expand_mengobati.setVisibility(View.GONE);
            arrowUpMengobati.setBackgroundResource(R.drawable.ic_arrow_down);
        }
    }

    private void mencegahVirus() {
        if (expand_mencegah.getVisibility()==View.GONE) {
            TransitionManager.beginDelayedTransition(card_mencegah, new AutoTransition());
            expand_mencegah.setVisibility(View.VISIBLE);
            arrowUpMencegah.setBackgroundResource(R.drawable.ic_arrow_up);
        } else {
            expand_mencegah.setVisibility(View.GONE);
            arrowUpMencegah.setBackgroundResource(R.drawable.ic_arrow_down);
        }
    }

    private void mengenalVirus() {
        if (expand_mengenal.getVisibility()==View.GONE) {
            TransitionManager.beginDelayedTransition(card_mengenal, new AutoTransition());
            expand_mengenal.setVisibility(View.VISIBLE);
            arrowUpMengenal.setBackgroundResource(R.drawable.ic_arrow_up);
        } else {
            expand_mengenal.setVisibility(View.GONE);
            arrowUpMengenal.setBackgroundResource(R.drawable.ic_arrow_down);
        }
    }
}
