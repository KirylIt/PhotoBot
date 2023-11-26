import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
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

    public class NullPointerException extends RuntimeException{
        public NullPointerException() {
        }

        public NullPointerException(String message) {
            super(message);
        }
    }
    @Override
    public void onUpdateReceived(Update update) {

       // получение id фото
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
        }


        // загрузка в чат файла


        }
    }


