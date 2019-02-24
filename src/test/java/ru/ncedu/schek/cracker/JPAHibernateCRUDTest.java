package ru.ncedu.schek.cracker;

import org.junit.Assert;
import org.junit.Test;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.entities.Phone;
import ru.ncedu.schek.cracker.entities.Picture;

import java.util.List;

/**
 * Created by Admin on 24.02.2019.
 */

/*доделаю методы сервисов по ходу
* */
public class JPAHibernateCRUDTest extends JPAHibernateTest {
    @Test
    public void testGetObjectById_success() {
        Model model = em.find(Model.class, 1);
        Phone phone = em.find(Phone.class, 1);
        Picture picture= em.find(Picture.class,1);
        Assert.assertNotNull(model);
        Assert.assertNotNull(phone);
        Assert.assertNotNull(picture);
    }

    @Test
    public void testGetAll_success() {
        List<Model> models = em.createNamedQuery("Model.getAllModels", Model.class).getResultList();
        List<Phone> phones= em.createNamedQuery("Phone.getAllPhones", Phone.class).getResultList();
        List<Picture> pictures= em.createNamedQuery("Phone.getAllPhones", Picture.class).getResultList();
        Assert.assertEquals(1, models.size());
        Assert.assertEquals(1, phones.size());
        Assert.assertEquals(1, pictures.size());
    }

    @Test
    public void testPersist_success() {
        em.getTransaction().begin();
        Model m1 =new Model("Xiaomi Redmi Note 4S",10000,20000);
        Model m2 =new Model("Iphone 4S",10000,56000);
        Model m3=new Model("Meizu mini",9000,15600);
        em.persist(m1);
        em.persist(m2);
        em.persist(m3);

        Phone p1= new Phone(m1, 15000, "green");
        Phone p2= new Phone(m2, 43000, "yellow");
        Phone p3= new Phone(m3, 10000, "white");
        Phone p4= new Phone(m3, 12000, "black");

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);

        em.getTransaction().commit();
        //картинки пока не трогаем
        List<Model> models = em.createNamedQuery("Model.getAllModels", Model.class).getResultList();
        List<Phone> phones = em.createNamedQuery("Phone.getAllPhones", Phone.class).getResultList();

        Assert.assertNotNull(models);
        Assert.assertEquals(3, models.size());

        Assert.assertNotNull(phones);
        Assert.assertEquals(4, phones.size());
    }

    @Test
    public void testDelete_success(){
        Model model = em.find(Model.class, 1);
        Phone phone = em.find(Phone.class, 1);

        em.getTransaction().begin();
        em.remove(model);
        em.remove(phone);
        em.getTransaction().commit();

        List<Model> models = em.createNamedQuery("Model.getAllModels", Model.class).getResultList();
        List<Phone> phones = em.createNamedQuery("Phone.getAllPhones", Phone.class).getResultList();

        Assert.assertEquals(0, models.size());
        Assert.assertEquals(0, phones.size());
    }
}
