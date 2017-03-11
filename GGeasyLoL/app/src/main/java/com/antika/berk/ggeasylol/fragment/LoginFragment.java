package com.antika.berk.ggeasylol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;

public class LoginFragment extends Fragment {
    TextView forget, signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        forget=(TextView)view.findViewById(R.id.forget);
        signup=(TextView)view.findViewById(R.id.signUp);

        SpannableString content = new SpannableString("Forget Password?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        forget.setText(content);
        SpannableString content1 = new SpannableString("Sign Up for GGEasy-LOL");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        signup.setText(content1);


        return view;
    }

}
