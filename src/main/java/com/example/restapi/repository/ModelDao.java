package com.example.restapi.repository;

import com.example.restapi.model.Model;

import java.io.IOException;

public interface ModelDao {

Model getModel(String name) throws Exception;
void changeSurname(String setSurname, String name) throws IOException;

}
