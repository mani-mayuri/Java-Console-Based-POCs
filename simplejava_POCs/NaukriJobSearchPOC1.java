package simplejava_POCs;
//WAP to check job eligibility based on candidate details and display available job opportunities..using nested if
import java.util.Scanner;

public class NaukriJobSearchPOC1 {

	public static void main(String[] args) {
		Scanner scr=new Scanner(System.in);
		System.out.println("Enter your Name:");
		String candidateName=scr.nextLine();
		System.out.println("Enter your Qualification: ");
		String qualification=scr.nextLine();
		if(qualification.equalsIgnoreCase("Btech") || qualification.equalsIgnoreCase("Degree")) {
			System.out.println("Enter your Branch: ");
			String branch=scr.nextLine();
			System.out.println("Enter your passedOut Year: ");
			int year=scr.nextInt();
			if(year==2026 || year==2025) {
				scr.nextLine();
				System.out.println("Enter your Btech Percentage: ");
				double percentage=scr.nextDouble();
				if(percentage>=80) {
					System.out.println("Nice..!Enter your Skills: ");
					String skills=scr.next();
					System.out.println("Enter your Experience: ");
					int experience=scr.nextInt();
					if(experience>=0 && experience<=5) {
						System.out.println("Enter your Preferred Location: ");
						String location=scr.next();
						System.out.println("Enter Expected Salary");
						double expectedSalary=scr.nextDouble();
						if(expectedSalary <= 500000) {
						    System.out.println("Any Active Backlogs? :");
						    String backlogs = scr.next();
						    if(backlogs.equalsIgnoreCase("No")) {
						    	System.out.println("--------------------------------------------------");
						    	System.out.println("🎉 Congratulations " + candidateName + "!");
						    	System.out.println();
						    	System.out.println("You are eligible for the following jobs:");
						    	System.out.println();
						    	System.out.println("✔ " + skills + " Developer");
						    	System.out.println("✔ Full Stack Developer");
						    	System.out.println("✔ Software Engineer");
						    	System.out.println();
						    	System.out.println("Preferred Location : " + location);
						    	System.out.println("Expected Salary    : ₹" + expectedSalary);
						    	System.out.println("--------------------------------------------------");
						    }else {
						    	System.out.println("Better luck Next Time");
						    }
						}
						else {
						    System.out.println("Expected Salary Too High..we will contact you later");
						}
					}else {
						System.out.println("Experience should be between 0 and 5 years");
					}
				}else {
					System.out.println("Minimum percentage required is 80%.");
				}
			}else {
				System.out.println("Sorry! Only 2025 and 2026 graduates can apply");
			}
		}else {
			System.out.println("Only Btech and Degree Students are Qualified");
		}
		scr.close();
		
	}
}
