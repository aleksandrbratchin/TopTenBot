package ru.aleksbratchin.TopTenBot.controller;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.aleksbratchin.TopTenBot.config.BotConfig;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        super(config.getToken());
        this.config = config;

        // Создание меню
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "Начальное меню"));
        commandList.add(new BotCommand("/new_game", "Cоздать новую игру"));
        commandList.add(new BotCommand("/find_game", "Найти игру"));
        try {
            this.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text = message.getText();
            System.out.println(text);
            long chatId = update.getMessage().getChatId();
            switch (text) {
                case "/start" -> {
                    sendMessage(chatId, "Welcome to the Telegram Bot!");
                }
                case "/stop" -> {
                }
                default -> {
                }
            }

        }
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }


    /***
     * Отправить сообщение пользователю
     */
    private void sendMessage(long chatId, String messageToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
