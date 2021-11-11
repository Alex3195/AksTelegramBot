package uz.myadmin.akstelegrambot.service;

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
        JSONObject data = new JSONObject();
        data.put("chatId", message.getChatId());
        data.put("contact", message.getContact().getPhoneNumber());
        data.put("userName", message.getFrom().getUserName());
        data.put("firstName", message.getFrom().getFirstName());
        data.put("lastName", message.getFrom().getLastName());
        data.put("status", Status.PASSIVE);

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

//    public void save(Message message) {
//        RestTemplate template = new RestTemplate();
//        BotUserModel model = new BotUserModel();
//        model.setChatId(message.getChatId());
//        model.setContact(message.getContact().getPhoneNumber());
//        model.setUserName(message.getFrom().getUserName());
//        model.setFirstName(message.getFrom().getFirstName());
//        model.setLastName(message.getFrom().getLastName());
//        model.setStatus(Status.PASSIVE);
//        template.postForEntity(url, model, BotUserModel.class);
////        template.getForObject(url, String.class);
//    }
}
