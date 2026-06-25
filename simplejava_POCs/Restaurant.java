package simplejava_POCs;
//WAP to display a restaurant menu, accept customer orders, and generate the total bill..using Do while with switch cases
import java.util.Scanner;
public class Restaurant {

	public static void main(String[] args) {
		Scanner scr=new Scanner(System.in);
		double totalBill=0;
		char continueOrder='Y';
		String billDetails = "";
		do {
			System.out.println("------------------------------------------------------------------");
			System.out.println(" \n    🏨🍽️✨   ***Welcome to Mayuri's Restaurant***  ✨🍽️🏨       ");
			System.out.println("-------------------------------------------------------------------");
			System.out.println("====================🍽️ **Here is Our Restuarant Menu** 🍽️======================-");
			System.out.println("1.Veg Starters..");
			System.out.println("2.Non Veg Starters..");
			System.out.println("3.Veg Menu..");
			System.out.println("4.Non Veg Menu..");
			System.out.println("5.Cool Drinks..");
			System.out.println("6. Total Bill..");
			System.out.println("Enter your Category: ");
			int category=scr.nextInt();
			switch(category) {
			case 1: 
				System.out.println("🥗 ----- VEG STARTERS ----- 🥗");
			    System.out.println("1. Gobi Manchurian - ₹160");
			    System.out.println("2. Paneer Tikka - ₹250");
			    System.out.println("3. Veg Manchurian - ₹170");
			    System.out.println("4. Crispy Corn - ₹180");
			    System.out.println("5. Mushroom 65 - ₹200");
			    System.out.println("----------------------------------");
			    System.out.println("Select Item: ");
			    int vegStarter=scr.nextInt();
			    switch(vegStarter) {
			    case 1:
			    	System.out.println("Gobi Manchurian added to cart..");
			    	System.out.println("Enter Quantity: ");
				    int starterQnty1=scr.nextInt();
			    	totalBill+=160*starterQnty1;
			    	billDetails += "Gobi Manchurian\t\t" + starterQnty1 + "\t₹" + (160 * starterQnty1) + "\n";
			    	break;
			    case 2:
			    	System.out.println("Paneer Tikka added to cart..");
			    	System.out.println("Enter Quantity: ");
				    int starterQnty2=scr.nextInt();
			    	totalBill+=250*starterQnty2;
			    	billDetails += "Paneer Tikka\t\t" + starterQnty2 + "\t₹" + (250 * starterQnty2) + "\n";
			    	break;
			    case 3:
			    	System.out.println(" Veg Manchurian added to cart..");
			    	System.out.println("Enter Quantity: ");
				    int starterQnty3=scr.nextInt();
			    	totalBill+=170*starterQnty3;
			    	billDetails += "Veg Manchurian\t\t" + starterQnty3 + "\t₹" + (170 * starterQnty3) + "\n";
			    	break;
			    case 4:
			    	System.out.println("Crispy Corn added to cart..");
			    	System.out.println("Enter Quantity: ");
				    int starterQnty4=scr.nextInt();
			    	totalBill+=180*starterQnty4;
			    	billDetails += "Crispy Corn\t\t" + starterQnty4 + "\t₹" + (180 * starterQnty4) + "\n";
			    	break;
			    case 5:
			    	System.out.println("Mushroom 65 added to cart..");
			    	System.out.println("Enter Quantity: ");
				    int starterQnty5=scr.nextInt();
			    	totalBill+=200*starterQnty5;
			    	billDetails += "Mushroom 65\t\t" + starterQnty5 + "\t₹" + (200 * starterQnty5) + "\n";
			    	break;
			    default:
			    	System.out.println("Sorry..! Entered Veg Starter is not available..");
			    	break;
			    }
			    break;
			case 2:
				System.out.println("🍗 ----- NON-VEG STARTERS ----- 🍗");
				System.out.println("1. Chicken 65 - ₹220");
				System.out.println("2. Chicken Lollipop - ₹250");
				System.out.println("3. Dragon Chicken - ₹280");
				System.out.println("4. Pepper Chicken - ₹260");
				System.out.println("5. Apollo Fish Fry - ₹300");
				System.out.println("----------------------------------");
				System.out.println("Select Item: ");
				int nonVegStr=scr.nextInt();
				switch(nonVegStr) {
				case 1:
					System.out.println("Chicken 65 added to cart.");
					System.out.println("Enter Quantity: ");
					int nvsQnty1=scr.nextInt();
		            totalBill += 220 * nvsQnty1;
		            billDetails += "Chicken 65\t\t" + nvsQnty1 + "\t₹" + (220 * nvsQnty1) + "\n";
		            break;
				case 2:
					System.out.println("Chicken Lollipop added to cart.");
					System.out.println("Enter Quantity: ");
					int nvsQnty2=scr.nextInt();
		            totalBill += 250 * nvsQnty2;
		            billDetails += "Chicken Lollipop\t\t" + nvsQnty2 + "\t₹" + (250 * nvsQnty2) + "\n";
		            break;
				case 3:
					System.out.println("Dragon Chicken added to cart.");
					System.out.println("Enter Quantity: ");
					int nvsQnty3=scr.nextInt();
		            totalBill += 280 * nvsQnty3;
		            billDetails += "Dragon Chicken\t\t" + nvsQnty3 + "\t₹" + (280 * nvsQnty3) + "\n";
		            break;
				case 4:
					System.out.println("Pepper Chicken added to cart.");
					System.out.println("Enter Quantity: ");
					int nvsQnty4=scr.nextInt();
		            totalBill += 260 * nvsQnty4;
		            billDetails += "Pepper Chicken\t\t" + nvsQnty4 + "\t₹" + (260 * nvsQnty4) + "\n";
		            break;
				case 5:
					System.out.println("Apollo Fish Fry added to cart.");
					System.out.println("Enter Quantity: ");
					int nvsQnty5=scr.nextInt();
		            totalBill += 220 * nvsQnty5;
		            billDetails += "Apollo Fish Fry\t\t" + nvsQnty5 + "\t₹" + (220 * nvsQnty5) + "\n";
		            break;
		         default:
		        	 System.out.println("Sorry..! Entered Non-Veg Starter is not available..");
		        	 break;
				}
				break;
			case 3:
				System.out.println("🥦----------**VEG MENU** --------🥦");
                System.out.println("1. Veg Fried Rice - ₹150");
                System.out.println("2. Veg Pulao - ₹190");
                System.out.println("3. Mushroom Biryani - ₹250");
                System.out.println("4.Paneer Biryani - ₹300");
                System.out.println("5.Paneer Butter Masala - ₹250");
                System.out.println("----------------------------------");
                System.out.println("Select Item: ");
                int vegChoice=scr.nextInt();
                switch(vegChoice) {
                case 1: 
                	System.out.println("Veg Fried Rice added to cart..");
                	System.out.println("Enter Quantity: ");
                    int vegQnty1=scr.nextInt();
                    totalBill += 150*vegQnty1;
                    billDetails += "Veg Fried Rice\t\t" + vegQnty1 + "\t₹" + (150 * vegQnty1) + "\n";
                    break;
                case 2: 
                	System.out.println("Veg Pulao added to cart..");
                	System.out.println("Enter Quantity: ");
                    int vegQnty2=scr.nextInt();
                    totalBill += 190*vegQnty2;
                    billDetails += "Veg Pulao\t\t" + vegQnty2 + "\t₹" + (190 * vegQnty2) + "\n";
                    break;
                case 3: 
                	System.out.println("Mushroom Biryani added to cart..");
                	System.out.println("Enter Quantity: ");
                    int vegQnty3=scr.nextInt();
                    totalBill += 250*vegQnty3;
                    billDetails += "Mushroom Biryani\t\t" + vegQnty3 + "\t₹" + (250 * vegQnty3) + "\n";
                    break;
                case 4: 
                	System.out.println("Paneer Biryani added to cart..");
                	System.out.println("Enter Quantity: ");
                    int vegQnty4=scr.nextInt();
                    totalBill += 300*vegQnty4;
                    billDetails += "Paneer Biryani\t\t" + vegQnty4 + "\t₹" + (300 * vegQnty4) + "\n";
                    
                    break;
                case 5: 
                	System.out.println("Paneer Butter Masala added to cart..");
                	System.out.println("Enter Quantity: ");
                    int vegQnty5=scr.nextInt();
                    totalBill += 250*vegQnty5;
                    billDetails += "Paneer Butter Masala\t\t" + vegQnty5 + "\t₹" + (250 * vegQnty5) + "\n";
                    break;
                default:
                	System.out.println("Sorry..! Entered Veg Item is not available..");
                	break;
                }
                break;
			case 4:
				System.out.println("🍗----------**NON-VEG MENU** --------🍗");
                System.out.println("1. Chicken Biryani - ₹150");
                System.out.println("2. Chicken Dum Biryani - ₹180");
                System.out.println("3. Fried piece Chiken Biryani - ₹250");
                System.out.println("4.SpicyChicken Biryani - ₹350");
                System.out.println("5. Chicken Manchuriya - ₹160");
                System.out.println("------------------------------------");
                System.out.println("Select Item: ");
                int nonVegChoice=scr.nextInt();
                switch(nonVegChoice) {
                case 1:
                	System.out.println("Chicken Biryani added to cart..");
                	System.out.println("Enter Quantity: ");
                    int nonVegQnty1=scr.nextInt();
                	totalBill += 150*nonVegQnty1;
                	billDetails += "Chicken Biryani\t\t" + nonVegQnty1 + "\t₹" + (150 * nonVegQnty1) + "\n";
                	break;
                case 2:
                	System.out.println("Chicken Dum Biryani added to cart..");
                	System.out.println("Enter Quantity: ");
                    int nonVegQnty2=scr.nextInt();
                	totalBill += 180*nonVegQnty2;
                	billDetails += "Chicken Dum Biryani\t\t" + nonVegQnty2 + "\t₹" + (180 * nonVegQnty2) + "\n";
                	break;
                case 3:
                	System.out.println("Fried piece Chiken Biryani added to cart..");
                	System.out.println("Enter Quantity: ");
                    int nonVegQnty3=scr.nextInt();
                	totalBill += 250*nonVegQnty3;
                	billDetails += "Fried Piece Chicken Biryani\t\t" + nonVegQnty3 + "\t₹" + (250 * nonVegQnty3) + "\n";
                	break;
                case 4:
                	System.out.println("Spicy Chicken Biryani added to cart..");
                	System.out.println("Enter Quantity: ");
                    int nonVegQnty4=scr.nextInt();
                	totalBill += 350*nonVegQnty4;
                	billDetails += "Spicy Chicken Biryani\t\t" + nonVegQnty4 + "\t₹" + (350 * nonVegQnty4) + "\n";
                	break;
                case 5:
                	System.out.println("Chicken Manchuriya added to cart..");
                	System.out.println("Enter Quantity: ");
                    int nonVegQnty5=scr.nextInt();
                	totalBill += 160*nonVegQnty5;
                	billDetails += "Chicken Manchuria\t\t" + nonVegQnty5 + "\t₹" + (160 * nonVegQnty5) + "\n";
                	break;
                default:
                	System.out.println("Sorry..! Entered Non-Veg Item is not available..");
                	break;
                }
                break;
			case 5: 
				System.out.println("🥤 ----- COOL DRINKS MENU ----- 🥤");
			    System.out.println("1. Coca-Cola - ₹40");
			    System.out.println("2. Sprite - ₹40");
			    System.out.println("3. Thums Up - ₹40");
			    System.out.println("4. Fanta - ₹40");
			    System.out.println("5. Maaza - ₹35");
			    System.out.println("6. Limca - ₹35");
			    System.out.println("7. Pepsi - ₹40");
			    System.out.println("----------------------------------");
			    System.out.println("Enter your Choice: ");
			    int drinkChoice=scr.nextInt();
			    switch(drinkChoice) {
			    case 1: 
			    	System.out.println("🥤 Coca-Cola added to cart.");
			    	System.out.println("Enter Quantity: ");
				    int dQuantity1=scr.nextInt();
		            totalBill += 40*dQuantity1;
		            billDetails += "Coca-Cola\t\t" + dQuantity1 + "\t₹" + (40 * dQuantity1) + "\n";
		            break;
			    case 2: 
			    	System.out.println("🥤 Sprite added to cart.");
			    	System.out.println("Enter Quantity: ");
				    int dQuantity2=scr.nextInt();
		            totalBill += 40*dQuantity2;
		            billDetails += "Sprite\t\t" + dQuantity2 + "\t₹" + (40 * dQuantity2) + "\n";
		            break;
			    case 3: 
			    	System.out.println("🥤 Thums Up added to cart.");
			    	System.out.println("Enter Quantity: ");
				    int dQuantity3=scr.nextInt();
		            totalBill += 40*dQuantity3;
		            billDetails += "Thums Up\t\t" + dQuantity3 + "\t₹" + (40 * dQuantity3) + "\n";
		            break;
			    case 4: 
			    	System.out.println("🥤 Fanta added to cart.");
			    	System.out.println("Enter Quantity: ");
				    int dQuantity4=scr.nextInt();
		            totalBill += 40*dQuantity4;
		            billDetails += "Fanta\t\t" + dQuantity4 + "\t₹" + (40 * dQuantity4) + "\n";
		            break;
			    case 5: 
			    	System.out.println("🥤 Maaza added to cart.");
			    	System.out.println("Enter Quantity: ");
				    int dQuantity5=scr.nextInt();
		            totalBill += 35*dQuantity5;
		            billDetails += "Maaza\t\t" + dQuantity5 + "\t₹" + (35 * dQuantity5) + "\n";
		            break;
			    case 6: 
			    	System.out.println("🥤 Limca added to cart.");
			    	System.out.println("Enter Quantity: ");
				    int dQuantity6=scr.nextInt();
		            totalBill += 35*dQuantity6;
		            billDetails += "Limca\t\t" + dQuantity6 + "\t₹" + (35 * dQuantity6) + "\n";
		            break;
			    case 7: 
			    	System.out.println("🥤 Pepsi added to cart.");
			    	System.out.println("Enter Quantity: ");
				    int dQuantity7=scr.nextInt();
		            totalBill += 40*dQuantity7;
		            billDetails += "Pepsi\t\t" + dQuantity7 + "\t₹" + (40 * dQuantity7) + "\n";
		            break;
		       default: 
		    	   System.out.println("Sorry..! Entered Drink is not available");
		    	   break;
		        
			    }
			    break;
			case 6:
				double gst = totalBill * 0.05;
			    double finalAmount = totalBill + gst;
			    System.out.println("\n🧾 ===== MAYURI'S RESTAURANT BILL ===== 🧾");
			    System.out.println("-----------------------------------------------------");
			    System.out.println("Item\t\tQty\tAmount");
			    System.out.println("-----------------------------------------------------");
			    System.out.print(billDetails);
			    System.out.println("------------------------------------------");
			    System.out.println("Subtotal : ₹" + totalBill);
			    System.out.println("GST (5%) : ₹" + gst);
			    System.out.println("------------------------------------------");
			    System.out.println("Total    : ₹" + finalAmount);
			    System.out.println("------------------------------------------");
			    continueOrder = 'N';
			    break;
			default:
                System.out.println("Invalid Category...!");
                break;
			}
			if(category != 6) {
			    System.out.print("Do you want to order another item? (Y/N): ");
			    continueOrder = scr.next().charAt(0);
			    if(continueOrder == 'N' || continueOrder == 'n') {
			        System.out.println("\nPlease select Category 6 to generate the bill.");
			        continueOrder = 'Y';
			    }
			}
		}while(continueOrder=='Y' || continueOrder=='y');
        System.out.println(" 🌸 Thank you for visiting our restaurant! We hope you enjoyed your meal. 😊🍴");
	}

}
