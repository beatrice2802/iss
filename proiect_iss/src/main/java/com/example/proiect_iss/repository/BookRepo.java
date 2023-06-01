package com.example.proiect_iss.repository;

import com.example.proiect_iss.domain.Book;
import com.example.proiect_iss.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class BookRepo {
    static SessionFactory sessionFactory;

    public BookRepo() {
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

    public List<Book> findAll() {
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx= session.beginTransaction();
                List<Book> res= session.createQuery("from Book",Book.class).list();
//                for (Book book :  res) {
//                    System.out.println(book.getAuthor() +book.getTitle());
//                }
                tx.commit();
                return res;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la findAll " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public List<Book> findAllReturned() {
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx= session.beginTransaction();
                List<Book> res= session.createQuery("from Book as b where b.status =:searchEntity",Book.class)
                        .setString("searchEntity","returned")
                        .list();
//                for (Book book :  res) {
//                    System.out.println(book.getAuthor() +book.getTitle());
//                }
                tx.commit();
                return res;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la findAll " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public Book save(Book entity){
        try(Session session=sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx= session.beginTransaction();
                session.save(entity);
                tx.commit();
                return entity;
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

    public Book delete(Book entity){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Book res = session.createQuery("from Book as b where b.id =:searchEntity", Book.class)
                        .setInteger("searchEntity",entity.getId())
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(res);
                tx.commit();
                return res;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la stergere "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public Book update(Book entity){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Book book=session.load(Book.class,entity.getId());
                book.setStatus(entity.getStatus());
//                Book res = session.createQuery("from Book as b where b.id =:searchEntity", Book.class)
//                        .setInteger("searchEntity",entity.getId())
//                        .setMaxResults(1)
//                        .uniqueResult();
//                session.update(res);
                tx.commit();
                return entity;
            } catch (RuntimeException ex) {
                System.err.println("Eroare la update "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        try {
//
//            BookRepo test = new BookRepo();
//            Book a=new Book();
////            a.setId(7);
////            a.setStatus("returned");
////            var res=test.update(a);
////            System.out.println(res);
//
//        }catch (Exception e){
//            System.err.println("Exception "+e);
//            e.printStackTrace();
//        }finally {
//            close();
//        }
//    }
}
