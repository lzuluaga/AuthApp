package com.cedesistemas.authapp.services;


import com.cedesistemas.authapp.models.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Repository {

    private IServices iServices;

    public Repository() {
        ServicesFactory servicesFactory = new ServicesFactory();
        iServices = (IServices) servicesFactory.getInstanceService(IServices.class);
    }

    public User logIn(String user, String password) throws IOException {
        try {
            Call<User> call = iServices.logIn(user, password);
            Response<User> response = call.execute();
            if (response.errorBody() != null){
                throw defaultError();
            }else{
                return response.body();
            }
        }catch (IOException e){
            throw defaultError();
        }
    }

    private IOException defaultError(){
        return new IOException("Ha ocurrido un error");
    }


}
