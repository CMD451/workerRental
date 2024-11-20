package indie.outsource.dtoMappers;


import indie.outsource.DTO.rent.CreateRentDTO;
import indie.outsource.DTO.rent.RentDTO;
import indie.outsource.model.Rent;

public final class RentMapper {
    public static RentDTO getRentDTO(Rent rent){
        return new RentDTO(rent.getStartDate(), rent.getEndDate(), rent.getUser().getId(), rent.getWorker().getId(), rent.getId());
    }

    static Rent getRentFromCreateRent(CreateRentDTO createRentDTO){
        Rent rent = new Rent();
        rent.setStartDate(createRentDTO.getStartDate());
        return rent;
    }

}
