// UC8: Linked List Based Palindrome Checker

public class PalindromeCheckerApp {

    // Node class for singly linked list
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {

        // Original string
        String text = "racecar";

        // Convert string to linked list
        Node head = createLinkedList(text);

        // Check palindrome
        boolean isPalindrome = checkPalindrome(head);

        // Display result
        if (isPalindrome) {
            System.out.println("The string \"" + text + "\" is a Palindrome.");
        } else {
            System.out.println("The string \"" + text + "\" is NOT a Palindrome.");
        }
    }

    // Convert string to linked list
    private static Node createLinkedList(String str) {
        Node head = null;
        Node tail = null;

        for (char ch : str.toCharArray()) {
            Node newNode = new Node(ch);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        return head;
    }

    // Check palindrome using fast/slow pointer and in-place reversal
    private static boolean checkPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // Find middle (slow will point to middle)
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        Node secondHalf = reverseList(slow.next);

        // Compare first half and reversed second half
        Node firstHalf = head;
        Node tempSecond = secondHalf;
        boolean isPalindrome = true;
        while (tempSecond != null) {
            if (firstHalf.data != tempSecond.data) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            tempSecond = tempSecond.next;
        }

        // Optional: Restore the list (reverse back second half)
        slow.next = reverseList(secondHalf);

        return isPalindrome;
    }

    // Reverse linked list
    private static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}