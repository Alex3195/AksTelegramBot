package uz.myadmin.akstelegrambot.commands;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public final class Commands {
    public static final String START = "/start";
    public static final String MENU = "Меню";
    public static final String DOC = "document";
    public static final String AUDIO = "audio";
    public static final String VIDEO = "video";
    public static final String MESSAGE = "message";
    public static final String SYNCHRONIZE = "Синхронизировать";
    public static final String REQUESTADMIN = "request";
    public static final String GET_ALL_SYNC = "Я хочу получить все";


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

        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();

        button.setText("Я хочу получить все");
        button1.setText("назад");
        row.add(button);

        row.add(button1);

        keyboard.add(row);

        markup.setKeyboard(keyboard);

        return markup;
    }

    public static ReplyKeyboardMarkup getReplyKeyboardMarkupBackToMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList();

        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();

        button.setText("Меню");
        button1.setText("назад");
        row.add(button);

        row.add(button1);

        keyboard.add(row);

        markup.setKeyboard(keyboard);

        return markup;
    }

    public static ReplyKeyboard getReplyKeyboardMarkupMenu() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList();

        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();

        button.setText("1");
        button1.setText("2");
        button2.setText("3");
        row.add(button);
        row.add(button1);
        row.add(button2);

        row1.add(new KeyboardButton("4"));
        row1.add(new KeyboardButton("5"));
        row1.add(new KeyboardButton("6"));

        row2.add(new KeyboardButton("7"));
        row2.add(new KeyboardButton("8"));
        row2.add(new KeyboardButton("9"));

        keyboard.add(row);
        keyboard.add(row1);
        keyboard.add(row2);
        markup.setKeyboard(keyboard);

        return markup;
    }
}
