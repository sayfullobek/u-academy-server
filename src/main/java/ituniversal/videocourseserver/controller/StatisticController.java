package ituniversal.videocourseserver.controller;

import ituniversal.videocourseserver.payload.StatisticDto;
import ituniversal.videocourseserver.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping
    public HttpEntity<?> getStatistic() {
        StatisticDto statisticDto = statisticService.statisticDto();
        return ResponseEntity.ok(statisticDto);
    }
}
