package ru.otus.spring2021.homework_3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Data
public class Quiz {
    private List<String> description;
}
