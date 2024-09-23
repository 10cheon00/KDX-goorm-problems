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
    }
}
