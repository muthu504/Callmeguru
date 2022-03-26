package com.codebrains.callme;


import java.net.InetAddress;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Properties;
    import java.util.Date;
    import javax.mail.*;
    import javax.mail.internet.*;
    import javax.activation.*;

 public class Mailing {

        public static void mail(String mailto,int id,String name,String pwd)
        {

            
            String host = "smtp.gmail.com";
            String from = "codebrains5@gmail.com";
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", host);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");    //port is 587 for TLS  if you use SSL then port is 465
            props.put("mail.debug", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            boolean debug = true;
            javax.mail.Session session1 = javax.mail.Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("codebrains5@gmail.com", "code@brain");
                        }
                    });
            session1.setDebug(debug);
            try {
                Message msg = new MimeMessage(session1);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(mailto)};
                msg.setRecipients(Message.RecipientType.TO, address);
               
                msg.setSubject(" Hello   "+name);
                       
                msg.setSentDate(new Date());
                msg.setContent("Thank you for Registerd in Call Me Guru<br>"
                        + "Your Account Has been Activated. <br>"
                        + " your id is "+id+"<br>"
                                + "you can login with your<br> <b>username</b>="+name+"<br><b> password</b>=<style=color:blue> "+pwd+" <br> "
                                        + " Thank you.","text/html");
                Transport transport = session1.getTransport("smtp");
                transport.send(msg);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }  
           
        }
        

        
    