package com.example.proiect_iss.repository;

import com.example.proiect_iss.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UserRepo {

    static SessionFactory sessionFactory;

    public UserRepo() {
        this.sessionFactory=sessionFactory;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e)
        {
            System.out.println("Exceptie: AICI " + e.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public User findOne(String username, String password) {
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx= session.beginTransaction();
                User res= (User) session.createQuery("from User as user where user.username like :searchUsername and user.password like :searchPassword").setString("searchUsername",username).setString("searchPassword",password).setMaxResults(1).uniqueResult();
                tx.commit();
                return res;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la findOne " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public User add(User entity) {
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx= session.beginTransaction();
                session.save(entity);
                tx.commit();
                //return entity;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return entity;
    }
    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
        try {

            UserRepo test = new UserRepo();
            User user=new User();
            user.setPassword("pass");
            user.setUsername("u");
            test.add((user));
            var a=test.findOne("u","pass");
            System.out.println(a);

        }catch (Exception e){
            System.err.println("Exception "+e);
            e.printStackTrace();
        }finally {
            close();
        }
    }
}





//package com.example.proiect_iss.repository;
//
//import com.example.proiect_iss.domain.User;
//import com.example.proiect_iss.utils.JdbcUtils;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Properties;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class UserRepo {
//    private JdbcUtils dbUtils;
//
//    private static final Logger logger= LogManager.getLogger();
//
//    public UserRepo(Properties props) {
//        logger.info("Initializing OrganizerDbRepo with properties: {} ",props);
//        dbUtils=new JdbcUtils(props);
//    }
//
//    public User findOne(String username, String password) {
//        logger.traceEntry();
//        User u = new User();
//        Connection con = dbUtils.getConnection();
//        try(PreparedStatement ps = con.prepareStatement("select is_librarian,cnp from users where username = ? and password = ?")){
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet result = ps.executeQuery();
//            if(result. next()) {
//                logger.traceEntry("Login reusit!");
//                u.setLibrarian(result.getBoolean("is_librarian"));
//                u.setUsername(username);
//                u.setPassword(password);
//                u.setCnp(result.getString("cnp"));
//                return u;
//            }
//        } catch (SQLException ex) {
//            logger.error(ex);
//            System.err.println("Error DB"+ex);
//        }
//        logger.traceExit(u);
//        return null;
//    }
//
//    public User add(User u) {
//        logger.traceEntry();
//        Connection con = dbUtils.getConnection();
//        try(PreparedStatement ps = con.prepareStatement("insert into users (cnp,name,address,phone,username,password,is_librarian) values (?,?,?,?,?,?,?)")){
//            ps.setString(1,u.getCnp());
//            ps.setString(2, u.getName());
//            ps.setString(3, u.getAddress());
//            ps.setString(4, u.getPhoneNumber());
//            ps.setString(5, u.getUsername());
//            ps.setString(6, u.getPassword());
//            if(u.getLibrarian() == null) ps.setBoolean(7,false);
//            else ps.setBoolean(7,u.getLibrarian());
//            ps.execute();
//            logger.traceEntry("Register reusit!");
//            return u;
//        } catch (SQLException ex) {
//            logger.error(ex);
//            System.err.println("Error DB"+ex);
//        }
//        return null;
//    }
//}
