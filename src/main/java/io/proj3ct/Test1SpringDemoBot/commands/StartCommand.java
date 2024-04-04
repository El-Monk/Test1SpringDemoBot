package io.proj3ct.Test1SpringDemoBot.commands;

import com.vdurmont.emoji.EmojiParser;
import io.proj3ct.Test1SpringDemoBot.model.User;
import io.proj3ct.Test1SpringDemoBot.model.UserRepository;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service(name = "/start")
public class StartCommand extends Command {
    private final UserRepository userRepository;

    public StartCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean exist(String str) {
        return str.equals("/start");
    }

    @Override
    public String explanation() {
        return "";
    }

    @Override
    public SendMessage process(Update update) {
        long chatId = update.getMessage().getChatId();
        registerUser(update.getMessage());
        return startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
    }

    private void registerUser(Message msg) {

        if (userRepository.findById(msg.getChatId()).isEmpty()) {
            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setUserName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
//            log.info("user saved: " + user);
        }
    }

    private SendMessage startCommandReceived(long chatId, String name) {

        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you!" + " :blush:");
//        log.info("Replied to user " + name);
        SendMessage message = prepareAndSendMessage(chatId, answer);

        return message;
    }
}
