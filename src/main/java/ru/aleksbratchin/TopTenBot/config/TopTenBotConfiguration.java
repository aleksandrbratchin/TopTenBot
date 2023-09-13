package ru.aleksbratchin.TopTenBot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.aleksbratchin.TopTenBot.controller.TelegramBot;

@Component
public class TopTenBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramBot telegramBot) throws TelegramApiException{
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(telegramBot);
        return api;
    }

}
