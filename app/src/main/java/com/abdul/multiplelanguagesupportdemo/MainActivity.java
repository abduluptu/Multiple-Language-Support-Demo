package com.abdul.multiplelanguagesupportdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.abdul.multiplelanguagesupportdemo.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show AlertDialog to display list of languages
                showChangeLanguageDialog();
            }
        });

    }

    private void showChangeLanguageDialog() {
        // Language options array
        final String languages[] = {"English", "Arabic", "Hindi", "Nepali", "Urdu"};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (position == 0) {
                    // English
                    setLocale("en");
                    recreate();
                } else if (position == 1) {
                    // Arabic
                    setLocale("ar");
                    recreate();
                } else if (position == 2) {
                    // Hindi
                    setLocale("hi");
                    recreate();
                } else if (position == 3) {
                    // Nepali
                    setLocale("ne");
                    recreate();
                } else if (position == 4) {
                    // Urdu
                    setLocale("ur");
                    recreate();
                }
                //dismiss AlertDialog once language is chosen
                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String language) {
        Locale mLocale = new Locale(language);
        Locale.setDefault(mLocale);

        Configuration configuration = new Configuration();
        configuration.locale = mLocale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        // Save language to Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putString("app_lang", language);
        mEditor.apply();
    }

    // Load Language saved in Shared Preferences
    private void loadLocale() {
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = preferences.getString("app_lang", "");
        setLocale(language);
    }
}