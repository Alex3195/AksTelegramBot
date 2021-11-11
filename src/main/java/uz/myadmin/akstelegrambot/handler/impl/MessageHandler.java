package uz.myadmin.akstelegrambot.handler.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.myadmin.akstelegrambot.commands.GeneralCommandHandler;
import uz.myadmin.akstelegrambot.handler.Handler;

@Component
@RequiredArgsConstructor
public class MessageHandler implements Handler<Message> {
    private final GeneralCommandHandler generalCommandHandler;
    private final ContactHandler contactHandler;

    @Override
    public void handleMessage(Message message) throws TelegramApiException {
        if (message.hasText()) {
            generalCommandHandler.executeCommand(message, message.getText());
        } else {
            if (message.hasContact()) {
                contactHandler.handleMessage(message);
            }
        }
    }
}
