/**
*This class shows the characteristics of the object. Each dead body will be the object
*
* @param victimId, bodyTemp, ambientTemp, livorColor, isLivorFixed
* return the object (makes it)
*/

public class deceasedBody {
    private String victimId;
    private double bodyTemp;
    private double ambientTemp;
    private String livorColor;
    private boolean isLivorFixed;
    private int[] rigorStage; // [Face/Jaw, Arms/Upper, Legs/Lower]

    // Constructor
    public deceasedBody(String victimId, double bodyTemp, double ambientTemp, String livorColor, boolean isLivorFixed, int[] rigorStage) {
        this.victimId = victimId;
        this.bodyTemp = bodyTemp;
        this.ambientTemp = ambientTemp;
        this.livorColor = livorColor;
        this.isLivorFixed = isLivorFixed;
        this.rigorStage = rigorStage;
    }

    // Getters and Setters
    public String getVictimId() { return victimId; }
    public void setVictimId(String victimId) { this.victimId = victimId; }

    public double getBodyTemp() { return bodyTemp; }
    public void setBodyTemp(double bodyTemp) { this.bodyTemp = bodyTemp; }

    public double getAmbientTemp() { return ambientTemp; }
    public void setAmbientTemp(double ambientTemp) { this.ambientTemp = ambientTemp; }

    public String getLivorColor() { return livorColor; }
    public void setLivorColor(String livorColor) { this.livorColor = livorColor; }

    public boolean getIsLivorFixed() { return isLivorFixed; }
    public void setIsLivorFixed(boolean isLivorFixed) { this.isLivorFixed = isLivorFixed; }

    public int[] getRigorStage() { return rigorStage; }
    public void setRigorStage(int[] rigorStage) { this.rigorStage = rigorStage; }

    // Analysis Methods derived from your bytecode strings
    public double calculateAlgorMortisFor27(double targetTemp) {
        // Standard Glaister formula estimation framework
        return (37.0 - bodyTemp) / 1.5; 
    }
 /*
This method is for calculating livor mortis
*/
    public String calculateLivorMortis(boolean fixed) {
        if (fixed) {
            return "At least 6 to 8 hours ago (lividity is permanent)";
        } else {
            return "less than 6 hours ago(lividity is temporary)";
        }
    }

    /*
    This calculates the rigor mortis by the help of the array
    */
    public String calculateRigorMortis(int[] stages) {
        // Peak rigor check (averaging around completely rigid level 3)
        if (stages[0] == 3 && stages[1] == 3 && stages[2] == 3) {
            return "Approximately 12 hours ago, peak rigor is exhibited";
        }
        if (stages[0] > 0 && stages[2] == 0) {
            return "rigor is beginning, it is from between 2 to 6 hours";
        }
        if (stages[0] == 0 && stages[2] > 0) {
            return "rigor is beginning to slow down, it is between 15 to 36 hours";
        }
        if (stages[0] == 0 && stages[1] == 0 && stages[2] == 0) {
            return "it is either very recent(0 to 2 hrs) OR it is completely resolved(36 to 48 hours)";
        }
        return "estimation stage varies between 6 to 15 hours";
    }

    @Override
    public String toString() {
        return "Victim Id: " + victimId + 
               "\n| body temperature: " + bodyTemp + 
               "\n| ambient temperature: " + ambientTemp + 
               "\n| Lividity: " + livorColor + " (fixed: " + isLivorFixed + ")" + 
               "\n| rigor status: [" + rigorStage[0] + ", " + rigorStage[1] + ", " + rigorStage[2] + "]";
    }
}
