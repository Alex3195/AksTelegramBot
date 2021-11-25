package uz.myadmin.akstelegrambot.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.function.ServerResponse;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.myadmin.akstelegrambot.constant.Status;
import uz.myadmin.akstelegrambot.model.BotUserModel;
import uz.myadmin.akstelegrambot.model.TelegramSyncReport;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class UsersSevice {
    private final WebClient webClient;

    public UsersSevice() {
        String baseUrl = "http://localhost:8280/bot/api";
        webClient = WebClient.create(baseUrl);
    }

    public Mono<Long> getChatId() {
        return this.webClient.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BotUserModel.class)
                .map(BotUserModel::getChatId);
    }

    public BotUserModel saveUser(Message message) {

        BotUserModel model = new BotUserModel();
        model.setChatId(message.getChatId());
        model.setUserName(message.getFrom().getUserName());
        model.setFirstName(message.getFrom().getFirstName());
        model.setLastName(message.getFrom().getLastName());
        model.setContact(message.getContact().getPhoneNumber());
        model.setStatus(Status.PASSIVE);

        return this.webClient.put().uri("/add")
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(model)
                .retrieve().onStatus(HttpStatus::isError, clientResponse -> {
                    return Mono.error(new Exception("error"));
                })
                .bodyToMono(BotUserModel.class).block();
    }

    public Long getNumberOfReport(Message message) {
        BotUserModel model = new BotUserModel();
        model.setChatId(message.getChatId());
        model.setUserName(message.getFrom().getUserName());
        model.setFirstName(message.getFrom().getFirstName());
        model.setLastName(message.getFrom().getLastName());
        return this.webClient.post().uri("/get-number-of-reports-not-sync")
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(model)
                .retrieve().onStatus(HttpStatus::isError, clientResponse -> {
                    return Mono.error(new Exception("error"));
                })
                .bodyToMono(Long.class).block();
    }

    public List<TelegramSyncReport> getAllReportByDealerId(Message message) {
        List<TelegramSyncReport> res = new ArrayList<>();
        BotUserModel model = new BotUserModel();
        model.setChatId(message.getChatId());
        Object[] array = this.webClient.post().uri("/get-all-reports-by-dealer")
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(model)
                .retrieve().onStatus(HttpStatus::isError, clientResponse -> {
                    return Mono.error(new Exception("Error"));
                }).bodyToMono(Object[].class).block();
        for (Object data : array) {
            TelegramSyncReport report = new TelegramSyncReport();
            Map<String,Object> obj = (LinkedHashMap) data;
            report.setProductName(obj.get("productName").toString());
            report.setOldDate(obj.get("oldDate").toString());
            report.setOldPrice(obj.get("oldPrice").toString());
            report.setLastDate(obj.get("lastDate").toString());
            report.setLastPrice(obj.get("lastPrice").toString());
            res.add(report);
        }
        return res;
    }
}
