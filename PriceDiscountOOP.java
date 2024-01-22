import java.util.Scanner;

class Product {
    private String name;
    private int price;
    private int quantity;
    private boolean isGiftWrapped;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.isGiftWrapped = false;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setGiftWrapped(boolean isGiftWrapped) {
        this.isGiftWrapped = isGiftWrapped;
    }

    public int calculateTotal() {
        return quantity * price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isGiftWrapped() {
        return isGiftWrapped;
    }
    public int getPrice() {
        return price;
    }
}

class ShoppingCart {
    private Product[] products;

    public ShoppingCart(Product[] products) {
        this.products = products;
    }

    public int calculateSubtotal() {
        int subtotal = 0;
        for (Product product : products) {
            subtotal += product.calculateTotal();
        }
        return subtotal;
    }

    public Product[] getProducts() {
        return products;
    }
}

class DiscountCalculator {
    public static int calculateDiscountTiered50(Product product) {
        return (product.getQuantity() > 15 ? (product.getQuantity() - 15) * product.getPrice() * 50 / 100 : 0);
    }

    public static int calculateDiscountBulk10(int cartTotal) {
        return cartTotal * 10 / 100;
    }

    public static int calculateDiscountBulk5(Product product) {
        return (product.getQuantity() > 10 ? product.getQuantity() * product.getPrice() * 5 / 100 : 0);
    }

    public static int calculateDiscountFlat10() {
        return 10;
    }
}

public class PriceDiscountOOP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Product productA = new Product("Product A", 20);
        Product productB = new Product("Product B", 40);
        Product productC = new Product("Product C", 50);

        Product[] products = {productA, productB, productC};
        ShoppingCart cart = new ShoppingCart(products);

        for (Product product : cart.getProducts()) {
            System.out.print("Enter Quantity of " + product.getName() + ": ");
            int quantity = scanner.nextInt();

            System.out.print("Is " + product.getName() + " Wrapped as gift? (yes/no) - ");
            String giftWrapped = scanner.next();
            product.setGiftWrapped(giftWrapped.equalsIgnoreCase("yes"));

            product.setQuantity(quantity);
        }

        int cartSubtotal = cart.calculateSubtotal();

        int discountTiered50 = DiscountCalculator.calculateDiscountTiered50(productA)
                + DiscountCalculator.calculateDiscountTiered50(productB)
                + DiscountCalculator.calculateDiscountTiered50(productC);

        int discountBulk10 = DiscountCalculator.calculateDiscountBulk10(cartSubtotal);

        int discountBulk5 = DiscountCalculator.calculateDiscountBulk5(productA)
                + DiscountCalculator.calculateDiscountBulk5(productB)
                + DiscountCalculator.calculateDiscountBulk5(productC);

        int discountFlat10 = DiscountCalculator.calculateDiscountFlat10();

        int[] discounts = {discountTiered50, discountBulk10, discountBulk5, discountFlat10};

        int maxDiscountIndex = 0;
        for (int i = 1; i < discounts.length; i++) {
            if (discounts[i] > discounts[maxDiscountIndex]) {
                maxDiscountIndex = i;
            }
        }

        String[] discountNames = {"tiered_50", "bulk_10", "bulk_5", "flat_10"};
        String discountName = discountNames[maxDiscountIndex];
        int totalDiscount = discounts[maxDiscountIndex];

        int giftWrapFee = 0;
        for (Product product : cart.getProducts()) {
            if (product.isGiftWrapped()) {
                giftWrapFee += product.getQuantity();
            }
        }

        int totalQuantity = productA.getQuantity() + productB.getQuantity() + productC.getQuantity();
        int shippingFee = ( totalQuantity % 10 == 0) ? 5 * (totalQuantity / 10) : 5 * ((totalQuantity / 10) + 1);

        int total = cartSubtotal - totalDiscount + giftWrapFee + shippingFee;

        // Displaying Output
        System.out.println("Name        | Quantity  | Total ");
        for (Product product : cart.getProducts()) {
            System.out.println(product.getName() + ":  | " + product.getQuantity() + "      | " + product.calculateTotal());
        }

        System.out.println("Subtotal: " + cartSubtotal);
        System.out.println("Discount Name: " + discountName);
        System.out.println("Discount: " + totalDiscount);
        System.out.println("Gift Wrap Fee: " + giftWrapFee);
        System.out.println("Shipping Fee: " + shippingFee);
        System.out.println("Total: " + total);

        // Close the scanner
        scanner.close();
    }
}
