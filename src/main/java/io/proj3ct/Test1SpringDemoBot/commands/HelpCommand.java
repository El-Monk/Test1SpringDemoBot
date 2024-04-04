package io.proj3ct.Test1SpringDemoBot.commands;

import io.proj3ct.Test1SpringDemoBot.model.UserRepository;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service(name = "/help")
public class HelpCommand extends Command{
    private final UserRepository userRepository;

    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities. \n\n" +
            "You can execute commands from the main menu in the left or by typing a command:\n\n" +
            "Type /start to see a welcome message \n\n" +
            "Type /mydata to see data stored about yourself\n\n" +
            "Type /help to see this message again";

    public HelpCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean exist(String str) {
        return str.equals("/help");
    }

    @Override
    public String explanation() {
        return "";
    }

    @Override
    public SendMessage process(Update update) {
        long chatId = update.getMessage().getChatId();
        return prepareAndSendMessage(chatId, HELP_TEXT);
    }
}
