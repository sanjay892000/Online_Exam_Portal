import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import java.util.InputMismatchException;

//Functionalities class contain all the necessary methods used in this project
public class Functionalities {
    //Array containing the correct answers
    char correctAnswers[]={'b','d','c','b','c','d','c','b','b','c'};
    //Array containing the answers by user
    char userAnswers[]=new char[10];
    //Arraylist for storing the newly signed up users
    ArrayList<User> users = new ArrayList<User>();
    static int score=0;
    static int correct=0;
    static int wrong=0;
    Scanner sc = new Scanner(System.in);
    User u = new User();

    //menu method will display menu and take user's choice
    public void menu() {
        try {
            System.out.println("\n_____________________________MENU___________________________");
            Scanner sc = new Scanner(System.in);
            System.out.println("\nEnter\n1 for signup\n2 for login\n3 to update your profile\n4 to exit\n-------------------->");
            int choice = sc.nextInt();

            if (choice == 1) {
                signup();
            }
            else if (choice == 2) {
                login();
            } 
            else if(choice == 3){
                update();
            }
            else if(choice == 4){
                System.out.println("\n--------------------EXITED--------------------");
                System.exit(0);
            }
            else {
                System.out.println("\n--------------------INVALID CHOICE !------------------");
            }
            
        } catch (InputMismatchException e) {
            System.out.println("\n--------------------INVALID INPUT !-------------------");
        }
    }

    //login method will login user's id after verification in login page
    public void login() {
        boolean flag = false;

        System.out.println("\nREDIRECTING YOU TO THE LOGIN PAGE PLEASE WAIT AND REMEMBER YOUR CREDENTIALS......................... ");

        //this particular code is to make a delay to make it feel like realtime
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
        //this particular code is used to clear screen when needed 
        //note : this code will not work properly in command prompt hence, ignore
        System.out.print("\033[H\033[2J"); 
        System.out.flush();

        System.out.println("\n____________________________LOGIN PAGE___________________________");

        System.out.print("\nEnter your id : ");
        int id = sc.nextInt();
        System.out.print("\nEnter your password : ");
        int pass = sc.nextInt();

        for (User i : users) {
            if (i.getId() == id && i.getPass() == pass) { 
                System.out.println("\n------------LOGIN SUCCESSFULL------------");
                flag = true;
                System.out.println("\n--------------WELCOME MR/MRS. "+i.getName()+"-------------");
                examPortal();
            }
        }
        if (flag==false) {
            System.out.println("\n-----------WRONG CREDENTIALS OR NO SUCH ACCOUNT EXISTS !--------------");
            menu();
        }
    }

    //signup method is used to go  to sign up page if you are a new user and dont have account
    //NOTE : After sign up user will be redirected to login page hence remember the credentials 
    //NOTE : after signing up each new user will get an id randomly and password will be decided by user only
    public void signup() {
        Random rand = new Random();
        System.out.println("\nREDIRECTING YOU TO THE SIGNUP PAGE PLEASE WAIT..................");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.print("\033[H\033[2J"); 
        System.out.flush();

        System.out.println("\n____________________________SIGNUP PAGE___________________________");

        System.out.print("\nEnter your name : ");
        
        String name = sc.nextLine();
        sc.nextLine();
        
        System.out.print("\nEnter your password in numbers (5 digit): ");
        int pass = sc.nextInt();    

        int id = rand.nextInt(1000); //To generate random id for new user
        User u1 = new User(name, id, pass);
        users.add(u1);

        System.out.println("\n-----------Your id is : " + id + "--------------");
        System.out.println("\n---------REGISTRATION SUCCESSFULL----------");

        System.out.println("\n---------------DO YOU WANT TO ADD ANOTHER ACCOUNT PRESS 1 AGAIN-------------");
        menu();

    }

    //this method will lead you to the examportal page i.e to the test page
    public void examPortal(){
        int i =0;
        System.out.println("\nREDIRECTING YOU TO THE EXAM PORTAL PLEASE WAIT..................");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.print("\033[H\033[2J"); 
        System.out.flush();

        System.out.println("\n______________________________EXAM PORTAL__________________________________\n");

        System.out.println("\nTIMER STARTED -------- 5 min\nNOTE: Test WILL BE AUTOSUBMITTED WHEN TIME IS OVER !\n");
        System.out.println("\n______________________________________________________________________________\n");
        clock();
        try{
            //file reading which is in txt form 
            String fileName= "C:\\Users\\ranaa\\Desktop\\Internship Projects\\Exam portal\\Questions.txt.txt";
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            Scanner in = new Scanner(System.in);

           
                while(sc.hasNextLine()){
                    try{
                    String line = sc.nextLine();

                    if(line.isEmpty()){
                        System.out.print("Ans : ");
                        char userAnswer = in.next().charAt(0);

                        System.out.println();

                        if(userAnswer >= 'a' && userAnswer<='d'){
                        userAnswers[i]=userAnswer;
                        i++;
                        
                        }
                        else{
                            System.out.println("INVALID CHOICE TRY AGAIN !..........");
                            if(line.isEmpty()){
                            System.out.print("Ans : ");
                            userAnswer = in.next().charAt(0);
                            if(userAnswer >= 'a' && userAnswer<='d'){
                            userAnswers[i]=userAnswer;
                            i++;
                        }}
                        }
                    }
                   else{
                        System.out.println(line);
                    }
                    
                    }catch(InputMismatchException e){
                        System.out.println("\n-----------------INVALID INPUT PLEASE RESTART !--------------");
                    }
                }

                check();
                displayScore();

        }catch(FileNotFoundException e){
            System.out.println("\n-------------FILE DOES NOT EXISTS !---------------");
        }
       
    }
    //check() method will check and match user's answers from the correct answers and calculate the score
    public void check(){
       System.out.println("\nEVALUATING YOUR ANSWERS PLEASE WAIT..............");
       int i=0,j=0;

       while(i<correctAnswers.length){
        if(userAnswers[i]==correctAnswers[j]){
            score+=2;
            correct++;
            i++;
            j++;
        }
        else if(userAnswers[i]!=correctAnswers[j]){
            wrong++;
            i++;
            j++;
        }
       }try {
            TimeUnit.SECONDS.sleep(4);
       } catch (Exception e) {
            System.out.println(e.getMessage());
       }
       System.out.println("\n<<<<<<<<<<<< EVALUATION COMPLETED >>>>>>>>>>>>>>");
    }   

