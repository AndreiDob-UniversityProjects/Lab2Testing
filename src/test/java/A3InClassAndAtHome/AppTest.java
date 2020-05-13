package A3InClassAndAtHome;

import lab2testing.domain.Nota;
import lab2testing.domain.Student;
import lab2testing.domain.Tema;
import lab2testing.repository.NotaXMLRepository;
import lab2testing.repository.StudentXMLRepository;
import lab2testing.repository.TemaXMLRepository;
import lab2testing.service.Service;
import lab2testing.validation.NotaValidator;
import lab2testing.validation.StudentValidator;
import lab2testing.validation.TemaValidator;
import lab2testing.validation.Validator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    Service service;

    /**
     * Rigorous Test :-)
     */
    @Before
    public void doBefore() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme_test.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note_test.xml");


        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);
        service.deleteStudent("2");
        service.deleteTema("2");
//        for (Student s : service.findAllStudents()) {
//            service.deleteStudent(s.getID());
//        }
//        for (Tema t:service.findAllTeme()){
//            service.deleteTema(t.getID());
//        }

        service.saveStudent("1", "aaa", 932);
        service.saveTema("1", "Scurt.", 10, 2);
    }

    @Test
    public void studentIdIsEmpty() {
        assertEquals(2, service.saveStudent("", "aaa", 932));
    }

    @Test
    public void addAssignmentWithEmptyId() {
        assertEquals(2 , service.saveTema("", "Scurt.", 10, 2));
    }

    @Test
    public void addGradeOk() {
        assertEquals(0 ,service.saveNota("1", "1", 9, 11, "bun"));
    }

    @Test
    public void bigBangIntegrationTest() {
        assertEquals(1,service.saveStudent("2", "bbb", 932));
        assertEquals(1,service.saveTema("2", "lung.", 10, 2));
        assertEquals(2,service.saveNota(null, "2", 9, 11, "si mai bun"));

    }



}
