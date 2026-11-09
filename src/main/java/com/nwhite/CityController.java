package com.nwhite;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    private final ChatClient chatClient;
    private final WeatherTool weatherTool;

    public CityController(ChatClient.Builder builder, WeatherTool weatherTool) {
        this.chatClient = builder.
                build();
        this.weatherTool = weatherTool;
    }

    @GetMapping("/cities")
    public String cityFaq(@RequestParam String message) {
        return chatClient
                .prompt()
                .user(message)
                .tools(weatherTool)
                .call()
                .content();
    }
}
