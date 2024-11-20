package indie.outsource.repositories;


import indie.outsource.model.Worker;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

public interface WorkerRepository extends BaseRepository<Worker, UUID> {

}
