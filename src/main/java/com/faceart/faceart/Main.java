package com.faceart.faceart;

import com.faceart.faceart.commands.createTable.CreateTable;
import com.faceart.faceart.commands.createTable.users.CreateTableUsers;

import java.util.ArrayList;

public class Main {
    public static void main(MysqlxDatatypes.Scalar.String[] args) throws InterruptedException {
        ArrayList<CreateTable> createDatabaseTables = new ArrayList<>();

        createDatabaseTables.add(new CreateTableUsers());
        createDatabaseTables.forEach(CreateTable::createTable);

        System.out.println("Database is set up");
    }
}
