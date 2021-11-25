package uz.myadmin.akstelegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramSyncReport {
    private String productName;
    private String oldDate;
    private String oldPrice;
    private String lastDate;
    private String lastPrice;
}
