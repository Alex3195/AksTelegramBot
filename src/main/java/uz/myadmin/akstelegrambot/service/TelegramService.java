package uz.myadmin.akstelegrambot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.myadmin.akstelegrambot.botConfig.MyTelegramLongPollingBot;

@RequiredArgsConstructor
@Service
public class TelegramService {
    private final MyTelegramLongPollingBot telegramLongPollingBot;

    public void executeMessage(SendMessage message) throws TelegramApiException {
        telegramLongPollingBot.execute(message);
    }

    public void executeDocument(SendDocument document) throws TelegramApiException {
        telegramLongPollingBot.execute(document);
    }

    public void executePhoto(SendPhoto photo) throws TelegramApiException {
        telegramLongPollingBot.execute(photo);
    }
}
