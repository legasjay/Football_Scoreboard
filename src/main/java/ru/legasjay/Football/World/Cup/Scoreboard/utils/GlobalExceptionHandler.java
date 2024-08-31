package ru.legasjay.Football.World.Cup.Scoreboard.utils;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    public String handleTeamNotFound(TeamNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error"; // Возвращает страницу с соответствующим сообщением
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public String handleMatchNotFound(MatchNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error"; // Возвращает страницу с соответствующим сообщением
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception e, Model model) {
        model.addAttribute("errorMessage", "Произошла ошибка: " + e.getMessage());
        return "error"; // Возвращает стандартный обработчик ошибок
    }
}
