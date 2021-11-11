package uz.myadmin.akstelegrambot.commands;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public final class Commands {
    public static final String START = "/start";
    public static final String PHOTO = "photo";
    public static final String DOC = "document";
    public static final String AUDIO = "audio";
    public static final String VIDEO = "video";
    public static final String MESSAGE = "message";
    public static final String SYNCHRONIZE = "Синхронизировать";
    public static final String REQUESTADMIN = "request";


    public static ReplyKeyboardMarkup getShareContactKeyBoard() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(false);
        markup.setSelective(true);

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        KeyboardButton buttonAskContact = new KeyboardButton();
        buttonAskContact.setRequestContact(true);
        buttonAskContact.setText("Share contact");
        row.add(buttonAskContact);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }

    public static ReplyKeyboardMarkup getEmptyKeyboard() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(false);
        markup.setSelective(true);

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        KeyboardButton testButton = new KeyboardButton();
        testButton.setText("Синхронизировать");
        row.add(testButton);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }

    public static ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList();

        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        KeyboardButton button3 = new KeyboardButton();

        button.setText("photo");
        button1.setText("document");
        button2.setText("video");
        button3.setText("mp3");

        row.add(button);
        row1.add(button1);
        row2.add(button2);
        row3.add(button3);

        keyboard.add(row);
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        markup.setKeyboard(keyboard);

        return markup;
    }
}
