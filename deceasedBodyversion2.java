/*
*This class shows the characteristics of the object. Each dead body will be the object
*
* @param victimId, bodyTemp, ambientTemp, livorColor, isLivorFixed
* return the object (makes it)
*/
// version 2.0 of shape (columbia University)

package com.example.Forensic.Calculator;

public class deceasedBody {
private String victimId;
private double bodyTemp;
private double minTemp // for range
private double maxTemp // for range
private double ambientTemp;
private String livorColor;
private boolean isLivorFixed;
private int[] rigorStage;

public deceasedBody(String victimId, double maxTemp, double minTemp, double bodyTemp, double ambientTemp, String livorColor, boolean isLivorFixed, int[] rigorStage) {
this.victimId = victimId;
this.bodyTemp = bodyTemp;
this.minTemp= minTemp // if no range, pass the same value
this.maxTemp= maxTemp // if np rnage, pass the same value
this.ambientTemp = ambientTemp;
this.livorColor = livorColor;
this.isLivorFixed = isLivorFixed;
this.rigorStage = rigorStage;
}

  // This is for a single individual value
    public double calculateAlgorMortisFor27(double targetTemp) {
        // Standard Glaister formula estimation framework
        return (37.0 - bodyTemp) / 1.5; 
    }

// --- Handles Range for algor mortis ---

// Algor Mortis: Returns a range string
public String calculateAlgorMortisRange(double minTemp, double maxTemp) {
double lowTime = (37.0 - maxTemp) / 1.5;
double highTime = (37.0 - minTemp) / 1.5;
if (lowTime == highTime) {
return lowTime + " hours";
}
return lowTime + " to " + highTime + " hours";
}

// this is for single values
  public String calculateLivorMortis(boolean fixed) {
        if (fixed) {
            return "At least 6 to 8 hours ago (lividity is permanent)";
        } else {
            return "less than 6 hours ago(lividity is temporary)";
        }
    }

  
// Livor Mortis: Returns a range string (Handles 'fixed' state as priority)
public String calculateLivorMortisRange(boolean isFixed, int minH, int maxH) {
if (isFixed) {
return "At least 6 to 8 hours (Permanent)";
}
if (minH == maxH) {
return minH + " hours (Temporary)";
}
return minH + " to " + maxH + " hours (Temporary) ";
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

@Override // leave it there since it wont crash
public String toString() { // string representation of an object and thats why we are overriding
return "Victim Id: " + victimId +
"\n| body temperature: " + bodyTemp +
"\n| ambient temperature: " + ambientTemp +
"\n| Lividity: " + livorColor + " (fixed: " + isLivorFixed + ")" +
"\n| rigor status: [" + rigorStage[0] + ", " + rigorStage[1] + ", " + rigorStage[2] + "]";
}
}

