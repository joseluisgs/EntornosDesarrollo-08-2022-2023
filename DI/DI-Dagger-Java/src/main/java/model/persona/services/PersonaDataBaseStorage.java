package model.persona.services;

import model.persona.model.Persona;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class PersonaDataBaseStorage implements IPersonaStorage {
    private final String id = UUID.randomUUID().toString();

    @Inject
    public PersonaDataBaseStorage() {

    }

    @Override
    public Persona save(Persona item) {
        System.out.println("PersonaDataBaseStorage.save() --> " + item);
        return item;
    }

    @Override
    public String toString() {
        return "PersonaDataBaseStorage{" +
                "id='" + id + '\'' +
                '}';
    }
}
