package main.java.Restaurent_Ordering_System.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.Restaurent_Ordering_System.dao.OrderDAO;
import main.java.Restaurent_Ordering_System.dao.UserDAO;
import main.java.Restaurent_Ordering_System.model.MenuItem;
import main.java.Restaurent_Ordering_System.model.Order;
import main.java.Restaurent_Ordering_System.model.User;
import main.java.Restaurent_Ordering_System.service.Restaurant;
import main.java.Restaurent_Ordering_System.service.ServiceFactory;

import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;

public class RestaurantApp {

	private final Restaurant restaurant = new Restaurant();
	private final Order currentOrder = new Order();
	private final OrderDAO orderDao = ServiceFactory.getOrderDAO();
	private final Scanner scanner = new Scanner(System.in);
	private final UserDAO userDAO = ServiceFactory.getUserDAO();
	private User loggedInUser;

	public static void main(String[] args) {
		new RestaurantApp().run();
	}

	private void run() {
		System.out.println("Welcome To Our Restaurant 🍽!");

		boolean systemRunning = true;

		while (systemRunning) {
			System.out.println("\nSelect Role to Login:");
			System.out.println("1) Admin");
			System.out.println("2) Staff");
			System.out.println("3) Client");
			System.out.println("0) Exit");
			System.out.print("Choose option: ");
			int roleChoice = readInt();

			switch (roleChoice) {
				case 1 -> {
					if (login("admin")) runUserMenu();
				}
				case 2 -> {
					if (login("staff")) runUserMenu();
				}
				case 3 -> {
					loggedInUser = new User(-1, "guest_client", "client");
					System.out.println("Login successful! Welcome, " + loggedInUser.getUsername() + " (" + loggedInUser.getRole() + ")");
					runUserMenu();
				}
				case 0 -> {
					systemRunning = false;
					System.out.println("Exiting the system...");
				}
				default -> System.out.println("Invalid option. Try again.");
			}
		}

		System.out.println("Thank you for using the Restaurant Ordering System!");
	}

	private boolean login(String expectedRole) {
		scanner.nextLine();
		System.out.print("Enter username: ");
		String username = scanner.nextLine().trim();
		System.out.print("Enter password: ");
		String password = scanner.nextLine().trim();

		loggedInUser = userDAO.login(username, password);
		if (loggedInUser == null || !loggedInUser.getRole().equalsIgnoreCase(expectedRole)) {
			System.out.println("Login failed: Invalid credentials or incorrect role.");
			return false;
		}

		System.out.println("Login successful! Welcome, " + loggedInUser.getUsername() + " (" + loggedInUser.getRole() + ")");
		return true;
	}

	private void runUserMenu() {
		boolean userSession = true;
		boolean orderPlaced = false;
		int placedOrderId = -1;

		while (userSession) {
			printMainMenu(orderPlaced);
			System.out.print("Choose option: ");
			int choice = readInt();

			switch (loggedInUser.getRole().toLowerCase()) {
				case "admin" -> {
					switch (choice) {
						case 1 -> showMenu();
						case 2 -> orderDao.viewAllOrders();
						case 3 -> cancelClientOrder();
						case 4 -> cancelStaffOrder();
						case 5 -> restoreClientOrder();
						case 6 -> restoreStaffOrder();
						case 0 -> {
							userSession = false;
							System.out.println("Logging out...");
						}
						default -> System.out.println("Invalid option. Try again.");
					}
				}
				case "staff", "client" -> {
					switch (choice) {
						case 1 -> showMenu();
						case 2 -> addItemToOrder();
						case 3 -> viewCurrentOrder();
						case 4 -> {
							placedOrderId = finalizeOrder();
							if (placedOrderId > 0) orderPlaced = true;
						}
						case 5 -> {
							if (orderPlaced) {
								long minutes = Duration.between(currentOrder.getOrderTime(), LocalDateTime.now()).toMinutes();
								if (minutes < 5) {
									boolean cancelled = currentOrder.cancelOrder();
									if (cancelled) {
										boolean success = false;
										if (loggedInUser.getRole().equalsIgnoreCase("client")) {
											success = orderDao.cancelClientOrderById(placedOrderId, loggedInUser.getId());
										} else if (loggedInUser.getRole().equalsIgnoreCase("staff")) {
											success = orderDao.cancelStaffOrderById(placedOrderId, loggedInUser.getId());
										}
										if (success) {
											System.out.println("✅ Order cancelled.");
											orderPlaced = false;
										} else {
											System.out.println("❌ Cancel failed.");
										}
									}
								} else {
									System.out.println("❌ Cannot cancel after 5 minutes. Please contact admin.");
								}
							} else {
								System.out.println("❌ No cancellable order found.");
							}
						}
						case 0 -> {
							userSession = false;
							System.out.println("Logging out...");
						}
						default -> System.out.println("Invalid option. Try again.");
					}
				}
			}
		}
	}

