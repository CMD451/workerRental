package indie.outsource.documents;

import indie.outsource.documents.user.UserMgd;
import indie.outsource.model.Rent;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class RentMgd extends AbstractEntityMgd {

    @BsonProperty("startDate")
    LocalDateTime startDate;
    @BsonProperty("endDate")
    LocalDateTime endDate;
    @BsonProperty("worker")
    WorkerMgd worker;
    @BsonProperty(value = "user",useDiscriminator = true)
    UserMgd user;

    @BsonCreator
    public RentMgd(
            @BsonProperty("_id") UUID id,
            @BsonProperty("startDate") LocalDateTime startDate,
            @BsonProperty("endDate") LocalDateTime endDate,
            @BsonProperty("worker") WorkerMgd worker,
            @BsonProperty("user") UserMgd user) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.worker = worker;
        this.user = user;
    }

    public RentMgd(Rent rent){
        super(rent.getId());
        this.startDate = rent.getStartDate();
        this.endDate = rent.getEndDate();
        this.worker = new WorkerMgd(rent.getWorker());
        this.user = UserMgd.fromDomainModel(rent.getUser());
    }

    public Rent toDomainModel(){
        Rent rent = new Rent();
        rent.setId(getId());
        rent.setStartDate(getStartDate());
        rent.setEndDate(getEndDate());
        rent.setWorker(this.worker.toDomainModel());
        rent.setUser(this.user.toDomainModel());
        return rent;
    }
}
