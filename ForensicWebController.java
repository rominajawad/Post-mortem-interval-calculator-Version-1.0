package com.example.Forensic.Calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ForensicWebController {

 // 1. Serves the web interface directly when she opens the page
 @GetMapping("/")
 public String showHomePage() {
 return "index";
 }

 // 2. Handles the button click completely in the background
 @PostMapping("/calculate")
 public String processCalculations(WebRequest request, Model model) {
 try {
 // Safely extracts the visual web text inputs
 String victimId = request.getParameter("victimId");
 double bodyTemp = Double.parseDouble(request.getParameter("bodyTemp"));
 double ambientTemp = Double.parseDouble(request.getParameter("ambientTemp"));
 String livorColor = request.getParameter("livorColor");
 boolean isLivorFixed = Boolean.parseBoolean(request.getParameter("isLivorFixed"));

 // Handles the dropdown values seamlessly
 int jaw = 0, arms = 0, legs = 0;
 if (request.getParameter("rigorJaw") != null) {
 jaw = Integer.parseInt(request.getParameter("rigorJaw"));
 arms = Integer.parseInt(request.getParameter("rigorArms"));
 legs = Integer.parseInt(request.getParameter("rigorLegs"));
 } else if (request.getParameter("stages") != null) {
 // Fallback check if the HTML is still using the old "stages" attribute name
 String[] stages = request.getParameterValues("stages");
 if (stages != null && stages.length >= 3) {
 jaw = Integer.parseInt(stages[0]);
 arms = Integer.parseInt(stages[1]);
 legs = Integer.parseInt(stages[2]);
 }
 }

 int[] rigorArray = { jaw, arms, legs };

 // Instantiates your deceasedBody model object using the form values
 deceasedBody body = new deceasedBody(victimId, bodyTemp, ambientTemp, livorColor, isLivorFixed, rigorArray);

 // Directly executes your underlying backend formula logic 
 double algorHours = body.calculateAlgorMortisFor27(body.getBodyTemp());
 String livorAssessment = body.calculateLivorMortis(body.getIsLivorFixed());
 String rigorAssessment = body.calculateRigorMortis(rigorArray);

 // Builds the clear, formatted report block right on the webpage
 String compiledReport = "=====================================\n" +
 "COMPREHENSIVE PMO ANALYSIS REPORT\n" +
 "Target Profile ID: " + body.getVictimId() + "\n" +
 "-------------------------------------\n" +
 "Algor Mortis Estimated Time: " + String.format("%.2f", algorHours) + " Hours\n" +
 "Livor Mortis Coloration: " + body.getLivorColor() + " (" + livorAssessment + ")\n" +
 "Rigor Mortis Stage Assessment: " + rigorAssessment + "\n" +
 "=====================================";

 // Sends the finished calculation string directly back to her browser view
 model.addAttribute("report", compiledReport);

 } catch (Exception e) {
 // If anything goes wrong, it shows the error message beautifully inside the app container instead of crashing
 model.addAttribute("report", "Error processing application inputs: " + e.getMessage());
 }
 
 return "index";
 }
}
