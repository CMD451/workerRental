package indie.outsource.WorkerRental;

import indie.outsource.user.CreateUserDTO;
import io.restassured.common.mapper.TypeRef;
import indie.outsource.WorkerRental.worker.WorkerRepository;
import indie.outsource.worker.CreateWorkerDTO;
import indie.outsource.worker.WorkerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static indie.outsource.WorkerRental.requests.UserRequests.getUsers;
import static indie.outsource.WorkerRental.requests.WorkerRequests.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WorkerTest {

    @Autowired
    WorkerRepository workerRepository;


    @BeforeEach
    public void setup(){
        workerRepository.deleteAll();
    }

    @Test
    void createWorkerTest(){
        WorkerDTO worker = createDefaultWorker();

        assertEquals("Adam", worker.getName());

        WorkerDTO worker2 = getWorker(worker.getId());

        assertEquals(worker.getName(), worker2.getName());
    }

    @Test
    void readWorkerTest(){
        WorkerDTO worker = createDefaultWorker();

        WorkerDTO worker2 = getWorker(worker.getId());

        assertEquals(worker.getName(), worker2.getName());
    }

    @Test
    void updateWorkerTest(){
        WorkerDTO worker = createDefaultWorker();

        updateWorker(worker.getId(), new CreateWorkerDTO("Marek"));
        WorkerDTO worker2 = getWorker(worker.getId());
        assertEquals("Marek", worker2.getName());
    }

    @Test
    void deleteWorkerTest(){
        WorkerDTO worker = createDefaultWorker();
        deleteWorker(worker.getId());

        List<WorkerDTO> workers = getWorkers();

        assertEquals(0, workers.size());
    }

    @Test
    void tooShortWorkerNameTest(){
        CreateWorkerDTO createWorkerDTO = new CreateWorkerDTO("A");
        given().contentType("application/json").
                body(createWorkerDTO).when().post("/workers").
                then().statusCode(400);
        assertEquals(0, getWorkers().size());
    }


}
