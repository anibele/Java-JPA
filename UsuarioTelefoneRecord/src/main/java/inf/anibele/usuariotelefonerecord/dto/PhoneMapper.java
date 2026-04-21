package inf.anibele.usuariotelefonerecord.dto;
import inf.anibele.usuariotelefonerecord.model.Phone;

public class PhoneMapper {

    public static Phone toEntity(CreatePhoneDTO dto) {
        Phone phone = new Phone();
        phone.setNumber(dto.number());
        return phone;
    }

    public static PhoneDTO toDTO(Phone phone) {
        return new PhoneDTO(phone.getId(), phone.getNumber());
    }
}
