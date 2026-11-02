package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private Spinner spinnerSourceUnit;
    private Spinner spinnerTargetUnit;
    private EditText editTextInput;
    private Button btnConvert;
    private TextView textViewResult;

    // Unit conversion factors relative to base unit for each category
    // Length base: Meter
    private static final double[] LENGTH_FACTORS = {
            1.0,          // Meter (m)
            1000.0,       // Kilometer (km)
            0.01,         // Centimeter (cm)
            0.001,        // Millimeter (mm)
            1609.344,     // Mile (mi)
            0.9144,       // Yard (yd)
            0.3048,       // Foot (ft)
            0.0254        // Inch (in)
    };

    // Weight base: Kilogram
    private static final double[] WEIGHT_FACTORS = {
            1.0,           // Kilogram (kg)
            0.001,         // Gram (g)
            0.000001,      // Milligram (mg)
            0.45359237,    // Pound (lb)
            0.028349523125 // Ounce (oz)
    };

    // Volume base: Liter
    private static final double[] VOLUME_FACTORS = {
            1.0,            // Liter (L)
            0.001,          // Milliliter (mL)
            3.785411784,    // Gallon (US gal)
            0.2365882365    // Cup (US cup)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerSourceUnit = findViewById(R.id.spinner_source_unit);
        spinnerTargetUnit = findViewById(R.id.spinner_target_unit);
        editTextInput = findViewById(R.id.edit_text_input);
        btnConvert = findViewById(R.id.btn_convert);
        textViewResult = findViewById(R.id.text_view_result);

        // Setup Category Spinner
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories,
                android.R.layout.simple_spinner_item
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // Listener for Category Selection to reset and populate unit dropdowns accordingly
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUnitSpinners(position);
                textViewResult.setText(R.string.result_placeholder);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Listener for Convert Button
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    /**
     * Updates the source and target unit spinners based on the selected category position.
     */
    private void updateUnitSpinners(int categoryPosition) {
        int arrayResId;
        switch (categoryPosition) {
            case 0: // Length
                arrayResId = R.array.length_units;
                break;
            case 1: // Weight
                arrayResId = R.array.weight_units;
                break;
            case 2: // Temperature
                arrayResId = R.array.temperature_units;
                break;
            case 3: // Volume
                arrayResId = R.array.volume_units;
                break;
            default:
                arrayResId = R.array.length_units;
                break;
        }

        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(
                this,
                arrayResId,
                android.R.layout.simple_spinner_item
        );
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSourceUnit.setAdapter(unitAdapter);
        spinnerTargetUnit.setAdapter(unitAdapter);

        // Default target unit to second item if available for better initial UX
        if (unitAdapter.getCount() > 1) {
            spinnerTargetUnit.setSelection(1);
        }
    }

    /**
     * Validates input, performs conversion, and updates the result view.
     */
    private void performConversion() {
        String inputText = editTextInput.getText() != null ? editTextInput.getText().toString().trim() : "";

        // Input validation
        if (inputText.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_empty_input), Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue;
        try {
            inputValue = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.toast_invalid_number), Toast.LENGTH_SHORT).show();
            return;
        }

        int categoryPosition = spinnerCategory.getSelectedItemPosition();
        int sourceUnitPos = spinnerSourceUnit.getSelectedItemPosition();
        int targetUnitPos = spinnerTargetUnit.getSelectedItemPosition();

        if (sourceUnitPos == Spinner.INVALID_POSITION || targetUnitPos == Spinner.INVALID_POSITION) {
            Toast.makeText(this, "Please select valid units", Toast.LENGTH_SHORT).show();
            return;
        }

        double convertedValue;

        switch (categoryPosition) {
            case 0: // Length
                convertedValue = convertUsingFactors(inputValue, sourceUnitPos, targetUnitPos, LENGTH_FACTORS);
                break;
            case 1: // Weight
                convertedValue = convertUsingFactors(inputValue, sourceUnitPos, targetUnitPos, WEIGHT_FACTORS);
                break;
            case 2: // Temperature
                convertedValue = convertTemperature(inputValue, sourceUnitPos, targetUnitPos);
                break;
            case 3: // Volume
                convertedValue = convertUsingFactors(inputValue, sourceUnitPos, targetUnitPos, VOLUME_FACTORS);
                break;
            default:
                convertedValue = 0.0;
                break;
        }

        String targetUnitLabel = spinnerTargetUnit.getSelectedItem() != null ?
                spinnerTargetUnit.getSelectedItem().toString() : "";

        DecimalFormat df = new DecimalFormat("#,##0.######");
        String formattedResult = df.format(convertedValue) + " " + targetUnitLabel;

        textViewResult.setText(formattedResult);
    }

    /**
     * Standard conversion using base unit factors.
     */
    private double convertUsingFactors(double value, int sourcePos, int targetPos, double[] factors) {
        if (sourcePos < 0 || sourcePos >= factors.length || targetPos < 0 || targetPos >= factors.length) {
            return 0.0;
        }
        double valueInBaseUnit = value * factors[sourcePos];
        return valueInBaseUnit / factors[targetPos];
    }

    /**
     * Temperature conversion logic.
     * Positions: 0 -> Celsius (°C), 1 -> Fahrenheit (°F), 2 -> Kelvin (K)
     */
    private double convertTemperature(double value, int sourcePos, int targetPos) {
        if (sourcePos == targetPos) {
            return value;
        }

        // Convert source to Celsius first
        double celsius;
        switch (sourcePos) {
            case 0: // Celsius
                celsius = value;
                break;
            case 1: // Fahrenheit
                celsius = (value - 32.0) * 5.0 / 9.0;
                break;
            case 2: // Kelvin
                celsius = value - 273.15;
                break;
            default:
                celsius = value;
                break;
        }

        // Convert Celsius to target unit
        switch (targetPos) {
            case 0: // Celsius
                return celsius;
            case 1: // Fahrenheit
                return (celsius * 9.0 / 5.0) + 32.0;
            case 2: // Kelvin
                return celsius + 273.15;
            default:
                return celsius;
        }
    }
}
