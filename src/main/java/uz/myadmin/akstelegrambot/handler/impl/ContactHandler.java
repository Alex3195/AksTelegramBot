package uz.myadmin.akstelegrambot.handler.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.myadmin.akstelegrambot.commands.Commands;
import uz.myadmin.akstelegrambot.constant.Status;
import uz.myadmin.akstelegrambot.handler.Handler;
import uz.myadmin.akstelegrambot.model.BotUserModel;
import uz.myadmin.akstelegrambot.service.TelegramService;
import uz.myadmin.akstelegrambot.service.UsersSevice;

@Service
@RequiredArgsConstructor
public class ContactHandler implements Handler<Message> {
    private final TelegramService telegramService;
    private final UsersSevice usersSevice;

    @Override
    public void handleMessage(Message message) throws TelegramApiException {
        BotUserModel model = usersSevice.saveUser(message);
        if (model.getStatus() == Status.PASSIVE) {
            telegramService.executeMessage(SendMessage
                    .builder()
                    .chatId(message.getChatId().toString())
                    .text("you are registered for being active ask for admin")
                    .replyToMessageId(message.getMessageId())
                    .replyMarkup(Commands.getEmptyKeyboard())
                    .build());
        }else {
            telegramService.executeMessage(SendMessage
                    .builder()
                    .chatId(message.getChatId().toString())
                    .text("you are already registered and activated")
                    .replyToMessageId(message.getMessageId())
                    .replyMarkup(Commands.getEmptyKeyboard())
                    .build());
        }
    }
}

