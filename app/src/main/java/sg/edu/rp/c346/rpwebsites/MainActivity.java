package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spn1;
    Spinner spn2;
    Button btnGo;
    ArrayList<String> alCategory;
    ArrayAdapter<String> aaCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn1 = findViewById(R.id.spinner1);
        spn2 = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.buttonGo);

        // Initialise the ArrayList
        alCategory = new ArrayList<>();

        // Create an ArrayAdapter using the default Spinner layout and the ArrayList
        aaCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alCategory);

        // Bind the ArrayAdapter to the spinner
        spn2.setAdapter(aaCategory);

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        alCategory.clear();
                        //Get the string-array and store as an Array
                        String[] strNumbers = getResources().getStringArray(R.array.sub_category_rp);
                        // Shared Preference
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Integer savePosition2 = prefs.getInt("savePosition2", 0);
                        spn2.setSelection(savePosition2);
                        //Convert Array to List and add to the ArrayList
                        alCategory.addAll(Arrays.asList(strNumbers));
                        aaCategory.notifyDataSetChanged();
                        break;
                    case 1:
                        alCategory.clear();
                        //Get the string-array and store as an Array
                        strNumbers = getResources().getStringArray(R.array.sub_category_soi);
                        // Shared Preference
                        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        savePosition2 = prefs.getInt("savePosition2", 0);
                        spn2.setSelection(savePosition2);
                        //Convert Array to List and add to the ArrayList
                        alCategory.addAll(Arrays.asList(strNumbers));
                        aaCategory.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int spinner1 = spn1.getSelectedItemPosition();
                int spinner2 = spn2.getSelectedItemPosition();
                if (spinner1 == 0 && spinner2 == 0) {
                    Intent intent = new Intent(getBaseContext(), WebPage.class);
                    intent.putExtra("url", "https://www.rp.edu.sg");
                    startActivity(intent);
                } else if (spinner1 == 0 && spinner2==1) {
                    Intent intent = new Intent(getBaseContext(), WebPage.class);
                    intent.putExtra("url", "https://www.rp.edu.sg/student-life");
                    startActivity(intent);
                } else if (spinner1 == 1 && spinner2 == 0) {
                    Intent intent = new Intent(getBaseContext(), WebPage.class);
                    intent.putExtra("url", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getBaseContext(), WebPage.class);
                    intent.putExtra("url", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12");
                    startActivity(intent);
                }
                String[][] sites = {
                        {"https://www.rp.edu.sg", "https://www.rp.edu.sg/student-life"},
                        {"https://www.rp.edu.sg/soi/full-time-diplomas/details/r47", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"},
                };

                String url = sites[spn1.getSelectedItemPosition()][spn2.getSelectedItemPosition()];

                Intent intent = new Intent(getBaseContext(), WebPage.class);
                intent.putExtra("url", url);
                startActivity(intent);


                // Step 1a: Obtain an instance of the SharedPreference
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                // Step 1b: Obtain an instance of the SharedPreference Editor for update later
                SharedPreferences.Editor prefEdit = prefs.edit();

                // Step 1c: Add the key-value pair
                int savePosition1 = spn1.getSelectedItemPosition();
                int savePosition2 = spn2.getSelectedItemPosition();
                prefEdit.putInt("savePosition1", savePosition1);
                prefEdit.putInt("savePosition2", savePosition2);

                // Step 1d: Call commit() to save the changes into SharedPreferences
                prefEdit.commit();
            }

        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data with the "greeting" from the SharedPreference object.
        // this make it so that the first greeting is "No greetings!" but after the app is paused,
        // the greeting will always be "Hello!"
        Integer savePosition1 = prefs.getInt("savePosition1", 0);

        spn1.setSelection(savePosition1);

    }
}
