package services;

import Entites.User;
import java.util.List;

public interface Iservices<T>
{

    public void addUser(User u);

    public List<User> getUser();

    public void updateUser(int id,User u);

    public void deleteUser(User u);
    
}
