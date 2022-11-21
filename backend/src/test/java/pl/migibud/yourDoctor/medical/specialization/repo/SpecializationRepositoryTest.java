package pl.migibud.yourDoctor.medical.specialization.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SpecializationRepositoryTest {

    @Autowired
    SpecializationRepository specializationRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void givenListOfSpecializations_when_then(){
        //given

        //when

        //then
    }

}