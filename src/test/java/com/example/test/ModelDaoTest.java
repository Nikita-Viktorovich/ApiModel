package com.example.test;

import com.example.restapi.model.Model;
import com.example.restapi.repository.ModelDao;
import com.example.restapi.database.ModelService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

public class ModelDaoTest {

    @Mock
    private ModelDao dao;

    private final ModelService modelService;

    public ModelDaoTest(){
        MockitoAnnotations.initMocks(this);
        this.modelService= new ModelService(dao);
    }

    //comment this after first run
 /*   {
        modelService.createTable();
        modelService.insertRecord("nick", "lime");
        modelService.insertRecord("tony", "montana");
    }*/

    @Test
    public void getModelByName() throws Exception {
        given(dao.getModel("nick")).willReturn(
                new Model("nick","lime"));
    }

    @Test
    public void getNotExistModel() {
        Model m = modelService.getModel("nicky11");
        assertThat(m).isNull();
    }

    @Test
    public void check() throws Exception {
        given(dao.getModel(anyString())).willThrow(Exception.class);
    }

    @Test
    public void updateDataInModel() throws Exception {
        dao.changeSurname("free", "tony" );
        given(dao.getModel("tony")).willReturn(
                new Model("tony","free"));
    }


}
