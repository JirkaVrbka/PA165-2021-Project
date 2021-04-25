package cz.idomatojde.facade;

import cz.idomatojde.dto.timetable.AddTimetableDTO;
import cz.idomatojde.dto.user.RegisterUserDTO;
import cz.idomatojde.dto.timetable.TimetableDTO;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ContextConfiguration("classpath:applicationConfig.xml")
@EnableAutoConfiguration
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TimetableFacadeTest extends AbstractTestNGSpringContextTests {

    @Inject
    TimetableFacade timetableFacade;

    @Inject
    UserFacade userFacade;

    //@Test
    void firstTest() {
        RegisterUserDTO userDto = new RegisterUserDTO();
        userDto.setUsername("testUser");
        userDto.setPassword("hashhhhh");
        userDto.setName("John");
        userDto.setSurname("Doe");
        userDto.setPhoneNumber("+420123456789");
        userDto.setEmail("a@a.a");

        userFacade.registerUser(userDto);

        AddTimetableDTO dto = new AddTimetableDTO();
        dto.setUserId(1);
        dto.setYear(2030);
        dto.setWeek(40);

        long id = timetableFacade.addTimetable(dto);

        TimetableDTO timetableDTO = timetableFacade.getTimetable(id);
    }

    //@Test
    void secondTest() {
        long timetableId = 1;

        TimetableDTO timetableDTO = timetableFacade.getTimetable(timetableId);

        assertThat(timetableDTO.getYear()).isEqualTo(2030);
    }
}
