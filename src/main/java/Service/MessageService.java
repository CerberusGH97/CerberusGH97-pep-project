package Service;

import java.sql.SQLException;
import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
}
public Message addMessage(Message message){
    if(message.message_text.length()==0||message.message_text.length()>=255){
        return null;
    }else{
    return messageDAO.insertMessage(message);
    }
}

public List<Message> getAllMessages(){
    return messageDAO.getAllMessages();
}

public Message getMessagebyID(int message_id){
    return messageDAO.getMessagebyID(message_id);
}

public Message deleteMessage(int message_id) throws SQLException {
    Message beforeDelete = this.messageDAO.getMessagebyID(message_id);
    if (beforeDelete!=null){
            messageDAO.deleteMessage(message_id);
            return beforeDelete;
        } else if(beforeDelete==null){
            return null;
        }
    return null;
        
    }
    
    


public Message updateMessage(int message_id, Message message){
Message messagefromDB = this.messageDAO.getMessagebyID(message_id);
if(messagefromDB==null||message.message_text.length()==0||message.message_text.length()>=255){
    return null;
}  else {
    messageDAO.updateMessage(message_id, message);
    return messageDAO.getMessagebyID(message_id);}
}
    
public List<Message> getMessagebyUser(int account_id){
    return messageDAO.getMessagebyUser(account_id);
}

public List<Message> getNonExistentMessage (int message_id){
    return messageDAO.getNonExistentMessage(message_id);
}


}