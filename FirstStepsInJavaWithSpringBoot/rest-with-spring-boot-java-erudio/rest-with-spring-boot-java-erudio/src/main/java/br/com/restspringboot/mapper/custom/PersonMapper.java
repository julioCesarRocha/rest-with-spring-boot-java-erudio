package br.com.restspringboot.mapper.custom;

import br.com.restspringboot.data.vo.v2.PersonVOV2;
import br.com.restspringboot.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVO(Person person) {
        PersonVOV2 vo = new PersonVOV2();
        vo.setLastName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthday(new Date());
        vo.setGender(person.getGender());

        return vo;
    }

    public Person convertVOtoEntity(PersonVOV2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
//        entity.setBirthday(new Date());
        entity.setLastName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());

        return entity;
    }
}
