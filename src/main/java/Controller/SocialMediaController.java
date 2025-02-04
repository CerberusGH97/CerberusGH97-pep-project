package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

 

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::addAccountHandler);
        app.post("/login", this::loginAccountHandler);
        app.post("/messages", this::addMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("messages/{message_id}", this::getMessagebyIDHandler);
        app.delete("messages/{message_id}", this::deleteMessageHandler);
        app.patch("messages/{message_id}", this::updateMessageHandler);
        app.get("accounts/{account_id}/messages", this::getMessagebyUserHandler);
        
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void addAccountHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount!=null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        }else{
            ctx.status(400);
        }
        
    }

    private void loginAccountHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account login = mapper.readValue(ctx.body(),Account.class);
        Account newLogin = accountService.loginAccount(login);
        if(newLogin!=null){
            ctx.json(mapper.writeValueAsString(newLogin));
        }else{
            ctx.status(401);
        }

    }

    private void addMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage!=null){
            ctx.json(mapper.writeValueAsString(addedMessage));
        }else{
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx){
        ctx.json(messageService.getAllMessages());
    }

    private void getMessagebyIDHandler(Context ctx){
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message messagetest = messageService.getMessagebyID(message_id);
        if(messagetest!=null){
            ctx.json(messageService.getMessagebyID(message_id));
        }else{
            ctx.status(200);
            ctx.json("");
        }

        
    }

    private void deleteMessageHandler(Context ctx) throws SQLException {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        if(messageService.getNonExistentMessage(message_id).isEmpty()){
            ctx.status(200);
            ctx.json("");
        }else {ctx.json(messageService.deleteMessage(message_id));} 
        }
    

    private void updateMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessage(message_id, message);
        if(updatedMessage!=null){
            ctx.json(mapper.writeValueAsString(updatedMessage));
        }else{
            ctx.status(400);
        }

    }

    private void getMessagebyUserHandler(Context ctx){
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        ctx.json(messageService.getMessagebyUser(account_id));
    }

 }
