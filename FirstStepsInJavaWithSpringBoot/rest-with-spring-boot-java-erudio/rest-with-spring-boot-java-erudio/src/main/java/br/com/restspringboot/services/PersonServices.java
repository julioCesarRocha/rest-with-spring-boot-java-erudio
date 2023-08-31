package br.com.restspringboot.services;

import br.com.restspringboot.data.vo.v1.PersonVO;
import br.com.restspringboot.data.vo.v2.PersonVOV2;
import br.com.restspringboot.exceptions.ResourceNotFoundException;
import br.com.restspringboot.mapper.DozerMapper;
import br.com.restspringboot.mapper.custom.PersonMapper;
import br.com.restspringboot.model.Person;
import br.com.restspringboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper mapper;


    public List<PersonVO> findAll() {
        logger.info("Finding all person!");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
      }

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records find for this ID"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }


    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating one person with V2!");

        var entity = mapper.convertVOtoEntity(person);
        return mapper.convertEntityToVO(repository.save(entity));
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating one person!");

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records find for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records find for this ID"));
        repository.delete(entity);
    }
}

