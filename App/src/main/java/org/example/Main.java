package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MAX_MESSAGE_LENGTH = 250;

    public Main() {
    }

    public static void main(String[] args) {
        Queue messageQueue = new Queue(250);
        Stack messageStack = new Stack(250);
        boolean exit = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//vòng lặp chính
        while(!exit) {
            //menu tuyf chọn
            System.out.println("----- Menu -----");
            System.out.println("1. Input message");
            System.out.println("2. Send Message");
            System.out.println("3. Received message / View message");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
//luu tru tuy chon
            int choice;
            //luu tu so thanh tuy chon
            try {
                choice = Integer.parseInt(reader.readLine());
                //neu nguoi dung nhap sai
            } catch (IOException | NumberFormatException var7) {
                System.out.println("Invalid input. Please enter a valid choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    enqueueStringToQueue(messageQueue, reader);
                    break;
                case 2:
                    dequeueToStack(messageQueue, messageStack);
                    break;
                case 3:
                    System.out.println("Messages in the stack:");
                    printMessagesInStack(messageStack);
                    break;
                case 4:
                    exit = true;
                    break;
                    //khong hop le
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting the program.");
    }


    // Phương thức thêm một chuỗi vào Queue
    private static void enqueueStringToQueue(Queue queue, BufferedReader reader) {
        System.out.print("Enter the string (less than 250 characters): ");
//doc từ bần phím
        try {
            String input = reader.readLine();
            if (input.length() <= 250) {
                queue.enqueue(input);
                System.out.println("String enqueued successfully!");
            } else {
                System.out.println("String length exceeds 250 characters. Please try again.");
            }
        } catch (IOException var3) {
            // Xử lý ngoại lệ nếu có lỗi khi đọc dữ liệu từ bàn phím
            var3.printStackTrace();
        }

    }

    // Phương thức chuyển dữ liệu từ Queue sang Stack

    private static void dequeueToStack(Queue queue, Stack stack) {
        // Lặp qua tất cả các phần tử trong Queue và đẩy vào Stack
        while(!queue.isEmpty()) {
            String message = queue.dequeue();
            stack.push(message);
        }

    }
    // Phương thức hiển thị thông điệp từ Stack
    private static void printMessagesInStack(Stack stack) {
        while(!stack.isEmpty()) {
            String message = stack.pop();
            System.out.println("Received message: " + message);
        }

    }

    static class Queue {
        // Định nghĩa lớp Queue để lưu trữ các phần tử theo cơ chế hàng đợi
        private String[] array;
        private int front;
        private int rear;
        private int capacity;
        private int size;

        public Queue(int capacity) {
            // Constructor khởi tạo hàng đợi với sức chứa cho trước
            this.capacity = capacity;
            this.array = new String[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        public void enqueue(String element) {
            // Phương thức enqueue để thêm một phần tử vào cuối hàng đợi
            if (this.size == this.capacity) {
                System.out.println("Queue is full. Cannot enqueue.");
            } else {
                this.rear = (this.rear + 1) % this.capacity;
                this.array[this.rear] = element;
                ++this.size;
            }
        }

        public String dequeue() {
            // Phương thức dequeue để lấy phần tử để stack làm việc
            if (this.isEmpty()) {
                System.out.println("Queue is empty. Cannot dequeue.");
                return null;
            } else {
                String element = this.array[this.front];
                this.front = (this.front + 1) % this.capacity;
                --this.size;
                return element;
            }
        }

        public String get(int index) {
            // Phương thức get để lấy phần tử ở một vị trí cụ thể trong hàng đợi
            if (index >= 0 && index < this.size) {
                return this.array[(this.front + index) % this.capacity];
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        // Phương thức kiểm tra xem hàng đợi có rỗng hay không
        public boolean isEmpty() {

            return this.size == 0;
        }

        public int size() {
            return this.size;
        }
    }

    static class Stack {
        private String[] array;
        private int top;
        private int capacity;
        private int size;

        public Stack(int capacity) {
            this.capacity = capacity;
            this.array = new String[capacity];
            this.top = -1;
            this.size = 0;
        }

        public void push(String element) {
            if (this.size == this.capacity) {
                System.out.println("Stack is full. Cannot push.");
            } else {
                ++this.top;
                this.array[this.top] = element;
                ++this.size;
            }
        }

        public String pop() {
            if (this.isEmpty()) {
                System.out.println("Stack is empty. Cannot pop.");
                return null;
            } else {
                String element = this.array[this.top];
                --this.top;
                --this.size;
                return element;
            }
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int size() {
            return this.size;
        }
    }
}