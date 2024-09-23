public class Main {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("Foo");
        list.add("Bar");
        list.add("Baz");

        for(String s : list) {
            System.out.println(s);
        }

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        list.delete(2);
        list.delete(2); // won't work!

        for(String s : list) {
            System.out.println(s);
        }

        Queue<Integer> queue = new Queue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);

        for(Integer i : queue) {
            System.out.println(i);
        }

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop()); // won't work! should be null

        for(Integer i : queue) {
            System.out.println(i);
        }

        Stack<Double> stack = new Stack<>();
        stack.push(1.0);
        stack.push(2.0);
        stack.push(3.5);
        stack.push(4.6);
        stack.push(-13.5);

        for(Double d : stack) {
            System.out.println(d);
        }

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


        stack.push(1.5);
        stack.push(60.934);

        for(Double d : stack) {
            System.out.println(d);
        }
    }
}
