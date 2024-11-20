package indie.outsource.dtoMappers;


import indie.outsource.DTO.worker.CreateWorkerDTO;
import indie.outsource.DTO.worker.WorkerDTO;
import indie.outsource.model.Worker;

public final class WorkerMapper {
    public static WorkerDTO getWorkerDto(Worker worker) {
        return new WorkerDTO(worker.getName(), worker.getId());
    }

    public static Worker getWorker(CreateWorkerDTO createWorkerDTO) {
        Worker worker = new Worker();
        worker.setName(createWorkerDTO.getName());
        return worker;
    }
}
