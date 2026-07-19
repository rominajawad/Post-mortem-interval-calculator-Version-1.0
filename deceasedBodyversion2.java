/*
*This class shows the characteristics of the object. Each dead body will be the object
*
* @param victimId, bodyTemp, ambientTemp, livorColor, isLivorFixed
* return the object (makes it)
*/
// version 2.0 at shape (columbia University)

package com.example.Forensic.Calculator;

public class deceasedBody {
private String victimId;
private double bodyTemp;
private double bodyMinTemp; // for range
private double bodyMaxTemp; // for range
private double ambientTemp;
private double minAmbientTemp; // for range
private double maxAmbientTemp; // for range
private String livorColor;
private boolean isLivorFixed;
private int[] rigorStage;
private boolean isRange; // to see if its a range or single value, making it easier for user to enter either a range or a single value

// Constructor for single values
public deceasedBody(String victimId, double bodyTemp, double ambientTemp, String livorColor, boolean isLivorFixed, int[] rigorStage) {
this.victimId = victimId;
this.bodyTemp = bodyTemp;
this.ambientTemp = ambientTemp;
this.livorColor = livorColor;
this.isLivorFixed = isLivorFixed;
this.rigorStage = rigorStage;
this.isRange= false;
}

// constructor for range of value
public deceasedBody(String victimId, double bodyMinTemp, double bodyMaxTemp, double minAmbientTemp, double maxAmbientTemp, String livorColor, boolean isLivorFixed, int[] rigorStage)
  {
this.victimId= victimId;
this.bodyMinTemp= bodyMinTemp;
this.bodyMaxTemp= bodyMaxTemp;
this.minAmbientTemp= minAmbientTemp;
this.maxAmbientTemp= maxAmbientTemp;
this.livorColor= livorColor;
this.isLivorFixed= isLivorFixed;
this.rigorStage= rigorStage;
this.isRange= true;
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
public double getBodyMinTemp()  { return bodyMinTemp; }
public double getBodyMaxTemp()  { return bodyMaxTemp; }
public double getAmbientTemp() { return ambientTemp; }
public double getMinAmbientTemp() { return minAmbientTemp; }
public double getMaxAmbientTemp() {return maxAmbientTemp; }
public String getLivorColor() { return livorColor; }
public boolean getIsLivorFixed() { return isLivorFixed; }
public int[] getRigorStage() { return rigorStage; }


// Setters
public void setVictimId(String newVictimId){victimId= newVictimId;}
public void setBodyTemp(double newBodyTemp){bodyTemp= newBodyTemp; this.isRange= false; } // the value is single so its not a range
public void setBodyMinTemp(double newBodyMinTemp){bodyMinTemp= newBodyMinTemp; this.isRange= true;} // if its a range, set the flag to true
public void setBodyMaxTemp(double newBodyMaxTemp){bodyMaxTemp= newBodyMaxTemp; this.isRange=true;}// if its a range, set the flag to true
public void setAmbientTemp(double newAmbientTemp){ambientTemp= newAmbientTemp; this.isRange= false;} // the value is single so its not a range
public void setMinAmbientTemp(double newMinAmbientTemp){minAmbientTemp= newMinAmbientTemp; this.isRange=true;} // if its a range, set it to true
public void setMaxAmbientTemp(double newMaxAmbientTemp){maxAmbientTemp= newMaxAmbientTemp; this.isRange=true;} // since its a range, set it to true
public void setLivorColor(String newLivorColor){livorColor= newLivorColor;}
public void setIsLivorFixed(boolean newIsLivorFixed){isLivorFixed= newIsLivorFixed;}
public void setRigorStage(int newRigorStage){rigorStage= newRigorStage;}
  
@Override // leave it there since it wont crash
public String toString() { // string representation of an object and thats why we are overriding
String tempDisplay;
String ambientDisplay;
if(isRange){
tempDisplay= bodyMinTemp + " to " + bodyMaxTemp;
ambientDisplay= minAmbientTemp + " to " + maxAmbientTemp;
} else {
tempDisplay= String.valueOf(bodyTemp);
ambientDisplay= String.valueOf(ambientTemp);
}

return "Victim Id : " + victimId+
       "\n| Body Temperature: " + tempDisplay +
       "\n| Ambient Temperature: " + ambientDisplay +
       "\n| Lividity: " + livorColor + "(Fixed: " + isLivorFixed + ")" +
       "\n| Rigor Status: [" + rigorStage[0] + ", " + rigorStage[1] + ", " + rigorStage[2] + "]";

  
}
}

// everytime we update the object, we need to switch the state if there is a boolean switch
