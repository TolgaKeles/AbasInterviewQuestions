package abas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		List<Order> orders = new ArrayList<>();
        orders.add(new Order(1000, 2000, 12, new BigDecimal(100.51)));
        orders.add(new Order(1000, 2001, 31, new BigDecimal(200)));
        orders.add(new Order(1000, 2002, 22, new BigDecimal(150.86)));
        orders.add(new Order(1000, 2003, 41, new BigDecimal(250)));
        orders.add(new Order(1000, 2004, 55, new BigDecimal(244)));
        orders.add(new Order(1001, 2001, 88, new BigDecimal(44.531)));
        orders.add(new Order(1001, 2002, 121, new BigDecimal(88.11)));
        orders.add(new Order(1001, 2004, 74, new BigDecimal(211)));
        orders.add(new Order(1001, 2002, 14, new BigDecimal(88.11)));
        orders.add(new Order(1002, 2003, 2, new BigDecimal(12.1)));
        orders.add(new Order(1002, 2004, 3, new BigDecimal(22.3)));
        orders.add(new Order(1002, 2003, 8, new BigDecimal(12.1)));
        orders.add(new Order(1002, 2002, 16, new BigDecimal(94)));
        orders.add(new Order(1002, 2005, 9, new BigDecimal(44.1)));
        orders.add(new Order(1002, 2006, 19, new BigDecimal(90)));
        
        System.out.println("Total amount of all orders: " + getTotalAmount(orders));
        System.out.println("Avarage amount of all pruducts: " + getAvarageOfTotalAmount(orders));
        System.out.println("Avarage amount of every pruducts: " + getProductAveragePrice(orders));
        System.out.println("Count of every pruducts of every order: " + getOrderProductQuantities(orders));
        // or for the 4. if we want to see the values with their keyword
        Map<Integer, Map<Integer, Integer>> orderProductQuantities = getOrderProductQuantities(orders);
        for (Map.Entry<Integer, Map<Integer, Integer>> orderEntry : orderProductQuantities.entrySet()) {
            int orderNumber = orderEntry.getKey();
            for (Map.Entry<Integer, Integer> productEntry : orderEntry.getValue().entrySet()) {
                int productNumber = productEntry.getKey();
                int quantity = productEntry.getValue();
                System.out.println("Order Number: " + orderNumber + ", Product Number: " + productNumber + ", Quantity: " + quantity);
            }
        }
	}
	//1.a
	public static BigDecimal getTotalAmount(List <Order> orders) {
		
		BigDecimal totalAmount = BigDecimal.ZERO;
        for (Order order : orders) {
        	BigDecimal orderTotal = order.getUnitPrice().multiply(new BigDecimal(order.getQuantity()));
            totalAmount = totalAmount.add(orderTotal);
        }
        return totalAmount.setScale(2, RoundingMode.HALF_UP);
	}

	//1.b
	public static BigDecimal getAvarageOfTotalAmount(List <Order> orders) {
		
		BigDecimal totalAmount = getTotalAmount(orders);
		BigDecimal avarageAmount = BigDecimal.ZERO;
		BigDecimal totalQuantity = BigDecimal.ZERO;
		
		for (Order order : orders) {
			BigDecimal quantity = new BigDecimal(order.getQuantity());
			totalQuantity = totalQuantity.add(quantity); 
		}
		avarageAmount = totalAmount.divide(totalQuantity, 2, RoundingMode.HALF_UP);
		return avarageAmount;
	}
	//1.c
	public static Map<Integer, BigDecimal> getProductAveragePrice(List<Order> orders) { 
		 Map<Integer, List<Order>> productOrderMap = new HashMap<>();

	        // Group orders by product number 
	        for (Order order : orders) {
	            productOrderMap.putIfAbsent(order.getProductNumber(), new ArrayList<>());

	            List<Order> productOrders = productOrderMap.get(order.getProductNumber());
	            productOrders.add(order);
	        
	        }

	        // Calculate the average price for three orders of each product number
	        Map<Integer, BigDecimal> averagePriceMap = new HashMap<>();
	        for (Map.Entry<Integer, List<Order>> entry : productOrderMap.entrySet()) {
	            BigDecimal totalAmount = BigDecimal.ZERO;
	            BigDecimal totalQuantity = BigDecimal.ZERO;

	            for (Order order : entry.getValue()) {
	                BigDecimal orderTotal = order.getUnitPrice().multiply(new BigDecimal(order.getQuantity()));
	                totalAmount = totalAmount.add(orderTotal);
	                totalQuantity = totalQuantity.add(new BigDecimal(order.getQuantity()));
	            }

	            BigDecimal averagePrice = BigDecimal.ZERO;
	            if (totalQuantity.compareTo(BigDecimal.ZERO) > 0) {
	                averagePrice = totalAmount.divide(totalQuantity, 2, RoundingMode.HALF_UP);
	            }

	            averagePriceMap.put(entry.getKey(), averagePrice);
	        }

	        return averagePriceMap;
	}
	//1.d
	public static Map<Integer, Map<Integer, Integer>> getOrderProductQuantities(List<Order> orders) {
        Map<Integer, Map<Integer, Integer>> orderProductMap = new HashMap<>();

        for (Order order : orders) {
            orderProductMap.putIfAbsent(order.getOrderNumber(), new HashMap<>());

            Map<Integer, Integer> productQuantityMap = orderProductMap.get(order.getOrderNumber());
            productQuantityMap.put(order.getProductNumber(), 
                productQuantityMap.getOrDefault(order.getProductNumber(), 0) + order.getQuantity());
        }

        return orderProductMap;
    }
}

class Order{
	
	private int orderNumber;
	private int productNumber;
	private int quantity;
	private BigDecimal unitPrice;
	
	public Order (int orderNumber, int productNumber, int quantity, BigDecimal unitPrice) {
		this.orderNumber = orderNumber;
		this.productNumber = productNumber;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	

	
}
