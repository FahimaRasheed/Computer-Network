
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class server {
    public static void main(String args[]) throws IOException {
        
      ServerSocket ss=new ServerSocket(4444); 
      while(true)

      {
          Socket so=ss.accept();
          //int a=getbalance("12345");
        System.out.println("client connected");      
        PrintStream ps;
        BufferedReader buffr=new BufferedReader(new InputStreamReader(so.getInputStream()));
        String str=buffr.readLine();  
        //System.out.println("client ");  
        String[] req=str.split(" ");
        for(String i:req)
            System.out.println(i); 
         ps=new PrintStream(so.getOutputStream());
        switch(req[1])
        {
            case "1":
                //System.out.println("client ");      
                deposit(req[0],req[2]);
                break;
            case "2":
                withdraw(req[0],req[2]);
                break;
            case "3":
                 int ar=getbalance(str);
                  ps.println(String.valueOf(ar));
                  break;
            default:
                ps.println("Wrong_command");
        
        }
       
      //  ps.println(String.valueOf(peri));
        ps.close();
      
      so.close(); 
        buffr.close();
       // ps.close();
       System.out.println("client disconnected");  
      }
    }
     public static void deposit(String acc,String amt){
       try
       {
           System.out.println(amt);
           String sql="select balance from login where accno=?";
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","codes22");
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,acc);
            ResultSet rs = st.executeQuery();            
            while(rs.next())
            {
           int balance=rs.getInt("balance");                     
           int amount=Integer.parseInt(amt);
//         Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","codes22");
            String sq="update login set balance=? where accno=?";
            PreparedStatement sta = conn.prepareStatement(sq);
                System.out.println(amount);
            sta.setString(1,String.valueOf(amount+balance));
            //String.valueOf(balance)
            sta.setString(2,acc);
           
            sta.executeUpdate();
           
            } 
            
      conn.close();
        }
        catch(Exception ex)
        {
          //  JOptionPane.showMessageDialog(null, ex);
        }
     }
      public static void withdraw(String acc,String amt){
          
          try
       {
           System.out.println(amt);
           String sql="select balance from login where accno=?";
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","codes22");
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,acc);
            ResultSet rs = st.executeQuery();            
            while(rs.next())
            {
           int balance=rs.getInt("balance");                     
           int amount=Integer.parseInt(amt);
//         Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","codes22");
            String sq="update login set balance=? where accno=?";
            PreparedStatement sta = conn.prepareStatement(sq);
                System.out.println(amount);
                if(balance>amount)
               {
            sta.setString(1,String.valueOf(balance-amount));
               }
            //String.valueOf(balance)
            sta.setString(2,acc);
           
            sta.executeUpdate();
           
            } 
            
      conn.close();
        }
        catch(Exception ex)
        {
          //  JOptionPane.showMessageDialog(null, ex);
        }
          
          
          
      }
     public static int getbalance(String str)
     {
         Integer bal=0;
         //String bal;
          try
        {
           // Integer acc=Integer.parseInt(str);
            String sql="select balance from login where accno=?";
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","codes22");
           PreparedStatement st = conn.prepareStatement(sql);
           st.setString(1,str);
           ResultSet rs = st.executeQuery();
           
            if(rs.next())
            {
               bal=rs.getInt("balance");
                
               System.out.println(bal);
               
            }    
            
            conn.close();         
            }
            catch(Exception ex)
            {
             // JOptionPane.showMessageDialog(null, ex);
            }
        return bal;
     } 
     
     }

