package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
}
public Message addMessage(Message message){
    return messageDAO.insertMessage(message);
}

public List<Message> getAllMessages(){
    return messageDAO.getAllMessages();
}

public List<Message> getMessagebyID(int message_id){
    return messageDAO.getMessagebyID(message_id);
}

public Message deleteMessage(int message_id){
    return messageDAO.deleteMessage(message_id);
}

public Message updateMessage(int message_id, Message message){
    return messageDAO.updateMessage(message_id, message);
}
    
public List<Message> getMessagebyUser(int account_id){
    return messageDAO.getMessagebyUser(account_id);
}

}
