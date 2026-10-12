package com.workintech.spring17challenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Grade {
    private int coefficient;
    private String note;
}