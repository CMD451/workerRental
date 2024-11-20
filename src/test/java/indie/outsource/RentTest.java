package indie.outsource;


import indie.outsource.DTO.rent.CreateRentDTO;
import indie.outsource.DTO.rent.RentDTO;
import indie.outsource.DTO.user.UserDTO;
import indie.outsource.DTO.worker.WorkerDTO;
import indie.outsource.repositories.RentRepository;
import indie.outsource.repositories.UserRepository;
import indie.outsource.repositories.WorkerRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;

import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static indie.outsource.RentRequests.createRent;
import static indie.outsource.UserRequests.activateUser;
import static indie.outsource.UserRequests.createDefaultUser;
import static indie.outsource.WorkerRequests.createDefaultWorker;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class RentTest {

    @Inject
    UserRepository userRepository;
    @Inject
    WorkerRepository workerRepository;
    @Inject
    RentRepository rentRepository;

    @BeforeEach
    @AfterEach
    public void teardown(){
        rentRepository.deleteAll();
        userRepository.deleteAll();
        workerRepository.deleteAll();
    }

    @Test
    void rentTest(){
        UserDTO user = createDefaultUser();
        activateUser(user.getId());

        WorkerDTO worker = createDefaultWorker();

        CreateRentDTO createRentDTO = new CreateRentDTO(LocalDateTime.now().plusHours(2));
        RentDTO rent = createRent(user.getId(), worker.getId(), createRentDTO);

        assertEquals(1,get("/rents").as(new TypeRef<List<RentDTO>>() {}).size());
    }

    @Test
    void createRentForRentedWorkerTest(){
        UserDTO user = createDefaultUser();
        activateUser(user.getId());

        WorkerDTO worker = createDefaultWorker();

        CreateRentDTO createRentDTO = new CreateRentDTO(LocalDateTime.now().plusHours(2));
        RentDTO rent = createRent(user.getId(), worker.getId(), createRentDTO);

        CreateRentDTO createRentDTO2 = new CreateRentDTO(LocalDateTime.now().plusHours(20));
        given().contentType("application/json").
                body(createRentDTO2).when().post("/rents/users/{clientId}/workers/{workerId}", user.getId(), worker.getId()).
                then().statusCode(409);

        assertEquals(1,get("/rents").as(new TypeRef<List<RentDTO>>() {}).size());
    }
}
