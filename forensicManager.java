package com.example.Forensic.Calculator;
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

            if (choice == 1) {
                System.out.print("Enter victim Id: ");
                String id = scanner.nextLine();
                System.out.print("Enter body temperature: ");
                double bTemp = scanner.nextDouble();
                System.out.print("Enter Environmental Ambient Temperature (°C): ");
                double aTemp = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter Lividity Discoloration Color: ");
                String color = scanner.nextLine();
                System.out.print("Is Lividity Fixed/Permanent? (true/false): ");
                boolean fixed = scanner.nextBoolean();

                System.out.println("Enter Rigor Stiffness Levels (0 = Relaxed, 3 = Completely Rigid):");
                int[] rigor = new int[3];
                System.out.print("-> Face / Jaw Stiffness Level: ");
                rigor[0] = scanner.nextInt();
                System.out.print("-> Arms / Upper Body Stiffness Level: ");
                rigor[1] = scanner.nextInt();
                System.out.print("-> Legs / Lower Body Stiffness Level: ");
                rigor[2] = scanner.nextInt();

                cases.add(new deceasedBody(id, bTemp, aTemp, color, fixed, rigor));
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
                    for (deceasedBody b : cases) {
                        System.out.println("Target Profile ID: " + b.getVictimId());
                        System.out.println("--------------------------------------------------");
                        
                        double algorHours = b.calculateAlgorMortisFor27(b.getBodyTemp());
                        System.out.printf("Algor Mortis Calculus : %.2f hours post-mortem\n", algorHours);
                        
                        System.out.println("Livor Mortis Indicator: " + b.calculateLivorMortis(b.getIsLivorFixed()));
                        System.out.println("Rigor Mortis Indicator: " + b.calculateRigorMortis(b.getRigorStage()));
                        
                        System.out.println("FINAL CONVERGENCE CONCLUSION:");
                        if (algorHours >= 2 && algorHours <= 6) {
                            System.out.println(">> High probability convergence points to an EARLY death window: 2 to 6 hours ago.");
                        } else if (algorHours > 12) {
                            System.out.println(">> High probability convergence points to an EXTENDED timeline: 12+ hours ago.");
                        } else {
                            System.out.println(">> Evidence suggests an INTERMEDIATE post-mortem interval window.");
                        }
                        System.out.println("Cross-reference scene temperature factors to establish localized modifications.");
                    }
                }

            } else if (choice == 4) {
                System.out.println("Shutting down forensic system. Have a great day!");
                break;

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
                if (line.isEmpty() || line.startsWith("//")) continue;
                
                String[] tokens = line.split(",");
                String id = tokens[0];
                double bTemp = Double.parseDouble(tokens[1]);
                double aTemp = Double.parseDouble(tokens[2]);
                String color = tokens[3];
                boolean fixed = Boolean.parseBoolean(tokens[4]);
                
                int[] rigor = new int[3];
                rigor[0] = Integer.parseInt(tokens[5]);
                rigor[1] = Integer.parseInt(tokens[6]);
                rigor[2] = Integer.parseInt(tokens[7]);
                
                cases.add(new deceasedBody(id, bTemp, aTemp, color, fixed, rigor));
                System.out.println("-> Successfully pulled object metrics into memory for: " + id);
            }
            fileScanner.close();
            System.out.println("[SUCCESS] External data files parsed cleanly. Total profiles loaded: " + cases.size());
            
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] File processing halted. Unable to find specified dataset file path: " + filename);
        } catch (Exception e) {
            System.out.println("[ERROR] Formatting structure exception encountered inside the external data file.");
        }
    }
}
