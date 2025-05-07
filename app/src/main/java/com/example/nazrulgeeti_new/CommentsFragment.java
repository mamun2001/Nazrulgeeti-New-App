package com.example.nazrulgeeti_new;

//package com.example.nazrulgeetiapp;

public class CommentsFragment extends HomeFragment {
    @Override
    public void onStart() {
        super.onStart();
        webView.loadUrl("https://nazrulgeeti.org/opinion");
    }
}