	private void printMainMenu(boolean orderPlaced) {
		System.out.println("\n-----------------------------");
		switch (loggedInUser.getRole().toLowerCase()) {
			case "admin" -> {
				System.out.println("1) Show Menu");
				System.out.println("2) View Order Reports");
				System.out.println("3) Cancel Client Order");
				System.out.println("4) Cancel Staff Order");
				System.out.println("5) Restore Client Order");
				System.out.println("6) Restore Staff Order");
				System.out.println("0) Logout");
			}
			case "staff", "client" -> {
				System.out.println("1) Show Menu");
				System.out.println("2) Add Item to Order");
				System.out.println("3) View Current Order");
				System.out.println("4) Finalize Order");
				if (orderPlaced) {
					long minutes = Duration.between(currentOrder.getOrderTime(), LocalDateTime.now()).toMinutes();
					if (minutes < 5) {
						System.out.println("5) Cancel Order");
					} else {
						System.out.println("Cannot cancel after 5 minutes. Contact admin.");
					}
				}
				System.out.println("0) Logout");
			}
		}
	}

	private void cancelClientOrder() {
		System.out.print("Enter Client Order ID to cancel: ");
		int orderId = readInt();
		boolean success = orderDao.cancelClientOrderById(orderId, loggedInUser.getId());
		System.out.println(success ? "✅ Client order cancelled successfully." : "❌ Failed to cancel client order.");
	}

	private void cancelStaffOrder() {
		System.out.print("Enter Staff Order ID to cancel: ");
		int orderId = readInt();
		boolean success = orderDao.cancelStaffOrderById(orderId, loggedInUser.getId());
		System.out.println(success ? "✅ Staff order cancelled successfully." : "❌ Failed to cancel staff order.");
	}

	private void restoreClientOrder() {
		System.out.print("Enter Client Order ID to restore: ");
		int orderId = readInt();
		boolean restored = orderDao.restoreCancelledClientOrderById(orderId, loggedInUser.getId());
		System.out.println(restored ? "✅ Client order restored successfully." : "❌ Could not restore client order.");
	}

	private void restoreStaffOrder() {
		System.out.print("Enter Staff Order ID to restore: ");
		int orderId = readInt();
		boolean restored = orderDao.restoreCancelledStaffOrderById(orderId, loggedInUser.getId());
		System.out.println(restored ? "✅ Staff order restored successfully." : "❌ Could not restore staff order.");
	}

	private void showMenu() {
		System.out.println("\n--- Select Menu Category ---");
		System.out.println("1) Veg");
		System.out.println("2) Non-Veg");
		System.out.print("Choose option: ");
		int choice = readInt();

		String category = switch (choice) {
			case 1 -> "veg";
			case 2 -> "non-veg";
			default -> {
				System.out.println("Invalid choice.");
				yield null;
			}
		};

		if (category != null) {
			List<MenuItem> filtered = restaurant.getMenuByCategory(category);
			System.out.println("\n--- Menu: " + category.toUpperCase() + " ---");
			System.out.printf("%-4s %-25s %-10s%n", "No.", "Item Name", "Price (₹)");
			System.out.println("-----------------------------------------------");

			int startIndex = category.equalsIgnoreCase("non-veg") ? 11 : 1;
			for (int i = 0; i < filtered.size(); i++) {
				MenuItem item = filtered.get(i);
				System.out.printf("%-4d %-25s ₹%-9.2f%n", startIndex + i, item.getName(), item.getPrice());
			}
		}
	}

	private void addItemToOrder() {
		System.out.print("Enter Item ID: ");
		int itemId = readInt();
		System.out.print("Enter quantity: ");
		int quantity = readInt();

		MenuItem item = restaurant.getItemById(itemId);
		if (item != null && quantity > 0) {
			currentOrder.addItem(item, quantity);
			System.out.println("✅ Item added to order.");
		} else {
			System.out.println("❌ Invalid item or quantity.");
		}
	}

	private void viewCurrentOrder() {
		if (currentOrder.isEmpty()) {
			System.out.println("📝 Your order is empty.");
		} else {
			currentOrder.printBill();
		}
	}

	private int finalizeOrder() {
		if (currentOrder.isEmpty()) {
			System.out.println("❌ Cannot finalize an empty order.");
			return -1;
		}

		currentOrder.printBill();

		if (loggedInUser.getRole().equalsIgnoreCase("client")) {
			System.out.print("Proceed with payment? (yes/no): ");
			String confirm = scanner.next();
			if (!confirm.equalsIgnoreCase("yes")) {
				System.out.println("❌ Order cancelled.");
				return -1;
			}
		}

		int orderId = orderDao.saveOrder(currentOrder, loggedInUser.getId());
		if (orderId > 0) {
			currentOrder.setOrderTime(LocalDateTime.now());
			System.out.println("✅ Order placed! Order ID: " + orderId);
		} else {
			System.out.println("❌ Order failed to save.");
		}

		return orderId;
	}

	private int readInt() {
		try {
			return scanner.nextInt();
		} catch (InputMismatchException e) {
			scanner.nextLine();
			return -1;
		}
	}
}
