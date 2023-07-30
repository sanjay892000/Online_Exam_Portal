public class Main {
    public static void main(String[] args) {
        //preSignedUp users which you can login without signup
        User u1 = new User("akshay rana", 248, 10000);
        User u2 = new User("vikash singh", 780, 29000);

        //adding the above users in array list containing objects of all users
        Functionalities fn = new Functionalities();
        fn.users.add(u1);
        fn.users.add(u2);

        fn.menu();
       
    }
}
