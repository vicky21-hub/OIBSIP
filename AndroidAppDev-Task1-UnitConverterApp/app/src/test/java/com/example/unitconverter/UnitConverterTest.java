package com.example.unitconverter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnitConverterTest {

    private static final double DELTA = 0.0001;

    // Length conversion tests
    // Length factors: Meter(0):1.0, Kilometer(1):1000.0, Centimeter(2):0.01, Millimeter(3):0.001, Mile(4):1609.344, Yard(5):0.9144, Foot(6):0.3048, Inch(7):0.0254
    @Test
    public void testLengthConversion_MeterToCentimeter() {
        double mToCm = convertFactors(1.0, 0, 2, new double[]{1.0, 1000.0, 0.01, 0.001, 1609.344, 0.9144, 0.3048, 0.0254});
        assertEquals(100.0, mToCm, DELTA);
    }

    @Test
    public void testLengthConversion_InchToCentimeter() {
        double inToCm = convertFactors(1.0, 7, 2, new double[]{1.0, 1000.0, 0.01, 0.001, 1609.344, 0.9144, 0.3048, 0.0254});
        assertEquals(2.54, inToCm, DELTA);
    }

    // Weight conversion tests
    // Weight factors: Kg(0):1.0, Gram(1):0.001, Mg(2):0.000001, Pound(3):0.45359237, Ounce(4):0.028349523125
    @Test
    public void testWeightConversion_KgToGram() {
        double kgToG = convertFactors(1.5, 0, 1, new double[]{1.0, 0.001, 0.000001, 0.45359237, 0.028349523125});
        assertEquals(1500.0, kgToG, DELTA);
    }

    // Temperature conversion tests
    @Test
    public void testTemperature_CelsiusToFahrenheit() {
        double result = convertTemperature(0.0, 0, 1);
        assertEquals(32.0, result, DELTA);

        double result100 = convertTemperature(100.0, 0, 1);
        assertEquals(212.0, result100, DELTA);
    }

    @Test
    public void testTemperature_FahrenheitToCelsius() {
        double result = convertTemperature(32.0, 1, 0);
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testTemperature_CelsiusToKelvin() {
        double result = convertTemperature(25.0, 0, 2);
        assertEquals(298.15, result, DELTA);
    }

    private double convertFactors(double value, int sourcePos, int targetPos, double[] factors) {
        double valueInBaseUnit = value * factors[sourcePos];
        return valueInBaseUnit / factors[targetPos];
    }

    private double convertTemperature(double value, int sourcePos, int targetPos) {
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
}
