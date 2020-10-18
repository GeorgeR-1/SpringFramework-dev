import interfaces.Mentor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.FullTimeMentor;
import services.PartTimeMentor;

public class CybertekApp {
    public static void main(String[] args) {

        //BeanFactory container = new ClassPathXmlApplicationContext("config.xml");

        ApplicationContext container = new ClassPathXmlApplicationContext("config.xml");

        Mentor mentor = (Mentor) container.getBean("fullTimeMentor");

        mentor.createAccount();

        Mentor mentor1 = (Mentor) container.getBean("partTimeMentor");

        mentor1.createAccount();

        Mentor mentor2 = container.getBean("fullTimeMentor", Mentor.class);

        mentor2.createAccount();

    }
}
