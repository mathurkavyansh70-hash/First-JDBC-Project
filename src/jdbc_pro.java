import java.lang.*;
import java.util.*;
import java.sql.*;
public class jdbc_pro {
     public static void addstudent(Connection con,Scanner sc) throws Exception{
        String sname;
        String email;
        int age;
        String course;
        System.out.println("enter the name: ");
        sname=sc.nextLine();
        System.out.println("enter the email address(unique): ");
        email=sc.nextLine();
        System.out.println("enter the course name");
        course=sc.nextLine();
        System.out.println("enter the  age: ");
        age=sc.nextInt();
        String sql="insert into studentdata(sname , email , age , course)values(?,?,?,?)";
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,sname);
        st.setString(2,email);
        st.setInt(3,age);
        st.setString(4,course);

        st.execute();
     }
     public static void viewallstudents(Connection con) throws Exception{
        String sql="select * from studentdata";
        Statement st=con.createStatement();
        ResultSet rst=st.executeQuery(sql);
        while(rst.next()){
            System.out.println("Sid : "+rst.getInt(1));
            System.out.println("Name : "+rst.getString(2));
            System.out.println("Email : "+ rst.getString(3));
            System.out.println("Age : "+rst.getInt(4));
            System.out.println("Course Name :"+rst.getString(5));
            System.out.println("\n\n");
        }
        
    }
    public static void viewAstudentBySID(Connection con , Scanner sc)throws Exception{
        int id;
        System.out.println("enter the sid of the student you want to see");
        id=sc.nextInt();
        sc.nextLine(); //buffer clear 
        String sql="select * from studentdata where sid=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1,id);
        ResultSet rst = pst.executeQuery();
        while(rst.next()){
            System.out.println("Sid : "+rst.getInt(1));
            System.out.println("Name : "+rst.getString(2));
            System.out.println("Email : "+ rst.getString(3));
            System.out.println("Age : "+rst.getInt(4));
            System.out.println("Course Name :"+rst.getString(5));
            System.out.println("\n\n");
        }
        
     }
     public static void updatestudentdata(Connection con,Scanner sc)throws Exception{
        int id;
        System.out.println("enter the sid of the student you want to make changes");
        id=sc.nextInt();
        sc.nextLine();
        System.out.println("What do you want to update(choose any one at a time please)?");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. Age");
        System.out.println("4. Course Name");
        int choice = sc.nextInt();
        String sql="";
        sc.nextLine();
        PreparedStatement pst=con.prepareStatement(sql);
        switch(choice){
            case 1:
                System.out.println("enter the new name : ");
                String name=sc.nextLine();
                sql="update studentdata set sname=? where sid=?";
                pst=con.prepareStatement(sql);
                pst.setInt(2,id);
                pst.setString(1,name);
                pst.execute();
                break;
                case 2:
                    System.out.println("enter the new email : ");
                    String emailid=sc.nextLine();
                    sql="update studentdata set email=? where sid=?";
                    pst=con.prepareStatement(sql);
                    pst.setInt(2,id);
                    pst.setString(1,emailid);
                    pst.execute();
                    break;
                    case 3:
                        System.out.println("enter the new Age : ");
                        int Age=sc.nextInt();
                        sql="update studentdata set age=? where sid=?";
                        pst=con.prepareStatement(sql);
                        pst.setInt(2,id);
                        pst.setInt(1,Age);
                        pst.execute();
                        break;
                        case 4:
                            System.out.println("enter the new Course Name : ");
                            String Course=sc.nextLine();
                            sql="update studentdata set course=? where sid=?";
                            pst=con.prepareStatement(sql);
                            pst.setInt(2,id);
                            pst.setString(1,Course);
                            pst.execute();
                            break;
                            default:
                                System.out.println("Invalid input");
        }

     }
     public static void deletedata(Connection con, Scanner sc)throws Exception{
        int id;
        System.out.println("enter the id of the student to dlete the data of ");
        id=sc.nextInt();
        sc.nextLine();
        String sql="delete from studentdata where sid=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1,id);
        pst.execute();

     }
    public static void main(String[] args)throws Exception {
        
        String url="jdbc:postgresql://localhost:5432/project";
        String username="postgres";
        String Pass="707416";
        Connection con=DriverManager.getConnection(url,username,Pass);
        System.out.println("connections established");
        Scanner sc=new Scanner(System.in);
boolean running = true;

while (running) {

    System.out.println("\n1. Add Student");
    System.out.println("2. View Students");
    System.out.println("3. view a Student by ID");
    System.out.println("4. Update Student");
    System.out.println("5. Delete Student");
    System.out.println("6. Exit");

    int choice = sc.nextInt();
    sc.nextLine();

    switch (choice) {
        case 1:
            addstudent(con, sc);
            break;
        case 2:
            viewallstudents(con);
            break;
        case 3:
            viewAstudentBySID(con, sc);
            break;
        case 4:
            updatestudentdata(con, sc);
            break;
        case 5:
            deletedata(con, sc);
        case 6:
            running = false;
            break;
        default:
            System.out.println("Invalid choice");
    }
}
    con.close();
    sc.close();
    System.out.println("connections are closed now");
        
    }
}
                    