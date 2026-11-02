package com.example.unitconverter;

public class TestRunner {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    // Factors
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

    private static final double[] WEIGHT_FACTORS = {
            1.0,           // Kilogram (kg)
            0.001,         // Gram (g)
            0.000001,      // Milligram (mg)
            0.45359237,    // Pound (lb)
            0.028349523125 // Ounce (oz)
    };

    private static final double[] VOLUME_FACTORS = {
            1.0,            // Liter (L)
            0.001,          // Milliliter (mL)
            3.785411784,    // Gallon (US gal)
            0.2365882365    // Cup (US cup)
    };

    public static void main(String[] args) {
        System.out.println("--- STARTING FEATURE & CONVERSION TESTS ---");

        // 1. Length Tests
        testEquals("1 Meter to Centimeters", convertUsingFactors(1.0, 0, 2, LENGTH_FACTORS), 100.0);
        testEquals("1 Kilometer to Meters", convertUsingFactors(1.0, 1, 0, LENGTH_FACTORS), 1000.0);
        testEquals("1 Inch to Centimeters", convertUsingFactors(1.0, 7, 2, LENGTH_FACTORS), 2.54);
        testEquals("1 Foot to Inches", convertUsingFactors(1.0, 6, 7, LENGTH_FACTORS), 12.0);
        testEquals("1 Yard to Feet", convertUsingFactors(1.0, 5, 6, LENGTH_FACTORS), 3.0);
        testEquals("1 Mile to Kilometers", convertUsingFactors(1.0, 4, 1, LENGTH_FACTORS), 1.609344);

        // 2. Weight Tests
        testEquals("1 Kilogram to Grams", convertUsingFactors(1.0, 0, 1, WEIGHT_FACTORS), 1000.0);
        testEquals("1 Gram to Milligrams", convertUsingFactors(1.0, 1, 2, WEIGHT_FACTORS), 1000.0);
        testEquals("1 Pound to Ounces", convertUsingFactors(1.0, 3, 4, WEIGHT_FACTORS), 16.0);
        testEquals("1 Kilogram to Pounds", convertUsingFactors(1.0, 0, 3, WEIGHT_FACTORS), 2.20462262);

        // 3. Temperature Tests
        testEquals("0 Celsius to Fahrenheit", convertTemperature(0.0, 0, 1), 32.0);
        testEquals("100 Celsius to Fahrenheit", convertTemperature(100.0, 0, 1), 212.0);
        testEquals("32 Fahrenheit to Celsius", convertTemperature(32.0, 1, 0), 0.0);
        testEquals("212 Fahrenheit to Celsius", convertTemperature(212.0, 1, 0), 100.0);
        testEquals("0 Celsius to Kelvin", convertTemperature(0.0, 0, 2), 273.15);
        testEquals("-40 Celsius to Fahrenheit", convertTemperature(-40.0, 0, 1), -40.0);
        testEquals("37 Celsius to Fahrenheit", convertTemperature(37.0, 0, 1), 98.6);

        // 4. Volume Tests
        testEquals("1 Liter to Milliliters", convertUsingFactors(1.0, 0, 1, VOLUME_FACTORS), 1000.0);
        testEquals("1 Gallon to Liters", convertUsingFactors(1.0, 2, 0, VOLUME_FACTORS), 3.785411784);

        // 5. Input Validation Logic Tests
        testValidation("", false, "Empty string input validation");
        testValidation("  ", false, "Whitespace input validation");
        testValidation("abc", false, "Non-numeric string validation");
        testValidation("12.3.4", false, "Multiple decimals validation");
        testValidation("123.45", true, "Valid decimal number validation");
        testValidation("-15.5", true, "Valid negative number validation");

        System.out.println("\n-------------------------------------------");
        System.out.println("TEST RESULTS: " + testsPassed + " Passed, " + testsFailed + " Failed.");
        System.out.println("-------------------------------------------");

        if (testsFailed > 0) {
            System.exit(1);
        }
    }

    private static double convertUsingFactors(double value, int sourcePos, int targetPos, double[] factors) {
        if (sourcePos < 0 || sourcePos >= factors.length || targetPos < 0 || targetPos >= factors.length) {
            return 0.0;
        }
        double valueInBaseUnit = value * factors[sourcePos];
        return valueInBaseUnit / factors[targetPos];
    }

    private static double convertTemperature(double value, int sourcePos, int targetPos) {
        if (sourcePos == targetPos) return value;
        double celsius;
        switch (sourcePos) {
            case 0: celsius = value; break;
            case 1: celsius = (value - 32.0) * 5.0 / 9.0; break;
            case 2: celsius = value - 273.15; break;
            default: celsius = value; break;
        }
        switch (targetPos) {
            case 0: return celsius;
            case 1: return (celsius * 9.0 / 5.0) + 32.0;
            case 2: return celsius + 273.15;
            default: return celsius;
        }
    }

    private static void testValidation(String input, boolean expectedValid, String testName) {
        boolean isValid = true;
        if (input == null || input.trim().isEmpty()) {
            isValid = false;
        } else {
            try {
                Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                isValid = false;
            }
        }

        if (isValid == expectedValid) {
            System.out.println("[PASS] " + testName);
            testsPassed++;
        } else {
            System.err.println("[FAIL] " + testName + " - Expected " + expectedValid + " but got " + isValid);
            testsFailed++;
        }
    }

    private static void testEquals(String testName, double actual, double expected) {
        double delta = 0.0001;
        if (Math.abs(actual - expected) <= delta) {
            System.out.println("[PASS] " + testName + ": " + actual);
            testsPassed++;
        } else {
            System.err.println("[FAIL] " + testName + ": Expected " + expected + " but got " + actual);
            testsFailed++;
        }
    }
}
