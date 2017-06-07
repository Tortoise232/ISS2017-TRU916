package ro.tru916.core.util;

import ro.tru916.core.model.Conference;
import ro.tru916.core.model.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Alex on 16.05.2017.
 */
public class EmailSender {
    public static boolean SendMail(String message, String to[]) {
        String from = "tru916ConfSys@gmail.com";
        String password = "WebConferenceISS";
        String host = "smtp.gmail.com";
        Properties props = System.getProperties();
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (int i = 0; i < toAddress.length; i++) {
                mimeMessage.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            mimeMessage.setSubject("Conference App");
            mimeMessage.setText(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return false;
    }

    public static void userRegistrationSuccess(User user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] to = {user.getEmail()};
                    EmailSender.SendMail("Welcome to WebConference "
                            + user.getName() + ", your account has been created with username "+user.getUsername()+" !", to);
                    System.out.println("Success, email sent to "+user.getUsername());
                }
                catch(Exception e) {
                    System.err.println("Error at sending email to "+user.getUsername());
                }
            }
        }).start();
    }

    public static void conferenceRegistrationSuccess(User user, Conference newConf){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] to = {user.getEmail()};
                    EmailSender.SendMail("Congratulations on organizing your first conference"
                            + user.getName() + ",  see the details of " + newConf.getName()+" at http://localhost:4200/conferences/" + newConf.getName(), to);
                    System.out.println("Success, email sent to "+user.getUsername());
                }
                catch(Exception e) {
                    System.err.println("Error at sending email to "+user.getUsername());
                }
            }
        }).start();
    }

    public static void conferenceAttending(User user, Conference conf){
        new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                String[] to = {user.getEmail()};
                EmailSender.SendMail("Hello "
                        + user.getName() + ",  we're glad you're attending " + conf.getName() +
                        "! See more details at http://localhost:4200/conferences/" + conf.getName(), to);
                System.out.println("Success, email sent to "+user.getUsername());
            }
            catch(Exception e) {
                System.err.println("Error at sending email to "+user.getUsername());
            }
        }
    }).start();
    }

    public static void conferenceReviewing(User user, Conference conf){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] to = {user.getEmail()};
                    EmailSender.SendMail("Hello "
                            + user.getName() + ",  you've been assigned by " + conf.getOwner().getName() +
                            " on the reviewing board for conference " + conf.getName() + "! See more details at http://localhost:4200/conferences/" + conf.getName(), to);
                    System.out.println("Success, email sent to "+user.getUsername());
                }
                catch(Exception e) {
                    System.err.println("Error at sending email to "+user.getUsername());
                }
            }
        }).start();
    }

}