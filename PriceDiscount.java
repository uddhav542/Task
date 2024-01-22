import java.util.Scanner;

public class PriceDiscount {
    public static void main(String[] args) {
        // Given Data
        int priceA = 20;
        int priceB = 40;
        int priceC = 50;

        // Taking Inputs
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Quantity of A: ");
        int A = scanner.nextInt();
        System.out.print("Is A Wrapped as gift? (yes/no) - ");
        String Ad = scanner.next();
        System.out.print("Enter Quantity of B: ");
        int B = scanner.nextInt();
        System.out.print("Is B Wrapped as gift? (yes/no) - ");
        String Bd = scanner.next();
        System.out.print("Enter Quantity of C: ");
        int C = scanner.nextInt();
        System.out.print("Is C Wrapped as gift? (yes/no) - ");
        String Cd = scanner.next();

        int giftWrapFee = 0;

        int cart = A * priceA + B * priceB + C * priceC;

        // Calculate flat 10% discount
        int discountFlat10 = 10;

        // Calculate bulk 5% discount
        int discountBulk5 = (A > 10 ? A * priceA * 5 / 100 : 0) +
                            (B > 10 ? B * priceB * 5 / 100 : 0) +
                            (C > 10 ? C * priceC * 5 / 100 : 0);

        // Calculate bulk 10% discount
        int discountBulk10 = (cart * 10 / 100);

        // Calculate tiered 50% discount
        int discountTiered50 = (A > 15 ? (A - 15) * priceA * 50 / 100 : 0) +
                               (B > 15 ? (B - 15) * priceB * 50 / 100 : 0) +
                               (C > 15 ? (C - 15) * priceC * 50 / 100 : 0);

        // Calculate total discount based on various conditions
        int[] discounts = { discountTiered50, discountBulk10, discountBulk5, discountFlat10 };

        //Choosing most benefacial discount
        int maxDiscountIndex = 0;
        for (int i = 1; i < discounts.length; i++) {
            if (discounts[i] > discounts[maxDiscountIndex]) {
                maxDiscountIndex = i;
            }
        }

        String[] discountNames = { "tiered_50", "bulk_10", "bulk_5", "flat_10" };
        String discountName = discountNames[maxDiscountIndex];
        int totalDiscount = discounts[maxDiscountIndex];

        // Update gift wrap fee
        if (Ad.equalsIgnoreCase("yes")) {
            giftWrapFee += A;
        }
        if (Bd.equalsIgnoreCase("yes")) {
            giftWrapFee += B;
        }
        if (Cd.equalsIgnoreCase("yes")) {
            giftWrapFee += C;
        }

        // Calculate shipping fee
        int shippingFee = (A + B + C) % 10 == 0 ? 5 * ((A + B + C) / 10) : 5 * ((A + B + C) / 10 + 1);

        // Calculate total cost
        int total = cart - totalDiscount + giftWrapFee + shippingFee;

        // Displaying Output
        System.out.println("Name        | Quantity  | Total ");
        System.out.println("Product A:  | " + A + "      | " + A * priceA);
        System.out.println("Product B:  | " + B + "      | " + B * priceB);
        System.out.println("Product C:  | " + C + "      | " + C * priceC);

        System.out.println("subtotal: " + cart);
        System.out.println("Discount Name: " + discountName);
        System.out.println("Discount: " + totalDiscount);
        System.out.println("gift wrap fee: " + giftWrapFee);
        System.out.println("Shipping fee: " + shippingFee);
        System.out.println("Total: " + total);

        // Close the scanner
        scanner.close();
    }
}
