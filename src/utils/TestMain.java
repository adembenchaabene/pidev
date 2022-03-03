package Utils;

import Modules.Admin;
import Modules.User;
import Utils.Enums.Roles;
import java.sql.SQLException;
import services.UserServices;



public class TestMain {
    public static void main(String[] args) {
      
       Admin admin=new Admin("test","test","test@email.com","testname",Roles.Admin);
        UserServices service=UserServices.getInstance();
        //service.updateUser(44444445,admin);
        System.out.println(service.getAll());
        //service.deleteUser(admin); 
        //System.out.println(service.getUser());
        
        
    /*
    UserServices us;
        us = new UserServices();
    try{
      
       System.out.println("TRI BY ID decroissant");
       System.out.println(us.sortedbyId());
        
        System.out.println("GetAll");
        System.out.println(us.getAll());
        
      
        System.out.println("FindByMail");
        System.out.println(us.findByMail("email@email.com"));
        
        
        
       
        System.out.println("FindByPrenom");
        System.out.println(us.findByPrenom("Abida"));
        
       
        System.out.println("FindByNom");
        System.out.println(us.findByName("test"));

    }
    catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
        
        */
        
    }
}
