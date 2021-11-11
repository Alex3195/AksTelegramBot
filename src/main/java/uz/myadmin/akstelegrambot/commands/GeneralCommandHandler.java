package uz.myadmin.akstelegrambot.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.myadmin.akstelegrambot.service.TelegramService;

@Service
@RequiredArgsConstructor
public class GeneralCommandHandler implements CommandHandler<Message> {
    private final TelegramService telegramService;

    @Override
    public void executeCommand(Message message, String command) throws TelegramApiException {
        switch (command) {
            case Commands.START:
                telegramService.executeMessage(
                        SendMessage.builder()
                                .text("hi " + message.getFrom().getFirstName())
                                .chatId(message.getChatId().toString())
                                .replyMarkup(Commands.getShareContactKeyBoard())
                                .build()
                );
                break;
            case Commands.SYNCHRONIZE:
                telegramService.executeMessage(
                        SendMessage.builder()
                                .text("hi " + message.getFrom().getFirstName())
                                .chatId(message.getChatId().toString())
                                .replyMarkup(Commands.getReplyKeyboardMarkup())
                                .build()
                );
                break;
        }
    }
}
