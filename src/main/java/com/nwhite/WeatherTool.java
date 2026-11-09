package com.nwhite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class WeatherTool {
    private final WeatherService weatherService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherTool(WeatherConfigProperties props) {
        this.weatherService = new WeatherService(props);
    }

    @Tool(description = "Get the current weather conditions for the given city.")
    public WeatherService.Response currentWeatherFunction(
            @ToolParam(description = "City name, for example: London") String city
    ) {
        return weatherService.apply(new WeatherService.Request(city));
    }
}
