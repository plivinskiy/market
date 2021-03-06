package market.dto.assembler;

import market.domain.Contacts;
import market.domain.UserAccount;
import market.dto.UserDTO;
import market.rest.CartRestController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 *
 */
@Component
public class UserAccountDtoAssembler extends RepresentationModelAssemblerSupport<UserAccount, UserDTO> {

	public UserAccountDtoAssembler() {
		super(CartRestController.class, UserDTO.class);
	}

	@Override
	public UserDTO toModel(UserAccount userAccount) {
		UserDTO dto = instantiateModel(userAccount);
		dto.setEmail(userAccount.getEmail());
		dto.setPassword("hidden");
		dto.setName(userAccount.getName());
		dto.setPhone(userAccount.getContacts().getPhone());
		dto.setAddress(userAccount.getContacts().getAddress());
		dto.add(linkTo(CartRestController.class).withRel("Shopping cart"));
		return dto;
	}

	public UserAccount toDomain(UserDTO user) {
		UserAccount userAccount = new UserAccount(user.getEmail(), user.getPassword(), user.getName(), true);
		userAccount.setContacts(new Contacts(userAccount, user.getPhone(), user.getAddress()));
		return userAccount;
	}
}
