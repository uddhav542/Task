# Given Data
Price_A = 20;
Price_B = 40;
Price_C = 50;


# Taking Inputs
A = int(input("Enter Quantity of A: "))
Ad = input("Is A Wrapped as gift? (yes/no) - ")
B = int(input("Enter Quantity of B: "))
Bd =  input("Is B Wrapped as gift? (yes/no) - ")
C = int(input("Enter Quantity of C: "))
Cd =  input("Is C Wrapped as gift? (yes/no) - ")

gift_wrap_fee = 0

cart =  A*Price_A +  B*Price_B +  C*Price_C 

def flat_10_discount(cart):
    return 10

def bulk_5_discount(A, B , C ):
    discount = 0
    if(A>10):
        discount +=  (A*Price_A)*0.05
    if(B>10):
        discount +=  (B*Price_B)*0.05
    if(C>10):
        discount +=  (C*Price_C)*0.05

    return discount 

def bulk_10_discount(cart):
   return  (cart*0.1)

def tiered_50_discount(A, B , C):
    discount = 0
    if(A>15):
        A = A -15
        discount +=  (A*Price_A)*0.5
    if(B>15):
        B = B - 15
        discount +=  (B*Price_B)*0.5
    if(C>15):
        C = C - 15
        discount +=  (C*Price_C)*0.5
    return discount
   
# Calculating Discounts
def calculate_Discount(cart, A, B ,C):
    discount_flat_10 = 0
    discount_bulk_5 = 0
    discount_bulk_10 = 0
    discount_tiered_50 = 0
                                               
    if(A+B+C > 30 and A>15 or B>15 or C>15):
        discount_tiered_50 = tiered_50_discount(A, B, C)
    if(A+B+C>20):
        discount_bulk_10 = bulk_10_discount(cart)
    if(A>10 or B>10 or C>10):
        discount_bulk_5 = bulk_5_discount(A, B , C)
    if(cart>200):
        discount_flat_10 = flat_10_discount(cart)   

    discounts = {
        "tiered_50": discount_tiered_50,
        "bulk_10": discount_bulk_10,
        "bulk_5": discount_bulk_5,
        "flat_10": discount_flat_10
    }

    max_discount_name = max(discounts, key=discounts.get)
    max_discount_value = discounts[max_discount_name]

    return max_discount_name, max_discount_value

discount_name , total_discount = calculate_Discount(cart, A, B, C)

if(Ad == "yes"): gift_wrap_fee = A
if(Bd == "yes"): gift_wrap_fee += B
if(Cd == "yes"): gift_wrap_fee += C


if((A+B+C)%10 == 0):
    shipping_fee = 5 * ((A+B+C)//10)
else: 
    shipping_fee = 5 * (((A+B+C)//10) + 1)

total = cart - total_discount + gift_wrap_fee + shipping_fee

# Displaying Output
print ( "Name        | Quantity  | Total ")
print ( "Product A:  | ", A ,"      | ", A*Price_A)
print ( "Product B:  | ", B ,"      | ", B*Price_B)
print ( "Product C:  | ", C ,"      | ", C*Price_C)

print("subtotal:", cart)
print("Discount Name:", discount_name)
print("Discount:", total_discount)
print("gift wrap fee:", gift_wrap_fee)
print("Shipping fee:", shipping_fee)
print("Total: ", total)

