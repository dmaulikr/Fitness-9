package com.sebastianboyd.fitness;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;


public class PreferencesActivity extends BaseActivity {

    EditText incomeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        incomeEditText = (EditText) findViewById(R.id.editText);

        Context context = getApplicationContext();
        int income = SaveData.getIntPref(context, SaveData.INCOME);
        incomeEditText.setText(Integer.toString(income));
    }

    @Override
    public void onBackPressed() {
        save();
        super.onBackPressed();
    }

    private void save() {
        String incomeString = incomeEditText.getText().toString();
        int income;
        try {
            income = Integer.parseInt(incomeString);
        } catch (NumberFormatException e) {
            Log.e("Preferences", "Could not save preferences, income invalid");
            return;
        }
        Context context = getApplicationContext();
        SaveData.setIntPref(context, SaveData.INCOME, income);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                save();
//                finish();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
