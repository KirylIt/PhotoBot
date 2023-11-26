import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.awt.DefaultKeyboardFocusManager.*;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "supers_photo_bot";
    }

    @Override
    public String getBotToken() {
        return "6660203187:AAHWQRHOvG-lExOFkxl9WJG-uEc29m3u0bs";
    }

    public class NullPointerException extends RuntimeException {
        public NullPointerException() {
        }

        public NullPointerException(String message) {
            super(message);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        // получение id фото
        // + Hockey - AgACAgIAAxkBAANXZWOgNU7-hCnIwBGzS1RpRNDYpKIAAsfXMRu-XxhLzOETWGNrfWIBAAMCAAN5AAMzBA
        // + Vape - AgACAgIAAxkBAANVZWOgErHs5XLCvT9s1aRo19gFpKsAAsTXMRu-XxhLwz9xPMufg-8BAAMCAAN5AAMzBA
        // + Просто я - AgACAgIAAxkBAANZZWOgTRyRHMU2rlIhIMVF8xtWorIAAsjXMRu-XxhLAT0ciU2Bqv4BAAMCAAN5AAMzBA
        // + Я и Вика - AgACAgIAAxkBAANbZWOgYtViVsChnXa4goszgCVS1IAAAsnXMRu-XxhLjnQ-L2623QMBAAMCAAN5AAMzBA
        // + Джава код - AgACAgIAAxkBAANdZWOgfoB50pIhjW2hVQM1t25UdzkAAsvXMRu-XxhLkh5BlI5G0S0BAAMCAAN4AAMzBA

        /*
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        long chat_id = update.getMessage().getChatId();
        List<PhotoSize> photos = update.getMessage().getPhoto();
        String id = photos.stream()
                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                .findFirst()
                .orElse(null).getFileId();
        sendMessage.setText(id);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/

        // загрузка в чат файла
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Vape"));
        keyboardRow1.add(new KeyboardButton("Hockey"));

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("Просто я"));
        keyboardRow2.add(new KeyboardButton("Я и Вика"));
        keyboardRow2.add(new KeyboardButton("Джава код"));


        List<KeyboardRow> list = new ArrayList<>();
        list.add(keyboardRow1);
        list.add(keyboardRow2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(list);
        sendMessage.setText(update.getMessage().getText());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        InputFile inputFile = new InputFile();

        switch (update.getMessage().getText()) {
            case "Vape":
                inputFile.setMedia("AgACAgIAAxkBAANVZWOgErHs5XLCvT9s1aRo19gFpKsAAsTXMRu-XxhLwz9xPMufg-8BAAMCAAN5AAMzBA");
                break;
            case "Hockey":
                inputFile.setMedia("AgACAgIAAxkBAANXZWOgNU7-hCnIwBGzS1RpRNDYpKIAAsfXMRu-XxhLzOETWGNrfWIBAAMCAAN5AAMzBA");
                break;
            case "Просто я":
                inputFile.setMedia("AgACAgIAAxkBAANZZWOgTRyRHMU2rlIhIMVF8xtWorIAAsjXMRu-XxhLAT0ciU2Bqv4BAAMCAAN5AAMzBA");
                break;
            case "Я и Вика":
                inputFile.setMedia("AgACAgIAAxkBAANbZWOgYtViVsChnXa4goszgCVS1IAAAsnXMRu-XxhLjnQ-L2623QMBAAMCAAN5AAMzBA");
                break;
            case "Джава код":
                inputFile.setMedia("AgACAgIAAxkBAANdZWOgfoB50pIhjW2hVQM1t25UdzkAAsvXMRu-XxhLkh5BlI5G0S0BAAMCAAN4AAMzBA");
                break;
        }
        sendPhoto.setPhoto(inputFile);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}


