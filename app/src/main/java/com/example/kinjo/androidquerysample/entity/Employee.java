package com.example.kinjo.androidquerysample.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;

/**
 * 社員情報エンティティ
 * Created by kinjo on 2015/07/09.
 */
@DatabaseTable(tableName = "employee")
@Data
public class Employee {

    /* Filed Name */
    public static final String ID = "id";
    public static final String CODE = "code";
    public static final String NAME = "name";

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String code;

    @DatabaseField
    private String name;

}
