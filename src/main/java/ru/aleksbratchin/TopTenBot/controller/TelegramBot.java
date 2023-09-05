package ru.aleksbratchin.TopTenBot.service;

import lombok.Data;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.aleksbratchin.TopTenBot.config.BotConfig;

@Service
@Data
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(update.hasMessage() && message.hasText()) {
            String text = message.getText();

            switch (text){
                case "/start" -> {}
                case "/stop" -> {}
                    default -> {}
            }
            
        }
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

}
