/*
*This class shows the characteristics of the object. Each dead body will be the object
*
* @param victimId, bodyTemp, ambientTemp, livorColor, isLivorFixed
* return the object (makes it)
*/


package com.example.Forensic.Calculator;

public class deceasedBody {
private String victimId;
private double bodyTemp;
private double ambientTemp;
private String livorColor;
private boolean isLivorFixed;
private int[] rigorStage;

public deceasedBody(String victimId, double bodyTemp, double ambientTemp, String livorColor, boolean isLivorFixed, int[] rigorStage) {
this.victimId = victimId;
this.bodyTemp = bodyTemp;
this.ambientTemp = ambientTemp;
this.livorColor = livorColor;
this.isLivorFixed = isLivorFixed;
this.rigorStage = rigorStage;
}

// --- UNIVERSAL CALCULATORS (Handles Single Values & Ranges) ---

// Algor Mortis: Returns a range string
public String calculateAlgorMortis(double minTemp, double maxTemp) {
double lowTime = (37.0 - maxTemp) / 1.5;
double highTime = (37.0 - minTemp) / 1.5;
if (lowTime == highTime) {
return String.format("%.2f hours", lowTime);
}
return String.format("%.2f to %.2f hours", lowTime, highTime);
}

// Livor Mortis: Returns a range string (Handles 'fixed' state as priority)
public String calculateLivorMortis(boolean isFixed, int minHours, int maxHours) {
if (isFixed) {
return "At least 6 to 8 hours (Permanent)";
}
if (minHours == maxHours) {
return String.format("%d hours (Temporary)", minHours);
}
return String.format("%d to %d hours (Temporary)", minHours, maxHours);
}

// Rigor Mortis Estimation
public String calculateRigorMortis(int minStage, int maxStage) {
if (minStage == 3 && maxStage == 3) {
return "Approximately 12 hours ago (Peak rigor is exhibited)";
}
if (minStage == 0 && maxStage == 0) {
return "Either very recent (0-2 hours) OR completely resolved (36-48 hours)";
}
if (minStage == 1 && maxStage == 1) return "Rigor is beginning (2 to 6 hours ago)";
if (minStage == 2 && maxStage == 3) return "Rigor is advancing (6 to 12 hours ago)";

return "Rigor timeline estimation varies within the standard 6 to 15 hour window";
}

// Getters
public String getVictimId() { return victimId; }
public double getBodyTemp() { return bodyTemp; }
public double getAmbientTemp() { return ambientTemp; }
public String getLivorColor() { return livorColor; }
public boolean getIsLivorFixed() { return isLivorFixed; }
public int[] getRigorStage() { return rigorStage; }

@Override
public String toString() {
return "Victim Id: " + victimId +
"\n| body temperature: " + bodyTemp +
"\n| ambient temperature: " + ambientTemp +
"\n| Lividity: " + livorColor + " (fixed: " + isLivorFixed + ")" +
"\n| rigor status: [" + rigorStage[0] + ", " + rigorStage[1] + ", " + rigorStage[2] + "]";
}
}