    //displayScore() method will redirect user to the score board page and display his score
    public void displayScore(){
        Functionalities f = new Functionalities();
        System.out.println("\nREDIRECTING YOU TO THE SCORE BOARD PLEASE WAIT.................");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n__________________________SCORE BOARD____________________________");
        System.out.println("\nYOUR SCORE OUT OF 20 IS : "+score);
        System.out.println("\nWRONG ANSWERS : "+wrong);
        System.out.println("\nCORRECT ANSWERS : "+correct);

        //resetting the score for next user
        correct=0;
        score=0;
        wrong=0;
        logout();
    }
    //logout method to end from session or to restart menu
    public void logout(){
        char  choice =' ';
        System.out.println("\n_____________________________________________________________________\n");
        try{
        System.out.println("\nDO YOU WANT TO LOGOUT ?\n\nENTER 'y' FOR YES\nENTER 'n' FOR NO\n" );
        System.out.println("--------------------------->");
        choice = sc.next().charAt(0);
        if(choice == 'y'){
            System.out.println("\n.....................YOUR ID IS LOGGED OUT.....................\n");
            System.exit(0);        
        }
        else if(choice =='n'){
            menu();
        }
        else{
            System.out.println("INVALID CHOICE");
            logout();
        }
    }   catch(InputMismatchException e){
        System.out.println("\nILLEGAL INPUT !");
        logout();
    }
    }

    //Method to update profile as per user decides
    public void update(){
        
        System.out.println("\n--------------------Login your id first to update your profile ---------------------");
        loginForUpdate();
        System.out.println("\nREDIRECTING YOU TO UPDATE PROFILE PAGE PLEASE WAIT..........................");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        System.out.print("\033[H\033[2J"); 
        System.out.flush();

        System.out.println("\n_____________________________UPDATE PROFILE___________________________");
        try{
        System.out.print("\nEnter:\n1 to update your name\n2 to update password\n\n");
        int choice = sc.nextInt();

        if(choice == 1 ){
            System.out.print("\nEnter your new name : ");
            sc.nextLine();
            String name = sc.nextLine();
            
            u.setName(name);
            System.out.println("\n<<<<<<<< NAME CHANGED SUCCESSFULLY >>>>>>>>");
        }
        else if(choice == 2){
            System.out.print("\nEnter your new 5 digit password : ");
            int pass = sc.nextInt();
            u.setPass(pass);
            System.out.println("\n<<<<<<<<<< PASSWORD CHANGED SUCCESFULLY >>>>>>>");
        }
        else{
            System.out.println("\nINVALID CHOICE !");
        }

        menu();

        }catch(InputMismatchException e){
            System.out.println("\nINVALID INPUT !");
        }
       
    }

    //loginForUpdate() will help in varification of user before changing the credentials 
    public void loginForUpdate(){
         boolean flag = false;

        System.out.println("\nREDIRECTING YOU TO THE LOGIN PAGE PLEASE WAIT AND REMEMBER YOUR CREDENTIALS......................... ");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    

        System.out.print("\033[H\033[2J"); 
        System.out.flush();

        System.out.println("\n____________________________LOGIN PAGE___________________________");

        System.out.print("\nEnter your id : ");
        int id = sc.nextInt();
        System.out.print("\nEnter your password : ");
        int pass = sc.nextInt();

        for (User i : users) {
            if (i.getId() == id && i.getPass() == pass) {
                System.out.println("\n------------LOGIN SUCCESSFULL------------");
                flag = true;
                System.out.println("\n--------------WELCOME MR/MRS. "+i.getName()+"-------------");
                break;
            }
            else if(i.getId() != id || i.getPass() != pass){
                System.out.println("\nWRONG CREDENTIALS OR NO SUCH USER FOUND !");
                menu();
            }
        }
    }
    
    //clock() function will start a timer using Timer class object 
    //Note: Timer will end at 5 mins.
    //Note : Code will be autosubmitted when time is over
    public void clock(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                System.out.println("\n\nTIME IS UP ! AUTOSUBMITTING ......");
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                System.out.print("\033[H\033[2J"); 
                System.out.flush();

                check();
                displayScore();
            }
        };
        
        timer.schedule(task,300000);
       
    }

}

//User class 
class User {
    private int id;
    private int pass;
    private String name;

    User(String name, int id,int pass) {
        this.name = name;
        this.id = id;
        this.pass = pass;
    }

    User() {
        
    }

    // getters
    public int getId() {
        return this.id;
    }

    public int getPass() {
        return this.pass;
    }

    public String getName() {
        return this.name;
    }

    // custom toString method
    public String toString() {
        return this.name;
    }
    
    //setters
    public void setName (String name){
        this.name =  name;
    }
    public void setPass(int pass){
        this.pass =  pass;
    } 
}
//Task 4 Online Examination  interface of internship in Oasis Infobyte