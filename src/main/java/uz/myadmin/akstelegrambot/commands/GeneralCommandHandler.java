package uz.myadmin.akstelegrambot.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.myadmin.akstelegrambot.model.TelegramSyncReport;
import uz.myadmin.akstelegrambot.service.TelegramService;
import uz.myadmin.akstelegrambot.service.UsersSevice;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralCommandHandler implements CommandHandler<Message> {
    private final TelegramService telegramService;
    private final UsersSevice usersSevice;

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
                Long numberOfReports = usersSevice.getNumberOfReport(message);
                telegramService.executeMessage(
                        SendMessage.builder()
                                .text("У вас " + numberOfReports + " несинхронизированных продукта")
                                .chatId(message.getChatId().toString())
                                .replyMarkup(Commands.getReplyKeyboardMarkup())
                                .build()
                );
                break;
            case Commands.GET_ALL_SYNC:
                List<TelegramSyncReport> reportList = usersSevice.getAllReportByDealerId(message);
                for (TelegramSyncReport report : reportList) {
                    String messageText = "Product name: " + report.getProductName() + "\n"
                            + "Date : " + report.getOldDate() + "\n"
                            + "Old price: " + report.getOldPrice() + "\n"
                            + "Date : " + report.getLastDate() + "\n"
                            + "Last price: " + report.getLastPrice();
                    telegramService.executeMessage(
                            SendMessage.builder()
                                    .text(messageText)
                                    .chatId(message.getChatId().toString())
                                    .replyMarkup(Commands.getReplyKeyboardMarkupBackToMenu())
                                    .build()
                    );
                }
                break;
            case Commands.MENU:
                telegramService.executeMessage(
                        SendMessage.builder()
                                .chatId(message.getChatId().toString())
                                .replyMarkup(Commands.getReplyKeyboardMarkupMenu())
                                .build()
                );

        }
    }
}
