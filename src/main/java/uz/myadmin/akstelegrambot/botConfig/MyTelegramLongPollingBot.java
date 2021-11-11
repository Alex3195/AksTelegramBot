package uz.myadmin.akstelegrambot.botConfig;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.myadmin.akstelegrambot.handler.impl.UpdateHandler;

import javax.annotation.Resource;

@Component
public class MyTelegramLongPollingBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.name}")
    private String userName;
    @Value("${telegram.bot.token}")
    private String token;
    @Resource
    private UpdateHandler updateHandler;

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.handleMessage(update);

    }
}
