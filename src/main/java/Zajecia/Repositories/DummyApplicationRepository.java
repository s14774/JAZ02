package Zajecia.Repositories;

import Zajecia.domain.ConferenceApplication;

import java.util.ArrayList;
import java.util.List;

public class DummyApplicationRepository implements ConferenceApplicationRepository{

    private static List<ConferenceApplication> db = new ArrayList<>();

    @Override
    public ConferenceApplication getApplicationByEmailAddress(String email) {
        for(ConferenceApplication application : db){
            if(application.getEmail().equalsIgnoreCase(email))
                return application;
        }
        return null;
    }

    @Override
    public void add(ConferenceApplication application) {
        db.add(application);
    }

    @Override
    public int count() {
        return db.size();
    }
}
