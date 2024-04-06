package io.proj3ct.Test1SpringDemoBot.commands;

import io.proj3ct.Test1SpringDemoBot.model.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service("/register")
public class RegisterCommand extends Command{
    private final UserRepository userRepository;

    public RegisterCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean exist(String str) {
        return str.equals("/register");
    }

    @Override
    public String explanation() {
        return " ";
    }

    @Override
    public SendMessage process(Update update) {
        long chatId = update.getMessage().getChatId();
        return register(chatId);
    }

    private SendMessage register(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Do you really want to register");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        var yesButton = new InlineKeyboardButton();
        yesButton.setText("Yes");
        yesButton.setCallbackData("YES_BUTTON");

        var noButton = new InlineKeyboardButton();
        noButton.setText("No");
        noButton.setCallbackData("NO_BUTTON");

        rowInline.add(yesButton);
        rowInline.add(noButton);

        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }


}
