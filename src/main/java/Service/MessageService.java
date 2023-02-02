package Service;

import DAO.MessageDAO;

public class MessageService {
    private MessageDAO messageDAO;

public MessageService(){
    messageDAO = new MessageDAO();
}

    
}
