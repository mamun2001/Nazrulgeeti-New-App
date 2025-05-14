package com.sleepingcat.nazrulgeeti_new;

//package com.example.nazrulgeetiapp;

public class ContactFragment extends HomeFragment {
    @Override
    public void onStart() {
        super.onStart();
        webView.loadUrl("https://nazrulgeeti.org/contact");
    }
}

