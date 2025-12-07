 package dao;

import java.util.List;

public interface IDao<T>{
     
    boolean create(T c);
    
    boolean update (T c);
    
    boolean delete (T c);
    
    T findById (int id);
    
    List<T> findAll();
    

}
