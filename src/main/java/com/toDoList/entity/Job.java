package com.toDoList.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Job {

    private long id;

    private String description;
}
