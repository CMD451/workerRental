package indie.outsource.services;



import indie.outsource.exceptions.ResourceNotFoundException;
import indie.outsource.exceptions.WorkerRentedException;
import indie.outsource.model.Worker;
import indie.outsource.repositories.RentRepository;
import indie.outsource.repositories.WorkerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class WorkerService {

    @Inject
    WorkerRepository workerRepository;

    @Inject
    RentRepository rentRepository;

    public Worker findById(UUID id) {
        if(workerRepository.findById(id).isPresent()){
            return workerRepository.findById(id).get();
        }
        else
            throw new ResourceNotFoundException();
    }

    public List<Worker> findAll() {
        return (List<Worker>) workerRepository.findAll();
    }

    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Worker worker){
        if (workerRepository.findById(worker.getId()).isEmpty()){
            throw new ResourceNotFoundException("Worker with id " + worker.getId() + " not found");
        }
        return workerRepository.save(worker);
    }

    public void delete(UUID id) {
        if(workerRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException();
        }
        if(rentRepository.existsByWorker_IdAndEndDateIsNull(id)){
            throw new WorkerRentedException();
        }
       workerRepository.deleteById(id);
    }
}
