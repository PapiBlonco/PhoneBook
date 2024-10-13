import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact {
    String name;
    String number;
    String address;


    public Contact(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = "";
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Number: " + number + ", Address: " + address;
    }
}

public class PhoneBook {
    private final List<Contact> contacts;

    public PhoneBook() {
        contacts = new ArrayList<>();
    }

    // Method to add a new contact
    public void addContact(String name, String number, String address) {
        contacts.add(new Contact(name, number, address));
        System.out.println("Contact added: " + name + " - " + number + " - " + address);
    }

    // Method to display all contacts
    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            System.out.println("Contact List:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    // Method to delete a contact by name or number
    public void deleteContact(String query) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.name.equalsIgnoreCase(query) || contact.number.equals(query)) {
                contacts.remove(contact);
                System.out.println("Contact deleted: " + contact);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found: " + query);
        }
    }

    // Method to search for a contact by name or number
    public void searchContact(String query) {
        for (Contact contact : contacts) {
            if (contact.name.equalsIgnoreCase(query) || contact.number.equals(query)) {
                System.out.println("Contact found: " + contact);
                return; // End search if contact is found
            }
        }
        System.out.println("Contact not found");
    }

    // Method to update a contact's details
    public void updateContact(String query) {
        for (Contact contact : contacts) {
            if (contact.name.equalsIgnoreCase(query) || contact.number.equals(query) || contact.address.equals(query)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the new name (or press Enter to keep the current name): ");
                String newName = scanner.nextLine();
                System.out.print("Enter the new number (or press Enter to keep the current number): ");
                String newNumber = scanner.nextLine();

                // Update name if newName is not empty
                if (!newName.isEmpty()) {
                    contact.name = newName;
                }
                // Update number if newNumber is not empty
                if (!newNumber.isEmpty()) {
                    contact.number = newNumber;
                }

                System.out.println("Contact updated: " + contact);
                return; // End method after updating
            }
        }
        System.out.println("Contact not found: " + query);
    }

    // Merge sort method
    public void sortContacts() {
        mergeSort(contacts, 0, contacts.size() - 1);
        System.out.println("Contacts sorted alphabetically.");
    }

    // Recursive merge sort function
    private void mergeSort(List<Contact> contacts, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Sort first and second halves
            mergeSort(contacts, left, mid);
            mergeSort(contacts, mid + 1, right);

            // Merge the sorted halves
            merge(contacts, left, mid, right);
        }
    }

    // Merge function to combine two sorted halves
    private void merge(List<Contact> contacts, int left, int mid, int right) {
        // Find sizes of two sub arrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        List<Contact> leftArray = new ArrayList<>(n1);
        List<Contact> rightArray = new ArrayList<>(n2);

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray.add(contacts.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightArray.add(contacts.get(mid + 1 + j));
        }

        // Merge the temporary arrays
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray.get(i).name.compareToIgnoreCase(rightArray.get(j).name) <= 0) {
                contacts.set(k, leftArray.get(i));
                i++;
            } else {
                contacts.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray if any
        while (i < n1) {
            contacts.set(k, leftArray.get(i));
            i++;
            k++;
        }

        // Copy remaining elements of rightArray if any
        while (j < n2) {
            contacts.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        PhoneBook phonebook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Menu loop
        do {
            System.out.println("\nPhoneBook Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Display All Contacts");
            System.out.println("3. Delete Contact");
            System.out.println("4. Search Contact");
            System.out.println("5. Update Contact");
            System.out.println("6. Sort Contacts");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a new contact
                    System.out.print("Enter the name of the new contact: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter the phone number of the new contact: ");
                    String newPhoneNumber = scanner.nextLine();
                    System.out.print("Enter the address of the new contact: ");
                    String newAddress = scanner.nextLine();
                    phonebook.addContact(newName, newPhoneNumber, newAddress);
                    break;

                case 2:
                    // Display all contacts
                    phonebook.displayAllContacts();
                    break;

                case 3:
                    // Delete a contact
                    System.out.print("Enter the name or number of the contact you want to delete: ");
                    String contactToDelete = scanner.nextLine();
                    phonebook.deleteContact(contactToDelete);
                    break;

                case 4:
                    // Search for a contact
                    System.out.print("Enter the contact name or number to search: ");
                    String query = scanner.nextLine();
                    phonebook.searchContact(query);
                    break;

                case 5:
                    // Update a contact
                    System.out.print("Enter the name or number of the contact you want to update: ");
                    String contactToUpdate = scanner.nextLine();
                    phonebook.updateContact(contactToUpdate);
                    break;

                case 6:
                    // Sort contacts
                    phonebook.sortContacts();
                    break;

                case 7:
                    // Exit the program
                    System.out.println("Exiting the phonebook.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        // Close the scanner
        scanner.close();
    }
}
