package Zajecia.Repositories;

import Zajecia.domain.ConferenceApplication;

public interface ConferenceApplicationRepository {
    ConferenceApplication getApplicationByEmailAddress(String email);
    void add(ConferenceApplication application);
    int count();
}
