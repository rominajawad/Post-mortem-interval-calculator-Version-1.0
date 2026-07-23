// version 2.0 at shape(Columbia University)

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class forensicManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<deceasedBody> cases = new ArrayList<>();
/*
This runs the while loop for an interactive menu
*/
        while (true) {
            System.out.println("==================================================");
            System.out.println("FORENSIC SCIENCE CRIMINAL INVESTIGATION");
            System.out.println("MAIN MENU:");
            System.out.println("1.Enter crime scene case data:");
            System.out.println("2.View registered data summary");
            System.out.println("3.Run post mortem interval analysis");
            System.out.println("4.Exit system");
            System.out.println("5.Load Cases From External File");
            System.out.println("Select an option(1-5)");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            double a1,a2,b1,b2; // declare them at top so that they are created en if they hit a no!
            if (choice == 1) {
                System.out.println("Enter victim Id: ");
                String id = scanner.nextLine();

                System.out.println("Enter the body temperature. (Not sure?) ");
                boolean isRange= scanner.nextLine().equalsIgnoreCase("yes");
                    
                if(isRange){
                System.out.println("Enter Min/Max Body temp: ");
                    b1=scanner.nextDouble();
                    b2=scanner.nextDouble();
                } else{
                System.out.println("Enter Body Temperature: ");
                b1=scanner.nextDouble();
                b2=b1; // b2 becomes whatever b1 is and that is how it can contain the same value
                }

                scanner.nextLine();
                
                System.out.println("Enter the ambient temperature. (Not sure?) ");
                boolean ambientRange = scanner.nextLine().equalsIgnoreCase("yes");
            
                if(ambientRange){
                System.out.println("Enter Min/Max Ambient Temp: ");
                    a1=scanner.nextDouble();
                    a2=scanner.nextDouble();
                } else{
                System.out.println("Enter Ambient Temperature: ");
                a1 = scanner.nextDouble();
                a2=a1; // treat it as single value
                }
               
               scanner.nextLine();
               
                System.out.println("Enter Lividity Discoloration Color: ");
                String color = scanner.nextLine();
                System.out.println("Is Lividity Fixed/Permanent? (true/false): ");
                boolean fixed = scanner.nextBoolean();

                System.out.println("Enter Rigor Stiffness Levels (0 = Relaxed, 3 = Completely Rigid):");
                int[] rigor = new int[3];
                System.out.print("-> Face / Jaw Stiffness Level: ");
                rigor[0] = scanner.nextInt();
                System.out.print("-> Arms / Upper Body Stiffness Level: ");
                rigor[1] = scanner.nextInt();
                System.out.print("-> Legs / Lower Body Stiffness Level: ");
                rigor[2] = scanner.nextInt();
                scanner.nextLine();

If ((isRange && ambientRange) or (!isRange&& !ambientRange)){
Cases.add(new deceasedBody(id,b1,b2,a1,a2,color, fixed, rigor))
} else {
Cases.add(new deceasedBody(id,b1,b2,a1,a2,color, fixed, rigot))
}
                System.out.println("[SUCCESS] Case profile initialized and saved into memory.");

            } else if (choice == 2) {
                if (cases.isEmpty()) {
                    System.out.println("[ERROR] No case data found. Please select Option 1 or Option 5 first.");
                } else {
                    System.out.println("--- RAW CASE STRING REPRESENTATIONS ---");
                    for (deceasedBody b : cases) {
                        System.out.println("-----------------------------------------");
                        System.out.println(b.toString());
                        System.out.println("-----------------------------------------");
                    }
                }

            } else if (choice == 3) {
                if (cases.isEmpty()) {
                    System.out.println("[ERROR] No case data found. Please select Option 1 or Option 5 first.");
                } else {
                    System.out.println("==================================================");
                    System.out.println("COMPREHENSIVE PMO ANALYSIS REPORT");
                    for (deceasedBody b : cases) { // going through the cases and placing the object in a variable called 'b'
                        System.out.println("Target Profile ID: " + b.getVictimId());
                        System.out.println("--------------------------------------------------");
                        
                        System.out.println("Algor Mortis Calculation: " + b.getAlgorMortisReport());// have this router method for variety
                        
                        System.out.println("Livor Mortis Indicator: " + b.getLivorMortisReport());// for variety (the router method)

                        System.out.println(" Rigor Mortis indicator: " + b.getRigorMortisReport()); // same thing
                        
                        
                        System.out.print("FINAL CONVERGENCE CONCLUSION: ");
                        double finalHours= b.getFinalEstimateHours();
                        System.out.println(">> Estimated time of death " + String.format("%.2f", finalHours) + " hours ago");
                        System.out.println("Cross-reference scene temperature factors to establish localized modifications.");
                    }
                }

            } else if (choice == 4) {
                System.out.println("Forensic calculator is shutting down. Have a great day!");
                break; // if 4 then immediately exit

            } else if (choice == 5) {
                loadCasesFromFile("cases.txt", cases);
            } else {
                System.out.println("[INVALID Choice] Please enter a value between 1 and 5.");
            }
        }
        scanner.close();
    }

    public static void loadCasesFromFile(String filename, ArrayList<deceasedBody> cases) {
        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);
            System.out.println("[SYSTEM] Loading data elements from: " + filename);
            
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("//")) 
                    continue;
                
                String[] tokens = line.split(",");

                for(int i = 0; i < tokens.length; i++){
                tokens[i] = tokens[i].trim(); 
                }
                boolean isRange= Boolean.parseBoolean(tokens[0]); // sees if its boolean
                String id = tokens[1];
                int[] rigor= new int[3];

                if(isRange){
                double minB= Double.parseDouble(tokens[2]);
                double maxB= Double.parseDouble(tokens[3]);
                double minA= Double.parseDouble(tokens[4]);
                double maxA= Double.parseDouble(tokens[5]);
                String color= tokens[6];
                boolean fixed= Boolean.parseBoolean(tokens[7]);
                rigor[0]=Integer.parseInt(tokens[8]);
                rigor[1]=Integer.parseInt(tokens[9]);
                rigor[2]=Integer.parseInt(tokens[10]);

                cases.add(new deceasedBody(id, minB, maxB, minA, maxA, color, fixed, rigor));
                } else{
                 double bTemp= Double.parseDouble(tokens[2]);
                 double aTemp= Double.parseDouble(tokens[3]);
                 String color= tokens[4];
                boolean fixed= Boolean.parseBoolean(tokens[5]);
                rigor[0]= Integer.parseInt(tokens[6]);
                rigor[1]= Integer.parseInt(tokens[7]);
                rigor[2]= Integer.parseInt(tokens[8]);

                cases.add(new deceasedBody(id, bTemp, aTemp, color, fixed, rigor));
                }
                }
            fileScanner.close();
            System.out.println("[SUCCESS] External data files parsed cleanly. Total profiles loaded: " + cases.size());
            
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] File processing halted. Unable to find specified dataset file path: " + filename);
        } catch (Exception e) {
            System.out.println("[ERROR] Formatting structure exception encountered inside the external data file.");
        }
    }

