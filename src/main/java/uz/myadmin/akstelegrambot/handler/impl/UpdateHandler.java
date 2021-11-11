package uz.myadmin.akstelegrambot.handler.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.myadmin.akstelegrambot.handler.Handler;
import uz.myadmin.akstelegrambot.service.TelegramService;

@Component
@RequiredArgsConstructor
public class UpdateHandler implements Handler<Update> {
    private final TelegramService telegramService;
    private final MessageHandler messageHandler;

    @Override
    public void handleMessage(Update update) throws TelegramApiException {
        if (update.hasMessage()) {
            messageHandler.handleMessage(update.getMessage());
        } else {
            telegramService.executeMessage(SendMessage
                    .builder()
                    .chatId(update.getMessage().getChatId().toString())
                    .text("no").replyToMessageId(update.getMessage().getMessageId())
                    .build());
        }
    }
}
