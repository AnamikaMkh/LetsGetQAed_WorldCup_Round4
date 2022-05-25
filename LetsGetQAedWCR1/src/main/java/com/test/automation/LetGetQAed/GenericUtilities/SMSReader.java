package com.test.automation.LetGetQAed.GenericUtilities;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;


public class SMSReader {

    private static final String ACCOUNT_SID = "ACe545e2c42fa8cf5297479079a2bb7aa6";
    private static final String AUTH_TOKEN = "4f299f0b1df661046087b92ea4a55c50";
    private static final String TO_PHONE_NUMBER = "+16506839527"; //test number where we receive SMS
    
    public SMSReader(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public static String getMessage(){
         String OTPMessage = getMessages()
                    .filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                    .filter(m -> m.getTo().equals(TO_PHONE_NUMBER))
                    .map(Message::getBody)
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);
        
 System.out.println("Message from test class of twilio is :"+ OTPMessage);
    	 
    	 StringBuilder OTP = new StringBuilder();
    	    for (int i = 0; i < OTPMessage.length(); i++) {
    	        if (Character.isDigit(OTPMessage.charAt(i))) {
    	            OTP.append(OTPMessage.charAt(i));
    	           
    	        } 
    	    }
    	    System.out.println("Your OTP code is: " + OTP.toString());
    	    
    	    return OTP.toString();
    	    
        
    }
    //deletes all the messages
   /* public void deleteMessages(){
        getMessages()
                .filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                .filter(m -> m.getTo().equals(TO_PHONE_NUMBER))
                .map(Message::getSid)
                .map(sid -> Message.deleter(ACCOUNT_SID, sid))
                .forEach(MessageDeleter::delete);

    }*/
    
    private static Stream<Message> getMessages(){
        ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }
    
  /*  public static void main(String[] args)
    {
    	// Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    	 String code= getMessage();
    	 System.out.println("Code from test class of twilio is :"+ code);
    	 
    	 StringBuilder myNumbers = new StringBuilder();
    	    for (int i = 0; i < code.length(); i++) {
    	        if (Character.isDigit(code.charAt(i))) {
    	            myNumbers.append(code.charAt(i));
    	            //System.out.println(code.charAt(i) + " is a digit.");
    	        } 
    	    }
    	    System.out.println("Your OTP code is: " + myNumbers.toString());
    }*/

}
