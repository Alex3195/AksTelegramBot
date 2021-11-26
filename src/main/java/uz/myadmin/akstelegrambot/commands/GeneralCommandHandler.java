package uz.myadmin.akstelegrambot.commands;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.myadmin.akstelegrambot.model.TelegramSyncReport;
import uz.myadmin.akstelegrambot.service.TelegramService;
import uz.myadmin.akstelegrambot.service.UsersSevice;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GeneralCommandHandler implements CommandHandler<Message> {
    private final TelegramService telegramService;
    private final UsersSevice usersSevice;
    private Set<ReplyKeyboardMarkup> markupSet;

    @Override
    public void executeCommand(Message message, String command) throws TelegramApiException {
        markupSet = new HashSet<>();
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
                                .replyMarkup(Commands.getSyncAllReplyKeyboardMarkup())
                                .build()
                );
                markupSet.add(Commands.getSyncAllReplyKeyboardMarkup());
                break;
            case Commands.GET_ALL_SYNC:
                List<TelegramSyncReport> reportList = usersSevice.getAllReportByDealerId(message);
                for (TelegramSyncReport report : reportList) {
                    String messageText = "Название продукта: " + report.getProductName() + "\n"
                            + "Дата: " + report.getOldDate() + "\n"
                            + "Старая цена: " + report.getOldPrice() + "\n"
                            + "Дата: " + report.getLastDate() + "\n"
                            + "Последняя цена: " + report.getLastPrice();
                    telegramService.executeMessage(
                            SendMessage.builder()
                                    .text(messageText)
                                    .chatId(message.getChatId().toString())
                                    .replyMarkup(Commands.getMenuKeyboard())
                                    .build()
                    );
                }

                break;
            case Commands.MENU:
                telegramService.executeMessage(
                        SendMessage.builder()
                                .text("Menu")
                                .chatId(message.getChatId().toString())
                                .replyMarkup(Commands.getMenuKeyboard())
                                .build()
                );
                break;
            case Commands.REMAINING_GOODS:
                Map data = usersSevice.getReportRemainingGoods(message);
                String messageText = "Дата: " + data.get("date").toString() + " \n"
                        + "Дилер: " + data.get("dealerName").toString() + " \n"
                        + "Количество видов товаров: " + data.get("countProductTypes").toString() + "\n"
                        + "Сумма товара : " + data.get("countProducts") + "\n"
                        + "Сумма стоимости товара: " + data.get("sumProductPrice");
                telegramService.executeMessage(
                        SendMessage.builder()
                                .text(messageText)
                                .chatId(message.getChatId().toString())
                                .replyMarkup(Commands.getMenuKeyboard())
                                .build());
        }
    }
}
