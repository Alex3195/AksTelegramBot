package uz.myadmin.akstelegrambot.commands;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface CommandHandler <T>{
    void executeCommand(T t,String command) throws TelegramApiException;
}
